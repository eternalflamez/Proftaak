/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 *
 * @author Sander
 */
public class Human extends Speler {
    public Human(String naam, double rating, Color kleur, Point2D batLokatie, int hoek)
    {
       super(naam, rating, kleur, batLokatie, hoek);
    }
    
    public void beweeg(int richting)
    {
        // Todo, get keyinput en beweeg
        this.getBat().beweeg(richting);
        
    }
}
