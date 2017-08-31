package com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects;

import com.brothergamecompany.pixelassault.framework.DynamicGameObject;
import com.brothergamecompany.pixelassault.framework.math.Vector2;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo.MonsterStats;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo.TowerStats;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.World;
import com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.Account;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.BasicValuesSynchronizer;

import static com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo.MonsterStats.getMonsterCoins;

/**
 * Created by maxgm_umv4xdu on 04.06.2017.
 */

public class Monster extends DynamicGameObject {
    public int monsterHp;
    public int monsterLvl;
    public float monsterSpeed;
    public int state;
    public static final int STATE_ALIVE = 0;
    public static final int STATE_KILLED = 1;
    public static final int STATE_REACHED_MAELSTROM = 2;


    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_LEFT = 3;

    //не убирать final модификатор! Иначе полетит пул для монстров. Если хотите
    //создать босса с другим размером, пожалуйста делайте новый класс.
    //я плохой программист, поэтому пишу этот комментарий.
    public static final float MONSTER_WIDTH = 1.1f;
    public static final float MONSTER_HEIGHT = 1.1f;

    public float stateTime;

    private float deathAnimationLength;
    private float sinkAnimationLength;
    private boolean animInitDone;
    private int currentPathNumber = 0;
    private int currentCellX;
    private int currentCellY;
    public int direction;
    private boolean updateDirection = false;


    public Monster(float x, float y, int monsterLvl) {
        super(x, y, MONSTER_WIDTH, MONSTER_HEIGHT);
        monsterInit(x, y, monsterLvl);
    }

    public void monsterInit(float x, float y, int monsterLvl) {
        this.position.set(x, y);
        this.monsterLvl = monsterLvl;
        stateTime = 0;
        currentCellX = Account.MOB_SPAWNER_X;
        currentCellY = Account.MOB_SPAWNER_Y;
        monsterSpeed = MonsterStats.getMonsterSpeed(monsterLvl);
        monsterHp = MonsterStats.getMonsterHp(monsterLvl);
        currentPathNumber = 0;
        state = STATE_ALIVE;
        animInitDone = false;
        direction = DIRECTION_RIGHT;
    }

    //единственное условие - все тайлы должны быть связаны. Нужно будет проверять это в построителе
    public boolean update(float deltaTime) {
        stateTime += deltaTime;
        if (!animInitDone) {initAnimLength(); animInitDone = true;}
        resetVelocity();

        if (monsterHp <= 0 && state == STATE_ALIVE){
            stateTime = 0;
            state = STATE_KILLED;
        }

        if(World.monsterPath.get(currentPathNumber).type2 == Tile.TYPE_LAST_PATH_TILE && state != STATE_REACHED_MAELSTROM){
            stateTime = 0;
            state = STATE_REACHED_MAELSTROM;
        }

        if (state == STATE_ALIVE) {
            //1 - up, 2 - right, 3 - down, 4 - left
            int moveDirection = getNextDirection();
            if (moveDirection == 1) {
                goUp();
            }
            if (moveDirection == 3) {
                goDown();
            }
            if (moveDirection == 2) {
                goRight();
            }
            if (moveDirection == 4) {
                goLeft();
            }

            position.add(velocity.x * deltaTime, velocity.y * deltaTime);
            bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
            if (World.monsterPath.get(currentPathNumber + 1).position.dist(position) < 0.1f) {
                currentPathNumber++;
            }
        }
        if (state == STATE_REACHED_MAELSTROM && stateTime > sinkAnimationLength) {
            return true;
        }
        if (state == STATE_KILLED && stateTime > deathAnimationLength){
            BasicValuesSynchronizer.totalMonstersKilled++;
            BasicValuesSynchronizer.totalCoins += MonsterStats.getMonsterCoins(monsterLvl);
            BasicValuesSynchronizer.totalExp += MonsterStats.getMonsterExp(monsterLvl);
            BasicValuesSynchronizer.killedMobs.add(monsterLvl);
            return true;
        }
        return false;
    }

    //1 - up, 2 - right, 3 - down, 4 - left
    private int getNextDirection() {
        Vector2 nextTilePos = World.monsterPath.get(currentPathNumber + 1).position;
        float dist = 0;
        int direction = 0;
        if (nextTilePos.x > position.x) {
            dist = nextTilePos.x - position.x;
            direction = 2;
        } else {
            dist = position.x - nextTilePos.x;
            direction = 4;
        }
        if (dist < nextTilePos.y - position.y) direction = 1;
        if (dist < position.y - nextTilePos.y) direction = 3;
        return direction;
    }

    private void initAnimLength() {
        deathAnimationLength = Assets.getMonsterAnimation(monsterLvl, STATE_KILLED).animationLength;
        sinkAnimationLength = Assets.getMonsterAnimation(monsterLvl, STATE_REACHED_MAELSTROM).animationLength;
    }

    private void goRight() {
        this.velocity.add(monsterSpeed, 0);
        direction = DIRECTION_RIGHT;
    }

    private void goLeft() {
        this.velocity.add(-monsterSpeed, 0);
        direction = DIRECTION_LEFT;
    }

    private void goUp() {
        this.velocity.add(0, monsterSpeed);
    }

    private void goDown() {
        this.velocity.add(0, -monsterSpeed);
    }

    private void resetVelocity() {
        this.velocity.set(0, 0);
    }



    public void updateForButton(float deltaTime) {
        stateTime+=deltaTime;
    }

    public boolean ifInRange(Tower tower) {
        return TowerStats.getTowerRange(tower.towerLevel) > position.dist(tower.position);
    }
}
