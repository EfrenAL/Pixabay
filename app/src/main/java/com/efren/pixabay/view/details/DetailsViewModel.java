package com.efren.pixabay.view.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.efren.pixabay.model.Image;
import com.efren.pixabay.repositories.ImageRepository;
import javax.inject.Inject;

/**
 * Created by efren.lamolda on 13.08.18.
 */

public class DetailsViewModel extends ViewModel {

    public ImageRepository repo;
    public MutableLiveData<Image> image =  new MutableLiveData<>();
    public MutableLiveData<Integer> loadingVisibility = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<Boolean> success = new MutableLiveData<>();

    @Inject
    public DetailsViewModel(ImageRepository repo) {
        this.repo = repo;
        image = repo.image;
        loadingVisibility = repo.loadingVisibility;
        errorMessage = repo.errorMessage;
        success = repo.success;
    }

    public LiveData<Image> getImage(Integer index) {
        return repo.getImage(index);
    }
}
