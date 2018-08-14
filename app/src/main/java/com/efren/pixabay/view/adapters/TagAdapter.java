package com.efren.pixabay.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.efren.pixabay.R;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by efren.lamolda on 13.08.18.
 */

public class TagAdapter extends RecyclerView.Adapter<TagHolder> {

    private List<String> tags;
    private Context context;

    public TagAdapter(String tagsString, Context context) {

        tagsString = tagsString.replace(",", " ");
        List<String> list = Arrays.asList(tagsString.split("\\s+"));

        this.tags = list;
        this.context = context;
    }

    @Override
    public TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TagHolder(LayoutInflater.from(context).inflate(R.layout.item_chip, parent, false));
    }

    @Override
    public void onBindViewHolder(TagHolder holder, int position) {
        holder.bind(tags.get(position));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }
}

class TagHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_chip) TextView chip;

    public TagHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String tag){
        chip.setText(tag);
    }
}
