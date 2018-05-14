package com.mtool.toolslib.utils.svgloader

import android.app.Activity
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mtool.toolslib.R
import com.mtool.toolslib.annotation.GlideApp

/**
 * Created by JayCruz on 2018/3/22.
 * SVG LOADER
 * 线上矢量图 LOADER
 */
class SvgUtil {
    companion object {
        fun urlToSVG(activity: Activity, url: String, intoView: ImageView, @DrawableRes placeHolderRes: Int = R.drawable.ic_error1, @DrawableRes errorRes: Int = R.drawable.ic_error1) {
            val uri = Uri.parse(url)

            val requestBuilder: RequestBuilder<PictureDrawable> =
                    GlideApp.with(activity)
                            .`as`(PictureDrawable::class.java)
                            .placeholder(placeHolderRes)
                            .error(errorRes)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .listener(SvgSoftwareLayerSetter())

            requestBuilder
                    .load(uri)
                    .into(intoView)
        }
    }
}