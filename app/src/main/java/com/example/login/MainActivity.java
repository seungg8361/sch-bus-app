package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{
    InfoDto b = new InfoDto();
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userId = intent.getStringExtra("user_id");

        Button morningButton = findViewById(R.id.morningButton);
        Button dinnerButton = findViewById(R.id.dinnerButton);
        Button dinnerbus = findViewById(R.id.dinnerbus);
        Button morningbus = findViewById(R.id.morningbus);
        Button inquiryButton = findViewById(R.id.inquiryButton);
        Button backButton = findViewById(R.id.backButton);
        morningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bus2.class);
                b.setUserId(userId);
                intent.putExtra("user_id",b.getUserId());
                startActivity(intent);
            }
        });
        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bus.class);
                b.setUserId(userId);
                intent.putExtra("user_id",b.getUserId());
                startActivity(intent);
            }
        });
        inquiryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReservationChecker.class);
                b.setUserId(userId);
                intent.putExtra("user_id",b.getUserId());
                startActivity(intent);
            }
        });
        dinnerbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, dinnerpicture.class);
                startActivity(intent);
            }
        });
        morningbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, morningbusplace.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}