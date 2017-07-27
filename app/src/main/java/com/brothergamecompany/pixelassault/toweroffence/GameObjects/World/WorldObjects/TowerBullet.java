package com.brothergamecompany.pixelassault.toweroffence.GameObjects.World.WorldObjects;

import com.brothergamecompany.pixelassault.framework.DynamicGameObject;
import com.brothergamecompany.pixelassault.framework.math.Vector2;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.ObjectsInfo.TowerStats;

public class TowerBullet extends DynamicGameObject {
    public static final float BULLET_WIDTH = 0.4f;
    public static final float BULLET_HEIGHT = 0.4f;
    private float BULLET_SPEED = 3f;
    private final Monster targetMonster;
    public boolean canSpin;
    public int angle;
    private final Vector2 targetPos;
    private final Vector2 targetPosCopy;
    private float bulletDamage;

    public TowerBullet(Vector2 towerPos, Monster targetMonster, boolean canSpin, float towerDamage, int towerLevel) {
        super(towerPos.x + TowerStats.getBulletPosX(towerLevel), towerPos.y + TowerStats.getBulletPosY(towerLevel), BULLET_WIDTH, BULLET_HEIGHT);
        BULLET_SPEED = TowerStats.getBulletSpeed(towerLevel);
        targetPos = targetMonster.position;
        this.targetMonster = targetMonster;
        this.canSpin = canSpin;
        bulletDamage = towerDamage;
        targetPosCopy = targetPos.cpy();
        angle = 0;
    }

    boolean update(float deltaTime) {
        targetPosCopy.set(targetPos.x, targetPos.y + Tile.TILE_HEIGHT / 2);
        float angle = targetPosCopy.sub(position).angle();
        float radAngle = angle * Vector2.TO_RADIANS;
        velocity.x = (float)Math.cos(radAngle) * BULLET_SPEED;
        velocity.y = (float)Math.sin(radAngle) * BULLET_SPEED;
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
        if (canSpin)
        {
            angle += 360 * deltaTime;
        }

        if (position.dist(targetPos.x, targetPos.y + Tile.TILE_HEIGHT / 2) < 0.1f){
            targetMonster.monsterHp -= bulletDamage;
            return true;
        }
        return false;
    }
}
