package com.efren.pixabay.view.details;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by efren.lamolda on 13.08.18.
 */

public class DetailsViewModelFactory implements ViewModelProvider.Factory {


    public DetailsViewModel viewModel;

    @Inject
    public DetailsViewModelFactory(DetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailsViewModel.class))
            return (T) viewModel;
        throw new IllegalArgumentException("Unknown class name");
    }
}
