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
        double tx, ty, cx, cy;
        
        double circleCenterX = positie.getX() + radius;
        double circleCenterY = positie.getY() + radius;

        double rectCenterX = other.getX() + (other.getWidth() * .5);
        double rectCenterY = other.getY() + (other.getHeight() * .5);

        double rectWidth = other.getWidth();
        double rectHeight = other.getHeight();
        double angle = Math.toRadians(other.getRotate());

        if (angle == 0) { // Higher Efficiency for Rectangles with 0 rotation.
            tx = circleCenterX;
            ty = circleCenterY;

            cx = rectCenterX;
            cy = rectCenterY;
        } else {
            tx = Math.cos(angle) * circleCenterX - Math.sin(angle) * circleCenterY;
            ty = Math.cos(angle) * circleCenterY + Math.sin(angle) * circleCenterX;

            cx = Math.cos(angle) * rectCenterX - Math.sin(angle) * rectCenterY;
            cy = Math.cos(angle) * rectCenterY + Math.sin(angle) * rectCenterX;
        }

        return testRectangleToPoint(rectWidth, rectHeight, angle, rectCenterX, rectCenterY, circleCenterX, circleCenterY)
                || testCircleToSegment(tx, ty, radius, cx - rectWidth / 2, cy + rectHeight / 2, cx + rectWidth / 2, cy + rectHeight / 2)
                || testCircleToSegment(tx, ty, radius, cx + rectWidth / 2, cy + rectHeight / 2, cx + rectWidth / 2, cy - rectHeight / 2)
                || testCircleToSegment(tx, ty, radius, cx + rectWidth / 2, cy - rectHeight / 2, cx - rectWidth / 2, cy - rectHeight / 2)
                || testCircleToSegment(tx, ty, radius, cx - rectWidth / 2, cy - rectHeight / 2, cx - rectWidth / 2, cy + rectHeight / 2);
    }

    public Circle getShape() 
    {
        return shape;
    }

    private boolean testRectangleToPoint(double rectWidth, double rectHeight, double angle, double rectCenterX, double rectCenterY, double pointX, double pointY) 
    {
        if (angle == 0) {
            // Higher Efficiency for Rectangles with 0 rotation.
            return Math.abs(rectCenterX - pointX) < rectWidth / 2 && Math.abs(rectCenterY - pointY) < rectHeight / 2;
        }

        double tx = Math.cos(angle) * pointX - Math.sin(angle) * pointY;
        double ty = Math.cos(angle) * pointY + Math.sin(angle) * pointX;

        double cx = Math.cos(angle) * rectCenterX - Math.sin(angle) * rectCenterY;
        double cy = Math.cos(angle) * rectCenterY + Math.sin(angle) * rectCenterX;

        return Math.abs(cx - tx) < rectWidth / 2 && Math.abs(cy - ty) < rectHeight / 2;
    }
    
    /** Circle To Segment. */
    boolean testCircleToSegment(double circleCenterX, double circleCenterY, double circleRadius, double lineAX, double lineAY, double lineBX, double lineBY) {
        double lineSize = Math.sqrt(Math.pow(lineAX-lineBX, 2) + Math.pow(lineAY-lineBY, 2));
        double distance;

        if (lineSize == 0) {
            distance = Math.sqrt(Math.pow(circleCenterX-lineAX, 2) + Math.pow(circleCenterY-lineAY, 2));
            return distance < circleRadius;
        }

        double u = ((circleCenterX - lineAX) * (lineBX - lineAX) + (circleCenterY - lineAY) * (lineBY - lineAY)) / (lineSize * lineSize);

        if (u < 0) {
            distance = Math.sqrt(Math.pow(circleCenterX-lineAX, 2) + Math.pow(circleCenterY-lineAY, 2));
        } else if (u > 1) {
            distance = Math.sqrt(Math.pow(circleCenterX-lineBX, 2) + Math.pow(circleCenterY-lineBY, 2));
        } else {
            double ix = lineAX + u * (lineBX - lineAX);
            double iy = lineAY + u * (lineBY - lineAY);
            distance = Math.sqrt(Math.pow(circleCenterX-ix, 2) + Math.pow(circleCenterY-iy, 2));
        }

        return distance < circleRadius;
    }
}
