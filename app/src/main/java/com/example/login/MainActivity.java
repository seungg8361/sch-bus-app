package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idText = (TextView) findViewById(R.id.idText);
        TextView passwordText = (TextView) findViewById(R.id.passwordText);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        String userPw = intent.getStringExtra("userPassword");
        String message = "환영합니다." + userId + "님!";

        idText.setText(userId);
        passwordText.setText(userPw);

        Button louteButton = findViewById(R.id.louteButton);
        louteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent louteintent = new Intent(MainActivity.this, louteActivity.class);
                MainActivity.this.startActivity(louteintent);
            }
        });

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button reservationButton = findViewById(R.id.reservationButton);
        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BuslistActivity.class);
                MainActivity.this.startActivity(intent);
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