package com.Centaurii.app.RatingCalculator.fragments;

import java.util.ArrayList;

import com.Centaurii.app.RatingCalculator.GameRatingCalculatorActivity;
import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.adapters.GameAdapter;
import com.Centaurii.app.RatingCalculator.listeners.AddRemovePlayersItemClickListener;
import com.Centaurii.app.RatingCalculator.listeners.CalculatorButtonsOnClick;
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
    ArrayAdapter<Profile> nonPlayersAdapter;
    ArrayAdapter<Profile> playersAdapter;
    ArrayList<Profile> nonPlayers;
    ArrayList<Profile> players;
    
    Button whatIf, submit;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        Button goBack = (Button) view.findViewById(R.id.back_button);
        goBack.setOnClickListener(new GoBackClickListener(getActivity()));
        
        nonPlayers = new ArrayList<Profile>();
        nonPlayers.addAll(((GameRatingCalculatorActivity) getActivity()).getSavedProfiles());
        players = new ArrayList<Profile>();
        
        nonPlayersAdapter = new GameAdapter(getActivity(), R.layout.game_list_segment, nonPlayers);
        playersAdapter = new GameAdapter(getActivity(), R.layout.game_list_segment, players);
        gameList = (ListView) view.findViewById(R.id.game_list);
        
        View addNewPlayer = inflater.inflate(R.layout.add_new_player, null);
        gameList.addFooterView(addNewPlayer);
        gameList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        gameList.setAdapter(playersAdapter);
        gameList.setOnItemClickListener(new AddRemovePlayersItemClickListener(getActivity(),
                nonPlayersAdapter, playersAdapter));
        
        //Button that actually calculates score
        submit = (Button) view.findViewById(R.id.submit_button);
        submit.setOnClickListener(new CalculatorButtonsOnClick(getActivity(), gameList,
                playersAdapter));
        
        
        return view;
    }
}
