package com.example.canvas.method3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import androidx.core.view.ViewCompat;

public class CustomCanvasView3 extends View implements TalkBackClickListener{
    private Paint mPaint;
    private TouchHelperTalkBack mTouchHelper;
    private ITalkBackView talkBackDelegate; // Phụ thuộc vào interface
    private boolean mTempFlag = false;

    public CustomCanvasView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(0xFF00FF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        // Tạo instance của implementation cụ thể (ví dụ: DefaultCanvasTalkBackView)
        talkBackDelegate = new TalkBackVirtualView(this, this);
        mTouchHelper = new TouchHelperTalkBack(this, talkBackDelegate, getContext());
        ViewCompat.setAccessibilityDelegate(this, mTouchHelper);

        addArea(new Rect(100, 100, 500, 500), "This is the first clickable area");
        addArea(new Rect(600, 100, 1000, 500), "This is the second clickable area");

        postDelayed(() -> {
            Rect newBounds = new Rect(300, 700, 900, 1000);
            String newDescription = "This is the third dynamically added area";
            addArea(newBounds, newDescription);
        }, 2000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        for (Rect area : talkBackDelegate.getInteractiveAreas()) {
//            canvas.drawRect(area, mPaint);
//        }
        canvas.drawRect(new Rect(100, 100, 500, 500), mPaint);
        canvas.drawRect(new Rect(600, 100, 1000, 500), mPaint);
        if (!mTempFlag) {
            postDelayed(() -> {
                int distance = 20;
//                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//                canvas.drawRect(new Rect(100+distance, 100+distance, 500+distance, 500+distance), mPaint);
//                canvas.drawRect(new Rect(600, 100, 1000, 500), mPaint);
//                invalidate();
//                updateAreaPosition(0, new Rect(100 + distance, 100 + distance, 500 + distance, 500 + distance));
                Toast.makeText(getContext(), "Update element 1", Toast.LENGTH_SHORT).show();
                updateDescription(0, "Update description");
            }, 10000);


            mTempFlag = true;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();

        int virtualViewId = mTouchHelper.getVirtualViewAt(event.getX(), event.getY());
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // Có thể bạn muốn invalidate và gửi TYPE_VIEW_HOVER_ENTER ở đây
                mTouchHelper.invalidateVirtualView(virtualViewId);
                mTouchHelper.sendEventForVirtualView(virtualViewId, AccessibilityEvent.TYPE_VIEW_HOVER_ENTER);

                return true; // Luôn trả về true để xử lý các sự kiện tiếp theo
            case MotionEvent.ACTION_UP:
//                 mTouchHelper.sendEventForVirtualView(virtualViewId, AccessibilityEvent.TYPE_VIEW_CLICKED); // KHÔNG DÙNG TRỰC TIẾP. DÙNG HÀM sendClickEvent.
                mTouchHelper.sendClickEvent(virtualViewId);
                return true;
            case MotionEvent.ACTION_MOVE:
                // Cập nhật hover nếu cần
                mTouchHelper.invalidateVirtualView(virtualViewId);
                mTouchHelper.sendEventForVirtualView(virtualViewId, AccessibilityEvent.TYPE_VIEW_HOVER_ENTER);

                return true;
            case MotionEvent.ACTION_CANCEL:
//                 Thoát hover nếu cần
                mTouchHelper.sendEventForVirtualView(virtualViewId, AccessibilityEvent.TYPE_VIEW_HOVER_EXIT);
                return true;
        }

        return super.onTouchEvent(event);

    }


    @Override
    protected boolean dispatchHoverEvent(MotionEvent event) {
        mTouchHelper.dispatchHoverEvent(event);
        return super.dispatchHoverEvent(event);
    }

    // Phương thức để cập nhật vị trí một khu vực
    public void updateAreaPosition(int index, Rect newRect) {
        talkBackDelegate.updateInteractiveAreaPosition(index, newRect);
    }

    public void addArea(Rect bounds, String description) {
        talkBackDelegate.addInteractiveElement(new TalkBackViewItem(bounds, description));
        mTouchHelper.invalidateVirtualView(talkBackDelegate.getInteractiveAreas().size());
    }

    public void updateDescription(int index, String description){
        talkBackDelegate.updateDescriptionPosition(index, description);
        mTouchHelper.invalidateVirtualView(talkBackDelegate.getInteractiveAreas().size());
    }

    @Override
    public void onTalkBackClick(int virtualViewId, int action) {
//        Toast.
        Toast.makeText(getContext(), "Clicked: " + virtualViewId, Toast.LENGTH_SHORT).show();
    }
}
