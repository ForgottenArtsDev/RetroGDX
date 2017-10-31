package com.forgottenartsstudios.networking.packets;

import com.forgottenartsstudios.data.MapItem;
import com.forgottenartsstudios.helpers.ServerVars;

/**
 * Created by forgo on 10/23/2017.
 */

public class SendMapItems extends Packet {
    public MapItem[] mapItems = new MapItem[ServerVars.MaxMapItems + 1];
}
