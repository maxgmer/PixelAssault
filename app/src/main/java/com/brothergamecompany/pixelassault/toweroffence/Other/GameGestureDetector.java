package com.brothergamecompany.pixelassault.toweroffence.Other;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.brothergamecompany.pixelassault.framework.impl.GLGraphics;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldRenderer;

/**
 * Created by maxgm_umv4xdu on 03.07.2017.
 */

public class GameGestureDetector {

    public final GestureDetector.SimpleOnGestureListener simpleOnGestureListener;
    public final ScaleGestureDetector.SimpleOnScaleGestureListener mScaleGestureListener;
    private float lastSpanX;
    private float lastSpanY;
    private float lastFocusX;
    private float lastFocusY;

    public GameGestureDetector(final GLGraphics glGraphics) {

        simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){

                float viewportOffsetX = distanceX * WorldRenderer.worldCam.frustumWidth * WorldRenderer.worldCam.zoom
                        / glGraphics.getWidth();
                float viewportOffsetY = -distanceY * WorldRenderer.worldCam.frustumHeight * WorldRenderer.worldCam.zoom
                        / glGraphics.getHeight();

                // Updates the viewport, refreshes the display.
                float visibleAreaWidth = WorldRenderer.worldCam.frustumWidth * WorldRenderer.worldCam.zoom;
                float visibleAreaHeight = WorldRenderer.worldCam.frustumHeight * WorldRenderer.worldCam.zoom;

                WorldRenderer.worldCam.position.add(viewportOffsetX, viewportOffsetY);
                if (WorldRenderer.worldCam.position.x + (visibleAreaWidth/2) > World.WORLD_WIDTH) WorldRenderer.worldCam.position.x = World.WORLD_WIDTH - (visibleAreaWidth/2);
                if (WorldRenderer.worldCam.position.x - (visibleAreaWidth/2) < 0) WorldRenderer.worldCam.position.x = visibleAreaWidth/2;
                if (WorldRenderer.worldCam.position.y + (visibleAreaHeight/2) > World.WORLD_HEIGHT) WorldRenderer.worldCam.position.y = World.WORLD_HEIGHT - (visibleAreaHeight/2);
                if (WorldRenderer.worldCam.position.y - (visibleAreaHeight/2) < 0) WorldRenderer.worldCam.position.y = visibleAreaHeight/2;

                return true;
            }
        };

        mScaleGestureListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                //lastSpanX = scaleGestureDetector.getCurrentSpanX();
                //lastSpanY = scaleGestureDetector.getCurrentSpanY();
                return true;
            }

            @Override
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                WorldRenderer.worldCam.zoom /= scaleGestureDetector.getScaleFactor();
                if ((WorldRenderer.worldCam.zoom / scaleGestureDetector.getScaleFactor()) < 0.2f) WorldRenderer.worldCam.zoom  = 0.2f;
                if ((WorldRenderer.worldCam.zoom / scaleGestureDetector.getScaleFactor()) > 1.0f) WorldRenderer.worldCam.zoom = 1.0f;
                float visibleAreaWidth = WorldRenderer.worldCam.frustumWidth * WorldRenderer.worldCam.zoom;
                float visibleAreaHeight = WorldRenderer.worldCam.frustumHeight * WorldRenderer.worldCam.zoom;
                if (WorldRenderer.worldCam.position.x + (visibleAreaWidth/2) > World.WORLD_WIDTH) WorldRenderer.worldCam.position.x = World.WORLD_WIDTH - (visibleAreaWidth/2);
                if (WorldRenderer.worldCam.position.x - (visibleAreaWidth/2) < 0) WorldRenderer.worldCam.position.x = visibleAreaWidth/2;
                if (WorldRenderer.worldCam.position.y + (visibleAreaHeight/2) > World.WORLD_HEIGHT) WorldRenderer.worldCam.position.y = World.WORLD_HEIGHT - (visibleAreaHeight/2);
                if (WorldRenderer.worldCam.position.y - (visibleAreaHeight/2) < 0) WorldRenderer.worldCam.position.y = visibleAreaHeight/2;
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }
        };
    }
}
