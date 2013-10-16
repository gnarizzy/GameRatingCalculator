package com.Centaurii.app.RatingCalculator.listeners;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.fragments.SettingsFragment;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class OnSettingsClickListener implements OnClickListener
{
    FragmentActivity act;
    
    public OnSettingsClickListener(FragmentActivity act)
    {
        this.act = act;
    }

    @Override
    public void onClick(View view)
    {
        act.getSupportFragmentManager()
           .beginTransaction()
           .replace(R.id.main_frame, new SettingsFragment())
           .addToBackStack(null)
           .commit();
    }

}
