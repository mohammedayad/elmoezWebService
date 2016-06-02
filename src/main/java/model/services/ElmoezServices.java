/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.daos.UserProfileDao;
import model.pojos.UserProfile;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Christena
 */
@Path("/elmoez")
public class ElmoezServices {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/testService")
    public String testService() {
        
            UserProfile newUser=new UserProfile();
            newUser.setFirstName("firstName");
            newUser.setLastName("lastName");
            newUser.setEmail("email");
            newUser.setPassword("password");
            newUser.setUserImage("image");
            UserProfileDao.register(newUser);
       
        return "{\"museum\":\"elmoez street\"}";
    }
    
    /**
     * ayad
     * sign up 
     * take json object of user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/signUp")
    public String signUp(String user) {
        
        boolean signUpState=false;
        
        System.out.println(user.toString());
        try {
            
            
            JSONObject json=new JSONObject(user);
            UserProfile newUser=new UserProfile();
            newUser.setFirstName((String) json.get("firstName"));
            newUser.setLastName((String) json.get("lastName"));
            newUser.setEmail((String) json.get("email"));
            newUser.setPassword((String) json.get("password"));
            newUser.setUserImage((String) json.get("image"));
            signUpState=UserProfileDao.register(newUser);
            
            
        } catch (JSONException ex) {
            Logger.getLogger(ElmoezServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       System.out.println("new user added");
        return "{\"state\":\""+signUpState+"\"}";
    }
    
    
}
