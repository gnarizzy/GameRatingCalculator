package com.Centaurii.app.RatingCalculator.fragments;

import com.Centaurii.app.RatingCalculator.GameRatingCalculatorActivity;
import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.model.Profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AddProfileFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_add_profile, null);
        
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
                }
                else
                {
                    checkBox.setChecked(true);
                }
            }
            
        });
        
        DialogInterface.OnClickListener buttonPress = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                switch(id)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                        EditText name = (EditText) view.findViewById(R.id.profile_name);
                        EditText rating = (EditText) view.findViewById(R.id.profile_rating);
                        EditText color = (EditText) view.findViewById(R.id.profile_color);
                        CheckBox provisional = (CheckBox) view.findViewById(R.id.profile_provisional);
                        
                        String profileName = name.getText().toString();
                        int profileRating = 1000;
                        try
                        {
                            profileRating = Integer.valueOf(rating.getText().toString());
                        }
                        catch(NumberFormatException e)
                        {
                            e.printStackTrace();
                        }
                        int profileColor = 0;
                        try
                        {
                            profileColor = Integer.valueOf(color.getText().toString());
                        }
                        catch(NumberFormatException e)
                        {
                            e.printStackTrace();
                        }
                        
                        Profile newProfile = new Profile(profileName,
                                                         profileRating,
                                                         provisional.isChecked(),
                                                         profileColor);
                        
                        ((GameRatingCalculatorActivity) AddProfileFragment.this.getActivity())
                                                    .getSavedProfiles()
                                                    .add(newProfile);
                        break;
                        
                    case DialogInterface.BUTTON_NEGATIVE:
                        AddProfileFragment.this.getDialog().cancel();
                        break;
                }
                
            }
        };
        builder.setView(view)
               .setPositiveButton("Add", buttonPress)
               .setNegativeButton("Cancel", buttonPress);
        
        return builder.create();
    }
}
