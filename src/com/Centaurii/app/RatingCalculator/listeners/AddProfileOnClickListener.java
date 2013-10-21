package com.Centaurii.app.RatingCalculator.listeners;

import com.Centaurii.app.RatingCalculator.GameRatingCalculatorActivity;
import com.Centaurii.app.RatingCalculator.fragments.AddProfileFragment;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AddProfileOnClickListener implements OnClickListener
{
    private GameRatingCalculatorActivity activity;
    
    public AddProfileOnClickListener(GameRatingCalculatorActivity activity)
    {
        this.activity = activity;
    }
    
    @Override
    public void onClick(View view)
    {
        if(activity.getSavedProfiles().size() < GameRatingCalculatorActivity.MAX_PROFILES())
        {
            new AddProfileFragment().show(activity.getSupportFragmentManager(), null);
        }
        else
        {
            Toast.makeText(activity, "You cannot exceed " + GameRatingCalculatorActivity.MAX_PROFILES()
                    + " profiles with your current settings", Toast.LENGTH_SHORT).show();
        }
    }
}
