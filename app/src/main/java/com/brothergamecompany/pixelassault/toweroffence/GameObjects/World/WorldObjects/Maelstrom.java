package com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects;

import com.brothergamecompany.pixelassault.framework.GameObject;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account;

/**
 * Created by maxgm_umv4xdu on 19.06.2017.
 */

public class Maelstrom extends GameObject {
    public static float MAELSTROM_WIDTH = 1.1f;
    public static float MAELSTROM_HEIGHT = 1.1f;
    public int angle;
    public Maelstrom() {
        super(World.tileCenterCoords(Account.getLastPathPos().first), World.tileCenterCoords(Account.getLastPathPos().second), MAELSTROM_WIDTH, MAELSTROM_HEIGHT);
        angle = 0;
    }
    public void updatePos() {
        this.position.set(World.tileCenterCoords(Account.getLastPathPos().first), World.tileCenterCoords(Account.getLastPathPos().second));
    }

    public void update(float deltaTime) {
        angle -= 180 * deltaTime;
    }
}
