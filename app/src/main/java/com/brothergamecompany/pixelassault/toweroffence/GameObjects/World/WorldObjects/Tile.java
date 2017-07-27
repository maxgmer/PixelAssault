package com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects;

import com.brothergamecompany.pixelassault.framework.GameObject;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;

/**
 * Created by maxgm_umv4xdu on 04.06.2017.
 */

public class Tile extends GameObject {
    //type
    public static final int TYPE_PATH = 1;
    public static final int TYPE_GRASS = 0;
    //secondType (for decorations)
    public static final int TYPE_LAST_PATH_TILE = 2;
    public static final int TYPE_FIRST_PATH_TILE = 3;

    public static final float TILE_WIDTH = 1.0f;
    public static final float TILE_HEIGHT = 1.0f;

    public boolean onMap;
    public int type;
    public int type2;
    public int gridX;
    public int gridY;
    //при инициализации тайла надо указывать координаты центра тайла, метод перевода в такие координаты есть в классе world.
    public void changePos(int gridX, int gridY) {
        this.position.set(World.tileCenterCoords(gridX), World.tileCenterCoords(gridY));
        this.gridX = gridX;
        this.gridY = gridY;
    }
    public Tile(int gridX, int gridY, int tileType) {
        super(World.tileCenterCoords(gridX), World.tileCenterCoords(gridY), TILE_WIDTH, TILE_HEIGHT);
        this.gridX = gridX;
        this.gridY = gridY;
        type = tileType;
        onMap = false;
        //this.bounds.lowerLeft.set(World.tileCenterCoords(gridX) - bounds.height / 3, World.tileCenterCoords(gridY) - bounds.width / 3);
    }

    public Tile(int gridX, int gridY, int tileType, int tileType2) {
        this(gridX, gridY, tileType);
        type2 = tileType2;
        onMap = false;
    }

    @Override
    public String toString() {
        return "gridX: " + gridX + "\n" +
                "gridY: " + gridY + "\n" +
                "Type: " + type + "\n" +
                "Type2: " + type2;
    }
}
