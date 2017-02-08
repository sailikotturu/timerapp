package com.example.sailik.mytimerapp;

import android.app.Notification;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;
//import android.app.content.Content;
import android.content.Context;


public class TimerFrag extends Fragment {

    Button buttonstart;
    Button buttonreset;
    EditText editText;
    //TextView textview;
    CountDownTimer countdowntimer;
    int number;
    NotificationCompat.Builder not;
    TaskStackBuilder stackBuilder;
    Intent resultIntent;
    PendingIntent pIntent;
    NotificationManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.timerfrag, container, false);
        //textview = (TextView) view.findViewById(R.id.timer_text);
        //textview.setVisibility(View.INVISIBLE);
        editText = (EditText) view.findViewById(R.id.edittext);
        //edittext.setVisibility(View.INVISIBLE);

        buttonstart = (Button) view.findViewById(R.id.button_start);
        buttonreset = (Button) view.findViewById(R.id.button_reset);

        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countdowntimer!=null) {
                    countdowntimer.cancel();
                }
                editText.setText(" ");
                //edittext.setVisibility(View.VISIBLE);
                //String input = editText.getText().toString();
                //number = Integer.parseInt(input) * 1000;

            }
        });


        buttonstart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String input = editText.getText().toString();
                number = Integer.parseInt(input) * 1000;

                //edittext.setVisibility(View.INVISIBLE);
                //textview.setVisibility(View.VISIBLE);
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

            editText.setText(Integer.toString(progress));

        }

        @Override
        public void onFinish() {
            startNotification();
            editText.setText(" ");

        }

        protected void startNotification() {
            // TODO Auto-generated method stub
            //Creating Notification Builder
            not = new NotificationCompat.Builder(getActivity());
            not.setContentTitle("Timer Alert");

            not.setContentText("Time limit reached!!");

            not.setTicker("New Alert!");

            not.setSmallIcon(R.drawable.img);
            stackBuilder = TaskStackBuilder.create(getActivity());
            stackBuilder.addParentStack(Result.class);
            //Intent which is opened when notification is clicked
            resultIntent = new Intent(getActivity(), Result.class);
            stackBuilder.addNextIntent(resultIntent);
            pIntent =  stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
            not.setContentIntent(pIntent);
            manager =(NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, not.build());

        }
    }
}





