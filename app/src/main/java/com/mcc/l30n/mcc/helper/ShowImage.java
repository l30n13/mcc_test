package com.mcc.l30n.mcc.helper;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.mcc.l30n.mcc.R;
import com.mcc.l30n.mcc.databinding.ItemImageBinding;

/**
 * Created by leon on 27/3/18.
 */

public class ShowImage implements View.OnClickListener {
    private ItemImageBinding binding;
    private Context context;
    private AlertDialog alertDialog;
    private String image;

    public ShowImage(Context context, String image) {
        this.context = context;
        this.alertDialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light).create();
        this.alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.image = image;
    }

    public void showDialog() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_image, null, false);

        Glide.with(context)
                .load(image)
                .thumbnail(0.5f)
                .into(binding.ivImage);

        binding.ivClose.setOnClickListener(this);
        alertDialog.setView(binding.getRoot());
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                alertDialog.dismiss();
                break;
        }
    }
}
