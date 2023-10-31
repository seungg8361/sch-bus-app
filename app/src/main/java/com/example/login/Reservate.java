package com.example.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Reservate extends AppCompatActivity {


    InfoDto dto = new InfoDto();
    List<InfoDto> dataList = new ArrayList<>();
    private Button backButton;
    private TextView reservate1,reservate2,reservate3,reservate4,reservate5;
    String userId, seat, date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservate);

        backButton = findViewById(R.id.backButton);
        reservate1 = findViewById(R.id.reservate1);
        reservate2 = findViewById(R.id.reservate2);
        reservate3 = findViewById(R.id.reservate3);
        reservate4 = findViewById(R.id.reservate4);
        reservate5 = findViewById(R.id.reservate5);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reservate.this, MainActivity.class);
                startActivity(intent);
            }
        });
        reservate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.size() > 0) {
                    InfoDto data = dataList.get(0);
                    if (data != null && !data.getBus().isEmpty()) {
                        Intent intent = new Intent(Reservate.this, ReservationChecker.class);
                        dto.setUserId(dataList.get(0).getUserId());
                        dto.setSeat(dataList.get(0).getSeat());
                        dto.setDate(dataList.get(0).getDate());
                        dto.setBus(dataList.get(0).getBus());
                        intent.putExtra("user_id", dto.getUserId());
                        intent.putExtra("seat", dto.getSeat());
                        intent.putExtra("date", dto.getDate());
                        intent.putExtra("bus", dto.getBus());
                        startActivity(intent);
                    }
                }
            }
        });
        reservate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.size() > 1) {
                    InfoDto data = dataList.get(1);
                    if (data != null && !data.getBus().isEmpty()) {
                        Intent intent = new Intent(Reservate.this, ReservationChecker.class);
                        dto.setUserId(dataList.get(1).getUserId());
                        dto.setSeat(dataList.get(1).getSeat());
                        dto.setDate(dataList.get(1).getDate());
                        dto.setBus(dataList.get(1).getBus());
                        intent.putExtra("user_id", dto.getUserId());
                        intent.putExtra("seat", dto.getSeat());
                        intent.putExtra("date", dto.getDate());
                        intent.putExtra("bus", dto.getBus());
                        startActivity(intent);
                    }
                }
            }
        });
        reservate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.size() > 2) {
                    InfoDto data = dataList.get(2);
                    if (data != null && !data.getBus().isEmpty()) {
                        Intent intent = new Intent(Reservate.this, ReservationChecker.class);
                        dto.setUserId(dataList.get(2).getUserId());
                        dto.setSeat(dataList.get(2).getSeat());
                        dto.setDate(dataList.get(2).getDate());
                        dto.setBus(dataList.get(2).getBus());
                        intent.putExtra("user_id", dto.getUserId());
                        intent.putExtra("seat", dto.getSeat());
                        intent.putExtra("date", dto.getDate());
                        intent.putExtra("bus", dto.getBus());
                        startActivity(intent);
                    }
                }
            }
        });
        reservate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.size() > 3) {
                    InfoDto data = dataList.get(3);
                    if (data != null && !data.getBus().isEmpty()) {
                        Intent intent = new Intent(Reservate.this, ReservationChecker.class);
                        dto.setUserId(dataList.get(3).getUserId());
                        dto.setSeat(dataList.get(3).getSeat());
                        dto.setDate(dataList.get(3).getDate());
                        dto.setBus(dataList.get(3).getBus());
                        intent.putExtra("user_id", dto.getUserId());
                        intent.putExtra("seat", dto.getSeat());
                        intent.putExtra("date", dto.getDate());
                        intent.putExtra("bus", dto.getBus());
                        startActivity(intent);
                    }
                }
            }
        });
        reservate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.size() > 4) {
                    InfoDto data = dataList.get(4);
                    if (data != null && !data.getBus().isEmpty()) {
                        Intent intent = new Intent(Reservate.this, ReservationChecker.class);
                        dto.setUserId(dataList.get(4).getUserId());
                        dto.setSeat(dataList.get(4).getSeat());
                        dto.setDate(dataList.get(4).getDate());
                        dto.setBus(dataList.get(4).getBus());
                        intent.putExtra("user_id", dto.getUserId());
                        intent.putExtra("seat", dto.getSeat());
                        intent.putExtra("date", dto.getDate());
                        intent.putExtra("bus", dto.getBus());
                        startActivity(intent);
                    }
                }
            }
        });
        Intent intent = getIntent();
        userId = intent.getStringExtra("user_id");

        dto.setUserId(userId);
        dto.setBus(String.valueOf(reservate1));
        dto.setBus(String.valueOf(reservate2));
        dto.setBus(String.valueOf(reservate3));
        dto.setBus(String.valueOf(reservate4));
        dto.setBus(String.valueOf(reservate5));
        dto.setSeat(seat);
        dto.setDate(date);

        new ReservationCheckTask().execute(dto.getUserId(), dto.getBus(), dto.getSeat(), dto.getDate());
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
            if(result.equals("내역이 없습니다.")) {
                Toast.makeText(Reservate.this, result, Toast.LENGTH_SHORT).show();
            }
            try {
                JSONArray jsonArray = new JSONArray(result); // JSON 배열로 파싱

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String userValue = jsonObject.getString("user");
                    String busValue = jsonObject.getString("bus");
                    String seatValue = jsonObject.getString("seat");
                    String dateValue = jsonObject.getString("date");

                    InfoDto info = new InfoDto();

                    info.setUserId(userValue);
                    info.setBus(busValue);
                    info.setSeat(seatValue);
                    info.setDate(dateValue);

                    dataList.add(info);
                }
                // 예약에 따라 TextViews 업데이트
                for (int i = 0; i < dataList.size() && i < 5; i++) {
                    TextView textView = null;
                    switch (i) {
                        case 0:
                            textView = reservate1;
                            break;
                        case 1:
                            textView = reservate2;
                            break;
                        case 2:
                            textView = reservate3;
                            break;
                        case 3:
                            textView = reservate4;
                            break;
                        case 4:
                            textView = reservate5;
                            break;
                    }

                    if (textView != null) {
                        textView.setText(dataList.get(i).getBus());
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            }
        }
    }