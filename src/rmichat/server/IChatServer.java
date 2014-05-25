/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmichat.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.beans.Observable;
import proftaak.Gebruiker;

/**
 *
 * @author Laurens
 */
public interface IChatServer extends Remote{
    void addBericht(String bericht) throws RemoteException;
    ArrayList<String> getBerichten() throws RemoteException;
 
}
