package com.Centaurii.app.RatingCalculator.interfaces;

import java.util.ArrayList;

import com.Centaurii.app.RatingCalculator.model.Profile;

public interface ProfileRetrieveAndSave
{
    public ArrayList<Profile> getProfiles(String rawProfiles);
    public String saveProfiles(ArrayList<Profile> profiles);
}
