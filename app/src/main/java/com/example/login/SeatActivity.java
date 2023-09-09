package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SeatActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

    }
    private class SeatSelectionTask extends AsyncTask<String, Void, String >{
        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://10.114.10.15:8080/select_seat";
            String seat = params[0];
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
                jsonParams.put("seat", seat);

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
                }

                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(SeatActivity.this, "좌석이 선택되었습니다.", Toast.LENGTH_SHORT).show();
            // 후속 처리 로직 작성
            Intent intent = new Intent(SeatActivity.this, SeatActivity.class);
            startActivity(intent);
            finish();
            try{
                JSONObject seatStatus = new JSONObject(result);
                updateSeatUI(seatStatus);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        private void updateSeatUI(JSONObject seatStatus) throws JSONException {

            int MAX_SEATS = 40;

            for (int seatNumber = 1; seatNumber <= MAX_SEATS; seatNumber++) {
                // 예약 상태 확인 (예약 가능한 경우 "available", 예약된 경우 "reserved"로 가정)
                String seatState = seatStatus.optString(String.valueOf(seatNumber), "");

                Button seatButton = findViewById(getResources().getIdentifier("seatButton" + seatNumber, "id", getPackageName()));

                if ("available".equals(seatState)) {
                    // 예약 가능한 좌석
                    seatButton.setEnabled(true);  // 사용자가 클릭 가능
                    seatButton.setBackgroundColor(0xFF87CEEB); // 배경 이미지를 예약 가능한 좌석으로 설정
                } else if ("reserved".equals(seatState)) {
                    // 예약된 좌석
                    seatButton.setEnabled(false);  // 사용자가 클릭 불가능
                    seatButton.setBackgroundColor(0xFF808080);  // 배경 이미지를 예약된 좌석으로 설정
                } else {
                    // 다른 상태 (예: 선택 가능한 상태를 추가로 처리)
                    seatButton.setEnabled(true);  // 사용자가 클릭 가능
                    seatButton.setBackgroundColor(0xFF87CEEB);  // 기본 배경 이미지로 설정
                }
            }
        }
    }
}