package com.brothergamecompany.pixelassault.toweroffence.GameScreens;

import com.brothergamecompany.pixelassault.framework.GLScreen;
import com.brothergamecompany.pixelassault.framework.Game;
import com.brothergamecompany.pixelassault.framework.gl.Camera2D;
import com.brothergamecompany.pixelassault.framework.gl.SpriteBatcher;
import com.brothergamecompany.pixelassault.framework.gl.SpriteBatcherColored;
import com.brothergamecompany.pixelassault.toweroffence.GameLauncher;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.MapBuilder;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.UserInterface.UIAndTouchListening;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldRenderer;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.BasicValuesSynchronizer;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.HttpRequestSender;
import com.brothergamecompany.pixelassault.toweroffence.Other.Notifications.NotificationManager;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maxgm_umv4xdu on 04.06.2017.
 */

public class GameScreen extends GLScreen {

    public static World world;
    private WorldRenderer renderer;
    private UIAndTouchListening ui;
    private Camera2D worldCam;
    private Camera2D guiCam;
    public static MapBuilder mapBuilder;


    public GameScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 1280, 720);
        worldCam = new Camera2D(glGraphics, WorldRenderer.FRUSTRUM_WIDTH, WorldRenderer.FRUSTRUM_HEIGHT);
        SpriteBatcher batcher = new SpriteBatcher(glGraphics, 10000);
        SpriteBatcherColored coloredBatcher = new SpriteBatcherColored(glGraphics, 10000);
        mapBuilder = new MapBuilder(glGame, guiCam, worldCam, batcher, coloredBatcher);
        world = new World();
        renderer = new WorldRenderer(glGraphics, batcher, world, mapBuilder, worldCam);
        ui = new UIAndTouchListening(glGame, glGraphics, batcher, coloredBatcher, world, mapBuilder, guiCam);
        NotificationManager.initNotificationManager(coloredBatcher, glGraphics);
    }

    @Override
    public void update(float deltaTime) {
        if (GameLauncher.accountLoaded) {
            world.update(deltaTime);
            ui.update(deltaTime);
        } else {
            LoadingScreen.update();
        }
    }


    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        if (GameLauncher.accountLoaded) {
            renderer.render();
            ui.render();
        } else {
            LoadingScreen.present();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
