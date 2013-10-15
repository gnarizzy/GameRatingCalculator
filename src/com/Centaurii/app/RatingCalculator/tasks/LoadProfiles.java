package com.Centaurii.app.RatingCalculator.tasks;

import java.io.File;

import com.Centaurii.app.RatingCalculator.GameRatingCalculatorActivity;
import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.util.Tags;

import android.app.Dialog;
import android.os.AsyncTask;

public class LoadProfiles extends AsyncTask<Void, Void, Void>
{
    private GameRatingCalculatorActivity activity;
    private Dialog splashScreen;
    
    public LoadProfiles(GameRatingCalculatorActivity activity)
    {
        this.activity = activity;
    }
    
    @Override
    protected void onPreExecute()
    {
        splashScreen = new Dialog(activity, R.style.splashTheme);
        splashScreen.setCancelable(false);
        splashScreen.setContentView(R.layout.splash_screen);
        splashScreen.show();
    }
    
    @Override
    protected Void doInBackground(Void... ignore)
    {
        //Check to make sure there is external storage available
        if(activity.checkExternalStorage())
        {
            File ratingsFolder = new File(Tags.RATINGS_FOLDER);
            if(!ratingsFolder.exists())
            {
                ratingsFolder.mkdir();
                activity.setFirstTime(true);
            }
        }
        
        
        
        
        
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    protected void onCancelled()
    {
        if(splashScreen != null)
        {
            splashScreen.dismiss();
        }
    }
    
    @Override
    protected void onPostExecute(Void ignore)
    {
        if(splashScreen != null)
        {
            splashScreen.dismiss();
        }
    }
}
