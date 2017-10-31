package com.forgottenartsstudios.networking.packets;

/**
 * Created by Perfekt on 9/7/2016.
 */
public class SendNPCMove extends Packet {
    public int mapNPCNum;
    public int x;
    public int y;
    public int dir;
    public int isMoving;
}
