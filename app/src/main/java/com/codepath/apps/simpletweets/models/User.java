package com.codepath.apps.simpletweets.models;

import org.parceler.Parcel;

/**
 * Created by ssunda1 on 6/4/16.
 */
//@Table(name = "User")
@Parcel(analyze={User.class})
public class User  {

    //@Column(name = "userId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private long userId;

    //@Column(name = "name")
    private String name;

    //@Column(name = "screenName")
    private String screenName;

    //@Column(name = "profileImageUrl")
    private String profileImageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
