/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proftaak;


/**
 *
 * @author Stephan
 */
public class Bericht implements Comparable<Bericht>{
    private String bericht;
    private long timestamp;
    private Gebruiker gebruiker;
    
    public Bericht(String Bericht, Gebruiker gebruiker)
    {    
        this.bericht = Bericht;
        this.gebruiker = gebruiker;
        timestamp = System.currentTimeMillis();
    }
    public long getTimestamp()
    {
        return timestamp;
    }
    public Gebruiker getGebruiker()
    {
        return gebruiker;
    }
    public String getBericht()
    {
        return bericht;
    }

    @Override
    public int compareTo(Bericht b) {
        return this.timestamp<b.timestamp?-1: 
                this.timestamp>b.timestamp?1:0;
       }
}
