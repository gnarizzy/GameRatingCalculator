package com.Centaurii.app.RatingCalculator.listeners;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class CalculatorButtonsOnClick implements OnClickListener
{
    FragmentActivity act;
    ListView players;
    
    public CalculatorButtonsOnClick(FragmentActivity act, ListView players)
    {
        this.act = act;
        this.players = players;
    }

    @Override
    public void onClick(View view)
    {
        
    }

}
