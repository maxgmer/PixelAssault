package com.brothergamecompany.pixelassault.toweroffence.GameObjects;

import android.content.Context;
import android.util.Log;

import com.brothergamecompany.pixelassault.R;
import com.brothergamecompany.pixelassault.framework.Game;
import com.brothergamecompany.pixelassault.framework.Input;
import com.brothergamecompany.pixelassault.framework.gl.Animation;
import com.brothergamecompany.pixelassault.framework.gl.Camera2D;
import com.brothergamecompany.pixelassault.framework.gl.SpriteBatcher;
import com.brothergamecompany.pixelassault.framework.gl.SpriteBatcherColored;
import com.brothergamecompany.pixelassault.framework.gl.TextureRegion;
import com.brothergamecompany.pixelassault.framework.math.OverlapTester;
import com.brothergamecompany.pixelassault.framework.math.Rectangle;
import com.brothergamecompany.pixelassault.framework.math.Vector2;
import com.brothergamecompany.pixelassault.toweroffence.GameLauncher;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.UserInterface.UIAndTouchListening;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Maelstrom;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tile;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tower;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldRenderer;
import com.brothergamecompany.pixelassault.toweroffence.GameScreens.GameScreen;
import com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets;
import com.brothergamecompany.pixelassault.toweroffence.Other.Font;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.HttpRequestSender;
import com.brothergamecompany.pixelassault.toweroffence.Other.Notifications.NotificationManager;

import java.util.ArrayList;
import java.util.List;

import static com.brothergamecompany.pixelassault.toweroffence.GameScreens.GameScreen.world;
import static com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets.confirmButtonPressed;
import static com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets.font;

/**
 * Created by maxgm_umv4xdu on 03.07.2017.
 */

public class MapBuilder {

    public enum CellContent{
        Path, SpawnerPortal, Maelstrom, Towers
    }
    private static final float HALF_BORDER_WIDTH = (Tile.TILE_WIDTH/7)/2;
    private static final float HALF_BORDER_HEIGHT = (Tile.TILE_HEIGHT/7)/2;
    public static final int MAP_BUILDER_START_MENU = 0;
    public static final int MAP_BUILDER_ERASER_ENABLED = 1;
    public static final int MAP_BUILDER_CONFIRM_REQUEST_SENT = 2;
    public static int mapBuilderState = MAP_BUILDER_START_MENU;
    private final Game game;
    private float stateTime;
    private final Camera2D guiCam;
    private final Camera2D worldCam;
    private final SpriteBatcher batcher;
    private final SpriteBatcherColored coloredBatcher;
    private Rectangle mapBuilderMove;
    private Rectangle mapBuilderDeleteAll;
    Vector2 touchPoint;
    Vector2 touchPoint2;
    public static boolean enabled;
    public static boolean baseConfirmed;
    private List<Tower> availableTowers;
    private List<Tile> availablePathTiles;
    private Tile firstTile;
    private Tile lastTile;
    private Maelstrom maelstrom;

    private boolean cellOrderNeedsUpdate;
    private List<CellContent> cellContentOrder;
    public static CellContent cellChosen = null;
    private Rectangle cellOne = new Rectangle(130, 15, 120, 120);
    private Rectangle cellTwo = new Rectangle(310, 15, 120, 120);
    private Rectangle cellThree = new Rectangle(490, 15, 120, 120);
    private Rectangle cellFour = new Rectangle(670, 15, 120, 120);
    private Rectangle cellFive = new Rectangle(850, 15, 120, 120);
    private Rectangle cellSix = new Rectangle(1030, 15, 120, 120);
    private Rectangle reChooseCell = new Rectangle(1090, 100, 100, 100);
    private Rectangle confirmButton = new Rectangle(1160, 600, 100, 100);
    private Rectangle eraser = new Rectangle(1160, 200, 100, 100);
    private Vector2 drawCoordsForChosenCell;

    private boolean enabledEraserPressed = false;
    private boolean eraserPressed = false;
    private boolean confirmButtonPressed = false;

