package com.mtool.toolslib.base.core.ext

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.widget.Toast
import com.mtool.toolslib.base.app.AppBase.Companion.context

/**
 * Created by JayCruz on 2017/10/14.
 */
fun Activity.toast(content:String){
    Toast.makeText(context,content,Toast.LENGTH_SHORT).show()
}