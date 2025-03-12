package com.example.canvas.method2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessibleCanvasView extends View{
    public AccessibleCanvasView(Context context) {
        super(context);
    }

//    private MyAccessibilityNodeProvider mAccessibilityNodeProvider;
//    private final List<CanvasElement> mCanvasElements = new ArrayList<>();
//    private final Map<Integer, CanvasElement> mElementMap = new HashMap<>();
//
//    public AccessibleCanvasView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        mAccessibilityNodeProvider = new MyAccessibilityNodeProvider(this); // Truyền this (delegate)
//        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
////            @Override
////            public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
////                return mAccessibilityNodeProvider;
////            }
//        });
//    }
//
//    // ... (các phương thức addElement, removeElement, updateElement, onDraw như trước) ...
//    public void addElement(CanvasElement element) {
//        mCanvasElements.add(element);
//        mElementMap.put(element.getId(), element);
//        invalidate();
//    }
//
//    public void updateElement(CanvasElement element){
//        if (mElementMap.containsKey(element.getId())) {
//            invalidate();
//        }
//    }
//    public void removeElement(CanvasElement element) {
//        mCanvasElements.remove(element);
//        mElementMap.remove(element.getId());
//        invalidate();
//    }
//
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        for (CanvasElement element : mCanvasElements) {
//            element.draw(canvas);
//        }
//    }
//    // --- Implement CanvasAccessibilityDelegate ---
//
//    @Override
//    public List<? extends CanvasElement> getCanvasElements() {
//        return mCanvasElements;
//    }
//
//    @Override
//    public CanvasElement getElementById(int id) {
//        return mElementMap.get(id);
//    }
//
//    @Override
//    public Rect getBoundsForElement(CanvasElement element) {
//        return element.getBounds();
//    }
//
//    @Override
//    public void performCanvasElementClick(int elementId) {
//        CanvasElement element = mElementMap.get(elementId);
//        if(element != null)
//        {
//            element.performAccessibilityClick();
//        }
//
//    }
//    @Override
//    public void onElementFocusChanged(int elementId, boolean focused){
//        CanvasElement element = mElementMap.get(elementId);
//        if(element != null)
//        {
//            element.onFocusChanged(focused);
//        }
//    }
//
//    @Override
//    public View getHostView() {
//        return this;
//    }
}