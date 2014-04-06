/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Sander
 */
public class AI extends Speler {
    public AI(String naam, double rating, Color kleur, Point2D batLokatie, int hoek)
    {
        super(naam, rating, kleur, batLokatie, hoek);
    }
    
    public Rectangle beweeg(double snelheidX, double snelheidY, double puckY)
    {
        double y = this.getBat().getPositie().getY();
        if( y<310 && y>170){
            if(puckY > this.getBat().getPositie().getY())
            {
                this.getBat().beweegSchuin(snelheidX, snelheidY);
            }
            else if(puckY<this.getBat().getPositie().getY())
            {
                this.getBat().beweegSchuin(-snelheidX, -snelheidY);
            }
        }
        
        return getBat().getRect();
    }
}
