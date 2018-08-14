package com.efren.pixabay.view.images;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.support.design.widget.Snackbar;
import com.efren.pixabay.R;
import com.efren.pixabay.model.Image;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by efren.lamolda on 07.08.18.
 */

public class ImageFragment extends Fragment{

    @Inject
    public ImageViewModelFactory imageViewModelFactory;
    private ImageViewModel viewModel;

    //UI Elements
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.li_main) LinearLayout linearLayout;
    @BindView(R.id.rv_images) RecyclerView recyclerView;
    private Snackbar errorSnackbar;
    private DataPassListener callback;

    public ImageFragment() {
    }

    public interface DataPassListener {
        void passData(Integer pos);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //configureDagger
        AndroidSupportInjection.inject(this);
        configureViewModel();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (DataPassListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement DataPassListener");
        }
    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(this, imageViewModelFactory).get(ImageViewModel.class);

        viewModel.init();
        viewModel.loadingVisibility.observe(this, visibility -> progressBar.setVisibility(visibility));
        viewModel.errorMessage.observe(this, error -> showError(error));
        viewModel.success.observe(this, success -> dismissError(success) );
        viewModel.images.observe(this, images -> showImages(images));
    }

    private void showImages(ArrayList<Image> images) {
        RecyclerView.Adapter adapter = new ImageAdapter(images, getContext(), clickHandler());
        recyclerView.setAdapter(adapter);
    }

    private ImageAdapter.OnItemClickListener clickHandler(){
        return (pos) -> callback.passData(pos);
    }

    private void showError(String error) {
        errorSnackbar =  Snackbar.make(linearLayout, error, Snackbar.LENGTH_INDEFINITE);
    }

    private void dismissError(Boolean sucess) {
        //ToDo
    }

    public void requestImages(String query){
        viewModel.getImages(query);
    }
}

