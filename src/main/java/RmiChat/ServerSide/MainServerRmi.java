package RmiChat.ServerSide;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MainServerRmi {
    public static void main(String []args){
        try {
            LocateRegistry.createRegistry(8374);
            LocateRegistry.createRegistry(4321);
            Naming.rebind("rmi://localhost:4321/remote",new ServerRmiImpl());
            System.out.println("Server Started ...");
        } catch (MalformedURLException | RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
