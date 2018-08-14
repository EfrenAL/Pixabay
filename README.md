# Pixabay

Android Application which uses the [PixaBay](https://pixabay.com/en/) Api to search for Images.

## Requirements

The user should be able to search for images entering one or more words in a text field. Display a list of results. Each entry should show:
• A thumbnail of the image
• The Pixabay user name
• A list of image’s tag

Clicking on a list item the app should open a dialog asking the user if he wants to see more details. In case of a
positive answer a new detail screen should be opened. The detail screen should contain:
• A bigger version of the image
• The name of the user
• A list of image’s tag
• The number of likes
• The number of favorites
• The number of comments

When the app starts it should trigger a search for the string “fruits”



## Implementation Details

The architecture decided to use is MVVM using Architecture Components. 

It allows to unit test every layer and keep view logic and buisines logic independent.

Project is using repository parttern and it is caching the data only in memory but to include other caching mechanisms as Room, Sqlite or Realm, should not a big effort due the layer independency.

Project is also using RxJava to request the data and Dagger2 to do the dependency injection. 

The next image represent the architecture used and the improvements that can be done to cache data

![alt text](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)


## UX/UI

Project is following Material Design guidelines. 

To display the images was used a RecyclerView Grid comosed by CardViews. Inside each cardview we have a another horizontal RecyclerView with the tags.

Main view:

![Screenshot1](mainView.png)


Details view:

![Screenshot2](detailView.png)

## Libraries

 1. RxJava
 2. Retrofit
 3. Glide
 4. ButterKnife
 5. Dagger2
 
 
