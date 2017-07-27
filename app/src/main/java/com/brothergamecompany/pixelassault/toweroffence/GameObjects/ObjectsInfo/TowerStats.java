package com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo;

import com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets;

/**
 * Created by maxgm_umv4xdu on 24.06.2017.
 */

public class TowerStats {
    public static final int TOWER_LEVEL_1 = 0;
    public static final int TOWER_LEVEL_2_FREEZE = 1;
    public static final int TOWER_LEVEL_2_FIRE = 2;
    public static final int TOWER_LEVEL_2_POISON = 3;

    public static final float TOWER_LEVEL_1_ATT_SPD = 1;
    public static final float TOWER_LEVEL_1_DMG = 10;
    public static final float TOWER_LEVEL_1_RANGE = 1.7f;

    public static float getAnimationFrameDuration(int towerLevel){
        if (towerLevel == TOWER_LEVEL_1){
            return (1 / TOWER_LEVEL_1_ATT_SPD) / (Assets.getTowerAnimation(towerLevel).keyFramesNumber);
        }
        return Float.MAX_VALUE;
    }

    public static float getTowerAttackSpd(int towerLevel) {
        if (towerLevel == TOWER_LEVEL_1) return TOWER_LEVEL_1_ATT_SPD;
        return Float.MAX_VALUE;
    }

    public static float getTowerDamage(int towerLevel) {
        if (towerLevel == TOWER_LEVEL_1) return TOWER_LEVEL_1_DMG;
        return Float.MIN_VALUE;
    }

    public static float getTowerRange(int towerLevel) {
        if (towerLevel == TOWER_LEVEL_1) return TOWER_LEVEL_1_RANGE;
        return 0;
    }

    public static boolean ifSpins(int towerLevel) {
        if (towerLevel == TOWER_LEVEL_1) return false;
        return false;
    }

    public static float getBulletSpeed(int towerLevel) {
        if (towerLevel == TOWER_LEVEL_1) return 2.5f;
        return 0.2f;
    }

    public static float getBulletPosY(int towerLevel) {
        if (towerLevel == TOWER_LEVEL_1) return 1.1f;
        return 0f;
    }

    public static float getBulletPosX(int towerLevel) {
        if (towerLevel == TOWER_LEVEL_1) return 0f;
        return 0f;
    }
}
