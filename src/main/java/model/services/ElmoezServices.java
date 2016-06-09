/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.daos.FeedsDao;
import model.daos.UserProfileDao;
import model.dtos.FeedsDto;
import model.pojos.Feeds;
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
     * ayad
     * sign up 
     * take json object of user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/signUp")
    public String signUp(String user) {
        
        boolean signUpFlag=false;
        String signUpState="";
        
        System.out.println(user.toString());
        try {
            
            
            JSONObject json=new JSONObject(user);
            UserProfile newUser=new UserProfile();
            newUser.setFirstName((String) json.get("firstName"));
            newUser.setLastName((String) json.get("lastName"));
            newUser.setEmail((String) json.get("email"));
            newUser.setPassword((String) json.get("password"));
            newUser.setUserImage("default.jpg");
            signUpFlag=UserProfileDao.register(newUser);
            System.out.println("new user added");
            if(signUpFlag){
                signUpState="registeredSuccessfully";
                
       
            }else{
                signUpState="registeredFailed";
    
       }
            
        } catch (JSONException ex) {
            Logger.getLogger(ElmoezServices.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
        System.out.println("state "+signUpState);
        return "{\"state\":\""+signUpState+"\"}";
       
       
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/logIn/{mail}/{pass}")
    
    public String logIn(@PathParam(value = "mail") String userMail,@PathParam(value = "pass") String userPass){
//        System.out.println("mail "+userMail+" pass "+userPass);
          UserProfile existUser=new UserProfile();
          existUser.setEmail(userMail);
          existUser.setPassword(userPass);
          String userState=UserProfileDao.checkLogin(existUser);
        
        return "{\"state\":\""+userState+"\"}";
        

    }
    
    /**
     * shereen
     * feeds from user
     */
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/feeds")
    public String getArrayOfFeeds(){
        
        List<Feeds> feeds=FeedsDao.getAllFeeds();
        List<FeedsDto> usersFeeds=new ArrayList<FeedsDto>();
        for (int i = 0; i < feeds.size(); i++) {
            FeedsDto feed=new FeedsDto();
            feed.setUserName(feeds.get(i).getUserProfile().getFirstName());
            feed.setUserImage(feeds.get(i).getUserProfile().getUserImage());
            feed.setFeed(feeds.get(i).getFeed());
            feed.setImage(feeds.get(i).getImage());
            feed.setFeedTime(feeds.get(i).getFeedTime());
            feed.setLikeFeed(feeds.get(i).getLikeFeed());
            usersFeeds.add(feed);
            
            
            
            
        }
        //System.out.println(feeds.get(0).getUserProfile().getFirstName());
        return usersFeeds.toString();
          

    }

   /**
     * Christina Dawoud
     * change the user name
     * take json object of user name and email
     */ 
   
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
    /**
     * Christina Dawoud
     * change password
     * take json object of user password and email 
     */

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

