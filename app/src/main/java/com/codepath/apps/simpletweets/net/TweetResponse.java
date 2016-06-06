
package com.codepath.apps.simpletweets.net;

import com.google.gson.annotations.SerializedName;

public class TweetResponse {

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("id_str")
    public String idStr;

    @SerializedName("text")
    public String text;

    @SerializedName("truncated")
    public boolean truncated;

    @SerializedName("entities")
    public Entities entities;

    @SerializedName("source")
    public String source;

    @SerializedName("user")
    public TweetUser user;

    @SerializedName("retweet_count")
    public int retweetCount;

    @SerializedName("favorite_count")
    public int favoriteCount;

}
