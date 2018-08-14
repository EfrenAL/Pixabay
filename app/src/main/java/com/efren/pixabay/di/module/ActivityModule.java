package com.efren.pixabay.di.module;

import com.efren.pixabay.view.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by efren.lamolda on 07.08.18.
 */
@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
