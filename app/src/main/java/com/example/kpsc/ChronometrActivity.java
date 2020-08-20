package com.example.kpsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class ChronometrActivity extends AppCompatActivity {
    Chronometer mChronometer;
    Button mStart,mPause,mReset,mSave;
    int time;
    long timeWhenStopped = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometr);
        mChronometer = findViewById(R.id.chronometer);
        mStart = findViewById(R.id.start);
        mReset = findViewById(R.id.reset);
        mPause = findViewById(R.id.pause);
        mSave = findViewById(R.id.save_time);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timeWhenStopped!=0)
                mChronometer.setBase(mChronometer.getBase()+SystemClock.elapsedRealtime()-timeWhenStopped);
                else {
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }
                mChronometer.start();
                mStart.setEnabled(false);
                mPause.setEnabled(true);

            }
        });
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeWhenStopped=  SystemClock.elapsedRealtime();
                mChronometer.stop();
                mPause.setEnabled(false);
                mStart.setEnabled(true);


            }
        });
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mStart.setEnabled(true);
                mPause.setEnabled(false);
                timeWhenStopped =0;

            }
        });
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   // time = 20;//(int)(SystemClock.elapsedRealtime()-timeWhenStopped)/1000;
                ;
                    Intent intent = getIntent();
                    intent.putExtra("totalTime",mChronometer.getText());
                    setResult(RESULT_OK,intent);
                    finish();

            }
        });

    }
}