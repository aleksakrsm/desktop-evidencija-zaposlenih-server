/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.threads;

import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import psproject_v5.constants.MyConstants;

/**
 *
 * @author aleks
 */
public class ServerThread extends Thread{
    private ServerSocket serverSocket;
    private List<ClientThread> clientThreads;
    public ServerThread() throws Exception{
        Properties p = new Properties();
        p.load(new FileReader(MyConstants.SERVER_PROPERTIES_FILE));
        int port = Integer.parseInt(p.getProperty(MyConstants.SERVER_PORT));
        serverSocket = new ServerSocket(port);
        clientThreads = new ArrayList<>();
    }

    @Override
    public void run() {
        while(serverSocket!=null && !serverSocket.isClosed()){
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Povezao se klijent broj : " + (clientThreads.size()+1));
                ClientThread client = new ClientThread(socket, this);
                client.start();
                clientThreads.add(client);
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    public List<ClientThread> getClientThreads() {
        return clientThreads;
    }
    private void stopClients(){
        for (ClientThread clientThread : clientThreads) {
            if(clientThread!=null && clientThread.isAlive())
                clientThread.stopClient();
        }
    }
    public void stopServer(){
        stopClients();
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void removeClient(ClientThread clientThread){
        clientThreads.remove(clientThread);
        System.out.println("broj povezanih klijenata: "+ clientThreads.size());
    }
    
}
