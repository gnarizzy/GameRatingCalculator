package com.Centaurii.app.RatingCalculator.util;

import android.os.Environment;

public class Tags
{
    /** Storage Location*/
    public static final String RATINGS_FOLDER = Environment.getExternalStorageDirectory() + "/RatingsFolder/";
    public static final String RATINGS_FILE = "profiles";
    
    /** JSON name for name/value pairs*/
    public static final String NAME = "name";
    public static final String RATING = "rating";
    public static final String PROVISIONAL = "prov";
    public static final String FAV_COLOR = "color";
}
