package com.Centaurii.app.RatingCalculator;

import java.util.ArrayList;

import com.Centaurii.app.RatingCalculator.fragments.HomeScreenFragment;
import com.Centaurii.app.RatingCalculator.model.Profile;
import com.Centaurii.app.RatingCalculator.tasks.LoadProfiles;
import com.Centaurii.app.RatingCalculator.tasks.SaveProfiles;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

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
    private ArrayList<Profile> savedProfiles;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        if(savedInstanceState == null)
        {
            new LoadProfiles(this).execute();
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.main_frame, new HomeScreenFragment())
                                       .commit();
        }
        else
        {
            //For now do the same thing.  In the future, change it to something else
            new LoadProfiles(this).execute();
        }
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i("GameRatingCalculator", "OnStop() is running");
        new SaveProfiles(this).execute();
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

    public ArrayList<Profile> getSavedProfiles()
    {
        return savedProfiles;
    }

    public void setSavedProfiles(ArrayList<Profile> savedProfiles)
    {
        this.savedProfiles = savedProfiles;
    }
}