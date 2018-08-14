package com.efren.pixabay;

import android.app.Activity;
import android.app.Application;
import com.efren.pixabay.di.component.DaggerAppComponent;
import com.efren.pixabay.di.module.AppModule;
import com.efren.pixabay.di.module.NetworkModule;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by efren.lamolda on 07.08.18.
 */

public class PixabayApplication extends Application implements HasActivityInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
