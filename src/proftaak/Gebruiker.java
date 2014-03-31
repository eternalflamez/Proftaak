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
    private final ArrayList<Integer> scoreLijst;
    private String naam;
    private int highScore;
    private int rating;
    
    public Gebruiker(String naam)
    {
        this.scoreLijst = new ArrayList<>();
    }
    
    public String getNaam()
    {
        return naam;
    }
    
    public int getScore()
    {
        return highScore;
    }
    
    public int getRating()
    {
        return rating;
    }
    
    public void setNewHighscore(int newScore)
    {
        this.highScore = newScore;
    }
    
    public void berekenRating(int nieuwScore){
        
        if (scoreLijst.size() > 4)
        {
            scoreLijst.remove(4);
        }
        scoreLijst.add(0, nieuwScore);
        
        rating = 0;
        
        for (int i = 0 ; i < scoreLijst.size();i++){
            
            rating += scoreLijst.get(i)* 5-i;
        }
        
    }
}
