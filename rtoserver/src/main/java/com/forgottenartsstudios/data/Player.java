package com.forgottenartsstudios.data;

import java.io.Serializable;

/**
 * Created by forgo on 10/6/2017.
 */

public class Player implements Serializable {
    private String Name;
    private int Job, Sprite, Level, Points;
    private int HP, MP, MaxHP, MaxMP;
    private int EXP, NextLVL;
    private int Map, X, Y, Dir;
    private int STR, DEF, VIT, AGI, MAG;
    private int Weapon, Offhand, Armor, Helmet, Acc1, Acc2;
    private int Party;
    private int HotKeyQ, HotKeyE;

    // Temp stuff
    private int OffsetX;
    private int OffsetY;
    private int Moving;
    private int Step;
    private int LastStep;
    private int Attacking;
    private long AttackTimer, DeathTimer;
    private int Target;
    private int TargetType;
    private int TempSprite;

    public Inventory_Struct[] inventory = new Inventory_Struct[60 + 1];
    public Spell_Inv_Struct[] spells = new Spell_Inv_Struct[60 + 1];

    public String getName() { return Name; }
    public int getJob() { return Job; }
    public int getSprite() { return Sprite; }
    public int getLevel() { return Level; }
    public int getPoints() { return Points; }
    public int getHP() { return HP; }
    public int getMP() { return MP; }
    public int getMaxHP() { return MaxHP; }
    public int getMaxMP() { return MaxMP; }
    public int getEXP() { return EXP; }
    public int getNextLVL() { return NextLVL; }
    public int getMap() { return Map; }
    public int getX() { return X; }
    public int getY() { return Y; }
    public int getDir() { return Dir; }
    public int getSTR() { return STR; }
    public int getDEF() { return DEF; }
    public int getVIT() { return VIT; }
    public int getAGI() { return AGI; }
    public int getMAG() { return MAG; }
    public int getWeapon() { return Weapon; }
    public int getOffhand() { return Offhand; }
    public int getArmor() { return Armor; }
    public int getHelmet() { return Helmet; }
    public int getAcc1() { return Acc1; }
    public int getAcc2() { return Acc2; }
    public int getStep() { return Step; }
    public int getLastStep() { return LastStep; }
    public int getMoving() { return Moving; }
    public int getOffsetX() { return OffsetX; }
    public int getOffsetY() { return OffsetY; }
    public int getAttacking() { return Attacking; }
    public long getAttackTimer() { return AttackTimer; }
    public int getParty() { return Party; }
    public int getHotKeyQ() { return HotKeyQ; }
    public int getHotKeyE() { return HotKeyE; }
    public int getTarget() { return Target; }
    public int getTargetType() { return TargetType; }
    public long getDeathTimer() { return DeathTimer; }
    public int getTempSprite() { return TempSprite; }

    public void setName(String name) { Name = name; }
    public void setJob(int job) { Job = job; }
    public void setSprite(int sprite) { Sprite = sprite; }
    public void setLevel(int level) { Level = level; }
    public void setPoints(int points) { Points = points; }
    public void setHP(int HP) { this.HP = HP; }
    public void setMP(int MP) { this.MP = MP; }
    public void setMaxHP(int MaxHP) { this.MaxHP = MaxHP; }
    public void setMaxMP(int MaxMP) { this.MaxMP = MaxMP; }
    public void setEXP(int EXP) { this.EXP = EXP; }
    public void setNextLVL(int NextLVL) { this.NextLVL = NextLVL; }
    public void setMap(int map) { Map = map; }
    public void setX(int x) { X = x; }
    public void setY(int y) { Y = y; }
    public void setDir(int dir) { Dir = dir; }
    public void setSTR(int str) { STR = str; }
    public void setDEF(int def) { DEF = def; }
    public void setVIT(int vit) { VIT = vit; }
    public void setAGI(int agi) { AGI = agi; }
    public void setMAG(int mag) { MAG = mag; }
    public void setWeapon(int weapon) { Weapon = weapon; }
    public void setOffhand(int offhand) { Offhand = offhand; }
    public void setArmor(int armor) { Armor = armor; }
    public void setHelmet(int helmet) { Helmet = helmet; }
    public void setAcc1(int acc1) { Acc1 = acc1; }
    public void setAcc2(int acc2) { Acc2 = acc2; }
    public void setStep(int step) { Step = step; }
    public void setLastStep(int lastStep) { LastStep = lastStep; }
    public void setMoving(int moving) { Moving = moving; }
    public void setOffsetX(int offsetX) { OffsetX = offsetX; }
    public void setOffsetY(int offsetY) { OffsetY = offsetY; }
    public void setAttacking(int attacking) { Attacking = attacking; }
    public void setAttackTimer(long attackTimer) { AttackTimer = attackTimer; }
    public void setParty(int party) { Party = party; }
    public void setHotKeyQ(int hotKeyQ) { HotKeyQ = hotKeyQ; }
    public void setHotKeyE(int hotKeyE) { HotKeyE = hotKeyE; }
    public void setTarget(int target) { Target = target; }
    public void setTargetType(int targetType) { TargetType = targetType; }
    public void setDeathTimer(long deathTimer) { DeathTimer = deathTimer; }
    public void setTempSprite(int tempSprite) { TempSprite = tempSprite; }
}
