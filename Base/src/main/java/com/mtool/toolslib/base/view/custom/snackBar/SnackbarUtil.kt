package com.mtool.toolslib.base.view.custom.snackBar

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by JayCruz on 2017/11/12.
 */
class SnackbarUtil {

    fun showSnackbar(v: View?, snackbarText: String?) {
        if (v == null || snackbarText == null) {
            return
        }
        Snackbar.make(v, snackbarText, Snackbar.LENGTH_LONG).show()
    }
}
