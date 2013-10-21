package com.Centaurii.app.RatingCalculator.fragments;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.listeners.OnCalculatorClickListener;
import com.Centaurii.app.RatingCalculator.listeners.OnProfileClickListener;
import com.Centaurii.app.RatingCalculator.listeners.OnSettingsClickListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
        
        FragmentActivity activity = getActivity();
        
        Button calculatorView = (Button)view.findViewById(R.id.calculator);
        calculatorView.setOnClickListener(new OnCalculatorClickListener(activity));
        
        Button profileView = (Button)view.findViewById(R.id.profiles);
        profileView.setOnClickListener(new OnProfileClickListener(activity));
        
        Button settingsView = (Button)view.findViewById(R.id.settings);
        settingsView.setOnClickListener(new OnSettingsClickListener(activity));
        
        return view;
    }
}
