package com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects;

import com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsRecycler.MonsterHandler;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.UserInterface.UIData;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account;

import static com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account.spawnPortalPower;

/**
 * Created by maxgm_umv4xdu on 10.06.2017.
 */

public class MonsterSpawner {
    private float spawnTimer;
    public static int currentSpawnLvl;
    public MonsterHandler monsterHandler;

    public MonsterSpawner() {
        currentSpawnLvl = UIData.monsterLevel;
        spawnTimer = 0;
        monsterHandler = new MonsterHandler();
    }

    public void confirmSpawnLvl() {
        currentSpawnLvl = UIData.monsterLevel;
        if (spawnTimer > currentSpawnLvl/ spawnPortalPower)
        spawnTimer = currentSpawnLvl/ spawnPortalPower;
    }

    public void update(float deltaTime) {
        if (World.spawnerPortal.enabled) {
            int spawnPortalCapacity = Account.spawnPortalPower;
            float timeSpawn = (float) (currentSpawnLvl / spawnPortalCapacity);
            if (spawnTimer > timeSpawn) {
                spawnTimer -= timeSpawn;
                monsterHandler.newMonster(World.tileCenterCoords(Account.MOB_SPAWNER_X), World.tileCenterCoords(Account.MOB_SPAWNER_Y), currentSpawnLvl);
            }
            spawnTimer += deltaTime;
        }
        else {
            spawnTimer = 0;
        }
    }
}
