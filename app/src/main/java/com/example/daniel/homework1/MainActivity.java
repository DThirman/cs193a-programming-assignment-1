//Daniel Thirman <Dthirman@stanford.edu>
//Click the buttons in the same sequence that is flashed upon the screen

package com.example.daniel.homework1;

import android.graphics.Color;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import java.lang.Thread;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    ArrayList<Integer> clicks;
    ArrayList<Integer> userClicks;
    Random rand;
    int clickIndex;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clicks = new ArrayList<Integer>();
        userClicks = new ArrayList<Integer>();
        rand = new Random();
        addNewClick();
        showClick();
        clickIndex=0;
    }

    public void addNewClick()
    {
        int next = rand.nextInt(3)+1;
        clicks.add(next);
    }

    public Button intToButton(int x)
    {
        switch (x)
        {
        case 1:
            return (Button)findViewById(R.id.button1);
        case 2:
            return (Button)findViewById(R.id.button2);
        case 3:
            return (Button)findViewById(R.id.button3);
        case 4:
            return (Button)findViewById(R.id.button4);
        default:
            return null;
        }

    }

    public int wait(int dur)
    {
        int total = 0;
        for(int i=0; i<dur; i++)
        {
            total+= rand.nextInt();
        }
        return total;
    }


    public void showClick()
    {
        if(clickIndex < clicks.size())
        {


            Animation a = new AlphaAnimation(1, 0);
            a.setDuration(500);
            a.setInterpolator(new LinearInterpolator());
            a.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {


                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    showClick();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            Button b = intToButton(clicks.get(clickIndex));
            b.startAnimation(a);
            clickIndex++;
        }
        else {
            clickIndex = 0;
        }
    }
    public void buttonClick(View view)
    {
        Button b = (Button) view;
        int click = Integer.parseInt(b.getText().toString());
        Log.v("Button", "Pressed " + b.getText().toString());
        userClicks.add(click);
        boolean success = true;
        for(int i =0; i<userClicks.size(); i++)
        {
            if(userClicks.get(i) != clicks.get(i))
            {
                success = false;
            }
        }
        if(success)
        {
            if(userClicks.size() == clicks.size())
            {
                ((TextView) findViewById(R.id.score)).setText("Score :" + userClicks.size());
                userClicks.clear();
                addNewClick();
                showClick();
            }
        }
        else
        {
            userClicks.clear();
            showClick();
        }
    }


}
