/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmichat.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import proftaak.Gebruiker;
import proftaak.LobbyGUIController;
import proftaak.Proftaak;

/**
 *
 * @author Laurens
 */
public class ChatServer extends UnicastRemoteObject implements IChatServer{
    private ArrayList<String> berichten;
    private LobbyGUIController lgc;
  
    public ChatServer() throws RemoteException{
        berichten = new ArrayList<String>();
        lgc = new LobbyGUIController();
    }

    @Override
    public void addBericht(String bericht) {
        berichten.add(bericht);
        lgc.refreshChatBox();
        
    }
    

    @Override
    public ArrayList<String> getBerichten() {
        return berichten;
    }

   




    
    
}
