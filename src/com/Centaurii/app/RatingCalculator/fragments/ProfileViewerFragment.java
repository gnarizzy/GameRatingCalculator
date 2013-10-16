package com.Centaurii.app.RatingCalculator.fragments;

import java.util.ArrayList;

import com.Centaurii.app.RatingCalculator.GameRatingCalculatorActivity;
import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.adapters.ProfileListAdapter;
import com.Centaurii.app.RatingCalculator.listeners.AddProfileOnClickListener;
import com.Centaurii.app.RatingCalculator.listeners.GoBackClickListener;
import com.Centaurii.app.RatingCalculator.model.Profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileViewerFragment extends Fragment implements OnItemClickListener
{
    ListView profilesList;
    ProfileListAdapter adapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
    {
        View view = inflater.inflate(R.layout.fragment_profiles, container, false);

        Button goBack = (Button) view.findViewById(R.id.back_button);
        goBack.setOnClickListener(new GoBackClickListener(getActivity()));
        
        TextView header = (TextView)view.findViewById(R.id.profile_header);
        header.setText("Profiles");
        
        Button addProfile = (Button)view.findViewById(R.id.add_profile);
        addProfile.setOnClickListener(
                new AddProfileOnClickListener((GameRatingCalculatorActivity)getActivity()));
        
        ArrayList<Profile> profiles = ((GameRatingCalculatorActivity) getActivity()).getSavedProfiles();
        
        profilesList = (ListView)view.findViewById(R.id.profiles_list);
        adapter = new ProfileListAdapter(getActivity(), R.layout.profile_list_segement, profiles);
        
        profilesList.setAdapter(adapter);
        profilesList.setOnItemClickListener(this);
        
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        DeleteListener listener = new DeleteListener((GameRatingCalculatorActivity) getActivity(), position);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Delete Profile?")
               .setPositiveButton("Yes", listener)
               .setNegativeButton("No", listener)
               .create()
               .show();
    }
    
    public void notifyAdapter()
    {
        adapter.notifyDataSetChanged();
    }
    
    private class DeleteListener implements DialogInterface.OnClickListener
    {
        GameRatingCalculatorActivity act;
        int position;
        
        public DeleteListener(GameRatingCalculatorActivity act, int position)
        {
            this.act = act;
            this.position = position;
        }
        
        @Override
        public void onClick(DialogInterface dialog, int id)
        {
            switch(id)
            {
                case Dialog.BUTTON_POSITIVE:
                    act.getSavedProfiles().remove(position);
                    ProfileViewerFragment.this.adapter.notifyDataSetChanged();
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    break;
            }
        }
        
    }
}
