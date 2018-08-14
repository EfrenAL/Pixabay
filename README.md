# Pixabay

Android Application which uses the [PixaBay](https://pixabay.com/en/) Api to search for Images.

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
 
 
