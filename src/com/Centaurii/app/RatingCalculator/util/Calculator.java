package com.Centaurii.app.RatingCalculator.util;

import com.Centaurii.app.RatingCalculator.model.Profile;

/**
 * Model class that stores information of players and calculates new ratings
 * Created 1/15/12
 * 
 * @author Gautam Narula, Andrew Schuster
 * 
 */
public class Calculator
{
    private final static double KVALUE = 32;
    
    public static int[] calculateRating(int winner, boolean commit, Profile... players)
    {   
        int tracker = 0;
        int numberOfPlayers = players.length;
        int[] endScores = new int[numberOfPlayers];
        for(Profile player: players)
        {
            //losing case
            if(winner != tracker)
            {
                endScores[tracker] = (int) Math.round(player.getRating() + 
                        calcRatingHelperLose(player, numberOfPlayers, calcAverage(players, tracker)));
                if(commit)
                {
                    player.setRating(endScores[tracker]);
                    if(player.isProvisional())
                    {
                        player.setProvisionalGamesLeft(player.getProvisionalGamesLeft() - 1);
                        if(player.getProvisionalGamesLeft() < 1)
                        {
                            player.setProvisional(false);
                        }
                    }
                }
            }
            //winning case
            else
            {
                endScores[tracker] = (int) Math.round(player.getRating() +
                        calcRatingHelperWin(player, numberOfPlayers, calcAverage(players, tracker)));
                if(commit)
                {
                    player.setRating(endScores[tracker]);
                    if(player.isProvisional())
                    {
                        player.setProvisionalGamesLeft(player.getProvisionalGamesLeft() - 1);
                        if(player.getProvisionalGamesLeft() < 1)
                        {
                            player.setProvisional(false);
                        }
                    }
                }
            }
            tracker++;
        }
        
        return endScores;
    }
    
    private static int calcAverage(Profile[] players, int pos)
    {
        int avgRating = 0;
        for(Profile player: players)
        {
            avgRating += player.getRating();
        }
        avgRating -= players[pos].getRating();
        avgRating = avgRating / (players.length - 1);
        return avgRating;
    }
    
    private static double calcRatingHelperWin(Profile player, int numPlayers, int avgRating)
    {
        int rating = player.getRating();
        
        double exponent = -1 * (rating - avgRating) / 400.0;
        double denominator = Math.pow(10, exponent) + 1;
        double expectancy = (1.0 / denominator) * (2.0 / numPlayers);
        if(player.isProvisional())
        {
            return (2 * KVALUE * (1 - expectancy));
        }
        return (KVALUE * (1 - expectancy));
    }
    
    private static double calcRatingHelperLose(Profile player, int numPlayers, int avgRating)
    {
        int rating = player.getRating();
        
        double exponent = -1 * (rating - avgRating) / 400.0;
        double denominator = Math.pow(10, exponent) + 1;
        double expectancy = (1.0 / denominator) * (2.0 / numPlayers);
        if(player.isProvisional())
        {
            return (2 * KVALUE * (0 - expectancy));
        }
        return (KVALUE * (0 - expectancy));
    }
}
