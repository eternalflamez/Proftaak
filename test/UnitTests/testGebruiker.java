package UnitTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import proftaak.Gebruiker;

/**
 *
 * @author Stephan
 */
public class testGebruiker {
    
        Gebruiker g;
    public testGebruiker() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        g = new Gebruiker("Kees");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testGet() {
         assertEquals("Kees", g.getNaam());
         assertEquals(0, g.getScore());
         assertEquals(15, g.getRating(), 0);
     }
     @Test
     public void testSetScore() {
         g.setNewHighscore(20);
         g.setNewHighscore(15);
         assertEquals(20, g.getScore());
     }
}
