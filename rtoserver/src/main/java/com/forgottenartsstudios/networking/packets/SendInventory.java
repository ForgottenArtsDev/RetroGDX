package com.forgottenartsstudios.networking.packets;

import com.forgottenartsstudios.data.Inventory_Struct;

/**
 * Created by forgo on 10/14/2017.
 */

public class SendInventory extends Packet {
    public int index;
    public Inventory_Struct[] invData = new Inventory_Struct[60 + 1];
}
