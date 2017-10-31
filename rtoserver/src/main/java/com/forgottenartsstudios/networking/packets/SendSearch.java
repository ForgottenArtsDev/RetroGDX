package com.forgottenartsstudios.networking.packets;

/**
 * Created by forgo on 10/12/2017.
 */

public class SendSearch extends Packet {
    public int searchType;
    public int index;
    public int npcIndex;
    public int x;
    public int y;
    public int mapNum;
}
