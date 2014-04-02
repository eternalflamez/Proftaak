/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Stephan
 */
public class Chatbox {
    private int id;
    private ArrayList<Bericht> berichtenlijst;
    
    public Chatbox()
    {
        berichtenlijst = new ArrayList<Bericht>(){};
    }
    /**
     * Een bericht object wordt hierin gemaakt.
     * @param bericht Het bericht in String
     * @param g de gebruiker
     */
    public void voegBerichtToe(String bericht, Gebruiker g)
    {
        Bericht b = new Bericht(bericht, g);
        berichtenlijst.add(b);
        
        Collections.sort(berichtenlijst);
    }
    
    /**
     * 
     * @return De arraylist met berichten.
     */
    public ArrayList<Bericht> getBerichtenlijst()
    {
        return berichtenlijst;
    }
}
