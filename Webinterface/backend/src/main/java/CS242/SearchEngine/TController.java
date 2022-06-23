package CS242.SearchEngine;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TController {
    static List<TweetModel> tweets;
    static {
        tweets = new ArrayList<>();
        tweets.add(new TweetModel("1", "Balaji"));
        tweets.add(new TweetModel("2", "Akhil"));
        tweets.add(new TweetModel("3", "Madhu"));
        tweets.add(new TweetModel("4", "Imran"));
        tweets.add(new TweetModel("5", "shreya"));
    }

    @GetMapping("/tweets")
    public List<TweetModel> searchTweets(
            @RequestParam(required=false, defaultValue="") String query) throws IOException, ParseException {

        if (query.isEmpty())
            return tweets;

        Lucene_imp LS  = new Lucene_imp();

        return LS.getResults(query);
    }
}
