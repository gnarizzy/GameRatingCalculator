package com.Centaurii.app.RatingCalculator.fragments;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.listeners.OnProfileClickListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeScreenFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        
        Button profileView = (Button)view.findViewById(R.id.profiles);
        profileView.setOnClickListener(new OnProfileClickListener(getActivity()));
        
        return view;
    }
}
