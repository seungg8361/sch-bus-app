package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import java.nio.charset.StandardCharsets;

public class bus extends Activity{       // 하교 버스 선택하기

    InfoDto c = new InfoDto();
    private Button gyodae1,gyodae2,ansan,incheon,songnae,backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");
        Log.i("user_id", String.valueOf(userId));

        gyodae1 = findViewById(R.id.gyodae1);
        gyodae2 = findViewById(R.id.gyodae2);
        ansan = findViewById(R.id.ansan);
        incheon = findViewById(R.id.incheon);
        songnae = findViewById(R.id.songnae);
        backButton = findViewById(R.id.backButton);

        gyodae1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bus.this, date.class);
                String SelectGyodae1 = "교대1";
                c.setBus(SelectGyodae1);
                c.setUserId(userId);
                intent.putExtra("user_id", c.getUserId());
                intent.putExtra("bus" , c.getBus());
                startActivity(intent);

                new bus.BusSelectionTask().execute(c.getBus(),c.getUserId());
            }
        });
        gyodae2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bus.this, date.class);
                String SelectGyodae2 = "교대21";
                c.setBus(SelectGyodae2);
                c.setUserId(userId);
                intent.putExtra("user_id", c.getUserId());
                intent.putExtra("bus" , c.getBus());
                startActivity(intent);

                new bus.BusSelectionTask().execute(c.getBus(),c.getUserId());
            }
        });
        ansan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bus.this, date.class);
                String SelectAnsan = "안산1";
                c.setBus(SelectAnsan);
                c.setUserId(userId);
                intent.putExtra("user_id", c.getUserId());
                intent.putExtra("bus" , c.getBus());
                startActivity(intent);

                new bus.BusSelectionTask().execute(c.getBus(),c.getUserId());
            }
        });
        incheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bus.this, date.class);
                String SelectIncheon = "인천1";
                c.setBus(SelectIncheon);
                c.setUserId(userId);
                intent.putExtra("user_id", c.getUserId());
                intent.putExtra("bus" , c.getBus());
                startActivity(intent);

                new bus.BusSelectionTask().execute(c.getBus(),c.getUserId());
            }
        });
        songnae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bus.this, date.class);
                String SelectSongnae = "송내1";
                c.setBus(SelectSongnae);
                c.setUserId(userId);
                intent.putExtra("user_id", c.getUserId());
                intent.putExtra("bus" , c.getBus());
                startActivity(intent);

                new bus.BusSelectionTask().execute(c.getBus(),c.getUserId());
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bus.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private class BusSelectionTask extends AsyncTask<String, Void, String >{
        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://10.114.10.18:8080/select_bus";
            String busName = params[0];
            String userId = params[1];
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
                jsonParams.put("user", userId);
                jsonParams.put("bus", busName);

                byte[] postData = jsonParams.toString().getBytes(StandardCharsets.UTF_8); // UTF-8로 인코딩
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.write(postData);
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
            Intent intent = new Intent(bus.this, date.class);
            intent.putExtra("bus" , c.getBus());
            startActivity(intent);
            finish();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(bus.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}