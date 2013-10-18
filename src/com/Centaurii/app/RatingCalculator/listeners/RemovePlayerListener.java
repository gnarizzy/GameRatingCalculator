package com.Centaurii.app.RatingCalculator.listeners;

import com.Centaurii.app.RatingCalculator.model.Profile;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RemovePlayerListener implements OnClickListener
{
    ListView list;
    int position;
    ArrayAdapter<Profile> from, to;
    
    public RemovePlayerListener(ListView list, int position,
            ArrayAdapter<Profile> from, ArrayAdapter<Profile> to)
    {
        super();
        this.list = list;
        this.position = position;
        this.from = from;
        this.to = to;
    }

    @Override
    public void onClick(DialogInterface dialog, int id)
    {
        switch(id)
        {
            case Dialog.BUTTON_POSITIVE:
                list.setItemChecked(position, true);
                break;
                
            case Dialog.BUTTON_NEGATIVE:
                list.setItemChecked(position, false);
                Profile temp = from.getItem(position);
                to.add(temp);
                from.remove(temp);
                break;
        }
    }

}
