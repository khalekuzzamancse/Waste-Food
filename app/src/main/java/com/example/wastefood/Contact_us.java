package com.example.wastefood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wastefood.databinding.ActivityAboutBinding;
import com.example.wastefood.databinding.ActivityContactUsBinding;

public class Contact_us extends DrawerBaseActivity {
    ActivityContactUsBinding activityContactUsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityContactUsBinding= ActivityContactUsBinding.inflate(getLayoutInflater());
        setContentView(activityContactUsBinding.getRoot());
        allocatedActivityTitle("Contact Us");
    }
}