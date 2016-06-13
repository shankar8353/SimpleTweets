package com.codepath.apps.simpletweets.fragments;

import android.os.Bundle;

/**
 * Created by ssunda1 on 6/10/16.
 */
public class UserTimelineFragment extends TweetsListFragment {

    public static UserTimelineFragment newInstance(String screenName) {
        UserTimelineFragment fragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("screenName", screenName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void loadData() {
        Bundle args = getArguments();
        String screenName = args.getString("screenName");
        client.getUserTimeline(screenName, maxId, new TweetResponseHandler());
    }
}
