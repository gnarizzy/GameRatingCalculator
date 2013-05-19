package com.Centaurii.app.RatingCalculator;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Controller class that sets up listeners/event handlers and interacts with Calculator.java based on user input
 * Created 9/10/12
 * Last updated 11/15/12
 * @author Gautam Narula
 *
 */

public class GameRatingCalculatorActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static final String PLAYER1="PLAYER1";
	private static final String PLAYER2="PLAYER2";
	private static final String PLAYER3="PLAYER3";
	private static final String PLAYER4="PLAYER4";
	private static final String PLAYER5="PLAYER5";
	private static final String PLAYER6="PLAYER6";
	private static final String SIZE="SIZE";
	private static final String P1PROVISIONAL= "P1PROVISIONAL";
	private static final String P2PROVISIONAL= "P2PROVISIONAL";
	private static final String P3PROVISIONAL= "P3PROVISIONAL";
	private static final String P4PROVISIONAL= "P4PROVISIONAL";
	private static final String P5PROVISIONAL= "P5PROVISIONAL";
	private static final String P6PROVISIONAL= "P6PROVISIONAL";
	
	private int player1;
	private int player2;
	private int player3;
	private int player4;
	private int player5;
	private int player6; 
	private int size;
	private int winner=1;
	
	private boolean p1Provisional;
	private boolean p2Provisional;
	private boolean p3Provisional;
	private boolean p4Provisional;
	private boolean p5Provisional;
	private boolean p6Provisional;
	
	private EditText p1EditText;
	private EditText p2EditText;
	private EditText p3EditText;
	private EditText p4EditText;
	private EditText p5EditText;
	private EditText p6EditText;
//	private EditText[] editTexts;
	
	private TextView p1Text;
	private TextView p2Text;
	private TextView p3Text;
	private TextView p4Text;
	private TextView p5Text;
	private TextView p6Text;
	private TextView numPlayers;
	
	private TextView player1Text;
	private TextView player2Text;
	private TextView player3Text;
	private TextView player4Text;
	private TextView player5Text;
	private TextView player6Text;
	
	private RadioButton threePlayers;
	private RadioButton fourPlayers;
	private RadioButton fivePlayers;
	private RadioButton sixPlayers;
	
	private CheckBox check1;
	private CheckBox check2;
	private CheckBox check3;
	private CheckBox check4;
	private CheckBox check5;
	private CheckBox check6;
	
	private CheckBox check01;
	private CheckBox check02;
	private CheckBox check03;
	private CheckBox check04;
	private CheckBox check05;
	private CheckBox check06;
	
	private SeekBar sizeSeekBar;
	
	private Button calculateButton;
	
	private static Calculator calculator;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        calculator=new Calculator();
        if (savedInstanceState==null) //app just started up
        	size=2;
      
        else //resuming 
        	{
        	player1=savedInstanceState.getInt(PLAYER1);
        	player2=savedInstanceState.getInt(PLAYER2);
        	player3=savedInstanceState.getInt(PLAYER3);
        	player4=savedInstanceState.getInt(PLAYER4);
        	player5=savedInstanceState.getInt(PLAYER5);
        	player6=savedInstanceState.getInt(PLAYER6);
        	size=savedInstanceState.getInt(SIZE);
        	onButtonClick(calculateButton);
        	}
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        p1EditText= (EditText) findViewById(R.id.editTextp1);
        p2EditText= (EditText) findViewById(R.id.EditTextp2);
        p3EditText= (EditText) findViewById(R.id.EditTextp3);
        p4EditText= (EditText) findViewById(R.id.EditTextp4);
        p5EditText= (EditText) findViewById(R.id.EditTextp5);
        p6EditText= (EditText) findViewById(R.id.EditTextp6);
        
        calculateButton= (Button) findViewById(R.id.calculateButton);
        
        
        p1EditText.addTextChangedListener(new TextListener1());
        p2EditText.addTextChangedListener(new TextListener2());
        p3EditText.addTextChangedListener(new TextListener3());
        p4EditText.addTextChangedListener(new TextListener4());
        p5EditText.addTextChangedListener(new TextListener5());
        p6EditText.addTextChangedListener(new TextListener6());
        
        p1Text=  (TextView) findViewById(R.id.p1Text);
        p2Text=  (TextView) findViewById(R.id.p2Text);
        p3Text=  (TextView) findViewById(R.id.p3Text);
        p4Text=  (TextView) findViewById(R.id.p4Text);
        p5Text=  (TextView) findViewById(R.id.p5Text);
        p6Text=  (TextView) findViewById(R.id.p6Text);
        
        player1Text= (TextView) findViewById(R.id.player1);
        player2Text= (TextView) findViewById(R.id.player2);
        player3Text= (TextView) findViewById(R.id.player3);
        player4Text= (TextView) findViewById(R.id.player4);
        player5Text= (TextView) findViewById(R.id.player5);
        player6Text= (TextView) findViewById(R.id.player6);
        
        numPlayers= (TextView) findViewById(R.id.numPlayersTextView);
