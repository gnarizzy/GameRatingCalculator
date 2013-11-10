package com.Centaurii.app.RatingCalculator.listeners;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

public class RateAppClickListener implements DialogInterface.OnClickListener
{
    Context mContext;
    SharedPreferences.Editor editor;
    String appPackage;
    
    public RateAppClickListener(Context mContext,
            SharedPreferences.Editor editor,
            String appPackage)
    {
        this.mContext = mContext;
        this.editor = editor;
        this.appPackage = appPackage;
    }
    
    
    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        switch(which)
        {
            case Dialog.BUTTON_NEGATIVE:
                if (editor != null)
                {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialog.dismiss();
                break;
            case Dialog.BUTTON_NEUTRAL:
                dialog.dismiss();
                break;
            case Dialog.BUTTON_POSITIVE:
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("market://details?id=" + appPackage)));
                dialog.dismiss();
                break;
        }
        
    }
    
}
