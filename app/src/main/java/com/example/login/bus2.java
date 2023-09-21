package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
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
    InfoDto d = new InfoDto();
    private Button gyodae11,gyodae22,ansan1,incheon1,songnae1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus2);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");

        gyodae11 = findViewById(R.id.gyodae11);
        gyodae22 = findViewById(R.id.gyodae22);
        ansan1 = findViewById(R.id.ansan1);
        incheon1 = findViewById(R.id.incheon1);
        songnae1 = findViewById(R.id.songnae1);
        gyodae11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bus2.this, date.class);
                String SelectGyodae11 = "gyodae11";
                d.setBus(SelectGyodae11);
                d.setUserId(userId);
                intent.putExtra("user_id", d.getUserId());
                intent.putExtra("bus" , d.getBus());
                startActivity(intent);

                new BusSelectionTask().execute(d.getBus());
            }
        });
        gyodae22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bus2.this, date.class);
                String SelectGyodae22 = "gyodae22";
                d.setBus(SelectGyodae22);
                d.setUserId(userId);
                intent.putExtra("user_id", d.getUserId());
                intent.putExtra("bus" , d.getBus());
                startActivity(intent);

                new BusSelectionTask().execute(d.getBus());
            }
        });
        ansan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bus2.this, date.class);
                String SelectAnsan1 = "ansan1";
                d.setBus(SelectAnsan1);
                d.setUserId(userId);
                intent.putExtra("user_id", d.getUserId());
                intent.putExtra("bus" , d.getBus());
                startActivity(intent);

                new BusSelectionTask().execute(d.getBus());
            }
        });
        incheon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bus2.this, date.class);
                String SelectIncheon1 = "incheon1";
                d.setBus(SelectIncheon1);
                d.setUserId(userId);
                intent.putExtra("user_id", d.getUserId());
                intent.putExtra("bus" , d.getBus());
                startActivity(intent);

                new BusSelectionTask().execute(d.getBus());
            }
        });
        songnae1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bus2.this, date.class);
                String SelectSongnae1 = "songnae1";
                d.setBus(SelectSongnae1);
                d.setUserId(userId);
                intent.putExtra("user_id", d.getUserId());
                intent.putExtra("bus" , d.getBus());
                startActivity(intent);

                new BusSelectionTask().execute(d.getBus());
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
            Intent intent = new Intent(bus2.this, date.class);
            intent.putExtra("bus" , d.getBus());
            intent.putExtra("userId", d.getUserId());
            startActivity(intent);
            finish();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(bus2.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}