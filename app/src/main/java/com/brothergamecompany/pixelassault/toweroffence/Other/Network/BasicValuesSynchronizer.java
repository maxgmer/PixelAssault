package com.brothergamecompany.pixelassault.toweroffence.Other.Network;

import android.util.Log;

import com.brothergamecompany.pixelassault.toweroffence.GameLauncher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxgm_umv4xdu on 25.08.2017.
 */

public class BasicValuesSynchronizer {
    public static final int SYNC_DELAY = 15;
    public static float stateTime = 0;
    public static int totalMonstersKilled = 0;//after last sync
    public static int totalCoins = 0;
    public static int totalExp = 0;
    public static List<Integer> killedMobs = new ArrayList<>();


    public static void update(float deltaTime){
        stateTime += deltaTime;
        if (stateTime > SYNC_DELAY && totalMonstersKilled != 0 && totalCoins != 0 && totalExp != 0) {
            Log.wtf("syncR", "HTTPSENT");
            GameLauncher.httpRequestSender.sendSyncRequest();
            stateTime = 0;
        }
    }

    public static void clear(int totalMonstersKilled, int totalCoins, int totalExp, List<Integer> levelsList) {
        BasicValuesSynchronizer.totalMonstersKilled -= totalMonstersKilled;
        BasicValuesSynchronizer.totalCoins -= totalCoins;
        BasicValuesSynchronizer.totalExp -= totalExp;
        killedMobs.removeAll(levelsList);
        levelsList.clear();
    }


}

