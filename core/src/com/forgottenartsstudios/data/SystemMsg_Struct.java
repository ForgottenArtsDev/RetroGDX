package com.forgottenartsstudios.data;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by forgo on 11/9/2017.
 */

public class SystemMsg_Struct {
    private int index;
    private int x;
    private int y;
    private String msg;
    private int timer;
    private Color color;

    public int getIndex() { return index; }
    public int getX() { return x; }
    public int getY() { return y; }
    public String getMsg() { return msg; }
    public int getTimer() { return timer; }
    public Color getColor() { return color; }

    public void setIndex(int index) { this.index = index; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setMsg(String msg) { this.msg = msg; }
    public void setTimer(int timer) { this.timer = timer; }
    public void setColor(Color color) { this.color = color; }
}
