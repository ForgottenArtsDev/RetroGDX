package com.forgottenartsstudios.data;

import java.io.Serializable;

/**
 * Created by Perfekt on 9/5/2016.
 */
public class NPC_Struct implements Serializable {
    // Name //
    public String Name;

    // Required Parameters //
    public int Sprite;
    public int Range;
    public int Type;
    public int Behaviour;
    public int SpawnSecs;
    public int Health;
    public int Exp;
    public int Level;

    public int STR;
    public int DEF;
    public int VIT;
    public int AGI;
    public int MAG;

    public int shopNum;

    public int weapon;
    public int armor;
    public int offhand;
    public int helmet;

    public int drop1;
    public int drop2;
    public int drop3;
    public int drop4;
    public int drop5;
    public int dropVal1;
    public int dropVal2;
    public int dropVal3;
    public int dropVal4;
    public int dropVal5;
    public int dropChance1;
    public int dropChance2;
    public int dropChance3;
    public int dropChance4;
    public int dropChance5;

    public int SoundFx;
}
