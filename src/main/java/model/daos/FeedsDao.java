/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import java.util.ArrayList;
import java.util.List;
import model.dataBaseConnection.DBConnection;
import model.dtos.FeedsDto;
import model.pojos.Feeds;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author sh
 */
public class FeedsDao {
    /**
     * shereen
     * method that return
     * all feeds from table Feeds
     */
    
    public static List<Feeds> getAllFeeds(){
        Session session=DBConnection.getSession();
        session.beginTransaction();
        Query hqlquery = session.createQuery("from Feeds");
        List<Feeds> result = (List<Feeds>)hqlquery.list();
        session.getTransaction().commit();
        System.out.println(result);
        return result;
    }
}
