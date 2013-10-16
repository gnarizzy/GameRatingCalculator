package com.Centaurii.app.RatingCalculator.fragments;

import com.Centaurii.app.RatingCalculator.GameRatingCalculatorActivity;
import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.listeners.AddProfileOnClickListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileViewerFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
    {
        View view = inflater.inflate(R.layout.fragment_profiles, container, false);
        
        TextView header = (TextView)view.findViewById(R.id.profile_header);
        header.setText("Profiles");
        
        Button addProfile = (Button)view.findViewById(R.id.add_profile);
        addProfile.setOnClickListener(
                new AddProfileOnClickListener((GameRatingCalculatorActivity)getActivity()));
        
        return view;
    }
}
