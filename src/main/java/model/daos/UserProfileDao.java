/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import java.util.List;
import model.dataBaseConnection.DBConnection;
import model.pojos.UserProfile;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.HibernateException;


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
       
    
    
    /**
     * nour
     * check if this mail and pass is exist or not
     */
    public static String checkLogin(UserProfile existUser){
        Session session=DBConnection.getSession();
        session.beginTransaction();
        String loginFlag="";
        String correctPass="";
        String userName="";
        String hqlQuery="select password,firstName,lastName from UserProfile where email=:x";
        Query query = session.createQuery(hqlQuery).setString("x", existUser.getEmail());
        
//        String correctPass=(String) query.uniqueResult();
        List<Object[]> users= (List<Object[]>)query.list();
        
        
        session.getTransaction().commit();
        
        if(users.size()>0){//if user register with correct mail
            for(Object[] user: users){
                correctPass = (String)user[0];
                userName=(String)user[1]+" "+(String)user[2];
         
         
            }
            if(correctPass.equals(existUser.getPassword()))//if user register with correct password
            {
                loginFlag="{\"state\":\"register Successfully\",\"userName\":\""+userName+"\"}";
            
            }else{
                loginFlag="{\"state\":\"incorrect password\"}";
            
            
            }
        
        
        }else{
            loginFlag="{\"state\":\"email is not exist\"}";
        
        
        }
        
//        System.out.println("pass "+query.uniqueResult());
        
        
        return loginFlag;
        
    }
 /**
     * Christina 
     *
     * func to edit user name
     */
    
    public static boolean editUserName(String email, String firstName, String lastName) {
        Session session = DBConnection.getSession();
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



 /**
     * Christina 
     *
     * func to edit password
     */

    public static boolean editPassword(String email, String password) {
        Session session = DBConnection.getSession();

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
     * Christina 
     *
     * func to edit profile picture
     */


    public static boolean editProfilePicture(String email,String ImageName) {
        Session session = DBConnection.getSession();
        String hql = "update UserProfile set userImage = :profilePicture WHERE email = :email ";
        Query query = session.createQuery(hql);
        query.setParameter("profilePicture", ImageName);
        query.setParameter("email", email);
        int executeUpdate = query.executeUpdate();
        session.beginTransaction();
        session.getTransaction().commit();

        return true;

    }

    public static boolean removeImage(String email) {

        Session session = DBConnection.getSession();

        String hql = "update UserProfile set userImage = :profilePicture WHERE email = :email ";
        Query query = session.createQuery(hql);
        query.setParameter("profilePicture", "default");
        query.setParameter("email", email);
        int executeUpdate = query.executeUpdate();
        session.beginTransaction();
        session.getTransaction().commit();

        return true;


    }
    public static UserProfile getUser(String email) {

        Session session = DBConnection.getSession();

        String hql = "from UserProfile WHERE email = :email ";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        UserProfile user = (UserProfile)query.uniqueResult();
        session.beginTransaction();
        session.getTransaction().commit();

        return user;


    }
    


    
}
