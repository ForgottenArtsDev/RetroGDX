package com.forgottenartsstudios.data;

import com.forgottenartsstudios.networking.packets.Packet;

import java.io.Serializable;

/**
 * Created by Perfekt on 7/31/2016.
 */
public class TileData_ServerStruct extends Packet implements Serializable {

    public int X;
    public int Y;
    public int Tileset;

}
