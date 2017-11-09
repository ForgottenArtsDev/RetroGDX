package com.forgottenartsstudios.data;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by forgo on 10/15/2017.
 */

public class Damage_Struct {
    private int mapNpcNum;
    private int x;
    private int y;
    private int damage;
    private int timer;

    public int getMapNpcNum() { return mapNpcNum; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getDamage() { return damage; }
    public int getTimer() { return timer; }

    public void setMapNpcNum(int mapNpcNum) {this.mapNpcNum = mapNpcNum; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setTimer(int timer) { this.timer = timer; }
}
