package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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
    private Button seat1,seat2,seat3,seat4;
    InfoDto dto = new InfoDto();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        Intent intent = getIntent();
        String selectBus = intent.getStringExtra("bus");
        String selectDate = intent.getStringExtra("date");

        seat1 = findViewById(R.id.seat1);
        seat2 = findViewById(R.id.seat2);
        seat3 = findViewById(R.id.seat3);
        seat4 = findViewById(R.id.seat4);

        seat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat1 = "seat1";
                String SelectBus = selectBus;
                String SelectDate = selectDate;
                dto.setBus(SelectBus);
                new SeatSelectionTask().execute(SelectBus,SelectDate,Selectseat1);
            }
        });
        seat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat2 = "seat2";
                dto.setSeat(String.valueOf(seat2));
                new SeatSelectionTask().execute(Selectseat2);
            }
        });
        seat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat3 = "seat3";
                dto.setSeat(String.valueOf(seat3));
                new SeatSelectionTask().execute(Selectseat3);
            }
        });
        seat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat4 = "seat4";
                dto.setSeat(String.valueOf(seat4));
                new SeatSelectionTask().execute(Selectseat4);
            }
        });
    }
    private class SeatSelectionTask extends AsyncTask<String, Void, String >{
        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://10.114.10.15:8080/select_seat";
            String bus = params[0];
            String date = params[1];
            String seat = params[2];
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
                jsonParams.put("bus", bus);
                jsonParams.put("date", date);
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

                    Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(response.toString()));
                    startActivity(intent1);           // kakaopay 결제창 화면 넘어가기.

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
                Intent intent = new Intent(SeatActivity.this, ReservationChecker.class);
                dto.setSeat(dto.getSeat());
                dto.setBus(dto.getBus());
                dto.setDate(dto.getDate());
                intent.putExtra("bus" , dto.getBus());
                intent.putExtra("date", dto.getDate());
                intent.putExtra("seat", dto.getSeat());
                startActivity(intent);
                finish();
            try{
                JSONObject seatStatus = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(SeatActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}