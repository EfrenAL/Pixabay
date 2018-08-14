package com.efren.pixabay.view.images;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.efren.pixabay.base.Constants;
import com.efren.pixabay.model.Image;
import com.efren.pixabay.repositories.ImageRepository;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Created by efren.lamolda on 07.08.18.
 */

public class ImageViewModel extends ViewModel {

    public ImageRepository repo;
    public MutableLiveData<ArrayList<Image>> images =  new MutableLiveData<>();
    public MutableLiveData<Integer> loadingVisibility = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<Boolean> success = new MutableLiveData<>();

    @Inject
    public ImageViewModel(ImageRepository repo) {
        this.repo = repo;
        images = this.repo.images;
        loadingVisibility = this.repo.loadingVisibility;
        errorMessage = this.repo.errorMessage;
        success = this.repo.success;
    }

    public void init() {
        if (this.images.getValue() != null) {
            return;
        }
        repo.getImages(Constants.INITIAL_QUERY);
    }

    @Override
    public void onCleared(){
        super.onCleared();
        repo.subscription.dispose();
    }

    public MutableLiveData<ArrayList<Image>> getImages(String query){
        return repo.getImages(query);
    }

}