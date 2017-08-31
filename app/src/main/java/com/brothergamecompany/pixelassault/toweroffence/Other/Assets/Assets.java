package com.brothergamecompany.pixelassault.toweroffence.Other.Assets;

import com.brothergamecompany.pixelassault.framework.gl.Animation;
import com.brothergamecompany.pixelassault.framework.gl.Texture;
import com.brothergamecompany.pixelassault.framework.gl.TextureRegion;
import com.brothergamecompany.pixelassault.framework.impl.GLGame;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo.TowerStats;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Monster;
import com.brothergamecompany.pixelassault.toweroffence.Other.Font;

import java.util.concurrent.atomic.AtomicReference;

import static android.R.attr.y;
import static android.R.style.Animation;

/**
 * Created by maxgm_umv4xdu on 04.06.2017.
 */

public class Assets {
    public static Texture objects;

    public static Animation portalStartEnabled;
    public static Animation portalStartDisabled;


    public static Animation monsterLvl1;
    public static Animation monsterLvl2;
    public static Animation monsterLvl3;
    public static Animation monsterLvl4;
    public static Animation monsterLvl5;
    public static Animation monsterLvl6;
    public static Animation monsterLvl7;
    public static Animation monsterLvl8;
    public static Animation monsterLvl9;
    public static Animation monsterLvl10;
    public static Animation monsterLvl11;
    public static Animation monsterLvl12;
    public static Animation monsterLvl13;
    public static Animation monsterLvl14;
    public static Animation monsterLvl15;

    public static Animation monsterDeathLvl1;
    public static Animation monsterDeathLvl2;
    public static Animation monsterDeathLvl3;
    public static Animation monsterDeathLvl4;
    public static Animation monsterDeathLvl5;
    public static Animation monsterDeathLvl6;
    public static Animation monsterDeathLvl7;
    public static Animation monsterDeathLvl8;
    public static Animation monsterDeathLvl9;
    public static Animation monsterDeathLvl10;
    public static Animation monsterDeathLvl11;
    public static Animation monsterDeathLvl12;
    public static Animation monsterDeathLvl13;
    public static Animation monsterDeathLvl14;
    public static Animation monsterDeathLvl15;
    public static Animation monsterDeathLvl16;
    public static Animation monsterDeathLvl17;
    public static Animation monsterDeathLvl18;
    public static Animation monsterDeathLvl19;
    public static Animation monsterDeathLvl20;

    public static Animation monsterSinkingLvl1;
    public static Animation monsterSinkingLvl2;
    public static Animation monsterSinkingLvl3;
    public static Animation monsterSinkingLvl4;
    public static Animation monsterSinkingLvl5;
    public static Animation monsterSinkingLvl6;
    public static Animation monsterSinkingLvl7;
    public static Animation monsterSinkingLvl8;
    public static Animation monsterSinkingLvl9;
    public static Animation monsterSinkingLvl10;
    public static Animation monsterSinkingLvl11;
    public static Animation monsterSinkingLvl12;
    public static Animation monsterSinkingLvl13;
    public static Animation monsterSinkingLvl14;
    public static Animation monsterSinkingLvl15;

    public static Animation towerLevel1;
    public static TextureRegion towerLevel1Bullet;

    public static TextureRegion accountStatsInfo;
    public static TextureRegion tileGrass;
    public static TextureRegion tilePath;
    public static TextureRegion maelstromEnd;
    public static TextureRegion shopBuildingsChosen;
    public static TextureRegion shopUpgradesChosen;
    public static TextureRegion shopConsumablesChosen;
    public static TextureRegion shopDecorationsChosen;

    public static TextureRegion monsterSpawnButton;
    public static TextureRegion monsterSpawnButtonPressed;
    public static TextureRegion arrowUp;
    public static TextureRegion arrowUpPressed;
    public static TextureRegion triangleButton;
    public static TextureRegion triangleButtonPressed;
    public static TextureRegion mapBuilderLauncherImg;

    public static TextureRegion reChooseCell;
    public static TextureRegion moveObjectsMapBuilder;
    public static TextureRegion greenMask;
    public static TextureRegion redMask;
    public static Animation gridBorder;
    public static Animation mapBuilderPaneOpen;
    public static Animation mapBuilderPaneClose;
    public static TextureRegion mapBuilderCellBackground;
    public static TextureRegion eraser;
    public static TextureRegion confirmButton;
    public static TextureRegion confirmButtonPressed;
    public static Font font;
    public static TextureRegion helpButton;
    public static TextureRegion helpButtonPressed;
    public static TextureRegion expBarFrame;
    public static TextureRegion expBarFilling;
    public static TextureRegion roundButton;
    public static TextureRegion roundButtonPressed;
    public static TextureRegion expBarBackground;
    public static TextureRegion horizontalRectanglePane;
    public static TextureRegion yellowRectangleButton;
    public static TextureRegion refresh;

