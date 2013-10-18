package com.Centaurii.app.RatingCalculator.listeners;

import com.Centaurii.app.RatingCalculator.model.Profile;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AddRemovePlayersItemClickListener implements OnItemClickListener
{
    FragmentActivity act;
    ListView nonPlayers;
    ArrayAdapter<Profile> nonPlayerAdapter;
    ArrayAdapter<Profile> playerAdapter;
    
    public AddRemovePlayersItemClickListener(FragmentActivity act, ArrayAdapter<Profile> nonPlayerAdapter,
            ArrayAdapter<Profile> playerAdapter)
    {
        this.act = act;
        this.nonPlayerAdapter = nonPlayerAdapter;
        this.playerAdapter = playerAdapter;
    }
    
    @Override
    public void onItemClick(AdapterView<?> listViewPlayers, View view, int position, long id)
    {
        
    }
        
}
