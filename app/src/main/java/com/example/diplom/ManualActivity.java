package com.example.diplom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManualActivity extends AppCompatActivity {

    private ListView listView;
    private String morseCodeMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_activity);
        getSupportActionBar().hide();

        listView = findViewById(R.id.listView);

        // Создание и заполнение списка символов и их кода Морзе
        List<String> characters = new ArrayList<>();
        characters.add("А         .-");
        characters.add("Б       -...");
        characters.add("В        .--");
        characters.add("Г        --.");
        characters.add("Д        -..");
        characters.add("Е          .");
        characters.add("Ж       ...-");
        characters.add("З       --..");
        characters.add("И         ..");
        characters.add("Й       .---");
        characters.add("К        -.-");
        characters.add("Л       .-..");
        characters.add("М         --");
        characters.add("Н         -.");
        characters.add("О        ---");
        characters.add("П       .--.");
        characters.add("Р        .-.");
        characters.add("С        ...");
        characters.add("Т          -");
        characters.add("У        ..-");
        characters.add("Ф       ..-.");
        characters.add("Х       ....");
        characters.add("Ц       -.-.");
        characters.add("Ч       ---.");
        characters.add("Ш       ----");
        characters.add("Щ       --.-");
        characters.add("Ъ       --.--");
        characters.add("Ы       -.--");
        characters.add("Ь       -..-");
        characters.add("Э       ..-..");
        characters.add("Ю       ..--");
        characters.add("Я       .-.-");

// Цифры
        characters.add("0       -----");
        characters.add("1       .----");
        characters.add("2       ..---");
        characters.add("3       ...--");
        characters.add("4       ....-");
        characters.add("5       .....");
        characters.add("6       -....");
        characters.add("7       --...");
        characters.add("8       ---..");
        characters.add("9       ----.");

// Символы
        characters.add(".       .-.-.-");
        characters.add(",       --..--");
        characters.add("?       ..--..");
        characters.add("'       .----.");
        characters.add("!       -.-.--");
        characters.add("/       -..-.");
        characters.add("(       -.--.");
        characters.add(")       -.--.-");
        characters.add("&       .-...");
        characters.add(":       ---...");
        characters.add(";       -.-.-.");
        characters.add("=       -...-");
        characters.add("+       .-.-.");
        characters.add("-       -....-");
        characters.add("_       ..--.-");
        characters.add("$       ...-..-");
        characters.add("@       .--.-.");
        characters.add("Пробел       /");



        // Создание ArrayAdapter и установка его для ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, characters);
        listView.setAdapter(adapter);
    }
}
