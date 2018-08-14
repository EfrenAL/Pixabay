package com.efren.pixabay.di.module;

import com.efren.pixabay.view.details.DetailsFragment;
import com.efren.pixabay.view.images.ImageFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by efren.lamolda on 07.08.18.
 */

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    public abstract ImageFragment contributeImageFragment();

    @ContributesAndroidInjector
    public abstract DetailsFragment contributeDetailsFragment();
}
