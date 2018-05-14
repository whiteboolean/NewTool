package com.mtool.toolslib.base.view.custom.recycleview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 用于RecycleView的分割线，左右边距都是15dp
 * Created by q2366 on 2016/2/29.
 */
public class RVDividerHeightMargin extends RecyclerView.ItemDecoration {


    private int margin = 0;
    private int mStrokeWidth = 0;
    private boolean needDraw = true;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RVDividerHeightMargin(Context context) {
        this(context, 0, 1, true);
    }

    public RVDividerHeightMargin(Context context, int margin, int height, boolean needDraw) {
        final float DENSITY = context.getResources().getDisplayMetrics().density;
        this.margin = (int) (DENSITY * margin);

        this.needDraw = needDraw;

        mStrokeWidth = (int) (DENSITY * 0.5 * height);
        if (mStrokeWidth < 1) mStrokeWidth = 1;

        mPaint.setColor(0xffdddddd);
        mPaint.setStrokeWidth(mStrokeWidth);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (!"skip".equals(view.getTag())) {
            outRect.set(0, 0, 0, mStrokeWidth);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (needDraw) {
            int count = parent.getChildCount();
            int right = parent.getWidth() - margin;
            for (int i = 0; i < count; i++) {
                int y = parent.getChildAt(i).getBottom();
                c.drawLine(margin, y, right, y, mPaint);
            }
        }
    }
}
