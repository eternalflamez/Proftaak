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
import proftaak.*;

/**
 *
 * @author Frank
 */
public class testLobby {
    
    Lobby testLobby = new Lobby();
    
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
    // @Test
    // public void hello() {}
    
    @Test
    public void testVoegSpelToe()
    {
        testLobby.voegSpelToe("TestSpel1", true, 1);
        testLobby.voegSpelToe("TestSpel2", true, 2);
        testLobby.voegSpelToe("TestSpel3", true, 3);
        testLobby.getSpellen().remove(1);
        assertTrue(testLobby.getSpellen().size() == 2);
    }
}
