package com.Centaurii.app.RatingCalculator;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("NewApi")
public class SettingsActivity extends PreferenceActivity
{

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        
        //Check if the version is Honeycomb or higher
        if(!Build.VERSION.RELEASE.startsWith("2"))
        {
            Drawable background = getResources().getDrawable(R.drawable.list_background);
            getActionBar().setBackgroundDrawable(background);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        LayoutInflater inflater = getLayoutInflater();
        
        View header = inflater.inflate(R.layout.settings_header, null, false);
        
        Button button = (Button) header.findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View view)
            {
                SettingsActivity.this.finish();
                SettingsActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
            
        });
        
        addPreferencesFromResource(R.xml.settings);
        getListView().setBackgroundResource(R.drawable.list_background);
        getListView().addHeaderView(header);
    }
    
    @Override
    public void onBackPressed() 
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}
