package com.Centaurii.app.RatingCalculator.implementations;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.Centaurii.app.RatingCalculator.GameRatingCalculatorActivity;
import com.Centaurii.app.RatingCalculator.interfaces.ProfileRetrieveAndSave;
import com.Centaurii.app.RatingCalculator.model.Profile;
import com.Centaurii.app.RatingCalculator.util.Tags;

public class ProfileHandler implements ProfileRetrieveAndSave
{

    @Override
    public ArrayList<Profile> getProfiles(String rawProfiles)
    {
        ArrayList<Profile> profiles = new ArrayList<Profile>();
        try
        {
            JSONArray arr = new JSONArray(rawProfiles);
            JSONObject person = null;
            for(int i = 0; i < arr.length(); i++)
            {
                person = arr.getJSONObject(i);
                profiles.add(new Profile(person.getString(Tags.NAME), person.getInt(Tags.RATING),
                        person.getBoolean(Tags.PROVISIONAL), 
                        person.optInt(Tags.PROVISIONAL_GAMES, 
                                (person.getBoolean(Tags.PROVISIONAL) ? GameRatingCalculatorActivity.DEFAULT_PROVISIONAL() : 0)),
                        person.getInt(Tags.FAV_COLOR)));
            }
        }
        catch (JSONException e)
        {
            Log.i("ProfileHandler", "There are no saved profiles");
            e.printStackTrace();
        }
        return profiles;
    }

    @Override
    public String saveProfiles(ArrayList<Profile> profiles)
    {
        JSONArray arr = new JSONArray();
        JSONObject profile = null;
        String rawProfiles = "";
        
        for(Profile person: profiles)
        {
            profile = new JSONObject();
            try
            {
                profile.put(Tags.NAME, person.getName());
                profile.put(Tags.RATING, person.getRating());
                profile.put(Tags.PROVISIONAL, person.isProvisional());
                profile.put(Tags.PROVISIONAL_GAMES, person.getProvisionalGamesLeft());
                profile.put(Tags.FAV_COLOR, person.getFavColor());
                arr.put(profile);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        rawProfiles = arr.toString();
        return rawProfiles;
    }

}
