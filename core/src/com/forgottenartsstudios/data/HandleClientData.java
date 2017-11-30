package com.forgottenartsstudios.data;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.forgottenartsstudios.helpers.AssetLoader;
import com.forgottenartsstudios.helpers.Variables;
import com.forgottenartsstudios.networking.packetdata.*;
import com.forgottenartsstudios.networking.packets.DisconnectPlayer;
import com.forgottenartsstudios.networking.packets.KeepAliveCheck;
import com.forgottenartsstudios.networking.packets.MovePlayer;
import com.forgottenartsstudios.networking.packets.PartyDecision;
import com.forgottenartsstudios.networking.packets.PartyInfo;
import com.forgottenartsstudios.networking.packets.PlayerData;
import com.forgottenartsstudios.networking.packets.SendHPRegen;
import com.forgottenartsstudios.networking.packets.SendInventory;
import com.forgottenartsstudios.networking.packets.SendItems;
import com.forgottenartsstudios.networking.packets.SendKillNPC;
import com.forgottenartsstudios.networking.packets.SendLogin;
import com.forgottenartsstudios.networking.packets.SendMapItems;
import com.forgottenartsstudios.networking.packets.SendMapNPCs;
import com.forgottenartsstudios.networking.packets.SendMessage;
import com.forgottenartsstudios.networking.packets.SendNPCDead;
import com.forgottenartsstudios.networking.packets.SendNPCDir;
import com.forgottenartsstudios.networking.packets.SendNPCDmg;
import com.forgottenartsstudios.networking.packets.SendNPCMove;
import com.forgottenartsstudios.networking.packets.SendNPCSpawn;
import com.forgottenartsstudios.networking.packets.SendNPCXP;
import com.forgottenartsstudios.networking.packets.SendOpenPlayerMenu;
import com.forgottenartsstudios.networking.packets.SendPartyInvite;
import com.forgottenartsstudios.networking.packets.SendPlayerDmg;
import com.forgottenartsstudios.networking.packets.SendPlayerWarp;
import com.forgottenartsstudios.networking.packets.SendRespawnNPC;
import com.forgottenartsstudios.networking.packets.SendShop;
import com.forgottenartsstudios.networking.packets.SendSystemMessage;
import com.forgottenartsstudios.networking.packets.SendVital;

import java.lang.reflect.Array;

/**
 * Created by forgo on 10/8/2017.
 */

public class HandleClientData {
    public static void HandleSendLogin(Object object) {
        Variables.MyAccount = new AccountData();
        for (int i = 1; i <= 3; i++) {
            Variables.MyAccount.chars[i] = new Player();
        }

        SendLogin sndLogin = (SendLogin) object;
        Variables.MyIndex = sndLogin.Index;
        Variables.MyAccount.setID(sndLogin.accountData.getID());
        Variables.MyAccount.setPW(sndLogin.accountData.getPW());
        Variables.MyAccount.setCID(sndLogin.accountData.getCID());

        Variables.players[Variables.MyIndex].inventory = new Inventory_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            Variables.players[Variables.MyIndex].inventory[i] = new Inventory_Struct();
        }

        // 1
        Variables.MyAccount.chars[1].setName(sndLogin.char1.getName());

        Variables.MyAccount.chars[1].setJob(sndLogin.char1.getJob());
        Variables.MyAccount.chars[1].setSprite(sndLogin.char1.getSprite());
        Variables.MyAccount.chars[1].setLevel(sndLogin.char1.getLevel());
        Variables.MyAccount.chars[1].setPoints(sndLogin.char1.getPoints());
        Variables.MyAccount.chars[1].setHP(sndLogin.char1.getHP());
        Variables.MyAccount.chars[1].setMP(sndLogin.char1.getMP());
        Variables.MyAccount.chars[1].setMaxHP(sndLogin.char1.getMaxHP());
        Variables.MyAccount.chars[1].setMaxMP(sndLogin.char1.getMaxMP());
        Variables.MyAccount.chars[1].setEXP(sndLogin.char1.getEXP());
        Variables.MyAccount.chars[1].setNextLVL(sndLogin.char1.getNextLVL());

