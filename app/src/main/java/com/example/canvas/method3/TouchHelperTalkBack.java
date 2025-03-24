package com.example.canvas.method3;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;

import java.util.List;

public class TouchHelperTalkBack extends ExploreByTouchHelper {

    private ITalkBackView mTalkBackView; // View triển khai interface CanvasTalkBackView
    private Context mContext;

    public TouchHelperTalkBack(View host, ITalkBackView canvasView, Context mContext) {
        super(host);
        this.mTalkBackView = canvasView;
        this.mContext = mContext;
    }

    @Override
    protected int getVirtualViewAt(float x, float y) {
        Log.i("duy-duy", "getVirtualViewAt: x, y: " + x + ", " + y);
        List<Rect> areas = mTalkBackView.getInteractiveAreas();
        for (int i = 0; i < areas.size(); i++) {
            if (areas.get(i).contains((int) x, (int) y)) {
                return i + 1; // Trả về ID ảo của khu vực
            }
        }
        return ExploreByTouchHelper.INVALID_ID;
    }

    @Override
    protected void getVisibleVirtualViews(List<Integer> virtualViews) {
        Log.i("duy-duy", "getVisibleVirtualViews: size: " + virtualViews.size());
        // Thêm tất cả các khu vực có thể tương tác vào danh sách
        List<Rect> areas = mTalkBackView.getInteractiveAreas();
        for (int i = 0; i < areas.size(); i++) {
            virtualViews.add(i + 1);
        }
    }

    @Override
    protected void onPopulateEventForVirtualView(int virtualViewId, AccessibilityEvent event) {
        List<Rect> areas = mTalkBackView.getInteractiveAreas();
        Log.i("duy-duy", "onPoludateEventForVirtualView: " + virtualViewId);
        if (virtualViewId > 0 && virtualViewId <= areas.size()) {
            Rect area = areas.get(virtualViewId - 1);
        }
    }

    @Override
    protected void onPopulateNodeForVirtualView(int virtualViewId, @NonNull AccessibilityNodeInfoCompat node) {
        List<Rect> areas = mTalkBackView.getInteractiveAreas();
        if (virtualViewId > 0 && virtualViewId <= areas.size()) {
            Log.i("duy-duy", "onPoludateNodeForVirtualView: " + virtualViewId);
            Rect area = areas.get(virtualViewId - 1);
            node.setContentDescription(mTalkBackView.getContentDescriptionForArea(area));
            node.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            node.setBoundsInParent(area);
            node.setClickable(true);
        }
    }

    @Override
    protected boolean onPerformActionForVirtualView(int virtualViewId, int action, Bundle arguments) {
        Log.i("duy-duy2", "onPerformActionForVirtualView: " + virtualViewId);
        return mTalkBackView.performActionForArea(virtualViewId, action);
    }

    public void sendClickEvent(int virtualViewId) {
        if (virtualViewId != INVALID_ID) {
            sendEventForVirtualView(virtualViewId, AccessibilityEvent.TYPE_VIEW_CLICKED);
        }
    }
}