package CS242.SearchEngine;

public class TweetModel {

    //User Information
    public String user_id;
    public String user_name;
    public String user_description;
    public String user_friends;
    public String user_followers;
    public String user_verified;
    public String user_location;

    //Tweet Information
    public String tweet_id;
    public String tweet_hashtags;
    public String tweet_content;
    public String tweet_url;
    public String tweet_likes;

    public String misc_timestamp;
    public String misc_coordinates;

    public TweetModel(String user_id, String user_name) {
        this.user_id   = user_id;
        this.user_name = user_name;
    }

    public TweetModel(String user_id, String user_name, String user_description, String user_friends, String user_followers, String user_verified, String user_location, String tweet_id, String tweet_hashtags, String tweet_content, String tweet_url, String tweet_likes, String misc_timeStamp, String misc_coordinates) {

        //User specific data
        this.user_id          = user_id;
        this.user_name        = user_name;
        this.user_description = user_description;
        this.user_friends     = user_friends;
        this.user_followers   = user_followers;
        this.user_verified    = user_verified;
        this.user_location    = user_location;

        //Tweet specific data
        this.tweet_id         = tweet_id;
        this.tweet_hashtags   = tweet_hashtags;
        this.tweet_content    = tweet_content;
        this.tweet_url        = tweet_url;
        this.tweet_likes      = tweet_likes;

        //Misc data
        this.misc_timestamp   = misc_timeStamp;
        this.misc_coordinates = misc_coordinates;

    }
}