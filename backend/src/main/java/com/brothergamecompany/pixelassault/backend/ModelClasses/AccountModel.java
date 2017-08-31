package com.brothergamecompany.pixelassault.backend.ModelClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxgm_umv4xdu on 01.08.2017.
 */

public class AccountModel {
    //этот класс будет заполняться данными с сервера при запуске приложения
    public int WORLD_WIDTH = 16;
    public int WORLD_HEIGHT = 9;
    //нужно проверять сверяя с монетами, например убито 1000, а монет прибавилось 5 это странно, возможна покупка, перед
    //покупкой всегда обновлять данные аккаунта на сервере.
    public int totalMonstersKilled = 0;
    public String nickname = "";
    public int totalCoins = 0;
    public int currentLevel = 1;
    public int currentExp = 0;
    public int spawnPortalPower = 1;
    //public static Inventory inventory;
    //public static PowerUpsApplied powerUpsApplied;
    public List<TileModel> path;
    public List<TowerModel> towers;
    public AccountModel() {
        path = new ArrayList<>();
        towers = new ArrayList<>();
        path.add(new TileModel(4, 4, true, 3));
        path.add(new TileModel(6, 4, true, 2));
        path.add(new TileModel(5, 4, true, 1));
        towers.add(new TowerModel(5, 3, true, 0));
    }

    public int getMaxLvlExp() {
        return currentLevel * (currentLevel * 10);
    }
}