    public MapBuilder(Game game, Camera2D guiCam, Camera2D worldCam, SpriteBatcher batcher, SpriteBatcherColored coloredBatcher) {
        this.worldCam = worldCam;
        this.game = game;
        this.guiCam = guiCam;
        this.batcher = batcher;
        this.coloredBatcher = coloredBatcher;
        mapBuilderMove = new Rectangle(400, 80, 200, 200);
        mapBuilderDeleteAll = new Rectangle(680, 80, 200, 200);
        touchPoint = new Vector2();
        touchPoint2 = new Vector2();
        enabled = false;
        drawCoordsForChosenCell = new Vector2();
    }

    public void enterMapBuilder() {
        WorldRenderer.stopDrawingMonsters();
        World.stopUpdatingMonsters();
        maelstrom = new Maelstrom();
        cellContentOrder = new ArrayList<>();
        stateTime = 0;
        availableTowers = new ArrayList<>();
        availablePathTiles = new ArrayList<>();
        for (int i = 0; i < Account.path.size(); i++) {
            if (!Account.path.get(i).onMap && Account.path.get(i).type2 != Tile.TYPE_FIRST_PATH_TILE && Account.path.get(i).type2 != Tile.TYPE_LAST_PATH_TILE)
                availablePathTiles.add(Account.path.get(i));
            else if (Account.path.get(i).type2 == Tile.TYPE_FIRST_PATH_TILE && !Account.path.get(i).onMap) firstTile = Account.path.get(i);
            else if (Account.path.get(i).type2 == Tile.TYPE_LAST_PATH_TILE && !Account.path.get(i).onMap) lastTile = Account.path.get(i);
        }
        for (int i = 0; i < Account.towers.size(); i++) {
            if (!Account.towers.get(i).onMap)
                availableTowers.add(Account.towers.get(i));
        }
        enabled = true;
        baseConfirmed = false;
        cellOrderNeedsUpdate = true;
    }

    public void draw() {
        if (enabled) {
            for (int i = 0; i < World.worldGrid.length; i++) {
                for (int j = 0; j < World.worldGrid[i].length; j++) {
                    Vector2 position = World.worldGrid[i][j].position;
                    batcher.drawSprite(position.x - HALF_BORDER_WIDTH, position.y + HALF_BORDER_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, Assets.gridBorder.getKeyFrame(stateTime, Animation.ANIMATION_LOOPING));
                    if (i == World.worldGrid.length - 1) {
                        batcher.drawSprite(position.x + 1 - HALF_BORDER_WIDTH, position.y + HALF_BORDER_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, Assets.gridBorder.getKeyFrame(stateTime, Animation.ANIMATION_LOOPING));
                    }
                    if (j == 0) {
                        batcher.drawSprite(position.x - HALF_BORDER_WIDTH, position.y - 1f + HALF_BORDER_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, Assets.gridBorder.getKeyFrame(stateTime, Animation.ANIMATION_LOOPING));
                    }
                }
            }
        }
    }

    public void update(float deltaTime, List<Input.TouchEvent> touchEvents) {
        if (enabled) {
            maelstrom.update(deltaTime);
            stateTime += deltaTime;

            switch (mapBuilderState) {
                case MAP_BUILDER_START_MENU:
                    updateMapBuilderMain(deltaTime, touchEvents);
                    break;
                case MAP_BUILDER_ERASER_ENABLED:
                    updateEraserState(deltaTime, touchEvents);
                    break;
                case MAP_BUILDER_CONFIRM_REQUEST_SENT:
                    updateConfirmRequestSent(deltaTime, touchEvents);
            }
        }
    }

    private void updateConfirmRequestSent(float deltaTime, List<Input.TouchEvent> touchEvents) {
        if (baseConfirmed) {
            UIAndTouchListening.state = UIAndTouchListening.USUAL_GAME_STATE;
            mapBuilderState = MAP_BUILDER_START_MENU;
            MapBuilder.enabled = false;
            World.startUpdatingMonstersAndTowers();
            WorldRenderer.startDrawingMonsters();
        }
    }

