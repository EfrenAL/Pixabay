package com.efren.pixabay.view.images;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.efren.pixabay.R;
import com.efren.pixabay.model.Image;
import com.efren.pixabay.view.adapters.TagAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by efren.lamolda on 13.08.18.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private ArrayList<Image> images;
    private Context context;
    private OnItemClickListener listener;

    interface OnItemClickListener {
        void onItemClick(Integer pos);
    }

    public ImageAdapter(ArrayList<Image> images, Context context, OnItemClickListener listener) {
        this.images = images;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.bind(images.get(position), context, listener, position);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false), context);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_picture) ImageView imageView;
    @BindView(R.id.rv_tags) RecyclerView tags;
    @BindView(R.id.tv_user) TextView userName;
    @BindView(R.id.rl_card) RelativeLayout card;

    public ImageViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        tags.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    public void bind(Image image, Context context, ImageAdapter.OnItemClickListener listener, int position) {
        Glide.with(context)
                .load(image.getPreviewURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        RecyclerView.Adapter adapter = new TagAdapter(image.getTags(), context);
        tags.setAdapter(adapter);
        userName.setText(image.getUser());
        card.setOnClickListener(it -> listener.onItemClick(position));
    }

}
