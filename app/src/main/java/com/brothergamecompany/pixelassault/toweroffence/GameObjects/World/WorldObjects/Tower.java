package com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects;

import com.brothergamecompany.pixelassault.framework.GameObject;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo.TowerStats;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxgm_umv4xdu on 24.06.2017.
 */

public class Tower extends GameObject {

    public float attackSpeed;//количество атак в секунду
    private float attackDelay;
    public int towerLevel;
    public float towerDamage;
    private Monster targetMonster;
    private boolean targetChosen;
    public float stateTime;
    public boolean onMap;
    public List<TowerBullet> towerBullets;
    private float attackTime;
    public int gridX;
    public int gridY;
    public boolean selected;//если выбрать, то будет показан круг рэнджа и апгрейды.
    public static final float TOWER_WIDTH = 2.0f;
    public static final float TOWER_HEIGHT = 2.0f;

    public void changePos(int gridX, int gridY) {
        this.position.set(World.tileCenterCoords(gridX), World.tileCenterCoords(gridY));
    }
    public Tower(int gridX, int gridY, int towerLevel) {
        super(World.tileCenterCoords(gridX), World.tileCenterCoords(gridY), TOWER_WIDTH, TOWER_HEIGHT);
        this.towerLevel = towerLevel;
        attackSpeed = TowerStats.getTowerAttackSpd(towerLevel);
        towerDamage = TowerStats.getTowerDamage(towerLevel);
        attackDelay = 1 / attackSpeed;
        targetChosen = false;
        towerBullets = new ArrayList<>();
        onMap = false;
    }
    public void reInit(int gridX, int gridY, int towerLevel, boolean onMap) {
        changePos(gridX, gridY);
        this.towerLevel = towerLevel;
        attackSpeed = TowerStats.getTowerAttackSpd(towerLevel);
        towerDamage = TowerStats.getTowerDamage(towerLevel);
        attackDelay = 1 / attackSpeed;
        targetChosen = false;
        towerBullets = new ArrayList<>();
        this.onMap = onMap;
    }

    public void update(float deltaTime) {
        for (int i = 0; i < towerBullets.size(); i++) {
            TowerBullet t = towerBullets.get(i);
            boolean reachedMob = t.update(deltaTime);
            if (reachedMob){
                towerBullets.remove(t);
            }
        }
        if (targetMonster != null) if (!targetMonster.ifInRange(this) || targetMonster.state == Monster.STATE_KILLED || targetMonster.state == Monster.STATE_REACHED_MAELSTROM) targetChosen = false;
        if (targetChosen) {
            if (attackTime >= attackDelay){
                shoot();
            }
            attackTime += deltaTime;
            stateTime += deltaTime;
        }
        else {
            chooseTarget();
        }
    }

    private void shoot() {
        towerBullets.add(new TowerBullet(position, targetMonster, TowerStats.ifSpins(towerLevel), towerDamage, towerLevel));
        stateTime = 0;
        attackTime = 0;
    }

    private void chooseTarget(){
        for (Monster monster : World.monstersSpawned){
            if (monster.ifInRange(this)){
                targetMonster = monster;
                targetChosen = true;
            }
        }
    }

    public void selectTower(){
        selected = !selected;
        for (Tower t : World.towers){
            t.selected = false;
        }
    }
}

