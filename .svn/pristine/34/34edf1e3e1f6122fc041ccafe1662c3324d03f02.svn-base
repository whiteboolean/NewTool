package com.mtool.toolslib.utils

import android.os.CountDownTimer
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

/**
 * Created by JayCruz on 2018/3/22.
 */
class CountDownTimerUtil {
    companion object {

        fun initCountDownTimer(sec: Long, tickListener: (Long) -> Unit, finishListener: () -> Unit): CountDownTimer {

            return object : CountDownTimer(sec * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    tickListener.invoke(millisUntilFinished)
                }

                override fun onFinish() {
                    finishListener.invoke()
                }
            }
        }

        fun initCountDownTimerWithRx(sec: Long, tickListener: (Long) -> Unit, finishListener: () -> Unit) {

            io.reactivex.Observable
                    .timer(sec, TimeUnit.SECONDS)
                    .subscribeBy(
                            onNext = { result -> tickListener.invoke(result) },
                            onError = { error -> },
                            onComplete = { finishListener.invoke() }
                    )
        }
    }
}