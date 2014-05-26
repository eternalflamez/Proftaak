/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmichat.server;
import proftaak.Gebruiker;
import proftaak.Spel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 *
 * @author Stephan
 */
public interface iLobby extends Remote{
    void voegSpelToe(String naam, String spelernaam, int highScore, double rating, ArrayList<Double> ratinglijst, Boolean publicGame) throws RemoteException;
    ArrayList<String> getSpellen() throws RemoteException;
}
