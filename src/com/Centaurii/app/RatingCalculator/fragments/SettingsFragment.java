package com.Centaurii.app.RatingCalculator.fragments;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.listeners.GoBackClickListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SettingsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
    {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        
        Button goBack = (Button) view.findViewById(R.id.back_button);
        goBack.setOnClickListener(new GoBackClickListener(getActivity()));
        
        return view;
    }
}
