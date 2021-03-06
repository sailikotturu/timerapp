package com.example.sailik.mytimerapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    TextView timer;
    TextView stopwatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (TextView)findViewById(R.id.timer_tv);
        timer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.timer1, 0, 0, 0);
        stopwatch = (TextView)findViewById(R.id.stopwatch_tv);
        stopwatch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.stopwatch1, 0, 0, 0);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.timer2, 0, 0, 0);
                timer.setTextColor(Color.parseColor("#357AE8"));
                timer.setBackgroundResource(R.drawable.line);
                stopwatch.setBackgroundResource(R.drawable.eraseline);
                stopwatch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.stopwatch1, 0, 0, 0);
                stopwatch.setTextColor(Color.parseColor("#303F9F"));
                Fragment fragment = new TimerFrag();
                replaceFragment(fragment);
//                Fragment timer_frag = new TimerFrag();
//                FragmentManager fm= getFragmentManager();
//                FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                fragmentTransaction.replace(R.id.fragspace, timer_frag);
//                fragmentTransaction.commit();
            }
        });
        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.stopwatch2, 0, 0, 0);
                stopwatch.setTextColor(Color.parseColor("#357AE8"));
                stopwatch.setBackgroundResource(R.drawable.line);
                timer.setBackgroundResource(R.drawable.eraseline);
                timer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.timer1, 0, 0, 0);
                timer.setTextColor(Color.parseColor("#303F9F"));

                Fragment fragment = new WatchFrag();
                replaceFragment(fragment);

                }
        });

    }

    private void replaceFragment (Fragment fragment){
        String backFragName =  fragment.getClass().getName();
        //String fragmentTag = backFragName;

        FragmentManager fm = getFragmentManager();
        boolean fragmentPopped = fm.popBackStackImmediate (backFragName, 0);

        if (!fragmentPopped && fm.findFragmentByTag(backFragName) == null){ //fragment not in back stack, create it.
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragspace, fragment, backFragName);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backFragName);
            ft.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }




}

