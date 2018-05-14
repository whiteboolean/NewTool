package com.mtool.toolslib.network.activity.netkit.converter

import com.lzy.okgo.callback.Callback
import com.lzy.okgo.convert.FileConvert
import com.mtool.toolslib.network.activity.netkit.DowloadProgress
import com.mtool.toolslib.network.activity.netkit.bean.ExtraInfo
import okhttp3.Response
import java.io.File

/**
 * Created by buck on 2017/10/25.
 */

open class FileConvert(folder: String? = null, name: String? = null) : NetKitConvert<File>, DowloadProgress {

    val fv: FileConvert

    init {
        if (folder == null) {
            fv = FileConvert(name)
        } else {
            fv = FileConvert(folder, name)
        }
    }

    override fun setCallbcak(callback: Callback<*>) {
        fv.setCallback(callback as Callback<File>)
    }

    override fun convertResponse(response: Response, extraInfo: ExtraInfo): File? {
        val f = fv.convertResponse(response)
        extraInfo.originData(f)
        return f
    }
}