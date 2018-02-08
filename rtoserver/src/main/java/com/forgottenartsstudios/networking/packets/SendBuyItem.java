package com.forgottenartsstudios.networking.packets;

/**
 * Created by forgo on 10/14/2017.
 */

public class SendBuyItem extends Packet {
    public int index;
    public int shopNum;
    public int shopSlot;
    public int buyAmt;
}
