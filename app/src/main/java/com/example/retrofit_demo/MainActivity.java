package com.example.retrofit_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.retrofit_demo.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import Fragments.Add_Product_Fragment;
import Fragments.Home_Fragment;
import Fragments.Inventory_Fragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TextView headername,headeremail;
    ImageView headerimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View headerview = binding.navigationView.getHeaderView(0);
        headerimage = headerview.findViewById(R.id.headerimage);
        headername = headerview.findViewById(R.id.headername);
        headeremail = headerview.findViewById(R.id.headeremail);

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                if(item.getItemId()==R.id.home)
                {
                    addfragment(new Home_Fragment());
                    binding.drawerlayout.close();
                }
                if(item.getItemId()==R.id.addproduct){
                    addfragment(new Add_Product_Fragment());
                    binding.drawerlayout.close();
                }
                if(item.getItemId()==R.id.inventory){
                    addfragment(new Inventory_Fragment());
                    binding.drawerlayout.close();
                }
                if(item.getItemId()==R.id.signout){
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

    };


    private void addfragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framlayout,fragment);
        transaction.commit();
    }
}