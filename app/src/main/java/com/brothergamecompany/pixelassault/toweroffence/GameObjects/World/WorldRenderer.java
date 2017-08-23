package com.brothergamecompany.pixelassault.toweroffence.GameObjects.World;

import com.brothergamecompany.pixelassault.framework.gl.Animation;
import com.brothergamecompany.pixelassault.framework.gl.Camera2D;
import com.brothergamecompany.pixelassault.framework.gl.SpriteBatcher;
import com.brothergamecompany.pixelassault.framework.gl.TextureRegion;
import com.brothergamecompany.pixelassault.framework.impl.GLGraphics;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.MapBuilder;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Maelstrom;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Monster;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.SpawnerPortal;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tile;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tower;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.TowerBullet;
import com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by maxgm_umv4xdu on 04.06.2017.
 */

public class WorldRenderer {
    private final GLGraphics glGraphics;
    private final World world;
    public static Camera2D worldCam;
    private final SpriteBatcher batcher;
    private static boolean drawMonsters = true;
    public static final int FRUSTRUM_HEIGHT = 9;
    public static final int FRUSTRUM_WIDTH = 16;
    private final MapBuilder mapBuilder;

    public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher, World world, MapBuilder mapBuilder, Camera2D worldCam){
        WorldRenderer.worldCam = worldCam;
        this.glGraphics = glGraphics;
        this.world = world;
        this.batcher = batcher;
        this.mapBuilder = mapBuilder;
    }

    public void render() {
        worldCam.setViewportAndMatrices();
        GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        if (world.worldInitialized) {
            batcher.beginBatch(Assets.objects);
            renderWorld();
            batcher.endBatch();
        }
        gl.glDisable(GL10.GL_BLEND);
    }

    private void renderWorld() {
        renderTiles();
        //renderTileDecorations(); //тут будет наложение на тайлы
        mapBuilder.draw();
        renderTowers();
        renderPortals();
        if (drawMonsters)
            renderMonsters();

    }

    private void renderTowers() {
        for (Tower t : World.towers) {
            batcher.drawSprite(t.position.x, t.position.y + Tile.TILE_HEIGHT / 3,
                    Tower.TOWER_WIDTH, Tower.TOWER_HEIGHT,
                    Assets.getTowerAnimation(t.towerLevel).getKeyFrame(t.stateTime, Animation.ANIMATION_LOOPING));
            for (TowerBullet bullet : t.towerBullets){
                batcher.drawSprite(bullet.position.x, bullet.position.y, TowerBullet.BULLET_WIDTH, TowerBullet.BULLET_HEIGHT, bullet.angle, Assets.getBulletRegion(t.towerLevel));
            }
        }
    }

    private void renderPortals() {
        if (World.lastTileOnMap)
        batcher.drawSprite(World.maelstrom.position.x, World.maelstrom.position.y,
                Maelstrom.MAELSTROM_WIDTH, Maelstrom.MAELSTROM_HEIGHT, World.maelstrom.angle,
                Assets.maelstromEnd);
        if (World.firstTileOnMap) {
            batcher.drawSprite(World.spawnerPortal.position.x, World.spawnerPortal.position.y + Tile.TILE_HEIGHT / 2,
                    SpawnerPortal.PORTAL_WIDTH, SpawnerPortal.PORTAL_HEIGHT,
                    World.spawnerPortal.enabled ?
                            Assets.portalStartEnabled.getKeyFrame(World.spawnerPortal.stateTime, Animation.ANIMATION_LOOPING):
                            Assets.portalStartDisabled.getKeyFrame(World.spawnerPortal.stateTime, Animation.ANIMATION_LOOPING));
        }
    }

    private void renderMonsters() {
        for(Monster monster : World.monstersSpawned){
            if (monster.state == Monster.STATE_KILLED) {
                batcher.drawSprite(monster.position.x, monster.position.y + Tile.TILE_HEIGHT / 2,
                        monster.direction == Monster.DIRECTION_RIGHT ? Monster.MONSTER_WIDTH : -Monster.MONSTER_WIDTH, Monster.MONSTER_HEIGHT,
                        Assets.getMonsterAnimation(monster.monsterLvl, Monster.STATE_KILLED).getKeyFrame(monster.stateTime, Animation.ANIMATION_NONLOOPING));
            }
            if (monster.state == Monster.STATE_REACHED_MAELSTROM) {
                batcher.drawSprite(monster.position.x, monster.position.y + Tile.TILE_HEIGHT / 2,
                        monster.direction == Monster.DIRECTION_RIGHT ? Monster.MONSTER_WIDTH : -Monster.MONSTER_WIDTH, Monster.MONSTER_HEIGHT,
                        Assets.getMonsterAnimation(monster.monsterLvl, Monster.STATE_REACHED_MAELSTROM).getKeyFrame(monster.stateTime, Animation.ANIMATION_NONLOOPING));
            }
            if (monster.state == Monster.STATE_ALIVE) {
                batcher.drawSprite(monster.position.x, monster.position.y + Tile.TILE_HEIGHT / 2,
                        monster.direction == Monster.DIRECTION_RIGHT ? Monster.MONSTER_WIDTH : -Monster.MONSTER_WIDTH, Monster.MONSTER_HEIGHT,
                        Assets.getMonsterAnimation(monster.monsterLvl, Monster.STATE_ALIVE).getKeyFrame(monster.stateTime, Animation.ANIMATION_LOOPING));
            }
        }
    }

    private void renderTiles() {
        TextureRegion tileTexture;
        for (Tile[] column : World.worldGrid) {
            for (Tile tile : column) {
                tileTexture = Assets.tileGrass;
                if (tile != null)
                    batcher.drawSprite(tile.position.x, tile.position.y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, tileTexture);
            }
        }
        for (int i = 0; i < World.path.size(); i++) {
            Tile tile = World.path.get(i);
            tileTexture = Assets.tilePath;
            batcher.drawSprite(tile.position.x, tile.position.y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, tileTexture);
        }
    }


    public static void stopDrawingMonsters() {
        drawMonsters = false;
    }

    public static void startDrawingMonsters() {
        drawMonsters = true;
    }
}
