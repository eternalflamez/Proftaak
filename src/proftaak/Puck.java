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
    private double radius;
    
    public Puck(Point2D positie, float grootte, Point2D richting)
    {
        this.radius = 20;
        this.grootte = grootte;
        this.richting = richting;
        this.positie = positie;
        
        shape = new Circle();
        
        shape.setCenterX(500);
        shape.setCenterY(500);
        shape.setRadius(radius);
    }
    
    public void beweegPuck(Point2D richting)
    {
        positie = new Point2D(positie.getX() + richting.getX(), positie.getY() + richting.getY());

        shape.setCenterX(positie.getX());
        shape.setCenterY(positie.getY());
    }
    
    public Boolean botstMet(Rectangle other)
    {
        double x = positie.getX();
        double y = positie.getY();
        
        double rectX = other.getX();
        double rectY = other.getY();
        double rectCenterX = other.getX() + other.getWidth() * .5;
        double rectCenterY = other.getY() + other.getHeight() * .5;
        
        double width = other.getWidth();
        double height = other.getHeight();
        double angle = Math.toRadians(other.getRotate());
        
        // Rotate circle's center point back
        double unrotatedCircleX = Math.cos(angle) * (x - rectCenterX) - 
                Math.sin(angle) * (y - rectCenterY) + rectCenterX;
        double unrotatedCircleY  = Math.sin(angle) * (x - rectCenterX) + 
                Math.cos(angle) * (y - rectCenterY) + rectCenterY;

        // Closest point in the rectangle to the center of circle rotated backwards(unrotated)
        double closestX, closestY;

        // Find the unrotated closest x point from center of unrotated circle
        if (unrotatedCircleX  < rectX)
            closestX = rectX;
        else if (unrotatedCircleX  > rectX + width)
            closestX = rectX + width;
        else
            closestX = unrotatedCircleX ;

        // Find the unrotated closest y point from center of unrotated circle
        if (unrotatedCircleY < rectY)
            closestY = rectY;
        else if (unrotatedCircleY > rectY + height)
            closestY = rectY + height;
        else
            closestY = unrotatedCircleY;

        // Determine collision
        boolean collision = false;

        double a = Math.abs(unrotatedCircleX - closestX);
        double b = Math.abs(unrotatedCircleY - closestY);
        
        double c = Math.sqrt((a * a) + (b * b));
        
        if (c < radius)
            collision = true; // Collision
        else
            collision = false;
        
        return collision;
    }
    
    public Circle getShape()
    {
        return shape;
    }
}
