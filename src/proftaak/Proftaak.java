/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 * @author Sander
 */
public class Proftaak extends Application {
    
    @Override
    public void start(final Stage primaryStage) {
        // TODO: Login scherm
        // TODO: Lobby scherm
        toonLogin(primaryStage);
        toonLobby(primaryStage);
        
        Lobby lobby = new Lobby();
        Spel spel = lobby.voegSpelToe("naam", Boolean.FALSE);
        spel.startSpel(primaryStage);
        
        /*
        Button btn = new Button();
        btn.setText("Start spel");
        btn.setOnAction(new EventHandler<ActionEvent>() 
        {            
            @Override
            public void handle(ActionEvent event) {
                toonSpel(primaryStage);
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
        
    }
    
    private void toonLogin(Stage primaryStage)
    {
        
    }
    
    private void toonLobby(Stage primaryStage)
    {
        
    }
    
    private void toonSpel(Stage primaryStage)
    {
        // List of constants.
        // Wortel van 750.000 = hoogte van het scherm voor het geval dat Zijde A = Zijde B = Zijde C = 1000.
        int screenHeight = (int)Math.sqrt(750000);
        int sideWidth = 1000;
        int sideHeight = 20;
        double openingSize = 0.4;
        double wallWidth = sideWidth * ((1 - openingSize) / 2); // == sideWidth (1000) * 0.3 if openingSize == 0.4
        
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
        
        addShapesToPane(root, shapes);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void addShapesToPane(Pane root, List<Shape> shapes)
    {
        for(Shape shape : shapes)
        {
            root.getChildren().add(shape);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
