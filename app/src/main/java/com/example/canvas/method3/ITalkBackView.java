package com.example.canvas.method3;

import android.graphics.Rect;

import java.util.List;

public interface ITalkBackView {
    List<Rect> getInteractiveAreas();
    String getContentDescriptionForArea(Rect area);
    void updateInteractiveAreaPosition(int index, Rect newRect);
    void updateDescriptionPosition(int index, String newDescription);
    void addInteractiveElement(TalkBackViewItem talkBackViewItem);
    boolean performActionForArea(int virtualViewId, int action);
}
