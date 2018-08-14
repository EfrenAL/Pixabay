package com.efren.pixabay.di.component;

import com.efren.pixabay.PixabayApplication;
import com.efren.pixabay.di.module.AppModule;
import com.efren.pixabay.di.module.ActivityModule;
import com.efren.pixabay.di.module.FragmentModule;
import com.efren.pixabay.di.module.NetworkModule;
import javax.inject.Singleton;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by efren.lamolda on 07.08.18.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityModule.class, FragmentModule.class, AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(PixabayApplication app);
}
