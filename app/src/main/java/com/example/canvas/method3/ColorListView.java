package com.example.canvas.method3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.List;

public class ColorListView extends View implements TalkBackClickListener{
    private List<Integer> mColors;
    private List<Rect> mColorRects;
    private TouchHelperTalkBack mTouchHelper;
    private ITalkBackView talkBackDelegate;
    private Paint mPaint;

    public ColorListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mColors = new ArrayList<>();
        mColorRects = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        setColors(colors);
        mPaint = new Paint();
        mPaint.setColor(0xFF00FF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        talkBackDelegate = new TalkBackVirtualView(this, this);
        mTouchHelper = new TouchHelperTalkBack(this, talkBackDelegate, getContext());
        ViewCompat.setAccessibilityDelegate(this, mTouchHelper);

    }

    public void setColors(List<Integer> colors) {
        mColors = colors;
        calculateColorRects();
        invalidate(); // Yêu cầu vẽ lại
//        mAccessibilityHelper.invalidateRoot();//Quan trọng!
        mTouchHelper.invalidateVirtualView(talkBackDelegate.getInteractiveAreas().size());
    }


    private void calculateColorRects() {
        mColorRects.clear();
        int top = 0;
        for (int i = 0; i < mColors.size(); i++) {
            Rect rect = new Rect(0, top, getWidth(), top + 100);
            mColorRects.add(rect);
            top += 120;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mColors.size(); i++) {
            mPaint.setColor(mColors.get(i));
            canvas.drawRect(mColorRects.get(i), mPaint);
        }
    }

    public String getColorName(int color) {
        // (Đây chỉ là ví dụ đơn giản, trong thực tế bạn có thể cần
        //  một cách tốt hơn để lấy tên màu)
        if (color == Color.RED) {
            return "Red";
        } else if (color == Color.BLUE) {
            return "Blue";
        } else if (color == Color.GREEN) {
            return "Green";
        } else if (color == Color.YELLOW) {
            return "Yellow";
        } else {
            return "Unknown Color";
        }
    }

    @Override
    public void onTalkBackClick(int virtualViewId, int action) {

    }
}
