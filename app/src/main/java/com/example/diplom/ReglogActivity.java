package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReglogActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button regButton, enterButton;
    private EditText adminEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reglog_activity);
        getSupportActionBar().hide();
        Button regButton = findViewById(R.id.register);
        Button enterButton = findViewById(R.id.enter);
        EditText adminEdit = findViewById(R.id.adminEdit);

        String[] userTypes = {"Пользователь", "Администратор"};

        Spinner spinner = (Spinner) findViewById(R.id.spinnerdostup);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selecterItem = adapterView.getItemAtPosition(position).toString();
                if (selecterItem.equals("Администратор")){
                    adminEdit.setVisibility(View.VISIBLE);
                    regButton.setVisibility(View.GONE);
                    enterButton.setVisibility(View.GONE);
                } else{
                    adminEdit.setVisibility(View.GONE);
                    regButton.setVisibility(View.VISIBLE);
                    enterButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adminEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after ) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String enteretText = s.toString();
                if (enteretText.equals("swap3")){
                    Intent intent = new Intent(ReglogActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public void onClick_registration(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ReglogActivity.this, RegistrationActivity.class));
            }
        }, 0);
    }
    public void onClick_login(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ReglogActivity.this, LoginActivity.class));
                finish();
            }
        }, 0);
    }



}
