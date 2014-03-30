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
import proftaak.Bericht;
import proftaak.Chatbox;
import proftaak.Gebruiker;

/**
 *
 * @author Stephan
 */
public class testChatbox {
    
        Gebruiker g1 = new Gebruiker("g1");
        Gebruiker g2 = new Gebruiker("g2");
        Chatbox chatbox = new Chatbox();
        Bericht b = new Bericht("bericht1", g1);
        
    public testChatbox() {
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
    @Test
    public void testVoegBerichtToe() 
    {
        chatbox.voegBerichtToe("bericht1", g1);
        chatbox.voegBerichtToe("bericht2", g2);
        chatbox.voegBerichtToe("bericht3", g2);
        
        assertEquals("Niet alle berichten toegevoegd.", chatbox.getBerichtenlijst().size(), 2);
        
        int testInt = 0;
        for(Bericht bericht: chatbox.getBerichtenlijst())
        {
            if(bericht.getBericht() == "bericht1" && bericht.getGebruiker()==g1)
            {
                testInt++;
            }
        }
        assertEquals(testInt, 1);
    }
    
    @Test
    public void testSort()
    {
    }
}
