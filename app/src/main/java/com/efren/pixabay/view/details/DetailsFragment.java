package com.efren.pixabay.view.details;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.efren.pixabay.R;
import com.efren.pixabay.base.Constants;
import com.efren.pixabay.model.Image;
import com.efren.pixabay.view.adapters.TagAdapter;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by efren.lamolda on 07.08.18.
 */

public class DetailsFragment extends Fragment {

    @Inject
    public DetailsViewModelFactory detailsViewModelFactory;
    private DetailsViewModel viewModel;

    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.iv_main_picture) ImageView picture;
    @BindView(R.id.rv_main_tags) RecyclerView tags;
    @BindView(R.id.tv_main_user) TextView userName;
    @BindView(R.id.tv_fav) TextView fav;
    @BindView(R.id.tv_comments) TextView comments;
    @BindView(R.id.tv_likes) TextView likes;

    public DetailsFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //configureDagger
        AndroidSupportInjection.inject(this);
        configureViewModel();
        tags.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_search);
        item.setVisible(false);
    }

    @Override
    public void onStart(){
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            viewModel.getImage(args.getInt(Constants.POSITION));
        }
    }

    private void configureViewModel() {
        viewModel = ViewModelProviders.of(this, detailsViewModelFactory).get(DetailsViewModel.class);

        viewModel.loadingVisibility.observe(this, visibility -> progressBar.setVisibility(visibility));
        viewModel.errorMessage.observe(this, error -> showError(error));
        viewModel.image.observe(this, images -> showImage(images));
    }

    private void showError(String error) {
        //ToDo show error message in Snackbar or Dialog
    }

    private void showImage(Image image) {

        Glide.with(getContext())
                .load(image.getLargeImageURL())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(picture);

        RecyclerView.Adapter adapter = new TagAdapter(image.getTags(), getContext());
        tags.setAdapter(adapter);

        userName.setText(image.getUser());
        fav.setText(image.getFavorites().toString());
        comments.setText(image.getComments().toString());
        likes.setText(image.getLikes().toString());
    }


}
