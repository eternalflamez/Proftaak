/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmichat.server;
import proftaak.Gebruiker;
import proftaak.Spel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Stephan
 */
public class LobbyServer extends UnicastRemoteObject implements iLobby{
    private ArrayList<Spel> spellen;
    public LobbyServer() throws RemoteException{
        spellen = new ArrayList<Spel>();
    }

    @Override
    public void voegSpelToe(String naam, String spelernaam, int highScore, double rating, ArrayList<Double> ratinglijst, Boolean publicGame) throws RemoteException {
        Spel nieuwSpel;
        Gebruiker gebruiker = new Gebruiker(spelernaam, highScore, rating, ratinglijst);
        if(spellen.size() > 0)
        {
            nieuwSpel = new Spel(naam, gebruiker, publicGame, spellen.get(spellen.size()-1).getId()+1);
        }
        else
        {
            nieuwSpel = new Spel(naam, gebruiker, publicGame, 0);
        }
        //nieuwSpel.addObserver(this);
        spellen.add(nieuwSpel);
        System.out.println("Server spelnaam: " + nieuwSpel.getNaam().toString());
    }

    @Override
    public ArrayList<String> getSpellen() throws RemoteException {
        ArrayList rmiSpellen = new ArrayList<String>();
        for(Spel s: spellen)
        {
            rmiSpellen.add(s.toString());
        }
        return rmiSpellen;
    }
    
}
