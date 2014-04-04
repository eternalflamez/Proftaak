/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proftaak;

import com.sun.javafx.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
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
        ArrayList<Line2D> lines = generateLines(other);
        
        if(lines.size() == 0)
        {
            return shape.intersects(other.getBoundsInLocal());
        }
        
        for(Line2D line : lines)
        {
            com.sun.javafx.geom.Point2D circleCenter = new com.sun.javafx.geom.Point2D((float)shape.getCenterX(), (float)shape.getCenterY());
            double distance = line.ptSegDist(circleCenter);

            if(distance < radius)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public ArrayList<Line2D> generateLines(Rectangle other)
    {
        double radians60 = Math.toRadians(other.getRotate());
        double radians30 = Math.toRadians(90 - other.getRotate());

        Line2D bottom, top, left, right;
        
        ArrayList<Line2D> lines = new ArrayList<Line2D>();
        
        if(other.getRotate() == 0)
        {
            // Efficiente collision check.
            return lines;
        }
        else if(other.getRotate() > 0)
        {
            // Maak 4 lijnen aan voor de box. Eindpunt = start + dX / dY * width
            // Per lijn object, gebruik "ptSegDist" om de afstand te kijken naar center van de cirkel
            // Line1.ptSegDist(cirkel.centerX, cirkel.centerY)
            // if(^  < radius)
            // Collision!
            com.sun.javafx.geom.Point2D topLeft = new com.sun.javafx.geom.Point2D((float)other.getX(), (float)other.getY());
            
            com.sun.javafx.geom.Point2D bottomLeft = new com.sun.javafx.geom.Point2D(
                    (float)(other.getX() - other.getHeight() * Math.sin(radians60)), 
                    (float)(other.getY() + other.getHeight() * Math.cos(radians60)));
            
            com.sun.javafx.geom.Point2D topRight = new com.sun.javafx.geom.Point2D(
                    (float)(other.getX() + other.getWidth() * Math.sin(radians30)), 
                    (float)(other.getY() + other.getWidth() * Math.cos(radians30)));
            
            com.sun.javafx.geom.Point2D bottomRight = new com.sun.javafx.geom.Point2D(
                    (float)(topRight.x - other.getHeight() * Math.cos(radians30)), 
                    (float)(topRight.y + other.getHeight() * Math.sin(radians30)));
            
            bottom = new Line2D(bottomLeft, bottomRight);
            top = new Line2D(topLeft, topRight);
            left = new Line2D(bottomLeft, topLeft);
            right = new Line2D(bottomRight, topRight);
            
//            double angle = other.getRotate();
//            double rectCenterX = (other.getWidth() / 2) + other.getX();
//            double rectCenterY = (other.getHeight() / 2) + other.getX();
//            double radians = angle * Math.PI / 180;
//            
//            // Rotate circle's center point back
//            double unrotatedCircleX = Math.cos(radians) * (shape.getCenterX() - rectCenterX) - 
//                    Math.sin(radians) * (shape.getCenterY() - rectCenterY) + rectCenterX;
//            double unrotatedCircleY  = Math.sin(radians) * (shape.getCenterX() - rectCenterX) + 
//                    Math.cos(radians) * (shape.getCenterY() - rectCenterY) + rectCenterY;
//
//            Circle unrotatedCircle = new Circle();
//            unrotatedCircle.setCenterX(unrotatedCircleX);
//            unrotatedCircle.setCenterY(unrotatedCircleY);
//            unrotatedCircle.setRadius(radius);
//            
//            other.setRotate(0);
//            Boolean collision = unrotatedCircle.intersects(other.getBoundsInLocal());
//            other.setRotate(angle);
//
//            return collision;
        }
        else
        {
            com.sun.javafx.geom.Point2D topLeft = new com.sun.javafx.geom.Point2D((float)other.getX(), (float)other.getY());
            
            com.sun.javafx.geom.Point2D bottomLeft = new com.sun.javafx.geom.Point2D(
                    (float)(other.getX() + other.getHeight() * Math.sin(radians60)), 
                    (float)(other.getY() - other.getHeight() * Math.cos(radians60)));
            
            com.sun.javafx.geom.Point2D topRight = new com.sun.javafx.geom.Point2D(
                    (float)(other.getX() - other.getWidth() * Math.sin(radians30)), 
                    (float)(other.getY() - other.getWidth() * Math.cos(radians30)));
            
            com.sun.javafx.geom.Point2D bottomRight = new com.sun.javafx.geom.Point2D(
                    (float)(topRight.x + other.getHeight() * Math.cos(radians30)), 
                    (float)(topRight.y - other.getHeight() * Math.sin(radians30)));
            
            bottom = new Line2D(bottomLeft, bottomRight);
            top = new Line2D(topLeft, topRight);
            left = new Line2D(bottomLeft, topLeft);
            right = new Line2D(bottomRight, topRight);
        }
        
        lines.add(bottom);
        lines.add(top);
        lines.add(left);
        lines.add(right);
        
        return lines;
    }
    
    public double findDistance(double fromX, double fromY, double toX, double toY){
        double a = Math.abs(fromX - toX);
        double b = Math.abs(fromY - toY);

        return Math.sqrt((a * a) + (b * b));
    }

    public Circle getShape() 
    {
        return shape;
    }
}
