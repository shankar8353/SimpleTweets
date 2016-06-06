
package com.codepath.apps.simpletweets.net;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Entities {

    @SerializedName("urls")
    public List<Url> urls = new ArrayList<>();

}
