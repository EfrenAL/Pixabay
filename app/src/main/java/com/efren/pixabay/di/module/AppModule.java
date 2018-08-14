package com.efren.pixabay.di.module;

import android.app.Application;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by efren.lamolda on 07.08.18.
 */
@Module
public class AppModule {
    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

}
