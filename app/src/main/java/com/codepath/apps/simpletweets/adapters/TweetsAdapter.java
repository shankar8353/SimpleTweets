package com.codepath.apps.simpletweets.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.simpletweets.R;
import com.codepath.apps.simpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ssunda1 on 6/4/16.
 */
public class TweetsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Tweet> tweets;
    private OnItemClickListener listener;

    public TweetsAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        RecyclerView.ViewHolder holder;
        View view = inflater.inflate(R.layout.item_tweet, parent, false);
        holder = new ViewHolder(view, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rvHolder, int position) {
        Tweet tweet = tweets.get(position);
        ViewHolder holder = (ViewHolder) rvHolder;
        holder.tvUser.setText(tweet.getUser().getName());
        holder.tvScreenName.setText("@" + tweet.getUser().getScreenName());
        holder.tvRelTime.setText(tweet.getRelativeTimeAgo());
        holder.tvText.setText(tweet.getBody());
        Picasso.with(holder.ivProfile.getContext()).load(tweet.getUser().getProfileImageUrl()).into(holder.ivProfile);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivComposeProfile) ImageView ivProfile;
        @BindView(R.id.tvComposeUser) TextView tvUser;
        @BindView(R.id.tvComposeScreenName) TextView tvScreenName;
        @BindView(R.id.tvRelTime) TextView tvRelTime;
        @BindView(R.id.tvText) TextView tvText;
        private OnItemClickListener listener;

        public ViewHolder(View view, OnItemClickListener listener) {
            super(view);
            this.listener = listener;
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
}
