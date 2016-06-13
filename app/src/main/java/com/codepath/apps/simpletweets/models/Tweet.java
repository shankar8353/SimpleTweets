package com.codepath.apps.simpletweets.models;

import android.text.format.DateUtils;
import android.util.Log;

import org.parceler.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

//@Table(name = "Tweets")
@Parcel(analyze = {Tweet.class, User.class})
public class Tweet {
    //@Column(name = "tweetId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private long tweetId;

    //@Column(name = "body")
    private String body;

    //@Column(name = "timestamp")
    private String timestamp;

    private int retweetCount;

    private int favoriteCount;

    //@Column(name = "user")
    private User user;

    // Make sure to always define this constructor with no arguments
    public Tweet() {
        super();
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRelativeTimeAgo() {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(timestamp).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            return formatRelativeDate(relativeDate);
        }
        catch (ParseException e) {
            Log.e("ERROR", "Error parsing timestamp");
        }

        return relativeDate;
    }

    private String formatRelativeDate(String date) {
        String[] split = date.split(" ");
        if (split.length == 3) {
            String unit = split[1];
            if (unit.startsWith("second") || unit.startsWith("minute") || unit.startsWith("hour") || unit.startsWith("day")) {
                return split[0] + unit.charAt(0);
            }
        }
        return date;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
}
