package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BustimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bustime);

        final Button morningButton = findViewById(R.id.morningButton);
        final Button dinnerButton = findViewById(R.id.dinnerButton);

        morningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BustimeActivity.this, louteActivity2.class);
                startActivity(intent);
            }
        });
        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BustimeActivity.this, louteActivity.class);
                startActivity(intent);
            }
        });
    }
}
