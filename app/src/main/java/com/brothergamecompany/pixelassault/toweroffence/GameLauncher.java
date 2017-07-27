package com.brothergamecompany.pixelassault.toweroffence;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.brothergamecompany.pixelassault.framework.Screen;
import com.brothergamecompany.pixelassault.framework.impl.GLGame;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.MapBuilder;
import com.brothergamecompany.pixelassault.toweroffence.GameScreens.GameScreen;
import com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets;
import com.brothergamecompany.pixelassault.toweroffence.Other.GameGestureDetector;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maxgm_umv4xdu on 04.06.2017.
 */

public class GameLauncher extends GLGame {
    private boolean firstTimeCreate = true;

    private ScaleGestureDetector mScaleDetector;
    private GestureDetectorCompat mGestureDetector;

    @Override
    public Screen getStartScreen() {
        return new GameScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        if (firstTimeCreate) {
            Assets.load(this);
            firstTimeCreate = false;
        }
        else {
            Assets.reload();
        }

    }

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        GameGestureDetector gestureDetector = new GameGestureDetector(getGLGraphics());
        mGestureDetector = new GestureDetectorCompat(this, gestureDetector.simpleOnGestureListener);
        mScaleDetector = new ScaleGestureDetector(this, gestureDetector.mScaleGestureListener);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        if (MapBuilder.cellChosen == null) {
            mScaleDetector.onTouchEvent(event);
            mGestureDetector.onTouchEvent(event);
        }
        return false;
    }
}
