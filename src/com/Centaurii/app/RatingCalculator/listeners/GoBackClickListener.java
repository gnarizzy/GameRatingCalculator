package com.Centaurii.app.RatingCalculator.listeners;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class GoBackClickListener implements OnClickListener
{
    FragmentActivity act;
    
    public GoBackClickListener(FragmentActivity act)
    {
        this.act = act;
    }
    
    @Override
    public void onClick(View v)
    {
        act.getSupportFragmentManager()
           .popBackStack();
    }

}