    private void updateEraserState(float deltaTime, List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent touchEvent = touchEvents.get(i);
            touchPoint.set(touchEvent.x, touchEvent.y);
            touchPoint2.set(touchEvent.x, touchEvent.y);
            guiCam.touchToWorld(touchPoint);
            WorldRenderer.worldCam.touchToWorld(touchPoint2);
            if (OverlapTester.pointInRectangle(eraser, touchPoint)){
                if (touchEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    enabledEraserPressed = true;
                }
                if (enabledEraserPressed) {
                    if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                        mapBuilderState = MAP_BUILDER_START_MENU;
                        cellOrderNeedsUpdate = true;
                        cellContentOrder.clear();
                        enabledEraserPressed = false;
                    }
                }
            } else enabledEraserPressed = false;
        }

        for (int i = 0; i < World.path.size(); i++) {
            Tile tile = World.path.get(i);
            if ((int) tile.position.x == (int) touchPoint2.x && (int) tile.position.y == (int) touchPoint2.y) {
                tile.onMap = false;
                if (tile.type2 == Tile.TYPE_FIRST_PATH_TILE) {
                    firstTile = tile;
                    World.firstTileOnMap = false;
                }
                else if (tile.type2 == Tile.TYPE_LAST_PATH_TILE) {
                    lastTile = tile;
                    World.lastTileOnMap = false;
                    World.maelstrom.updatePos();
                }
                else this.availablePathTiles.add(tile);
                World.path.remove(tile);
            }
        }
        for (int i = 0; i < World.towers.size(); i++) {
            Tower tower = World.towers.get(i);
            if ((int) tower.position.x == (int) touchPoint2.x && (int) tower.position.y == (int) touchPoint2.y) {
                tower.onMap = false;
                this.availableTowers.add(tower);
                World.towers.remove(tower);
            }
        }
    }


    private void updateMapBuilderMain(float deltaTime, List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent touchEvent = touchEvents.get(i);
            touchPoint.set(touchEvent.x, touchEvent.y);
            touchPoint2.set(touchEvent.x, touchEvent.y);
            guiCam.touchToWorld(touchPoint);
            WorldRenderer.worldCam.touchToWorld(touchPoint2);


            if (OverlapTester.pointInRectangle(eraser, touchPoint)){
                if (touchEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    eraserPressed = true;
                }
                if (eraserPressed) {
                    if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                        if (cellChosen == null) {
                            mapBuilderState = MAP_BUILDER_ERASER_ENABLED;
                            eraserPressed = false;
                        }
                    }
                }
            } else eraserPressed = false;


            if (OverlapTester.pointInRectangle(confirmButton, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    confirmButtonPressed = true;
                }
                if (confirmButtonPressed) {
                    if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                        if (cellChosen == null) {
                            if (World.reInitializeWorld()) {
                                mapBuilderState = MAP_BUILDER_CONFIRM_REQUEST_SENT;
                                GameLauncher.httpRequestSender.confirmMapBuilderBase();
                            } else {
                                NotificationManager.makeNotification(300, 600, ((Context) game).getResources().getString(R.string.mapBuilder_error_no_way_to_finish), 1, 1f, 1f, 0f, 20, 26);
                            }
                            confirmButtonPressed = false;
                        }
                    }
                }
            } else confirmButtonPressed = false;


            if (OverlapTester.pointInRectangle(cellOne, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    if (cellChosen == null)
                        try {
                            cellChosen = cellContentOrder.get(0);
                            stateTime = 0;
                        }
                        catch (IndexOutOfBoundsException e) {
                            cellChosen = null;
                        }
                }
            }


            if (OverlapTester.pointInRectangle(cellTwo, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    if (cellChosen == null)
                        try {
                            cellChosen = cellContentOrder.get(1);
                            stateTime = 0;
                        } catch (IndexOutOfBoundsException e) {
                            cellChosen = null;
                        }
                }
            }


            if (OverlapTester.pointInRectangle(cellThree, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    if (cellChosen == null)
                        try {
                            cellChosen = cellContentOrder.get(2);
                            stateTime = 0;
                        } catch (IndexOutOfBoundsException e) {
                            cellChosen = null;
                        }
                }
            }


            if (OverlapTester.pointInRectangle(cellFour, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    if (cellChosen == null)
                        try {
                            cellChosen = cellContentOrder.get(3);
                            stateTime = 0;
                        } catch (IndexOutOfBoundsException e) {
                            cellChosen = null;
                        }
                }
            }


            if (OverlapTester.pointInRectangle(cellFive, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    if (cellChosen == null)
                        try {
                            cellChosen = cellContentOrder.get(4);
                            stateTime = 0;
                        } catch (IndexOutOfBoundsException e) {
                            cellChosen = null;
                        }
                }
            }


            if (OverlapTester.pointInRectangle(cellSix, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_DOWN) {
                    if (cellChosen == null)
                        try {
                            cellChosen = cellContentOrder.get(5);
                            stateTime = 0;
                        } catch (IndexOutOfBoundsException e) {
                            cellChosen = null;
                        }
                }
            }


            if (cellChosen != null) {
                drawCoordsForChosenCell.set(touchPoint);
                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    boolean cellFree = true;
                    boolean wasRecycled = false;
                    if (OverlapTester.pointInRectangle(reChooseCell, touchPoint)) {
                        cellFree = false;
                        wasRecycled = true;
                    }
                    for (Tile tile : World.path) {
                        if ((int) tile.position.x == (int) touchPoint2.x && (int) tile.position.y == (int) touchPoint2.y) {
                            cellFree = false;
                        }
                    }
                    for (Tower tower : World.towers) {
                        if ((int) tower.position.x == (int) touchPoint2.x && (int) tower.position.y == (int) touchPoint2.y) {
                            cellFree = false;
                        }
                    }
                    if (cellFree) {
                        if (cellChosen == CellContent.Path) {
                            Tile tile = availablePathTiles.remove(0);
                            tile.changePos((int) touchPoint2.x, (int) touchPoint2.y);
                            World.path.add(tile);
                            tile.onMap = true;
                        }
                        if (cellChosen == CellContent.SpawnerPortal) {
                            firstTile.changePos((int) touchPoint2.x, (int) touchPoint2.y);
                            World.path.add(firstTile);
                            Account.MOB_SPAWNER_X = (int) touchPoint2.x;
                            Account.MOB_SPAWNER_Y = (int) touchPoint2.y;
                            World.firstTileOnMap = true;
                            firstTile.onMap = true;
                            firstTile = null;
                            World.fillWorldGrid();
                        }
                        if (cellChosen == CellContent.Maelstrom) {
                            lastTile.changePos((int) touchPoint2.x, (int) touchPoint2.y);
                            lastTile.onMap = true;
                            World.path.add(lastTile);
                            World.lastTileOnMap = true;
                            lastTile = null;
                            World.maelstrom.updatePos();
                            World.fillWorldGrid();
                        }
                        if (cellChosen == CellContent.Towers) {
                            Tower tower = availableTowers.remove(0);
                            tower.changePos((int) touchPoint2.x, (int) touchPoint2.y);
                            tower.onMap = true;
                            World.towers.add(tower);
                        }
                    } else {
                        if (!wasRecycled)
                            NotificationManager.makeNotification(450, 600, ((Context) game).getResources().getString(R.string.map_builder_error_cell_busy), 1, 1f, 1f, 0f, 20, 26);
                    }
                    stateTime = 0;
                    cellChosen = null;
                    cellOrderNeedsUpdate = true;
                    cellContentOrder.clear();
                }
            }
        }
    }


    public void drawUI() {
        if (enabled) {
            switch (mapBuilderState) {
                case MAP_BUILDER_START_MENU:
                    if (cellChosen != null) {
                        batcher.drawSprite(640, 100, 1280, 200, Assets.mapBuilderPaneClose.getKeyFrame(stateTime, Animation.ANIMATION_NONLOOPING));
                        if (stateTime > Assets.mapBuilderPaneClose.animationLength)
                            batcher.drawSprite(1140, 150, 100, 100, Assets.reChooseCell);
                    }

                    if (cellChosen == null) {
                        batcher.drawSprite(640, 100, 1280, 200, Assets.mapBuilderPaneOpen.getKeyFrame(stateTime, Animation.ANIMATION_NONLOOPING));
                        if (stateTime > Assets.mapBuilderPaneOpen.animationLength) {
                            batcher.drawSprite(1210, 650, 100, 100, confirmButtonPressed ? Assets.confirmButtonPressed : Assets.confirmButton);
                            batcher.drawSprite(1210, 250, 100, 100, Assets.roundButton);
                            batcher.drawSprite(1210, 250, 85, 85, Assets.eraser);
                            int cellNumber = 0;
                            if (availablePathTiles.size() != 0) {
                                batcher.drawSprite(190 + (cellNumber * 180), 75, 70, 70, Assets.tilePath);
                                cellNumber++;
                                if (cellOrderNeedsUpdate)
                                    cellContentOrder.add(CellContent.Path);
                            }
                            if (firstTile != null) {
                                batcher.drawSprite(190 + (cellNumber * 180), 75, 70, 70, Assets.portalStartEnabled.getKeyFrame(stateTime, Animation.ANIMATION_LOOPING));
                                cellNumber++;
                                if (cellOrderNeedsUpdate)
                                    cellContentOrder.add(CellContent.SpawnerPortal);
                            }
                            if (lastTile != null) {
                                batcher.drawSprite(190 + (cellNumber * 180), 75, 70, 70, maelstrom.angle, Assets.maelstromEnd);
                                cellNumber++;
                                if (cellOrderNeedsUpdate)
                                    cellContentOrder.add(CellContent.Maelstrom);
                            }
                            if (availableTowers.size() != 0) {
                                int towerLevel = availableTowers.get(0).towerLevel;
                                batcher.drawSprite(190 + (cellNumber * 180), 75, 100, 100, Assets.getTowerAnimation(towerLevel).getKeyFrame(stateTime, Animation.ANIMATION_LOOPING));
                                cellNumber++;
                                if (cellOrderNeedsUpdate)
                                    cellContentOrder.add(CellContent.Towers);
                            }
                            batcher.drawSprite(190, 75, 120, 120, Assets.mapBuilderCellBackground);
                            batcher.drawSprite(370, 75, 120, 120, Assets.mapBuilderCellBackground);
                            batcher.drawSprite(550, 75, 120, 120, Assets.mapBuilderCellBackground);
                            batcher.drawSprite(730, 75, 120, 120, Assets.mapBuilderCellBackground);
                            batcher.drawSprite(910, 75, 120, 120, Assets.mapBuilderCellBackground);
                            batcher.drawSprite(1090, 75, 120, 120, Assets.mapBuilderCellBackground);

                            cellOrderNeedsUpdate = false;
                        }
                    }
                    if (cellChosen != null) {
                        TextureRegion textureRegion = null;
                        int angle = 0;
                        int width = 70;
                        int height = 70;
                        if (cellChosen == CellContent.Path) textureRegion = Assets.tilePath;
                        if (cellChosen == CellContent.SpawnerPortal) textureRegion = Assets.portalStartEnabled.getKeyFrame(stateTime, Animation.ANIMATION_LOOPING);
                        if (cellChosen == CellContent.Maelstrom) {textureRegion = Assets.maelstromEnd; angle = maelstrom.angle;}
                        if (cellChosen == CellContent.Towers) {
                            textureRegion = Assets.getTowerAnimation(availableTowers.get(0).towerLevel).getKeyFrame(stateTime, Animation.ANIMATION_LOOPING);
                            width = 100;
                            height = 100;
                        }
                        batcher.drawSprite(drawCoordsForChosenCell.x, drawCoordsForChosenCell.y, width, height, angle, textureRegion);
                    }
                    break;
                case MAP_BUILDER_ERASER_ENABLED:
                    batcher.drawSprite(1210, 250, 100, 100, Assets.roundButtonPressed);
                    batcher.drawSprite(1210, 250, 85, 85, Assets.eraser);
                    break;
                case MAP_BUILDER_CONFIRM_REQUEST_SENT:
                    if (!baseConfirmed) {
                        batcher.drawSprite(640, 360, 440, 260, Assets.horizontalRectanglePane);
                        Font.spaceBetweenLetters = 3;
                        //middleOfScreen - glyphWidth + spaceBetweenLetters * StringLength / 2 = text centered
                        font.drawColoredText(coloredBatcher, ((Context)game).getString(R.string.confirmingMapBuilder1), 640 - (((15 + 3) * 10)/2), 420, 0.3f, 0.3f, 0.3f, 1, 15, 19.5f);
                        font.drawColoredText(coloredBatcher, ((Context)game).getString(R.string.confirmingMapBuilder2), 640 - (((15 + 3) * 11)/2), 390, 0.3f, 0.3f, 0.3f, 1, 15, 19.5f);
                    }
                    break;
            }
        }
    }
}
