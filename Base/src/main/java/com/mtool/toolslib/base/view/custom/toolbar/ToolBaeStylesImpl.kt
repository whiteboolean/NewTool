package com.mtool.toolslib.base.view.custom.toolbar

import android.app.Activity
import android.graphics.Color
import android.support.annotation.LayoutRes
import android.view.View
import android.widget.*
import com.mtool.toolslib.base.R
import com.mtool.toolslib.base.core.ext.dp2px

/**
 * 所有项目上方的标题栏
 * Created by buck on 2017/10/2.
 */
class ToolBaeStylesImpl : ToolBarStyles {

    override var toolbar: ToolBar? = null
    override var act: Activity? = null

    override fun addMiddleLayout(@LayoutRes layout: Int, click: ((View) -> Unit)?) = toolbar?.addMiddleView(layout, click)!!

    override fun addMiddleLayout(view: View, params: RelativeLayout.LayoutParams) = toolbar?.addMiddleView(view, params)!!

    override fun addLeftLayout(@LayoutRes layout: Int, click: ((View) -> Unit)?) = toolbar?.addLeftView(layout, click)!!

    override fun addLeftLayout(view: View, lp: LinearLayout.LayoutParams, click: ((View) -> Unit)?) = toolbar?.addLeftView(view, lp, click)!!

    override fun addRightLayout(@LayoutRes layout: Int, click: ((View) -> Unit)?) = toolbar?.addRightView(layout, click)!!

    override fun addRightLayout(view: View, lp: LinearLayout.LayoutParams, click: ((View) -> Unit)?) = toolbar?.addRightView(view, lp, click)!!

    override fun addDefaultRightImageView(res: Int, id: Int, padding: Int, width: Int, height: Int, listener: ((View) -> Unit)?) = toolbar?.addDefaultRightImageView(res, id, padding, ToolBar.linearLayoutParams(width, height), listener)!!

    override fun addDefaultLeftImageView(res: Int, id: Int, padding: Int, width: Int, height: Int, listener: ((View) -> Unit)?) = toolbar?.addDefaultLeftImageView(res, id, padding, ToolBar.linearLayoutParams(width, height), listener)!!

    override fun setTitle(title: CharSequence?) = setTitle(title, 21, Color.WHITE)

    override fun setTitle(title: CharSequence?, txtSize: Int, color: Int) {
        val toolbar = this.toolbar ?: return

        val titleView = toolbar.middleLayout.findViewById<TextView>(R.id.title)

        if (titleView == null) {
            toolbar.addDefaultStyleTitle(title, txtSize, color)
        } else if (titleView is TextView) {
            titleView.text = title
            titleView.setTextColor(color)
            titleView.textSize = txtSize.toFloat()
        }
    }

    override fun setSupportBack(
            isSupport: Boolean,
            txt: CharSequence?, txtSize: Int,
            padding: Int,
            txtColor: Int,
            listener: ((View) -> Unit)?) {

        val toolbar = this.toolbar ?: return

        if (!isSupport) {
            val view = toolbar.findViewById<View>(R.id.back) ?: return
            toolbar.leftLayout.removeView(view)
            return
        }

        val click: (View) -> Unit = if (listener == null) {
            { act?.onBackPressed() }
        } else listener

        if (txt == null) {
            toolbar.addDefaultLeftImageView(
                    R.drawable.ic_action_back,
                    R.id.back,
                    padding,
                    ToolBar.linearLayoutParams(50.dp2px(), ToolBar.MATCH_PARENT), click)
        } else {
            val back = generateImageTxtStyleLayout(R.drawable.ic_action_back, txt, txtSize, txtColor)
            addLeftLayout(back, ToolBar.linearLayoutParams(ToolBar.WRAP_CONTENT, ToolBar.MATCH_PARENT), click)
        }
    }

    private fun generateImageTxtStyleLayout(res: Int, t: CharSequence, txtSize: Int, txtColor: Int) = act!!.layoutInflater.inflate(R.layout.layout_back_img_txt, null, false).also {
        (it.findViewById<ImageView>(R.id.img)).setImageResource(res)
        (it.findViewById<TextView>(R.id.txt)).let {
            it.textSize = txtSize.toFloat()
            it.setTextColor(txtColor)
            it.text = t
        }
    }
}