package com.brothergamecompany.pixelassault.toweroffence.GameObjects.UserInterface;


import android.content.Context;

import com.brothergamecompany.pixelassault.R;
import com.brothergamecompany.pixelassault.framework.Game;
import com.brothergamecompany.pixelassault.framework.Input;
import com.brothergamecompany.pixelassault.framework.gl.Animation;
import com.brothergamecompany.pixelassault.framework.gl.Camera2D;
import com.brothergamecompany.pixelassault.framework.gl.SpriteBatcher;
import com.brothergamecompany.pixelassault.framework.gl.SpriteBatcherColored;
import com.brothergamecompany.pixelassault.framework.impl.GLGame;
import com.brothergamecompany.pixelassault.framework.impl.GLGraphics;
import com.brothergamecompany.pixelassault.framework.math.OverlapTester;
import com.brothergamecompany.pixelassault.framework.math.Rectangle;
import com.brothergamecompany.pixelassault.framework.math.Vector2;
import com.brothergamecompany.pixelassault.toweroffence.GameLauncher;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.MapBuilder;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Monster;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldRenderer;
import com.brothergamecompany.pixelassault.toweroffence.GameScreens.GameScreen;
import com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets;
import com.brothergamecompany.pixelassault.toweroffence.Other.Font;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.SignIn;
import com.brothergamecompany.pixelassault.toweroffence.Other.Notifications.NotificationManager;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import static com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets.font;

/**
 * Created by maxgm_umv4xdu on 07.07.2017.
 */

public class UIAndTouchListening {
    public static final int USUAL_GAME_STATE = 0;
    private static final int SHOP_GAME_STATE = 1;
    private static final int MAP_BUILDER_GAME_STATE = 2;
    private static final int ACCOUNT_STATS_GAME_STATE = 3;
    public static int state;

    private static final int SHOP_BUILDINGS_CHOSEN = 0;
    private static final int SHOP_UPGRADES_CHOSEN = 1;
    private static final int SHOP_CONSUMABLES_CHOSEN = 2;
    private static final int SHOP_DECORATIONS_CHOSEN = 3;
    private static int shopState = -1;


    private final GLGraphics glGraphics;
    private final GLGame glGame;
    private final World world;
    private final Camera2D guiCam;
    private final Vector2 touchPoint;
    private final SpriteBatcher batcher;
    private final SpriteBatcherColored coloredBatcher;
    private final Rectangle spawnButton;
    private final Rectangle arrowUp;
    private final Rectangle arrowDown;
    private final Monster monsterForButton;
    private final Rectangle shopButton;
    private final Rectangle mapBuilderButton;
    private final Rectangle shopBuildingsTab;
    private final Rectangle shopUpgradesTab;
    private final Rectangle shopConsumablesTab;
    private final Rectangle shopDecorationsTab;
    private final Rectangle accountStats;

    private Rectangle refreshButtonAuthFailed;
    private final MapBuilder mapBuilder;
    private static boolean spawnButtonPressed = false;
    private static boolean arrowUpButtonPressed = false;
    private static boolean arrowDownButtonPressed = false;
    private static boolean mapBuilderButtonPressed = false;
    private static boolean shopButtonPressed = false;
    private Vector2 touchPoint2;

    //WorldListener worldListener; for the future sounds
    public UIAndTouchListening(GLGame glGame, GLGraphics glGraphics, SpriteBatcher batcher, SpriteBatcherColored coloredBatcher, World world, MapBuilder mapBuilder, Camera2D guiCam) {
        this.world = world;
        this.glGame = glGame;
        this.glGraphics = glGraphics;
        this.batcher = batcher;
        this.coloredBatcher = coloredBatcher;
        touchPoint = new Vector2();
        touchPoint2 = new Vector2();
        this.guiCam = guiCam;
        shopButton = new Rectangle(0, 0, 140, 140);
        state = USUAL_GAME_STATE;
        accountStats = new Rectangle(440, 585, 400, 130);
        spawnButton = new Rectangle(30, 230, 100, 100);
        arrowUp = new Rectangle(35, 485, 70, 70);
        arrowDown = new Rectangle(35, 375, 70, 70);
        shopBuildingsTab = new Rectangle(258, 573, 102, 147);
        shopUpgradesTab = new Rectangle(258, 426, 102, 147);
        shopConsumablesTab = new Rectangle(258, 279, 102, 147);
        shopDecorationsTab = new Rectangle(258, 132, 102, 147);
        mapBuilderButton = new Rectangle(1140, 0, 140, 140);
        refreshButtonAuthFailed = new Rectangle(540, 210, 100, 100);
        monsterForButton = new Monster(80, 280, UIData.monsterLevel);
        this.mapBuilder = mapBuilder;
    }

