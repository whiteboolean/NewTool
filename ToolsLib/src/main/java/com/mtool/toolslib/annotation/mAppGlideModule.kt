package com.mtool.toolslib.annotation

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.LibraryGlideModule
import java.io.InputStream
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.caverock.androidsvg.SVG
import com.mtool.toolslib.utils.svgloader.SvgDecoder
import com.mtool.toolslib.utils.svgloader.SvgDrawableTranscoder


/***
 * Glide 4.0 新版
 * 目前支持线上SVG图片系统加载
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide,
                                    registry: Registry) {
        registry.register(SVG::class.java, PictureDrawable::class.java, SvgDrawableTranscoder())
                .append(InputStream::class.java, SVG::class.java, SvgDecoder())
    }

    // Disable manifest parsing to avoid adding similar modules twice.
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
//override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//    registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
//}