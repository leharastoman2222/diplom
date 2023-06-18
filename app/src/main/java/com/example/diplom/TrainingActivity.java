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

public class TrainingActivity extends AppCompatActivity {

    private TextView morseLetterTextView;
    private EditText morseCodeEditText;
    private TextView scoreTextView;
    private String[] morseLetters;
    private String[] morseCodes;

    private String[] inputText;
    private Button clearBtn, checkButton, dotButton, dashButton, endBtn;
    private int currentLetterIndex;
    private int lettersScore;
    private DatabaseReference lettersReference;
    private FirebaseAuth lettersAuth;
    private List<String> lettersList;
    private List<String> morseCodesList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_activiy);
        getSupportActionBar().hide();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        lettersReference = database.getReference("Users");
        lettersAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = lettersAuth.getCurrentUser();

        inputText = new String[]{
                ".", "-"
        };

        morseLetterTextView = findViewById(R.id.morseLetterTextView);
        morseCodeEditText = findViewById(R.id.morseCodeEditText);
        scoreTextView = findViewById(R.id.scoreTextView);
        checkButton = findViewById(R.id.checkButton);
        dotButton = findViewById(R.id.dotButton);
        clearBtn = findViewById(R.id.clearBtn);
        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = morseCodeEditText.getText().toString();
                String newText = inputText + ".";
                morseCodeEditText.setText(newText);
            }
        });
        endBtn = findViewById(R.id.endBtn);
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = firebaseUser.getUid();

                DatabaseReference referenceLetters = FirebaseDatabase.getInstance().getReference("Users");
                referenceLetters.child(userID).child("lettersScore").setValue(lettersScore);
                Toast.makeText(TrainingActivity.this, "Тренировка завершена", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(TrainingActivity.this, AllTraningsActivity.class));
                        finish();
                    }
                },0);

            }
        });
        dashButton = findViewById(R.id.dashButton);
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
                "А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М",
                "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ",
                "Ы", "Ь", "Э", "Ю", "Я"
        };

        morseCodes = new String[]{
                ".-", "-...", ".--", "--.", "-..", ".",".", "...-", "--..", "..", ".---", "-.-", ".-..", "--", "-.",
                "---", ".--.", ".-.", "...", "-", "..-", "..-.", "....", "-.-.", "---.", "----", "--.-.", ".--.-.", "-.--",
                "-..-", "...-...", "..--", ".-.-"
        };

        lettersList = Arrays.asList(morseLetters);
        morseCodesList = Arrays.asList(morseCodes);

        long seed = System.nanoTime();
        Collections.shuffle(lettersList, new Random(seed));
        Collections.shuffle(morseCodesList, new Random(seed));

        currentLetterIndex = 0;
        lettersScore = 0;
        updateScore();
        showNextMorseLetter();

    }

    private void showNextMorseLetter() {
        if (currentLetterIndex < morseLetters.length) {
            String letter = morseLetters[currentLetterIndex];
            morseLetterTextView.setText(letter);
            morseCodeEditText.setText("");
        }
    }
    private void checkAnswer() {
        String userCode = morseCodeEditText.getText().toString().trim();
        String correctCode = morseCodes[currentLetterIndex];

        if (userCode.equals(correctCode)) {
            Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
            lettersScore++;
        } else {
            Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
        }

        currentLetterIndex = (currentLetterIndex + 1) % lettersList.size();
        updateScore();
        showNextMorseLetter();
    }
    private void updateScore() {
        String scoreText = "Баллы: " + lettersScore;
        scoreTextView.setText(scoreText);
    }

}
