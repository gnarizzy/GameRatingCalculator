package com.Centaurii.app.RatingCalculator.listeners;

import java.util.ArrayList;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.model.Profile;
import com.Centaurii.app.RatingCalculator.util.Tags;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

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
        int addPlayerPosition = listViewPlayers.getCount() - 1;
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        
        if(position == addPlayerPosition)
        {
            if(listViewPlayers.getCount() < Tags.MAX_GAME_PLAYERS + 1)
            {
                DialogInterface.OnClickListener listener = new AddListener();
                
                
                LayoutInflater inflater = (LayoutInflater)act.getLayoutInflater();
                View list = inflater.inflate(R.layout.add_players_dialog, null);
                
                nonPlayers = (ListView) list.findViewById(R.id.non_players);
                nonPlayers.setAdapter(nonPlayerAdapter);
                nonPlayers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                nonPlayers.setOnItemClickListener(new OnItemClickListener()
                {
    
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id)
                    {
                        boolean canAddMore = true;
                        ListView listView = (ListView) parent;
                        CheckBox checkBox = (CheckBox) view.findViewById(R.id.winner);
                        
                        //Check to see if there are more than 6 or more checks 
                        //          (means no more are allowed)
                        int totalChecks = playerAdapter.getCount();
                        for(int i = 0; i < listView.getCount(); i++)
                        {
                            View temp = listView.getChildAt(i);
                            CheckBox box = (CheckBox) temp.findViewById(R.id.winner);
                            if(box.isChecked())
                            {
                                totalChecks++;
                            }
                            if(totalChecks > Tags.MAX_GAME_PLAYERS - 1)
                            {
                                canAddMore = false;
                                break;
                            }
                        }
                        Log.i("AddRemove", "Total Checks: " + totalChecks);
                        
                        if(checkBox.isChecked())
                        {
                            listView.setItemChecked(position, false);
                            checkBox.toggle();
                        }
                        else if(canAddMore)
                        {
                            listView.setItemChecked(position, true);
                            checkBox.toggle();
                        }
                    }
                    
                });
                
                builder.setView(list)
                       .setPositiveButton("Add", listener)
                       .create()
                       .show();
            }
            else
            {
                Toast.makeText(act, "Max number of players is " + Tags.MAX_GAME_PLAYERS + ".", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            DialogInterface.OnClickListener listener = new RemoveListener((ListView)listViewPlayers, position);
            
            builder.setMessage("Make winner or delete?")
                   .setPositiveButton("Winner", listener)
                   .setNegativeButton("Delete", listener)
                   .create()
                   .show();
        }
    }
    
    //Activates when adding users to the player pool
    private class AddListener implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int id)
        {
            dialog.dismiss();
            switch(id)
            {
                case Dialog.BUTTON_POSITIVE:
                    Log.i("AddRemove", "Positive button pressed");
                    int children = nonPlayers.getChildCount();
                    
                    Profile temp;
                    ArrayList<Profile> toDelete = new ArrayList<Profile>();
                    for(int i = 0; i < children; i++)
                    {
                        View view = nonPlayers.getChildAt(i);
                        CheckBox checkBox = (CheckBox) view.findViewById(R.id.winner);
                        
                        if(checkBox.isChecked())
                        {
                            temp = nonPlayerAdapter.getItem(i);
                            toDelete.add(temp);
                            playerAdapter.add(temp);
                        }
                    }
                    
                    for(Profile delete: toDelete)
                    {
                        nonPlayerAdapter.remove(delete);
                    }
                    break;
            }
        }
        
    }
    
    //Activates when removing a user from the player pool
    private class RemoveListener implements DialogInterface.OnClickListener
    {
        int position;
        ListView listView;
        
        public RemoveListener(ListView listView, int position)
        {
            this.position = position;
            this.listView = listView;
        }
        
        @Override
        public void onClick(DialogInterface dialog, int id)
        {
            switch(id)
            {
                case Dialog.BUTTON_POSITIVE:
                    Log.i("AddRemove", "Winner button pressed");
                    
                    View childView;
                    CheckBox box;
                    for(int i = 0; i < listView.getCount(); i++)
                    {
                        childView = listView.getChildAt(i);
                        box = (CheckBox) childView.findViewById(R.id.winner);
                        
                        //In case it's the footer of the list view
                        if(box != null)
                        {
                            if(position != i)
                            {
                                box.setChecked(false);
                            }
                            else
                            {
                                box.setChecked(true);
                            }
                        }
                            
                    }
                    
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    Log.i("AddRemove", "Delete button pressed");
                    
                    View childView1;
                    CheckBox box1;
                    for(int i = 0; i < listView.getCount(); i++)
                    {
                        childView1 = listView.getChildAt(i);
                        box1 = (CheckBox) childView1.findViewById(R.id.winner);
                        
                        //In case it's the footer of the list view
                        if(box1 != null)
                        {
                            box1.setChecked(false);
                        }
                            
                    }
                    
                    Profile temp = playerAdapter.getItem(position);
                    nonPlayerAdapter.add(temp);
                    playerAdapter.remove(temp);
                    break;
            }
        }
    }
}
