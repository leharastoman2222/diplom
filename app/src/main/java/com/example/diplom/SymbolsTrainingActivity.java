package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SymbolsTrainingActivity extends AppCompatActivity {


    private TextView morseLetterTextView;
    private EditText morseCodeEditText;
    private TextView scoreTextView;
    private String[] morseLetters;
    private String[] morseCodes;

    private String[] inputText;

    private Button clearBtn, checkButton, dotButton, dashButton, endBtn;
    private int currentLetterIndex;
    private int symbolsScore;
    private List<String> lettersList;
    private List<String> morseCodesList;
    private DatabaseReference symbolsReference;
    private FirebaseAuth symbolsAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symbolstraining_activity);
        getSupportActionBar().hide();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        symbolsReference = database.getReference("Users");
        symbolsAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = symbolsAuth.getCurrentUser();

        inputText = new String[]{
                ".", "-"
        };

        morseLetterTextView = findViewById(R.id.symorseLetterTextView);
        morseCodeEditText = findViewById(R.id.symorseCodeEditText);
        scoreTextView = findViewById(R.id.syscoreTextView);
        checkButton = findViewById(R.id.sycheckButton);
        dotButton = findViewById(R.id.sydotButton);
        clearBtn = findViewById(R.id.syclearBtn);
        dashButton = findViewById(R.id.sydashButton);

        endBtn = findViewById(R.id.endBtn);
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = firebaseUser.getUid();

                DatabaseReference referenceLetters = FirebaseDatabase.getInstance().getReference("Users");
                referenceLetters.child(userID).child("symbolsScore").setValue(symbolsScore);
                Toast.makeText(SymbolsTrainingActivity.this, "Тренировка завершена", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SymbolsTrainingActivity.this, AllTraningsActivity.class));
                        finish();
                    }
                },0);

            }
        });

        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = morseCodeEditText.getText().toString();
                String newText = inputText + ".";
                morseCodeEditText.setText(newText);
            }
        });

        dashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = morseCodeEditText.getText().toString();
                String newText = inputText + "-";
                morseCodeEditText.setText(newText);
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morseCodeEditText.setText("");
            }
        });
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
        morseLetters = new String[]{
                "!", "@","$","&","(",")","_","-",",",".",":"
        };

        morseCodes = new String[]{
                "-.-.--", ".--.-.", "...-..-", ".-...", "-.--.","-.--.-", "..--.-", "-....-", "--..--", ".-.-.-","---..."
        };
        lettersList = Arrays.asList(morseLetters);
        morseCodesList = Arrays.asList(morseCodes);

        long seed = System.nanoTime();
        Collections.shuffle(lettersList, new Random(seed));
        Collections.shuffle(morseCodesList, new Random(seed));

        currentLetterIndex = 0;
        symbolsScore = 0;
        updateScore();
        showNextMorseLetter();
    }

    private void checkAnswer() {
        String userCode = morseCodeEditText.getText().toString().trim();
        String correctCode = morseCodes[currentLetterIndex];

        if (userCode.equals(correctCode)) {
            Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
            symbolsScore+= 2;
        } else {
            Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
        }

        currentLetterIndex = (currentLetterIndex + 1) % lettersList.size();;
        updateScore();
        showNextMorseLetter();
    }

    private void updateScore() {
        String scoreText = "Баллы: " + symbolsScore;
        scoreTextView.setText(scoreText);

    }

    private void showNextMorseLetter() {
        if (currentLetterIndex < morseLetters.length) {
            String letter = morseLetters[currentLetterIndex];
            morseLetterTextView.setText(letter);
            morseCodeEditText.setText("");
        } else {
            Toast.makeText(this, "Тренировка завершена", Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SymbolsTrainingActivity.this, AllTraningsActivity.class));
                    finish();
                }
            },0);
        }
    }
}
