package com.brothergamecompany.pixelassault.backend.Other;

/**
 * Created by maxgm_umv4xdu on 27.08.2017.
 */
public class MonsterStats {

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