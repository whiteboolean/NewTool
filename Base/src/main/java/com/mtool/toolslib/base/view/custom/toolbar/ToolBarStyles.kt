package com.mtool.toolslib.base.view.custom.toolbar

import android.app.Activity
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.mtool.toolslib.base.core.ext.isNotNull
import com.mtool.toolslib.base.core.ext.setHeight
import com.mtool.toolslib.base.core.ext.setPaddingTop
import com.mtool.toolslib.base.core.ext.dp2px

/**
 * 所有项目上方的标题栏
 * Created by buck on 2017/10/2.
 *
 */
interface ToolBarStyles {

    var act: Activity?

    var toolbar: ToolBar?

    fun setTitle(title: CharSequence?)

    fun setTitle(title: CharSequence?, txtSize: Int, @ColorInt color: Int)

    fun addMiddleLayout(@LayoutRes layout: Int, click: ((View) -> Unit)? = null): View

    fun addMiddleLayout(view: View, params: RelativeLayout.LayoutParams = ToolBar.relativeLayoutParams(ToolBar.WRAP_CONTENT, ToolBar.WRAP_CONTENT)): View

    fun addLeftLayout(@LayoutRes layout: Int, click: ((View) -> Unit)? = null): View

    fun addLeftLayout(view: View, lp: LinearLayout.LayoutParams = ToolBar.linearLayoutParams(ToolBar.WRAP_CONTENT, ToolBar.WRAP_CONTENT), click: ((View) -> Unit)? = null): View

    fun addRightLayout(@LayoutRes layout: Int, click: ((View) -> Unit)? = null): View

    fun addRightLayout(view: View, lp: LinearLayout.LayoutParams = ToolBar.linearLayoutParams(ToolBar.WRAP_CONTENT, ToolBar.WRAP_CONTENT), click: ((View) -> Unit)? = null): View

    fun addDefaultRightImageView(
            @DrawableRes res: Int,
            @IdRes id: Int = 0,
            padding: Int = 0,
            width: Int = 50.dp2px(),
            height: Int = ViewGroup.LayoutParams.MATCH_PARENT,
            click: ((View) -> Unit)? = null): ImageView

    fun addDefaultLeftImageView(
            @DrawableRes res: Int,
            @IdRes id: Int = 0,
            padding: Int = 0,
            width: Int = 50.dp2px(),
            height: Int = ViewGroup.LayoutParams.MATCH_PARENT,
            click: ((View) -> Unit)? = null): ImageView

    /**
     * 图片加文字样式的的返回键
     */
    fun setSupportBack(
            isSupport: Boolean = true,
            txt: CharSequence? = null,
            txtSize: Int = 14,
            padding: Int = 0,
            @ColorInt txtColor: Int = Color.WHITE,
            listener: ((View) -> Unit)? = null)

    fun setFullScreenMode(isFullScreen: Boolean) {
        toolbar.isNotNull {
            if (isFullScreen) {
                it.setPaddingTop(20.dp2px())
                it.setHeight(60.dp2px())

            } else {
                it.setPaddingTop(0.dp2px())
                it.setHeight(50.dp2px())
            }
        }
    }

}