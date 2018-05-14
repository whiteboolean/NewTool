package com.mtool.toolslib.view.custom.IconFont


import android.content.Context
import android.util.AttributeSet
import android.support.v7.widget.AppCompatTextView
import com.mtool.toolslib.app.AppTools


class IconFontForBase : AppCompatTextView {
    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        typeface = AppTools.getIconfontFromBase(context)
    }


}