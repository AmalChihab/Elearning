package RmiChat.ServerSide;

import RmiChat.ClientSide.ClientRmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ServerRmiImpl extends UnicastRemoteObject implements ServerRmi {
    private final ArrayList<ClientRmi> clients; //liste contient tous les clients mais qui ne sont pas bloqué

    //constructeur
    public ServerRmiImpl() throws RemoteException {
        super();
        this.clients = new ArrayList<>();
    }

    //cette fonction pour distribuer le message vers tous les clients connectes, ou une a liste presicé par le client (disscution privée)
    @Override
    public synchronized void broadcastMessage(String message, List<String> list) throws RemoteException {
        if(list.isEmpty()){
            int i= 0;
            while (i < clients.size()){
                clients.get(i++).retrieveMessage(message);
            }
        }else{
            for (ClientRmi client : clients) {
                for(int i=0;i<list.size();i++){
                    if(client.getName().equals(list.get(i))){
                        client.retrieveMessage(message);
                    }
                }
            }
        }
    }

    //cette fonction pour distribuer un fichier vers tous les clients connectes, ou une a liste presicé par le client (disscution privée)
    @Override
    public synchronized void broadcastMessage(ArrayList<Integer> inc, List<String> list,String filename) throws RemoteException {
        if(list.isEmpty()){
            int i= 0;
            while (i < clients.size()){
                clients.get(i++).retrieveMessage(filename,inc);
            }
        }else{
            for (ClientRmi client : clients) {
                for(int i=0;i<list.size();i++){
                    if(client.getName().equals(list.get(i))){
                        client.retrieveMessage(filename,inc);
                    }
                }
            }
        }
    }

    //cette fonction pour ajouter un client connectes a la liste des clients sur le serveur
    @Override
    public synchronized void addClient(ClientRmi client) throws RemoteException {
        this.clients.add(client);
    }

    //cette fonction pour recupere le nom des clients connectes
    @Override
    public synchronized Vector<String> getListClientByName(String name) throws RemoteException {
        Vector<String> list = new Vector<>();
        for (ClientRmi client : clients) {
            if(!client.getName().equals(name)){
                list.add(client.getName());
            }
        }
        return list;
    }


    //cette fonction pour supprimer totalement une liste des clients de chat (kick-out)
    @Override
    public synchronized void removeClient(List<String> clients){
        for(int j=0;j<this.clients.size();j++){
            for(int i=0;i<clients.size();i++){
                try {
                    if(this.clients.get(j).getName().equals(clients.get(i))){
                        this.clients.get(j).closeChat(clients.get(i) + " you are removed from the chat");
                        this.clients.remove(j);
                    }
                } catch (RemoteException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        }
    }

    //cette fonction pour supprimer totalement un seul client de chat (kick-out)
    @Override
    public synchronized void removeClient(String clients){
        for(int j=0;j<this.clients.size();j++){
            try {
                if(this.clients.get(j).getName().equals(clients)){
                    this.clients.remove(j);
                }
            } catch (RemoteException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }


    //cette fonction pour verfifier est que un username existe deja dans le serveur ou non, car username est l'identificateur sur chat
    @Override
    public boolean checkUsername(String username) throws RemoteException {
        boolean exist = false;
        for(int i=0;i<clients.size();i++){
            if(clients.get(i).getName().equals(username)){
                exist = true;
            }
        }
        return exist;
    }

}
