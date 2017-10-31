package com.forgottenartsstudios.networking.packets;

/**
 * Created by forgo on 10/10/2017.
 */

public class SendNPCSpawn extends Packet {
    public String name;
    public int npcNum;
    public int mapNPCNum;
    public int hp;
    public int maxHP;
    public int x;
    public int y;
    public int dir;
    public int sprite;
    public int isMoving;
}
