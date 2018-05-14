package com.mtool.toolslib.view.custom.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * Created by gerry on 2017/6/29.
 */

public class LumTransformation extends BitmapTransformation {

    private float lum;

    public LumTransformation(Context context, float lum) {
        super(context);
        this.lum = lum;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return ImageEffect(toTransform, lum);
    }

//    @Override
//    public String getId() {
//        return "lum" + lum;
//    }

    /**
     * @param bm
     * @param lum 亮度
     * @return
     */
    public static Bitmap ImageEffect(Bitmap bm, float lum) {
        Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);
        ColorMatrix ImageMatrix = new ColorMatrix();
        ImageMatrix.postConcat(lumMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(ImageMatrix));
        canvas.drawBitmap(bm, 0, 0, paint);
        return bitmap;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
