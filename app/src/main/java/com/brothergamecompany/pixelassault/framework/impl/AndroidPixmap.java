package com.brothergamecompany.pixelassault.framework.impl;

import android.graphics.Bitmap;

import com.brothergamecompany.pixelassault.framework.Graphics;
import com.brothergamecompany.pixelassault.framework.Pixmap;


public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    Graphics.PixmapFormat format;
    
    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public Graphics.PixmapFormat getFormat() {
        return format;
    }

    public void dispose() {
        bitmap.recycle();
    }      
}
