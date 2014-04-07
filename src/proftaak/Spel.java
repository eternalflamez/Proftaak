/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;



import com.sun.javafx.geom.Line2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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
    //private List<Speler> spelers;
    private List<Gebruiker> gebruikers;
    private Chatbox chatbox;
    private List<Color> colors;
    private AnimationTimer timer;
    private Point2D direction;
    private int richting; // wordt gebruikt bij het bewegen van de bat.
    private List<Human> humanSpelers;
    private List<AI> aiSpelers;
    
    private Speler lastTouchedPuck;
     // List of constants.
    // Wortel van 750.000 = hoogte van het scherm voor het geval dat Zijde A = Zijde B = Zijde C = 1000.
    int screenHeight = (int)Math.sqrt(187500); // a2 + b2 = c2, 62500 + ? = 250000
    int sideWidth = 500;
    int sideHeight = 10;
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
        lastTouchedPuck = new Speler(naam, wallWidth, Color.AQUA, new Point2D(0,0), id);
        
        colors = new ArrayList();
        //spelers = new ArrayList();
        gebruikers = new ArrayList();
        humanSpelers = new ArrayList();
        aiSpelers = new ArrayList();
        
        resetPuck();
        this.colors.add(Color.BLUE);
        this.colors.add(Color.GREEN);
        this.naam = naam;
        this.id = id;
        this.chatbox = new Chatbox();
        moeilijkheidsgraad = host.getRating();
        
        Human Player1 = new Human(host.getNaam(), host.getRating(), Color.RED, new Point2D(250, 405), 0);
        this.humanSpelers.add(Player1);
        gebruikers.add(host);
        
        if(!publicGame)
        {
              // TODO: Test point position
           this.aiSpelers.add(new AI("Bot " + 0, 0, /**colors.get(aiSpelers.size())**/ Color.GREEN, new Point2D(105, 250), -60));
           this.aiSpelers.add(new AI("Bot " + 1, 0,Color.BLUE, new Point2D(340, 225), -120));
        }
    }
    
    private void resetPuck()
    {
        int dX = 0, dY = 0;
        
        while(dX > -2 && dX < 2)
        {
            dX = getRandom(-4, 4);
        }
        
        while(dY > -2 && dY < 2)
        {
            dY = getRandom(-4, 4);
        }
        
        this.direction = new Point2D(dX, dY);
        this.puck = new Puck(new Point2D(250, 250), 10, direction);
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
    public List<Human> getSpelers()
    {
        return Collections.unmodifiableList(humanSpelers);
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
        Human speler = new Human(g.getNaam(), g.getRating(), colors.get(humanSpelers.size() - 1), new Point2D(0, 0), 60);
        humanSpelers.add(speler);
        gebruikers.add(g);
        
        // Trekt nieuw gemiddelde rating van de spelers.
        moeilijkheidsgraad = (moeilijkheidsgraad * humanSpelers.size() + g.getRating()) / (humanSpelers.size() + 1);
    }
    
    /**
     * Updates the objects in the field to their new positions.
     */
    private void updateVeld(Stage stage)
    {
         ArrayList<Line2D> lines = new ArrayList<Line2D>();
        Stage s = stage;
        List<Node> nodes = s.getScene().getRoot().getChildrenUnmodifiable();
        
        Pane root = new Pane();
        Scene scene = new Scene(root, sideWidth, screenHeight);
        setKeyEventHandler(scene);
        
        this.puck.beweegPuck(direction);
        Circle puck = this.puck.getShape();
        root.getChildren().add(puck);
        
        // Waarom we dan een for loop gebruiken als we daarna toch ALTIJD bots toevoegen?
        for(Human h: humanSpelers)
        {
            h.beweeg(richting * 2);
            Rectangle bat = h.getBat().getRect();
            root.getChildren().add(bat);
        }
        
        Label scoreSpeler = new Label();
        scoreSpeler.setText(String.valueOf(humanSpelers.get(0).getScore()));
        scoreSpeler.setLayoutX(10);
        scoreSpeler.setLayoutY(10);
        scoreSpeler.setTextFill(Color.RED);
        root.getChildren().add(scoreSpeler);
        
        // Ai bewegen en toevoegen.
        Rectangle aiBat1 = aiSpelers.get(0).beweeg(-1, Math.sqrt(3), puck.getCenterY());
        Rectangle aiBat2 = aiSpelers.get(1).beweeg(1, Math.sqrt(3), puck.getCenterY());
        
        root.getChildren().add(aiBat1);
        root.getChildren().add(aiBat2);

        Label scoreAI1 = new Label();
        scoreAI1.setText(String.valueOf(aiSpelers.get(0).getScore()));
        scoreAI1.setLayoutX(10);
        scoreAI1.setLayoutY(25);
        scoreAI1.setTextFill(Color.GREEN);
        root.getChildren().add(scoreAI1);
        
        Label scoreAI2 = new Label();
        scoreAI2.setText(String.valueOf(aiSpelers.get(1).getScore()));
        scoreAI2.setLayoutX(10);
        scoreAI2.setLayoutY(40);
        scoreAI2.setTextFill(Color.BLUE);
        root.getChildren().add(scoreAI2);
        
        Rectangle bottom = new Rectangle(wallWidth, screenHeight - sideHeight, sideWidth * openingSize, sideHeight);
        Rectangle left = new Rectangle(20, screenHeight / 2, sideWidth * openingSize, sideHeight);
        left.setRotate(-60);
        Rectangle right = new Rectangle(280, (screenHeight) / 2, sideWidth * openingSize, sideHeight);
        right.setRotate(60);
        
        if(this.puck.botstMet(bottom))
        {
            updateScore(lastTouchedPuck, humanSpelers.get(0));
            resetPuck();
        }
        else if(this.puck.botstMet(left))
        {
            updateScore(lastTouchedPuck, aiSpelers.get(0));
            resetPuck();
            // updateScore(lastTouchedPuck, humanSpelers.get(1));
        }
        else if(this.puck.botstMet(right))
        {
            updateScore(lastTouchedPuck, aiSpelers.get(1));
            resetPuck();
            // updateScore(lastTouchedPuck, humanSpelers.get(2));
        }
        
        // Collisies met de bats hier omdat.
        if(this.puck.botstMet(aiBat1))
        {
            difficultBounce(aiBat1);
        }
        else if(this.puck.botstMet(aiBat2))
        {
            difficultBounce(aiBat2);
        }
        else if(this.puck.botstMet(humanSpelers.get(0).getBat().getRect()))
        {
            difficultBounce(humanSpelers.get(0).getBat().getRect());
        }
        
        for (int i = nodes.size() - 1; i >= 0 ; i--) 
        {
            Node original = nodes.get(i);
            
            if(original.getClass().equals(new Rectangle().getClass()))
            {
                Rectangle origiTangle = (Rectangle)original;
                
                Rectangle shadowCopy = new Rectangle(origiTangle.getX(), origiTangle.getY(), origiTangle.getWidth(), origiTangle.getHeight());
                shadowCopy.setRotate(origiTangle.getRotate());
                
                root.getChildren().add(shadowCopy);
            
                
               // lines.addAll(this.puck.generateLines(shadowCopy));
                Rectangle shape = shadowCopy;
                
                if(this.puck.botstMet(shape))
                {
                    System.out.println("Botsing!");
                    
                    if(shape.getRotate() == 0)
                    {
                        direction = new Point2D(direction.getX(), direction.getY() * -1);
                        puck.setCenterY(shape.getLayoutY() - shape.getHeight() / 2);
                    }
                    else
                    {
                        difficultBounce(shape);
                    }
                }
            }
        }
        
        for(Line2D l : lines)
        {
            Line line = new Line(l.x1, l.y1, l.x2, l.y2);
//            // Tekent de collisie lijnen voor debug.
            //root.getChildren().add(line);
        }
        
        s.setScene(scene);
        s.show();
    }
    
    private void difficultBounce(Rectangle shape)
    {
        // Pak de angle waarin hij terug kaatst aan de rechterkant
        double angle = Math.atan(direction.getX() / direction.getY()) + 30 + 120;

        if(shape.getRotate() < 0)
        {
            // En aan de linkerkant
            angle = 30 - 60 + Math.toDegrees(Math.atan(direction.getX() / direction.getY()));
        }

        angle = Math.toRadians(angle);

        // double oldRatio = direction.getX() / direction.getY();
        double newXRatio = direction.getX() / Math.cos(Math.atan(direction.getX() / direction.getY()));
        double newYRatio = direction.getY() / Math.cos(Math.atan(direction.getX() / direction.getY()));
        // double newY = direction.getX() / newRatio;

        double newX = Math.cos(angle) * 3;
        double newY = Math.sin(angle) * 3;
        direction = new Point2D(newX, newY);
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
        
        Rectangle leftBottom = new Rectangle(-40, Math.sqrt(187500) * 0.85, wallWidth, sideHeight);
        leftBottom.setRotate(-60);
        Rectangle leftTop = new Rectangle(135, Math.sqrt(187500) * 0.15, wallWidth, sideHeight);
        leftTop.setRotate(-60);
        
        Rectangle rightBottom = new Rectangle(screenHeight - 40, Math.sqrt(187500) * 0.85, wallWidth, sideHeight);
        rightBottom.setRotate(60);
        Rectangle rightTop = new Rectangle(215, Math.sqrt(187500) * 0.15, wallWidth, sideHeight);
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
        
        for(Speler speler : humanSpelers)
        {
            totalRating += speler.getRating();
        }
        
        for(Gebruiker gebruiker : gebruikers)
        {
            // Pak rating van andere spelers en telt bij elkaar op.
            double totalOthersRating = totalRating - gebruiker.getRating();
            double points = (totalOthersRating - 2 * gebruiker.getRating()) / 8;
            int score = humanSpelers.get(counter).getScore();
            
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
        
        loser.removeScore();
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
    
    private void setKeyEventHandler(final Scene keyScene)
    {
        final EventHandler<KeyEvent> keyPressedEventHandler =
            new EventHandler<KeyEvent>() {
                @Override
                public void handle(final KeyEvent keyEvent) {
                    double batXPos = humanSpelers.get(0).getBat().getPositie().getX();
                    if (keyEvent.getCode() == KeyCode.LEFT) {
                        setRichting(-1);
                    }
                    else if(keyEvent.getCode() == KeyCode.RIGHT) 
                    {
                        setRichting(1);
                    }
                    else
                    {
                        setRichting(0);
                    }
                }
           };
        
        final EventHandler<KeyEvent> keyReleasedEventHandler =
            new EventHandler<KeyEvent>() {
                @Override
                public void handle(final KeyEvent keyEvent) {
                    setRichting(0);
                }
           };
        
        keyScene.setOnKeyPressed(keyPressedEventHandler);
        keyScene.setOnKeyReleased(keyReleasedEventHandler);
    }
}
