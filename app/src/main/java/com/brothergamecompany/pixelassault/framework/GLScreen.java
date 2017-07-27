package com.brothergamecompany.pixelassault.framework;


import com.brothergamecompany.pixelassault.framework.impl.GLGame;
import com.brothergamecompany.pixelassault.framework.impl.GLGraphics;

/**
 * Created by maxgm on 03.05.2017.
 */
public abstract class GLScreen extends Screen {
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;

    public GLScreen(Game game) {
        super(game);
        glGame = (GLGame)game;
        glGraphics = glGame.getGLGraphics();
    }
}
