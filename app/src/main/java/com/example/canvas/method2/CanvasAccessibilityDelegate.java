package com.example.canvas.method2;
import android.graphics.Rect;
import android.view.View;

import java.util.List;

public interface CanvasAccessibilityDelegate {
    List<? extends CanvasElement> getCanvasElements(); // Trả về danh sách các phần tử
    CanvasElement getElementById(int id);          // Lấy phần tử theo ID
    Rect getBoundsForElement(CanvasElement element);   // Lấy bounding rect của phần tử
    void performCanvasElementClick(int elementId); // Thực hiện hành động "click"
    View getHostView(); // Trả về View chủ (host view)
    void onElementFocusChanged(int elementId, boolean focused);

}
