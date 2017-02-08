package com.example.sailik.mytimerapp;

import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WatchFrag extends Fragment {

    private Button buttonstart;
    private Button buttonstop;
    private TextView textview;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_watch_frag, container, false);
        textview = (TextView) view.findViewById(R.id.timer_input);
        //textview.setVisibility(View.INVISIBLE);
        //editText = (EditText) view.findViewById(R.id.edittext);
        //edittext.setVisibility(View.INVISIBLE);

        buttonstart = (Button) view.findViewById(R.id.button_start);
        buttonstop = (Button) view.findViewById(R.id.button_stop);

        buttonstart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);

            }

        });

        buttonstop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                timeSwapBuff = 0L;
                textview.setText("00:00:00");
                customHandler.removeCallbacks(updateTimerThread);
                //textview.setText("00:00:00");
            }

        });
        return view;
    }


    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            textview.setText("" + mins + ":"

                            + String.format("%02d", secs) + ":"

                            + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);

        }

    };


}
