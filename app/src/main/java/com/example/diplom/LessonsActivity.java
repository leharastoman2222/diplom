package com.example.diplom;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LessonsActivity extends AppCompatActivity {

    private TextView textView, textView2;
    private Button soundBbtn, soundBtnPrivet;
    private MediaPlayer mediaplayerB, mediaplayerPrivet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons_activity);
        getSupportActionBar().hide();

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        soundBbtn = findViewById(R.id.soundBtn);
        soundBtnPrivet = findViewById(R.id.soundBtnPrivet);
        mediaplayerB = MediaPlayer.create(this, R.raw.b_morse_code1);
        mediaplayerPrivet = MediaPlayer.create(this, R.raw.privet);
        soundBtnPrivet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSoundPrivet();
            }
        });

        soundBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSoundB();
            }
        });

        textView.setText("Азбука Морзе - это система кодирования и передачи информации с помощью комбинаций точек, тире и пробелов. Она была разработана Сэмюэлем Морзе и его соавторами в 1830-х и 1840-х годах для использования в телеграфной связи.\n" +
                "\n" +
                "Основными элементами Азбуки Морзе являются два сигнала: точка (.) и тире (-). Каждая буква русского алфавита, цифра и некоторые специальные символы (например, знаки препинания) представлены уникальной последовательностью точек и тире, которые называются кодом Морзе. Например, буква \"A\" кодируется как \".-\", а буква \"Б\" - \"-...\".\n" +
                "\n" + "Так же вы можете совместить несколько букв чтобы получилось слово например \"Привет\" - \".--..-....--.-\".\n ");
        textView2.setText("Азбука Морзе была широко использована в телеграфной связи до развития более современных технологий. В простейшем варианте передачи сигналов Азбукой Морзе, оператор нажимал на кнопку, чтобы создать электрический контакт и генерировал сигналы, соответствующие коду Морзе выбранного символа. Получатель, наблюдая или слушая эти сигналы, декодировал их, чтобы восстановить оригинальное сообщение.\n" +
                "\n" +
                "Азбука Морзе стала основой для развития других форм кодирования и передачи информации, таких как аудио- и видеосигналы, используемые в радиосвязи. Она все еще используется в некоторых областях, например, в радиолюбительском и военном сообщении, а также как учебный инструмент для изучения радиосвязи и морских сигналов.\n" +
                "\n" +
                "Символы Азбуки Морзе также были приспособлены для использования в виде световых морзянок, сигналов дыма, флагов и других средств коммуникации в случаях, когда электричество не было доступно или нежелательно.\n" +
                "\n" +
                "В целом, Азбука Морзе является важным историческим достижением в области коммуникации и имеет большое значение для понимания развития связи на протяжении времени.");
    }

    private void playSoundPrivet() {
        if (mediaplayerPrivet.isPlaying()){
            mediaplayerPrivet.stop();
            mediaplayerPrivet.release();
            mediaplayerPrivet = MediaPlayer.create(this, R.raw.privet);
        }
        mediaplayerPrivet.start();
    }

    private void playSoundB() {
        if (mediaplayerB.isPlaying()){
            mediaplayerB.stop();
            mediaplayerB.release();
            mediaplayerB = MediaPlayer.create(this, R.raw.b_morse_code1);
        }
        mediaplayerB.start();
    }

}
