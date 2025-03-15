package com.example.canvas.method3;

import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TalkBackVirtualView implements ITalkBackView {

    protected List<InteractiveCanvasElement> interactiveElements = new ArrayList<>();
    private View hostView;
    private TalkBackClickListener talkBackClickListener;
    public TalkBackVirtualView(View hostView, TalkBackClickListener listener) {
        this.hostView = hostView;
        this.talkBackClickListener = listener;
    }

    @Override
    public List<Rect> getInteractiveAreas() {
        List<Rect> areas = new ArrayList<>();
        for (InteractiveCanvasElement element : interactiveElements) {
            areas.add(element.getBounds());
        }
        return areas;
    }

    @Override
    public String getContentDescriptionForArea(Rect area) {
        for (InteractiveCanvasElement element : interactiveElements) {
            if (element.getBounds().equals(area)) {
                return element.getContentDescription();
            }
        }
        return null;
    }

    @Override
    public void updateInteractiveAreaPosition(int index, Rect newRect) {
        if (index >= 0 && index < interactiveElements.size()) {
            interactiveElements.get(index).updateBounds(newRect);
        }
    }

    @Override
    public void addInteractiveElement(TalkBackArea talkBackArea) {
        interactiveElements.add(talkBackArea);
    }

    @Override
    public boolean performActionForArea(int virtualViewId, int action) {
        talkBackClickListener.onTalkBackClick(virtualViewId, action);
        return true;
    }
}