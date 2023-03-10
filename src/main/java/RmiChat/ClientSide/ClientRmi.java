package RmiChat.ClientSide;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ClientRmi extends Remote {
    //cette fonction pour recuperer les messages de la discuttions a partir de server
    void retrieveMessage(String message) throws RemoteException;

    //cette fonction pour recuperer les fichiers partag√©es de la discussion a partir de server
    void retrieveMessage(String filename, ArrayList<Integer> inc) throws RemoteException;

    //cette fonction pour envoyer un message a partir d'un client vers le serveur
    void sendMessage(List<String> list) throws RemoteException;

    //cette fonction pour recuperer le nom des clients connectes (identificateur des clients) ==> username
    String getName()throws RemoteException;

    //cette fonction pour desactiver a un client la fonctionnalit√© d'envoyer un message
    void closeChat(String message) throws RemoteException;

    //cette fonction pour activer a un client la fonctionnalit√© d'envoyer un message
    void openChat() throws RemoteException;
}
