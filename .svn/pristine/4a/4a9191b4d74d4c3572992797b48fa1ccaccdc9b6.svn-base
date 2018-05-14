package com.mtool.toolslib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by admin on 2018/1/11.
 */

public class BgAlphaUtil {
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public static void setBackgroundAlpha(float bgAlpha, Context context) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) context).getWindow().setAttributes(lp);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public static void setBackgroundAlpha(float bgAlpha, Activity act) {
        WindowManager.LayoutParams lp = act.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        act.getWindow().setAttributes(lp);
    }
}
