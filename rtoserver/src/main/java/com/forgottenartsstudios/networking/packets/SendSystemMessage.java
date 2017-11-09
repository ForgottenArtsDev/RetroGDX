package com.forgottenartsstudios.networking.packets;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by forgo on 11/9/2017.
 */

public class SendSystemMessage extends Packet {
    public int index;
    public int type;
    public String msg;
    public Color color;
}
