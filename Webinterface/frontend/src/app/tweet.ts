export class Tweet {
  user_id: string;
  user_name: string;
  user_description: string;
  user_friends: string;
  user_followers: string;
  user_verified: string;
  user_location: string;

  tweet_id: string;
  tweet_hashtag: string;
  tweet_content: string;
  tweet_url: string;
  tweet_likes: string;

  misc_timestamp: string;
  misc_coordinates: string;


  constructor() {
    
    this.user_id = '';
    this.user_name = '';
    this.user_description = '';
    this.user_friends = '';
    this.user_followers = '';
    this.user_verified = '';
    this.user_location = '';

    this.tweet_id = '';
    this.tweet_hashtag = ''; 
    this.tweet_content = '';
    this.tweet_url = '';
    this.tweet_likes = '';

    this.misc_timestamp = '';
    this.misc_coordinates = '';
  }
}