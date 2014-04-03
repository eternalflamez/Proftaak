/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitTests;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import proftaak.Speler;

/**
 *
 * @author Stephan
 */
public class testSpeler {
    Speler speler1;
    Speler speler2;
    
    public testSpeler() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        speler1 = new Speler("Kees", 20.1, Color.RED, new Point2D(0, 0), 60);
        
        speler2 = new Speler("Piet", 1323.6, Color.BLUE, new Point2D(0, 0), -60);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testAddGetScore() {
     speler1.addScore();
     assertEquals("Score klopt niet.", speler1.getScore(), 1);
     speler1.addScore();
     speler1.addScore();
     speler1.addScore();
     
     assertEquals("Score klopt niet.", speler1.getScore(), 4);
     }
     
     
     @Test
     public void testGetNaam() {
         assertEquals("naam klopt niet.","Kees", speler1.getNaam());
         assertEquals("naam klopt niet.","Piet", speler2.getNaam());
     }
     
     @Test
     public void testGetRating() {
         assertEquals("Rating moet 20.1 zijn.",20, speler1.getRating(),1);
         assertEquals("Rating moet 1323.6 zijn.",1323, speler2.getRating(),6);
     }
         
}
