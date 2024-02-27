/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psproject_v5.connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import psproject_v5.constants.MyConstants;

/**
 *
 * @author aleks
 */
public class ConnectionPool {

    private static ConnectionPool instance;
    private List<Connection> connections;

    private ConnectionPool() {
        try {
            connections = new LinkedList<>();
            Properties properties = new Properties();
            properties.load(new FileReader(MyConstants.DB_CONFIG_PROPERTIES_FILE));
//            Class.forName(MyConstants.DB_CONFIG_DRIVER);
            String url = properties.getProperty(MyConstants.DB_CONFIG_URL);
            String user = properties.getProperty(MyConstants.DB_CONFIG_USER);
            String password = properties.getProperty(MyConstants.DB_CONFIG_PASSWORD);
            String numberOfConnections = properties.getProperty(MyConstants.DB_CONFIG_NUMBER_OF_CONNECTIONS);
            int number = Integer.parseInt(numberOfConnections);
            
            System.out.println("broj konekcija: "+numberOfConnections);
            
            for (int i = 0; i < number; i++) {
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, user, password);
                    connection.setAutoCommit(false);
                    connections.add(connection);
                    System.out.println(" konekcija broj " + (i + 1) + " je napravljena");
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

//    public Connection getConnection() throws Exception {
//        
//        if (connections != null && !connections.isEmpty()) {
//            Connection c = connections.get(0);
//            connections.remove(0);
//            System.out.println("dao sam konekciju! Ostalo mi je " + connections.size());
//            return c;
//        }
//        throw new Exception("nema slobodne konekcije");
//    }
    public Connection getConnection() throws Exception {
        if(connections==null)return null;
        synchronized (this) {
            if(connections.isEmpty())
                wait();
            
            Connection c = connections.get(0);
            connections.remove(0);
            System.out.println("dao sam konekciju! Ostalo mi je " + connections.size());
            return c;    
        }
    }

    public void returnConnectionToPool(Connection connection) {
        synchronized (this) {
            
            connections.add(connection);
            System.out.println("vracena: "+connection);
            notify();
            System.out.println("hej!!!");
        }
        System.out.println("vratio sam konekciju! Ostalo mi je " + connections.size());
    }
}
