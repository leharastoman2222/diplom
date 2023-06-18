package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AllTraningsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alltrainings_activity);
        getSupportActionBar().hide();
    }
    public void onClick_letters(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AllTraningsActivity.this, TrainingActivity.class));
                finish();
            }
        },0);
    }
    public void onClick_words(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AllTraningsActivity.this, WordsTrainingActivity.class));
                finish();
            }
        },0);
    }
    public void onClick_sentence(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AllTraningsActivity.this, SentenceTrainingActivity.class));
                finish();
            }
        },0);
    }
    public void onClick_numbers(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AllTraningsActivity.this, NumberTrainingActivity.class));
                finish();
            }
        },0);
    }
    public void onClick_symbols(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent( AllTraningsActivity.this, SymbolsTrainingActivity.class));
                finish();
            }
        },0);
    }
    public void onClick_back(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AllTraningsActivity.this, MenuActivity.class));
                finish();
            }
        },0);
    }
}
