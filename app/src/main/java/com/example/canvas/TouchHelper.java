package com.example.canvas;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import java.util.List;
import android.view.accessibility.AccessibilityEvent;

public class TouchHelper extends ExploreByTouchHelper {

    private CanvasTalkBackView mCanvasView; // View triển khai interface CanvasTalkBackView
    private Context mContext;

    public TouchHelper(View host, CanvasTalkBackView canvasView, Context mContext) {
        super(host);
        this.mCanvasView = canvasView;
        this.mContext = mContext;
    }

    @Override
    protected int getVirtualViewAt(float x, float y) {
        List<Rect> areas = mCanvasView.getInteractiveAreas();
        for (int i = 0; i < areas.size(); i++) {
            if (areas.get(i).contains((int) x, (int) y)) {
                return i + 1; // Trả về ID ảo của khu vực
            }
        }
        return ExploreByTouchHelper.INVALID_ID;
    }

    @Override
    protected void getVisibleVirtualViews(List<Integer> virtualViews) {
        // Thêm tất cả các khu vực có thể tương tác vào danh sách
        List<Rect> areas = mCanvasView.getInteractiveAreas();
        for (int i = 0; i < areas.size(); i++) {
            virtualViews.add(i + 1);
        }
    }

    @Override
    protected void onPopulateEventForVirtualView(int virtualViewId, AccessibilityEvent event) {
        List<Rect> areas = mCanvasView.getInteractiveAreas();
        if (virtualViewId > 0 && virtualViewId <= areas.size()) {
            Rect area = areas.get(virtualViewId - 1);
            event.setContentDescription(mCanvasView.getContentDescriptionForArea(area));
        }
    }

    @Override
    protected void onPopulateNodeForVirtualView(int virtualViewId, @NonNull AccessibilityNodeInfoCompat node) {
        List<Rect> areas = mCanvasView.getInteractiveAreas();
        if (virtualViewId > 0 && virtualViewId <= areas.size()) {
            Rect area = areas.get(virtualViewId - 1);
            node.setContentDescription(mCanvasView.getContentDescriptionForArea(area));
            node.setBoundsInParent(area);
            node.setClickable(true);
        }
    }

    @Override
    protected boolean onPerformActionForVirtualView(int virtualViewId, int action, Bundle arguments) {
        List<Rect> areas = mCanvasView.getInteractiveAreas();
        if (virtualViewId > 0 && virtualViewId <= areas.size()) {
            Rect area = areas.get(virtualViewId - 1);
            return mCanvasView.performActionForArea(area, action); // todo
        }
        return false;
    }
}