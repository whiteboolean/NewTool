package com.mtool.toolslib.view.custom


import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.mtool.toolslib.R
import org.jetbrains.anko.find

/***
 * 无记录 和 系统状态异常显示画面
 * */
class StatusView : FrameLayout {
    /**
     * The empty state
     */
    val TYPE_EMPTY = 1
    /**
     * The loading state
     */
    val TYPE_LOADING = 2
    /**
     * The error state
     */
    val TYPE_ERROR = 3

    enum class currentStatus constructor(val value: Int, val title: String, val content: String, val image: Int) {
        TYPE_EMPTY(0, "尚无记录", "快去添加一些记录吧！", R.drawable.ic_status_nodata),
        TYPE_NETWORK(1, "Opps", "请检查您的连线状态！", R.drawable.ic_status_nowifi),
        TYPE_NONE(2, "", "", R.drawable.ic_status_nowifi);

        companion object {
            fun getStatus(num: Int): currentStatus {
                values().forEach {
                    if (it.value == num) {
                        return it
                    }
                }
                return TYPE_NONE
            }
        }
    }

    private var mTitleMessage: String = "哎呀！发生了一些错误"
    private var mContentMessage: String = "页面加载失败"
    private var mStatusPic: Int = R.drawable.ic_status_nodata
    private var refreshAction: () -> Unit = {
        Log.e("StatusView", "ERROR ==> " + "(StatusView:54)----->尚未实作下拉更新机制")
    }

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
    }

    fun setInitStatus(status: currentStatus) = also {
        this.mContentMessage = status.content
        this.mTitleMessage = status.title
        this.mStatusPic = status.image
    }


    fun setTitleMessage(mTitleMessage: String) = also {
        this.mTitleMessage = mTitleMessage
    }

    fun setContentMessage(mContentMessage: String) = also {
        this.mContentMessage = mContentMessage
    }

    fun setRefreshAction(action: () -> Unit) = also {
        this.refreshAction = action
    }

    fun setImagePic(@DrawableRes res: Int) = also {
        this.mStatusPic = res
    }

    fun showView() {

        val view = mInflater.inflate(R.layout.view_state_content, null)
        val title = view.find<TextView>(R.id.tx_status_title)
        val content = view.find<TextView>(R.id.tx_status_content)
        val srl = view.find<SwipeRefreshLayout>(R.id.srl)
        val pic = view.find<ImageView>(R.id.iv_status_pic)

        pic.setImageResource(mStatusPic)
        srl.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccent))
        srl.setOnRefreshListener {
            refreshAction.invoke()
            srl.isRefreshing = false
        }

        title.text = mTitleMessage
        content.text = mContentMessage

        addView(view)
    }

    private lateinit var mInflater: LayoutInflater
    private fun initView() {
        mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

}