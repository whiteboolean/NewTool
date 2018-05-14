package com.mtool.toolslib.view.custom.glide;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;


/**
 * Created by JayCruz on 2017/10/25.
 */

public class SimpleFullImageTarget extends ImageViewTarget<Drawable> {

    public SimpleFullImageTarget(ImageView view) {
        super(view);
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        view.setImageDrawable(placeholder);
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {
        view.setImageDrawable(placeholder);
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    @Override
    protected void setResource(Drawable resource) {
        view.setImageDrawable(resource);
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        view.setImageDrawable(errorDrawable);
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }


    @Override
    public void setDrawable(Drawable drawable) {
        view.setImageDrawable(drawable);
        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

}
