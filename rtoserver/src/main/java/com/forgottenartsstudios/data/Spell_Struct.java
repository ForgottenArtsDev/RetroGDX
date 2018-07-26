package com.forgottenartsstudios.data;

import java.io.Serializable;

/**
 * Created by forgo on 12/16/2017.
 */

public class Spell_Struct implements Serializable {
    public String Name;
    public int Type;
    public int Icon;

    public int LevelReq;
    public int ClassReq;
    public int Range;

    public int Animation;
    public int AnimFrames;
    public int AnimSpeed;

    public int CastTime;
    public int CoolDown;

    public int MPCost;
    public int DmgHealAmt;
    public int SoundFx;
}
