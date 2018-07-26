package com.forgottenartsstudios.data;

/**
 * Created by forgo on 1/5/2018.
 */

public class MapSpell {
    int spellNum;
    int x;
    int y;
    int type;
    int index;
    int frame;
    long timer;

    public int getSpellNum() { return spellNum; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getType() { return type; }
    public int getIndex() { return index; }
    public int getFrame() { return frame; }
    public long getTimer() { return timer; }

    public void setSpellNum(int spellNum) { this.spellNum = spellNum; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setType(int type) { this.type = type; }
    public void setIndex(int index) { this.index = index; }
    public void setFrame(int frame) { this.frame = frame; }
    public void setTimer(long timer) { this.timer = timer; }
}
