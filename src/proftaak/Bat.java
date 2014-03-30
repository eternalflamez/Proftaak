/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;


import java.awt.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Sander
 */
public class Bat {
    private Color kleur;
    private Point positie;
    
    private double richting;
    
    public Bat(String kleur, Point positie)
    {
        
    }
    
    public void Draw(Canvas canvas){
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Rectangle Rect = new Rectangle(100,25,kleur);
        Rect.getTransforms().add(new Rotate(richting));
        
        
                
        
        
        
        
        
        
    }
}
