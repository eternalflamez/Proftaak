/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Sander
 */
public class Lobby implements Observer{
    private Gebruiker gebruiker;
    private Chatbox chatbox;
    private List<Gebruiker> gebruikers;
    private List<Spel> spellen;

    public Lobby()
    {
        chatbox = new Chatbox();
    }
    
    /**
     * 
     * @return Alle bestaande spellen.
     */
    public List<Spel> getSpellen()
    {
        return spellen;
    }
    
    /**
     * 
     * @return Alle gebruikers in de lobby.
     */ 
    public List<Gebruiker> getGebruikers()
    {
        return Collections.unmodifiableList(gebruikers);
    }
    
    /**
     * Voeg spel toe aan de lobby.
     * @param naam De naam van het spel.
     * @param publicGame Openbaar of privé.
     */
    public void voegSpelToe(String naam, Boolean publicGame)
    {
        Spel nieuwSpel = new Spel(naam, gebruiker, publicGame, spellen.size()+1);
        nieuwSpel.addObserver(this);
        spellen.add(nieuwSpel);
    }
    
    public List<Integer> toonHighScores()
    {
        List<Integer> highScores = new ArrayList<>();
        for(Gebruiker g : gebruikers)
        {
            highScores.add(g.getScore());
        }
        return Collections.unmodifiableList(highScores);
        //Bewaard voor de volgende iteratie
    }
    
    /**
     * 
     * @param naam
     * @param wachtwoord
     * @return true als het inloggen is geslaagd, anders false
     */
    public Boolean login(String naam, String wachtwoord)
    {
        //Bewaard voor volgende iteratie
        gebruiker = new Gebruiker(naam);
        gebruikers.add(gebruiker);
        return true;
    }
    
    public void logout()
    {
        //Bewaard voor volgende iteratie
    }
    
    public void stuurBericht(String bericht)
    {
        chatbox.voegBerichtToe(bericht, gebruiker);
    }
    
    public Boolean registreer(String naam, String wachtwoord, String email)
    {
        //Bewaard voor volgende iteratie
        return true;
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        
    }
}
