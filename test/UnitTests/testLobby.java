/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import proftaak.Lobby;

/**
 *
 * @author Stephan
 */
public class testLobby {
    
    Lobby lobby1 = new Lobby();
    public testLobby() {
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
    public void testLogin() {
    lobby1.login("Kees", "hoi");
    assertEquals("Kees", lobby1.getGebruikers().get(0).getNaam());    
    }
    
    @Test
    public void testVoegSpel() {
        
    lobby1.login("Kees", "hoi");
    lobby1.voegSpelToe("Spel1", Boolean.FALSE);
    assertEquals("Spel1", lobby1.getSpellen().get(0).getNaam());
    assertEquals("Kees", lobby1.getSpellen().get(0).getSpelers().get(0).getNaam());    
    }
    @Test
    public void testVoegBericht(){
        
    lobby1.login("Kees", "hoi");
    lobby1.stuurBericht("Bericht1");
    //get chatbox??
    }
}
