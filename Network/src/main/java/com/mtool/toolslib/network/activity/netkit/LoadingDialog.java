package com.mtool.toolslib.network.activity.netkit;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.WindowManager;

import com.mtool.toolslib.network.R;


/**
 * Created by buck on 2017/10/3.
 */

public class LoadingDialog extends Dialog {

    private int mGravity;

    public LoadingDialog(@NonNull Context context) {
        this(context, R.style.dialog_full_screen);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setGravity(int gravity) {
        mGravity = gravity;
    }

    public void show1() {
        WindowManager.LayoutParams attr = getWindow().getAttributes();
        attr.width = WindowManager.LayoutParams.MATCH_PARENT;
        attr.gravity = Gravity.BOTTOM;
        getWindow().setWindowAnimations(R.style.dialog_anima_default);
        super.show();
    }

    @Override
    public void show() {
        try {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.width = WindowManager.LayoutParams.MATCH_PARENT;
            super.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showWithoutAnima() {
        try {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.width = WindowManager.LayoutParams.MATCH_PARENT;
            attr.height = WindowManager.LayoutParams.MATCH_PARENT;
            super.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
