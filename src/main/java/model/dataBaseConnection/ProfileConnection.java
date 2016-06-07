/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dataBaseConnection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Christena
 */
public class ProfileConnection {

    private static SessionFactory sessionFactory;
    private static Session session;

    private static SessionFactory getInstance() {
        if (sessionFactory == null) {
            synchronized (SessionFactory.class) {
                if (sessionFactory == null) {
                    sessionFactory = new Configuration().configure().buildSessionFactory();

                }
            
            }
        }
                return sessionFactory;

            }
      
      
            


    public static Session getSession() {

        session = getInstance().openSession();

        return session;

    }

}
