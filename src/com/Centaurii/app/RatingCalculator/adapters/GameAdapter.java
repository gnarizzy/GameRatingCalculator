package com.Centaurii.app.RatingCalculator.adapters;

import java.util.List;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.model.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class GameAdapter extends ArrayAdapter<Profile>
{
    Context context;
    int resource;
    List<Profile> objects;
    
    public GameAdapter(Context context, int resource, List<Profile> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    
    @Override
    public Profile getItem(int i)
    {
        return objects.get(i);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        ProfileHolder holder;
        if(v == null)
        {
            LayoutInflater inflater = 
                    (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resource, parent, false);
            
            holder = new ProfileHolder();
            holder.winner = (CheckBox) v.findViewById(R.id.winner);
            holder.profileImage = (ImageView) v.findViewById(R.id.profile_image);
            holder.profileName = (TextView) v.findViewById(R.id.name);
            holder.profileRating = (TextView) v.findViewById(R.id.rating);
            holder.profileProvisional = (TextView) v.findViewById(R.id.provisional);
            
            v.setTag(holder);
        }
        else
        {
            holder = (ProfileHolder)v.getTag();
        }
        
        //You may need to set stuff visible.  This is your personal note on that
        
        final Profile prof = getItem(position);
        holder.profileImage.setBackgroundColor(prof.getFavColor());
        holder.profileName.setText(prof.getName());
        holder.profileRating.setText("" + prof.getRating());
        if(prof.isProvisional())
        {
            holder.profileProvisional.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.profileProvisional.setVisibility(View.GONE);
        }
        
        
        return v;
    }
    
    static class ProfileHolder
    {
        CheckBox winner;
        TextView addNewPlayer;
        ImageView profileImage;
        TextView profileName;
        TextView profileRating;
        TextView profileProvisional;
    }
}
