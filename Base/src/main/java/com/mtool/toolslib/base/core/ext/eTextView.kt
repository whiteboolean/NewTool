package com.mtool.toolslib.base.core.ext

import android.graphics.Paint
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.widget.TextView

/**
 * Created by JayCruz on 2017/10/22.
 */

fun TextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}
fun TextView.deleteLine() {
    paint.flags = paint.flags or Paint.STRIKE_THRU_TEXT_FLAG
    paint.isAntiAlias = true
}

fun TextView.bold() {
    paint.isFakeBoldText = true
    paint.isAntiAlias = true
}

fun String.spannableSize(textSize: Int, isDip: Boolean, start: Int, end: Int): SpannableString {
    val sp = SpannableString(this)
    sp.setSpan(AbsoluteSizeSpan(textSize, isDip), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    return sp
}

fun String.spannableBold( start: Int, end: Int): SpannableString {
    val sp = SpannableString(this)
    sp.setSpan(StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    return sp
}
fun TextView.setColorOfSubstring(substring: String, color: Int) {
    try {
        val spannable = android.text.SpannableString(text)
        val start = text.indexOf(substring)
        spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, color)), start, start + substring.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = spannable
    } catch (e: Exception) {
        Log.d("ViewExtensions",  "exception in setColorOfSubstring, text=$text, substring=$substring", e)
    }
}

fun TextView.font(font: String) {
    typeface = Typeface.createFromAsset(context.assets, "fonts/$font.ttf")
}

fun TextView.setDrawableLeft(drawable: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0)
}