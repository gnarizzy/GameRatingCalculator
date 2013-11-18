package com.Centaurii.app.RatingCalculator;

import java.util.ArrayList;

import com.Centaurii.app.RatingCalculator.fragments.HomeScreenFragment;
import com.Centaurii.app.RatingCalculator.model.Profile;
import com.Centaurii.app.RatingCalculator.tasks.LoadProfiles;
import com.Centaurii.app.RatingCalculator.tasks.SaveProfiles;
import com.Centaurii.app.RatingCalculator.util.Tags;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Controller class that sets up listeners/event handlers and interacts with Calculator.java based on user input
 * Created 9/10/12
 * Last updated 10/14/13
 * @author Gautam Narula
 *
 */

@SuppressLint("NewApi")
public class GameRatingCalculatorActivity extends FragmentActivity 
{
    private boolean isFirstTime;
    private ArrayList<Profile> savedProfiles;
    
    /* Saved Preference Variables*/
    public static int MAX_PROFILES, MAX_PLAYERS, DEFAULT_PROVISIONAL, DEFAULT_RATING;
    public static boolean SPLASH_SCREEN;
    
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        
        //Check if the version is Honeycomb or higher
        if(!Build.VERSION.RELEASE.startsWith("2"))
        {
            Drawable background = getResources().getDrawable(R.drawable.list_background);
            getActionBar().setBackgroundDrawable(background);
        }
        
        MAX_PROFILES = Integer.valueOf(sharedPrefs.getString(Tags.MAX_PROFILES, "20"));
        MAX_PLAYERS = Integer.valueOf(sharedPrefs.getString(Tags.MAX_PLAYERS, "6"));
        DEFAULT_PROVISIONAL = Integer.valueOf(sharedPrefs.getString(Tags.DEFAULT_PROVISIONAL, "5"));
        DEFAULT_RATING = Integer.valueOf(sharedPrefs.getString(Tags.DEFAULT_RATING, "1000"));
        SPLASH_SCREEN = sharedPrefs.getBoolean(Tags.SPLASH, false);
        
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
        
        Log.i("GameRating", "Max Players: " + sharedPrefs.getString("max_profiles", "20"));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.container, menu);
        LayoutInflater inflater = getLayoutInflater();
        for(int i = 0; i < menu.size(); i++)
        {
            View view = inflater.inflate(R.layout.menu_items, null, false);
            
            ((TextView) view.findViewById(R.id.menu_items_title)).setText(menu.getItem(i).getTitle());
            MenuItemCompat.setActionView(menu.getItem(i), view);
        }
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.action_settings:
                Toast.makeText(this, "You pressed help!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return true;
        }
    }
    
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i("GameRatingCalculator", "OnPause() is running");
        new SaveProfiles(this).execute();
    }
    
    @Override
    protected void onRestart()
    {
        super.onRestart();
        SharedPreferences sharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        
        MAX_PROFILES = Integer.valueOf(sharedPrefs.getString(Tags.MAX_PROFILES, "20"));
        MAX_PLAYERS = Integer.valueOf(sharedPrefs.getString(Tags.MAX_PLAYERS, "6"));
        DEFAULT_PROVISIONAL = Integer.valueOf(sharedPrefs.getString(Tags.DEFAULT_PROVISIONAL, "5"));
        DEFAULT_RATING = Integer.valueOf(sharedPrefs.getString(Tags.DEFAULT_RATING, "1000"));
        SPLASH_SCREEN = sharedPrefs.getBoolean(Tags.SPLASH, false);
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

    public static int MAX_PROFILES()
    {
        return MAX_PROFILES;
    }

    public static int MAX_PLAYERS()
    {
        return MAX_PLAYERS;
    }
    
    public static int DEFAULT_PROVISIONAL()
    {
        return DEFAULT_PROVISIONAL;
    }
    
    public static int DEFAULT_RATING()
    {
        return DEFAULT_RATING;
    }
    
    public static boolean SPLASH_SCREEN()
    {
        return SPLASH_SCREEN;
    }
}