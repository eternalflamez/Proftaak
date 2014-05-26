/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmichat.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import proftaak.LobbyGUIController;

/**
 *
 * @author Laurens
 */
public class Server {

    /**
     * @param args the command line arguments
     */
      static    ChatServer cs;
    public static void main(String[] args) throws MalformedURLException {
          try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            
             cs = new ChatServer();
      
            Naming.rebind("cs", (Remote) cs);
            System.out.println("Running...");
        } catch (RemoteException exc) {
            System.out.println(exc);
        }
    }
    public ChatServer getCs(){
        return cs;
    }
    
}
