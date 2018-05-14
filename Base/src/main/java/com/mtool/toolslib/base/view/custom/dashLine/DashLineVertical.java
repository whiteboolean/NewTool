package com.mtool.toolslib.base.view.custom.dashLine;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

public class DashLineVertical extends View {
    public DashLineVertical(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(android.R.color.darker_gray));
        Path path = new Path();
        path.moveTo(5, 0);
        path.lineTo(5, 2000);
        PathEffect effects = new DashPathEffect(new float[]{5, 10, 5, 10}, 1);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }

}
