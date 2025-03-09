package com.example.canvas;

import android.graphics.Rect;

public class InteractiveArea {
    private Rect mRect;
    private String mDescription;

    public InteractiveArea(Rect rect, String description) {
        this.mRect = rect;
        this.mDescription = description;
    }

    public Rect getRect() {
        return mRect;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setRect(Rect newRect) {
        this.mRect = newRect;
    }
}

