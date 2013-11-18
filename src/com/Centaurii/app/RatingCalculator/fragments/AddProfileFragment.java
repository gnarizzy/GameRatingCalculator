package com.Centaurii.app.RatingCalculator.fragments;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import com.Centaurii.app.RatingCalculator.GameRatingCalculatorActivity;
import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.adapters.ColorAdapter;
import com.Centaurii.app.RatingCalculator.model.Profile;
import com.Centaurii.app.RatingCalculator.util.Tags;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProfileFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        final Resources res = getActivity().getResources();
        
        Set<String> colorSet = Tags.getColorMap().keySet();
        ArrayList<String> colorList = new ArrayList<String>(colorSet);
        ColorAdapter adapter = new ColorAdapter(getActivity(), R.layout.color_spinner, colorList);
        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_add_profile, null);
        
        final EditText eT = (EditText) view.findViewById(R.id.profile_name);
        eT.setHint(res.getString(R.string.profile_name));
        
        final EditText ratingTextBox = (EditText) view.findViewById(R.id.profile_rating);
        ratingTextBox.setHint(res.getString(R.string.rating) + " (Default is " + GameRatingCalculatorActivity.DEFAULT_RATING() + ")");
        
        //Make the whole checkbox area pressable and make num_prov_games appear
        final EditText numProvGames = (EditText) view.findViewById(R.id.num_prov_games);
        numProvGames.setHint(res.getString(R.string.prov_games_option) + " (Default is " + GameRatingCalculatorActivity.DEFAULT_PROVISIONAL() + ")");
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.profile_provisional);
        final LinearLayout myCheckBox = (LinearLayout) view.findViewById(R.id.my_check_box);
        myCheckBox.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                if(checkBox.isChecked())
                {
                    checkBox.setChecked(false);
                    numProvGames.setVisibility(View.GONE);
                }
                else
                {
                    checkBox.setChecked(true);
                    numProvGames.setVisibility(View.VISIBLE);
                }
            }
            
        });
        
        final Spinner colorSpinner = (Spinner) view.findViewById(R.id.profile_color);
        colorSpinner.setAdapter(adapter);
        
        
        /* Handles the creation of the new profile through an AlertDialog */
        DialogInterface.OnClickListener buttonPress = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                switch(id)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                        Random rand = new Random();
                        EditText name = (EditText) view.findViewById(R.id.profile_name);
                        EditText rating = (EditText) view.findViewById(R.id.profile_rating);
                        Spinner color = (Spinner) view.findViewById(R.id.profile_color);
                        CheckBox provisional = (CheckBox) view.findViewById(R.id.profile_provisional);
                        EditText numProvGames = (EditText) view.findViewById(R.id.num_prov_games);
                        
                        String profileName = name.getText().toString();
                        if(profileName.equals(""))
                        {
                            profileName = Tags.DEFAULT_NAME + (rand.nextInt() % 10000);
                        }
                        
                        //Check to see if adequate values were put in
                        int profileRating, profileProvGames;
                        try
                        {
                            profileRating = Integer.valueOf(rating.getText().toString());
                        }
                        catch(NumberFormatException e)
                        {
                            profileRating = GameRatingCalculatorActivity.DEFAULT_RATING();
                        }
                        try
                        {
                            profileProvGames = Integer.valueOf(numProvGames.getText().toString());
                            if(profileProvGames == 0)
                            {
                                Toast.makeText(getActivity(), res.getString(R.string.value_error), Toast.LENGTH_SHORT).show();
                                throw new NumberFormatException();
                            }
                        }
                        catch(NumberFormatException e)
                        {
                            profileProvGames = GameRatingCalculatorActivity.DEFAULT_PROVISIONAL();
                        }
                        int profileColor = Tags.getColorMap().get(color.getSelectedItem());
                        
                        Profile newProfile = new Profile(profileName,
                                                         profileRating,
                                                         provisional.isChecked(),
                                                         (provisional.isChecked() ? profileProvGames : 0),
                                                         profileColor);
                        
                        ArrayList<Profile> profiles = ((GameRatingCalculatorActivity) AddProfileFragment.this.getActivity())
                                                    .getSavedProfiles();
                        profiles.add(newProfile);
                        ((ProfileViewerFragment) AddProfileFragment.this.getActivity()
                                                                   .getSupportFragmentManager()
                                                                   .findFragmentByTag(Tags.PROFILE_FRAGMENT))
                                                                   .notifyAdapter();
                       
                        break;
                        
                    case DialogInterface.BUTTON_NEGATIVE:
                        AddProfileFragment.this.getDialog().cancel();
                        break;
                }
                
            }
        };
        builder.setView(view)
               .setPositiveButton(res.getString(R.string.add_profile), buttonPress)
               .setNegativeButton(res.getString(R.string.cancel), buttonPress);
        
        return builder.create();
    }
}
