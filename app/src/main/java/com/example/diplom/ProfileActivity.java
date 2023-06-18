package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileNameTextView, wordsScoreTextView, lettersScoreTextView, sentenceScoreTextView, numbersScoreTextView, symbolsScoreTextView ;
    private String username;
    private int wordsScore, lettersScore,sentencesScore,numbersScore,symbolsScore;
    private FirebaseAuth authProfile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        getSupportActionBar().hide();

        profileNameTextView = findViewById(R.id.profile_name);
        wordsScoreTextView = findViewById(R.id.wordsScoreTextView);
        lettersScoreTextView = findViewById(R.id.lettersScoreTextView);
        sentenceScoreTextView = findViewById(R.id.sentencesScoreTextView);
        numbersScoreTextView = findViewById(R.id.numbersScoreTextView);
        symbolsScoreTextView = findViewById(R.id.symbolsScoreTextView);

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        if (firebaseUser == null){
            Toast.makeText(this, "Что то пошло не так!", Toast.LENGTH_SHORT).show();
        }else{
            showUserProfile(firebaseUser);
        }
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();

        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Users");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User readUserDetails = snapshot.getValue(User.class);
                if (readUserDetails != null){
                    username = readUserDetails.getUsername();
                    wordsScore = readUserDetails.getWordsScore();
                    lettersScore = readUserDetails.getLettersScore();
                    sentencesScore = readUserDetails.getSentencesScore();
                    numbersScore = readUserDetails.getNumbersScore();
                    symbolsScore = readUserDetails.getSymbolsScore();

                    profileNameTextView.setText(username);
                    wordsScoreTextView.setText("Баллы за тренировку слов:" + wordsScore);
                    lettersScoreTextView.setText("Баллы за тренировку букв:" + lettersScore);
                    sentenceScoreTextView.setText("Баллы за тренировку предложений:" + sentencesScore);
                    numbersScoreTextView.setText("Баллы за тренировку цифр:" + numbersScore);
                    symbolsScoreTextView.setText("Баллы за тренировку символов:" + symbolsScore);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Что то пошло не так1", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onClick_back(View view){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ProfileActivity.this, MenuActivity.class));
            }
        },0);
    }
}
