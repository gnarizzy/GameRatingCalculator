package com.Centaurii.app.RatingCalculator.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.Centaurii.app.RatingCalculator.GameRatingCalculatorActivity;
import com.Centaurii.app.RatingCalculator.implementations.ProfileHandler;
import com.Centaurii.app.RatingCalculator.interfaces.ProfileRetrieveAndSave;
import com.Centaurii.app.RatingCalculator.util.Tags;

import android.os.AsyncTask;
import android.util.Log;

public class SaveProfiles extends AsyncTask<Void, Void, Void>
{
    GameRatingCalculatorActivity activity;
    
    public SaveProfiles(GameRatingCalculatorActivity activity)
    {
        this.activity = activity;
    }
    
    @Override
    protected Void doInBackground(Void... arg0)
    {
        File ratingsFile = new File(activity.getExternalFilesDir(null), Tags.RATINGS_FILE);
        ProfileRetrieveAndSave prof = new ProfileHandler();
        
        String saveData = prof.saveProfiles(activity.getSavedProfiles());
        
        PrintWriter writer = null;
        try
        {
            Log.i("SaveProfiles", "Writing information to file");
            writer = new PrintWriter(ratingsFile, "UTF-8");
            writer.println(saveData);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(writer != null)
            {
                writer.close();
            }
        }
        return null;
    }

}
