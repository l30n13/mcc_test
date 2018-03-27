package com.mcc.l30n.mcc.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mcc.l30n.mcc.BR;
import com.mcc.l30n.mcc.R;
import com.mcc.l30n.mcc.databinding.ItemProfileBinding;
import com.mcc.l30n.mcc.model.Profile;

import java.util.List;

/**
 * Created by leon on 27/3/18.
 */

public class AllProfileRecyclerAdapter extends RecyclerView.Adapter<AllProfileRecyclerAdapter.AllProfileViewHolder> {
    private Context context;
    private List<Profile> allProfile;

    public AllProfileRecyclerAdapter(Context context, List<Profile> allProfile) {
        this.context = context;
        this.allProfile = allProfile;
    }

    @Override
    public AllProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false);
        return new AllProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AllProfileViewHolder holder, int position) {
        Profile profile = allProfile.get(position);
        holder.getBinding().setVariable(BR.profile, profile);


        byte[] decodedString = Base64.decode(profile.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.iv_profile_image.setImageBitmap(decodedByte);
    }

    @Override
    public int getItemCount() {
        return allProfile.size();
    }

    public class AllProfileViewHolder extends RecyclerView.ViewHolder {
        private ItemProfileBinding binding;
        private ImageView iv_profile_image;

        public AllProfileViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            iv_profile_image = itemView.findViewById(R.id.iv_profile_image);
        }

        public ItemProfileBinding getBinding() {
            return binding;
        }
    }
}
