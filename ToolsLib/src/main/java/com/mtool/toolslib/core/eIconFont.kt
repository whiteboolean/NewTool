package com.mtool.toolslib.core

import android.text.Html
import android.text.Spanned

/**
 * Created by JayCruz on 2018/3/14.
 */
/***
 * 将 字串CODE 改成 ICON FONT
 *
 */
fun String.castToIconFontText(): Spanned {
    return Html.fromHtml(this)
}