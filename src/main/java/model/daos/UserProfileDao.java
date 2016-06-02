/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import model.dataBaseConnection.DBConnection;
import model.pojos.UserProfile;
import org.hibernate.Session;

/**
 *
 * @author 3yad
 */
public class UserProfileDao {
    
    
    
    /**
     * ayad
     * register for a new user
     * @return 
     */
    public static boolean register(UserProfile newUser){
        Session session=DBConnection.getSession();
        session.beginTransaction();
        session.persist(newUser);
        session.getTransaction().commit();
        return true;
    
    
    
    }
    
}
