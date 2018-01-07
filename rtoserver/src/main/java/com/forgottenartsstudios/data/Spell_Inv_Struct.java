package com.forgottenartsstudios.data;

/**
 * Created by forgo on 12/23/2017.
 */

public class Spell_Inv_Struct {
    public int spellNum;
    public int castTime;
    public long castTimeTimer;
    public int coolDown;
    public long coolDownTimer;

    public int getSpellNum() { return spellNum; }
    public int getCastTime() { return castTime; }
    public long getCastTimeTimer() { return castTimeTimer; }
    public int getCoolDown() { return coolDown; }
    public long getCoolDownTimer() { return coolDownTimer; }

    public void setSpellNum(int spellNum) { this.spellNum = spellNum; }
    public void setCastTime(int castTime) { this.castTime = castTime; }
    public void setCastTimeTimer(long castTimeTimer) { this.castTimeTimer = castTimeTimer; }
    public void setCoolDown(int coolDown) { this.coolDown = coolDown; }
    public void setCoolDownTimer(long coolDownTimer) { this.coolDownTimer = coolDownTimer; }
}
