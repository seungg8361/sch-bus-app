package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class louteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loute);

        Button songnae = findViewById(R.id.songnae);
        songnae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songnaeintent = new Intent(louteActivity.this, SongnaeActivity.class);
                louteActivity.this.startActivity(songnaeintent);
            }
        });
    }
}