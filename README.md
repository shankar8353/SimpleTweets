# Simple Twitter Client

Extend the Twitter client from previous project to use a tabbed interface and fragments.

Submitted by: Shankar Sundaram

Time spent: 12 hours spent in total

## User Stories

The following **required** functionality is completed:

* Includes all required user stories from Week 3 Twitter Client
* User can switch between Timeline and Mention views using tabs
  * User can view their home timeline tweets.
  * User can view the recent mentions of their username.
* User can navigate to view their own profile
  * User can see picture, tagline, # of followers, # of following, and tweets on their profile.
* User can click on the profile image in any tweet to see another user's profile.
  * User can see picture, tagline, # of followers, # of following, and tweets of clicked user.
  * Profile view should include that user's timeline
* User can infinitely paginate any of these timelines (home, mentions, user) by scrolling to the bottom


The following **optional** features are implemented:

* [ ] Advanced: Robust error handling, check if internet is available, handle error cases, network failures
* [ ] Advanced: When a network request is sent, user sees an indeterminate progress indicator
* [ ] Advanced: User can "reply" to any tweet on their home timeline
* [ ] The user that wrote the original tweet is automatically "@" replied in compose
* [x] Advanced: User can click on a tweet to be taken to a "detail view" of that tweet
* [ ] Advanced: User can take favorite (and unfavorite) or retweet actions on a tweet
* [x] Advanced: Improve the user interface and theme the app to feel "twitter branded"
* [ ] Advanced: User can search for tweets matching a particular query and see results
* [x] Bonus: Use Parcelable instead of Serializable using the popular Parceler library
* [x] Bonus: Apply the popular Butterknife annotation library to reduce view boilerplate
* [ ] Bonus: User can view their Twitter direct messages (and/or send new ones)


## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='Demo.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

* Tried to show the link in a WebView in the list view but it did not work well. The performance was terrible and only the newspaper name could fit in the specified height. 

## License

    Copyright [2016] [Shankar Sundaram]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

