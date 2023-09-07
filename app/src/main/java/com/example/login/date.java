package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class date extends Activity {
    String[] items = {"날짜 선택","2023-09-18", "2023-09-19", "2023-09-20", "2023-09-21", "2023-09-22"};
    Button seatButtons[];
    private String selectBus;
    private AdapterView<Adapter> spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        final TextView textView = findViewById(R.id.textView);
        Spinner spinner = findViewById((R.id.spinner));
        selectBus = getIntent().getStringExtra("bus");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    new DateSelectionTask().execute(items[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private class DateSelectionTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://10.114.10.15:8080/select_date";
            String selectBus_test = "gyodae1";
            String date = params[0];
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

                jsonParams.put("bus", selectBus_test);
                jsonParams.put("date", date);

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
            Toast.makeText(date.this, "날짜가 선택되었습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(date.this, LoginActivity.class);
            startActivity(intent);
            super.onPostExecute(result);
            try {
                JSONArray reservedSeats = new JSONArray(result);

                List<String> reservedSeatList = new ArrayList<>();
                for (int i = 0; i < reservedSeats.length(); i++) {
                    String seatNumber = reservedSeats.getString(i);
                    reservedSeatList.add(seatNumber);
                }

                for (String seatNumber : reservedSeatList) {
                    int index = Integer.parseInt(seatNumber) - 1;

                    if (index >= 0 && index < seatButtons.length) {
                        Button seatButton = seatButtons[index];
                        seatButton.setEnabled(false);
                        seatButton.setBackgroundColor(Color.GRAY);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}