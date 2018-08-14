package com.efren.pixabay.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.view.View;

import com.efren.pixabay.base.Constants;
import com.efren.pixabay.base.PixabayApi;
import com.efren.pixabay.model.Image;
import com.efren.pixabay.model.ImageRequestResponse;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by efren.lamolda on 07.08.18.
 */

@Singleton
public class ImageRepository {

    private PixabayApi pixabayApi;

    public Disposable subscription;
    public MutableLiveData<ArrayList<Image>> images =  new MutableLiveData<>();
    public MutableLiveData<Image> image =  new MutableLiveData<>();
    public MutableLiveData<Integer> loadingVisibility = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<Boolean> success = new MutableLiveData<>();

    @Inject
    public ImageRepository(PixabayApi pixabayApi) {
        this.pixabayApi = pixabayApi;
    }

    public MutableLiveData<ArrayList<Image>> getImages(String query){

        query = query.replaceAll("\\s+","+");

        subscription = pixabayApi.getImages(Constants.API_KEY, query, Constants.API_IMAGE_TYPE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe( it -> onRetrieveStart())
                .doOnTerminate(() -> onRetrieveFinish())
                .subscribe( requestResponse ->onRetrieveSuccess(requestResponse),
                            error->onRetrieveError(error));
        return images;

    }

    private void onRetrieveError(Throwable it) {
        success.postValue(false);
        errorMessage.postValue(it.getMessage());
    }

    private void onRetrieveFinish() {
        loadingVisibility.postValue(View.GONE);
    }

    private void onRetrieveStart() {
        loadingVisibility.postValue(View.VISIBLE);
        success.postValue(true);
    }

    private void onRetrieveSuccess(ImageRequestResponse imageRequestResponse){
        images.postValue((ArrayList<Image>) imageRequestResponse.getHits());
        success.postValue(true);
    }

    public LiveData<Image> getImage(Integer index){
        image.postValue(images.getValue().get(index));
        return image;
    }
}
