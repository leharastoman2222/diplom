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

public class SentenceTrainingActivity extends AppCompatActivity {

    private TextView morseLetterTextView;
    private EditText morseCodeEditText;
    private TextView scoreTextView;
    private String[] morseLetters;
    private String[] morseCodes;

    private String[] inputText;

    private Button clearBtn, checkButton, dotButton, dashButton, slashButton, endBtn;
    private int currentLetterIndex;
    private int sentenceScore;
    private List<String> lettersList;
    private List<String> morseCodesList;
    private DatabaseReference sentenceReference;
    private FirebaseAuth sentenceAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sentencetraining_activity);
        getSupportActionBar().hide();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        sentenceReference = database.getReference("Users");
        sentenceAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = sentenceAuth.getCurrentUser();

        inputText = new String[]{
                ".", "-", "/"
        };

        morseLetterTextView = findViewById(R.id.smorseLetterTextView);
        morseCodeEditText = findViewById(R.id.smorseCodeEditText);
        scoreTextView = findViewById(R.id.sscoreTextView);
        checkButton = findViewById(R.id.scheckButton);
        dotButton = findViewById(R.id.sdotButton);
        clearBtn = findViewById(R.id.sclearBtn);
        dashButton = findViewById(R.id.sdashButton);
        slashButton = findViewById(R.id.slashButton);

        endBtn = findViewById(R.id.endBtn);
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = firebaseUser.getUid();

                DatabaseReference referenceLetters = FirebaseDatabase.getInstance().getReference("Users");
                referenceLetters.child(userID).child("sentencesScore").setValue(sentenceScore);
                Toast.makeText(SentenceTrainingActivity.this, "Тренировка завершена", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SentenceTrainingActivity.this, AllTraningsActivity.class));
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
        
        slashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = morseCodeEditText.getText().toString();
                String newText = inputText + "/";
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
                "Как дела?", "Что нового?", "Кофе пожалуйста", "Я люблю программирование",
        };

        morseCodes = new String[]{
                "-.-.--.-/-....-...-..--..", "---.----/-.---.-------.---..--..", "-.----..-../.--.---...-.-.-....-.---...-.-",
                ".-.-/.-....---....-....--/.--..-.-----..-..-----...-.---.--.--...."
        };
        lettersList = Arrays.asList(morseLetters);
        morseCodesList = Arrays.asList(morseCodes);

        long seed = System.nanoTime();
        Collections.shuffle(lettersList, new Random(seed));
        Collections.shuffle(morseCodesList, new Random(seed));

        currentLetterIndex = 0;
        sentenceScore = 0;
        updateScore();
        showNextMorseLetter();

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
                    startActivity(new Intent(SentenceTrainingActivity.this, AllTraningsActivity.class));
                    finish();
                }
            }, 0);
        }
    }

    private void updateScore() {
        String scoreText = "Баллы: " + sentenceScore;
        scoreTextView.setText(scoreText);
    }

    private void checkAnswer() {
        String userCode = morseCodeEditText.getText().toString().trim();
        String correctCode = morseCodes[currentLetterIndex];

        if (userCode.equals(correctCode)) {
            Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
            sentenceScore+= 3;
        } else {
            Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
        }

        currentLetterIndex = (currentLetterIndex + 1) % lettersList.size();;
        updateScore();
        showNextMorseLetter();
    }

}
