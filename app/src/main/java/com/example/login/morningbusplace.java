package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class morningbusplace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morningbusplace);

        Button gyodaeplace = findViewById(R.id.gyodaeplace);
        Button gyodaeplace2 = findViewById(R.id.gyodaeplace2);
        Button ansanplace = findViewById(R.id.ansanplace);
        Button incheonplace = findViewById(R.id.incheonplace);
        Button songnaeplace = findViewById(R.id.songnaeplace);

        gyodaeplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(morningbusplace.this, gyodaepicture.class);
                startActivity(intent);
            }
        });
        gyodaeplace2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(morningbusplace.this, gyodaepicture.class);
                startActivity(intent);
            }
        });

        ansanplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(morningbusplace.this, ansanpicture.class);
                startActivity(intent);
            }
        });

        incheonplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(morningbusplace.this, incheonpicture.class);
                startActivity(intent);
            }
        });

        songnaeplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(morningbusplace.this, songnaepicture.class);
                startActivity(intent);
            }
        });

    }
}