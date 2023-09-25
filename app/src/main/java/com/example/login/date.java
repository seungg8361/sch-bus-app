package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class date extends Activity {
    //String[] items = {"날짜 선택","2023-09-18", "2023-09-19", "2023-09-20", "2023-09-21", "2023-09-22"};
   // Button seatButtons[];
    private String selectBus;
    private AdapterView<Adapter> spinner;
    InfoDto e = new InfoDto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Intent intent = getIntent();
        selectBus = intent.getStringExtra("bus"); //                     버스 정보 가져오기

        final TextView textView = findViewById(R.id.textView);
        Spinner spinner = findViewById((R.id.spinner));

        List<String> dateList = generateDateList(5);//            5일간의 날짜를 생성

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, dateList //items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public  void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDate = (String) parent.getItemAtPosition(position);

                if (!"날짜를 선택해주세요.".equals(selectedDate)){
                    Toast.makeText(date.this, "선택된 날짜: " + selectedDate, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(date.this, SeatActivity.class);
                    e.setBus(selectBus);
                    intent.putExtra("bus" , e.getBus());
                    e.setDate(dateList.get(position));
                    intent.putExtra("date", e.getDate());
                    startActivity(intent);

                    new DateSelectionTask().execute(dateList.get(position), String.valueOf(selectBus));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
            private List<String> generateDateList(int numberOfDays) {
                    List<String> dateList = new ArrayList<>();
                    dateList.add("날짜를 선택해주세요.");
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                for (int i = 0; i < numberOfDays; i++) {
                    dateList.add(sdf.format(calendar.getTime()));
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                    return dateList;
            }
    private class DateSelectionTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://10.114.10.15:8080/select_date";
            String date = params[0];
            String bus = params[1];
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

                jsonParams.put("date", date);
                jsonParams.put("bus", bus);

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
            Intent intent = new Intent(date.this, SeatActivity.class);
            intent.putExtra("date" , e.getDate());
            intent.putExtra("bus" , e.getBus());
            intent.putExtra("user_id", e.getUserId());

            startActivity(intent);

            super.onPostExecute(result);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(date.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}