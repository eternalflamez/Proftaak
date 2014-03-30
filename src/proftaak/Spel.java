/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.beans.InvalidationListener;
import java.util.Observable;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 *
 * @author Sander
 */
public class Spel extends Observable {
    private String naam;
    private int id;
    private int moeilijkheidsgraad;
    private Puck puck;
    private List<Speler> spelers;
    private Chatbox chatbox;
    private Boolean paused = true;
    private List<Color> colors;
    
    public Spel(String naam, Gebruiker host, Boolean publicGame, int id)
    {
        int richtingX = 5 + (int)(Math.random() * ((12 - 5) + 1)); // punt tussen 5 en 12
        int richtingY = 5 + (int)(Math.random() * ((12 - 5) + 1)); // punt tussen 5 en 12v

        this.colors.add(Color.BLUE);
        this.colors.add(Color.GREEN);
        this.puck = new Puck(new Point2D(50, 50), 10, new Point2D(richtingX, richtingY));
        this.naam = naam;
        this.id = id;
        this.chatbox = new Chatbox();
        
        moeilijkheidsgraad = host.getRating();
        
        // TODO: Actually give the correct point.
        Speler Player1 = new Speler(host.getNaam(), host.getScore(), Color.RED, new Point2D(0, 0));
        this.spelers.add(Player1);
        
        if(!publicGame)
        {
            for(int i = 0; i < 2; i++)
            {
                // TODO: actually give correct points
                this.spelers.add(new AI("Bot " + i, 0, colors.get(spelers.size() - 1), new Point2D(0, 0)));
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
        // TODO: Actually give correct point
        Speler speler = new Speler(g.getNaam(), g.getRating(), colors.get(spelers.size() - 1), new Point2D(0, 0));
        spelers.add(speler);
        
        // Trekt nieuw gemiddelde rating van de spelers.
        moeilijkheidsgraad = (moeilijkheidsgraad * spelers.size() + g.getRating()) / (spelers.size() + 1);
    }
    
    public void tekenVeld()
    {
        // TODO: Zet batjes en het balletje op de juiste positie, deel de juiste kleuren uit, etc.
    }
    
    public void updateVeld()
    {
        // TODO: Neem user input en beweeg de speler(s), en beweeg de bal
    }
    
    public void startSpel()
    {
        paused = false;
        // TODO: Figure out why we needed this.
    }
    
    public void eindigSpel()
    {
        notifyObservers();
        // TODO: Berekening van nieuwe scores van spelers
        
        /*
        for(Speler speler : spelers)
        {
            // Pak rating van andere spelers en tel bij elkaar op.
            int totalOthersRating = 0;
            //int points = (totalOthersRating - 2 * speler.getRating()) / 8;

            // TODO: Zorg ervoor dat Jason een array maakt bij gebruiker voor de laatste 5 scores.
            // Voeg score toe aan speler, door laatste uit de array te gooien, en de huidige vooraan te inserten.
            // En als je dan toch bezig bent, recalculate score.
            
        }
                */
    }
    
    public void updateScore(Speler s)
    {
        s.addScore();
    }
    
    public int getMoeilijkheidsgraad()
    {
        return moeilijkheidsgraad;
    }
}
