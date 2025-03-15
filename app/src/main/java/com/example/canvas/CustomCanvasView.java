package com.example.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.List;

public class CustomCanvasView extends View implements CanvasTalkBackView {

    private List<InteractiveArea> mInteractiveAreas;
    private Paint mPaint;
    private TouchHelper mTouchHelper;

    public CustomCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mInteractiveAreas = new ArrayList<>();
        mInteractiveAreas.add(new InteractiveArea(new Rect(100, 100, 500, 500), "This is the first clickable area"));
        mInteractiveAreas.add(new InteractiveArea(new Rect(600, 100, 1000, 500), "This is the second clickable area"));

        mPaint = new Paint();
        mPaint.setColor(0xFF00FF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        // Khởi tạo DoodleAIDragTouchHelper
//        mTouchHelper = new TouchHelper(this, this, getContext());
        ViewCompat.setAccessibilityDelegate(this, mTouchHelper);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (InteractiveArea area : mInteractiveAreas) {
            canvas.drawRect(area.getRect(), mPaint);
        }
    }

    @Override
    public List<Rect> getInteractiveAreas() {
        List<Rect> rects = new ArrayList<>();
        for (InteractiveArea area : mInteractiveAreas) {
            rects.add(area.getRect());
        }
        return rects;
    }

    @Override
    public String getContentDescriptionForArea(Rect area) {
        for (InteractiveArea interactiveArea : mInteractiveAreas) {
            if (interactiveArea.getRect().equals(area)) {
                return interactiveArea.getDescription();
            }
        }
        return null;
    }

    @Override
    public boolean performActionForArea(Rect area, int action) {
        // Xử lý hành động khi người dùng chạm vào khu vực
        if (action == AccessibilityNodeInfo.ACTION_CLICK) {
            return true;
        }
        return false;
    }

    @Override
    public void updateInteractiveAreaPosition(int index, Rect newRect) {
        if (index >= 0 && index < mInteractiveAreas.size()) {
            mInteractiveAreas.get(index).setRect(newRect);

//            invalidate(); todo
            AccessibilityEvent event = AccessibilityEvent.obtain(AccessibilityEvent.TYPE_VIEW_FOCUSED);
            sendAccessibilityEvent(event.getEventType());
        }
    }

    @Override
    protected boolean dispatchHoverEvent(MotionEvent event) {
        // Chuyển sự kiện hover cho TouchHelper xử lý
        if (mTouchHelper.dispatchHoverEvent(event)) {
            return true;
        }
        return super.dispatchHoverEvent(event);
    }
}