        Variables.MyAccount.chars[1].setMap(sndLogin.char1.getMap());
        Variables.MyAccount.chars[1].setX(sndLogin.char1.getX());
        Variables.MyAccount.chars[1].setY(sndLogin.char1.getY());
        Variables.MyAccount.chars[1].setDir(sndLogin.char1.getDir());

        Variables.MyAccount.chars[1].setSTR(sndLogin.char1.getSTR());
        Variables.MyAccount.chars[1].setDEF(sndLogin.char1.getDEF());
        Variables.MyAccount.chars[1].setVIT(sndLogin.char1.getVIT());
        Variables.MyAccount.chars[1].setAGI(sndLogin.char1.getAGI());
        Variables.MyAccount.chars[1].setMAG(sndLogin.char1.getMAG());

        Variables.MyAccount.chars[1].setWeapon(sndLogin.char1.getWeapon());
        Variables.MyAccount.chars[1].setOffhand(sndLogin.char1.getOffhand());
        Variables.MyAccount.chars[1].setArmor(sndLogin.char1.getArmor());
        Variables.MyAccount.chars[1].setHelmet(sndLogin.char1.getHelmet());
        Variables.MyAccount.chars[1].setAcc1(sndLogin.char1.getAcc1());
        Variables.MyAccount.chars[1].setAcc2(sndLogin.char1.getAcc2());

