package com.Centaurii.app.RatingCalculator.util;

import java.util.List;

import com.Centaurii.app.RatingCalculator.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ColorAdapter extends ArrayAdapter<String>
{
    Context context;
    int resource;
    List<String> objects;
    
    
    public ColorAdapter(Context context, int resource, List<String> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }
    
    @Override
    public String getItem(int i)
    {
        return objects.get(i);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        ColorHandler handler;
        if(v == null)
        {
            LayoutInflater inflater = 
                    (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resource, parent, false);
            
            handler = new ColorHandler();
            handler.colorPreview = (TextView) v.findViewById(R.id.sample_color);
            handler.colorName = (TextView) v.findViewById(R.id.color_name);
            
            v.setTag(handler);
        }
        else
        {
            handler = (ColorHandler)v.getTag();
        }
        
        final String color = getItem(position);
        final int sample = Tags.getColorMap().get(color);
        handler.colorName.setText(color);
        handler.colorPreview.setBackgroundColor(sample);
        
        return v;
    }
    
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        ColorHandler handler;
        if(v == null)
        {
            LayoutInflater inflater = 
                    (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resource, parent, false);
            
            handler = new ColorHandler();
            handler.colorPreview = (TextView) v.findViewById(R.id.sample_color);
            handler.colorName = (TextView) v.findViewById(R.id.color_name);
            
            v.setTag(handler);
        }
        else
        {
            handler = (ColorHandler)v.getTag();
        }
        
        final String color = getItem(position);
        final int sample = Tags.getColorMap().get(color);
        handler.colorName.setText(color);
        handler.colorPreview.setBackgroundColor(sample);
        
        return v;
    }
    
    static class ColorHandler
    {
        TextView colorPreview;
        TextView colorName;
    }

}
