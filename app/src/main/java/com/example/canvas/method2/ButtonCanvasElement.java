package com.example.canvas.method2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.Button;

public class ButtonCanvasElement implements CanvasElement {
    private int mId;
    private Rect mBounds;
    private String mText;
    private boolean mIsClickable; // Thêm biến isClickable
    private boolean mIsFocused = true; // Thêm biến isFocused.

    public ButtonCanvasElement(int id, int left, int top, int right, int bottom, String text, boolean clickable) {
        mId = id;
        mBounds = new Rect(left, top, right, bottom);
        mText = text;
        mIsClickable = clickable; // Khởi tạo isClickable
    }

    @Override
    public boolean isClickable(){
        return mIsClickable;
    }
    @Override
    public int getId() {
        return mId;
    }

    @Override
    public Rect getBounds() {
        return mBounds;
    }

    @Override
    public String getContentDescription() {
        return mText;
    }
    @Override
    public boolean isFocused(){
        return mIsFocused;
    }

    @Override
    public void onFocusChanged(boolean focused){
        mIsFocused = focused;
        //Có thể gọi invalidate() ở đây, để cập nhật trạng thái vẽ của button (ví dụ, thêm highlight).
    }

    @Override
    public void draw(Canvas canvas) {
        // Vẽ button (ví dụ: vẽ một hình chữ nhật và text).
        Paint paint = new Paint();
        paint.setColor(mIsFocused ? Color.BLUE : Color.GRAY);  // Màu khác nhau khi focus
        canvas.drawRect(mBounds, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        canvas.drawText(mText, mBounds.left + 10, mBounds.top + 30, paint); // Điều chỉnh vị trí text cho phù hợp.
    }

    @Override
    public void performAccessibilityClick() {

    }

    @Override
    public String getClassName() {
        return Button.class.getName();
    }
}