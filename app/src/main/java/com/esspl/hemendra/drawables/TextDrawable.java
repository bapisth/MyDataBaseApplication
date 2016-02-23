package com.esspl.hemendra.drawables;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

/**
 * Created by BAPI1 on 04-01-2016.
 */
public class TextDrawable extends Drawable {

    private static final int DEFAULT_COLOR = Color.RED;
    private static final int DEFAULT_TEXTSIZE = 15;
    private Paint paint;
    private CharSequence text;
    private int mIntrinsicWidth;
    private int mIntrinsicHeight;

    public TextDrawable(Resources resources, CharSequence text) {
        this.text = text;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(DEFAULT_COLOR);
        paint.setTextAlign(Paint.Align.CENTER);
        float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,DEFAULT_TEXTSIZE, resources.getDisplayMetrics());
        paint.setTextSize(textSize);
        mIntrinsicWidth = (int)(paint.measureText(text, 0, text.length() )+0.5);
        mIntrinsicHeight = paint.getFontMetricsInt(null);
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.drawText(text, 0, text.length(), bounds.centerX(), bounds.centerY(), paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return paint.getAlpha();
    }

    @Override
    public int getIntrinsicWidth() {
        return mIntrinsicWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mIntrinsicHeight;
    }
}
