package com.brothergamecompany.pixelassault.framework;


public interface Pixmap {
    int getWidth();

    int getHeight();

    Graphics.PixmapFormat getFormat();

    void dispose();
}
