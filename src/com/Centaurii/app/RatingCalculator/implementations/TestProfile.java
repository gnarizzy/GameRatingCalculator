package com.Centaurii.app.RatingCalculator.implementations;

import java.util.ArrayList;

import com.Centaurii.app.RatingCalculator.interfaces.ProfileRetrieveAndSave;
import com.Centaurii.app.RatingCalculator.model.Profile;

public class TestProfile implements ProfileRetrieveAndSave
{

    @Override
    public ArrayList<Profile> getProfiles(String rawProfiles)
    {
        return new ArrayList<Profile>();
    }

    @Override
    public String saveProfiles(ArrayList<Profile> profiles)
    {
        return "";
    }

}
