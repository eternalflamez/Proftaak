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
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Sander
 */
public class Spel extends Observable {
    private String naam;
    private int id;
    private double moeilijkheidsgraad;
    private Puck puck;
    private List<Speler> spelers;
    private List<Gebruiker> gebruikers;
    private Chatbox chatbox;
    private List<Color> colors;
    private AnimationTimer timer;
    private Point2D direction;
    
    private int richting; // wordt gebruikt bij het bewegen van de bat.
    private List<Human> humanSpelers;
    
     // List of constants.
    // Wortel van 750.000 = hoogte van het scherm voor het geval dat Zijde A = Zijde B = Zijde C = 1000.
    int screenHeight = (int)Math.sqrt(750000);
    int sideWidth = 1000;
    int sideHeight = 20;
    double openingSize = 0.4;
    double wallWidth = sideWidth * ((1 - openingSize) / 2); // == sideWidth (1000) * 0.3 if openingSize == 0.4
    
    /**
     * Creates a new spel object.
     * @param naam The name of the game.
     * @param host The person that hosts the game.
     * @param publicGame If this is false, we are playing with bots.
     * @param id The id of the game.
     */
    public Spel(String naam, Gebruiker host, Boolean publicGame, int id)
    {
        colors = new ArrayList();
        spelers = new ArrayList();
        gebruikers = new ArrayList();
        humanSpelers = new ArrayList();
        
        int richtingX = 5 + (int)(Math.random() * ((12 - 5) + 1)); // punt tussen 5 en 12
        int richtingY = 5 + (int)(Math.random() * ((12 - 5) + 1)); // punt tussen 5 en 12

        this.direction = new Point2D(getRandom(-10, 10), getRandom(-10, 10));
        this.colors.add(Color.BLUE);
        this.colors.add(Color.GREEN);
        this.puck = new Puck(new Point2D(500, 500), 40, new Point2D(richtingX, richtingY));
        this.naam = naam;
        this.id = id;
        this.chatbox = new Chatbox();
        
        moeilijkheidsgraad = host.getRating();
        
        Human Player1 = new Human(host.getNaam(), host.getRating(), Color.RED, new Point2D(500, 950), 0);
        this.humanSpelers.add(Player1);
        gebruikers.add(host);
        
        if(!publicGame)
        {
                // TODO: Test point position
           this.spelers.add(new AI("Bot " + 0, 0, colors.get(spelers.size()), new Point2D(280, 500), -60));
           this.spelers.add(new AI("Bot " + 1, 0, colors.get(spelers.size()), new Point2D(720, 450), 60));
     
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
        // TODO: Actually give correct point and angle
        Speler speler = new Speler(g.getNaam(), g.getRating(), colors.get(spelers.size() - 1), new Point2D(0, 0), 60);
        spelers.add(speler);
        gebruikers.add(g);
        
        // Trekt nieuw gemiddelde rating van de spelers.
        moeilijkheidsgraad = (moeilijkheidsgraad * spelers.size() + g.getRating()) / (spelers.size() + 1);
    }
    
    /**
     * Updates the objects in the field to their new positions.
     */
    public void updateVeld(Stage stage)
    {
        // TODO: Neem user input en beweeg de speler(s), en beweeg de bal
        Pane root = new Pane();
        Scene scene = new Scene(root, sideWidth, screenHeight);
        
        this.puck.beweegPuck(direction);
        
        Circle puck = this.puck.getShape();
        root.getChildren().add(puck);
        
        
        for(Human h: humanSpelers)
        {
            h.beweeg(richting);
            Rectangle bat = h.getBat().getRect();
            root.getChildren().add(bat);
        }
//         Bat's tekenen van alle spelers
        for(Speler s: spelers)
        {
            Rectangle bat = s.getBat().getRect();
            root.getChildren().add(bat);
        }
        
        
        List<Node> nodes = stage.getScene().getRoot().getChildrenUnmodifiable();
        
        for (int i = nodes.size() - 1; i >= 0 ; i--) 
        {
            Node shapeCopy = nodes.get(i);
            root.getChildren().add(shapeCopy);
            
            if(shapeCopy.getClass().equals(new Rectangle().getClass()))
            {
                if(this.puck.botstMet((Rectangle)shapeCopy))
                {
                    System.out.println("Bosting!");
                    direction = new Point2D(direction.getX() * -1, direction.getY() * -1);
                }
            }
        }
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Starts the game.
     */
    public void startSpel(final Stage stage)
    {
        List<Shape> shapes = new ArrayList();
        
        Pane root = new Pane();
        Scene scene = new Scene(root, sideWidth, screenHeight);
        
        Rectangle bottomLeft = new Rectangle(0, screenHeight - sideHeight, wallWidth, sideHeight);
        Rectangle bottomRight = new Rectangle(sideWidth - wallWidth, screenHeight - sideHeight, wallWidth, sideHeight);
        
        Rectangle leftBottom = new Rectangle(-80, Math.sqrt(750000) * 0.85, wallWidth, sideHeight);
        leftBottom.setRotate(-60);
        Rectangle leftTop = new Rectangle(270, Math.sqrt(750000) * 0.15, wallWidth, sideHeight);
        leftTop.setRotate(-60);
        
        Rectangle rightBottom = new Rectangle(screenHeight - 80, Math.sqrt(750000) * 0.85, wallWidth, sideHeight);
        rightBottom.setRotate(60);
        Rectangle rightTop = new Rectangle(430, Math.sqrt(750000) * 0.15, wallWidth, sideHeight);
        rightTop.setRotate(60);
        
        shapes.add(bottomLeft);
        shapes.add(bottomRight);
        shapes.add(leftBottom);
        shapes.add(leftTop);
        shapes.add(rightBottom);
        shapes.add(rightTop);
        
        for(Shape shape : shapes)
        {
            root.getChildren().add(shape);
        }
        
        stage.setScene(scene);
        stage.show();
        
        // This is needed to stop the animation timer from re-opening the application.
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                timer.stop();
                eindigSpel();
            }
        });
        
