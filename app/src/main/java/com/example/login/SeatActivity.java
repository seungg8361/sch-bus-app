package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import java.nio.charset.StandardCharsets;

public class SeatActivity extends Activity{
    private Button seat1,seat2,seat3,seat4,seat5,seat6,seat7,seat8,seat9,seat10
            ,seat11,seat12,seat13,seat14,seat15,seat16,seat17,seat18,seat19,seat20
            ,seat21,seat22,seat23,seat24,seat25,seat26,seat27,seat28,seat29,seat30
            ,seat31,seat32,seat33,seat34,seat35,seat36,seat37,seat38,seat39,seat40
            ,seat41,seat42,seat43,seat44,seat45,backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        Intent intent = getIntent();
        String selectUser = intent.getStringExtra("user_id");
        String selectBus = intent.getStringExtra("bus");
        String selectDate = intent.getStringExtra("date");
        backButton = findViewById(R.id.backButton);
        seat1 = findViewById(R.id.seat1);seat2 = findViewById(R.id.seat2);seat3 = findViewById(R.id.seat3);
        seat4 = findViewById(R.id.seat4);seat5 = findViewById(R.id.seat5);seat6 = findViewById(R.id.seat6);
        seat7 = findViewById(R.id.seat7);seat8 = findViewById(R.id.seat8);seat9 = findViewById(R.id.seat9);
        seat10 = findViewById(R.id.seat10);seat11 = findViewById(R.id.seat11);seat12 = findViewById(R.id.seat12);
        seat13 = findViewById(R.id.seat13);seat14 = findViewById(R.id.seat14);seat15 = findViewById(R.id.seat15);
        seat16 = findViewById(R.id.seat16);seat17 = findViewById(R.id.seat17);seat18 = findViewById(R.id.seat18);
        seat19 = findViewById(R.id.seat19);seat20 = findViewById(R.id.seat20);seat21 = findViewById(R.id.seat21);
        seat22 = findViewById(R.id.seat22);seat23 = findViewById(R.id.seat23);seat24 = findViewById(R.id.seat24);
        seat25 = findViewById(R.id.seat25);seat26 = findViewById(R.id.seat26);seat27 = findViewById(R.id.seat27);
        seat28 = findViewById(R.id.seat28);seat29 = findViewById(R.id.seat29);seat30 = findViewById(R.id.seat30);
        seat31 = findViewById(R.id.seat31);seat32 = findViewById(R.id.seat32);seat33 = findViewById(R.id.seat33);
        seat34 = findViewById(R.id.seat34);seat35 = findViewById(R.id.seat35);seat36 = findViewById(R.id.seat36);
        seat37 = findViewById(R.id.seat37);seat38 = findViewById(R.id.seat38);seat39 = findViewById(R.id.seat39);
        seat40 = findViewById(R.id.seat40);seat41 = findViewById(R.id.seat41);seat42 = findViewById(R.id.seat42);
        seat43 = findViewById(R.id.seat43);seat44 = findViewById(R.id.seat44);seat45 = findViewById(R.id.seat45);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeatActivity.this, date.class);
                startActivity(intent);
            }
        });
        seat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "1";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "2";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "3";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "4";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "5";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "6";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "7";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "8";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "9";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "10";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "11";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "12";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "13";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "14";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "15";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "16";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "17";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "18";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "19";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "20";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "21";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "22";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "23";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "24";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "25";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "26";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "27";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "28";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "29";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "30";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "31";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "32";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "33";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "34";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "35";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "36";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "37";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "38";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "39";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "40";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "41";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "42";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "43";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "44";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
        seat45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Selectseat = "45";
                new SeatSelectionTask().execute(selectBus,selectDate,Selectseat,selectUser);
            }
        });
    }
    private class SeatSelectionTask extends AsyncTask<String, Void, String >{
        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://10.114.10.18:8080/select_seat";
            String bus = params[0];
            String date = params[1];
            String seat = params[2];
            String user = params[3];
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
                jsonParams.put("user", user);

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

                    Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(response.toString()));
                    startActivity(intent1);           // kakaopay 결제창 화면 넘어가기.

                }else {
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
            if (result.equals("이미 예약된 좌석입니다.")) {
                Toast.makeText(SeatActivity.this, result, Toast.LENGTH_SHORT).show();
                seat1.setEnabled(false);
            }
            else {
                Intent intent = new Intent(SeatActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}