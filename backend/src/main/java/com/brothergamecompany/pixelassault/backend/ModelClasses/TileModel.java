package com.brothergamecompany.pixelassault.backend.ModelClasses;

/**
 * Created by maxgm_umv4xdu on 01.08.2017.
 */
public class TileModel {

    //type
    //public static final int TYPE_USUAL_PATH = 1;
    //public static final int TYPE_LAST_PATH_TILE = 2;
    //public static final int TYPE_FIRST_PATH_TILE = 3;
    public boolean onMap;
    public int type;
    public int gridX;
    public int gridY;

    public TileModel() {
        this.type = 1;
        onMap = false;
        gridX = 0;
        gridY = 0;
    }
    public TileModel(int type) {
        this.type = type;
        onMap = false;
        gridX = 0;
        gridY = 0;
    }
    public TileModel(int gridX, int gridY, boolean onMap, int type) {
        this.type = type;
        this.onMap = onMap;
        this.gridX = gridX;
        this.gridY = gridY;
    }

}
