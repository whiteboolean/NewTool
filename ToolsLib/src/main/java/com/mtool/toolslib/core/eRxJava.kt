package com.mtool.toolslib.core

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by JayCruz on 2018/1/26.
 */

fun <T> Observable<T>.ioInIoOut(): Observable<T> {
    return subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.schedulers.Schedulers.io())
}
fun <T> Observable<T>.ioInAndroidOut(): Observable<T> {
    return subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
}
fun <T> Observable<T>.computInAndroidOut(): Observable<T> {
    return subscribeOn(io.reactivex.schedulers.Schedulers.computation())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.androidInAndroidOut(): Observable<T> {
    return subscribeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.debounceForEditText(): Observable<T> {
    return debounce(500, TimeUnit.MILLISECONDS)
}

fun <T> Observable<T>.debounceForButton(): Observable<T> {
    return debounce(1, TimeUnit.SECONDS)
}

fun <T> Observable<T>.debounceForIO(): Observable<T> {
    return debounce(100, TimeUnit.MILLISECONDS)
}
