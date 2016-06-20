/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.daos;

import model.dataBaseConnection.DBConnection;
import model.pojos.MonumentsInformation;
import model.pojos.MonumentsInformationId;
import model.pojos.UserProfile;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Christena
 */
public class MonumentsDao {

    public static MonumentsInformation getData(String monument_id,String name) {

        Session session = DBConnection.getSession();

        MonumentsInformationId monumentsInformationId=new MonumentsInformationId(Integer.valueOf(monument_id),name);
        
        String hql = "from MonumentsInformation where id = :MonumentsInformationId";
        Query query = session.createQuery(hql);
        query.setParameter("MonumentsInformationId", monumentsInformationId);
        MonumentsInformation monumentsInformation = (MonumentsInformation) query.uniqueResult();
        session.beginTransaction();
        session.getTransaction().commit();

        return monumentsInformation;

    }
}
