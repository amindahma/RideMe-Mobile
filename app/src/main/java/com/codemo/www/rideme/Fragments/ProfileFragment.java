package com.codemo.www.rideme.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codemo.www.rideme.FragmentNavigator;
import com.codemo.www.rideme.MainActivity;
import com.codemo.www.rideme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private static MainActivity main;
    private EditText nameText;
    private EditText nicText;
    private Button saveBtn;

    private static final String TAG = "Profilepragment";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        nameText = (EditText) view.findViewById(R.id.nameText);
        nicText = (EditText) view.findViewById(R.id.nicText);
        saveBtn = (Button) view.findViewById(R.id.saveBtn);
        Log.d(TAG, "view is creating................................");
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.saveUser(nameText.getText().toString(), nicText.getText().toString());
                Log.d(TAG, "user is saving  "+nameText.getText().toString()+"  " + nicText.getText().toString());
                FragmentNavigator.navigateTo("homeFragment");
            }
        });
        return view;
    }



    public static MainActivity getMain() {
        return ProfileFragment.main;
    }

    public static void setMain(MainActivity main) {
        ProfileFragment.main = main;
    }
}
