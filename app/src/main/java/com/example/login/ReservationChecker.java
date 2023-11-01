package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReservationChecker extends Activity {

    InfoDto dto = new InfoDto();
    private Button mainButton;
    private TextView user, bus, seat, date;
    String userId, busId, seatId, dateId;
    private Button backButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);


        user = findViewById(R.id.userId);
        bus = findViewById(R.id.busnumber);
        seat = findViewById(R.id.seatnumber);
        date = findViewById(R.id.datenumber);

        Intent intent = getIntent();
        userId = intent.getStringExtra("user_id");
        busId = intent.getStringExtra("bus");
        seatId = intent.getStringExtra("seat");
        dateId = intent.getStringExtra("date");
        dto.setUserId(userId);
        dto.setBus(busId);
        dto.setSeat(seatId);
        dto.setDate(dateId);

        mainButton = findViewById(R.id.mainButton);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationChecker.this, Reservate.class);
                startActivity(intent);
            }
        });
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationChecker.this, MainActivity.class);
                startActivity(intent);
            }
        });
                    user.setText(dto.getUserId() + "님");
                    bus.setText("버스 :  " + dto.getBus());
                    seat.setText("좌석 :  " + dto.getSeat() + " 번 좌석");
                    date.setText("날짜 :  " + dto.getDate());
                }

        }
