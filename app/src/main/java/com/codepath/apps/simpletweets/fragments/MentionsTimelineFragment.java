package com.codepath.apps.simpletweets.fragments;

/**
 * Created by ssunda1 on 6/10/16.
 */
public class MentionsTimelineFragment extends TweetsListFragment {

    @Override
    public void loadData() {
        client.getMentionsTimeline(maxId, new TweetResponseHandler());
    }
}
