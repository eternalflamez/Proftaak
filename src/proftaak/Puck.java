/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Sander
 */
public class Puck {
    private float grootte;
    private Point2D richting;
    private Point2D positie;
    private Circle shape;
    
    public Puck(Point2D positie, float grootte, Point2D richting)
    {
        this.grootte = grootte;
        this.richting = richting;
        this.positie = positie;
        
        shape = new Circle();
        
        shape.setCenterX(500);
        shape.setCenterY(500);
        shape.setRadius(20);
    }
    
    public void beweegPuck(Point2D richting)
    {
        positie = new Point2D(positie.getX() + richting.getX(), positie.getY() + richting.getY());

        shape.setCenterX(positie.getX());
        shape.setCenterY(positie.getY());
    }
    
    public Boolean botstMet(Rectangle other)
    {
        return shape.intersects(other.xProperty().doubleValue(), other.yProperty().doubleValue(), other.widthProperty().doubleValue(), other.heightProperty().doubleValue());
    }
    
    public Circle getShape()
    {
        return shape;
    }
}
