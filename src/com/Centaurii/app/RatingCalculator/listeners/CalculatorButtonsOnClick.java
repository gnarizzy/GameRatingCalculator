package com.Centaurii.app.RatingCalculator.listeners;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.model.Profile;
import com.Centaurii.app.RatingCalculator.util.Calculator;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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

    @SuppressWarnings("unchecked")
    @Override
    public void onClick(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        
        ArrayAdapter<Profile> people = (ArrayAdapter<Profile>) players.getAdapter();
        
        Profile[] calculateArray = new Profile[people.getCount()];
        
        int winner = -1;
        for(int i = 0; i < people.getCount(); i++)
        {
            calculateArray[i] = people.getItem(i);
            View childView = players.getChildAt(i);
            CheckBox box = (CheckBox) childView.findViewById(R.id.winner);
            if(box.isChecked())
            {
                winner = i;
            }
        }
        
        int[] scores = Calculator.calculateRating(winner, false, calculateArray);
        
        LayoutInflater inflater = act.getLayoutInflater();
        ViewGroup masterView = new LinearLayout(act);
        View slaveView;
        int tracker = 0;
        for(Profile prof: calculateArray)
        {
            slaveView = inflater.inflate(R.layout.score_reveal_dialog_slave_view, masterView);
            if(tracker == winner)
            {
                
            }
            tracker++;
        }
        
        builder.create()
               .show();
    }

}
