package com.example.login;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends Activity {

    private EditText userIdEditText;
    private EditText passwordEditText;
    private EditText nameEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userIdEditText = findViewById(R.id.idText);
        passwordEditText = findViewById(R.id.passwordText);
        nameEditText = findViewById(R.id.nameText);
        registerButton = findViewById(R.id.registerButton2);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 입력한 회원가입 정보 가져오기
                String userId = userIdEditText.getText().toString();
                String userPwd = passwordEditText.getText().toString();
                String userName = nameEditText.getText().toString();

                // 회원가입 작업 실행
                new RegisterTask().execute(userId, userPwd, userName);
            }
        });
    }

    private class RegisterTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://10.114.10.18:8080/signup_app"; // 회원가입 API URL
            String userId = params[0];
            String password = params[1];
            String name = params[2];
            String result = "";

            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                // 회원가입 정보를 JSON 형태로 변환
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("user_id", userId);
                jsonParams.put("password", password);
                jsonParams.put("name", name);

                // JSON 데이터를 요청의 body에 넣기
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParams.toString());
                os.flush();
                os.close();

                // 응답 처리
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // 서버로부터 응답 데이터 읽기
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    StringBuilder response = new StringBuilder();
                    char[] buffer = new char[1024];
                    int bytesRead;
                    while ((bytesRead = inputStreamReader.read(buffer)) != -1) {
                        response.append(buffer, 0, bytesRead);
                    }
                    inputStreamReader.close();

                    result = response.toString();
                } else {
                    // 서버로부터 응답 데이터 읽기
                    InputStream errorStream = conn.getErrorStream();
                    InputStreamReader errorStreamReader = new InputStreamReader(errorStream);
                    StringBuilder errorResponse = new StringBuilder();
                    char[] errorBuffer = new char[1024];
                    int errorBytesRead;
                    while ((errorBytesRead = errorStreamReader.read(errorBuffer)) != -1) {
                        errorResponse.append(errorBuffer, 0, errorBytesRead);
                    }
                    errorStreamReader.close();

                    result = errorResponse.toString();
                }

                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
                result = "회원가입 실패";
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
            // 회원가입 결과에 따른 후속 처리 로직 작성
        }
    }
}