    public void render() {
        GL10 gl = glGraphics.getGL();
        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        batcher.beginBatch(Assets.objects);
        coloredBatcher.beginBatch(Assets.objects);
        drawUI();
        batcher.drawSprite(0,0,1,1,Assets.towerLevel1Bullet);
        batcher.endBatch();
        coloredBatcher.drawSprite(1, 1, 1, 1, Assets.arrowUp, 1, 0, 0, 0);
        coloredBatcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
    }

    private void drawUI() {
        NotificationManager.draw();
        switch (state) {
            case USUAL_GAME_STATE:
                drawUsualGameState();
                break;
            case SHOP_GAME_STATE:
                drawShopGameState();
                break;
            case MAP_BUILDER_GAME_STATE:
                drawMapBuilderGameState();
                break;
            case ACCOUNT_STATS_GAME_STATE:
                drawAccountStatsGameState();
                break;
        }
    }

    private void drawAccountStatsGameState() {
        drawUsualGameState();
        batcher.drawSprite(640, 360, 640, 480, Assets.accountStatsInfo);
        font.drawColoredText(coloredBatcher, (glGame).getResources().getString(R.string.account_stats_header), 490, 530, 0.3f, 0.3f, 0.3f, 1, 22, 28.6f);
        font.drawColoredText(coloredBatcher, (glGame).getResources().getString(R.string.account_stats_nickname), 370, 460, 0.3f, 0.3f, 0.3f, 1, 17, 22.1f);
        font.drawColoredText(coloredBatcher, Account.nickname, 930 - (Account.nickname.length() * (17 + Font.spaceBetweenLetters)), 460, 0.3f, 0.3f, 0.3f, 1, 17, 22.1f);

        font.drawColoredText(coloredBatcher, (glGame).getResources().getString(R.string.account_stats_currentLevel), 370, 410, 0.3f, 0.3f, 0.3f, 1, 17, 22.1f);
        font.drawColoredText(coloredBatcher, String.valueOf(Account.currentLevel), 930 - (String.valueOf(Account.currentLevel).length() * (17 + Font.spaceBetweenLetters)), 410, 0.3f, 0.3f, 0.3f, 1, 17, 22.1f);

        font.drawColoredText(coloredBatcher, (glGame).getResources().getString(R.string.account_stats_expToLvl), 370, 360, 0.3f, 0.3f, 0.3f, 1, 17, 22.1f);
        font.drawColoredText(coloredBatcher, String.valueOf(Account.getMaxLvlExp() - Account.currentExp), 930 - (String.valueOf(Account.getMaxLvlExp() - Account.currentExp).length() * (17 + Font.spaceBetweenLetters)), 360, 0.3f, 0.3f, 0.3f, 1, 17, 22.1f);

        font.drawColoredText(coloredBatcher, (glGame).getResources().getString(R.string.account_stats_portalPower), 370, 310, 0.3f, 0.3f, 0.3f, 1, 17, 22.1f);
        font.drawColoredText(coloredBatcher, String.valueOf(Account.spawnPortalPower), 930 - (String.valueOf(Account.spawnPortalPower).length() * (17 + Font.spaceBetweenLetters)), 310, 0.3f, 0.3f, 0.3f, 1, 17, 22.1f);

        font.drawColoredText(coloredBatcher, (glGame).getResources().getString(R.string.account_stats_monstersKilled), 370, 260, 0.3f, 0.3f, 0.3f, 1, 17, 22.1f);
        font.drawColoredText(coloredBatcher, String.valueOf(Account.totalMonstersKilled), 930 - (String.valueOf(Account.totalMonstersKilled).length() * (17 + Font.spaceBetweenLetters)), 260, 0.3f, 0.3f, 0.3f, 1, 17, 22.1f);
    }

    private void drawMapBuilderGameState() {
        mapBuilder.drawUI();
    }

    private void drawShopGameState() {
        switch (shopState){
            case SHOP_BUILDINGS_CHOSEN:
                batcher.drawSprite(180, 360, 360, 720, Assets.shopBuildingsChosen);
                break;
            case SHOP_UPGRADES_CHOSEN:
                batcher.drawSprite(180, 360, 360, 720, Assets.shopUpgradesChosen);
                break;
            case SHOP_CONSUMABLES_CHOSEN:
                batcher.drawSprite(180, 360, 360, 720, Assets.shopConsumablesChosen);
                break;
            case SHOP_DECORATIONS_CHOSEN:
                batcher.drawSprite(180, 360, 360, 720, Assets.shopDecorationsChosen);
                break;
        }

    }

