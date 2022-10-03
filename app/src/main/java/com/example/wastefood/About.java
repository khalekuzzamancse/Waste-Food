package com.example.wastefood;


import android.os.Bundle;

import com.example.wastefood.databinding.ActivityAboutBinding;

public class About extends DrawerBaseActivity {
    ActivityAboutBinding activityAboutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutBinding= ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(activityAboutBinding.getRoot());
        allocatedActivityTitle("About");
    }
}