//        threePlayers= (RadioButton) findViewById(R.id.radio3Player);
//        fourPlayers= (RadioButton) findViewById(R.id.radio4Player);
//        fivePlayers= (RadioButton) findViewById(R.id.radio5Player);
//        sixPlayers= (RadioButton) findViewById(R.id.radio6Player);
        
        check1= (CheckBox) findViewById(R.id.checkBox1);
        check2= (CheckBox) findViewById(R.id.checkBox2);
        check3= (CheckBox) findViewById(R.id.checkBox3);
        check4= (CheckBox) findViewById(R.id.checkBox4);
        check5= (CheckBox) findViewById(R.id.checkBox5);
        check6= (CheckBox) findViewById(R.id.checkBox6);
        
        check01= (CheckBox) findViewById(R.id.checkBox01);
        check02= (CheckBox) findViewById(R.id.checkBox02);
        check03= (CheckBox) findViewById(R.id.checkBox03);
        check04= (CheckBox) findViewById(R.id.checkBox04);
        check05= (CheckBox) findViewById(R.id.checkBox05);
        check06= (CheckBox) findViewById(R.id.checkBox06);
        
        CheckListener checkListener=new CheckListener();
        check1.setOnClickListener(checkListener);
        check2.setOnClickListener(checkListener);
        check3.setOnClickListener(checkListener);
        check4.setOnClickListener(checkListener);
        check5.setOnClickListener(checkListener);
        check6.setOnClickListener(checkListener);
        
        check01.setOnClickListener(checkListener);
        check02.setOnClickListener(checkListener);
        check03.setOnClickListener(checkListener);
        check04.setOnClickListener(checkListener);
        check05.setOnClickListener(checkListener);
        check06.setOnClickListener(checkListener);
        
        
        sizeSeekBar= (SeekBar) findViewById(R.id.sizeSeekBar);
        sizeSeekBar.setOnSeekBarChangeListener(new SeekBarListener());
       
        updateValues();
      
        player3Text.setVisibility(View.INVISIBLE);
		player4Text.setVisibility(View.INVISIBLE);
		player5Text.setVisibility(View.INVISIBLE);
		player6Text.setVisibility(View.INVISIBLE);
		
		p3EditText.setVisibility(View.INVISIBLE);
		p4EditText.setVisibility(View.INVISIBLE);
		p5EditText.setVisibility(View.INVISIBLE);
		p6EditText.setVisibility(View.INVISIBLE);
		
		p3Text.setVisibility(View.INVISIBLE);
		p4Text.setVisibility(View.INVISIBLE);
		p5Text.setVisibility(View.INVISIBLE);
		p6Text.setVisibility(View.INVISIBLE);
		
		check3.setVisibility(View.INVISIBLE);
		check03.setVisibility(View.INVISIBLE);
		check4.setVisibility(View.INVISIBLE);
		check04.setVisibility(View.INVISIBLE);
		check5.setVisibility(View.INVISIBLE);
		check05.setVisibility(View.INVISIBLE);
		check6.setVisibility(View.INVISIBLE);
		check06.setVisibility(View.INVISIBLE);
        
    }

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		outState.putInt(PLAYER1, player1);
		outState.putInt(PLAYER2, player2);
		outState.putInt(PLAYER3, player3);
		outState.putInt(PLAYER4, player4);
		outState.putInt(PLAYER5, player5);
		outState.putInt(PLAYER6, player6);
		outState.putInt(SIZE, size);
		outState.putBoolean(P1PROVISIONAL, p1Provisional);
		outState.putBoolean(P2PROVISIONAL, p2Provisional);
		outState.putBoolean(P3PROVISIONAL, p3Provisional);
		outState.putBoolean(P4PROVISIONAL, p4Provisional);
		outState.putBoolean(P5PROVISIONAL, p5Provisional);
		outState.putBoolean(P6PROVISIONAL, p6Provisional);
	}
	
	private void updateValues()
	{  if((winner-1)>=0&&(winner-1)<6) //making sure it's valid due to unknown nullpointers here
		{
		try {
		calculator.setWinner(winner-1);
		calculator.setSize(size);
		calculator.setRating(1,player1);
		calculator.setRating(2,player2);
		calculator.setRating(3,player3);
		calculator.setRating(4,player4);
		calculator.setRating(5,player5);
		calculator.setRating(6,player6);
		if(p1Provisional)
			calculator.setProvisional(1);
		else
			calculator.unSetProvisional(1);
		if(p2Provisional)
			calculator.setProvisional(2);
		else
			calculator.unSetProvisional(2);
		if(p3Provisional)
			calculator.setProvisional(3);
		else
			calculator.unSetProvisional(3);
		if(p4Provisional)
			calculator.setProvisional(4);
		else
			calculator.unSetProvisional(4);
		if(p5Provisional)
			calculator.setProvisional(5);
		else
			calculator.unSetProvisional(5);
		if(p6Provisional)
			calculator.setProvisional(6);
		else
			calculator.unSetProvisional(6);}
		catch (Exception e) {}
		}
	}
	
	public void onButtonClick(View v)
	{	updateValues();
	try {
		int[] results= calculator.calculateRating(); //a nullpointer exception occurs here sometimes when resuming. Not sure why. 
		
		p1Text.setText("New Rating: "+results[0]); //nullpointer exception sometimes happened here. Not sure why.
		p2Text.setText("New Rating: "+results[1]);
		if (size>2)
			p3Text.setText("New Rating: "+results[2]);

		if (size>3)
			p4Text.setText("New Rating: "+results[3]);
		if (size>4)
			p5Text.setText("New Rating: "+results[4]);
		if (size>5)
			p6Text.setText("New Rating: "+results[5]);}
		catch(Exception e){}
		
	}
	
	
	public void onRadioButtonClick(View v) {
	    RadioButton button = (RadioButton) v;
	   if (button==threePlayers)
	   { calculator.setSize(3);
	     size=3;}
	   if (button==fourPlayers)
	   {  calculator.setSize(4);
	   	  size=4;
	   }
	   if (button==fivePlayers)
	   { calculator.setSize(5);
	   	   size=5;}
	   if (button==sixPlayers)
	   { calculator.setSize(6);
	     size=6;
	   }
	   
	}
	
	private class CheckListener implements OnClickListener{

		public void onClick(View v) {
			if(v==check01)
				{ winner=1;
				 if(check02.isChecked())
					 check02.toggle();
				 if(check03.isChecked())
					 check03.toggle();
				 if(check04.isChecked())
					 check04.toggle();
				 if(check05.isChecked())
					 check05.toggle();
				 if(check06.isChecked())
					 check06.toggle();
				}
			
			
			
			if(v==check02)
			{ winner=2;
			 if(check01.isChecked())
				 check01.toggle();
			 if(check03.isChecked())
				 check03.toggle();
			 if(check04.isChecked())
				 check04.toggle();
			 if(check05.isChecked())
				 check05.toggle();
			 if(check06.isChecked())
				 check06.toggle();
			}
			if(v==check03)
			{ winner=3;
			 if(check01.isChecked())
				 check01.toggle();
			 if(check02.isChecked())
				 check02.toggle();
			 if(check04.isChecked())
				 check04.toggle();
			 if(check05.isChecked())
				 check05.toggle();
			 if(check06.isChecked())
				 check06.toggle();
			}
			if(v==check04)
			{ winner=4;
			 if(check01.isChecked())
				 check01.toggle();
			 if(check02.isChecked())
				 check02.toggle();
			 if(check03.isChecked())
				 check03.toggle();
			 if(check05.isChecked())
				 check05.toggle();
			 if(check06.isChecked())
				 check06.toggle();
			}
			if(v==check05)
			{ winner=5;
			 if(check01.isChecked())
				 check01.toggle();
			 if(check02.isChecked())
				 check02.toggle();
			 if(check03.isChecked())
				 check03.toggle();
			 if(check04.isChecked())
				 check04.toggle();
			 if(check06.isChecked())
				 check06.toggle();
			}
			if(v==check06)
			{ winner=6;
			 if(check01.isChecked())
				 check01.toggle();
			 if(check02.isChecked())
				 check02.toggle();
			 if(check03.isChecked())
				 check03.toggle();
			 if(check04.isChecked())
				 check04.toggle();
			 if(check05.isChecked())
				 check05.toggle();
			}
			if (v==check1)
				{if (((CheckBox)v).isChecked())
					p1Provisional=true;
				else 
					p1Provisional=false;}
			
			if (v==check2)
				{if (((CheckBox)v).isChecked())
					p2Provisional=true;
				else 
					p2Provisional=false;}
			
			if (v==check3)
			{if (((CheckBox)v).isChecked())
				p3Provisional=true;
			else 
				p3Provisional=false;}
			
			if (v==check4)
			{if (((CheckBox)v).isChecked())
				p4Provisional=true;
			else 
				p4Provisional=false;}
			
			if (v==check5)
			{if (((CheckBox)v).isChecked())
				p5Provisional=true;
			else 
				p5Provisional=false;}
			
			if (v==check6)
			{if (((CheckBox)v).isChecked())
				p6Provisional=true;
			else 
				p6Provisional=false;}
			
			updateValues();
			
		}
		
	}
	
	private class TextListener1 implements TextWatcher{
	
		public void afterTextChanged(Editable s) {
			
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			try {
				
				player1= Integer.parseInt(s.toString());
				calculator.setRating(1, player1);
				
			}
			catch (Exception e)
			{
				
			}
		}
		
	}
	private class TextListener2 implements TextWatcher{

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			try {
				player2= Integer.parseInt(s.toString());
				calculator.setRating(2, player2);
				
			}
			catch (Exception e)
			{
				
		    }
		}
	}
	private class TextListener3 implements TextWatcher{

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			try {
				player3= Integer.parseInt(s.toString());
				calculator.setRating(3, player3);
			}
			catch (Exception e)
			{
				
			}
		}
		
	}
	private class TextListener4 implements TextWatcher{

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			try {
				player4= Integer.parseInt(s.toString());
				calculator.setRating(4, player4);
			}
			catch (NumberFormatException e)
			{
				
			}
		}
		
	}
	
	private class TextListener5 implements TextWatcher{

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			try {
				player5= Integer.parseInt(s.toString());
				calculator.setRating(5, player5);
			}
			catch (NumberFormatException e)
			{
				
			}
		}
		
	}
	
	private class SeekBarListener implements OnSeekBarChangeListener{

		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			switch (progress){ //progress bar goes from 0-4 so add two to value to get real size
			case 0: size=2;
					updateValues();
					numPlayers.setText("Number of Players: 2");
					
					check3.setVisibility(View.INVISIBLE);
					check03.setVisibility(View.INVISIBLE);
					check4.setVisibility(View.INVISIBLE);
					check04.setVisibility(View.INVISIBLE);
					check5.setVisibility(View.INVISIBLE);
					check05.setVisibility(View.INVISIBLE);
					check6.setVisibility(View.INVISIBLE);
					check06.setVisibility(View.INVISIBLE);
					
					player3Text.setVisibility(View.INVISIBLE);
					player4Text.setVisibility(View.INVISIBLE);
					player5Text.setVisibility(View.INVISIBLE);
					player6Text.setVisibility(View.INVISIBLE);
					
					p3EditText.setVisibility(View.INVISIBLE);
					p4EditText.setVisibility(View.INVISIBLE);
					p5EditText.setVisibility(View.INVISIBLE);
					p6EditText.setVisibility(View.INVISIBLE);
					
					p3Text.setVisibility(View.INVISIBLE);
					p4Text.setVisibility(View.INVISIBLE);
					p5Text.setVisibility(View.INVISIBLE);
					p6Text.setVisibility(View.INVISIBLE);
					break;
					
			case 1: size=3;
					updateValues();
					numPlayers.setText("Number of Players: 3");
					
					check3.setVisibility(View.VISIBLE);
					check03.setVisibility(View.VISIBLE);
					check4.setVisibility(View.INVISIBLE);
					check04.setVisibility(View.INVISIBLE);
					check5.setVisibility(View.INVISIBLE);
					check05.setVisibility(View.INVISIBLE);
					check6.setVisibility(View.INVISIBLE);
					check06.setVisibility(View.INVISIBLE);
					
					player3Text.setVisibility(View.VISIBLE);
					player4Text.setVisibility(View.INVISIBLE);
					player5Text.setVisibility(View.INVISIBLE);
					player6Text.setVisibility(View.INVISIBLE);
					
					p3EditText.setVisibility(View.VISIBLE);
					p4EditText.setVisibility(View.INVISIBLE);
					p5EditText.setVisibility(View.INVISIBLE);
					p6EditText.setVisibility(View.INVISIBLE);
					
					p3Text.setVisibility(View.VISIBLE);
					p4Text.setVisibility(View.INVISIBLE);
					p5Text.setVisibility(View.INVISIBLE);
					p6Text.setVisibility(View.INVISIBLE);
					break;
					
			case 2: size=4;
					updateValues();
					numPlayers.setText("Number of Players: 4");
					
					check3.setVisibility(View.VISIBLE);
					check03.setVisibility(View.VISIBLE);
					check4.setVisibility(View.VISIBLE);
					check04.setVisibility(View.VISIBLE);
					check5.setVisibility(View.INVISIBLE);
					check05.setVisibility(View.INVISIBLE);
					check6.setVisibility(View.INVISIBLE);
					check06.setVisibility(View.INVISIBLE);
					
					player3Text.setVisibility(View.VISIBLE);
					player4Text.setVisibility(View.VISIBLE);
					player5Text.setVisibility(View.INVISIBLE);
					player6Text.setVisibility(View.INVISIBLE);
					
					p3EditText.setVisibility(View.VISIBLE);
					p4EditText.setVisibility(View.VISIBLE);
					p5EditText.setVisibility(View.INVISIBLE);
					p6EditText.setVisibility(View.INVISIBLE);
					
					p3Text.setVisibility(View.VISIBLE);
					p4Text.setVisibility(View.VISIBLE);
					p5Text.setVisibility(View.INVISIBLE);
					p6Text.setVisibility(View.INVISIBLE);
					break;
			case 3: size=5;
					updateValues();
					numPlayers.setText("Number of Players: 5");
					
					check3.setVisibility(View.VISIBLE);
					check03.setVisibility(View.VISIBLE);
					check4.setVisibility(View.VISIBLE);
					check04.setVisibility(View.VISIBLE);
					check5.setVisibility(View.VISIBLE);
					check05.setVisibility(View.VISIBLE);
					check6.setVisibility(View.INVISIBLE);
					check06.setVisibility(View.INVISIBLE);
					
					player3Text.setVisibility(View.VISIBLE);
					player4Text.setVisibility(View.VISIBLE);
					player5Text.setVisibility(View.VISIBLE);
					player6Text.setVisibility(View.INVISIBLE);
					
					p3EditText.setVisibility(View.VISIBLE);
					p4EditText.setVisibility(View.VISIBLE);
					p5EditText.setVisibility(View.VISIBLE);
					p6EditText.setVisibility(View.INVISIBLE);
					
					p3Text.setVisibility(View.VISIBLE);
					p4Text.setVisibility(View.VISIBLE);
					p5Text.setVisibility(View.VISIBLE);
					p6Text.setVisibility(View.INVISIBLE);
					break;	
			case 4: size=6;
					updateValues();
					numPlayers.setText("Number of Players: 6");
					
					check3.setVisibility(View.VISIBLE);
					check03.setVisibility(View.VISIBLE);
					check4.setVisibility(View.VISIBLE);
					check04.setVisibility(View.VISIBLE);
					check5.setVisibility(View.VISIBLE);
					check05.setVisibility(View.VISIBLE);
					check6.setVisibility(View.VISIBLE);
					check06.setVisibility(View.VISIBLE);
					
					player3Text.setVisibility(View.VISIBLE);
					player4Text.setVisibility(View.VISIBLE);
					player5Text.setVisibility(View.VISIBLE);
					player6Text.setVisibility(View.VISIBLE);
					
					p3EditText.setVisibility(View.VISIBLE);
					p4EditText.setVisibility(View.VISIBLE);
					p5EditText.setVisibility(View.VISIBLE);
					p6EditText.setVisibility(View.VISIBLE);
					
					p3Text.setVisibility(View.VISIBLE);
					p4Text.setVisibility(View.VISIBLE);
					p5Text.setVisibility(View.VISIBLE);
					p6Text.setVisibility(View.VISIBLE);
					break;	
			}
			
			
			
		}

		public void onStartTrackingTouch(SeekBar arg0) {
			
			
		}

		public void onStopTrackingTouch(SeekBar arg0) {
			
			
		}
		
	}
	
	private class TextListener6 implements TextWatcher{

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			try {
				player6= Integer.parseInt(s.toString());
				calculator.setRating(6, player1);
			}
			catch (NumberFormatException e)
			{
			
			}
		}
	
	
		
	}
}