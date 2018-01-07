package com.forgottenartsstudios.networking.packets;

/**
 * Created by forgo on 1/5/2018.
 */

public class SendMapSpells extends Packet {
    public int index;
    public int target;
    public int spellNum;
    public int x;
    public int y;
    public int type;
}
