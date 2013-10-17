package com.Centaurii.app.RatingCalculator.listeners;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class CalculatorButtonsOnClick implements OnClickListener
{
    FragmentActivity act;
    boolean submit;
    
    public CalculatorButtonsOnClick(FragmentActivity act, boolean submit)
    {
        this.act = act;
        this.submit = submit;
    }

    @Override
    public void onClick(View view)
    {
        if(submit)
        {
            
        }
        else
        {
            
        }
    }

}
