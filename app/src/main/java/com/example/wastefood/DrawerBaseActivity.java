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

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    public void setContentView(View view){
        drawerLayout=(DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base,null);
        FrameLayout container=drawerLayout.findViewById(R.id.activitycontainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar=drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView=drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(item.getItemId()==R.id.chat){
            Intent intent=new Intent(this,About.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        else if(item.getItemId()==R.id.contactuS){
            Intent intent=new Intent(this,Contact_us.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        else if(item.getItemId()==R.id.loGIn){
            Intent intent=new Intent(this,SignUp.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
        else if(item.getItemId()==R.id.ragister){
            Intent intent=new Intent(this,Registration.class);
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