/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sander
 */
public class Spel {
    private String naam;
    private int id;
    private int moeilijkheidsgraad;
    private Puck puck;
    private List<Speler> spelers;
    private Chatbox chatbox;
    
    public Spel(String naam, Gebruiker host, Boolean publicGame, int id)
    {
        this.naam = naam;
        this.id = id;
        
        moeilijkheidsgraad = host.getRating();
        
        Speler Player1 = new Speler(host.getNaam(), host.getScore());
        this.spelers.add(Player1);
        
        if(!publicGame)
        {
            for(int i = 0; i < 2; i++)
            {
                this.spelers.add(new AI("Bot " + i, 0));
            }
        }
    }
    
    public String getNaam()
    {
        return naam;
    }
    
    public List<Speler> getSpelers()
    {
        return Collections.unmodifiableList(spelers);
    }
    
    public int getId()
    {
        return id;
    }
    
    public void voegGebruikerToe(Gebruiker g)
    {
        Speler speler = new Speler(g.getNaam(), g.getScore());
        spelers.add(speler);
    }
    
    public void tekenVeld()
    {
        
    }
    
    public void updateVeld()
    {
        
    }
    
    public void startSpel()
    {
        
    }
    
    public void eindigSpel()
    {
        
    }
    
    public void UpdateScore(Speler s)
    {
        s.addScore();
    }
    
    public int getMoeilijkheidsgraad()
    {
        return -1;
    }
}
