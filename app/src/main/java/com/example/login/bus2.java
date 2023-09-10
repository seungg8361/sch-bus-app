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

public class bus2 extends Activity{      // 등교 버스 선택하기

    private Button gyodae11;
    private Button gyodae22;
    private Button ansan1;
    private Button incheon1;
    private Button songnae1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        gyodae11 = findViewById(R.id.gyodae11);
        gyodae22 = findViewById(R.id.gyodae22);
        ansan1 = findViewById(R.id.ansan1);
        incheon1 = findViewById(R.id.incheon1);
        songnae1 = findViewById(R.id.songnae1);
        gyodae11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SelectGyodae1 = "gyodae1";
                new BusSelectionTask().execute(SelectGyodae1);
            }
        });
        gyodae22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SelectGyodae2 = "gyodae2";
                new BusSelectionTask().execute(SelectGyodae2);
            }
        });
        ansan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SelectAnsan = "ansan";
                new BusSelectionTask().execute(SelectAnsan);
            }
        });
        incheon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SelectIncheon = "incheon";
                new BusSelectionTask().execute(SelectIncheon);
            }
        });
        songnae1.setOnClickListener(new View.OnClickListener() {
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
            Toast.makeText(bus2.this, result, Toast.LENGTH_SHORT).show();
            // 후속 처리 로직 작성
            Intent intent = new Intent(bus2.this, date.class);
            intent.putExtra("bus" , gyodae11.getText().toString());
            intent.putExtra("bus" , gyodae22.getText().toString());
            intent.putExtra("bus" , ansan1.getText().toString());
            intent.putExtra("bus" , incheon1.getText().toString());
            intent.putExtra("bus" , songnae1.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}