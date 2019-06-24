package com.example.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int seconds = 0;
    boolean running = false;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        Button b1 = findViewById(R.id.start);
        Button b2 = findViewById(R.id.stop);
        Button b3 = findViewById(R.id.reset);
        tv1 = findViewById(R.id.timetv);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        runTimer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.start: startTimer();

                break;

            case R.id.stop: stopTimer();
                break;

            case R.id.reset: reset();
        }
    }

    private void startTimer(){
        running = true;

    }

    private void stopTimer(){
        running = false;
    }

    private void reset(){
        seconds = 0;
        running = false;
    }

    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds/3600)/60;
                String time = String.format("%d:%02d:%02d",hours,minutes,seconds);
                tv1.setText(time);
                if(running)
                    seconds++;
                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        super.onSaveInstanceState(outState);
    }

}
