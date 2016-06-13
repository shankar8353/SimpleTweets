package com.codepath.apps.simpletweets.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.TwitterApplication;
import com.codepath.apps.simpletweets.activities.ProfileActivity;
import com.codepath.apps.simpletweets.activities.TweetDetailActivity;
import com.codepath.apps.simpletweets.adapters.TweetsAdapter;
import com.codepath.apps.simpletweets.models.Tweet;
import com.codepath.apps.simpletweets.net.TwitterClient;
import com.codepath.apps.simpletweets.utils.EndlessRecyclerViewScrollListener;
import com.codepath.apps.simpletweets.utils.TwitterResponseMapper;
import com.loopj.android.http.TextHttpResponseHandler;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;

/**
 * Created by ssunda1 on 6/10/16.
 */
public abstract class TweetsListFragment extends Fragment {

    protected TwitterClient client;
    private TweetsAdapter adapter;
    private List<Tweet> tweets;

    protected long maxId = Long.MAX_VALUE;

    private Unbinder unbinder;
    @BindView(R.id.rvTweets) RecyclerView rvTweets;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        unbinder = ButterKnife.bind(this, view);

        rvTweets.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTweets.setLayoutManager(layoutManager);
        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadData();
            }
        });
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = TwitterApplication.getRestClient();
        tweets = new ArrayList<>();
        adapter = new TweetsAdapter(tweets);
        adapter.setOnItemClickListener(new TweetsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Tweet tweet = tweets.get(position);
                int id = itemView.getId();
                switch (id) {
                    case R.id.ivComposeProfile:
                    case R.id.tvComposeUser:
                    case R.id.tvComposeScreenName:
                        showProfile(itemView, tweet);
                        break;
                    default:
                        showDetail(itemView, tweet);
                        break;
                }
            }
        });

        loadData();
    }

    private void showDetail(View itemView, Tweet tweet) {
        Intent i = new Intent(itemView.getContext(), TweetDetailActivity.class);
        i.putExtra("tweet", Parcels.wrap(tweet));
        startActivity(i);
    }

    private void showProfile(View itemView, Tweet tweet) {
        String screenName = tweet.getUser().getScreenName();
        Intent i = new Intent(itemView.getContext(), ProfileActivity.class);
        i.putExtra("screenName", screenName);
        startActivity(i);
    }

    public void addTweet(Tweet tweet) {
        tweets.add(0, tweet);
        adapter.notifyDataSetChanged();
    }

    private void swipeRefresh() {
        maxId = Long.MAX_VALUE;
        loadData();
    }

    public abstract void loadData();

    private void addTweets(List<Tweet> results) {
        if (maxId == Long.MAX_VALUE) {
            tweets.clear();
            adapter.notifyDataSetChanged();
            swipeContainer.setRefreshing(false);
        }
        for (Tweet t : results) {
            if (t.getTweetId() < maxId) {
                maxId = t.getTweetId();
            }
        }
        maxId--;
        int currentSize = tweets.size();
        tweets.addAll(results);
        adapter.notifyItemRangeInserted(currentSize, tweets.size());
    }

    class TweetResponseHandler extends TextHttpResponseHandler {
        @Override
        public void onSuccess(int statusCode, Header[] headers, String response) {
            List<Tweet> results = TwitterResponseMapper.mapTweets(response);
            addTweets(results);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

        }
    }

}
