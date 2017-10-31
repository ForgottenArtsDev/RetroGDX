package com.forgottenartsstudios.editorsuite;

/**
 * Created by Perfekt on 7/31/2016.
 */
public class editorPlayerData {
    private String name;
    private int access;

    private int sprite;

    private int map;
    private int x;
    private int y;
    private int dir;

    // Movement //
    private int OffsetX;
    private int OffsetY;
    private int Moving;
    private int Step;

    public String getName() {
        return name;
    }
    public int getAccess() {
        return access;
    }
    public int getSprite() {
        return sprite;
    }
    public int getMap() {
        return map;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getDir() {
        return dir;
    }
    public int getOffsetX() { return OffsetX; }
    public int getOffsetY() { return OffsetY; }
    public int isMoving() { return Moving; }
    public int getStep() { return Step; }

    public void setName(String newName) { name = newName; }
    public void setAccess(int newAccess) {
        access = newAccess;
    }
    public void setSprite(int newSprite) {
        sprite = newSprite;
    }
    public void setMap(int newMap) {
        map = newMap;
    }
    public void setX(int newX) {
        x = newX;
    }
    public void setY(int newY) {
        y = newY;
    }
    public void setDir(int newDir) {
        dir = newDir;
    }
    public void setOffsetX(int newOffsetX) { OffsetX = newOffsetX; }
    public void setOffsetY(int newOffsetY) { OffsetY = newOffsetY; }
    public void setIsMoving(int isMoving) { Moving = isMoving; }
    public void setStep(int newStep) { Step = newStep; }
}
