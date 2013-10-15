package com.Centaurii.app.RatingCalculator.listeners;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.fragments.ProfileViewerFragment;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class OnProfileClickListener implements OnClickListener
{
    FragmentActivity activity;
    
    public OnProfileClickListener(FragmentActivity activity)
    {
        this.activity = activity;
    }
    
    @Override
    public void onClick(View view)
    {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame, new ProfileViewerFragment())
                .addToBackStack(null)
                .commit();
    }

}
