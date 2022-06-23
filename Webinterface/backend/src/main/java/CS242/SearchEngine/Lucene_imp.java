package CS242.SearchEngine;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.BooleanSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lucene_imp {

    public List<TweetModel> getResults(String query) throws IOException, ParseException {

        //Set the indexed data directory
        Directory userDirectory  = FSDirectory.open(Paths.get("user_index"));
        Directory tweetDirectory = FSDirectory.open(Paths.get("tweet_index"));

        DirectoryReader userIndexReader  = DirectoryReader.open(userDirectory);
        DirectoryReader tweetIndexReader = DirectoryReader.open(tweetDirectory);

        //Set the indexSearcher
        IndexSearcher userIndexSearcher  = new IndexSearcher(userIndexReader);
        IndexSearcher tweetIndexSearcher = new IndexSearcher(tweetIndexReader);

        //It will set the ranking algorithm 
        userIndexSearcher.setSimilarity(new BooleanSimilarity());
        tweetIndexSearcher.setSimilarity(new BM25Similarity());

        //Create and set field weights (R&D this!)
        String[] user_fields = {"user_id", "user_name", "user_description", "user_friends", "user_followers", "user_verified", "user_location"};
        Map<String, Float> user_weights = new HashMap<>();
        user_weights.put(user_fields[0], 1.0f);
        user_weights.put(user_fields[1], 1.0f);
        user_weights.put(user_fields[2], 1.0f);
        user_weights.put(user_fields[3], 1.0f);
        user_weights.put(user_fields[4], 1.0f);
        user_weights.put(user_fields[5], 1.0f);
        user_weights.put(user_fields[6], 1.0f);

        //Create and set field weights (R&D this!)
        String[] tweet_fields = {"user_id", "tweet_id", "tweet_hashtags", "tweet_content", "tweet_url", "tweet_likes", "misc_timestamp", "misc_coordinates"};
        Map<String, Float> tweet_weights = new HashMap<>();
        tweet_weights.put(tweet_fields[0], 1.0f);
        tweet_weights.put(tweet_fields[1], 1.0f);
        tweet_weights.put(tweet_fields[2], 1.0f);
        tweet_weights.put(tweet_fields[3], 1.0f);
        tweet_weights.put(tweet_fields[4], 1.0f);
        tweet_weights.put(tweet_fields[5], 1.0f);
        tweet_weights.put(tweet_fields[6], 1.0f);
        tweet_weights.put(tweet_fields[7], 1.0f);

        //Set the query analyzer (R&D this!)
        Analyzer userAnalyzer = new KeywordAnalyzer();
        Analyzer tweetAnalyzer = new EnglishAnalyzer();

        MultiFieldQueryParser userDataParser  = new MultiFieldQueryParser(user_fields, userAnalyzer, user_weights);
        MultiFieldQueryParser tweetDataParser = new MultiFieldQueryParser(tweet_fields, tweetAnalyzer, tweet_weights);
        Query tweetQuery  = tweetDataParser.parse(query);

        //Create the return variable
        List<TweetModel> results = new ArrayList<>();

        //Set the number of results (R&D this!)
        int topCount = 20;
        ScoreDoc[] scores = tweetIndexSearcher.search(tweetQuery, topCount).scoreDocs;

        //Print the output (R&D this!)
        for(int rank = 0; rank < scores.length; ++rank) {
            
            //Get TweetModel details
            Document tweetDoc = tweetIndexSearcher.doc(scores[rank].doc);

            //Get user details
            Query userQuery  = userDataParser.parse(tweetDoc.get("user_id"));
            ScoreDoc[] user  = userIndexSearcher.search(userQuery, 1).scoreDocs;
            Document userDoc = userIndexSearcher.doc(user[0].doc);

            String user_id          = userDoc.get("user_id");
            String user_name        = userDoc.get("user_name");
            String user_description = userDoc.get("user_description");
            String user_friends     = userDoc.get("user_friends");
            String user_followers   = userDoc.get("user_followers");
            String user_verified    = userDoc.get("user_verified");
            String user_location    = userDoc.get("user_location");

            //TweetModel Information
            String tweet_id         = tweetDoc.get("tweet_id");
            String tweet_hashtags   = tweetDoc.get("tweet_hashtags");
            String tweet_content    = tweetDoc.get("tweet_content");
            String tweet_url        = tweetDoc.get("tweet_url");
            String tweet_likes      = tweetDoc.get("tweet_likes");

            String misc_timestamp   = tweetDoc.get("misc_timestamp");
            String misc_coordinates = tweetDoc.get("misc_coordinates");

            TweetModel result = new TweetModel(user_id, user_name, user_description, user_friends, user_followers, user_verified, user_location, tweet_id, tweet_hashtags, tweet_content, tweet_url, tweet_likes, misc_timestamp, misc_coordinates);

            results.add(result);

        }
        tweetDirectory.close();
        userDirectory.close();

        //Return the search results
        return results;
    }
}
