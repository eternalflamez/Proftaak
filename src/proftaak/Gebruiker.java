/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proftaak;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.scene.control.TextArea;
import rmichat.server.ChatServer;
import rmichat.server.IChatServer;
import rmichat.server.Server;

/**
 *
 * @author Sander
 */
public class Gebruiker implements Observer {

    private final ArrayList<Double> ratingLijst;
    private String Name;
    private int highScore;
    private double rating;
    private ArrayList<String> berichtenRMI;
    private ArrayList<String> berichten = new ArrayList<String>();
    private IChatServer ics;

    public Gebruiker(String naam) {

        this.ratingLijst = new ArrayList<>();
        this.Name = naam;
        highScore = 0;
        rating = 15;
        ratingLijst.add(rating);

    }
    public Gebruiker (String naam, int highScore, double rating, ArrayList<Double> ratinglijst)
    {
        this.Name = naam;
        this.highScore = highScore;
        this.rating = rating;
        this.ratingLijst=ratinglijst;
    }
    

    public String getNaam() {
        return Name;
    }

    public int getScore() {
        return highScore;
    }

    public double getRating() {
        return rating;
    }
       public ArrayList<Double> getRatingLijst()
    {
        return ratingLijst;
    }

    public void setNewHighscore(int newScore) {
        if (newScore > this.highScore) {
            this.highScore = newScore;
        }
    }

    public void berekenRating(double nieuwRating) {

        if (ratingLijst.size() > 4) {
            ratingLijst.remove(4);
        }
        ratingLijst.add(0, nieuwRating);

        rating = 0;

        for (int i = 0; i < ratingLijst.size(); i++) {

            rating += ratingLijst.get(i) * 5 - i;
        }
    }



    public void addBericht(String bericht) throws RemoteException {

        ics.addBericht(this.Name + ": " + bericht);

    }

    public void startServer(String ipAdressServer) throws NotBoundException, MalformedURLException, RemoteException {
        System.out.println("Connecting");
        ics = (IChatServer) Naming.lookup("rmi://" + ipAdressServer + ":1099/cs");

        System.out.println("Connected");
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<String> getBerichtenRMI() throws NotBoundException, MalformedURLException {
        berichtenRMI = new ArrayList<>();
        try {
            berichtenRMI = ics.getBerichten();
        } catch (RemoteException exc) {
            System.out.println(exc);
        }

        return berichtenRMI;
    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("nigga");
    }
}
