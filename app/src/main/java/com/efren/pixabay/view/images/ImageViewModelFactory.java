package com.efren.pixabay.view.images;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import javax.inject.Inject;

/**
 * Created by efren.lamolda on 07.08.18.
 */


public class ImageViewModelFactory implements ViewModelProvider.Factory {

    public ImageViewModel viewModel;

    @Inject
    public ImageViewModelFactory(ImageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ImageViewModel.class))
            return (T) viewModel;
        throw new IllegalArgumentException("Unknown class name");
    }
}
