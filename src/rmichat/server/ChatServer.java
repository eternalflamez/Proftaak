/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmichat.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Laurens
 */
public class ChatServer extends UnicastRemoteObject implements IChatServer{
    private ArrayList<String> berichten;
    public ChatServer() throws RemoteException{
        berichten = new ArrayList<String>();
    }

    @Override
    public void addBericht(String bericht) {
        berichten.add(bericht);
        
        System.out.println("Server ontving bericht: " + bericht.toString());
        
    }
    

    @Override
    public ArrayList<String> getBerichten() {
        return berichten;
    }
    
    
}
