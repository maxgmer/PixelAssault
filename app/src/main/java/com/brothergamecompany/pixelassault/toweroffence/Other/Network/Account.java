package com.brothergamecompany.pixelassault.toweroffence.Other.Network;

import android.support.v4.util.Pair;

import com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo.TowerStats;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tile;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tower;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxgm_umv4xdu on 04.06.2017.
 */

public class Account {
    public static int WORLD_WIDTH = 16;
    public static int WORLD_HEIGHT = 9;
    //нужно проверять сверяя с монетами, например убито 1000, а монет прибавилось 5 это странно, возможна покупка, перед
    //покупкой всегда обновлять данные аккаунта на сервере.
    public static int totalMonstersKilled = 0;
    public static String nickname = "maxgmer";
    public static int totalCoins = 0;
    public static int currentLevel = 5;
    public static int currentExp = 125;
    public static int spawnPortalPower = 1;
    public static float speedUpgrade = 0;
    public static float attackUpgrade = 0;
    //public static Inventory inventory;
    //public static PowerUpsApplied powerUpsApplied;
    public static List<Tile> path = new ArrayList<Tile>();
    public static List<Tower> towers = new ArrayList<>();
    public static int MOB_SPAWNER_X = 0;
    public static int MOB_SPAWNER_Y = 0;
    public static String uid;

    public static Pair<Integer, Integer> getLastPathPos() {
        for (Tile tile : path) {
            if (tile.type2 == Tile.TYPE_LAST_PATH_TILE)
                return Pair.create(tile.gridX, tile.gridY);
        }
        return null;
    }
    static {

    }

    public static int getMaxLvlExp() {
        return currentLevel * (currentLevel * 10);
    }
}

