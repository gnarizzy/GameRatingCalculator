package com.Centaurii.app.RatingCalculator.listeners;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.model.Profile;
import com.Centaurii.app.RatingCalculator.util.Tags;

import android.app.AlertDialog;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
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

    public AddRemovePlayersItemClickListener(FragmentActivity act,
            ArrayAdapter<Profile> nonPlayerAdapter,
            ArrayAdapter<Profile> playerAdapter)
    {
        this.act = act;
        this.nonPlayerAdapter = nonPlayerAdapter;
        this.playerAdapter = playerAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> listViewPlayers, View view,
            int position, long id)
    {
        int total = listViewPlayers.getCount();
        AlertDialog.Builder builder = new AlertDialog.Builder(act);

        View alertView;
        if (position == total - 1)
        {
            // Execute the Add Player
            alertView = renderNonPlayersView();

            builder.setView(alertView)
                   .setPositiveButton("Add", null) //This listener transfers from nonplayers to players
                   .create()
                   .show();
        }
        else
        {
            // Show the options for a Player
            alertView = renderPlayersView();

            builder.setView(alertView)
                   .setPositiveButton("Winner", null) //This listener makes the current choice a winner
                   .setNegativeButton("Delete", null) //This listener removes the current choice
                   .create()
                   .show();
        }
    }

    private View renderNonPlayersView()
    {
        LayoutInflater inflater = act.getLayoutInflater();
        View view = inflater.inflate(R.layout.add_players_dialog, null);

        ListView lv = (ListView) view.findViewById(R.id.non_players);
        lv.setAdapter(nonPlayerAdapter);
        lv.setOnItemClickListener(new AddPlayers());

        return view;
    }

    private View renderPlayersView()
    {
        LayoutInflater inflater = act.getLayoutInflater();
        View view = inflater.inflate(R.layout.player_options, null);

        return view;
    }

    private class AddPlayers implements OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id)
        {
            ListView listView = (ListView) parent;
            int checkedItems = listView.getCheckedItemIds().length;
            int currentPlayers = AddRemovePlayersItemClickListener.this.playerAdapter.getCount();
            
            if(checkedItems + currentPlayers > Tags.MAX_GAME_PLAYERS)
            {
                listView.setItemChecked(position, false);
            }
        }

    }
}