    private void drawUsualGameState() {
        batcher.drawSprite(70, 520, 70, 70, arrowUpButtonPressed ? Assets.arrowUpPressed : Assets.arrowUp);
        font.drawText(batcher, UIData.monsterLevel + " LVL", 30, 465, 20, 26);
        batcher.drawSprite(70, 410, 70, -70, arrowDownButtonPressed ? Assets.arrowUpPressed : Assets.arrowUp);
        batcher.drawSprite(70, 70, -140, 140, shopButtonPressed ? Assets.triangleButtonPressed : Assets.triangleButton);
        batcher.drawSprite(1210, 70, 140, 140, mapBuilderButtonPressed ? Assets.triangleButtonPressed : Assets.triangleButton);
        batcher.drawSprite(1230, 50, 90, 90, Assets.mapBuilderLauncherImg);
        batcher.drawSprite(80, 280, 100, 100, spawnButtonPressed ? Assets.monsterSpawnButtonPressed :  Assets.monsterSpawnButton);
        batcher.drawSprite(80, 280, 80, 80, Assets.getMonsterAnimation(UIData.monsterLevel, Monster.STATE_ALIVE).getKeyFrame(monsterForButton.stateTime, Animation.ANIMATION_LOOPING));
        batcher.drawSprite(1210, 650, 75, 75, Assets.helpButton);
        batcher.drawSprite(640, 650, 400, 130, Assets.expBarBackground);
        float percentageOfExpBarFilled = (float) Account.currentExp / (float)Account.getMaxLvlExp();
        float widthOfExpBarFilling = 300 * percentageOfExpBarFilled;
        batcher.drawSprite(490 + widthOfExpBarFilling / 2, 650, widthOfExpBarFilling, 50, Assets.expBarFilling);
        batcher.drawSprite(640, 650, 320, 50, Assets.expBarFrame);
        if (GameLauncher.signIn.notifyUser) {
            batcher.drawSprite(640, 360, 440, 260, Assets.horizontalRectanglePane);
            if (!GameLauncher.signIn.signInSuccessful) {
                Font.spaceBetweenLetters = 3;
                                                                                 //middleOfScreen - glyphWidth + spaceBetweenLetters * StringLength / 2 = text centered
                font.drawColoredText(coloredBatcher, glGame.getString(R.string.authentication), 640 - (((15 + 3) * 14)/2), 420, 0.3f, 0.3f, 0.3f, 1, 15, 19.5f);
                font.drawColoredText(coloredBatcher, glGame.getString(R.string.failed), 640 - (((15 + 3) * 6)/2), 390, 0.3f, 0.3f, 0.3f, 1, 15, 19.5f);
                batcher.drawSprite(640, 310, 50, 50, Assets.refresh);
            }
        }
    }

    public void update(float deltaTime) {
        NotificationManager.update(deltaTime);
        List<Input.TouchEvent> touchEvents = glGame.getInput().getTouchEvents();
        switch (state) {
            case USUAL_GAME_STATE:
                updateUsualGameState(deltaTime, touchEvents);
                break;
            case SHOP_GAME_STATE:
                updateShopGameState(deltaTime, touchEvents);
                break;
            case MAP_BUILDER_GAME_STATE:
                updateMapBuilderGameState(deltaTime, touchEvents);
                break;
            case ACCOUNT_STATS_GAME_STATE:
                updateAccountStatsGameState(deltaTime, touchEvents);
        }
    }

