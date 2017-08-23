package com.brothergamecompany.pixelassault.backend.ModelClasses;
/**
 * Created by maxgm_umv4xdu on 01.08.2017.
 */

public class TowerModel {
    public boolean onMap;
    public int gridX;
    public int gridY;
    public int towerLevel;
    public TowerModel() {
        onMap = false;
        gridX = 0;
        gridY = 0;
        towerLevel = 0;
    }

    public TowerModel(int gridX, int gridY, boolean onMap, int towerLevel) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.onMap = onMap;
        this.towerLevel = towerLevel;
    }
}
