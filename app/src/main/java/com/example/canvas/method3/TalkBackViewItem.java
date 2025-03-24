package com.example.canvas.method3;

import android.graphics.Rect;

class TalkBackViewItem implements InteractiveCanvasElement {
    private Rect bounds;
    private String description;

    public TalkBackViewItem(Rect bounds, String description) {
        this.bounds = bounds;
        this.description = description;
    }

    @Override
    public Rect getBounds() {
        return bounds;
    }

    @Override
    public String getContentDescription() {
        return description;
    }

    @Override
    public void updateBounds(Rect newBounds) {
        this.bounds = newBounds;
    }

    @Override
    public void updateDescription(String description) {
        this.description = description;
    }
}