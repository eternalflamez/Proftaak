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
public class AI extends Speler {
    public AI(String naam, double rating, Color kleur, Point2D batLokatie, int hoek)
    {
        super(naam, rating, kleur, batLokatie, hoek);
    }
    
    public void beweeg(int snelheid, double puckY)
    {
        if(puckY > this.getBat().getPositie().getY())
        {
            this.getBat().beweegSchuin(snelheid);
        }
        else if(puckY<this.getBat().getPositie().getY())
        {
            this.getBat().beweegSchuin(-snelheid);
        }
    }
}
