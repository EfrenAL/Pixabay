package com.efren.pixabay.di.module;

import android.arch.lifecycle.ViewModelProvider;
import com.efren.pixabay.base.PixabayApi;
import com.efren.pixabay.repositories.ImageRepository;
import com.efren.pixabay.view.details.DetailsViewModelFactory;
import com.efren.pixabay.view.images.ImageViewModelFactory;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import static com.efren.pixabay.base.Constants.BASE_URL;

/**
 * Created by efren.lamolda on 07.08.18.
 */

@Module
public class NetworkModule {

    @Provides
    @Reusable
    public PixabayApi providePixabayApi(Retrofit retrofit) {
        return retrofit.create(PixabayApi.class);
    }

    @Provides
    @Reusable
    public Retrofit provideRetrofitInterface() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @Singleton
    public ImageRepository provideImageRepository(PixabayApi pixabayApi) {
        return new ImageRepository(pixabayApi);
    }

    @Provides
    public ViewModelProvider.Factory provideImageViewModelFactory(ImageViewModelFactory imageViewModelFactory){ return imageViewModelFactory;}

    @Provides
    public ViewModelProvider.Factory provideDetailsViewModelFactory(DetailsViewModelFactory detailsViewModelFactory){ return detailsViewModelFactory;}
}
