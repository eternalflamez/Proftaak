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
import proftaak.Spel;

/**
 *
 * @author Sander
 */
public class SpelTest {
    Spel spel;
    Gebruiker host;
    
    public SpelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        host = new Gebruiker("Host");
        spel = new Spel("Spel 1", host, false, 1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void Constructor()
    {
        assertEquals("Spel 1", spel.getNaam());
        assertEquals(host.getRating(), spel.getSpelers().get(0).getRating());
        assertEquals(host.getNaam(), spel.getSpelers().get(0).getNaam());
        assertEquals(3, spel.getSpelers().size());
        assertEquals(0, spel.getMoeilijkheidsgraad());
    }
}
