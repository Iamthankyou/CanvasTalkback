package com.example.canvas.method3;

import android.graphics.Rect;

public interface InteractiveCanvasElement {
    Rect getBounds();
    String getContentDescription();
    void updateBounds(Rect newBounds);
}