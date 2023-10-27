package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ReservationChecker extends Activity {

    InfoDto dto = new InfoDto();
    private Button mainButton;
    private TextView user, bus,seat,date;
    String userId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        user = findViewById(R.id.userId);
        bus = findViewById(R.id.busnumber);
        seat = findViewById(R.id.seatnumber);
        date = findViewById(R.id.datenumber);

        Intent intent = getIntent();
        userId = intent.getStringExtra("user_id");
        dto.setUserId(userId);
        dto.setBus(String.valueOf(bus));
        dto.setSeat(String.valueOf(seat));
        dto.setDate(String.valueOf(date));

        new ReservationCheckTask().execute(dto.getUserId(),dto.getBus(),dto.getSeat(),dto.getDate());

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
            String urlString = "http://10.114.10.18:8080/check_my_ticket";
            String userId = params[0];
            String bus = params[1];
            String seat = params[2];
            String date = params[3];
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
                jsonParams.put("user", userId);
                jsonParams.put("bus", bus);
                jsonParams.put("seat", seat);
                jsonParams.put("date", date);

                byte[] postData = jsonParams.toString().getBytes(StandardCharsets.UTF_8); // UTF-8로 인코딩
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.write(postData);
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
            try {
                if (result.equals("내역이 없습니다.")) {
                    Toast.makeText(ReservationChecker.this, result, Toast.LENGTH_SHORT).show();
                }
                JSONObject json = new JSONObject(result);
                String users = json.getString("user");
                String buss = json.getString("bus");
                String seats = json.getString("seat");
                String dates = json.getString("date");
                dto.setUserId(users);
                dto.setBus(String.valueOf(bus));
                dto.setSeat(String.valueOf(seat));
                dto.setDate(String.valueOf(date));
                user = findViewById(R.id.userId);
                user.setText(users + "님");
                bus = findViewById(R.id.busnumber);
                bus.setText("버스 :  "+buss);
                seat = findViewById(R.id.seatnumber);
                seat.setText("좌석 :  "+seats +" 번 좌석");
                date = findViewById(R.id.datenumber);
                date.setText("날짜 :  " + dates);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            }
        }
    }