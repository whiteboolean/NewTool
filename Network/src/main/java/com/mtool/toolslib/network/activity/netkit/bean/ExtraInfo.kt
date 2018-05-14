package com.mtool.toolslib.network.activity.netkit.bean

/**
 * Created by buck on 2017/10/25.
 */
class ExtraInfo(val info: HashMap<String, Any?> = HashMap()) {

    fun originData(data: Any?) {
        info[ORIGIN_DATA] = data
    }

    fun pagingInfo(pi: PagingInfo?) {
        info[PAGING_INFO] = pi
    }

    fun errorMsg(msg: String) {
        info[ERROR_MSG] = msg
    }

    fun statusCode(statusCode: Int) {
        info[STATUS_CODE] = statusCode
    }
//
//    fun responseBean(rb: com.wishland.modularization.lotterylib.model.net.BaseResponseBean?) {
//        info[RESPONSE_BEAN] = rb
//    }

    fun originData() = info[ORIGIN_DATA]

    fun pagingInfo() = info[PAGING_INFO] as PagingInfo

    fun statusCode() = info[STATUS_CODE] as Int

    fun errorMsg() = info[ERROR_MSG] as String
//
//    fun responseBean() = info[RESPONSE_BEAN] as? com.wishland.modularization.lotterylib.model.net.BaseResponseBean

    companion object {
        const val ORIGIN_DATA = "origin_data"
        const val PAGING_INFO = "paging_info"
        const val RESPONSE_BEAN = "response_bean"
        const val ERROR_MSG = "error_message"
        const val STATUS_CODE = "status_code"
    }
}
