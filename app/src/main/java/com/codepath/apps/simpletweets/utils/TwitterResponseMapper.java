package com.codepath.apps.simpletweets.utils;

import android.support.annotation.NonNull;

import com.codepath.apps.simpletweets.models.Tweet;
import com.codepath.apps.simpletweets.models.User;
import com.codepath.apps.simpletweets.net.TweetResponse;
import com.codepath.apps.simpletweets.net.TweetUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssunda1 on 6/12/16.
 */
public class TwitterResponseMapper {

    // private constructor to prevent instantation
    private TwitterResponseMapper() {

    }

    public static User mapUser(String response) {
        Gson gson = new GsonBuilder().create();
        TweetUser tweetUser = gson.fromJson(response, TweetUser.class);
        User user = getUser(tweetUser);
        return user;
    }

    @NonNull
    private static User getUser(TweetUser tweetUser) {
        User user = new User();
        user.setUserId(Long.valueOf(tweetUser.idStr));
        user.setName(tweetUser.name);
        user.setScreenName(tweetUser.screenName);
        user.setProfileImageUrl(tweetUser.profileImageUrl);
        user.setFollowerCount(tweetUser.followersCount);
        user.setFollowingCount(tweetUser.friendsCount);
        user.setTagline(tweetUser.description);
        return user;
    }

    public static List<Tweet> mapTweets(String response) {
        Type collectionType = new TypeToken<List<TweetResponse>>() {}.getType();
        Gson gson = new GsonBuilder().create();
        List<TweetResponse> tweetResponseList = gson.fromJson(response, collectionType);
        List<Tweet> tweets = new ArrayList<>(tweetResponseList.size());
        for (TweetResponse t : tweetResponseList) {
            Tweet tweet = new Tweet();
            tweet.setBody(t.text);
            tweet.setTimestamp(t.createdAt);
            tweet.setTweetId(Long.valueOf(t.idStr));
            tweet.setRetweetCount(t.retweetCount);
            tweet.setFavoriteCount(t.favoriteCount);
            User user = getUser(t.user);
            tweet.setUser(user);
            tweets.add(tweet);
        }
        return tweets;
    }
}
