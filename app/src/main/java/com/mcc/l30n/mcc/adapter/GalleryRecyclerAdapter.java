package com.mcc.l30n.mcc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mcc.l30n.mcc.R;
import com.mcc.l30n.mcc.helper.ShowImage;

/**
 * Created by leon on 27/3/18.
 */

public class GalleryRecyclerAdapter extends RecyclerView.Adapter<GalleryRecyclerAdapter.GalleryViewHolder> {
    private Context context;
    private String[] images;

    public GalleryRecyclerAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        String image = images[position];
        Glide
                .with(context)
                .load(image)
                .thumbnail(0.5f)
                .into(holder.iv_image);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class GalleryViewHolder extends ViewHolder {
        private ImageView iv_image;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowImage showImage = new ShowImage(context, images[getAdapterPosition()]);
                    showImage.showDialog();
                }
            });
        }
    }
}
