package com.example.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;

import com.example.canvas.method2.ButtonCanvasElement;
import com.example.canvas.method2.CanvasAccessibilityDelegate;
import com.example.canvas.method2.CanvasElement;
import com.example.canvas.method2.MyAccessibilityNodeProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomCanvasView2 extends View implements CanvasAccessibilityDelegate {

    private List<InteractiveArea> mInteractiveAreas;
    private Paint mPaint;
    private final List<CanvasElement> mCanvasElements = new ArrayList<>();
    private final Map<Integer, CanvasElement> mElementMap = new HashMap<>();
    private MyAccessibilityNodeProvider mAccessibilityNodeProvider;

    public CustomCanvasView2(Context context, AttributeSet attrs) {
        super(context, attrs);

        mInteractiveAreas = new ArrayList<>();
        mInteractiveAreas.add(new InteractiveArea(new Rect(100, 100, 500, 500), "This is the first clickable area"));
        mInteractiveAreas.add(new InteractiveArea(new Rect(600, 100, 1000, 500), "This is the second clickable area"));

        mPaint = new Paint();
        mPaint.setColor(0xFF00FF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mAccessibilityNodeProvider = new MyAccessibilityNodeProvider(this);
        ViewCompat.setAccessibilityDelegate(this, mAccessibilityNodeProvider); // Giữ nguyên
//    }

//        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityNodeProvider(mAccessibilityNodeProvider));
//        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
//            @Override
//            public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
//                return mAccessibilityNodeProvider;
//            }
//        });

//        setAccessibilityDelegate(new AccessibilityDelegate(){
//            @Override
//            public AccessibilityNodeProvider getAccessibilityNodeProvider(@NonNull View host) {
//                return (AccessibilityNodeProvider) mAccessibilityNodeProvider;
//            }
//        });

//        setAccessibilityDelegate(new AccessibilityDelegateCompat(){
//            @Override
//            public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
//                return mAccessibilityNodeProvider;
//            }
//        });

        addElement(new ButtonCanvasElement(0, 100, 100, 500, 500, "Button 1", true));
        addElement(new ButtonCanvasElement(1, 600, 100, 1000, 500, "Button 2", false));
    }

    public void addElement(CanvasElement element) {
        mCanvasElements.add(element);
        mElementMap.put(element.getId(), element);
        invalidate();
    }

    public void updateElement(CanvasElement element){
        if (mElementMap.containsKey(element.getId())) {
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (CanvasElement element : mCanvasElements) {
            element.draw(canvas);
        }
    }

    @Override
    public List<? extends CanvasElement> getCanvasElements() {
        return mCanvasElements;
    }

    @Override
    public CanvasElement getElementById(int id) {
        return mElementMap.get(id);
    }

    @Override
    public Rect getBoundsForElement(CanvasElement element) {
        return element.getBounds();
    }

    @Override
    public void performCanvasElementClick(int elementId) {
        CanvasElement element = mElementMap.get(elementId);
        if(element != null)
        {
            element.performAccessibilityClick();
        }
    }

    @Override
    public View getHostView() {
        return this;
    }

    @Override
    public void onElementFocusChanged(int elementId, boolean focused) {
        CanvasElement element = mElementMap.get(elementId);
        if(element != null)
        {
            element.onFocusChanged(focused);
            invalidate();
        }
    }
}
