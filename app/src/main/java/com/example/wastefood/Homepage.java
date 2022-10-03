package com.example.wastefood;

import android.content.Intent;
import android.os.Bundle;

import com.example.wastefood.databinding.ActivityHomepageBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends DrawerBaseActivity {
    ActivityHomepageBinding activityHomepageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomepageBinding=ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(activityHomepageBinding.getRoot());
        allocatedActivityTitle("WasteFood");




    }

}