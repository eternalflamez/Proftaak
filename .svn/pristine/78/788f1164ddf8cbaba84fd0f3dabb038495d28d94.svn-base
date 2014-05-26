/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;


/**
 *
 * @author Sander
 */
public class Lobby implements Observer{
    private ArrayList<String> berichten = new ArrayList<String>();
    public Gebruiker gebruiker;
    private Chatbox chatbox;
    private List<Gebruiker> gebruikers;
    private List<Spel> spellen;
    private Proftaak p;
    public String ipAdress;
    public boolean firstLobby = true;

    public Lobby() 
    {
        chatbox = new Chatbox();
        gebruikers = new ArrayList<>();
        spellen = new ArrayList<>();
    
        if(firstLobby){
     
        ipAdress = "127.0.0.1";
        gebruiker = new Gebruiker("test");
        try {
            gebruiker.startServer(ipAdress);
        } catch (NotBoundException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
        firstLobby = false;
        }
    }
    
    /**
     * 
     * @return Alle bestaande spellen.
     */
    public List<Spel> getSpellen()
    {
        return Collections.unmodifiableList(spellen);
    }
    
    /**
     * 
     * @return Alle gebruikers in de lobby.
     */ 
    public List<Gebruiker> getGebruikers()
    {
        return Collections.unmodifiableList(gebruikers);
    }
    
    public List<Bericht> getChatBerichten()
    {
        return chatbox.getBerichtenlijst();
    }
    
    /**
     * Voeg spel toe aan de lobby.
     * @param naam De naam van het spel.
     * @param publicGame Openbaar of privé.
     */
    public Spel voegSpelToe(String naam, Boolean publicGame)
    {
        Spel nieuwSpel;
        if(spellen.size() > 0)
        {
            nieuwSpel = new Spel(naam, gebruiker, publicGame, spellen.get(spellen.size()-1).getId()+1);
        }
        else
        {
            nieuwSpel = new Spel(naam, gebruiker, publicGame, 0);
        }
        nieuwSpel.addObserver(this);
        spellen.add(nieuwSpel);
        
        return nieuwSpel;
    }
    
    /**
     * 
     * @return Lijst met highscores van alle gebruikers in de lobby.
     */
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
  
        gebruikers.add(gebruiker);
        return true;
    }
    
    /**
     * Gebruiker uitloggen (nog niet geimplementeerd)
     */
    public void logout()
    {
        //Bewaard voor volgende iteratie
    }
    
    /**
     * Bericht toevoegen aan de chatbox
     * @param bericht string met inhoud van het bericht
     */
    public void stuurBericht(String bericht)
    {
        try {
            
            gebruiker.addBericht(bericht);
          
        } catch (RemoteException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  chatbox.voegBerichtToe(bericht, gebruiker);
    }
    
    /**
     * Nieuwe gebruiker registreren (nog niet geimplementeerd)
     * @param naam
     * @param wachtwoord
     * @param email
     * @return 
     */
    public Boolean registreer(String naam, String wachtwoord, String email)
    {
        //Bewaard voor volgende iteratie
        return true;
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        for(int i = 0; i < spellen.size(); i++)
        {
            Spel s = spellen.get(i);
            if(s.getId() == ((Spel)o).getId())
            {
                spellen.remove(i);
            }
        }
    }

      public ArrayList<String> getBerichten() throws NotBoundException, MalformedURLException {
        berichten = gebruiker.getBerichtenRMI();
        return berichten;
    }

    public void showBerichten() throws NotBoundException, MalformedURLException {
     
    }
}
