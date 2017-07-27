package com.brothergamecompany.pixelassault.toweroffence.GameObjects.World;

import android.support.v4.util.Pair;

import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.MonsterSpawner;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Maelstrom;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Monster;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.SpawnerPortal;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tile;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Tower;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account;
import com.brothergamecompany.pixelassault.toweroffence.Other.WaveAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxgm_umv4xdu on 04.06.2017.
 */

public class World {
    public static int WORLD_WIDTH = Account.WORLD_WIDTH;
    public static int WORLD_HEIGHT = Account.WORLD_HEIGHT;
    public static Tile[][] worldGrid;
    public static List<Tile> monsterPath;
    public MonsterSpawner monsterSpawner;
    public static List<Monster> monstersSpawned;
    public static SpawnerPortal spawnerPortal;
    public static Maelstrom maelstrom;
    public static List<Tile> path;
    private static boolean updateMonstersAndTowers = true;
    private boolean worldInitialized = false;
    public static boolean firstTileOnMap;
    public static boolean lastTileOnMap;
    public static List<Tower> towers;
    public World() {
        initializeWorld();
    }

    private void initializeWorld(){
        worldGrid = new Tile[WORLD_WIDTH][WORLD_HEIGHT];
        monsterSpawner = new MonsterSpawner();
        monstersSpawned = monsterSpawner.monsterHandler.monsters;
        spawnerPortal = new SpawnerPortal();
        maelstrom = new Maelstrom();
        monsterPath = new ArrayList<>();
        towers = new ArrayList<>();
        path = new ArrayList<>();
        firstTileOnMap = false;
        lastTileOnMap = false;
        for (int i = 0; i < Account.towers.size(); i++) {
            if (Account.towers.get(i).onMap) {
                towers.add(Account.towers.get(i));
            }
        }
        for (int i = 0; i < Account.path.size(); i++) {
            if (Account.path.get(i).onMap) {
                path.add(Account.path.get(i));
                if (Account.path.get(i).type2 == Tile.TYPE_FIRST_PATH_TILE)
                    firstTileOnMap = true;
                if (Account.path.get(i).type2 == Tile.TYPE_LAST_PATH_TILE)
                    lastTileOnMap = true;
            }
        }
        fillWorldGrid();
        insertPathToGrid();
        createMonsterPath();
        worldInitialized = true;
    }

    public static boolean reInitializeWorld() {
        fillWorldGrid();
        insertPathToGrid();
        return createMonsterPath();
    }

    public static void fillWorldGrid() {
        for (int i = 0; i < worldGrid.length; i++) {
            for (int j = 0; j < worldGrid[i].length; j++) {
                worldGrid[i][j] = new Tile(i, j, Tile.TYPE_GRASS);
            }
        }

    }

    //top right bottom left 0 1 2 3 in the array
    private static boolean createMonsterPath() {
        boolean[][] booleanWorldGrid = makeBooleanCopyOfTheWorld();
        WaveAlgorithm waveAlgorithm = new WaveAlgorithm(booleanWorldGrid, Pair.create(Account.MOB_SPAWNER_X, Account.MOB_SPAWNER_Y), Account.getLastPathPos());
        ArrayList<Pair<Integer, Integer>> path = waveAlgorithm.getPath();
        monsterPath.clear();
        if (path != null) {
            for (Pair pair : path) {
                monsterPath.add(worldGrid[(int) pair.first][(int) pair.second]);
            }
            return true;
        }
        return false;
    }

    private static void insertPathToGrid() {
        for(int i = 0; i < World.path.size(); i++) {
            if (World.path.get(i).onMap){
                worldGrid[World.path.get(i).gridX][World.path.get(i).gridY] = World.path.get(i);
            }
        }
    }

    public void update(float deltaTime) {
        if(worldInitialized) {
            updatePortals(deltaTime);
            if(updateMonstersAndTowers) {
                updateMonsters(deltaTime);
                updateTowers(deltaTime);
            }
        }
    }

    private void updateTowers(float deltaTime) {
        for (Tower t : towers) {
            t.update(deltaTime);
        }
    }

    private void updatePortals(float deltaTime) {
        if (firstTileOnMap)
        spawnerPortal.update(deltaTime);
        if (lastTileOnMap)
        maelstrom.update(deltaTime);
    }

    //при входе в водоворот
    private void destroyMonster(Monster monster) {
        monstersSpawned.remove(monster);
        monsterSpawner.monsterHandler.freeMonsterForReuse(monster);

    }

    private void updateMonsters(float deltaTime) {
        if (monsterPath.size() != 0){
        monsterSpawner.update(deltaTime);
        for (int i = 0; i < monstersSpawned.size();) {
            Monster monster = monstersSpawned.get(i);
            boolean destroyed = monster.update(deltaTime);
            if (destroyed) {//в листе при удалении элементы сдвигаются влево, соответственно если не отследить изменение листа, можно пропустить один update
                destroyMonster(monster);
                continue;
            }
            i++;
        }
        }
    }

    private static boolean[][] makeBooleanCopyOfTheWorld(){
        boolean[][] booleanGrid = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
        for (int i = 0; i < worldGrid.length; i++) {
            for (int j = 0; j < worldGrid[i].length; j++) {
                booleanGrid[i][j] = worldGrid[i][j].type == Tile.TYPE_PATH;
            }
        }
        return booleanGrid;
    }

    //получает номер клетки, возвращает ее центр. Так как
    //мир состоит из квадратных объектов, формула одна и для x, и для y.
    //первый тайл в гриде - 0

    //the first tile in the grid is 0
    //gets cell number and returns coordinates of its center.
    //As the world objects are squares, y and x can use the same formula.
    public static float tileCenterCoords(int gridValue) {
        return (gridValue + 1) - 0.5f;
    }

    public static void stopUpdatingMonsters() {
        updateMonstersAndTowers = false;
    }

    public static void startUpdatingMonstersAndTowers() {
        updateMonstersAndTowers = true;
    }
}
