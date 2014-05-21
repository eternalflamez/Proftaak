/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proftaak;



import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Sander
 */
public class Bat {

    private final Color kleur;
    private Point2D positie;

    private Rectangle rect;
    private double richting;

    public Bat(Color kleur, Point2D positie, double hoek) {
        
        this.kleur = kleur;
        this.positie = positie;
        this.richting = hoek;
        
        this.rect =  new Rectangle(40,10,kleur);
        
        rect.setRotate(hoek);
        rect.setX(positie.getX());
        rect.setY(positie.getY());
    }
    
    public void beweeg(double beweging)
    {
        if(positie.getX()+beweging < 315 && positie.getX()+beweging > 145)
        {
            positie = new Point2D(positie.getX() + beweging, positie.getY());
        }
        
        rect.setX(positie.getX());
        rect.setY(positie.getY());
    }
     public void beweegSchuin(double bewegingX,double bewegingY)
    {
        positie = new Point2D(positie.getX() + bewegingX, positie.getY() + bewegingY);

        rect.setX(positie.getX());
        rect.setY(positie.getY());
    }
    
    public Rectangle getRect()
    {
        return rect;
    }
    
    public Point2D getPositie()
    {
        return positie;
    }
}
