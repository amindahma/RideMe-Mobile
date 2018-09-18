package com.codemo.www.rideme;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.codemo.www.rideme.Fragments.HistoryFragment;
import com.codemo.www.rideme.Fragments.HomeFragment;
import com.codemo.www.rideme.Fragments.ProfileFragment;

/**
 * Created by aminda on 9/17/2018.
 */

public class FragmentNavigator {

    private static FragmentManager manager;

    public static void navigateTo(String fragment){

        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment == "homeFragment") {
            if (manager.findFragmentByTag("homeFragment") == null) {
                transaction.add(R.id.screen_area, new HomeFragment(), "homeFragment");
            } else {
                transaction.show(manager.findFragmentByTag("homeFragment"));
                Log.d("rht", "aaaaaaaaaaaaaaaaaaaa.....resume home ....aaaaaaaaaaaaaaaaaaaaaa***");
            }
            if (manager.findFragmentByTag("profileFragment") != null) {
                transaction.hide(manager.findFragmentByTag("profileFragment"));
                Log.d("rht", "aaaaaaaaaaaaaaaaaaaa.....hide profileFragment ....aaaaaaaaaaaaaaaaaaaaaa***");
            }
            if(manager.findFragmentByTag("historyFragment") != null){
                transaction.hide(manager.findFragmentByTag("historyFragment"));
                Log.d("rht","aaaaaaaaaaaaaaaaaaaa.....hide historyFragment ....aaaaaaaaaaaaaaaaaaaaaa***");
            }
        }
        else if(fragment == "profileFragment"){
            if(manager.findFragmentByTag("profileFragment") == null){
                transaction.add(R.id.screen_area, new ProfileFragment(), "profileFragment");
            }else{
                transaction.show(manager.findFragmentByTag("profileFragment"));
                Log.d("rht","aaaaaaaaaaaaaaaaaaaa.....resume profileFragment ....aaaaaaaaaaaaaaaaaaaaaa***");
            }
            if(manager.findFragmentByTag("homeFragment") != null){
                transaction.hide(manager.findFragmentByTag("homeFragment"));
                Log.d("rht","aaaaaaaaaaaaaaaaaaaa.....hide home ....aaaaaaaaaaaaaaaaaaaaaa***");
            }
            if(manager.findFragmentByTag("historyFragment") != null){
                transaction.hide(manager.findFragmentByTag("historyFragment"));
                Log.d("rht","aaaaaaaaaaaaaaaaaaaa.....hide historyFragment ....aaaaaaaaaaaaaaaaaaaaaa***");
            }
        }
        else if(fragment == "historyFragment"){
            if(manager.findFragmentByTag("historyFragment") == null){
                transaction.add(R.id.screen_area, new HistoryFragment(), "historyFragment");
            }else{
                transaction.show(manager.findFragmentByTag("historyFragment"));
                Log.d("rht","aaaaaaaaaaaaaaaaaaaa.....resume historyFragment ....aaaaaaaaaaaaaaaaaaaaaa***");
            }
            if(manager.findFragmentByTag("homeFragment") != null){
                transaction.hide(manager.findFragmentByTag("homeFragment"));
                Log.d("rht","aaaaaaaaaaaaaaaaaaaa.....hide home ....aaaaaaaaaaaaaaaaaaaaaa***");
            }
            if (manager.findFragmentByTag("profileFragment") != null) {
                transaction.hide(manager.findFragmentByTag("profileFragment"));
                Log.d("rht", "aaaaaaaaaaaaaaaaaaaa.....hide profileFragment ....aaaaaaaaaaaaaaaaaaaaaa***");
            }
        }

        transaction.commit();
    }

    public static void setManager(FragmentManager manager) {
        FragmentNavigator.manager = manager;
    }

}
