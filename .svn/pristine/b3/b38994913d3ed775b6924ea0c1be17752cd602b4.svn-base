package com.mtool.toolslib.view.custom.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.IntRange;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;


/**
 * Created by gerry on 2017/6/29.
 */

public class BlurTransformation extends BitmapTransformation {

    private Context context;
    private int mRadius;

    public BlurTransformation(Context context, @IntRange(from = 1, to = 25) int radius) {
        super(context);
        this.context = context;
        this.mRadius = radius;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return RenderScriptGaussianBlur.doBlur(toTransform, mRadius, false);
    }

//    @Override
//    public String getId() {
//        return "blur" + mRadius; // todo
//    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
