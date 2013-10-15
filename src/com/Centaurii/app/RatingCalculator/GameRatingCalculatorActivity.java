package com.Centaurii.app.RatingCalculator;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;

/**
 * Controller class that sets up listeners/event handlers and interacts with Calculator.java based on user input
 * Created 9/10/12
 * Last updated 10/14/13
 * @author Gautam Narula
 *
 */

public class GameRatingCalculatorActivity extends FragmentActivity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public boolean checkExternalStorage()
    {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}