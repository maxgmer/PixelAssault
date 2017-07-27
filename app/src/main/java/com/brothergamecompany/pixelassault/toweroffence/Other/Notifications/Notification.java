package com.brothergamecompany.pixelassault.toweroffence.Other.Notifications;

/**
 * Created by maxgm_umv4xdu on 18.07.2017.
 */

public class Notification {
    protected float x;
    protected float y;
    protected String text;
    protected float timeLeft;
    protected float R;
    protected float G;
    protected float B;
    protected float A;
    protected final float fadeTime;
    protected float glyphWidth;
    protected float glyphHeight;

    public Notification(float x, float y, String text, float duration,
                        float red, float green, float blue, float alpha, float glyphWidth, float glyphHeight) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.timeLeft = duration;
        this.R = red;
        this.G = green;
        this.B = blue;
        this.A = alpha;
        fadeTime = 1;
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;
    }
}
