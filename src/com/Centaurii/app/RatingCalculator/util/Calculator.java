package com.Centaurii.app.RatingCalculator.util;

/**
 * Model class that stores information of players and calculates new ratings
 * Created 1/15/12
 * 
 * @author Gautam Narula
 * 
 */
public class Calculator
{

    private double rating1, rating2, rating3, rating4, rating5, rating6;
    double[] ratings = { rating1, rating2, rating3, rating4, rating5, rating6 };
    private final double KVALUE = 32;
    private double size = 6.0;
    private boolean player1, player2, player3, player4, player5, player6;
    boolean[] provisionals = { player1, player2, player3, player4, player5,
            player6 };
    int winner = 0;

    public void setWinner(int win)
    {
        winner = win;
    }

    public void setSize(int newSize)
    {
        size = (double) newSize;
    }

    public void setRating(int position, int rating)
    {
        ratings[position - 1] = (double) rating;
    }

    private double getAverageRating(int position)
    {
        double sum = 0.0;
        for (int i = 0; i < (int) (size); i++)
            sum = sum + ratings[i];
        double total = sum - ratings[position - 1];
        return total / (size - 1);

    }

    private double winExpectancy(double rating, double oppRating)

    {
        double exponent = -1 * (rating - oppRating) / 400.0;
        double denominator = Math.pow(10, exponent) + 1;
        double expectancy = (1.0 / denominator) * (2.0 / size);
        // System.out.println("Expected score " + 1.0 / denominator);
        // System.out.println("Expectancy is now " + expectancy);
        return expectancy;
    }

    public void setProvisional(int position)
    {
        provisionals[position - 1] = true;
    }

    public void unSetProvisional(int position)
    {
        provisionals[position - 1] = false;
    }

    public int getSize()
    {
        return (int) (size);
    }

    public int[] calculateRating()
    {
        int[] results = new int[(int) size];
        for (int i = 0; i < size; i++)
        {
            if (winner == i)
            {
                if (provisionals[i])
                    results[i] = (int) (Math.round((ratings[i] + 2
                            * KVALUE
                            * (1 - winExpectancy(ratings[i],
                                    getAverageRating(i + 1))))));
                else
                    results[i] = (int) (Math.round((ratings[i] + KVALUE
                            * (1 - winExpectancy(ratings[i],
                                    getAverageRating(i + 1))))));
            }
            else
            {
                if (provisionals[i])
                    results[i] = (int) (Math.round((ratings[i] + 2
                            * KVALUE
                            * (0 - winExpectancy(ratings[i],
                                    getAverageRating(i + 1))))));
                else
                    results[i] = (int) (Math.round((ratings[i] + KVALUE
                            * (0 - winExpectancy(ratings[i],
                                    getAverageRating(i + 1))))));

            }
        }

        return results;

    }

}
