package com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo;

import static com.brothergamecompany.pixelassault.toweroffence.GameObjects.UserInterface.UIData.monsterLevel;

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

    private static final int monsterLvl1Hp = 10;
    private static final int monsterLvl2Hp = 30;
    private static final int monsterLvl3Hp = 70;
    private static final int monsterLvl4Hp = 100;
    private static final int monsterLvl5Hp = 150;
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

    private static final int monsterLvl1Coins = 10;
    private static final int monsterLvl2Coins = 50;
    private static final int monsterLvl3Coins = 100;
    private static final int monsterLvl4Coins = 145;
    private static final int monsterLvl5Coins = 160;
    private static final int monsterLvl6Coins = 200;
    private static final int monsterLvl7Coins = 260;
    private static final int monsterLvl8Coins = 290;
    private static final int monsterLvl9Coins = 360;
    private static final int monsterLvl10Coins = 400;
    private static final int monsterLvl11Coins = 460;
    private static final int monsterLvl12Coins = 510;
    private static final int monsterLvl13Coins = 570;
    private static final int monsterLvl14Coins = 630;
    private static final int monsterLvl15Coins = 700;

    private static final int monsterLvl1Exp = 2;
    private static final int monsterLvl2Exp = 4;
    private static final int monsterLvl3Exp = 6;
    private static final int monsterLvl4Exp = 8;
    private static final int monsterLvl5Exp = 10;
    private static final int monsterLvl6Exp = 13;
    private static final int monsterLvl7Exp = 16;
    private static final int monsterLvl8Exp = 19;
    private static final int monsterLvl9Exp = 22;
    private static final int monsterLvl10Exp = 25;
    private static final int monsterLvl11Exp = 30;
    private static final int monsterLvl12Exp = 35;
    private static final int monsterLvl13Exp = 40;
    private static final int monsterLvl14Exp = 45;
    private static final int monsterLvl15Exp = 50;


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

    public static int getMonsterCoins(int monsterLevel) {
        if (monsterLevel == 1) return monsterLvl1Coins;
        if (monsterLevel == 2) return monsterLvl2Coins;
        if (monsterLevel == 3) return monsterLvl3Coins;
        if (monsterLevel == 4) return monsterLvl4Coins;
        if (monsterLevel == 5) return monsterLvl5Coins;
        if (monsterLevel == 6) return monsterLvl6Coins;
        if (monsterLevel == 7) return monsterLvl7Coins;
        if (monsterLevel == 8) return monsterLvl8Coins;
        if (monsterLevel == 9) return monsterLvl9Coins;
        if (monsterLevel == 10) return monsterLvl10Coins;
        if (monsterLevel == 11) return monsterLvl11Coins;
        if (monsterLevel == 12) return monsterLvl12Coins;
        if (monsterLevel == 13) return monsterLvl13Coins;
        if (monsterLevel == 14) return monsterLvl14Coins;
        if (monsterLevel == 15) return monsterLvl15Coins;
        return 1;
    }

    public static int getMonsterExp(int monsterLevel) {
        if (monsterLevel == 1) return monsterLvl1Exp;
        if (monsterLevel == 2) return monsterLvl2Exp;
        if (monsterLevel == 3) return monsterLvl3Exp;
        if (monsterLevel == 4) return monsterLvl4Exp;
        if (monsterLevel == 5) return monsterLvl5Exp;
        if (monsterLevel == 6) return monsterLvl6Exp;
        if (monsterLevel == 7) return monsterLvl7Exp;
        if (monsterLevel == 8) return monsterLvl8Exp;
        if (monsterLevel == 9) return monsterLvl9Exp;
        if (monsterLevel == 10) return monsterLvl10Exp;
        if (monsterLevel == 11) return monsterLvl11Exp;
        if (monsterLevel == 12) return monsterLvl12Exp;
        if (monsterLevel == 13) return monsterLvl13Exp;
        if (monsterLevel == 14) return monsterLvl14Exp;
        if (monsterLevel == 15) return monsterLvl15Exp;
        return 1;
    }
}
