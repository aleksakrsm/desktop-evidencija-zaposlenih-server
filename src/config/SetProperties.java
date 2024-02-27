/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import psproject_v5.constants.MyConstants;

/**
 *
 * @author aleks
 */
public class SetProperties {

    public static void main(String[] args) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config/dbconfig.properties");

            // set the properties value
            prop.setProperty(MyConstants.DB_CONFIG_DRIVER, "com.mysql.jdbc.cj.Driver");
            prop.setProperty(MyConstants.DB_CONFIG_URL, "jdbc:mysql://127.0.0.1:3306/fakultet_projekat");
            prop.setProperty(MyConstants.DB_CONFIG_USER, "root");
            prop.setProperty(MyConstants.DB_CONFIG_PASSWORD, "krs3414ale.");
            prop.setProperty(MyConstants.DB_CONFIG_NUMBER_OF_CONNECTIONS, "5");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {

        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {

                }
            }

        }
        try {

            output = new FileOutputStream("config/server.properties");

            // set the properties value
            prop.setProperty(MyConstants.SERVER_PORT, "9000");
            

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {

        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {

                }
            }

        }
        
        
    }
}
