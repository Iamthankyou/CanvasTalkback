package com.example.canvas.method3;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

public class DefaultCanvasTalkBackView{
    private Context context;

    public DefaultCanvasTalkBackView(View hostView) {
        this.context = hostView.getContext();
    }
}