    public static void load(GLGame game) {
        objects = new Texture(game, "objectatlas.png");
        AssetsLoader.loadMonsters();
        AssetsLoader.loadTowers();
        refresh = new TextureRegion(objects, 128, 256, 16, 16);
        accountStatsInfo = new TextureRegion(objects, 208, 208, 64, 48);
        roundButton = new TextureRegion(objects, 40, 160, 16, 16);
        roundButtonPressed = new TextureRegion(objects, 56, 160, 16, 16);
        horizontalRectanglePane = new TextureRegion(objects, 145, 240, 48, 32);
        expBarFilling = new TextureRegion(objects, 112, 240, 1, 8);
        yellowRectangleButton = new TextureRegion(objects, 113, 240, 16, 16);
        expBarFrame = new TextureRegion(objects, 80, 240, 32, 8);
        expBarBackground = new TextureRegion(objects, 80, 256, 48, 16);
        helpButton = new TextureRegion(objects, 32, 256, 16, 16);
        helpButtonPressed = new TextureRegion(objects, 48, 256, 16, 16);
        font = new Font(objects, 192, 0, 16, 16, 20);
        gridBorder = new Animation(0.16f,
                new TextureRegion(objects, 128, 328, 8, 8),
                new TextureRegion(objects, 136, 328, 8, 8),
                new TextureRegion(objects, 144, 328, 8, 8),
                new TextureRegion(objects, 152, 328, 8, 8),
                new TextureRegion(objects, 160, 328, 8, 8),
                new TextureRegion(objects, 168, 328, 8, 8),
                new TextureRegion(objects, 176, 328, 8, 8),
                new TextureRegion(objects, 184, 328, 8, 8),
                new TextureRegion(objects, 192, 328, 8, 8),
                new TextureRegion(objects, 200, 328, 8, 8),
                new TextureRegion(objects, 208, 328, 8, 8),
                new TextureRegion(objects, 208, 320, 8, 8),
                new TextureRegion(objects, 200, 320, 8, 8),
                new TextureRegion(objects, 192, 320, 8, 8),
                new TextureRegion(objects, 200, 320, 8, 8),
                new TextureRegion(objects, 208, 320, 8, 8),
                new TextureRegion(objects, 208, 328, 8, 8),
                new TextureRegion(objects, 200, 328, 8, 8),
                new TextureRegion(objects, 192, 328, 8, 8),
                new TextureRegion(objects, 184, 328, 8, 8),
                new TextureRegion(objects, 176, 328, 8, 8),
                new TextureRegion(objects, 168, 328, 8, 8),
                new TextureRegion(objects, 160, 328, 8, 8),
                new TextureRegion(objects, 152, 328, 8, 8),
                new TextureRegion(objects, 144, 328, 8, 8),
                new TextureRegion(objects, 136, 328, 8, 8));

        mapBuilderPaneOpen = new Animation(0.1f,
                new TextureRegion(objects, 80, 160, 64, 16),
                new TextureRegion(objects, 80, 176, 64, 16),
                new TextureRegion(objects, 80, 192, 64, 16),
                new TextureRegion(objects, 80, 208, 64, 16),
                new TextureRegion(objects, 80, 224, 64, 16));

        mapBuilderPaneClose = new Animation(0.1f,
                new TextureRegion(objects, 80, 224, 64, 16),
                new TextureRegion(objects, 80, 208, 64, 16),
                new TextureRegion(objects, 80, 192, 64, 16),
                new TextureRegion(objects, 80, 176, 64, 16),
                new TextureRegion(objects, 80, 160, 64, 16));

        mapBuilderCellBackground = new TextureRegion(objects, 144, 224, 16, 16);

        confirmButton = new TextureRegion(objects, 64, 208, 16, 16);
        confirmButtonPressed = new TextureRegion(objects, 64, 192, 16, 16);
        eraser = new TextureRegion(objects, 32, 192, 32, 32);
        greenMask = new TextureRegion(objects, 136, 272, 1, 1);
        redMask = new TextureRegion(objects, 136, 273, 1, 1);
        moveObjectsMapBuilder = new TextureRegion(objects, 128, 280, 16, 16);
        reChooseCell = new TextureRegion(objects, 128, 296, 32, 32);

        mapBuilderLauncherImg = new TextureRegion(objects, 32, 176, 16, 16);
        tileGrass = new TextureRegion(objects, 0, 160, 32, 32);
        tilePath = new TextureRegion(objects, 0, 192, 32, 32);
        maelstromEnd = new TextureRegion(objects, 0, 256, 16, 16);

        shopBuildingsChosen = new TextureRegion(objects, 0, 272, 32, 64);
        shopUpgradesChosen = new TextureRegion(objects, 32, 272, 32, 64);
        shopConsumablesChosen = new TextureRegion(objects, 64, 272, 32, 64);
        shopDecorationsChosen = new TextureRegion(objects, 96, 272, 32, 64);

        monsterSpawnButton = new TextureRegion(objects, 0, 224, 16, 16);
        monsterSpawnButtonPressed = new TextureRegion(objects, 16, 224, 16, 16);
        arrowUp = new TextureRegion(objects, 32, 224, 16, 16);
        arrowUpPressed = new TextureRegion(objects, 16, 256, 16, 16);
        triangleButton = new TextureRegion(objects, 64, 224, 16, 16);
        triangleButtonPressed = new TextureRegion(objects, 64, 240, 16, 16);

        portalStartEnabled = new Animation(0.16f,
                new TextureRegion(objects, 0, 240, 8, 8),
                new TextureRegion(objects, 8, 240, 8, 8),
                new TextureRegion(objects, 16, 240, 8, 8),
                new TextureRegion(objects, 24, 240, 8, 8),
                new TextureRegion(objects, 32, 240, 8, 8),
                new TextureRegion(objects, 40, 240, 8, 8));

        portalStartDisabled = new Animation(0.16f,
                new TextureRegion(objects, 0, 248, 8, 8),
                new TextureRegion(objects, 8, 248, 8, 8),
                new TextureRegion(objects, 16, 248, 8, 8),
                new TextureRegion(objects, 24, 248, 8, 8),
                new TextureRegion(objects, 32, 248, 8, 8),
                new TextureRegion(objects, 40, 248, 8, 8),
                new TextureRegion(objects, 48, 248, 8, 8),
                new TextureRegion(objects, 56, 248, 8, 8));
    }


