/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.util.ArrayList;

/**
 *
 * @author Sander
 */
public class Gebruiker {
    private final ArrayList<Double> ratingLijst;
    private String naam;
    private int highScore;
    private double rating;
    
    public Gebruiker(String naam)
    {
        this.ratingLijst = new ArrayList<>();
        this.naam = naam;
        highScore = 0;
        rating = 15;
    }
    
    public String getNaam()
    {
        return naam;
    }
    
    public int getScore()
    {
        return highScore;
    }
    
    public double getRating()
    {
        return rating;
    }
    
    public void setNewHighscore(int newScore)
    {
        if (newScore > this.highScore)
        {
            this.highScore = newScore;
        }
    }
    
    public void berekenRating(double nieuwScore){
        
        if (ratingLijst.size() > 4)
        {
            ratingLijst.remove(4);
        }
        ratingLijst.add(0, nieuwScore);
        
        rating = 0;
        
        for (int i = 0 ; i < ratingLijst.size();i++){
            
            rating += ratingLijst.get(i)* 5-i;
        }
    }
}
