package com.codepath.apps.simpletweets.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivDetailProfile) ImageView ivProfile;
    @BindView(R.id.tvDetailUser) TextView tvUser;
    @BindView(R.id.tvDetailScreenName) TextView tvScreenName;
    @BindView(R.id.tvDetailBody) TextView tvText;
    @BindView(R.id.tvRetweets) TextView tvRetweets;
    @BindView(R.id.tvFavorites) TextView tvFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Tweet tweet = Parcels.unwrap(intent.getParcelableExtra("tweet"));

        tvUser.setText(tweet.getUser().getName());
        tvScreenName.setText("@" + tweet.getUser().getScreenName());
        tvText.setText(tweet.getBody());
        tvRetweets.setText(String.valueOf(tweet.getRetweetCount()));
        tvFavorites.setText(String.valueOf(tweet.getFavoriteCount()));
        Picasso.with(ivProfile.getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfile);
    }
}
