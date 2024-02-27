/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import psproject_v5.connection.ConnectionPool;
import psproject_v5.controller.Controller;
import psproject_v5.domain.AcademicTitle;
import psproject_v5.domain.Department;
import psproject_v5.domain.Employee;
import psproject_v5.domain.IEntity;
import psproject_v5.domain.Status;

/**
 *
 * @author aleks
 */
public class TestKonekcije {
    public static void main(String[] args) {
//        try {
//            Connection c1 = ConnectionPool.getInstance().getConnection();
//            Connection c2 = ConnectionPool.getInstance().getConnection();
//            Connection c3 = ConnectionPool.getInstance().getConnection();
//            ConnectionPool.getInstance().returnConnectionToPool(c1);
////            ConnectionPool.getInstance().returnConnectionToPool(c2);
//        } catch (Exception ex) {
//            Logger.getLogger(TestKonekcije.class.getName()).log(Level.SEVERE, null, ex);
//        }
            System.out.println("glavna: " + Thread.currentThread().isAlive());
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("ova: " + Thread.currentThread().isAlive());
                    System.out.println("1______: trazi");
                    Connection c = ConnectionPool.getInstance().getConnection();
                    System.out.println("1______: "+ LocalDateTime.now()+"\n\t"+c.toString());
                    
//                    Thread.sleep(3000);
                    ConnectionPool.getInstance().returnConnectionToPool(c);
                    System.out.println("\t\t1 ugasen");
                } catch (Exception ex) {
                    Logger.getLogger(TestKonekcije.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 0);
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestKonekcije.class.getName()).log(Level.SEVERE, null, ex);
        }
        timer1.cancel();
        
        System.out.println("glavna: " + Thread.currentThread().isAlive());
        System.out.println("aloo");
//        Timer timer1 = new Timer();
//        timer1.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("1______: trazi");
//                    Connection c = ConnectionPool.getInstance().getConnection();
//                    System.out.println("1______: "+ LocalDateTime.now()+"\n\t"+c.toString());
//                    
//                    Thread.sleep(31000);
//                    ConnectionPool.getInstance().returnConnectionToPool(c);
//                    System.out.println("\t\t1 ugasen");
//                } catch (Exception ex) {
//                    Logger.getLogger(TestKonekcije.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }, 0);
//        Timer timer2 = new Timer();
//        timer2.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("2 __  : trazi");
//                    Connection c = ConnectionPool.getInstance().getConnection();
//                    System.out.println("2 __  : "+ LocalDateTime.now()+"\n\t"+c.toString());
//                    
//                    Thread.sleep(22000);
//                    ConnectionPool.getInstance().returnConnectionToPool(c);
//                    System.out.println("\t\t2 ugasen");
//                } catch (Exception ex) {
//                    Logger.getLogger(TestKonekcije.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }, 1000);
//        Timer timer3 = new Timer();
//        timer3.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("3   : trazi");
//                    Connection c = ConnectionPool.getInstance().getConnection();
//                    System.out.println("3   : "+ LocalDateTime.now()+"\n\t"+c.toString());
//                    
//                    Thread.sleep(20000);
//                    ConnectionPool.getInstance().returnConnectionToPool(c);
//                    System.out.println("\t\t3 ugasen");
//                } catch (Exception ex) {
//                    Logger.getLogger(TestKonekcije.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }, 2000);
//        Timer timer4 = new Timer();
//        timer4.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("4 : trazi");
//                    Connection c = ConnectionPool.getInstance().getConnection();
//                    System.out.println("4 : "+ LocalDateTime.now()+"\n\t"+c.toString());
//                    
//                    Thread.sleep(20000);
//                    ConnectionPool.getInstance().returnConnectionToPool(c);
//                    System.out.println("\t\t4 ugasen");
//                } catch (Exception ex) {
//                    Logger.getLogger(TestKonekcije.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }, 2000);
        
        
        
    }
}
