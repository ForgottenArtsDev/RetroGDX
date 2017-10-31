package com.forgottenartsstudios.data;

import com.forgottenartsstudios.networking.packets.Packet;

import java.io.Serializable;

/**
 * Created by Perfekt on 7/31/2016.
 */
public class Tile_ServerStruct extends Packet implements Serializable{

    public TileData_ServerStruct[] Layer;
    public int[] Autotile;
    public int Type;
    public int Data1;
    public int Data2;
    public int Data3;
    public int DirBlock;

    // NOT SAVED
    public int[] TileNum = new int[4 + 1];

}
