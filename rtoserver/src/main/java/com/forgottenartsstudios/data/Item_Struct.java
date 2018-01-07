package com.forgottenartsstudios.data;

import java.io.Serializable;

/**
 * Created by forgo on 1/8/2017.
 */

public class Item_Struct implements Serializable {
    // Name //
    public String Name;

    // Required Parameters //
    public int Icon;
    public int itemType;
    public int isStackable;
    public int LVL;
    public int HP;
    public int MP;
    public int STR;
    public int DEF;
    public int VIT;
    public int AGI;
    public int MAG;

    public int Cost;
    public int SoundFx;
}
