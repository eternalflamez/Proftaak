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
    private List<Bericht> berichtenlijst;
    
    public Chatbox()
    {
        berichtenlijst = new ArrayList<Bericht>(){};
    }
    
    public void voegBerichtToe(String bericht, Gebruiker g)
    {
        Bericht b = new Bericht(bericht, g);
        berichtenlijst.add(b);
    }
    public List<Bericht> getBerichtenlijst()
    {
        return berichtenlijst;
    }
}