        timer = new AnimationTimer() {
            private long prevUpdate;

            @Override public void handle(long now) {
                long lag = now - prevUpdate;
                if (lag >= 1000000000 / 60) {
                    updateVeld(stage);
                    prevUpdate = now;
                }
            }
            @Override public void start() {
                prevUpdate = System.nanoTime();
                super.start();
            }
        };

        timer.start();
        
         
        /* Complete rectangle
        Rectangle bottom = new Rectangle(0, Math.sqrt(750000) - 20, 1000, 20);
        Rectangle left = new Rectangle(-250, (Math.sqrt(750000)) / 2, 1000, 20);
        left.setRotate(-60);
        Rectangle right = new Rectangle(250, (Math.sqrt(750000)) / 2, 1000, 20);
        right.setRotate(60);
        
        Pane root = new Pane();
        root.getChildren().add(bottom);
        root.getChildren().add(left);
        root.getChildren().add(right);
        */
    }
    
    private int getRandom(int min, int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
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
            double totalOthersRating = totalRating - gebruiker.getRating();
            double points = (totalOthersRating - 2 * gebruiker.getRating()) / 8;
            int score = spelers.get(counter).getScore();
            
            gebruiker.berekenRating(points + score);
            
            // Kijkt of huidige score meer is dan de highscore.
            if(score > gebruiker.getScore())
            {
                gebruiker.setNewHighscore(score);
            }
        }
        
        notifyObservers();
    }
    
    /**
     * Adds a point to a certain player.
     * @param winner The player that scored.
     * @param loser The player that was scored at.
     */
    public void updateScore(Speler winner, Speler loser)
    {
        // TODO: een removeScore toevoegen.
        winner.addScore();
        
        // loser.removeScore();
    }
    
    /**
     * Gets the difficulty level of the game, based on the average rating of the players.
     * @return The average rating of all players in the game.
     */
    public double getMoeilijkheidsgraad()
    {
        return moeilijkheidsgraad;
    }
    
    
    public void setRichting(int richting)
    {
        if(richting == 0 || richting == -1 ||richting == 1)
        {
            this.richting = richting;
        }
    }
}
