package com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsRecycler;

import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxgm_umv4xdu on 11.06.2017.
 */

public class MonsterHandler {
    List<Monster> monsterPool;
    public List<Monster> monsters;
    public static int freeMonsters = 0;
    public static boolean monsterPoolHasFree = false;

    public MonsterHandler() {
        monsterPool = new ArrayList<>();
        monsters = new ArrayList<>();
    }

    public void newMonster(float x, float y, int currentSpawnLvl) {
        Monster monster;
        if (!monsterPoolHasFree) {
            monster = new Monster(x, y, currentSpawnLvl);
            monsterPool.add(monster);
        }
        else {
            monster = monsterPool.remove(monsterPool.size() - 1);
            monster = getReinitializedMob(x, y, currentSpawnLvl, monster);
            freeMonsters--;
            if (freeMonsters <= 0){
                monsterPoolHasFree = false;
            }
        }
        monsters.add(monster);
    }

    private Monster getReinitializedMob(float newX, float newY, int newMonsterLvl, Monster previousMonster) {
        previousMonster.monsterInit(newX, newY, newMonsterLvl);
        return previousMonster;
    }

    public void freeMonsterForReuse(Monster monster) {
        MonsterHandler.monsterPoolHasFree = true;
        MonsterHandler.freeMonsters++;
        monsterPool.add(monster);
    }
}
