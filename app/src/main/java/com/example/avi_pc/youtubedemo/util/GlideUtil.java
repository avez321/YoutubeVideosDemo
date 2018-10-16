package com.example.avi_pc.youtubedemo.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtil {

    public static void showImage(ImageView imageView, String imgUrl, int placeHolder) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(placeHolder);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        requestOptions.fitCenter();
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .apply(requestOptions)
                .into(imageView);
    }
}
