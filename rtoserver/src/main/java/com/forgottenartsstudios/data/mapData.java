package com.forgottenartsstudios.data;

import com.forgottenartsstudios.networking.packets.Packet;

import java.io.Serializable;

/**
 * Created by Perfekt on 7/31/2016.
 */
public class mapData extends Packet implements Serializable {

    public String Name;
    public int Revision;
    public int Moral;
    public int Tileset;
    public int Up;
    public int Down;
    public int Left;
    public int Right;
    public int Music;
    public int BootMap;
    public int BootX;
    public int BootY;
    public int MaxX;
    public int MaxY;
    public Tile_ServerStruct[][] Tile;
    public int Weather;
    public int[][] SoundID;
}
