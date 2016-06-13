package com.codepath.apps.simpletweets.net;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "qi0OqjEWFcpsYjG7aqhxXDo5g";       // Change this
    public static final String REST_CONSUMER_SECRET = "vxuyLqmYDErW0XtfBO6gGlaZb2yybAufFRaIUdnOLJyuXYbldS"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://sscodepathtweets"; // Change this (here and in manifest)

    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    public void getHomeTimeline(long maxId, AsyncHttpResponseHandler handler) {
        getTimeline("statuses/home_timeline.json", maxId, handler);
    }

    public void getMentionsTimeline(long maxId, AsyncHttpResponseHandler handler) {
        getTimeline("statuses/mentions_timeline.json", maxId, handler);
    }

    public void getUserTimeline(String screenName, long maxId, AsyncHttpResponseHandler handler) {
        String apiPath = "statuses/user_timeline.json";
        RequestParams params = new RequestParams();
        params.put("count", 50);
        if (screenName != null) {
            params.put("screen_name", screenName);
        }
        if (maxId == Long.MAX_VALUE) {
            params.put("since_id", 1);
        }
        else {
            params.put("max_id", maxId);
        }
        getClient().get(getApiUrl(apiPath), params, handler);
    }

    public void getTimeline(String apiPath, long maxId, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("count", 50);
        if (maxId == Long.MAX_VALUE) {
            params.put("since_id", 1);
        }
        else {
            params.put("max_id", maxId);
        }
        getClient().get(getApiUrl(apiPath), params, handler);
    }

    public void postTweet(String body, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", body);
        getClient().post(apiUrl, params, handler);
    }

    public void getCurrentUser(AsyncHttpResponseHandler handler) {
        getUser(null, handler);
    }

    public void getUser(String screenName, AsyncHttpResponseHandler handler) {
        String apiUrl;
        RequestParams params = null;
        if (screenName == null) {
            apiUrl = getApiUrl("account/verify_credentials.json");
        }
        else {
            apiUrl = getApiUrl("users/show.json");
            params = new RequestParams();
            params.put("screen_name", screenName);
        }
        getClient().get(apiUrl, params, handler);
    }
}