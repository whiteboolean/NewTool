package com.mtool.toolslib.base.view.custom.viewPager;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 多页面FRAGMENT 使永的VIEW PAGER
 * Created by buck on 2017/10/2.
 */
public class ProhibitScrollViewPager extends ViewPager {

    public ProhibitScrollViewPager(Context context) {
        super(context);
    }

    public ProhibitScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * viewpage的onTouchEvent屏蔽
     */
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }
}