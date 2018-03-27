package com.mcc.l30n.mcc.utils;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;

/**
 * Created by leon on 27/3/18.
 */

public class AccessPermission {
    public static void accessPermission(Activity activity) {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(activity, permissions, 404);
    }
}
