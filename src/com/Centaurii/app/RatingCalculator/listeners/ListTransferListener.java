package com.Centaurii.app.RatingCalculator.listeners;

import java.util.ArrayList;

import com.Centaurii.app.RatingCalculator.model.Profile;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;

public class ListTransferListener implements OnClickListener
{
    SparseBooleanArray fromLocations;
    ArrayAdapter<Profile> from, to;
    
    public ListTransferListener(SparseBooleanArray fromLocations, ArrayAdapter<Profile> from, ArrayAdapter<Profile> to)
    {
        this.fromLocations = fromLocations;
        this.from = from;
        this.to = to;
    }
    
    @Override
    public void onClick(DialogInterface dialog, int id)
    {
        ArrayList<Profile> profs = new ArrayList<Profile>();
        Profile temp;
        for(int i = 0; i < from.getCount(); i++)
        {
            Log.i("ListTransfer", "Location: " + i + "  value: " + fromLocations.get(i));
            if(fromLocations.get(i))
            {   
                temp = from.getItem(i);
                profs.add(temp);
                to.add(temp);
            }
        }
        dialog.dismiss();
        for(Profile toDelete: profs)
        {
            from.remove(toDelete);
        }
    }

}
