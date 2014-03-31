/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;



import java.util.Collections;
import java.util.List;
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
    private List<Gebruiker> gebruikers;
    private Chatbox chatbox;
    private List<Color> colors;
    
    public Spel(String naam, Gebruiker host, Boolean publicGame, int id)
    {
        int richtingX = 5 + (int)(Math.random() * ((12 - 5) + 1)); // punt tussen 5 en 12
        int richtingY = 5 + (int)(Math.random() * ((12 - 5) + 1)); // punt tussen 5 en 12

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
        gebruikers.add(host);
        
        if(!publicGame)
        {
            for(int i = 0; i < 2; i++)
            {
                // TODO: actually give correct points
                this.spelers.add(new AI("Bot " + i, 0, colors.get(spelers.size() - 1), new Point2D(0, 0)));
            }
        }
    }
    
    /**
     * Gets the name of the game.
     * @return The name of the game.
     */
    public String getNaam()
    {
        return naam;
    }
    
    /**
     * Gets the players of the game.
     * @return An unmodifiable list of players of this game.
     */
    public List<Speler> getSpelers()
    {
        return Collections.unmodifiableList(spelers);
    }
    
    /**
     * Gets the id of the game.
     * @return The id of this game.
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Adds a user to the game. (TODO: Remove a user from the game?)
     * @param g The user to add to the game.
     */
    public void voegGebruikerToe(Gebruiker g)
    {
        // TODO: Actually give correct point
        Speler speler = new Speler(g.getNaam(), g.getRating(), colors.get(spelers.size() - 1), new Point2D(0, 0));
        spelers.add(speler);
        gebruikers.add(g);
        
        // Trekt nieuw gemiddelde rating van de spelers.
        moeilijkheidsgraad = (moeilijkheidsgraad * spelers.size() + g.getRating()) / (spelers.size() + 1);
    }
    
    /**
     * Draws the field, including bats, the ball, and the walls at starting position.
     */
    public void tekenVeld()
    {
        // TODO: Zet batjes en het balletje op de juiste positie, deel de juiste kleuren uit, etc.
    }
    
    /**
     * Updates the objects in the field to their new positions.
     */
    public void updateVeld()
    {
        // TODO: Neem user input en beweeg de speler(s), en beweeg de bal
    }
    
    /**
     * Starts the game.
     */
    public void startSpel()
    {
        // TODO: Timer voor updateVeld
    }
    
    /**
     * Ends the game. Also notifies observers that this has been done.
     */
    public void eindigSpel()
    {
        int totalRating = 0;
        int counter = 0;
        
        for(Speler speler : spelers)
        {
            totalRating += speler.getRating();
        }
        
        for(Gebruiker gebruiker : gebruikers)
        {
            // Pak rating van andere spelers en telt bij elkaar op.
            int totalOthersRating = totalRating - gebruiker.getRating();
            int points = (totalOthersRating - 2 * gebruiker.getRating()) / 8;

            gebruiker.berekenRating(points);
            
            // Kijkt of huidige score meer is dan de highscore.
            int score = spelers.get(counter).getScore();
            
            if(score > gebruiker.getScore())
            {
                gebruiker.setNewHighscore(score);
            }
        }
        
        notifyObservers();
    }
    
    /**
     * Adds a point to a certain player.
     * @param s The player that scored.
     */
    public void updateScore(Speler s)
    {
        s.addScore();
    }
    
    /**
     * Gets the difficulty level of the game, based on the average rating of the players.
     * @return The average rating of all players in the game.
     */
    public int getMoeilijkheidsgraad()
    {
        return moeilijkheidsgraad;
    }
}
