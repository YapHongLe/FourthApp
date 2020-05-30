package com.example.fourthapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends Activity {

    private Button startButton;
    private Button pauseButton;
    private EditText etNumber;
    private Button btnAdd;
    private Button btnBack;

    private TextView timerValue;

    private long startTime = 0;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0;
    long timeSwapBuff = 0;
    long updatedTime = 0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerValue = (TextView) findViewById(R.id.tvTimer);

        startButton = (Button) findViewById(R.id.btnStart);

        etNumber = (EditText) findViewById(R.id.etNumber);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnBack = (Button) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimerActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pushupnumber = etNumber.getText().toString().trim();

                DBHelper dbh = new DBHelper(TimerActivity.this);
                long insert = dbh.insertNumber(pushupnumber);
                dbh.close();

                if (insert != -1) {
                    Toast.makeText(TimerActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);

            }
        });



        pauseButton = (Button) findViewById(R.id.btnPause);

        pauseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);

            }
        });

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            final int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);

        }

    };



}
