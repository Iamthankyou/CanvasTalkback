package com.example.canvas;

import android.graphics.Rect;

import java.util.List;

public interface CanvasTalkBackView {
    List<Rect> getInteractiveAreas();

    String getContentDescriptionForArea(Rect area);

    boolean performActionForArea(Rect area, int action);

    void updateInteractiveAreaPosition(int index, Rect newRect);
}

