package com.Centaurii.app.RatingCalculator.util;

import com.Centaurii.app.RatingCalculator.R;
import com.Centaurii.app.RatingCalculator.listeners.RateAppClickListener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class AppRater
{
    private final static String APP_TITLE = "Game Rating Calculator";
    private final static String APP_PNAME = "com.Centaurii.app.RatingCalculator";

    private final static int DAYS_UNTIL_PROMPT = 3;
    private final static int LAUNCHES_UNTIL_PROMPT = 7;

    public static void app_launched(Context mContext)
    {
        SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
        if (prefs.getBoolean("dontshowagain", false))
        {
            return;
        }

        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0)
        {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT)
        {
            if (System.currentTimeMillis() >= date_firstLaunch
                    + (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000))
            {
                showRateDialog(mContext, editor);
            }
        }

        editor.commit();
    }

    public static void showRateDialog(final Context mContext,
            final SharedPreferences.Editor editor)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        View view = inflater.inflate(R.layout.rate_app_layout, null, false);
        
        TextView header = (TextView) view.findViewById(R.id.rate_app_header);
        header.setText("Rate the " + APP_TITLE);
        
        TextView body = (TextView) view.findViewById(R.id.rate_app_body);
        body.setText("If you enjoy using " + APP_TITLE +", please take a moment " +
                "to leave a rating.  If you have any suggestions for future releases, " +
                "feel free to leave them in your comments.\n\nThank you for your support!\n");
        
        DialogInterface.OnClickListener listener = new RateAppClickListener(mContext,
                editor, APP_PNAME);
        
        builder.setView(view)
               .setNegativeButton("No, thanks", listener)
               .setNeutralButton("Remind me later", listener)
               .setPositiveButton("Rate the app!", listener);

        builder.show();
    }
    
    
}