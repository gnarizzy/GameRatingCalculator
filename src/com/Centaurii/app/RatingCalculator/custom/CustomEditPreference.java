package com.Centaurii.app.RatingCalculator.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

public class CustomEditPreference extends EditTextPreference
{
    Context context;

    public CustomEditPreference(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
    }
    
    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        if(which == Dialog.BUTTON_POSITIVE)
        {
            EditText editText = getEditText();
            int value = Integer.valueOf(editText.getText().toString());
            if(value == 0)
            {
                Toast.makeText(context, "You cannot set the value to zero", Toast.LENGTH_SHORT).show();
            }
            else
            {
                super.onClick(dialog, which);
            }
        }
        else
        {
            super.onClick(dialog, which);
        }
    }
}
