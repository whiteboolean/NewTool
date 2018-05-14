package com.mtool.toolslib.base.view.custom.toolbar

import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.mtool.toolslib.base.R

/**
 * 所有项目上方的标题栏
 * Created by buck on 2017/10/2.
 */
class ToolBar : RelativeLayout {

    val leftLayout: LinearLayout by lazy(mode = LazyThreadSafetyMode.NONE) { findViewById<LinearLayout>(R.id.left_layout) }
    val middleLayout: RelativeLayout by lazy(mode = LazyThreadSafetyMode.NONE) { findViewById<RelativeLayout>(R.id.middle_layout) }
    val rightLayout: LinearLayout by lazy(mode = LazyThreadSafetyMode.NONE) { findViewById<LinearLayout>(R.id.right_layout) }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /***************** left *******************/

    fun addLeftView(@LayoutRes layout: Int, click: ((View) -> Unit)? = null)
            = inflaterAndAddView(leftLayout, layout, click)

    fun addLeftView(view: View, params: LinearLayout.LayoutParams, click: ((View) -> Unit)? = null)
            = addView(leftLayout, view, params, click)

    fun addDefaultLeftImageView(@DrawableRes res: Int, @IdRes id: Int, padding: Int, params: LinearLayout.LayoutParams, click: ((View) -> Unit)? = null)
            = generateDefaultImageView(res, id, padding, click).also { addLeftView(it, params, click) }

    /******************* middle *******************/

    fun addMiddleView(@LayoutRes layout: Int, click: ((View) -> Unit)? = null)
            = inflaterAndAddView(middleLayout, layout, click)

    fun addMiddleView(view: View, params: RelativeLayout.LayoutParams, click: ((View) -> Unit)? = null)
            = addView(middleLayout, view, params, click)

    fun addDefaultStyleTitle(titleTxt: CharSequence?, txtSize: Int, txtColor: Int, click: ((View) -> Unit)? = null) {
        val params = relativeLayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)

        val title = generateDefaultTitle(txtSize, txtColor)
        title.id = R.id.title
        title.text = titleTxt
        addMiddleView(title, params, click)
    }

    /******************* right *******************/

    fun addRightView(@LayoutRes layout: Int, click: ((View) -> Unit)? = null)
            = inflaterAndAddView(rightLayout, layout, click)

    fun addRightView(view: View, params: LinearLayout.LayoutParams, click: ((View) -> Unit)? = null)
            = addView(rightLayout, view, params, click)

    fun addDefaultRightImageView(@DrawableRes res: Int, @IdRes id: Int, padding: Int, params: LinearLayout.LayoutParams, click: ((View) -> Unit)? = null)
            = generateDefaultImageView(res, id, padding, click).also { addRightView(it, params, click) }

    fun addRigtViewWithTitle(titleTxt: CharSequence?, txtSize: Int, txtColor: Int, click: ((View) -> Unit)? = null) {

        val params = linearLayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        params.gravity = Gravity.CENTER
        params.rightMargin = 25

        val title = generateDefaultTitle(txtSize, txtColor)
        title.id = R.id.title
        title.text = titleTxt
        addRightView(title, params, click)
    }

    /****************** other ********************/

    private fun inflaterAndAddView(group: ViewGroup, @LayoutRes res: Int, click: ((View) -> Unit)? = null): View {
        LayoutInflater.from(context).inflate(res, group, true)
        val view = group.getChildAt(group.childCount - 1)
        if (click != null) view.setOnClickListener(click)
        return view
    }

    private fun addView(group: ViewGroup, view: View, params: ViewGroup.LayoutParams? = null, click: ((View) -> Unit)? = null): View {
        group.addView(view, params)
        if (click != null) view.setOnClickListener(click)
        return view
    }

    private fun generateDefaultImageView(@DrawableRes res: Int, @IdRes id: Int, padding: Int, click: ((View) -> Unit)?) = ImageView(context).also {
        it.id = id
        it.setImageResource(res)
        it.setPadding(padding, padding, padding, padding)
        if (click != null) it.setOnClickListener(click)
    }

    private fun generateDefaultTitle(textSize: Int, @ColorInt color: Int) = TextView(context).also {
        it.textSize = textSize.toFloat()
        it.setTextColor(color)
    }

    companion object {
        val MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT
        val WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT

        fun linearLayoutParams(width: Int, height: Int): LinearLayout.LayoutParams {
            return LinearLayout.LayoutParams(width, height)
        }

        fun relativeLayoutParams(width: Int, height: Int): RelativeLayout.LayoutParams {
            return RelativeLayout.LayoutParams(width, height)
        }
    }
}