        Variables.MyAccount.chars[1].inventory = new Inventory_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            Variables.MyAccount.chars[1].inventory[i] = new Inventory_Struct();
            Variables.MyAccount.chars[1].inventory[i].setItemNum(sndLogin.char1.inventory[i].getItemNum());
            Variables.MyAccount.chars[1].inventory[i].setItemVal(sndLogin.char1.inventory[i].getItemVal());
        }

        // CHAR 2
        Variables.MyAccount.chars[2].setName(sndLogin.char2.getName());

        Variables.MyAccount.chars[2].setJob(sndLogin.char2.getJob());
        Variables.MyAccount.chars[2].setSprite(sndLogin.char2.getSprite());
        Variables.MyAccount.chars[2].setLevel(sndLogin.char2.getLevel());
        Variables.MyAccount.chars[2].setPoints(sndLogin.char2.getPoints());
        Variables.MyAccount.chars[2].setHP(sndLogin.char2.getHP());
        Variables.MyAccount.chars[2].setMP(sndLogin.char2.getMP());
        Variables.MyAccount.chars[2].setMaxHP(sndLogin.char2.getMaxHP());
        Variables.MyAccount.chars[2].setMaxMP(sndLogin.char2.getMaxMP());
        Variables.MyAccount.chars[2].setEXP(sndLogin.char2.getEXP());
        Variables.MyAccount.chars[2].setNextLVL(sndLogin.char2.getNextLVL());

        Variables.MyAccount.chars[2].setMap(sndLogin.char2.getMap());
        Variables.MyAccount.chars[2].setX(sndLogin.char2.getX());
        Variables.MyAccount.chars[2].setY(sndLogin.char2.getY());
        Variables.MyAccount.chars[2].setDir(sndLogin.char2.getDir());

        Variables.MyAccount.chars[2].setSTR(sndLogin.char2.getSTR());
        Variables.MyAccount.chars[2].setDEF(sndLogin.char2.getDEF());
        Variables.MyAccount.chars[2].setVIT(sndLogin.char2.getVIT());
        Variables.MyAccount.chars[2].setAGI(sndLogin.char2.getAGI());
        Variables.MyAccount.chars[2].setMAG(sndLogin.char2.getMAG());

        Variables.MyAccount.chars[2].setWeapon(sndLogin.char2.getWeapon());
        Variables.MyAccount.chars[2].setOffhand(sndLogin.char2.getOffhand());
        Variables.MyAccount.chars[2].setArmor(sndLogin.char2.getArmor());
        Variables.MyAccount.chars[2].setHelmet(sndLogin.char2.getHelmet());
        Variables.MyAccount.chars[2].setAcc1(sndLogin.char2.getAcc1());
        Variables.MyAccount.chars[2].setAcc2(sndLogin.char2.getAcc2());

        Variables.MyAccount.chars[2].inventory = new Inventory_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            Variables.MyAccount.chars[2].inventory[i] = new Inventory_Struct();
            Variables.MyAccount.chars[2].inventory[i].setItemNum(sndLogin.char2.inventory[i].getItemNum());
            Variables.MyAccount.chars[2].inventory[i].setItemVal(sndLogin.char2.inventory[i].getItemVal());
        }

        // CHAR 3
        Variables.MyAccount.chars[3].setName(sndLogin.char3.getName());

        Variables.MyAccount.chars[3].setJob(sndLogin.char3.getJob());
        Variables.MyAccount.chars[3].setSprite(sndLogin.char3.getSprite());
        Variables.MyAccount.chars[3].setLevel(sndLogin.char3.getLevel());
        Variables.MyAccount.chars[3].setPoints(sndLogin.char3.getPoints());
        Variables.MyAccount.chars[3].setHP(sndLogin.char3.getHP());
        Variables.MyAccount.chars[3].setMP(sndLogin.char3.getMP());
        Variables.MyAccount.chars[3].setMaxHP(sndLogin.char3.getMaxHP());
        Variables.MyAccount.chars[3].setMaxMP(sndLogin.char3.getMaxMP());
        Variables.MyAccount.chars[3].setEXP(sndLogin.char3.getEXP());
        Variables.MyAccount.chars[3].setNextLVL(sndLogin.char3.getNextLVL());

        Variables.MyAccount.chars[3].setMap(sndLogin.char3.getMap());
        Variables.MyAccount.chars[3].setX(sndLogin.char3.getX());
        Variables.MyAccount.chars[3].setY(sndLogin.char3.getY());
        Variables.MyAccount.chars[3].setDir(sndLogin.char3.getDir());

        Variables.MyAccount.chars[3].setSTR(sndLogin.char3.getSTR());
        Variables.MyAccount.chars[3].setDEF(sndLogin.char3.getDEF());
        Variables.MyAccount.chars[3].setVIT(sndLogin.char3.getVIT());
        Variables.MyAccount.chars[3].setAGI(sndLogin.char3.getAGI());
        Variables.MyAccount.chars[3].setMAG(sndLogin.char3.getMAG());

        Variables.MyAccount.chars[3].setWeapon(sndLogin.char3.getWeapon());
        Variables.MyAccount.chars[3].setOffhand(sndLogin.char3.getOffhand());
        Variables.MyAccount.chars[3].setArmor(sndLogin.char3.getArmor());
        Variables.MyAccount.chars[3].setHelmet(sndLogin.char3.getHelmet());
        Variables.MyAccount.chars[3].setAcc1(sndLogin.char3.getAcc1());
        Variables.MyAccount.chars[3].setAcc2(sndLogin.char3.getAcc2());

        Variables.MyAccount.chars[3].inventory = new Inventory_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            Variables.MyAccount.chars[3].inventory[i] = new Inventory_Struct();
            Variables.MyAccount.chars[3].inventory[i].setItemNum(sndLogin.char3.inventory[i].getItemNum());
            Variables.MyAccount.chars[3].inventory[i].setItemVal(sndLogin.char3.inventory[i].getItemVal());
        }

        Variables.Game_State = Variables.Game_State_CharSelect;
    }
    public static void HandleAccountNotFound() {
        Variables.AccountNotFound = true;
    }
    public static void HandleAccountRegistered() {
        Variables.AccountNotFound = false;
        Variables.AccountRegistered = true;
    }
    public static void HandlePlayerData(Object object) {
        PlayerData plData = (PlayerData) object;
        int index = plData.index;
        if (plData.playerData.getMap() == 0) { return; }

        Variables.players[index].setName(plData.playerData.getName());

        for (int i = 1; i <= 3; i++) {
            if (Variables.players[index].getName().equals(Variables.MyAccount.chars[i].getName())) {
                Variables.MyAccount.setActiveChar(i);
                break;
            }
        }

        Variables.players[index].setJob(plData.playerData.getJob());
        Variables.players[index].setLevel(plData.playerData.getLevel());
        Variables.players[index].setSprite(plData.playerData.getSprite());
        Variables.players[index].setPoints(plData.playerData.getPoints());

        Variables.players[index].setHP(plData.playerData.getHP());
        Variables.players[index].setMP(plData.playerData.getMP());
        Variables.players[index].setMaxHP(plData.playerData.getMaxHP());
        Variables.players[index].setMaxMP(plData.playerData.getMaxMP());

        Variables.players[index].setEXP(plData.playerData.getEXP());
        Variables.players[index].setNextLVL(plData.playerData.getNextLVL());

        Variables.players[index].setMap(plData.playerData.getMap());
        Variables.players[index].setX(plData.playerData.getX());
        Variables.players[index].setY(plData.playerData.getY());
        Variables.players[index].setDir(plData.playerData.getDir());

        Variables.players[index].setSTR(plData.playerData.getSTR());
        Variables.players[index].setDEF(plData.playerData.getDEF());
        Variables.players[index].setVIT(plData.playerData.getVIT());
        Variables.players[index].setAGI(plData.playerData.getAGI());
        Variables.players[index].setMAG(plData.playerData.getMAG());

        Variables.players[index].setWeapon(plData.playerData.getWeapon());
        Variables.players[index].setOffhand(plData.playerData.getOffhand());
        Variables.players[index].setArmor(plData.playerData.getArmor());
        Variables.players[index].setHelmet(plData.playerData.getHelmet());
        Variables.players[index].setAcc1(plData.playerData.getAcc1());
        Variables.players[index].setAcc2(plData.playerData.getAcc2());

        Variables.players[index].setParty(plData.playerData.getParty());

        if (index == Variables.MyIndex) {
            Variables.MinX = 0;
            Variables.MinY = 0;
            Variables.MaxX = 14;
            Variables.MaxY = 14;
        }

        Variables.Game_State = Variables.Game_State_InGame;
    }
    public static void HandleMovePlayer(Object object) {
        MovePlayer mPlayer = (MovePlayer) object;

        int Index = mPlayer.Index;
        int Map = mPlayer.Map;
        int X = mPlayer.X;
        int Y = mPlayer.Y;
        int Dir = mPlayer.Dir;
        boolean canMove = mPlayer.canMove;

        if (Index != Variables.MyIndex) {
            if (canMove) {
                Variables.players[Index].setMap(Map);
                Variables.players[Index].setX(X);
                Variables.players[Index].setY(Y);
                Variables.players[Index].setDir(Dir);

                switch (Dir) {
                    case Variables.DIR_UP:
                        Variables.players[Index].setOffsetY(32);
                        break;
                    case Variables.DIR_DOWN:
                        Variables.players[Index].setOffsetY(32 * -1);
                        break;
                    case Variables.DIR_LEFT:
                        Variables.players[Index].setOffsetX(32);
                        break;
                    case Variables.DIR_RIGHT:
                        Variables.players[Index].setOffsetX(32 * -1);
                        break;
                }
            } else {
                Variables.players[Index].setDir(Dir);
            }
        }
    }
    public static void HandleSpawnNPC(Object object) {
        SendNPCSpawn sndNPCSpawn = (SendNPCSpawn) object;

        String name = sndNPCSpawn.name;
        int npcNum = sndNPCSpawn.npcNum;
        int mapNPCNum = sndNPCSpawn.mapNPCNum;
        int x = sndNPCSpawn.x;
        int y = sndNPCSpawn.y;
        int hp = sndNPCSpawn.hp;
        int maxHP = sndNPCSpawn.maxHP;
        int dir = sndNPCSpawn.dir;
        int sprite = sndNPCSpawn.sprite;

        //Variables.MapNPCs[mapNPCNum] = new MapNPC();

        Variables.MapNPCs[mapNPCNum].setName(name);
        Variables.MapNPCs[mapNPCNum].setNum(npcNum);
        Variables.MapNPCs[mapNPCNum].setHP(hp);
        Variables.MapNPCs[mapNPCNum].setMaxHP(maxHP);
        Variables.MapNPCs[mapNPCNum].setX(x);
        Variables.MapNPCs[mapNPCNum].setY(y);
        Variables.MapNPCs[mapNPCNum].setDir(dir);
        Variables.MapNPCs[mapNPCNum].setSprite(sprite);
        Variables.MapNPCs[mapNPCNum].setDead(false);
    }
    public static void HandleKeepAliveCheck(Object object) { SendClientData.SendKeepAliveCheck(); }
    public static void HandleDisconnectPlayer(Object object) {
        DisconnectPlayer dPlayer = (DisconnectPlayer) object;

        int Index = dPlayer.index;

        Variables.players[Index] = new Player();
    }
    public static void HandleNPCDead(Object object) {
        SendNPCDead sndNPCDead = (SendNPCDead) object;
        int mapNPCNum = sndNPCDead.mapNPCNum;
    }
    public static void HandleNPCDir(Object object) {
        SendNPCDir sndNPCDir = (SendNPCDir) object;
        int npcNum = sndNPCDir.mapNPCNum;
        int npcDir = sndNPCDir.dir;

        Variables.MapNPCs[npcNum].setDir(npcDir);
    }
    public static void HandleNPCMove(Object object) {
        SendNPCMove sndNPCMove = (SendNPCMove) object;
        int npcNum = sndNPCMove.mapNPCNum;
        int x = sndNPCMove.x;
        int y = sndNPCMove.y;
        int dir = sndNPCMove.dir;
        int isMoving = sndNPCMove.isMoving;

        Variables.MapNPCs[npcNum].setX(x);
        Variables.MapNPCs[npcNum].setY(y);
        Variables.MapNPCs[npcNum].setDir(dir);
        Variables.MapNPCs[npcNum].setMoving(isMoving);

        switch (dir) {
            case Variables.DIR_UP:
                Variables.MapNPCs[npcNum].setOffsetY(32);
                return;
            case Variables.DIR_DOWN:
                Variables.MapNPCs[npcNum].setOffsetY(32 * -1);
                return;
            case Variables.DIR_LEFT:
                Variables.MapNPCs[npcNum].setOffsetX(32);
                return;
            case Variables.DIR_RIGHT:
                Variables.MapNPCs[npcNum].setOffsetX(32 * -1);
                return;
        }
    }
    public static void HandleVital(Object object) {
        SendVital sVital = (SendVital) object;

        Variables.players[Variables.MyIndex].setLevel(sVital.lvl);

        Variables.players[Variables.MyIndex].setHP(sVital.hp);
        Variables.players[Variables.MyIndex].setMaxHP(sVital.maxHP);
        Variables.players[Variables.MyIndex].setMP(sVital.mp);
        Variables.players[Variables.MyIndex].setMaxMP(sVital.maxMP);

        Variables.players[Variables.MyIndex].setEXP(sVital.exp);
        Variables.players[Variables.MyIndex].setNextLVL(sVital.nextLvl);
        Variables.players[Variables.MyIndex].setPoints(sVital.points);

        Variables.players[Variables.MyIndex].setSTR(sVital.str);
        Variables.players[Variables.MyIndex].setDEF(sVital.def);
        Variables.players[Variables.MyIndex].setVIT(sVital.vit);
        Variables.players[Variables.MyIndex].setAGI(sVital.agi);
        Variables.players[Variables.MyIndex].setMAG(sVital.mag);

        Variables.players[Variables.MyIndex].setWeapon(sVital.weapon);
        Variables.players[Variables.MyIndex].setOffhand(sVital.offhand);
        Variables.players[Variables.MyIndex].setArmor(sVital.armor);
        Variables.players[Variables.MyIndex].setHelmet(sVital.helmet);
        Variables.players[Variables.MyIndex].setAcc1(sVital.acc1);
        Variables.players[Variables.MyIndex].setAcc2(sVital.acc2);

        Variables.useItem = false;
        Variables.buyItem = false;
    }
    public static void HandlePlayerWarp(Object object) {
        Variables.reloadingMap = true;
        Variables.pauseMovement = true;
        SendPlayerWarp sPWarp = (SendPlayerWarp) object;

        int index = sPWarp.index;
        int mapNum = sPWarp.mapNum;
        int x = sPWarp.x;
        int y = sPWarp.y;
        int oldMapNum = Variables.players[index].getMap();

        if (index == Variables.MyIndex) {
            Variables.players[index].setMap(mapNum);
            Variables.players[index].setX(x);
            Variables.players[index].setY(y);
        }

        Variables.reloadingMap = false;
    }
    public static void HandleItems(Object object) {
        SendItems sItems = (SendItems) object;
        int itemNum = sItems.itemNum;
        Variables.Items[itemNum] = sItems.item;
    }
    public static void HandleSendShop(Object object) {
        SendShop sndShop = (SendShop) object;

        Variables.ShopNum = sndShop.shopNum;
        Variables.Shop.Name = sndShop.name;
        Variables.Shop.welcomeMsg = sndShop.welcomeMsg;
        Variables.Shop.goodbyeMsg = sndShop.goodbyeMsg;
        Variables.Shop.salesTax = sndShop.salesTax;

        Variables.Shop.itemNum = new int[20 + 1];
        Variables.Shop.itemVal = new int[20 + 1];

        for (int i = 1; i <= 20; i++) {
            Variables.Shop.itemNum[i] = sndShop.itemNums[i];
            Variables.Shop.itemVal[i] = sndShop.itemVals[i];
        }

        Variables.inShop = true;
    }
    public static void HandleSendInventory(Object object) {
        SendInventory sInv = (SendInventory) object;

        int index = sInv.index;

        Variables.players[index].inventory = new Inventory_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            Variables.players[index].inventory[i] = new Inventory_Struct();
        }

        for (int i = 1; i <= 60; i++) {
            Variables.players[index].inventory[i].setItemNum(sInv.invData[i].getItemNum());
            Variables.players[index].inventory[i].setItemVal(sInv.invData[i].getItemVal());
        }
    }
    public static void HandleBoughtItemMsg() {
        Variables.BoughtMsgTimer = 1;
    }
    public static void HandleKillNPC(Object object) {
        SendKillNPC sKNPC = (SendKillNPC) object;

        int mapNpcNum = sKNPC.mapNpcIndex;

        Variables.MapNPCs[mapNpcNum].setHP(0);
        Variables.MapNPCs[mapNpcNum].setDead(true);
    }
    public static GlyphLayout layout = new GlyphLayout();
    public static void HandleDmgNPC(Object object) {
        SendNPCDmg sDmg = (SendNPCDmg) object;

        for (int i = 1; i <= 20; i++) {
            if (Variables.DrawNPCDamage[i].getTimer() == 0) {
                Variables.DrawNPCDamage[i].setMapNpcNum(sDmg.mapNPCNum);
                Variables.DrawNPCDamage[i].setDamage(sDmg.damage);

                layout.setText(AssetLoader.nameFont, Variables.DrawNPCDamage[i].getDamage() + "");
                float width = layout.width;// contains the width of the current set text

                float PlayerX = ((Variables.MapNPCs[Variables.DrawNPCDamage[i].getMapNpcNum()].getX() * Variables.MoveSize) + Variables.MapNPCs[Variables.DrawNPCDamage[i].getMapNpcNum()].getOffsetX());
                float PlayerY = ((Variables.MapNPCs[Variables.DrawNPCDamage[i].getMapNpcNum()].getY() * Variables.MoveSize) + Variables.MapNPCs[Variables.DrawNPCDamage[i].getMapNpcNum()].getOffsetY());

                float nameX = PlayerX - ((int)width / 2) + 24;
                float nameY = PlayerY - 28;

                Variables.DrawNPCDamage[i].setX((int)nameX);
                Variables.DrawNPCDamage[i].setY((int)nameY);
                Variables.DrawNPCDamage[i].setTimer(20);
                break;
            }
        }
    }
    public static void HandleRespawnNPC(Object object) {
        SendRespawnNPC sRNPC = (SendRespawnNPC) object;

        int hp = sRNPC.hp;
        int x = sRNPC.x;
        int y = sRNPC.y;
        int mapNPCNum = sRNPC.mapNPCNum;

        Variables.MapNPCs[mapNPCNum].setHP(hp);
        Variables.MapNPCs[mapNPCNum].setX(x);
        Variables.MapNPCs[mapNPCNum].setY(y);
        Variables.MapNPCs[mapNPCNum].setDir(Variables.DIR_DOWN);
        Variables.MapNPCs[mapNPCNum].setDead(false);
    }
    public static void HandleMapNPCs(Object object) {
        SendMapNPCs sendMapNPCs = (SendMapNPCs) object;

        for (int i = 1; i <= Variables.MaxMapNPCs; i++) {
            Variables.MapNPCs[i].setName(sendMapNPCs.mapNPCs[i].getName());
            Variables.MapNPCs[i].setNum(sendMapNPCs.mapNPCs[i].getNum());

            Variables.MapNPCs[i].setHP(sendMapNPCs.mapNPCs[i].getHP());
            Variables.MapNPCs[i].setMaxHP(sendMapNPCs.mapNPCs[i].getMaxHP());

            Variables.MapNPCs[i].setX(sendMapNPCs.mapNPCs[i].getX());
            Variables.MapNPCs[i].setY(sendMapNPCs.mapNPCs[i].getY());
            Variables.MapNPCs[i].setDir(sendMapNPCs.mapNPCs[i].getDir());

            Variables.MapNPCs[i].setTarget(sendMapNPCs.mapNPCs[i].getTarget());
            Variables.MapNPCs[i].setTargetType(sendMapNPCs.mapNPCs[i].getTargetType());

            Variables.MapNPCs[i].setDead(sendMapNPCs.mapNPCs[i].isDead());
        }
    }
    public static void HandleNPCXP(Object object) {
        SendNPCXP sendNPCXP = (SendNPCXP) object;
        for (int i = 1; i <= 20; i++) {
            if (Variables.DrawXP[i].getTimer() == 0) {
                Variables.DrawXP[i].setDamage(sendNPCXP.xp);

                layout.setText(AssetLoader.nameFont, Variables.DrawXP[i].getDamage() + "");
                float width = layout.width;// contains the width of the current set text

                float PlayerX = ((Variables.players[Variables.MyIndex].getX() * Variables.MoveSize) + Variables.players[Variables.MyIndex].getOffsetX());
                float PlayerY = ((Variables.players[Variables.MyIndex].getY() * Variables.MoveSize) + Variables.players[Variables.MyIndex].getOffsetY());

                float nameX = PlayerX - ((int)width / 2) + 24;
                float nameY = PlayerY - 28;

                Variables.DrawXP[i].setMapNpcNum(Variables.MyIndex);
                Variables.DrawXP[i].setX((int)nameX);
                Variables.DrawXP[i].setY((int)nameY);
                Variables.DrawXP[i].setTimer(20);
                break;
            }
        }
    }
    public static void HandleMapItems(Object object) {
        SendMapItems sendMapItems = (SendMapItems) object;

        for (int i = 1; i <= Variables.MaxMapItems; i++) {
            Variables.MapItems[i] = sendMapItems.mapItems[i];
        }
    }
    public static void HandleNotEnoughGoldMsg() {
        Variables.NotEnoughGoldMsgTimer = 1;
    }
    public static void HandlePlayerDmg(Object object) {
        SendPlayerDmg sDmg = (SendPlayerDmg) object;

        for (int i = 1; i <= 20; i++) {
            if (Variables.DrawPlayerDamage[i].getTimer() == 0) {
                Variables.DrawPlayerDamage[i].setMapNpcNum(sDmg.index);
                Variables.DrawPlayerDamage[i].setDamage(sDmg.damage);

                layout.setText(AssetLoader.nameFont, Variables.DrawPlayerDamage[i].getDamage() + "");
                float width = layout.width;// contains the width of the current set text

                float PlayerX = ((Variables.players[sDmg.index].getX() * Variables.MoveSize) + Variables.players[sDmg.index].getOffsetX());
                float PlayerY = ((Variables.players[sDmg.index].getY() * Variables.MoveSize) + Variables.players[sDmg.index].getOffsetY());

                float nameX = PlayerX - ((int)width / 2) + 24;
                float nameY = PlayerY - 28;

                Variables.DrawPlayerDamage[i].setX((int)nameX);
                Variables.DrawPlayerDamage[i].setY((int)nameY);
                Variables.DrawPlayerDamage[i].setTimer(20);
                break;
            }
        }
    }
    public static void HandleSendMessage(Object object) {
        SendMessage sendMessage = (SendMessage) object;

        int type = sendMessage.type;
        String msg = sendMessage.msg;
        double msgLength = msg.length();

        if (msgLength > 110) {
            String[] multiLine = msg.split("(?<=\\G.{110})");
            double lines = Array.getLength(multiLine);
            for (int a = 0; a <= lines - 1; a++) {
                for (int i = 1; i <= 100; i++) {
                    if (Variables.chatMessages[i].getType() == 0) {
                        Variables.chatMessages[i].setType(type);
                        Variables.chatMessages[i].setMsg(multiLine[a]);
                        Variables.chatMessageIndex = i;
                        break;
                    }
                }
            }
        } else {
            for (int i = 1; i <= 100; i++) {
                if (Variables.chatMessages[i].getType() == 0) {
                    Variables.chatMessages[i].setType(type);
                    Variables.chatMessages[i].setMsg(msg);
                    Variables.chatMessageIndex = i;
                    break;
                }
            }
        }
    }
    public static void HandleSendSystemMessage(Object object) {
        SendSystemMessage sendSystemMessage = (SendSystemMessage) object;

        int index = sendSystemMessage.index;
        int type = sendSystemMessage.type;
        String msg = sendSystemMessage.msg;
        Color color = sendSystemMessage.color;

        System.out.println(msg);

        for (int i = 1; i <= 20; i++) {
            if (Variables.DrawSystemMessage[i].getTimer() == 0) {
                Variables.DrawSystemMessage[i].setMsg(msg);

                layout.setText(AssetLoader.nameFont, Variables.DrawSystemMessage[i].getMsg());
                float width = layout.width;// contains the width of the current set text

                float PlayerX = ((Variables.players[index].getX() * Variables.MoveSize) + Variables.players[index].getOffsetX());
                float PlayerY = ((Variables.players[index].getY() * Variables.MoveSize) + Variables.players[index].getOffsetY());

                float nameX = PlayerX - ((int)width / 2) + 24;
                float nameY = PlayerY - 28;

                Variables.DrawSystemMessage[i].setIndex(index);
                Variables.DrawSystemMessage[i].setX((int) nameX);
                Variables.DrawSystemMessage[i].setY((int) nameY);
                Variables.DrawSystemMessage[i].setColor(color);
                Variables.DrawSystemMessage[i].setTimer(20);
                break;
            }
        }
    }
    public static void HandleHPRegen(Object object) {
        SendHPRegen sendHPRegen = (SendHPRegen) object;
        for (int i = 1; i <= 20; i++) {
            if (Variables.DrawHP[i].getTimer() == 0) {
                Variables.DrawHP[i].setDamage(sendHPRegen.hp);

                layout.setText(AssetLoader.nameFont, Variables.DrawHP[i].getDamage() + "");
                float width = layout.width;// contains the width of the current set text

                float PlayerX = ((Variables.players[Variables.MyIndex].getX() * Variables.MoveSize) + Variables.players[Variables.MyIndex].getOffsetX());
                float PlayerY = ((Variables.players[Variables.MyIndex].getY() * Variables.MoveSize) + Variables.players[Variables.MyIndex].getOffsetY());

                float nameX = PlayerX - ((int)width / 2) + 24;
                float nameY = PlayerY - 28;

                Variables.DrawHP[i].setMapNpcNum(Variables.MyIndex);
                Variables.DrawHP[i].setX((int)nameX);
                Variables.DrawHP[i].setY((int)nameY);
                Variables.DrawHP[i].setTimer(20);
                break;
            }
        }
    }
    public static void HandleOpenPlayerMenu(Object object) {
        SendOpenPlayerMenu sendOpenPlayerMenu = (SendOpenPlayerMenu) object;

        int index = sendOpenPlayerMenu.index;
        int targetIndex = sendOpenPlayerMenu.targetIndex;

        if (index == targetIndex) { return; }

        Variables.target = targetIndex;
    }
    public static void HandlePartyInvite(Object object) {
        SendPartyInvite sendPartyInvite = (SendPartyInvite) object;

        int target = sendPartyInvite.target;
        int index = sendPartyInvite.index;

        if (target == Variables.MyIndex) {
            Variables.PartyLeader = index;
            Variables.PartyInvite = true;
        }
    }
    public static void HandlePartyInfo(Object object) {
        PartyInfo partyInfo = (PartyInfo) object;

        int index = partyInfo.index;
        Variables.players[index].setParty(partyInfo.partyNum);
        Variables.MyParty = partyInfo.party;

        if (partyInfo.partyNum == 0) {
            if (Variables.inMenu) {
                Variables.inMenu = false;
            }
        }
    }
}
