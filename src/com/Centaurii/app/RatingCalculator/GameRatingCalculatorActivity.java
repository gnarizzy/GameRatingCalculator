package com.Centaurii.app.RatingCalculator;

import com.Centaurii.app.RatingCalculator.fragments.HomeScreenFragment;
import com.Centaurii.app.RatingCalculator.tasks.LoadProfiles;

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
    private boolean isFirstTime;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        new LoadProfiles(this).execute();
        
        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.main_frame, new HomeScreenFragment())
                                       .commit();
        }
    }
    
    public boolean checkExternalStorage()
    {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public boolean isFirstTime()
    {
        return isFirstTime;
    }

    public void setFirstTime(boolean isFirstTime)
    {
        this.isFirstTime = isFirstTime;
    }
}