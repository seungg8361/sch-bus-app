package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class BusReservationActivity extends AppCompatActivity {

    private Spinner toSpinner;
    private Spinner timeSpinner;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busreservation);

        toSpinner = findViewById(R.id.toSpinner);
        timeSpinner = findViewById(R.id.timeSpinner);
        searchButton = findViewById(R.id.searchButton);

        ArrayAdapter<CharSequence> toAdapter = ArrayAdapter.createFromResource(this,R.array.buss,android.R.layout.simple_spinner_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this,R.array.morningtime,android.R.layout.simple_spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selectedDestination = toSpinner.getSelectedItem().toString();
                String selectedTime = timeSpinner.getSelectedItem().toString();
            }
        });
    }
}
