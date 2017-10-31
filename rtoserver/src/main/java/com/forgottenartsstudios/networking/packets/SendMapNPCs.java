package com.forgottenartsstudios.networking.packets;

import com.forgottenartsstudios.data.MapNPC;
import com.forgottenartsstudios.helpers.ServerVars;

/**
 * Created by forgo on 10/22/2017.
 */

public class SendMapNPCs extends Packet {
    public MapNPC[] mapNPCs = new MapNPC[ServerVars.MaxMapNPCs + 1];
}
