package com.mcc.l30n.mcc.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.mcc.l30n.mcc.utils.AccessPermission;

/**
 * Created by leon on 27/3/18.
 */

public class ImagePickerHelper {
    private Activity activity;
    private Context context;

    public ImagePickerHelper(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
        AccessPermission.accessPermission(activity);
    }

    public void pickerImage() {
        ImagePicker.create(activity)
                .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.
                .folderMode(true) // folder mode (false by default)
//                .toolbarFolderTitle("Folder") // folder selection title
                .toolbarImageTitle("Tap to select") // image selection title
                .toolbarArrowColor(Color.WHITE) // Toolbar 'up' arrow color
                .single() // single mode
//                .multi() // multi mode (default mode)
                .limit(1) // max images can be selected (99 by default)
                .showCamera(true) // show camera or not (true by default)
                .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
//                .origin(images) // original selected images, used in multi mode
//                .exclude(images) // exclude anything that in image.getPath()
//                .excludeFiles(files) // same as exclude but using ArrayList<File>
//                .theme(R.style.CustomImagePickerTheme) // must inherit ef_BaseTheme. please refer to sample
//                .enableLog(false) // disabling log
//                .imageLoader(new GrayscaleImageLoder()) // custom image loader, must be serializeable
                .start(); // start image picker activity with request code
    }
}
