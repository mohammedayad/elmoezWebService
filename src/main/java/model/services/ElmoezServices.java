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

        UserProfile newUser = new UserProfile();
        newUser.setFirstName("firstName");
        newUser.setLastName("lastName");
        newUser.setEmail("email");
        newUser.setPassword("password");
        newUser.setUserImage("image");
        UserProfileDao.register(newUser);

        return "{\"museum\":\"elmoez street\"}";
    }

    /**
     * ayad sign up take json object of user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/signUp")
    public String signUp(String user) {

        boolean signUpState = false;

        System.out.println(user.toString());
        try {

            JSONObject json = new JSONObject(user);
            UserProfile newUser = new UserProfile();
            newUser.setFirstName((String) json.get("firstName"));
            newUser.setLastName((String) json.get("lastName"));
            newUser.setEmail((String) json.get("email"));
            newUser.setPassword((String) json.get("password"));
            newUser.setUserImage("default.jpg");
            signUpState = UserProfileDao.register(newUser);
            System.out.println("new user added");

        } catch (JSONException ex) {
            Logger.getLogger(ElmoezServices.class.getName()).log(Level.SEVERE, null, ex);
            signUpState = false;

        }
        System.out.println("state " + signUpState);

        if (signUpState) {
            return "{\"state\":\"registeredSuccessfully\"}";

        } else {
            return "{\"state\":\"registeredFailed\"}";

        }
    }

    @POST
    @Path("/username")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public String editUserName(String userName) {

        boolean editNameState=false;
        try {
            System.out.println("request comeeeeeeeeeeeee");
            JSONObject json = new JSONObject(userName);
            String email=(String) json.getString("email");
            String firstName=(String) json.getString("firstName");
            String lastName=(String) json.getString("lastName");
            editNameState= UserProfileDao.editUserName(email,firstName,lastName);
            
            
        } catch (JSONException ex) {

            ex.printStackTrace();
        return "{\"state\":\""+"exception"+"\"}";
        }
        return "{\"state\":\""+editNameState+"\"}";

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/password")
    public String editPassword(String Password) {

        boolean editPasswordState=false;
        try {
            JSONObject json = new JSONObject(Password);
            String email=(String) json.get("email");
            String password=(String) json.get("password");
            editPasswordState= UserProfileDao.editPassword(email,password);
            
            
        } catch (JSONException ex) {

            ex.printStackTrace();
        }
        return "{\"state\":\""+editPasswordState+"\"}";

    }

}
