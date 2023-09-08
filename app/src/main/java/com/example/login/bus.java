package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class bus extends Activity{       // 하교 버스 선택하기

    private Button gyodae1;
    private Button gyodae2;
    private Button ansan;
    private Button incheon;
    private Button songnae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loute);

        gyodae1 = findViewById(R.id.gyodae1);
        gyodae2 = findViewById(R.id.gyodae2);
        ansan = findViewById(R.id.ansan);
        incheon = findViewById(R.id.incheon);
        songnae = findViewById(R.id.songnae);
        gyodae1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SelectGyodae1 = "gyodae1";
                new BusSelectionTask().execute(SelectGyodae1);
            }
        });
        gyodae2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SelectGyodae2 = "gyodae2";
                new BusSelectionTask().execute(SelectGyodae2);
            }
        });
        ansan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SelectAnsan = "ansan";
                new BusSelectionTask().execute(SelectAnsan);
            }
        });
        incheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SelectIncheon = "incheon";
                new BusSelectionTask().execute(SelectIncheon);
            }
        });
        songnae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SelectSongnae = "songnae";
                new BusSelectionTask().execute(SelectSongnae);
            }
        });
    }
    private class BusSelectionTask extends AsyncTask<String, Void, String >{
        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://10.114.10.15:8080/select_bus";
            String busName = params[0];
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
                jsonParams.put("bus", busName);

                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParams.toString());
                os.flush();
                os.close();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //Toast.makeText(bus.this, "성공이다", Toast.LENGTH_SHORT).show();
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
                    //Toast.makeText(bus.this, "성공해라", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(bus.this, result, Toast.LENGTH_SHORT).show();
            // 후속 처리 로직 작성
            Intent intent = new Intent(bus.this, date.class);
            intent.putExtra("bus" , gyodae1.getText().toString());
            intent.putExtra("bus" , gyodae2.getText().toString());
            intent.putExtra("bus" , ansan.getText().toString());
            intent.putExtra("bus" , incheon.getText().toString());
            intent.putExtra("bus" , songnae.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}