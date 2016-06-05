/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dataBaseConnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 3yad
 */
public class DBConnection {
    
    //JDBC 
//    private static String driver = "com.mysql.jdbc.Driver";
//    private static String url = "jdbc:mysql://localhost:3306/";
//    private static String dbuser = "root";
//    private static String dbpass ="m3630804";
//    private static String dbname ="elmoez";
//    private static Statement st;
    
    
    //hibrnet
    private static SessionFactory sessionFactory;
    private static Session session;
    
    
    /**
     * ayad
     * hibrnet singlton design pattern for session factory
     * @return 
     */
    private static SessionFactory getInstance(){
        if(sessionFactory==null){
            synchronized (SessionFactory.class){
                if(sessionFactory==null){
                    sessionFactory=new Configuration().configure().buildSessionFactory();
                
                
                }
                    
            
            
            }
        
        
        }
        
        return sessionFactory;
    
    
    }//end of singlton instance
  
    //uesing hibrnet
    public static Session getSession(){
//        SessionFactory sessionFactory=new Configuration().configure("hibernetConfigrations/hibernate.cfg.xml").buildSessionFactory();
//        Session session=sessionFactory.openSession();
//        return session;
        session=getInstance().openSession();
        
        return session;


    
        
    }
    //uesing jdbc
//    public static Statement getStatement(){
//        try{
//                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//                Connection con = DriverManager.getConnection(url+dbname, dbuser, dbpass);
//                st = con.createStatement();
//        
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//        return st;  
//    }
    
}
