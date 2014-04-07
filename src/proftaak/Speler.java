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
    private double rating;
    private int hoek;
  
    private final Bat bat;
    
    public Speler(String naam, double rating, Color kleur, Point2D batLokatie, int Hoek)
    {
        this.rating = rating;
        this.bat = new Bat(kleur, batLokatie, Hoek);
        this.naam = naam;
        this.hoek = Hoek;
        score = 20;
    }
    
    public void addScore(){
        score++;
    }
    
    public void removeScore(){
        score--;
    }
    
    public String getNaam()
    {
        return naam;
    }
        
    public double getRating()
    {
        return rating;
    }
    
    public int getScore()
    {
        return score;
    }
    public Bat getBat()
    {
        return bat;
    }
}
