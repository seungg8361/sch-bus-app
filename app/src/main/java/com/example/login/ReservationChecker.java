package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReservationChecker extends Activity {

    InfoDto dto = new InfoDto();
    private Button mainButton;
    private TextView user, bus,seat,date;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        user = findViewById(R.id.tvTitle);
        bus = findViewById(R.id.busnumber);
        seat = findViewById(R.id.seatnumber);
        date = findViewById(R.id.datenumber);

        new ReservationCheckTask().execute(String.valueOf(bus),String.valueOf(seat),String.valueOf(date));

        user.setText(dto.getUserId() + "님의 승차권");

        mainButton = findViewById(R.id.mainButton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationChecker.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private class ReservationCheckTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://10.114.10.15:8080/kakaoPaySuccess";
            String busnumber = params[0];
            String seatnumber = params[1];
            String datenumber = params[2];
            String result = "";

            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                // 예약 번호를 JSON 형태로 변환
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("usernumber", busnumber);
                jsonParams.put("seatnumber", seatnumber);
                jsonParams.put("datenumber", datenumber);

                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParams.toString());
                os.flush();
                os.close();

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
            }
            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(ReservationChecker.this, result, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ReservationChecker.this, ReservationChecker.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ReservationChecker.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}