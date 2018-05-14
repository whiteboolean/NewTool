package com.mtool.toolslib.base.view.custom.refresh;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mtool.toolslib.base.R;

/***
 * 上拉加载更多的LAYOUT
 */
public class RefreshFooterLayout extends SpecifyHeightLayout implements Runnable, View.OnClickListener {

    public interface RefreshListener {
        void onRefresh();
    }

    private static final String TXT_LOADING = "正在加载中...";
    private static final String TXT_CLICK_RETRY = "加载失败，请点我重试";

    private boolean hasMore;
    private boolean isLoading;
    private boolean isError;

    private RefreshListener mListener;

    private View mChild;
    private ProgressBar mProgress;
    private TextView mState;
    private RelativeLayout mALoading;
    private LinearLayout mANoMore;

    public RefreshFooterLayout(Context context) {
        super(context);
        init();
    }

    public RefreshFooterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshFooterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mChild = LayoutInflater.from(getContext()).inflate(R.layout.footer_refreshing, this, false);
        mChild.setOnClickListener(this);

        mProgress = (ProgressBar) mChild.findViewById(R.id.progress);
        mState = (TextView) mChild.findViewById(R.id.refresh_status);

        mALoading = (RelativeLayout) mChild.findViewById(R.id.area_loading);
        mANoMore = (LinearLayout) mChild.findViewById(R.id.area_no_more);


        int heightSpace = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int widthSpace = View.MeasureSpec.makeMeasureSpec(getResources().getDisplayMetrics().widthPixels, View.MeasureSpec.EXACTLY);

        mChild.measure(widthSpace, heightSpace);

        int height = mChild.getMeasuredHeight();

        addView(mChild);

        setHeight(height);
        setOriginHeight(height);
    }

    public void setRefreshListener(RefreshListener l) {
        mListener = l;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;

        resetOrightHeight();
        if (hasMore) {
            mALoading.setVisibility(View.VISIBLE);
            mANoMore.setVisibility(View.GONE);
        } else {
            mALoading.setVisibility(View.GONE);
            mANoMore.setVisibility(View.VISIBLE);
        }
    }

    public void setError() {
        hasMore = false;
        isLoading = false;
        isError = true;
        mProgress.setVisibility(View.GONE);
        mState.setText(TXT_CLICK_RETRY);
    }

    public void onLoadComplete() {
        postDelayed(this, 200);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        load();
    }

    public void load() {
        if (!isLoading && hasMore && !isError) {
            isLoading = true;
            resetOrightHeight();
            mState.setText(TXT_LOADING);
            mListener.onRefresh();
        }
    }

    @Override
    public void run() {
        isLoading = false;
    }

    @Override
    public void onClick(View v) {
        if (isError) {
            isLoading = true;
            hasMore = true;
            isError = false;
            mState.setText(TXT_LOADING);
            mListener.onRefresh();
            mProgress.setVisibility(View.VISIBLE);
        }
    }
}
