package com.mtool.toolslib.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

/***
 * 复制工具类
 */
public class CopyUtil {
    public static void setCopy(Activity act, String str) {
        try {
            ClipboardManager clipboardManager = (ClipboardManager) act.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData cp = ClipData.newPlainText("剪贴版", str);
            clipboardManager.setPrimaryClip(cp);  // 将内容set到剪贴板

        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("CopyUtil", "Exception");
        }
    }

    public static String getCopy(Activity act) {
        try {
            ClipboardManager clipboardManager = (ClipboardManager) act.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboardManager.hasPrimaryClip()) {
                return clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();  // 获取内容
            } else {
                return "";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("CopyUtil", "Exception");
            return "";
        }
    }
}
