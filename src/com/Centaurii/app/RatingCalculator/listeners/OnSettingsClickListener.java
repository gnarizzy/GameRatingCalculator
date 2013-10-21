package com.Centaurii.app.RatingCalculator.listeners;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.SettingsActivity;

import android.content.Intent;
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
        Intent intent = new Intent(act, SettingsActivity.class);
        act.startActivity(intent);
        act.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}
