package com.example.canvas.method2;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface CanvasElement {
    int getId();
    Rect getBounds();
    String getContentDescription();
    void draw(Canvas canvas);
    void performAccessibilityClick(); // Phương thức xử lý click cho TalkBack
    String getClassName();
    boolean isClickable();
    boolean isFocused();
    void onFocusChanged(boolean focused);
}
