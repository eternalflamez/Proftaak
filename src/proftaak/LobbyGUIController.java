/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proftaak;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultCaret;

/**
 * FXML Controller class
 *
 * @author Frank
 */
public class LobbyGUIController implements Initializable {

    @FXML
    private ListView<?> lvGames;
    @FXML
    private TextField tfSpelnaam;
    @FXML
    private Button btStartSpel;
    @FXML
    private ListView<?> lvGebruikersInGame;
    @FXML
    private ListView<?> lvGebruikersInLobby;
    @FXML
    private TextArea taChatbox;
    @FXML
    private TextField tfBericht;
    @FXML
    private Button btPlaatsBericht;
    Gebruiker gebruiker;
    Lobby lobby;
    private Spel spel = null;

    public LobbyGUIController(Lobby lobby, Gebruiker g) {

        this.gebruiker = g;
        this.lobby = lobby;


    }
    public LobbyGUIController(){
        taChatbox = new TextArea();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void btStartGame(Event evt) {
        spel = lobby.voegSpelToe("naam", Boolean.FALSE);
        spel.startSpel(new Stage());
    }

    public void btPlaatsBericht(Event evt) {
        lobby.stuurBericht(tfBericht.getText());
        
    }

    public void refreshChatBox() {

     {
         try {
    taChatbox.clear();
         for(String bericht : gebruiker.getBerichtenRMI())
                       
         taChatbox.appendText(bericht + "\n");
                     
         } catch (NotBoundException ex) {
         Logger.getLogger(LobbyGUIController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (MalformedURLException ex) {
         Logger.getLogger(LobbyGUIController.class.getName()).log(Level.SEVERE, null, ex);
         }
     
       
    }
    

}

    
}
