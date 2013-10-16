package com.Centaurii.app.RatingCalculator.util;

import java.util.HashMap;

import android.graphics.Color;
import android.os.Environment;

public class Tags
{
    /** Storage Location*/
    public static final String RATINGS_FOLDER = Environment.getExternalStorageDirectory() + "/RatingsFolder/";
    public static final String RATINGS_FILE = "profiles";
    
    /** Fragment Strings*/
    public static final String PROFILE_FRAGMENT = "profile";
    
    /** JSON name for name/value pairs*/
    public static final String NAME = "name";
    public static final String RATING = "rating";
    public static final String PROVISIONAL = "prov";
    public static final String FAV_COLOR = "color";
    
    /** Useful constants.  These may be moved to the main activity for settings purposes*/
    public static final int MAX_PROFILES = 10;
    public static final boolean REQUIRE_CHANGE_PASSWORD = true;
    public static final String DEFAULT_NAME = "Player";
    public static final int DEFAULT_RATING = 1000;
    
    /** Color table*/
    public static HashMap<String, Integer> COLOR_MAP = new HashMap<String, Integer>();
    public static HashMap<String, Integer> getColorMap()
    {
        if(COLOR_MAP.size() == 0)
        {
            COLOR_MAP.put("Black", Color.BLACK);
            COLOR_MAP.put("Blue", Color.BLUE);
            COLOR_MAP.put("Cyan", Color.CYAN);
            COLOR_MAP.put("Dark Gray", Color.DKGRAY);
            COLOR_MAP.put("Gray", Color.GRAY);
            COLOR_MAP.put("Green", Color.GREEN);
            COLOR_MAP.put("Light Gray", Color.LTGRAY);
            COLOR_MAP.put("Magenta", Color.MAGENTA);
            COLOR_MAP.put("White", Color.WHITE);
            COLOR_MAP.put("Yellow", Color.YELLOW);
            COLOR_MAP.put("Red", Color.RED);
        }
        return COLOR_MAP;
    }
}
