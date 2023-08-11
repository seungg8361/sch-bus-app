package com.example.login;

import static com.example.login.R.id;
import static com.example.login.R.id.registerButton;
import static com.example.login.R.layout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_register);

        final EditText idText = findViewById(R.id.idText);
        final EditText passwordText = findViewById(R.id.passwordText);
        final EditText nameText = findViewById(R.id.nameText);

        TextView registerButton2 = findViewById(R.id.registerButton2);
        registerButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String userId = idText.getText().toString();
                String userPw = passwordText.getText().toString();
                String userName = nameText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원 등록에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                                Intent registerintent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(registerintent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원 등록에 실패했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userId, userPw, userName, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}