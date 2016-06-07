package model.pojos;
// Generated 31-May-2016 22:35:14 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONObject;

/**
 * Feeds generated by hbm2java
 */

public class Feeds  implements java.io.Serializable {

    
     private Integer feedsId;
     @XmlElement(name="userProfile")
     private UserProfile userProfile;
     @XmlElement(name="feed")
     private String feed;
     @XmlElement(name="image")
     private String image;
     @XmlElement(name="feedTime")
     private Date feedTime;
     @XmlElement(name="likeFeed")
     private int likeFeed;

    public Feeds() {
    }

    public Feeds(UserProfile userProfile, String feed, String image, Date feedTime, int likeFeed) {
       this.userProfile = userProfile;
       this.feed = feed;
       this.image = image;
       this.feedTime = feedTime;
       this.likeFeed = likeFeed;
    }
   
    public Integer getFeedsId() {
        return this.feedsId;
    }
    
    public void setFeedsId(Integer feedsId) {
        this.feedsId = feedsId;
    }
    public UserProfile getUserProfile() {
        return this.userProfile;
    }
    
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    public String getFeed() {
        return this.feed;
    }
    
    public void setFeed(String feed) {
        this.feed = feed;
    }
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    public Date getFeedTime() {
        return this.feedTime;
    }
    
    public void setFeedTime(Date feedTime) {
        this.feedTime = feedTime;
    }
    public int getLikeFeed() {
        return this.likeFeed;
    }
    
    public void setLikeFeed(int likeFeed) {
        this.likeFeed = likeFeed;
    }
    
    
   @Override
    public String toString() {
        try {
            
            return new JSONObject()
                    .put("userProfile", userProfile)
                    .put("feed", feed)
                    .put("image", image)
                    .put("feedTime", feedTime)
                    .put("likeFeed", likeFeed).toString();
        } catch (Exception e) {
            return null;
        }


}

}


