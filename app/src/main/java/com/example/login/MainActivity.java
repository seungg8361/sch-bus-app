package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity{
    InfoDto b = new InfoDto();

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");
        b.setUserId(userId);
        Toast.makeText(MainActivity.this, "환영합니다."+ userId, Toast.LENGTH_SHORT).show();

        Button morningButton = findViewById(R.id.morningButton);
        Button dinnerButton = findViewById(R.id.dinnerButton);
        Button dinnerbus = findViewById(R.id.dinnerbus);
        Button morningbus = findViewById(R.id.morningbus);
        Button inquiryButton = findViewById(R.id.inquiryButton);
        morningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bus2.class);
                //String userId = intent.getStringExtra("user_id");
                Log.i("user_id",String.valueOf(b.getUserId()));
                //b.setUserId(userId);

                intent.putExtra("user_id",b.getUserId());
                startActivity(intent);
            }
        });
        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bus.class);
                intent.putExtra("userId",b.getUserId());
                startActivity(intent);
            }
        });
        inquiryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReservationChecker.class);
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
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (doubleBackToExitPressedOnce) {
                // 두 번 눌러서 앱 종료
                finish();
            } else {
                // 한 번 더 누를 때까지 알림
                Toast.makeText(this, "한 번 더 눌러 뒤로가기 버튼을 종료합니다.", Toast.LENGTH_SHORT).show();
                doubleBackToExitPressedOnce = true;
                // 2초 후에 리셋
                new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}