package com.forgottenartsstudios.networking.packets;

import com.forgottenartsstudios.data.Item_Struct;

/**
 * Created by forgo on 10/12/2017.
 */

public class SendItems extends Packet {
    public int index;
    public int itemNum;
    public Item_Struct item;
}
