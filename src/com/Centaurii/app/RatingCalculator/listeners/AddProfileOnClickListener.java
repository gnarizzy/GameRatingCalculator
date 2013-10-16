package com.Centaurii.app.RatingCalculator.listeners;

import com.Centaurii.app.RatingCalculator.GameRatingCalculatorActivity;
import com.Centaurii.app.RatingCalculator.fragments.AddProfileFragment;

import android.view.View;
import android.view.View.OnClickListener;

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
        new AddProfileFragment().show(activity.getSupportFragmentManager(), null);
    }
}
