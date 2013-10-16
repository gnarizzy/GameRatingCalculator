package com.Centaurii.app.RatingCalculator.fragments;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.listeners.GoBackClickListener;
import com.Centaurii.app.RatingCalculator.model.Profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CalculatorFragment extends Fragment
{
    ListView gameList;
    ArrayAdapter<Profile> adapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        Button goBack = (Button) view.findViewById(R.id.back_button);
        goBack.setOnClickListener(new GoBackClickListener(getActivity()));
        
        gameList = (ListView) view.findViewById(R.id.game_list);
        
        return view;
    }
}
