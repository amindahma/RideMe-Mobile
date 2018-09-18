package com.codemo.www.rideme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.codemo.www.rideme.Fragments.HistoryFragment;
import com.codemo.www.rideme.Fragments.HomeFragment;
import com.codemo.www.rideme.Fragments.ProfileFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private Fragment fragment = null;
    private String username;
    private String nic;
    private String driverInfo;
    private boolean active = false;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        manager = getSupportFragmentManager();
        FragmentNavigator.setManager(manager);
        transaction = manager.beginTransaction();
        transaction.add(R.id.screen_area, new HomeFragment(), "homeFragment");
        transaction.add(R.id.screen_area, new ProfileFragment(), "profileFragment");
        transaction.add(R.id.screen_area, new HistoryFragment(), "historyFragment");
        HomeFragment.setMain(this);
        ProfileFragment.setMain(this);
        HistoryFragment.setMain(this);

        SharedPreferences sharedpreferences = getSharedPreferences(String.valueOf(R.string.user_details), Context.MODE_PRIVATE);
        if(!sharedpreferences.getString(String.valueOf(R.string.USER_USERNAME),String.valueOf(R.string.notLogged)).equals(String.valueOf(R.string.notLogged))){
            setUsername(sharedpreferences.getString(String.valueOf(R.string.USER_USERNAME),String.valueOf(R.string.notLogged)));
            setNic(sharedpreferences.getString(String.valueOf(R.string.USER_NIC),String.valueOf(R.string.notLogged)));
            setActive(true);
//            navigationView.getMenu().findItem(R.id.nav_logout).setTitle("Log Out");
            FragmentNavigator.navigateTo("homeFragment");
            Log.d(TAG, "user logeed in"+ getUsername() + " NIC "+ getNic());
//            startDriverClient();
        }else{
            Log.d(TAG, "user not logeed in");
            FragmentNavigator.navigateTo("profileFragment");
//            navigationView.getMenu().findItem(R.id.nav_start).setVisible(false);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            FragmentNavigator.navigateTo("homeFragment");
        } else if (id == R.id.nav_gallery) {
            FragmentNavigator.navigateTo("profileFragment");
        } else if (id == R.id.nav_slideshow) {
            FragmentNavigator.navigateTo("historyFragment");
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showFragment(Fragment fragment){
        if(fragment != null){
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.screen_area, fragment);
            ft.commit();
        }

    }

    public void saveUser(String username, String nic) {
        setUsername(username);
        setNic(nic);
        SharedPreferences sharedpreferences = getSharedPreferences(String.valueOf(R.string.user_details), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(String.valueOf(R.string.USER_USERNAME), username);
        editor.putString(String.valueOf(R.string.USER_NIC), nic);
        editor.apply();
//        navigationView.getMenu().findItem(R.id.nav_start).setVisible(true);
//        navigationView.getMenu().findItem(R.id.nav_logout).setTitle("Log Out");
    }



    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