    public static void reload() {
        objects.reload();
    }

    public static Animation getMonsterAnimation(int level, int state) {
        if (state == Monster.STATE_ALIVE) {
            if (level == 1) return monsterLvl1;
            if (level == 2) return monsterLvl2;
            if (level == 3) return monsterLvl3;
            if (level == 4) return monsterLvl4;
            if (level == 5) return monsterLvl5;
            if (level == 6) return monsterLvl6;
            if (level == 7) return monsterLvl7;
            if (level == 8) return monsterLvl8;
            if (level == 9) return monsterLvl9;
            if (level == 10) return monsterLvl10;
            if (level == 11) return monsterLvl11;
            if (level == 12) return monsterLvl12;
            if (level == 13) return monsterLvl13;
            if (level == 14) return monsterLvl14;
            if (level == 15) return monsterLvl15;
        }
        if (state == Monster.STATE_KILLED) {
            if (level == 1) return monsterDeathLvl1;
            if (level == 2) return monsterDeathLvl2;
            if (level == 3) return monsterDeathLvl3;
            if (level == 4) return monsterDeathLvl4;
            if (level == 5) return monsterDeathLvl5;
            if (level == 6) return monsterDeathLvl6;
            if (level == 7) return monsterDeathLvl7;
            if (level == 8) return monsterDeathLvl8;
            if (level == 9) return monsterDeathLvl9;
            if (level == 10) return monsterDeathLvl10;
            if (level == 11) return monsterDeathLvl11;
            if (level == 12) return monsterDeathLvl12;
            if (level == 13) return monsterDeathLvl13;
            if (level == 14) return monsterDeathLvl14;
            if (level == 15) return monsterDeathLvl15;
        }
        if (state == Monster.STATE_REACHED_MAELSTROM) {
            if (level == 1) return monsterSinkingLvl1;
            if (level == 2) return monsterSinkingLvl2;
            if (level == 3) return monsterSinkingLvl3;
            if (level == 4) return monsterSinkingLvl4;
            if (level == 5) return monsterSinkingLvl5;
            if (level == 6) return monsterSinkingLvl6;
            if (level == 7) return monsterSinkingLvl7;
            if (level == 8) return monsterSinkingLvl8;
            if (level == 9) return monsterSinkingLvl9;
            if (level == 10) return monsterSinkingLvl10;
            if (level == 11) return monsterSinkingLvl11;
            if (level == 12) return monsterSinkingLvl12;
            if (level == 13) return monsterSinkingLvl13;
            if (level == 14) return monsterSinkingLvl14;
            if (level == 15) return monsterSinkingLvl15;
        }
        return monsterLvl1;
    }

    public static Animation getTowerAnimation(int towerLevel) {
        if (towerLevel == TowerStats.TOWER_LEVEL_1) return towerLevel1;
        return null;
    }

    public static TextureRegion getBulletRegion(int towerLevel) {
        if (towerLevel == TowerStats.TOWER_LEVEL_1) return towerLevel1Bullet;
        return towerLevel1Bullet;
    }
}
