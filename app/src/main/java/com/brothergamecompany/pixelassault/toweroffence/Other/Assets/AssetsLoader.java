package com.brothergamecompany.pixelassault.toweroffence.Other.Assets;


import com.brothergamecompany.pixelassault.framework.gl.Animation;
import com.brothergamecompany.pixelassault.framework.gl.TextureRegion;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo.TowerStats;

import static com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets.objects;

/**
 * Created by maxgm_umv4xdu on 07.07.2017.
 */

class AssetsLoader {

    static boolean loadMonsters() {
        loadMonstersWalking();
        loadMonstersDeath();
        loadMonstersDrowning();
        return true;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                               Walking Animation Loading Method
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private static void loadMonstersWalking() {
        Assets.monsterLvl1 = new Animation(0.16f,
                new TextureRegion(objects, 0, 0, 16, 16),
                new TextureRegion(objects, 16, 0, 16, 16),
                new TextureRegion(objects, 32, 0, 16, 16),
                new TextureRegion(objects, 48, 0, 16, 16),
                new TextureRegion(objects, 64, 0, 16, 16),
                new TextureRegion(objects, 80, 0, 16, 16));

        Assets.monsterLvl2 = new Animation(0.16f,
                new TextureRegion(objects, 0, 16, 16, 16),
                new TextureRegion(objects, 16, 16, 16, 16),
                new TextureRegion(objects, 32, 16, 16, 16),
                new TextureRegion(objects, 48, 16, 16, 16),
                new TextureRegion(objects, 64, 16, 16, 16),
                new TextureRegion(objects, 80, 16, 16, 16));

        Assets.monsterLvl3 = new Animation(0.16f,
                new TextureRegion(objects, 0, 32, 16, 16),
                new TextureRegion(objects, 16, 32, 16, 16),
                new TextureRegion(objects, 32, 32, 16, 16),
                new TextureRegion(objects, 48, 32, 16, 16),
                new TextureRegion(objects, 64, 32, 16, 16),
                new TextureRegion(objects, 80, 32, 16, 16));

        Assets.monsterLvl4 = new Animation(0.16f,
                new TextureRegion(objects, 0, 48, 16, 16),
                new TextureRegion(objects, 16, 48, 16, 16),
                new TextureRegion(objects, 32, 48, 16, 16),
                new TextureRegion(objects, 48, 48, 16, 16),
                new TextureRegion(objects, 64, 48, 16, 16),
                new TextureRegion(objects, 80, 48, 16, 16));

        Assets.monsterLvl5 = new Animation(0.16f,
                new TextureRegion(objects, 0, 64, 16, 16),
                new TextureRegion(objects, 16, 64, 16, 16),
                new TextureRegion(objects, 32, 64, 16, 16),
                new TextureRegion(objects, 48, 64, 16, 16),
                new TextureRegion(objects, 64, 64, 16, 16),
                new TextureRegion(objects, 80, 64, 16, 16));

        Assets.monsterLvl6 = new Animation(0.16f,
                new TextureRegion(objects, 0, 80, 16, 16),
                new TextureRegion(objects, 16, 80, 16, 16),
                new TextureRegion(objects, 32, 80, 16, 16),
                new TextureRegion(objects, 48, 80, 16, 16));

        Assets.monsterLvl7 = new Animation(0.16f,
                new TextureRegion(objects, 0, 96, 16, 16),
                new TextureRegion(objects, 16, 96, 16, 16),
                new TextureRegion(objects, 32, 96, 16, 16),
                new TextureRegion(objects, 48, 96, 16, 16));

        Assets.monsterLvl8 = new Animation(0.16f,
                new TextureRegion(objects, 0, 112, 16, 16),
                new TextureRegion(objects, 16, 112, 16, 16),
                new TextureRegion(objects, 32, 112, 16, 16),
                new TextureRegion(objects, 48, 112, 16, 16));

        Assets.monsterLvl9 = new Animation(0.16f,
                new TextureRegion(objects, 0, 128, 16, 16),
                new TextureRegion(objects, 16, 128, 16, 16),
                new TextureRegion(objects, 32, 128, 16, 16),
                new TextureRegion(objects, 48, 128, 16, 16));

        Assets.monsterLvl10 = new Animation(0.16f,
                new TextureRegion(objects, 0, 144, 16, 16),
                new TextureRegion(objects, 16, 144, 16, 16),
                new TextureRegion(objects, 32, 144, 16, 16),
                new TextureRegion(objects, 48, 144, 16, 16));

        Assets.monsterLvl11 = new Animation(0.16f,
                new TextureRegion(objects, 0, 368, 16, 16),
                new TextureRegion(objects, 16, 368, 16, 16),
                new TextureRegion(objects, 32, 368, 16, 16),
                new TextureRegion(objects, 48, 368, 16, 16));

        Assets.monsterLvl12 = new Animation(0.16f,
                new TextureRegion(objects, 0, 384, 16, 16),
                new TextureRegion(objects, 16, 384, 16, 16),
                new TextureRegion(objects, 32, 384, 16, 16),
                new TextureRegion(objects, 48, 384, 16, 16));

        Assets.monsterLvl13 = new Animation(0.16f,
                new TextureRegion(objects, 0, 400, 16, 16),
                new TextureRegion(objects, 16, 400, 16, 16),
                new TextureRegion(objects, 32, 400, 16, 16),
                new TextureRegion(objects, 48, 400, 16, 16));

        Assets.monsterLvl14 = new Animation(0.16f,
                new TextureRegion(objects, 0, 416, 16, 16),
                new TextureRegion(objects, 16, 416, 16, 16),
                new TextureRegion(objects, 32, 416, 16, 16),
                new TextureRegion(objects, 48, 416, 16, 16));

        Assets.monsterLvl15 = new Animation(0.16f,
                new TextureRegion(objects, 0, 432, 16, 16),
                new TextureRegion(objects, 16, 432, 16, 16),
                new TextureRegion(objects, 32, 432, 16, 16),
                new TextureRegion(objects, 48, 432, 16, 16));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                               Drowning Animation Loading Method
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private static void loadMonstersDrowning(){
        Assets.monsterSinkingLvl1 = new Animation(0.20f,
                new TextureRegion(objects, 0, 0, 16, 16),
                new TextureRegion(objects, 160, 0, 16, 16),
                new TextureRegion(objects, 176, 0, 16, 16));

        Assets.monsterSinkingLvl2 = new Animation(0.20f,
                new TextureRegion(objects, 0, 16, 16, 16),
                new TextureRegion(objects, 160, 16, 16, 16),
                new TextureRegion(objects, 176, 16, 16, 16));

        Assets.monsterSinkingLvl3 = new Animation(0.20f,
                new TextureRegion(objects, 0, 32, 16, 16),
                new TextureRegion(objects, 160, 32, 16, 16),
                new TextureRegion(objects, 176, 32, 16, 16));

        Assets.monsterSinkingLvl4 = new Animation(0.20f,
                new TextureRegion(objects, 0, 48, 16, 16),
                new TextureRegion(objects, 160, 48, 16, 16),
                new TextureRegion(objects, 176, 48, 16, 16));

        Assets.monsterSinkingLvl5 = new Animation(0.20f,
                new TextureRegion(objects, 0, 64, 16, 16),
                new TextureRegion(objects, 160, 64, 16, 16),
                new TextureRegion(objects, 176, 64, 16, 16));

        Assets.monsterSinkingLvl6 = new Animation(0.20f,
                new TextureRegion(objects, 0, 80, 16, 16),
                new TextureRegion(objects, 112, 80, 16, 16),
                new TextureRegion(objects, 128, 80, 16, 16));

        Assets.monsterSinkingLvl7 = new Animation(0.20f,
                new TextureRegion(objects, 0, 96, 16, 16),
                new TextureRegion(objects, 112, 96, 16, 16),
                new TextureRegion(objects, 128, 96, 16, 16));

        Assets.monsterSinkingLvl8 = new Animation(0.20f,
                new TextureRegion(objects, 0, 112, 16, 16),
                new TextureRegion(objects, 112, 112, 16, 16),
                new TextureRegion(objects, 128, 112, 16, 16));

        Assets.monsterSinkingLvl9 = new Animation(0.20f,
                new TextureRegion(objects, 0, 128, 16, 16),
                new TextureRegion(objects, 112, 128, 16, 16),
                new TextureRegion(objects, 128, 128, 16, 16));

        Assets.monsterSinkingLvl10 = new Animation(0.20f,
                new TextureRegion(objects, 0, 144, 16, 16),
                new TextureRegion(objects, 112, 144, 16, 16),
                new TextureRegion(objects, 128, 144, 16, 16));

        Assets.monsterSinkingLvl11 = new Animation(0.20f,
                new TextureRegion(objects, 128, 368, 16, 16),
                new TextureRegion(objects, 144, 368, 16, 16));

        Assets.monsterSinkingLvl12 = new Animation(0.20f,
                new TextureRegion(objects, 112, 384, 16, 16),
                new TextureRegion(objects, 128, 384, 16, 16));

        Assets.monsterSinkingLvl13 = new Animation(0.20f,
                new TextureRegion(objects, 112, 400, 16, 16),
                new TextureRegion(objects, 128, 400, 16, 16));

        Assets.monsterSinkingLvl14 = new Animation(0.20f,
                new TextureRegion(objects, 112, 416, 16, 16),
                new TextureRegion(objects, 128, 416, 16, 16));

        Assets.monsterSinkingLvl15 = new Animation(0.20f,
                new TextureRegion(objects, 112, 432, 16, 16),
                new TextureRegion(objects, 128, 432, 16, 16));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                               Death Animation Loading Method
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private static void loadMonstersDeath() {
        Assets.monsterDeathLvl1 = new Animation(0.16f,
                new TextureRegion(objects, 0, 0, 16, 16),
                new TextureRegion(objects, 96, 0, 16, 16),
                new TextureRegion(objects, 112, 0, 16, 16),
                new TextureRegion(objects, 128, 0, 16, 16),
                new TextureRegion(objects, 144, 0, 16, 16));

        Assets.monsterDeathLvl2 = new Animation(0.16f,
                new TextureRegion(objects, 0, 16, 16, 16),
                new TextureRegion(objects, 96, 16, 16, 16),
                new TextureRegion(objects, 112, 16, 16, 16),
                new TextureRegion(objects, 128, 16, 16, 16),
                new TextureRegion(objects, 144, 16, 16, 16));

        Assets.monsterDeathLvl3 = new Animation(0.16f,
                new TextureRegion(objects, 0, 32, 16, 16),
                new TextureRegion(objects, 96, 32, 16, 16),
                new TextureRegion(objects, 112, 32, 16, 16),
                new TextureRegion(objects, 128, 32, 16, 16),
                new TextureRegion(objects, 144, 32, 16, 16));

        Assets.monsterDeathLvl4 = new Animation(0.16f,
                new TextureRegion(objects, 0, 48, 16, 16),
                new TextureRegion(objects, 96, 48, 16, 16),
                new TextureRegion(objects, 112, 48, 16, 16),
                new TextureRegion(objects, 128, 48, 16, 16),
                new TextureRegion(objects, 144, 48, 16, 16));

        Assets.monsterDeathLvl5 = new Animation(0.16f,
                new TextureRegion(objects, 0, 64, 16, 16),
                new TextureRegion(objects, 96, 64, 16, 16),
                new TextureRegion(objects, 112, 64, 16, 16),
                new TextureRegion(objects, 128, 64, 16, 16),
                new TextureRegion(objects, 144, 64, 16, 16));

        Assets.monsterDeathLvl6 = new Animation(0.16f,
                new TextureRegion(objects, 0, 80, 16, 16),
                new TextureRegion(objects, 64, 80, 16, 16),
                new TextureRegion(objects, 80, 80, 16, 16),
                new TextureRegion(objects, 96, 80, 16, 16));

        Assets.monsterDeathLvl7 = new Animation(0.16f,
                new TextureRegion(objects, 0, 96, 16, 16),
                new TextureRegion(objects, 64, 96, 16, 16),
                new TextureRegion(objects, 80, 96, 16, 16),
                new TextureRegion(objects, 96, 96, 16, 16));

        Assets.monsterDeathLvl8 = new Animation(0.16f,
                new TextureRegion(objects, 0, 112, 16, 16),
                new TextureRegion(objects, 64, 112, 16, 16),
                new TextureRegion(objects, 80, 112, 16, 16),
                new TextureRegion(objects, 96, 112, 16, 16));

        Assets.monsterDeathLvl9 = new Animation(0.16f,
                new TextureRegion(objects, 0, 128, 16, 16),
                new TextureRegion(objects, 64, 128, 16, 16),
                new TextureRegion(objects, 80, 128, 16, 16),
                new TextureRegion(objects, 96, 128, 16, 16));

        Assets.monsterDeathLvl10 = new Animation(0.16f,
                new TextureRegion(objects, 0, 144, 16, 16),
                new TextureRegion(objects, 64, 144, 16, 16),
                new TextureRegion(objects, 80, 144, 16, 16),
                new TextureRegion(objects, 96, 144, 16, 16));

        Assets.monsterDeathLvl11 = new Animation(0.16f,
                new TextureRegion(objects, 64, 368, 16, 16),
                new TextureRegion(objects, 80, 368, 16, 16),
                new TextureRegion(objects, 96, 368, 16, 16),
                new TextureRegion(objects, 112, 368, 16, 16));

        Assets.monsterDeathLvl12 = new Animation(0.16f,
                new TextureRegion(objects, 64, 384, 16, 16),
                new TextureRegion(objects, 80, 384, 16, 16),
                new TextureRegion(objects, 96, 384, 16, 16),
                new TextureRegion(objects, 112, 368, 16, 16));

        Assets.monsterDeathLvl13 = new Animation(0.16f,
                new TextureRegion(objects, 64, 400, 16, 16),
                new TextureRegion(objects, 80, 400, 16, 16),
                new TextureRegion(objects, 96, 400, 16, 16),
                new TextureRegion(objects, 112, 368, 16, 16));

        Assets.monsterDeathLvl14 = new Animation(0.16f,
                new TextureRegion(objects, 64, 416, 16, 16),
                new TextureRegion(objects, 80, 416, 16, 16),
                new TextureRegion(objects, 96, 416, 16, 16),
                new TextureRegion(objects, 112, 368, 16, 16));

        Assets.monsterDeathLvl15 = new Animation(0.16f,
                new TextureRegion(objects, 64, 432, 16, 16),
                new TextureRegion(objects, 80, 432, 16, 16),
                new TextureRegion(objects, 96, 432, 16, 16),
                new TextureRegion(objects, 112, 368, 16, 16));
    }

    public static boolean loadTowers() {
        Assets.towerLevel1Bullet = new TextureRegion(objects, 32, 160, 8, 8);
        Assets.towerLevel1 = new Animation(
                new TextureRegion(objects, 0, 336, 32, 32),
                new TextureRegion(objects, 32, 336, 32, 32),
                new TextureRegion(objects, 64, 336, 32, 32),
                new TextureRegion(objects, 96, 336, 32, 32),
                new TextureRegion(objects, 128, 336, 32, 32),
                new TextureRegion(objects, 160, 336, 32, 32),
                new TextureRegion(objects, 192, 336, 32, 32)
        );
        Assets.towerLevel1.setFrameDuration(TowerStats.getAnimationFrameDuration(TowerStats.TOWER_LEVEL_1));
        return true;
    }
}
