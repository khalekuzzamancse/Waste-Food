package com.example.wastefood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class DrawerDonarBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    public void setContentView(View view){
        drawerLayout=(DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_donar_base,null);
        FrameLayout container=drawerLayout.findViewById(R.id.activitycontainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar=drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView=drawerLayout.findViewById(R.id.nav_view_donar);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId()==R.id.home){
            Intent intent=new Intent(this,Homepage.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        if(item.getItemId()==R.id.profile){
            Intent intent=new Intent(this,DonarProfile.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        if(item.getItemId()==R.id.editprofile){
            Intent intent=new Intent(this,DonarProfile.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        if(item.getItemId()==R.id.index){
            Intent intent=new Intent(this,DonarHomePage.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        if(item.getItemId()==R.id.logout){
            Intent intent=new Intent(this,Homepage.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        return false;
    }

    protected void allocatedActivityTitle(String titleString){
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(titleString);
        }
    }
}