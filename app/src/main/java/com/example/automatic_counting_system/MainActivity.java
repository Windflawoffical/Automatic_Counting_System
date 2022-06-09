package com.example.automatic_counting_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Обработка нажатия на кнопку "Вход"
        findViewById(R.id.btn_signin).setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity.this, signin.class);
            startActivity(intent1);
        });;
        //Обработка нажатия на кнопку "Регистрация"
        findViewById(R.id.btn_registration).setOnClickListener(view -> {
            Intent intent2 = new Intent(this, registration.class);
            startActivity(intent2);
        });
    }
}