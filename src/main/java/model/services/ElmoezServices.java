/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @Context 
    ServletContext context;

    
    public ElmoezServices(@Context ServletContext context) {
    
        this.context=context;
    }
    
    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/testService")
    public String testService() {

//        UserProfile newUser = new UserProfile();
//        newUser.setFirstName("firstName");
//        newUser.setLastName("lastName");
//        newUser.setEmail("email");
//        newUser.setPassword("password");
//        newUser.setUserImage("image");
//        UserProfileDao.register(newUser);

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
    /**
     * nour
     * log in 
     * take json object of user and check it is correct log in or not
     */
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
          
        
        return userState;
        

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
    
    
    /**
     * Christina Dawoud
     * change profile picture
     */
    
        @POST
	@Path("/image")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadImage(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,@FormDataParam("email") String email) {
            
      
                String path=context.getRealPath("/images");
                System.out.println(path+"\n");

		String uploadedFileLocation = path+"\\"+ fileDetail.getFileName();

                String ImageName=fileDetail.getFileName();
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation,email);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,String uploadedFileLocation,String email) {

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
                        
                                UserProfileDao.editProfilePicture(email);
                        
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
        
        
        
    /**
     * Christina Dawoud
     * remove profile picture
     */
    
        @POST
	@Path("/removeimage")
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public String removeImage(String email) {
            boolean removeImageState=false;
      
            System.out.println("\n\n\n  request comeeeeeeeeeee   \n\n\n");
            try {
            JSONObject json = new JSONObject(email);
            String Email=(String) json.get("email");
            
            System.out.println("\n\n\n  Emailllllllllllllll"+Email+"  \n\n\n");
            
            
            removeImageState= UserProfileDao.removeImage(Email);
            
            
        } catch (JSONException ex) {

            ex.printStackTrace();
        }
        return "{\"state\":\""+removeImageState+"\"}";

	}

        /**
     * Christina Dawoud
     * remove profile picture
     */
    
        @POST
	@Path("/getData")
	@Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
	public String getDataFromDB(String id) {
            boolean returnData=false;
      
        return "{\"state\":\""+returnData+"\"}";

	}


}

