package com.mtool.toolslib.view.activity

import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.*
import android.widget.Toast
import com.mtool.toolslib.R
import com.mtool.toolslib.base.core.ext.isNotNull
import com.mtool.toolslib.base.core.ext.setHeight
import com.mtool.toolslib.network.activity.fragment.BaseStylesFragment
import com.mtool.toolslib.network.activity.netkit.get
import kotlinx.android.synthetic.main.fgm_web_view.*
import org.jetbrains.anko.toast
import java.io.File
import java.util.*

/**
 * Created by JayCruz on 2017/12/4.
 */
class WebViewFGM : BaseStylesFragment() {
    private var url: String = ""
    private var isCustomerService = false
    private var webViewHeight = 0

    companion object {
        private val FILE_CHOOSER_RESULT_CODE = 10000
        val ACTION_HTML = "com.whh.www.html"
        val INTENT_URL = "url"
        val INTENT_TITLE = "title"
        val INTENT_SUB_TITLE = "subTitle"
        val INTENT_ID = "id"
        fun clearCookies(context: Context) {
            val cookieSyncMngr = CookieSyncManager.createInstance(context)
            val cookieManager = CookieManager.getInstance()
            cookieManager.removeAllCookie()
        }
    }

    fun initUrl(url: String) = also {
        this.url = url
    }

    fun isCustomerService(isCustomerService: Boolean) = also {
        this.isCustomerService = isCustomerService
    }

    fun setWebBiewHeight(webViewHeight:Int) = also{
        this.webViewHeight = webViewHeight
    }


    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(R.layout.fgm_web_view, container, false)

    override fun initDate() {
        initStatusBar()
        initWebView()
        initLongClick()
        loadURL()
    }

    fun loadURL() {
        if (webview != null) {
//            webview.setHeight(webViewHeight)
            webview.setProgressBar(pb)
            if (url.isNotEmpty()) {

                if ((!url.contains("https://") || url.contains("http://"))) {
                    url = "http://$url"
                }
                webview.loadUrl(url)
                webview.setErrorPage(bg_errorpage)
            } else {
                webview.setErrorPage(bg_errorpage)
            }
        }
    }


    override fun onDetach() {
        super.onDetach()
        try {
            webview.finish()
        } catch (ex: Exception) {
            print("WEBVIEW销毁")
        }
    }


    private fun initLongClick() {
        webview.setOnLongClickListener { v ->
            val result = (v as WebView).hitTestResult
            val type = result.type
            if (type == WebView.HitTestResult.IMAGE_TYPE) {
                setDataPopup(result.extra)
            }
            false
        }

    }

    protected fun initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        /***
         * 不知为何，搭载在FRAGMENT 内 会撑大到输入框隐藏在BAR 底下
         */
//        if (isCustomerService) {
//            AndroidBug54971Workaround.assistActivity(content)
//        }
    }

    private fun initWebView() {
        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (pb != null) {
                    pb.visibility = View.VISIBLE
                    pb.progress = progress
                    if (progress == 100)
                        pb.visibility = View.GONE
                }
            }

            override fun onJsAlert(view: WebView, url: String, message: String,
                                   result: JsResult): Boolean {
                //加这段可以证webview中的alert弹出来
                return super.onJsAlert(view, url, message, result)
            }

            // For Android < 3.0
            fun openFileChooser(valueCallback: ValueCallback<Uri>) {
                uploadTypeAboveL = false
                uploadMessage = valueCallback
                openImageChooserActivity()
            }

            // For Android  >= 3.0
            fun openFileChooser(valueCallback: ValueCallback<Uri>, acceptType: String) {
                uploadTypeAboveL = false
                uploadMessage = valueCallback
                openImageChooserActivity()
            }

            //For Android  >= 4.1
            fun openFileChooser(valueCallback: ValueCallback<Uri>, acceptType: String, capture: String) {
                uploadTypeAboveL = false
                uploadMessage = valueCallback
                openImageChooserActivity()
            }

            // For Android >= 5.0
            override fun onShowFileChooser(webView: WebView, filePathCallback: ValueCallback<Array<Uri>>, fileChooserParams: WebChromeClient.FileChooserParams): Boolean {
                uploadTypeAboveL = true
                uploadMessageAboveL = filePathCallback
                openImageChooserActivity()
                return true
            }
        }
    }

    /***
     * 客服系统图片上传机制 相关代码
     */

    fun setDataPopup(imgUrl: String) {
        val fileName1 = UUID.randomUUID().toString()
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("点击确定保存图片")
        builder.setPositiveButton("确定") { dialog, which -> saveImage(imgUrl, Environment.getExternalStorageDirectory().absolutePath + "/Download/", "$fileName1.png") }
        builder.setNeutralButton("取消") { dialog, which -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }

    private fun saveImage(imgUrl: String, destFileDir: String, destFileName: String) {
        get(imgUrl, "")
                .FileConvert(File::class, destFileDir, destFileName)
                .onSuccess { target, response, extraInfo, ignore ->
                    target.isNotNull {
                        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                        val uri = Uri.fromFile(it)
                        intent.data = uri
                        activity.sendBroadcast(intent)
                        activity.toast("保存成功")
                    }
                }
                .onError { ex, response, extraInfo, ignore ->
                    activity.toast("保存失败")
                }.end()

    }

    private fun openImageChooserActivity() {
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "image/*"
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE)
    }

    private val FILE_CHOOSER_RESULT_CODE = 10000
    private var uploadTypeAboveL = true
    private lateinit var uploadMessage: ValueCallback<Uri>
    private lateinit var uploadMessageAboveL: ValueCallback<Array<Uri>>
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            val result = if (data == null || resultCode != Activity.RESULT_OK) null else data.data
            if (uploadTypeAboveL) {
                onActivityResultAboveL(requestCode, resultCode, data)
            } else {
                uploadMessage.onReceiveValue(result)
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onActivityResultAboveL(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE)
            return
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                val dataString = intent.dataString
                val clipData = intent.clipData
                if (clipData != null) {
                    val count = clipData.itemCount
                    val results = arrayOf<Uri>()
                    for (i in 0 until clipData.itemCount) {
                        val item = clipData.getItemAt(i)
                        results[i] = item.uri
                    }
                    uploadMessageAboveL.onReceiveValue(results)
                }
                if (dataString != null) {
                    uploadMessageAboveL.onReceiveValue(arrayOf(Uri.parse(dataString)))
                }
            }
        }
    }
}