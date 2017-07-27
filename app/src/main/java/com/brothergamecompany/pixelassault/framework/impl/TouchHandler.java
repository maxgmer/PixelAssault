package com.brothergamecompany.pixelassault.framework.impl;

import java.util.List;

import android.view.View.OnTouchListener;

import com.brothergamecompany.pixelassault.framework.Input;


public interface TouchHandler extends OnTouchListener {
    boolean isTouchDown(int pointer);
    
    int getTouchX(int pointer);
    
    int getTouchY(int pointer);
    
    List<Input.TouchEvent> getTouchEvents();
}
