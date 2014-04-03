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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
        //toonLobby(primaryStage);
        
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
    
    private void toonLogin(final Stage primaryStage)
    {
        final Lobby lobby = new Lobby();
        final TextField tfLoginName = new TextField();
        final TextField tfLoginPassword = new TextField();
        final Label lbLoginError = new Label();
        Button btLogin = new Button();
        StackPane root = new StackPane();
        
        tfLoginName.setTranslateY(-50);
        tfLoginName.setTranslateX(-50);
        tfLoginName.setMaxWidth(150);
        root.getChildren().add(tfLoginName);
        
        tfLoginPassword.setTranslateY(0);
        tfLoginPassword.setTranslateX(-50);
        tfLoginPassword.setMaxWidth(150);
        root.getChildren().add(tfLoginPassword);
        
        btLogin.setTranslateY(50);
        btLogin.setTranslateX(0);
        btLogin.setText("Login");
        root.getChildren().add(btLogin);
        
        lbLoginError.setTranslateX(-50);
        lbLoginError.setTranslateY(75);
        lbLoginError.setTextFill(Color.RED);
        lbLoginError.setText("Ongeldige login gegevens");
        lbLoginError.setVisible(false);
        root.getChildren().add(lbLoginError);
        
        btLogin.setOnAction(new EventHandler<ActionEvent>() 
        {            
            @Override
            public void handle(ActionEvent event) {
                if(tfLoginName.getText().trim().length() > 0 && tfLoginPassword.getText().trim().length() > 0)
                {
                    primaryStage.close();
                    try {
                        toonLobby(primaryStage, lobby);
                    } catch (Exception ex) {
                        
                    }
                }
                else
                {
                    lbLoginError.setVisible(true);
                }
            }
        });

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void toonLobby(Stage primaryStage, Lobby lobby) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("LobbyGUI.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        //Spel spel = lobby.voegSpelToe("naam", Boolean.FALSE);
        //spel.startSpel(primaryStage);
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
