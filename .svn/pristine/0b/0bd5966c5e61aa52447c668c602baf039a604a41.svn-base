/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    Lobby lobby;
    private Spel spel = null;
    
    public LobbyGUIController(Lobby lobby)
    {
        this.lobby = lobby;
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
        Bericht b = lobby.getChatBerichten().get(lobby.getChatBerichten().size()-1);
        StringBuilder sb = new StringBuilder();
        sb.append(b.getGebruiker().getNaam()).append(": ").append(b.getBericht()).append("\n");
        taChatbox.appendText(sb.toString());
    }
}