    private final Rectangle accountStatsArea = new Rectangle(320, 120, 640, 480);
    private void updateAccountStatsGameState(float deltaTime, List<Input.TouchEvent> touchEvents) {
        monsterForButton.updateForButton(deltaTime);
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent touchEvent = touchEvents.get(i);
            touchPoint.set(touchEvent.x, touchEvent.y);
            guiCam.touchToWorld(touchPoint);
            if (!(OverlapTester.pointInRectangle(accountStatsArea, touchPoint))) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    state = USUAL_GAME_STATE;
                }
            }
        }
    }

    private void updateMapBuilderGameState(float deltaTime, List<Input.TouchEvent> touchEvents) {
        mapBuilder.update(deltaTime, touchEvents);
    }
    private void updateUsualGameState(float deltaTime, List<Input.TouchEvent> touchEvents) {
        monsterForButton.updateForButton(deltaTime);
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent touchEvent = touchEvents.get(i);
            touchPoint.set(touchEvent.x, touchEvent.y);
            touchPoint2.set(touchEvent.x, touchEvent.y);
            guiCam.touchToWorld(touchPoint);
            WorldRenderer.worldCam.touchToWorld(touchPoint2);

            if (GameLauncher.signIn.notifyUser) {
                if (OverlapTester.pointInRectangle(refreshButtonAuthFailed, touchPoint)){
                    if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                        //Assets.playSound(Assets.clickSound);
                        GameLauncher.signIn.notifyUser = false;
                        GameLauncher.signIn.signIn();
                    }
                }
            }
            if (OverlapTester.pointInRectangle(World.spawnerPortal.bounds, touchPoint2)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    //Assets.playSound(Assets.portalSound);
                    World.spawnerPortal.enabled = !World.spawnerPortal.enabled;
                }
            }

            if (OverlapTester.pointInRectangle(mapBuilderButton, touchPoint)){
                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    //Assets.playSound(Assets.clickSound);
                    if (World.monstersSpawned.size() == 0) {
                        state = MAP_BUILDER_GAME_STATE;
                        GameScreen.mapBuilder.enterMapBuilder();
                        mapBuilderButtonPressed = false;
                    }
                    else {
                        NotificationManager.makeNotification(300, 550, (glGame).getResources().getString(R.string.map_builder_button_error_mobs_on_map_1), 0.7f, 1f, 1f, 0f, 20f, 26f);
                        NotificationManager.makeNotification(200, 550, (glGame).getResources().getString(R.string.map_builder_button_error_mobs_on_map_2), 1.5f, 1f, 1f, 0f, 16f, 20.8f);
                    }
                }
                else mapBuilderButtonPressed = true;
            } else  mapBuilderButtonPressed = false;


            if (OverlapTester.pointInRectangle(spawnButton, touchPoint)) {
                if (touchEvent.type != Input.TouchEvent.TOUCH_UP) {
                    spawnButtonPressed = true;
                } else {
                    //Assets.playSound(Assets.clickSound);
                    spawnButtonPressed = false;
                    world.monsterSpawner.confirmSpawnLvl();
                }
            }
            else spawnButtonPressed = false;

            if (OverlapTester.pointInRectangle(arrowUp, touchPoint)) {
                if (touchEvent.type != Input.TouchEvent.TOUCH_UP) {
                    arrowUpButtonPressed = true;
                }
                else {
                    //Assets.playSound(Assets.clickSound);
                    arrowUpButtonPressed = false;
                    if (UIData.monsterLevel < UIData.maxMonsterLevel && UIData.monsterLevel >= UIData.minMonsterLevel)
                        UIData.monsterLevel++;
                }
            }
            else arrowUpButtonPressed = false;

            if (OverlapTester.pointInRectangle(arrowDown, touchPoint)) {
                if (touchEvent.type != Input.TouchEvent.TOUCH_UP) {
                    arrowDownButtonPressed = true;
                }
                else {
                    //Assets.playSound(Assets.clickSound);
                    arrowDownButtonPressed = false;
                    if (UIData.monsterLevel <= UIData.maxMonsterLevel && UIData.monsterLevel > UIData.minMonsterLevel)
                        UIData.monsterLevel--;
                }
            }
            else arrowDownButtonPressed = false;

            if (OverlapTester.pointInRectangle(shopButton, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    //Assets.playSound(Assets.clickSound);
                    shopButtonPressed = false;
                    shopState = SHOP_BUILDINGS_CHOSEN;
                    state = SHOP_GAME_STATE;
                } else shopButtonPressed = true;
            } else  shopButtonPressed = false;

            if (OverlapTester.pointInRectangle(accountStats, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    //Assets.playSound(Assets.clickSound;
                    state = ACCOUNT_STATS_GAME_STATE;
                }
            }
        }
    }

    private void updateShopGameState(float deltaTime, List<Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent touchEvent = touchEvents.get(i);
            touchPoint.set(touchEvent.x, touchEvent.y);
            guiCam.touchToWorld(touchPoint);

            if (OverlapTester.pointInRectangle(shopBuildingsTab, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    shopState = SHOP_BUILDINGS_CHOSEN;
                }
            }

            if (OverlapTester.pointInRectangle(shopUpgradesTab, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    shopState = SHOP_UPGRADES_CHOSEN;
                }
            }

            if (OverlapTester.pointInRectangle(shopConsumablesTab, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    shopState = SHOP_CONSUMABLES_CHOSEN;
                }
            }

            if (OverlapTester.pointInRectangle(shopDecorationsTab, touchPoint)) {
                if (touchEvent.type == Input.TouchEvent.TOUCH_UP) {
                    shopState = SHOP_DECORATIONS_CHOSEN;
                }
            }
        }
    }
}
