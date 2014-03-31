/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;



import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 *
 * @author Sander
 */
public class Speler {
    private String naam;
    private int score;
    private int rating;
  
    private final Bat bat;
    
    public Speler(String naam, int rating, Color kleur, Point2D batLokatie)
    {
        this.rating = rating;
        this.bat = new Bat(kleur, batLokatie);
        
    }
    
    public void addScore(){
        score++;
    }
        
    public int getRating()
    {
        return rating;
    }
    
    public int getScore()
    {
        return score;
    }
}
