
package com.codepath.apps.simpletweets.net;

import com.google.gson.annotations.SerializedName;

public class TweetUser {

    @SerializedName("id")
    public long id;

    @SerializedName("id_str")
    public String idStr;

    @SerializedName("name")
    public String name;

    @SerializedName("screen_name")
    public String screenName;

    @SerializedName("location")
    public String location;

    @SerializedName("description")
    public String description;

    @SerializedName("url")
    public String url;

    @SerializedName("followers_count")
    public int followersCount;

    @SerializedName("friends_count")
    public int friendsCount;

    @SerializedName("listed_count")
    public int listedCount;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("favourites_count")
    public int favouritesCount;

    @SerializedName("profile_background_color")
    public String profileBackgroundColor;

    @SerializedName("profile_background_image_url")
    public String profileBackgroundImageUrl;

    @SerializedName("profile_background_image_url_https")
    public String profileBackgroundImageUrlHttps;

    @SerializedName("profile_background_tile")
    public boolean profileBackgroundTile;

    @SerializedName("profile_image_url")
    public String profileImageUrl;

    @SerializedName("profile_image_url_https")
    public String profileImageUrlHttps;

    @SerializedName("profile_link_color")
    public String profileLinkColor;

    @SerializedName("profile_sidebar_border_color")
    public String profileSidebarBorderColor;

    @SerializedName("profile_sidebar_fill_color")
    public String profileSidebarFillColor;

    @SerializedName("profile_text_color")
    public String profileTextColor;

    @SerializedName("profile_use_background_image")
    public boolean profileUseBackgroundImage;

    @SerializedName("has_extended_profile")
    public boolean hasExtendedProfile;

    @SerializedName("default_profile")
    public boolean defaultProfile;

    @SerializedName("default_profile_image")
    public boolean defaultProfileImage;

    @SerializedName("following")
    public boolean following;

    @SerializedName("follow_request_sent")
    public boolean followRequestSent;

    @SerializedName("notifications")
    public boolean notifications;

}
