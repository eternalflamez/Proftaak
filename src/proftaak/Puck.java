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
public class Puck 
{
    private Point2D richting;
    private Point2D positie;
    private Circle shape;
    private double radius;

    public Puck(Point2D positie, float grootte, Point2D richting) 
    {
        this.radius = grootte;
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
        double tx, ty, cx, cy;
        
        double circleCenterX = positie.getX() + radius;
        double circleCenterY = positie.getY() + radius;

        double rectCenterX = other.getX() + (other.getWidth() * .5);
        double rectCenterY = other.getY() + (other.getHeight() * .5);

        double rectWidth = other.getWidth();
        double rectHeight = other.getHeight();
        double angle = Math.toRadians(other.getRotate());
        
        return false;
    }

    public Circle getShape() 
    {
        return shape;
    }
}
