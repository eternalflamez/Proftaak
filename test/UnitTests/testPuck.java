/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitTests;

import javafx.geometry.Point2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import proftaak.Puck;

/**
 *
 * @author Stephan
 */
public class testPuck {
    int richtingX = 5 + (int)(Math.random() * ((12 - 5) + 1)); // punt tussen 5 en 12
    int richtingY = 5 + (int)(Math.random() * ((12 - 5) + 1)); // punt tussen 5 en 12
    Puck puck1 = new Puck(new Point2D(50, 50), 10, new Point2D(richtingX, richtingY));
    public testPuck() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
