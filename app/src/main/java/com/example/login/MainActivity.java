package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        String userPw = intent.getStringExtra("userPassword");
        String message = "환영합니다." + userId + "님!";

        Button reservationButton = findViewById(R.id.reservationButton);
        Button inquiryButton = (Button) findViewById(R.id.inquiryButton);
        Button louteButton = (Button) findViewById(R.id.louteButton);

        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bustimeintent = new Intent(MainActivity.this, BustimeActivity.class);
                MainActivity.this.startActivity(bustimeintent);
            }
        });
    }

    private long lastTimeBackPressed;
    @Override
    public void onBackPressed(){
        if (System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish();
            return;
        }
        Toast.makeText(this, "'뒤로' 버튼을 한 번 더 눌러 종료합니다.", Toast.LENGTH_SHORT);
        lastTimeBackPressed = System.currentTimeMillis();
    }
}