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
    public Human(String naam, int rating, Color kleur, Point2D batLokatie)
    {
       super(naam, rating, kleur, batLokatie);
    }
    
    public void beweeg()
    {
        // Todo, get keyinput en beweeg
    }
}
