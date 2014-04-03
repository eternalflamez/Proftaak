/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
