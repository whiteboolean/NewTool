package com.mtool.toolslib.app

import android.content.Context
import android.graphics.Typeface
import com.mtool.toolslib.base.app.AppNetWork
import com.mtool.toolslib.utils.FontUtil

/**
 * Created by JayCruz on 2017/12/17.
 */
open class AppTools : AppNetWork() {

    override fun onCreate() {
        super.onCreate()
        context = this
        initFont()
    }


    companion object {
        private val ICON_FONT_APP_PATH = "icon_font/iconfont_app.ttf"
        private val ICON_FONT_BASE_PATH = "icon_font/iconfont_base.ttf"
        var iconfont: Typeface? = null
        /***
         * ICON FONT 字体图标系统
         * 用于底层 共用基础图标 如 左右上下 箭头等
         */
        fun getIconfontFromBase(context: Context): Typeface? {
            if (iconfont != null) {
                return iconfont
            } else {
                iconfont = Typeface.createFromAsset(context.assets, ICON_FONT_BASE_PATH)
            }
            return iconfont
        }

        /***
         * ICON FONT 字体图标系统
         * 用于项目图标 项目内所有素色SVG字体图标
         */
        fun getIconfontFromApp(context: Context): Typeface? {
            if (iconfont != null) {
                return iconfont
            } else {
                iconfont = Typeface.createFromAsset(context.assets, ICON_FONT_APP_PATH)
            }
            return iconfont
        }
    }

    /**
     * 設定初始字體
     */
    private fun initFont() {
        FontUtil.setDefaultFont(this, "DEFAULT", "fonts/gen_shin_gothic_regular.ttf")
        FontUtil.setDefaultFont(this, "MONOSPACE", "fonts/gen_shin_gothic_regular.ttf")
        FontUtil.setDefaultFont(this, "SERIF", "fonts/gen_shin_gothic_regular.ttf")
        FontUtil.setDefaultFont(this, "SANS_SERIF", "fonts/gen_shin_gothic_regular.ttf")


//        FontUtil.setDefaultFont(this, "DEFAULT", "fonts/msyh.ttf")
//        FontUtil.setDefaultFont(this, "MONOSPACE", "fonts/msyh.ttf")
//        FontUtil.setDefaultFont(this, "SERIF", "fonts/msyh.ttf")
//        FontUtil.setDefaultFont(this, "SANS_SERIF", "fonts/msyh.ttf")

//        FontUtil.setDefaultFont(this, "DEFAULT", "fonts/hiragino_sans_gb_w3.ttf")
//        FontUtil.setDefaultFont(this, "MONOSPACE", "fonts/hiragino_sans_gb_w3.ttf")
//        FontUtil.setDefaultFont(this, "SERIF", "fonts/hiragino_sans_gb_w3.ttf")
//        FontUtil.setDefaultFont(this, "SANS_SERIF", "fonts/hiragino_sans_gb_w3.ttf")
    }


}