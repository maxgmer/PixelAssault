package com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects;

import com.brothergamecompany.pixelassault.framework.GameObject;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account;

/**
 * Created by maxgm_umv4xdu on 19.06.2017.
 */

public class SpawnerPortal extends GameObject {
    public static float PORTAL_WIDTH = 1.0f;
    public static float PORTAL_HEIGHT = 1.0f;
    public float stateTime;
    private int gridX;
    private int gridY;
    public boolean enabled;
    public SpawnerPortal() {
        super(World.tileCenterCoords(Account.MOB_SPAWNER_X), World.tileCenterCoords(Account.MOB_SPAWNER_Y), PORTAL_WIDTH, PORTAL_HEIGHT);
        gridX = Account.MOB_SPAWNER_X;
        gridY = Account.MOB_SPAWNER_Y;
        stateTime = 0;
        enabled = false;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        if (gridX != Account.MOB_SPAWNER_X || gridY != Account.MOB_SPAWNER_Y) {
            this.position.set(World.tileCenterCoords(Account.MOB_SPAWNER_X), World.tileCenterCoords(Account.MOB_SPAWNER_Y));
            this.bounds.lowerLeft.set(position.x - (PORTAL_WIDTH/2), position.y - (PORTAL_HEIGHT/2) + Tile.TILE_HEIGHT / 2);
        }
    }
}
