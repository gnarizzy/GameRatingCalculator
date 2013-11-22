package com.Centaurii.app.RatingCalculator.fragments;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.listeners.OnCalculatorClickListener;
import com.Centaurii.app.RatingCalculator.listeners.OnProfileClickListener;
import com.Centaurii.app.RatingCalculator.listeners.OnSettingsClickListener;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class HomeScreenFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        
        final FragmentActivity activity = getActivity();
        
        Button calculatorView = (Button)view.findViewById(R.id.calculator);
        calculatorView.setOnClickListener(new OnCalculatorClickListener(activity));
        
        Button profileView = (Button)view.findViewById(R.id.profiles);
        profileView.setOnClickListener(new OnProfileClickListener(activity));
        
        Button settingsView = (Button)view.findViewById(R.id.settings);
        settingsView.setOnClickListener(new OnSettingsClickListener(activity));
        
        if(Build.VERSION.RELEASE.startsWith("2"))
        {
           //Create button to account for lack of action bar
           Button helpButton = new Button(activity);
           helpButton.setText(R.string.action_settings);
           helpButton.setGravity(Gravity.CENTER);
           int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
           LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, height);
           helpButton.setLayoutParams(params);
           
           helpButton.setOnClickListener(new OnClickListener()
           {

            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setMessage(R.string.help_dialog);
                dialog.setNeutralButton(R.string.ok, null);
                dialog.show();
            }
               
           });
           
           ((ViewGroup)view).addView(helpButton);
        }
        
        return view;
    }
}
