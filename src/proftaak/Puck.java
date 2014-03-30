/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javafx.geometry.Point2D;

/**
 *
 * @author Sander
 */
public class Puck {
    private float grootte;
    private Point2D richting;
    private Point2D positie;
    private Ellipse2D shape;
    
    public Puck(Point2D positie, float grootte, Point2D richting)
    {
        this.grootte = grootte;
        this.richting = richting;
        this.positie = positie;
        
        shape = new Ellipse2D.Double(positie.getX(), positie.getY(), grootte, grootte);
    }
    
    public void beweegPuck(Point2D richting)
    {
        positie = new Point2D(positie.getX() + richting.getX(), positie.getY() + richting.getY());
        shape.setFrame(positie.getX(), positie.getY(), grootte, grootte);
    }
    
    public Boolean botstMet(Rectangle2D other)
    {
        return shape.intersects(other);
    }
    
    public Ellipse2D getShape()
    {
        return shape;
    }
}
