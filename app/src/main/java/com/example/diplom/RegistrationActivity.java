package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        etUsername = findViewById(R.id.Username);
        etEmail = findViewById(R.id.Email);
        etPassword = findViewById(R.id.Password);
        etConfirmPassword = findViewById(R.id.ConfirmPassword);
        btnRegister = findViewById(R.id.regbutton);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString() ;
                String confirmPassword = etConfirmPassword.getText().toString();
                // Проверки регистрации
                if (TextUtils.isEmpty(username)){
                    etUsername.setError("Нужно имя пользователя");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    etPassword.setError("Нужен пароль");
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)){
                    etConfirmPassword.setError("Подтвердите парооль");
                    return;
                }
                if (!password.equals(confirmPassword)){
                    etConfirmPassword.setError("Пароль не совпадают");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    etEmail.setError("Нужен Email");
                    return;
                }
                if(password.length() < 6){
                    etPassword.setError("Пароль должен быть длиннее 6 символов");
                    return;
                }

                // Регистрация
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        // Добавление имени и электронной почты в Realtime Database
                        User user = new User(username, email);
                        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Users");
                        if (firebaseUser != null) {
                            referenceProfile.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        //Переход в другое окно и оповещение о успешной регистрации
                                        Toast.makeText(RegistrationActivity.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegistrationActivity.this, MenuActivity.class);
                                        finish();
                                    } else {
                                        // Оповещение об ошибке
                                        Toast.makeText(RegistrationActivity.this, "Регистрация не прошла успешно!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });

            }
        });
    }


    // Переход на предыдущее окно
    public void onClick_back(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(RegistrationActivity.this, ReglogActivity.class));

            }
        }, 0);
    }

}
