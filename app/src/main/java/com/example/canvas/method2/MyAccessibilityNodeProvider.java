package com.example.canvas.method2;

import android.os.Bundle;

import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;

import java.util.List;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import  androidx.core.view.AccessibilityDelegateCompat; // Import

import com.example.canvas.CustomCanvasView2;

import java.util.List;

public class MyAccessibilityNodeProvider extends AccessibilityDelegateCompat { // Kế thừa

    private final CustomCanvasView2 mCanvasView;
    private final AccessibilityNodeProviderCompat mNodeProvider;


    public MyAccessibilityNodeProvider(CustomCanvasView2 canvasView) {
        mCanvasView = canvasView;
        mNodeProvider = new AccessibilityNodeProviderCompat() { // Tạo instance bên trong

            @Override
            public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int virtualViewId) {
                if (virtualViewId == -1) {
                    AccessibilityNodeInfoCompat rootNode = AccessibilityNodeInfoCompat.obtain(mCanvasView);
                    ViewCompat.onInitializeAccessibilityNodeInfo(mCanvasView, rootNode);

                    List<CanvasElement> items = (List<CanvasElement>) mCanvasView.getCanvasElements();
                    for (CanvasElement item : items) {
                        rootNode.addChild(mCanvasView, item.getId());
                    }
                    return rootNode;

                } else {
                    CanvasElement item = mCanvasView.getElementById(virtualViewId);
                    if (item != null) {
                        return createNodeForItem(item);
                    }
                }
                return null;
            }

            @Override
            public boolean performAction(int virtualViewId, int action, Bundle arguments) {
                // ... (như trước) ...
                CanvasElement item = mCanvasView.getElementById(virtualViewId);
                if (item != null) {
                    if (action == AccessibilityNodeInfoCompat.ACTION_CLICK) {
                        item.performAccessibilityClick();
                        return true;
                    } else if (action == AccessibilityNodeInfoCompat.ACTION_ACCESSIBILITY_FOCUS){
//                        item.setFocused(true);
                        mCanvasView.onElementFocusChanged(virtualViewId, true);
                        mCanvasView.invalidate();
                        return true;
                    } else if (action == AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS){
//                        item.setFocused(false);
                        mCanvasView.onElementFocusChanged(virtualViewId, true);
                        mCanvasView.invalidate();
                        return  true;
                    }

                }
                return false;
            }

            private AccessibilityNodeInfoCompat createNodeForItem(CanvasElement item) {
                // ... (như trước) ...
                AccessibilityNodeInfoCompat node = AccessibilityNodeInfoCompat.obtain();
                node.setClassName(item.getClassName());
                node.setText(item.getContentDescription());
                node.setContentDescription(item.getContentDescription());
                node.setBoundsInParent(item.getBounds());
                node.setParent(mCanvasView);
                node.addAction(AccessibilityNodeInfoCompat.ACTION_CLICK);
                node.addAction(AccessibilityNodeInfoCompat.ACTION_ACCESSIBILITY_FOCUS);
                node.addAction(AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
//                node.setFocusable(true);
                node.setClickable(true);
//                node.setAccessibilityFocused(true);

                return node;
            }
        };

    }
    @Override
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
        return mNodeProvider;
    }

}