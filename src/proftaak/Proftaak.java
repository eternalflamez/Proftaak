/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
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
        lobby.login("asd", "asad");
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
