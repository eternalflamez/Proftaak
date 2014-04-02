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
    /**
     * 
     * @return het tijdstip waarop het bericht is geplaatst in ms.
     */
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

    /**
     * Zorgt ervoor dat de berichten gesorteerd worden op tijdstip.
     * @param b het bericht
     * @return -1 als this.timestamp < b.timestamp
     * 1 als this.timestamp>b.timestamp
     */
    @Override
    public int compareTo(Bericht b) {
        return this.timestamp<b.timestamp?-1: 
                this.timestamp>b.timestamp?1:0;
       }
}
