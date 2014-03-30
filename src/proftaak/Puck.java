/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Sander
 */
public class Puck {
    private float grootte;
    private Point richting;
    private Point positie;
    private Ellipse2D shape;
    
    public Puck(Point positie, float grootte, Point richting)
    {
        this.grootte = grootte;
        this.richting = richting;
        this.positie = positie;
        
        shape = new Ellipse2D.Double(positie.x, positie.y, grootte, grootte);
    }
    
    public void beweegPuck(Point richting)
    {
        positie.x += richting.x;
        positie.y += richting.y;
        
        shape.setFrame(positie.x, positie.y, grootte, grootte);
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
