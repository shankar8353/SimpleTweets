package com.codepath.apps.simpletweets.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.TwitterApplication;
import com.codepath.apps.simpletweets.adapters.TweetsAdapter;
import com.codepath.apps.simpletweets.models.Tweet;
import com.codepath.apps.simpletweets.models.User;
import com.codepath.apps.simpletweets.net.TweetResponse;
import com.codepath.apps.simpletweets.net.TwitterClient;
import com.codepath.apps.simpletweets.utils.EndlessRecyclerViewScrollListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import org.parceler.Parcels;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_COMPOSE = 1;

    private TwitterClient client;
    private TweetsAdapter adapter;
    private List<Tweet> tweets;
    private long maxId = Long.MAX_VALUE;
    @BindView(R.id.rvTweets) RecyclerView rvTweets;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBar menu = getSupportActionBar();
        menu.setLogo(R.mipmap.ic_launcher);
        menu.setDisplayUseLogoEnabled(true);

        client = TwitterApplication.getRestClient();
        tweets = new ArrayList<>();
        adapter = new TweetsAdapter(tweets);
        rvTweets.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvTweets.setLayoutManager(layoutManager);
        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                populateTimeline();
            }
        });
        populateTimeline();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                maxId = Long.MAX_VALUE;
                populateTimeline();
            }
        });
    }

    private void populateTimeline() {
        client.getHomeTimeline(maxId, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                Type collectionType = new TypeToken<List<TweetResponse>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                List<TweetResponse> tweetResponseList = gson.fromJson(response, collectionType);
                addTweets(tweetResponseList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

        });
    }

    private void addTweets(List<TweetResponse> tweetResponseList) {
        if (maxId == Long.MAX_VALUE) {
            tweets.clear();
            adapter.notifyDataSetChanged();
            swipeContainer.setRefreshing(false);
        }
        List<Tweet> results = new ArrayList<>(tweetResponseList.size());
        for (TweetResponse t : tweetResponseList) {
            Tweet tweet = new Tweet();
            tweet.setBody(t.text);
            tweet.setTimestamp(t.createdAt);
            tweet.setTweetId(Long.valueOf(t.idStr));
            User user = new User();
            user.setUserId(Long.valueOf(t.user.idStr));
            user.setName(t.user.name);
            user.setScreenName(t.user.screenName);
            user.setProfileImageUrl(t.user.profileImageUrl);
            tweet.setUser(user);
            results.add(tweet);

            if (tweet.getTweetId() < maxId) {
                maxId = tweet.getTweetId();
            }
        }
        maxId--;
        int currentSize = tweets.size();
        tweets.addAll(results);
        adapter.notifyItemRangeInserted(currentSize, tweets.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_timeline, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_compose:
                compose();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void compose() {
        Intent i = new Intent(this, ComposeActivity.class);
        startActivityForResult(i, REQUEST_CODE_COMPOSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_COMPOSE) {
            if (resultCode == RESULT_OK) {
                Tweet tweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
                tweets.add(0, tweet);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
