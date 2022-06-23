import { Component, OnInit } from '@angular/core';

import { TweetService } from './tweet.service';
import { Tweet } from './tweet';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  title = 'SE-Angular-FE';

  tweets: Tweet[];
  lastQuery: string;

  constructor(private tweetService: TweetService) {
    this.tweets = [];
    this.lastQuery = '';
  }

  ngOnInit() {

  }  

  search(query: string) {

    this.lastQuery = query;

    this.tweetService.getTweets(query)
        .subscribe(tweets => this.tweets = tweets);
  }

}
