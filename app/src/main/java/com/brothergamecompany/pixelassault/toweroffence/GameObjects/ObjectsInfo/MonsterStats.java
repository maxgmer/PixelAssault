package com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo;

/**
 * Created by maxgm_umv4xdu on 10.06.2017.
 */

public class MonsterStats {
    private static final float monsterLvl1Speed = 1;
    private static final float monsterLvl2Speed = 1;
    private static final float monsterLvl3Speed = 1;
    private static final float monsterLvl4Speed = 1;
    private static final float monsterLvl5Speed = 1;
    private static final float monsterLvl6Speed = 1.5f;
    private static final float monsterLvl7Speed = 1.5f;
    private static final float monsterLvl8Speed = 1.5f;
    private static final float monsterLvl9Speed = 1.5f;
    private static final float monsterLvl10Speed = 1.5f;
    private static final float monsterLvl11Speed = 2f;
    private static final float monsterLvl12Speed = 2f;
    private static final float monsterLvl13Speed = 2f;
    private static final float monsterLvl14Speed = 2f;
    private static final float monsterLvl15Speed = 2f;

    private static final int monsterLvl1Hp = 20;
    private static final int monsterLvl2Hp = 35;
    private static final int monsterLvl3Hp = 50;
    private static final int monsterLvl4Hp = 75;
    private static final int monsterLvl5Hp = 100;
    private static final int monsterLvl6Hp = 200;
    private static final int monsterLvl7Hp = 250;
    private static final int monsterLvl8Hp = 300;
    private static final int monsterLvl9Hp = 350;
    private static final int monsterLvl10Hp = 400;
    private static final int monsterLvl11Hp = 500;
    private static final int monsterLvl12Hp = 550;
    private static final int monsterLvl13Hp = 600;
    private static final int monsterLvl14Hp = 650;
    private static final int monsterLvl15Hp = 900;


    public static float getMonsterSpeed(int monsterLevel) {
        if (monsterLevel == 1) return monsterLvl1Speed;
        if (monsterLevel == 2) return monsterLvl2Speed;
        if (monsterLevel == 3) return monsterLvl3Speed;
        if (monsterLevel == 4) return monsterLvl4Speed;
        if (monsterLevel == 5) return monsterLvl5Speed;
        if (monsterLevel == 6) return monsterLvl6Speed;
        if (monsterLevel == 7) return monsterLvl7Speed;
        if (monsterLevel == 8) return monsterLvl8Speed;
        if (monsterLevel == 9) return monsterLvl9Speed;
        if (monsterLevel == 10) return monsterLvl10Speed;
        if (monsterLevel == 11) return monsterLvl11Speed;
        if (monsterLevel == 12) return monsterLvl12Speed;
        if (monsterLevel == 13) return monsterLvl13Speed;
        if (monsterLevel == 14) return monsterLvl14Speed;
        if (monsterLevel == 15) return monsterLvl15Speed;
        return 1;
    }

    public static int getMonsterHp(int monsterLevel) {
        if (monsterLevel == 1) return monsterLvl1Hp;
        if (monsterLevel == 2) return monsterLvl2Hp;
        if (monsterLevel == 3) return monsterLvl3Hp;
        if (monsterLevel == 4) return monsterLvl4Hp;
        if (monsterLevel == 5) return monsterLvl5Hp;
        if (monsterLevel == 6) return monsterLvl6Hp;
        if (monsterLevel == 7) return monsterLvl7Hp;
        if (monsterLevel == 8) return monsterLvl8Hp;
        if (monsterLevel == 9) return monsterLvl9Hp;
        if (monsterLevel == 10) return monsterLvl10Hp;
        if (monsterLevel == 11) return monsterLvl11Hp;
        if (monsterLevel == 12) return monsterLvl12Hp;
        if (monsterLevel == 13) return monsterLvl13Hp;
        if (monsterLevel == 14) return monsterLvl14Hp;
        if (monsterLevel == 15) return monsterLvl15Hp;
        return 1;
    }
}
