/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proftaak;

import java.awt.Point;
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
    private final Point positie;

    private Rectangle rect;
    private double richting;

    public Bat(Color kleur, Point positie) {
        
        this.kleur = kleur;
        this.positie = positie;

    }
    
    
    public void beweeg(int beweging)
    {
        positie.x *= beweging;
        positie.y *= beweging;
    }

    public void AddRect(Group group) {

        if (rect != null) {
            group.getChildren().remove(rect);
        }
        
        rect = new Rectangle(100, 25, kleur);
        rect.getTransforms().add(new Rotate(richting));

        group.getChildren().add(rect);

    }
}
