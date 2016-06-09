/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import model.dataBaseConnection.DBConnection;
import model.dataBaseConnection.ProfileConnection;
import model.pojos.UserProfile;
//<<<<<<< HEAD
import org.hibernate.HibernateException;
//=======
//>>>>>>> origin/master
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author 3yad
 */
public class UserProfileDao {

    

    /**
     * ayad register for a new user
     *
     * @return
     */
    public static boolean register(UserProfile newUser) {
        Session session = DBConnection.getSession();
        session.beginTransaction();
        session.persist(newUser);
        session.getTransaction().commit();
        return true;

    }

    public static boolean editUserName() {

        Session session = ProfileConnection.getSession();

//        UserProfile user = (UserProfile) session.load(UserProfile.class, 1);
//        user.setFirstName("kokiiiiiiiiiiiiiii");
//        session.beginTransaction();
//        session.persist(user);
//        session.getTransaction().commit();
//        System.out.println("Seller added");

//        String hql = "UPDATE UserProfile set firstName = :first WHERE userId = :id;";
//        Query query = ses.createQuery(hql);
//        query.setParameter("first", "kooki");
//        query.setParameter("id", 1);
//        int executeUpdate = query.executeUpdate();
//        


        String hql = "update UserProfile set firstName = :first WHERE userId = :id ";
        Query query = session.createQuery(hql);
        query.setParameter("first", "mero");
        query.setParameter("id", 1);
        int executeUpdate = query.executeUpdate();


        session.beginTransaction();
        session.getTransaction().commit();
//
//        System.out.println("Rows affected: "+executeUpdate);

//        
        return true;
    }

    public static boolean editPassword() {

        Session session = ProfileConnection.getSession();

        String hql = "update UserProfile set password = :password WHERE userId = :id ";
        Query query = session.createQuery(hql);
        query.setParameter("password", "H");
        query.setParameter("id", 1);
        int executeUpdate = query.executeUpdate();


        session.beginTransaction();
        session.getTransaction().commit();

        return true;

    }

    public static boolean editUserName(String email, String firstName, String lastName) {
        Session session = ProfileConnection.getSession();
        String hql = "update UserProfile set firstName = :firstName , lastName = :lastName WHERE email = :email ";
        Query query = session.createQuery(hql);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName",lastName);
        query.setParameter("email",email );
        int executeUpdate = query.executeUpdate();
        session.beginTransaction();
        session.getTransaction().commit();
        return true;
    }

    public static boolean editPassword(String email, String password) {
        Session session = ProfileConnection.getSession();

        String hql = "update UserProfile set password = :password WHERE email = :email ";
        Query query = session.createQuery(hql);
        query.setParameter("password", password);
        query.setParameter("email", email);
        int executeUpdate = query.executeUpdate();
        session.beginTransaction();
        session.getTransaction().commit();

        return true;

    }

    
    /**
     * nour
     * check if this mail and pass is exist or not
     */
    public static String checkLogin(UserProfile existUser){
        Session session=DBConnection.getSession();
        session.beginTransaction();
        String loginFlag="";
        String hqlQuery="select password from UserProfile where email=:x";
        Query query = session.createQuery(hqlQuery).setString("x", existUser.getEmail());
        
        String correctPass=(String) query.uniqueResult();
        session.getTransaction().commit();
        
        if(correctPass!=null){//if user register with correct mail
            if(correctPass.equals(existUser.getPassword()))//if user register with correct password
            {
                loginFlag="register Successfully";
            
            }else{
                loginFlag="incorrect password";
            
            
            }
        
        
        }else{
            loginFlag="email is not exist";
        
        
        }
        
//        System.out.println("pass "+query.uniqueResult());
        
        
        return loginFlag;
        
    }
    
 
     /**
     * nour
     * check if this mail and pass is exist or not
     */
//    public static String checkLogin(UserProfile existUser){
//        Session session=DBConnection.getSession();
//        session.beginTransaction();
//        String loginFlag="";
//        String hqlQuery="select password from UserProfile where email=:x";
//        Query query = session.createQuery(hqlQuery).setString("x", existUser.getEmail());
//        
//        String correctPass=(String) query.uniqueResult();
//        session.getTransaction().commit();
//        
//        if(correctPass!=null){//if user register with correct mail
//            if(correctPass.equals(existUser.getPassword()))//if user register with correct password
//            {
//                loginFlag="register Successfully";
//            
//            }else{
//                loginFlag="incorrect password";
//            
//            
//            }
//        
//        
//        }else{
//            loginFlag="email is not exist";
//        
//        
//        }
//        
////        System.out.println("pass "+query.uniqueResult());
//        
//        
//        return loginFlag;
//        
//    }
    
    
 
}
