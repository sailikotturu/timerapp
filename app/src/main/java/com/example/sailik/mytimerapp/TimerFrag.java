package com.example.sailik.mytimerapp;

import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TimerFrag extends Fragment {

    Button buttonstart;
    Button buttonreset;
    EditText edittext;
    TextView textview;
    CountDownTimer countdowntimer;
    int number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.timerfrag, container, false);
        textview = (TextView) view.findViewById(R.id.timer_text);
        textview.setVisibility(View.INVISIBLE);
        edittext = (EditText) view.findViewById(R.id.timer_input);
        edittext.setVisibility(View.INVISIBLE);

        buttonstart = (Button) view.findViewById(R.id.button_start);
        buttonreset = (Button) view.findViewById(R.id.button_reset);

        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edittext.setVisibility(View.VISIBLE);
                String input = textview.getText().toString();
                number = Integer.parseInt(input) * 1000;

            }
        });


        buttonstart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                edittext.setVisibility(View.INVISIBLE);
                textview.setVisibility(View.VISIBLE);
                countdowntimer = new CountDownTimerClass(number, 1000);

                countdowntimer.start();

            }
        });
        return view;
    }


    public class CountDownTimerClass extends CountDownTimer {


        public CountDownTimerClass(long millisInFuture, long countDownInterval) {

            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {

            int progress = (int) (millisUntilFinished / 1000);

            textview.setText(Integer.toString(progress));

        }

        @Override
        public void onFinish() {

            textview.setText(" Count Down Finish ");

        }
    }
}





