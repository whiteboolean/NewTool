package com.mtool.toolslib.network.activity.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.lzy.okgo.OkGo
import com.mtool.toolslib.base.view.fragment.ToolBarFragment
import com.mtool.toolslib.network.activity.netkit.delegate.RequestDelegateController
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by buck on 2017/10/15.
 */

abstract class BaseStylesFragment : ToolBarFragment() {

    val disposable = CompositeDisposable()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requestDelegatesData()
    }


    override fun initDate() {

    }

    override fun getView(): View? {
        return super.getView()
    }

    override fun onDestroy() {
        super.onDestroy()
        OkGo.getInstance().cancelTag(this)
    }

    override fun onDetach() {
        super.onDetach()
        disposable.clear()
    }

    override fun onStop() {
        super.onStop()
    }


    private val requestDelegates = mutableListOf<RequestDelegateController>()


    protected fun useRequestDelegate() = RequestDelegateController().also { requestDelegates.add(it) }

    protected fun requestDelegatesData() {
        for (x in requestDelegates) {
            x.requestData()
        }
    }
}