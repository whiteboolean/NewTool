package com.mtool.toolslib.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by White on 2018/5/3.
 */

public class JumpToUtil {

    /**
     * 手机里是否安装了微信
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 手机里是否安装了QQ
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 跳转到微信
     * @param context
     */
    public static void jumpToWeChat(Context context) {
        ComponentName component = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
        Intent intent = new Intent();
        intent.setComponent(component);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到支付宝
     * @param context
     */
    public static void jumpToAliPay(Context context){
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.eg.android.AlipayGphone");
        context.startActivity(intent);
    }
}
