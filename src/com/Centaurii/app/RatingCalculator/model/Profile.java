package com.Centaurii.app.RatingCalculator.model;

import java.util.Random;

import android.graphics.drawable.Drawable;

public class Profile implements Comparable<Profile>
{
    private String name;
    private int rating;
    private boolean provisional;
    private int provisionalGamesLeft;
    private int favColor;
    private Drawable profilePicture;
    
    private static Random rand = new Random();
    
    /**
     * Full constructor for profile with picture included
     * 
     * @param name
     * @param rating
     * @param provisional
     * @param favColor
     * @param profilePicture
     */
    public Profile(String name, int rating, boolean provisional, 
            int provisionalGamesLeft, int favColor, Drawable profilePicture)
    {
        super();
        this.name = name;
        this.rating = rating;
        this.provisional = provisional;
        this.provisionalGamesLeft = provisionalGamesLeft;
        this.favColor = favColor;
        this.profilePicture = profilePicture;
    }
    
    /**
     * Constructor without picture
     * 
     * @param name
     * @param rating
     * @param provisional
     * @param favColor
     */
    public Profile(String name, int rating, boolean provisional, 
            int provisionalGamesLeft, int favColor)
    {
        this(name, rating, provisional, provisionalGamesLeft, favColor, null);
    }
    
    /**
     * Constructor for a temporary profile
     * 
     * @param name
     * @param rating
     */
    public Profile(String name, int rating)
    {
        this(name, rating, false, 0, rand.nextInt() | 0xff000000, null);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }
    
    public boolean isProvisional()
    {
        return provisional;
    }
    
    public void setProvisional(boolean provisional)
    {
        this.provisional = provisional;
    }

    public int getProvisionalGamesLeft()
    {
        return provisionalGamesLeft;
    }

    public void setProvisionalGamesLeft(int provisionalGamesLeft)
    {
        this.provisionalGamesLeft = provisionalGamesLeft;
    }

    public int getFavColor()
    {
        return favColor;
    }

    public void setFavColor(int favColor)
    {
        this.favColor = favColor;
    }

    public Drawable getProfilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(Drawable profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    @Override
    public int compareTo(Profile player)
    {
        return player.getRating() - rating;
    }
}
