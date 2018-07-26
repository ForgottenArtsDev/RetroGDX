package com.forgottenartsstudios.data;

/**
 * Created by forgo on 10/10/2017.
 */

public class MapNPC {
    private String Name;
    private boolean canMove; // canMove - I don't feel like recreating NPCs, ya know?
    private int Num;
    private int Level;

    private int sprite;
    private int dir;

    private int hp;
    private int mp;
    private int maxHP;
    private int maxMP;

    private int x;
    private int y;
    private int spawnX;
    private int spawnY;
    private int offsetX;
    private int offsetY;

    private int step;
    private int lastStep;

    private int target;
    private int targetType;
    private int spawnWait;

    private int isMoving;

    private int[] vitals = new int[2 + 1];
    private int attacking;
    private int attackTimer;

    private boolean dead;

    public String getName() { return Name; }
    public boolean isCanMove() { return canMove; }
    public int getNum() { return Num; }
    public int getLevel() { return Level; }
    public int getSprite() { return sprite; }
    public int getDir() { return dir; }
    public int getHP() { return hp; }
    public int getMP() { return mp; }
    public int getMaxHP() { return maxHP; }
    public int getMaxMP() { return maxMP; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getSpawnX() { return spawnX; }
    public int getSpawnY() { return spawnY; }
    public int getOffsetX() { return offsetX; }
    public int getOffsetY() { return offsetY; }
    public int getStep() { return step; }
    public int getLastStep() { return lastStep; }
    public int getTarget() { return target; }
    public int getTargetType() { return targetType; }
    public int getSpawnWait() { return spawnWait; }
    public int getVitals(int i) { return vitals[i]; }
    public int getAttacking() { return attacking; }
    public int getAttackTimer() { return attackTimer; }
    public int getIsMoving() { return isMoving; }
    public boolean isDead() { return dead; }

    public void setName(String newName) { Name = newName; }
    public void setCanMove(boolean newCanMove) { canMove = newCanMove; }
    public void setNum(int newNum) { Num = newNum; }
    public void setLevel(int level) { Level = level; }
    public void setSprite(int newSprite) { sprite = newSprite; }
    public void setDir(int newDir) { dir = newDir; }
    public void setHP(int hp) { this.hp = hp; }
    public void setMP(int mp) { this.mp = mp; }
    public void setMaxHP(int maxHP) { this.maxHP = maxHP; }
    public void setMaxMP(int maxMP) { this.maxMP = maxMP; }
    public void setX(int newX) { x = newX; }
    public void setY(int newY) { y = newY; }
    public void setSpawnX(int spawnX) { this.spawnX = spawnX; }
    public void setSpawnY(int spawnY) { this.spawnY = spawnY; }
    public void setOffsetX(int newOffsetX) { offsetX = newOffsetX; }
    public void setOffsetY(int newOffsetY) { offsetY = newOffsetY; }
    public void setStep(int step) { this.step = step; }
    public void setLastStep(int lastStep) { this.lastStep = lastStep; }
    public void setTarget(int newTarget) { target = newTarget; }
    public void setTargetType(int newTargetType) { targetType = newTargetType; }
    public void setSpawnWait(int newSpawnWait) { spawnWait = newSpawnWait; }
    public void setVitals(int i, int newVital) { vitals[i] = newVital; }
    public void setAttacking(int attacking) { this.attacking = attacking; }
    public void setAttackTimer(int newAttackTimer) { attackTimer = newAttackTimer; }
    public void setMoving(int newMoving) { isMoving = newMoving; }
    public void setDead(boolean dead) { this.dead = dead; }
}
