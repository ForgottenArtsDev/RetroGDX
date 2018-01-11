package com.forgottenartsstudios.networking.packets;

/**
 * Created by forgo on 1/11/2018.
 */

public class SendCoolDown extends Packet {
    public int index;
    public int spellSlot;
    public int coolDown;
}
