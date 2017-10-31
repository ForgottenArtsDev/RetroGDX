package com.forgottenartsstudios.networking.packets;

/**
 * Created by forgo on 10/9/2017.
 */

public class MovePlayer extends Packet {
    public int Index;
    public int Map;
    public int X;
    public int Y;
    public int Dir;
    public boolean canMove;
}
