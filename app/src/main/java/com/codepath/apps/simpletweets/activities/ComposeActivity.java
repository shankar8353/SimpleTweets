package com.codepath.apps.simpletweets.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.TwitterApplication;
import com.codepath.apps.simpletweets.models.Tweet;
import com.codepath.apps.simpletweets.models.User;
import com.codepath.apps.simpletweets.net.TweetResponse;
import com.codepath.apps.simpletweets.net.TwitterClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    @BindView(R.id.ivComposeProfile) ImageView ivProfile;
    @BindView(R.id.tvComposeUser) TextView tvUser;
    @BindView(R.id.tvComposeScreenName) TextView tvScreenName;
    @BindView(R.id.etComposeBody) EditText etBody;
    @BindView(R.id.tvComposeCount) TextView tvCount;
    @BindView(R.id.btnComposeTweet) Button btnTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        ButterKnife.bind(this);

        User loggedInUser = TwitterApplication.getLoggedInUser();
        if (loggedInUser != null) {
            tvUser.setText(loggedInUser.getName());
            tvScreenName.setText("@" + loggedInUser.getScreenName());
            Picasso.with(ivProfile.getContext()).load(loggedInUser.getProfileImageUrl()).into(ivProfile);
        }

        etBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // nothing to do
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // nothing to do
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                int remaining = 140 - text.length();
                tvCount.setText(String.valueOf(remaining));
                if (remaining < 0) {
                    btnTweet.setEnabled(false);
                }
                else {
                    btnTweet.setEnabled(true);
                }
            }
        });
    }

    public void postTweet(View view) {
        TwitterClient client = TwitterApplication.getRestClient();
        String body = etBody.getText().toString();
        client.postTweet(body, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                Toast.makeText(ComposeActivity.this, "Tweet sent successfully", Toast.LENGTH_SHORT).show();
                handlePostTweetResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }
        });
    }

    void handlePostTweetResponse(String response) {
        Gson gson = new GsonBuilder().create();
        TweetResponse t = gson.fromJson(response, TweetResponse.class);
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

        Intent data = new Intent();
        data.putExtra("tweet", Parcels.wrap(tweet));
        setResult(RESULT_OK, data);
        finish();
    }
}
