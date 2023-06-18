package com.example.diplom;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        getSupportActionBar().hide();
    }
    public void onClick_exit(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MenuActivity.this, ReglogActivity.class));
            }
        }, 0);
    }
    public void onClick_manual(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MenuActivity.this, ManualActivity.class));
            }
        }, 0);
    }
    public void onClick_lessons(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MenuActivity.this, LessonsActivity.class));
            }
        },0);
    }
    public void onClick_alltrainings(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MenuActivity.this, AllTraningsActivity.class));
            }
        }, 0);
    }
    public void onClick_leaders(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MenuActivity.this, LeadersActivity.class));
            }
        },0);
    }
    public void onClick_profile(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MenuActivity.this, ProfileActivity.class));
            }
        },0);
    }
}
