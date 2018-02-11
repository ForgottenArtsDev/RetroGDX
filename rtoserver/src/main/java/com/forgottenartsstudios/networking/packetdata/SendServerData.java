package com.forgottenartsstudios.networking.packetdata;

import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.forgottenartsstudios.data.AccountData;
import com.forgottenartsstudios.data.General;
import com.forgottenartsstudios.data.Inventory_Struct;
import com.forgottenartsstudios.data.MapItem;
import com.forgottenartsstudios.data.MapSpell;
import com.forgottenartsstudios.data.Party;
import com.forgottenartsstudios.data.Player;
import com.forgottenartsstudios.data.Spell_Inv_Struct;
import com.forgottenartsstudios.helpers.ServerVars;
import com.forgottenartsstudios.networking.packets.*;

import static com.forgottenartsstudios.server.RTOServer.server;

/**
 * Created by forgo on 10/10/2017.
 */

public class SendServerData {
    public static void SendSpawnNpc(int mapNum, int mapNpcNum, int isMoving) {
        SendNPCSpawn sndNPCSpawn = new SendNPCSpawn();

        if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum() <= 0) {
            sndNPCSpawn.name = "";
            sndNPCSpawn.mapNPCNum = mapNpcNum;
            sndNPCSpawn.npcNum = 0;
            sndNPCSpawn.hp = 0;
            sndNPCSpawn.maxHP = 0;
            sndNPCSpawn.x = 0;
            sndNPCSpawn.y = 0;
            sndNPCSpawn.dir = 0;
            sndNPCSpawn.sprite = 0;
            sndNPCSpawn.isMoving = 0;
            sndNPCSpawn.level = 0;

            for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                if (ServerVars.Players != null) {
                    if (ServerVars.Players[i] != null) {
                        if (ServerVars.Players[i].getMap() == mapNum) {
                            server.sendToTCP(ServerVars.Accounts[i].getCID(), sndNPCSpawn);
                        }
                    }
                }
            }
        } else {
            sndNPCSpawn.name = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].Name;
            sndNPCSpawn.mapNPCNum = mapNpcNum;
            sndNPCSpawn.npcNum = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum();
            sndNPCSpawn.hp = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getHP();
            sndNPCSpawn.maxHP = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getMaxHP();
            sndNPCSpawn.x = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX();
            sndNPCSpawn.y = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY();
            sndNPCSpawn.dir = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getDir();
            sndNPCSpawn.sprite = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getSprite();
            sndNPCSpawn.level = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getLevel();
            sndNPCSpawn.isMoving = isMoving;

            for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                if (ServerVars.Players != null) {
                    if (ServerVars.Players[i] != null) {
                        if (ServerVars.Players[i].getMap() == mapNum) {
                            server.sendToTCP(ServerVars.Accounts[i].getCID(), sndNPCSpawn);
                        }
                    }
                }
            }
        }
    }
    public static void SendPlayerData(int index, int indexTo) {
        PlayerData plData = new PlayerData();
        plData.playerData = new Player();

        plData.index = index;
        plData.playerData.setName(ServerVars.Players[index].getName());

        plData.playerData.setJob(ServerVars.Players[index].getJob());
        plData.playerData.setLevel(ServerVars.Players[index].getLevel());
        plData.playerData.setSprite(ServerVars.Players[index].getSprite());
        plData.playerData.setPoints(ServerVars.Players[index].getPoints());

        plData.playerData.setHP(ServerVars.Players[index].getHP());
        plData.playerData.setMP(ServerVars.Players[index].getMP());
        plData.playerData.setMaxHP(ServerVars.Players[index].getMaxHP());
        plData.playerData.setMaxMP(ServerVars.Players[index].getMaxMP());

        plData.playerData.setEXP(ServerVars.Players[index].getEXP());
        plData.playerData.setNextLVL(ServerVars.Players[index].getNextLVL());

        plData.playerData.setMap(ServerVars.Players[index].getMap());
        plData.playerData.setX(ServerVars.Players[index].getX());
        plData.playerData.setY(ServerVars.Players[index].getY());
        plData.playerData.setDir(ServerVars.Players[index].getDir());

        plData.playerData.setSTR(ServerVars.Players[index].getSTR());
        plData.playerData.setDEF(ServerVars.Players[index].getDEF());
        plData.playerData.setVIT(ServerVars.Players[index].getVIT());
        plData.playerData.setAGI(ServerVars.Players[index].getAGI());
        plData.playerData.setMAG(ServerVars.Players[index].getMAG());

        plData.playerData.setWeapon(ServerVars.Players[index].getWeapon());
        plData.playerData.setOffhand(ServerVars.Players[index].getOffhand());
        plData.playerData.setArmor(ServerVars.Players[index].getArmor());
        plData.playerData.setHelmet(ServerVars.Players[index].getHelmet());
        plData.playerData.setAcc1(ServerVars.Players[index].getAcc1());
        plData.playerData.setAcc2(ServerVars.Players[index].getAcc2());

        plData.playerData.setHotKeyQ(ServerVars.Players[index].getHotKeyQ());
        plData.playerData.setHotKeyE(ServerVars.Players[index].getHotKeyE());
        plData.playerData.setHotKeyR(ServerVars.Players[index].getHotKeyR());
        plData.playerData.setHotKeyF(ServerVars.Players[index].getHotKeyF());

        plData.playerData.setTarget(ServerVars.Players[index].getTarget());
        plData.playerData.setTargetType(ServerVars.Players[index].getTargetType());

        plData.playerData.setParty(ServerVars.Players[index].getParty());

        plData.playerData.setDeathTimer(ServerVars.Players[index].getDeathTimer());
        plData.playerData.setTempSprite(ServerVars.Players[index].getTempSprite());

        if (plData == null) {
            System.out.println("plData = null");
        }
        if (plData.playerData == null) {
            System.out.println("plData.playerData = null");
        }

        server.sendToTCP(ServerVars.Accounts[indexTo].getCID(), plData);

        SendInvData(index, index);
        SendSpellData(index, index);
        if (ServerVars.Players[index].getParty() > 0) {
            int pNum = ServerVars.Players[index].getParty();
            for (int i = 1; i <= 3; i++) {
                if (ServerVars.Parties[pNum].members[i] > 0) {
                    SendInvData(index, ServerVars.Parties[pNum].members[i]);
                    SendSpellData(index, ServerVars.Parties[pNum].members[i]);
                }
            }
        }
    }
    public static void SendAccountRegistered(Connection connection, boolean result) {
        AccountRegistered ar = new AccountRegistered();
        ar.result = result;
        server.sendToTCP(connection.getID(), ar);
    }
    public static void SendLogin(int index) {
        SendLogin sndLogin = new SendLogin();
        sndLogin.Index = index;
        //sndLogin.accountData = ServerVars.Accounts[index];
        sndLogin.accountData = new AccountData();
        sndLogin.char1 = new Player();
        sndLogin.char2 = new Player();
        sndLogin.char3 = new Player();
        sndLogin.accountData.setID(ServerVars.Accounts[index].getID());
        sndLogin.accountData.setPW(ServerVars.Accounts[index].getPW());
        sndLogin.accountData.setCID(ServerVars.Accounts[index].getCID());

        // CHAR 1
        sndLogin.char1.setName(ServerVars.Accounts[index].chars[1].getName());

        sndLogin.char1.setJob(ServerVars.Accounts[index].chars[1].getJob());
        sndLogin.char1.setSprite(ServerVars.Accounts[index].chars[1].getSprite());
        sndLogin.char1.setLevel(ServerVars.Accounts[index].chars[1].getLevel());
        sndLogin.char1.setPoints(ServerVars.Accounts[index].chars[1].getPoints());
        sndLogin.char1.setHP(ServerVars.Accounts[index].chars[1].getHP());
        sndLogin.char1.setMP(ServerVars.Accounts[index].chars[1].getMP());
        sndLogin.char1.setMaxHP(ServerVars.Accounts[index].chars[1].getMaxHP());
        sndLogin.char1.setMaxMP(ServerVars.Accounts[index].chars[1].getMaxMP());

        sndLogin.char1.setEXP(ServerVars.Accounts[index].chars[1].getEXP());
        sndLogin.char1.setNextLVL(ServerVars.Accounts[index].chars[1].getNextLVL());

        sndLogin.char1.setMap(ServerVars.Accounts[index].chars[1].getMap());
        sndLogin.char1.setX(ServerVars.Accounts[index].chars[1].getX());
        sndLogin.char1.setY(ServerVars.Accounts[index].chars[1].getY());
        sndLogin.char1.setDir(ServerVars.Accounts[index].chars[1].getDir());

        sndLogin.char1.setSTR(ServerVars.Accounts[index].chars[1].getSTR());
        sndLogin.char1.setDEF(ServerVars.Accounts[index].chars[1].getDEF());
        sndLogin.char1.setVIT(ServerVars.Accounts[index].chars[1].getVIT());
        sndLogin.char1.setAGI(ServerVars.Accounts[index].chars[1].getAGI());
        sndLogin.char1.setMAG(ServerVars.Accounts[index].chars[1].getMAG());

        sndLogin.char1.setWeapon(ServerVars.Accounts[index].chars[1].getWeapon());
        sndLogin.char1.setOffhand(ServerVars.Accounts[index].chars[1].getOffhand());
        sndLogin.char1.setArmor(ServerVars.Accounts[index].chars[1].getArmor());
        sndLogin.char1.setHelmet(ServerVars.Accounts[index].chars[1].getHelmet());
        sndLogin.char1.setAcc1(ServerVars.Accounts[index].chars[1].getAcc1());
        sndLogin.char1.setAcc2(ServerVars.Accounts[index].chars[1].getAcc2());

        sndLogin.char1.inventory = new Inventory_Struct[60 + 1];
        sndLogin.char1.spells = new Spell_Inv_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            sndLogin.char1.inventory[i] = new Inventory_Struct();
            sndLogin.char1.inventory[i].setItemNum(ServerVars.Accounts[index].chars[1].inventory[i].getItemNum());
            sndLogin.char1.inventory[i].setItemVal(ServerVars.Accounts[index].chars[1].inventory[i].getItemVal());

            sndLogin.char1.spells[i] = new Spell_Inv_Struct();
            sndLogin.char1.spells[i].setSpellNum(ServerVars.Accounts[index].chars[1].spells[i].getSpellNum());
        }

        // CHAR 2
        sndLogin.char2.setName(ServerVars.Accounts[index].chars[2].getName());

        sndLogin.char2.setJob(ServerVars.Accounts[index].chars[2].getJob());
        sndLogin.char2.setSprite(ServerVars.Accounts[index].chars[2].getSprite());
        sndLogin.char2.setLevel(ServerVars.Accounts[index].chars[2].getLevel());
        sndLogin.char2.setPoints(ServerVars.Accounts[index].chars[2].getPoints());
        sndLogin.char2.setHP(ServerVars.Accounts[index].chars[2].getHP());
        sndLogin.char2.setMP(ServerVars.Accounts[index].chars[2].getMP());
        sndLogin.char2.setMaxHP(ServerVars.Accounts[index].chars[2].getMaxHP());
        sndLogin.char2.setMaxMP(ServerVars.Accounts[index].chars[2].getMaxMP());

        sndLogin.char2.setEXP(ServerVars.Accounts[index].chars[2].getEXP());
        sndLogin.char2.setNextLVL(ServerVars.Accounts[index].chars[2].getNextLVL());

        sndLogin.char2.setMap(ServerVars.Accounts[index].chars[2].getMap());
        sndLogin.char2.setX(ServerVars.Accounts[index].chars[2].getX());
        sndLogin.char2.setY(ServerVars.Accounts[index].chars[2].getY());
        sndLogin.char2.setDir(ServerVars.Accounts[index].chars[2].getDir());

        sndLogin.char2.setSTR(ServerVars.Accounts[index].chars[2].getSTR());
        sndLogin.char2.setDEF(ServerVars.Accounts[index].chars[2].getDEF());
        sndLogin.char2.setVIT(ServerVars.Accounts[index].chars[2].getVIT());
        sndLogin.char2.setAGI(ServerVars.Accounts[index].chars[2].getAGI());
        sndLogin.char2.setMAG(ServerVars.Accounts[index].chars[2].getMAG());

        sndLogin.char2.setWeapon(ServerVars.Accounts[index].chars[2].getWeapon());
        sndLogin.char2.setOffhand(ServerVars.Accounts[index].chars[2].getOffhand());
        sndLogin.char2.setArmor(ServerVars.Accounts[index].chars[2].getArmor());
        sndLogin.char2.setHelmet(ServerVars.Accounts[index].chars[2].getHelmet());
        sndLogin.char2.setAcc1(ServerVars.Accounts[index].chars[2].getAcc1());
        sndLogin.char2.setAcc2(ServerVars.Accounts[index].chars[2].getAcc2());

        sndLogin.char2.inventory = new Inventory_Struct[60 + 1];
        sndLogin.char2.spells = new Spell_Inv_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            sndLogin.char2.inventory[i] = new Inventory_Struct();
            sndLogin.char2.inventory[i].setItemNum(ServerVars.Accounts[index].chars[2].inventory[i].getItemNum());
            sndLogin.char2.inventory[i].setItemVal(ServerVars.Accounts[index].chars[2].inventory[i].getItemVal());

            sndLogin.char2.spells[i] = new Spell_Inv_Struct();
            sndLogin.char2.spells[i].setSpellNum(ServerVars.Accounts[index].chars[2].spells[i].getSpellNum());
        }

        // CHAR 3
        sndLogin.char3.setName(ServerVars.Accounts[index].chars[3].getName());

        sndLogin.char3.setJob(ServerVars.Accounts[index].chars[3].getJob());
        sndLogin.char3.setSprite(ServerVars.Accounts[index].chars[3].getSprite());
        sndLogin.char3.setLevel(ServerVars.Accounts[index].chars[3].getLevel());
        sndLogin.char3.setPoints(ServerVars.Accounts[index].chars[3].getPoints());
        sndLogin.char3.setHP(ServerVars.Accounts[index].chars[3].getHP());
        sndLogin.char3.setMP(ServerVars.Accounts[index].chars[3].getMP());
        sndLogin.char3.setMaxHP(ServerVars.Accounts[index].chars[3].getMaxHP());
        sndLogin.char3.setMaxMP(ServerVars.Accounts[index].chars[3].getMaxMP());

        sndLogin.char3.setEXP(ServerVars.Accounts[index].chars[3].getEXP());
        sndLogin.char3.setNextLVL(ServerVars.Accounts[index].chars[3].getNextLVL());

        sndLogin.char3.setMap(ServerVars.Accounts[index].chars[3].getMap());
        sndLogin.char3.setX(ServerVars.Accounts[index].chars[3].getX());
        sndLogin.char3.setY(ServerVars.Accounts[index].chars[3].getY());
        sndLogin.char3.setDir(ServerVars.Accounts[index].chars[3].getDir());

        sndLogin.char3.setSTR(ServerVars.Accounts[index].chars[3].getSTR());
        sndLogin.char3.setDEF(ServerVars.Accounts[index].chars[3].getDEF());
        sndLogin.char3.setVIT(ServerVars.Accounts[index].chars[3].getVIT());
        sndLogin.char3.setAGI(ServerVars.Accounts[index].chars[3].getAGI());
        sndLogin.char3.setMAG(ServerVars.Accounts[index].chars[3].getMAG());

        sndLogin.char3.setWeapon(ServerVars.Accounts[index].chars[3].getWeapon());
        sndLogin.char3.setOffhand(ServerVars.Accounts[index].chars[3].getOffhand());
        sndLogin.char3.setArmor(ServerVars.Accounts[index].chars[3].getArmor());
        sndLogin.char3.setHelmet(ServerVars.Accounts[index].chars[3].getHelmet());
        sndLogin.char3.setAcc1(ServerVars.Accounts[index].chars[3].getAcc1());
        sndLogin.char3.setAcc2(ServerVars.Accounts[index].chars[3].getAcc2());

        sndLogin.char3.inventory = new Inventory_Struct[60 + 1];
        sndLogin.char3.spells = new Spell_Inv_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            sndLogin.char3.inventory[i] = new Inventory_Struct();
            sndLogin.char3.inventory[i].setItemNum(ServerVars.Accounts[index].chars[3].inventory[i].getItemNum());
            sndLogin.char3.inventory[i].setItemVal(ServerVars.Accounts[index].chars[3].inventory[i].getItemVal());

            sndLogin.char3.spells[i] = new Spell_Inv_Struct();
            sndLogin.char3.spells[i].setSpellNum(ServerVars.Accounts[index].chars[3].spells[i].getSpellNum());
        }

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sndLogin);
    }
    public static void SendMovePlayer(int Index, int Map, int X, int Y, int Dir, boolean canMove) {
        MovePlayer sPlayer;

        if (Dir == ServerVars.DIR_UP) {
            sPlayer = new MovePlayer();

            sPlayer.Index = Index;
            sPlayer.Map = Map;
            sPlayer.X = X;
            sPlayer.canMove = canMove;
            if (canMove) {
                sPlayer.Y = Y - 1;
            } else {
                sPlayer.Y = Y;
            }
            sPlayer.Dir = Dir;

            for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                if (ServerVars.Players[i] != null) {
                    if (ServerVars.Players[i].getMap() == Map) {
                        sPlayer = new MovePlayer();

                        sPlayer.Index = Index;
                        sPlayer.canMove = canMove;
                        sPlayer.Map = Map;
                        sPlayer.X = X;
                        if (canMove) {
                            sPlayer.Y = Y - 1;
                        } else {
                            sPlayer.Y = Y;
                        }
                        sPlayer.Dir = Dir;

                        server.sendToTCP(ServerVars.Accounts[i].getCID(), sPlayer);
                    }
                }
            }
        } else if (Dir == ServerVars.DIR_DOWN) {
            sPlayer = new MovePlayer();

            sPlayer.Index = Index;
            sPlayer.Map = Map;
            sPlayer.X = X;
            sPlayer.canMove = canMove;
            if (canMove) {
                sPlayer.Y = Y + 1;
            } else {
                sPlayer.Y = Y;
            }
            sPlayer.Dir = Dir;

            for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                if (ServerVars.Players[i] != null) {
                    if (ServerVars.Players[i].getMap() == Map) {
                        sPlayer = new MovePlayer();

                        sPlayer.Index = Index;
                        sPlayer.canMove = canMove;
                        sPlayer.Map = Map;
                        sPlayer.X = X;
                        if (canMove) {
                            sPlayer.Y = Y + 1;
                        } else {
                            sPlayer.Y = Y;
                        }
                        sPlayer.Dir = Dir;

                        server.sendToTCP(ServerVars.Accounts[i].getCID(), sPlayer);
                    }
                }
            }
        } else if (Dir == ServerVars.DIR_LEFT) {
            sPlayer = new MovePlayer();

            sPlayer.Index = Index;
            sPlayer.Map = Map;
            sPlayer.canMove = canMove;
            if (canMove) {
                sPlayer.X = X - 1;
            } else {
                sPlayer.X = X;
            }
            sPlayer.Y = Y;
            sPlayer.Dir = Dir;

            for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                if (ServerVars.Players[i] != null) {
                    if (ServerVars.Players[i].getMap() == Map) {
                        sPlayer = new MovePlayer();

                        sPlayer.Index = Index;
                        sPlayer.canMove = canMove;
                        sPlayer.Map = Map;
                        if (canMove) {
                            sPlayer.X = X - 1;
                        } else {
                            sPlayer.X = X;
                        }
                        sPlayer.Y = Y;
                        sPlayer.Dir = Dir;

                        server.sendToTCP(ServerVars.Accounts[i].getCID(), sPlayer);
                    }
                }
            }
        } else if (Dir == ServerVars.DIR_RIGHT) {
            sPlayer = new MovePlayer();

            sPlayer.Index = Index;
            sPlayer.Map = Map;
            sPlayer.canMove = canMove;
            if (canMove) {
                sPlayer.X = X + 1;
            } else {
                sPlayer.X = X;
            }
            sPlayer.Y = Y;
            sPlayer.Dir = Dir;

            for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                if (ServerVars.Players[i] != null) {
                    if (ServerVars.Players[i].getMap() == Map) {
                        sPlayer = new MovePlayer();

                        sPlayer.Index = Index;
                        sPlayer.canMove = canMove;
                        sPlayer.Map = Map;
                        if (canMove) {
                            sPlayer.X = X + 1;
                        } else {
                            sPlayer.X = X;
                        }
                        sPlayer.Y = Y;
                        sPlayer.Dir = Dir;

                        server.sendToTCP(ServerVars.Accounts[i].getCID(), sPlayer);
                    }
                }
            }
        }
    }
    public static void SendKeepAliveCheck(int Index) {
        KeepAliveCheck keepAliveCheck = new KeepAliveCheck();
        keepAliveCheck.index = Index;
        keepAliveCheck.keepAlive = false;

        server.sendToTCP(ServerVars.Accounts[Index].getCID(), keepAliveCheck);
    }
    public static void SendDisconnectPlayer(int Index) {
        DisconnectPlayer dPlayer = new DisconnectPlayer();
        dPlayer.index = Index;

        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            if (ServerVars.Accounts[i].getCID() > 0) {
                server.sendToTCP(ServerVars.Accounts[i].getCID(), dPlayer);
            }
        }
    }
    public static void SendNPCDir(int mapNum, int mapNPCNum) {
        SendNPCDir sndNPCDir = new SendNPCDir();

        sndNPCDir.mapNPCNum = mapNPCNum;
        sndNPCDir.dir = ServerVars.MapNPCs[mapNum].Npc[mapNPCNum].getDir();

        for (int i = 0; i <= 100; i++) {
            if (ServerVars.Players[i] != null) {
                if (ServerVars.Players[i].getMap() == mapNum) {
                    server.sendToTCP(ServerVars.Accounts[i].getCID(), sndNPCDir);
                }
            }
        }
    }
    public static void SendNpcMove(int mapNum, int mapNpcNum, int isMoving) {
        SendNPCMove sndNPCMove = new SendNPCMove();

        sndNPCMove.mapNPCNum = mapNpcNum;
        sndNPCMove.x = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX();
        sndNPCMove.y = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY();
        sndNPCMove.dir = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getDir();
        sndNPCMove.isMoving = isMoving;

        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            if (ServerVars.Players[i] != null) {
                if (ServerVars.Players[i].getMap() == mapNum) {
                    server.sendToTCP(ServerVars.Accounts[i].getCID(), sndNPCMove);
                }
            }
        }
    }
    public static void SendVital(int index) {
        SendVital sVital = new SendVital();

        sVital.lvl = ServerVars.Players[index].getLevel();

        sVital.hp = ServerVars.Players[index].getHP();
        sVital.maxHP = ServerVars.Players[index].getMaxHP();
        sVital.mp = ServerVars.Players[index].getMP();
        sVital.maxMP = ServerVars.Players[index].getMaxMP();

        sVital.exp = ServerVars.Players[index].getEXP();
        sVital.nextLvl = ServerVars.Players[index].getNextLVL();
        sVital.points = ServerVars.Players[index].getPoints();

        sVital.str = ServerVars.Players[index].getSTR();
        sVital.def = ServerVars.Players[index].getDEF();
        sVital.vit = ServerVars.Players[index].getVIT();
        sVital.agi = ServerVars.Players[index].getAGI();
        sVital.mag = ServerVars.Players[index].getMAG();

        sVital.weapon = ServerVars.Players[index].getWeapon();
        sVital.offhand = ServerVars.Players[index].getOffhand();
        sVital.armor = ServerVars.Players[index].getArmor();
        sVital.helmet = ServerVars.Players[index].getHelmet();
        sVital.acc1 = ServerVars.Players[index].getAcc1();
        sVital.acc2 = ServerVars.Players[index].getAcc2();

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sVital);
    }
    public static void SendPlayerWarp(int index, int mapNum, int x, int y) {
        SendPlayerWarp sPWarp = new SendPlayerWarp();

        sPWarp.index = index;
        sPWarp.mapNum = mapNum;
        sPWarp.x = x;
        sPWarp.y = y;

        int oldMap = ServerVars.Players[index].getMap();

        ServerVars.Players[index].setMap(mapNum);
        ServerVars.Players[index].setX(x);
        ServerVars.Players[index].setY(y);

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sPWarp);

        if (mapNum != oldMap) {
            for (int i = 1; i <= ServerVars.MaxMapNPCs; i++) {
                SendSpawnNpc(mapNum, i, 0);
            }
            SendMapItems(mapNum);
        }

        SendServerData.SendMapItems(mapNum);
    }
    public static void SendItems(int index, int itemNum) {
        SendItems sItems = new SendItems();

        sItems.index = index;
        sItems.itemNum = itemNum;
        sItems.item = ServerVars.Items[itemNum];

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sItems);
    }
    public static void SendSpells(int index, int spellNum) {
        SendSpells sSpells = new SendSpells();

        sSpells.index = index;
        sSpells.spellNum = spellNum;
        sSpells.spell = ServerVars.Spells[spellNum];

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sSpells);
    }
    public static void SendOpenShop(int index, int shopNum) {
        SendShop sndShop = new SendShop();
        sndShop.shopNum = shopNum;
        sndShop.name = ServerVars.Shops[shopNum].Name;
        sndShop.welcomeMsg = ServerVars.Shops[shopNum].welcomeMsg;
        sndShop.goodbyeMsg = ServerVars.Shops[shopNum].goodbyeMsg;
        sndShop.salesTax = ServerVars.Shops[shopNum].salesTax;

        for (int i = 1; i <= 20; i++) {
            sndShop.itemNums[i] = ServerVars.Shops[shopNum].itemNum[i];
            sndShop.itemVals[i] = ServerVars.Shops[shopNum].itemVal[i];
        }

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sndShop);
    }
    public static void SendInvData(int Index, int toIndex) {
        SendInventory sInv = new SendInventory();

        sInv.index = Index;

        for (int i = 1; i <= 60; i++) {
            sInv.invData[i] = ServerVars.Players[Index].inventory[i];
        }

        server.sendToTCP(ServerVars.Accounts[toIndex].getCID(), sInv);
    }
    public static void SendSpellData(int Index, int toIndex) {
        SendSpellInv sSpells = new SendSpellInv();

        sSpells.index = Index;

        for (int i = 1; i <= 60; i++) {
            sSpells.splData[i] = ServerVars.Players[Index].spells[i];
        }

        server.sendToTCP(ServerVars.Accounts[toIndex].getCID(), sSpells);
    }
    public static void SendBoughtItemMsg(int Index) {
        ItemBoughtMsg sBoughtMsg = new ItemBoughtMsg();

        server.sendToTCP(ServerVars.Accounts[Index].getCID(), sBoughtMsg);
    }
    public static void SendKillNPC(int index, int mapNpcNum, boolean giveXP) {
        // Add the XP and check if they leveled up
        if (giveXP) {
            int mapNum = ServerVars.Players[index].getMap();
            int pNum = ServerVars.Players[index].getParty();

            int playerLvl = ServerVars.Players[index].getLevel();
            int npcLvl = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getLevel();

            int lvlDiff = playerLvl - npcLvl;

            double XP = 0;
            double EXP = 0;

            if (pNum > 0) {
                int split = 0;
                for (int i = 1; i <= 3; i++) {
                    if (ServerVars.Parties[ServerVars.Players[index].getParty()].members[i] > 0) {
                        split = split + 1;
                    }
                }
                if (split == 2) {
                    EXP = (double)ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].Exp;
                    EXP = EXP * 1.2;
                    XP = EXP / 2;
                    if (lvlDiff <= 5) {
                        XP = (int)(XP * (10.0f / 100.0f));
                    }
                    if (lvlDiff < 1 && lvlDiff > -5) {
                        XP = (int)(XP * (50.0f / 100.0f));
                    }
                    if (lvlDiff > -1 && lvlDiff < 5) {
                        XP = (int)(XP * (125.0f / 100.0f));
                    }
                    if (lvlDiff >= -5) {
                        XP = (int)(XP * (150.0f / 100.0f));
                    }
                } else if (split == 3) {
                    EXP = (double)ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].Exp;
                    EXP = EXP * 1.5;
                    XP = EXP / 3;
                    if (lvlDiff <= 5) {
                        XP = (int)(XP * (10.0f / 100.0f));
                    }
                    if (lvlDiff < 1 && lvlDiff > -5) {
                        XP = (int)(XP * (50.0f / 100.0f));
                    }
                    if (lvlDiff > -1 && lvlDiff < 5) {
                        XP = (int)(XP * (125.0f / 100.0f));
                    }
                    if (lvlDiff >= -5) {
                        XP = (int)(XP * (150.0f / 100.0f));
                    }
                }
            } else {
                XP = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].Exp;
                if (lvlDiff <= 5) {
                    XP = (int)(XP * (10.0f / 100.0f));
                }
                if (lvlDiff < 1 && lvlDiff > -5) {
                    XP = (int)(XP * (50.0f / 100.0f));
                }
                if (lvlDiff > -1 && lvlDiff < 5) {
                    XP = (int)(XP * (125.0f / 100.0f));
                }
                if (lvlDiff >= -5) {
                    XP = (int)(XP * (150.0f / 100.0f));
                }
            }

            if (pNum > 0) {
                for (int i = 1; i <= 3; i++) {
                    if (ServerVars.Parties[pNum].members[i] > 0) {
                        SendServerData.SendNPCXP(ServerVars.Parties[pNum].members[i], (int) XP);
                    }
                }
            } else {
                SendServerData.SendNPCXP(index, (int) XP);
            }
            if (XP > 0) {
                if (pNum > 0) {
                    for (int i = 1; i <= 3; i++) {
                        if (ServerVars.Parties[pNum].members[i] > 0) {
                            ServerVars.Players[ServerVars.Parties[pNum].members[i]].setEXP(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getEXP() + (int) XP);
                            if (ServerVars.Players[ServerVars.Parties[pNum].members[i]].getEXP() >= ServerVars.Players[ServerVars.Parties[pNum].members[i]].getNextLVL()) {
                                int newXP = ServerVars.Players[ServerVars.Parties[pNum].members[i]].getEXP() - ServerVars.Players[ServerVars.Parties[pNum].members[i]].getNextLVL();
                                ServerVars.Players[ServerVars.Parties[pNum].members[i]].setEXP(newXP);
                                ServerVars.Players[ServerVars.Parties[pNum].members[i]].setLevel(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getLevel() + 1);
                                ServerVars.Players[ServerVars.Parties[pNum].members[i]].setNextLVL(General.getNextLevel(ServerVars.Parties[pNum].members[i]));
                                switch (ServerVars.Players[ServerVars.Parties[pNum].members[i]].getJob()) {
                                    case ServerVars.JOB_WARRIOR:
                                        ServerVars.Players[ServerVars.Parties[pNum].members[i]].setSTR(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getSTR() + 2);
                                        ServerVars.Players[ServerVars.Parties[pNum].members[i]].setVIT(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getVIT() + 1);
                                        break;
                                    case ServerVars.JOB_MAGE:
                                        ServerVars.Players[ServerVars.Parties[pNum].members[i]].setMAG(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getMAG() + 2);
                                        ServerVars.Players[ServerVars.Parties[pNum].members[i]].setSTR(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getSTR() + 1);
                                        break;
                                    case ServerVars.JOB_CLERIC:
                                        ServerVars.Players[ServerVars.Parties[pNum].members[i]].setMAG(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getMAG() + 2);
                                        ServerVars.Players[ServerVars.Parties[pNum].members[i]].setVIT(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getVIT() + 1);
                                        break;
                                    case ServerVars.JOB_RANGER:
                                        ServerVars.Players[ServerVars.Parties[pNum].members[i]].setAGI(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getAGI() + 2);
                                        ServerVars.Players[ServerVars.Parties[pNum].members[i]].setSTR(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getSTR() + 1);
                                        break;
                                    case ServerVars.JOB_ROGUE:
                                        ServerVars.Players[ServerVars.Parties[pNum].members[i]].setAGI(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getAGI() + 2);
                                        ServerVars.Players[ServerVars.Parties[pNum].members[i]].setVIT(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getVIT() + 1);
                                        break;
                                }
                                ServerVars.Players[ServerVars.Parties[pNum].members[i]].setPoints(ServerVars.Players[ServerVars.Parties[pNum].members[i]].getPoints() + 2);
                                int pSTR = ServerVars.Players[ServerVars.Parties[pNum].members[i]].getSTR();
                                int pDEF = ServerVars.Players[ServerVars.Parties[pNum].members[i]].getDEF();
                                int pVIT = ServerVars.Players[ServerVars.Parties[pNum].members[i]].getVIT();
                                int pAGI = ServerVars.Players[ServerVars.Parties[pNum].members[i]].getAGI();
                                int pMAG = ServerVars.Players[ServerVars.Parties[pNum].members[i]].getMAG();

                                int itemNum;
                                if (ServerVars.Players[ServerVars.Parties[pNum].members[i]].getWeapon() > 0) {
                                    itemNum = ServerVars.Players[ServerVars.Parties[pNum].members[i]].inventory[ServerVars.Players[ServerVars.Parties[pNum].members[i]].getWeapon()].getItemNum();
                                    pSTR = pSTR + ServerVars.Items[itemNum].STR;
                                    pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                                    pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                                    pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                                    pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                                }
                                if (ServerVars.Players[ServerVars.Parties[pNum].members[i]].getOffhand() > 0) {
                                    itemNum = ServerVars.Players[ServerVars.Parties[pNum].members[i]].inventory[ServerVars.Players[ServerVars.Parties[pNum].members[i]].getOffhand()].getItemNum();
                                    pSTR = pSTR + ServerVars.Items[itemNum].STR;
                                    pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                                    pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                                    pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                                    pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                                }
                                if (ServerVars.Players[ServerVars.Parties[pNum].members[i]].getArmor() > 0) {
                                    itemNum = ServerVars.Players[ServerVars.Parties[pNum].members[i]].inventory[ServerVars.Players[ServerVars.Parties[pNum].members[i]].getArmor()].getItemNum();
                                    pSTR = pSTR + ServerVars.Items[itemNum].STR;
                                    pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                                    pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                                    pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                                    pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                                }
                                if (ServerVars.Players[ServerVars.Parties[pNum].members[i]].getHelmet() > 0) {
                                    itemNum = ServerVars.Players[ServerVars.Parties[pNum].members[i]].inventory[ServerVars.Players[ServerVars.Parties[pNum].members[i]].getHelmet()].getItemNum();
                                    pSTR = pSTR + ServerVars.Items[itemNum].STR;
                                    pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                                    pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                                    pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                                    pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                                }
                                if (ServerVars.Players[index].getAcc1() > 0) {
                                    itemNum = ServerVars.Players[ServerVars.Parties[pNum].members[i]].inventory[ServerVars.Players[ServerVars.Parties[pNum].members[i]].getAcc1()].getItemNum();
                                    pSTR = pSTR + ServerVars.Items[itemNum].STR;
                                    pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                                    pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                                    pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                                    pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                                }
                                if (ServerVars.Players[index].getAcc2() > 0) {
                                    itemNum = ServerVars.Players[ServerVars.Parties[pNum].members[i]].inventory[ServerVars.Players[ServerVars.Parties[pNum].members[i]].getAcc2()].getItemNum();
                                    pSTR = pSTR + ServerVars.Items[itemNum].STR;
                                    pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                                    pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                                    pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                                    pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                                }

                                ServerVars.Players[ServerVars.Parties[pNum].members[i]].setMaxHP((pVIT * 2) * (pSTR / 2));
                                ServerVars.Players[ServerVars.Parties[pNum].members[i]].setMaxMP((pMAG * 2) * (pDEF / 2));

                                ServerVars.Players[ServerVars.Parties[pNum].members[i]].setTarget(0);
                                ServerVars.Players[ServerVars.Parties[pNum].members[i]].setTargetType(0);

                                for (int a = 1; a <= ServerVars.MaxPlayers; a++) {
                                    if (ServerVars.Players[a] != null) {
                                        if (ServerVars.Players[a].getMap() == ServerVars.Players[ServerVars.Parties[pNum].members[i]].getMap()) {
                                            SendServerData.SendSystemMessage(ServerVars.Parties[pNum].members[i], a, "Level Up!", Color.GREEN);
                                        }
                                    }
                                }
                            }
                            SendServerData.SendVital(ServerVars.Parties[pNum].members[i]);
                        }
                    }
                } else {
                    ServerVars.Players[index].setEXP(ServerVars.Players[index].getEXP() + (int) XP);
                    if (ServerVars.Players[index].getEXP() >= ServerVars.Players[index].getNextLVL()) {
                        int newXP = ServerVars.Players[index].getEXP() - ServerVars.Players[index].getNextLVL();
                        ServerVars.Players[index].setEXP(newXP);
                        ServerVars.Players[index].setLevel(ServerVars.Players[index].getLevel() + 1);
                        ServerVars.Players[index].setNextLVL(General.getNextLevel(index));
                        switch (ServerVars.Players[index].getJob()) {
                            case ServerVars.JOB_WARRIOR:
                                ServerVars.Players[index].setSTR(ServerVars.Players[index].getSTR() + 2);
                                ServerVars.Players[index].setVIT(ServerVars.Players[index].getVIT() + 1);
                                break;
                            case ServerVars.JOB_MAGE:
                                ServerVars.Players[index].setMAG(ServerVars.Players[index].getMAG() + 2);
                                ServerVars.Players[index].setSTR(ServerVars.Players[index].getSTR() + 1);
                                break;
                            case ServerVars.JOB_CLERIC:
                                ServerVars.Players[index].setMAG(ServerVars.Players[index].getMAG() + 2);
                                ServerVars.Players[index].setVIT(ServerVars.Players[index].getVIT() + 1);
                                break;
                            case ServerVars.JOB_RANGER:
                                ServerVars.Players[index].setAGI(ServerVars.Players[index].getAGI() + 2);
                                ServerVars.Players[index].setSTR(ServerVars.Players[index].getSTR() + 1);
                                break;
                            case ServerVars.JOB_ROGUE:
                                ServerVars.Players[index].setAGI(ServerVars.Players[index].getAGI() + 2);
                                ServerVars.Players[index].setVIT(ServerVars.Players[index].getVIT() + 1);
                                break;
                        }
                        ServerVars.Players[index].setPoints(ServerVars.Players[index].getPoints() + 2);
                        int pSTR = ServerVars.Players[index].getSTR();
                        int pDEF = ServerVars.Players[index].getDEF();
                        int pVIT = ServerVars.Players[index].getVIT();
                        int pAGI = ServerVars.Players[index].getAGI();
                        int pMAG = ServerVars.Players[index].getMAG();

                        int itemNum;
                        if (ServerVars.Players[index].getWeapon() > 0) {
                            itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getWeapon()].getItemNum();
                            pSTR = pSTR + ServerVars.Items[itemNum].STR;
                            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                        }
                        if (ServerVars.Players[index].getOffhand() > 0) {
                            itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getOffhand()].getItemNum();
                            pSTR = pSTR + ServerVars.Items[itemNum].STR;
                            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                        }
                        if (ServerVars.Players[index].getArmor() > 0) {
                            itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getArmor()].getItemNum();
                            pSTR = pSTR + ServerVars.Items[itemNum].STR;
                            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                        }
                        if (ServerVars.Players[index].getHelmet() > 0) {
                            itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getHelmet()].getItemNum();
                            pSTR = pSTR + ServerVars.Items[itemNum].STR;
                            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                        }
                        if (ServerVars.Players[index].getAcc1() > 0) {
                            itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getAcc1()].getItemNum();
                            pSTR = pSTR + ServerVars.Items[itemNum].STR;
                            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                        }
                        if (ServerVars.Players[index].getAcc2() > 0) {
                            itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getAcc2()].getItemNum();
                            pSTR = pSTR + ServerVars.Items[itemNum].STR;
                            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
                            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
                            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
                            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
                        }

                        ServerVars.Players[index].setMaxHP((pVIT * 2) * (pSTR / 2));
                        ServerVars.Players[index].setMaxMP((pMAG * 2) * (pDEF / 2));

                        switch (ServerVars.Players[index].getJob()) {
                            case 1: // Warrior
                                for (int i = 1; i <= ServerVars.MaxSpells; i++) {
                                    if (ServerVars.Spells[i].ClassReq == 1) {
                                        if (ServerVars.Spells[i].LevelReq == ServerVars.Players[index].getLevel()) {
                                            for (int a = 1; a <= 60; a++) {
                                                if (ServerVars.Players[index].spells[a].getSpellNum() == 0) {
                                                    ServerVars.Players[index].spells[a].setSpellNum(i);

                                                    SendServerData.SendMessage(index, ServerVars.MESSAGE_TYPE_SYSTEM, "New skill learned: " + ServerVars.Spells[i].Name);
                                                    SendServerData.SendPlayerData(index, index);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case 2: // Mage
                                for (int i = 1; i <= ServerVars.MaxSpells; i++) {
                                    if (ServerVars.Spells[i].ClassReq == 2) {
                                        if (ServerVars.Spells[i].LevelReq == ServerVars.Players[index].getLevel()) {
                                            for (int a = 1; a <= 60; a++) {
                                                if (ServerVars.Players[index].spells[a].getSpellNum() == 0) {
                                                    ServerVars.Players[index].spells[a].setSpellNum(i);

                                                    SendServerData.SendMessage(index, ServerVars.MESSAGE_TYPE_SYSTEM, "New skill learned: " + ServerVars.Spells[i].Name);
                                                    SendServerData.SendPlayerData(index, index);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case 3: // Cleric
                                for (int i = 1; i <= ServerVars.MaxSpells; i++) {
                                    if (ServerVars.Spells[i].ClassReq == 3) {
                                        if (ServerVars.Spells[i].LevelReq == ServerVars.Players[index].getLevel()) {
                                            for (int a = 1; a <= 60; a++) {
                                                if (ServerVars.Players[index].spells[a].getSpellNum() == 0) {
                                                    ServerVars.Players[index].spells[a].setSpellNum(i);

                                                    SendServerData.SendMessage(index, ServerVars.MESSAGE_TYPE_SYSTEM, "New skill learned: " + ServerVars.Spells[i].Name);
                                                    SendServerData.SendPlayerData(index, index);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                        }

                        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                            if (ServerVars.Players[i] != null) {
                                if (ServerVars.Players[i].getMap() == ServerVars.Players[index].getMap()) {
                                    SendServerData.SendSystemMessage(index, i, "Level Up!", Color.GREEN);
                                }
                            }
                        }
                    }
                    SendServerData.SendVital(index);
                    ServerVars.Players[index].setTarget(0);
                    ServerVars.Players[index].setTargetType(0);
                    SendServerData.SendPlayerData(index, index);
                }
            }
            if (ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].drop1 > 0) {
                int percent = ServerVars.Rnd.nextInt(100 + 1);
                int chance = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].dropChance1;
                if (percent <= chance) {
                    for (int i = 1; i <= ServerVars.MaxMapItems; i++) {
                        if (ServerVars.MapItems[mapNum].Item[i] == null) { ServerVars.MapItems[mapNum].Item[i] = new MapItem(); }
                        if (ServerVars.MapItems[mapNum].Item[i].itemNum <= 0) {
                            ServerVars.MapItems[mapNum].Item[i].itemNum = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].drop1;
                            ServerVars.MapItems[mapNum].Item[i].itemVal = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].dropVal1;
                            ServerVars.MapItems[mapNum].Item[i].x = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX();
                            ServerVars.MapItems[mapNum].Item[i].y = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY();
                            break;
                        }
                    }
                }
            }
            if (ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].drop2 > 0) {
                int percent = ServerVars.Rnd.nextInt(100 + 1);
                int chance = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].dropChance2;
                if (percent <= chance) {
                    for (int i = 1; i <= ServerVars.MaxMapItems; i++) {
                        if (ServerVars.MapItems[mapNum].Item[i] == null) { ServerVars.MapItems[mapNum].Item[i] = new MapItem(); }
                        if (ServerVars.MapItems[mapNum].Item[i].itemNum <= 0) {
                            ServerVars.MapItems[mapNum].Item[i].itemNum = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].drop2;
                            ServerVars.MapItems[mapNum].Item[i].itemVal = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].dropVal2;
                            ServerVars.MapItems[mapNum].Item[i].x = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX();
                            ServerVars.MapItems[mapNum].Item[i].y = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY();
                            break;
                        }
                    }
                }
            }
            if (ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].drop3 > 0) {
                int percent = ServerVars.Rnd.nextInt(100 + 1);
                int chance = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].dropChance3;
                if (percent <= chance) {
                    for (int i = 1; i <= ServerVars.MaxMapItems; i++) {
                        if (ServerVars.MapItems[mapNum].Item[i] == null) { ServerVars.MapItems[mapNum].Item[i] = new MapItem(); }
                        if (ServerVars.MapItems[mapNum].Item[i].itemNum <= 0) {
                            ServerVars.MapItems[mapNum].Item[i].itemNum = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].drop3;
                            ServerVars.MapItems[mapNum].Item[i].itemVal = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].dropVal3;
                            ServerVars.MapItems[mapNum].Item[i].x = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX();
                            ServerVars.MapItems[mapNum].Item[i].y = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY();
                            break;
                        }
                    }
                }
            }
            if (ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].drop4 > 0) {
                int percent = ServerVars.Rnd.nextInt(100 + 1);
                int chance = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].dropChance4;
                if (percent <= chance) {
                    for (int i = 1; i <= ServerVars.MaxMapItems; i++) {
                        if (ServerVars.MapItems[mapNum].Item[i] == null) { ServerVars.MapItems[mapNum].Item[i] = new MapItem(); }
                        if (ServerVars.MapItems[mapNum].Item[i].itemNum <= 0) {
                            ServerVars.MapItems[mapNum].Item[i].itemNum = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].drop4;
                            ServerVars.MapItems[mapNum].Item[i].itemVal = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].dropVal4;
                            ServerVars.MapItems[mapNum].Item[i].x = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX();
                            ServerVars.MapItems[mapNum].Item[i].y = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY();
                            break;
                        }
                    }
                }
            }
            if (ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].drop5 > 0) {
                int percent = ServerVars.Rnd.nextInt(100 + 1);
                int chance = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].dropChance5;
                if (percent <= chance) {
                    for (int i = 1; i <= ServerVars.MaxMapItems; i++) {
                        if (ServerVars.MapItems[mapNum].Item[i] == null) { ServerVars.MapItems[mapNum].Item[i] = new MapItem(); }
                        if (ServerVars.MapItems[mapNum].Item[i].itemNum <= 0) {
                            ServerVars.MapItems[mapNum].Item[i].itemNum = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].drop5;
                            ServerVars.MapItems[mapNum].Item[i].itemVal = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].dropVal5;
                            ServerVars.MapItems[mapNum].Item[i].x = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX();
                            ServerVars.MapItems[mapNum].Item[i].y = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY();
                            break;
                        }
                    }
                }
            }
            SendServerData.SendMapItems(mapNum);
        }
        //////////////////////////////////////////
        SendKillNPC sKNPC = new SendKillNPC();
        sKNPC.index = index;
        sKNPC.mapNpcIndex = mapNpcNum;

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sKNPC);
    }
    public static void SendNPCDmg(int index, int mapNPCNum, int damage) {
        SendNPCDmg sNPCDmg = new SendNPCDmg();

        sNPCDmg.mapNPCNum = mapNPCNum;
        sNPCDmg.damage = damage;

        int mapNum = ServerVars.Players[index].getMap();

        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            if (ServerVars.Players[i] != null) {
                if (ServerVars.Players[i].getMap() == mapNum) {
                    server.sendToTCP(ServerVars.Accounts[i].getCID(), sNPCDmg);
                }
            }
        }
    }
    public static void SendPlayerDmg(int index, int damage) {
        SendPlayerDmg sendPlayerDmg = new SendPlayerDmg();

        sendPlayerDmg.index = index;
        sendPlayerDmg.damage = damage;

        int mapNum = ServerVars.Players[index].getMap();

        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            if (ServerVars.Players[i] != null) {
                if (ServerVars.Players[i].getMap() == mapNum) {
                    server.sendToTCP(ServerVars.Accounts[i].getCID(), sendPlayerDmg);
                }
            }
        }
    }
    public static void SendRespawnNPC(int mapNum, int mapNPCNum) {
        SendRespawnNPC sRNPC = new SendRespawnNPC();

        sRNPC.hp = ServerVars.MapNPCs[mapNum].Npc[mapNPCNum].getHP();
        sRNPC.x = ServerVars.MapNPCs[mapNum].Npc[mapNPCNum].getX();
        sRNPC.y = ServerVars.MapNPCs[mapNum].Npc[mapNPCNum].getY();
        sRNPC.mapNPCNum = mapNPCNum;

        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            if (ServerVars.Players[i] != null) {
                if (ServerVars.Players[i].getMap() == mapNum) {
                    server.sendToTCP(ServerVars.Accounts[i].getCID(), sRNPC);
                }
            }
        }
    }
    public static void SendNPCXP(int index, int xp) {
        SendNPCXP sNPCXP = new SendNPCXP();

        sNPCXP.index = index;
        sNPCXP.xp = xp;

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sNPCXP);
    }
    public static void SendMapItems(int mapNum) {
        SendMapItems sendMapItems = new SendMapItems();
        for (int i = 1; i <= ServerVars.MaxMapItems; i++) {
            sendMapItems.mapItems[i] = ServerVars.MapItems[mapNum].Item[i];
        }

        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            if (ServerVars.Players[i] != null) {
                if (ServerVars.Players[i].getMap() == mapNum) {
                    server.sendToTCP(ServerVars.Accounts[i].getCID(), sendMapItems);
                }
            }
        }
    }
    public static void SendNotEnoughGoldMsg(int Index) {
        NotEnoughGoldMsg notEnoughGoldMsg = new NotEnoughGoldMsg();

        server.sendToTCP(ServerVars.Accounts[Index].getCID(), notEnoughGoldMsg);
    }
    public static void SendMessage(int index, int type, String msg) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.index = index;
        sendMessage.type = type;
        sendMessage.msg = msg;

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sendMessage);
    }
    public static void SendSystemMessage(int index, int toIndex, String msg, Color color) {
        SendSystemMessage sendSystemMessage = new SendSystemMessage();

        sendSystemMessage.index = index;
        sendSystemMessage.msg = msg;
        sendSystemMessage.color = color;

        server.sendToTCP(ServerVars.Accounts[toIndex].getCID(), sendSystemMessage);
    }
    public static void SendHPRegen(int index, int hp) {
        SendHPRegen sendHPRegen = new SendHPRegen();

        sendHPRegen.index = index;
        sendHPRegen.hp = hp;

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sendHPRegen);
    }
    public static void SendOpenPlayerMenu(int index, int targetIndex, int targetType) {
        SendOpenPlayerMenu sendOpenPlayerMenu = new SendOpenPlayerMenu();

        sendOpenPlayerMenu.index = index;
        sendOpenPlayerMenu.targetIndex = targetIndex;
        sendOpenPlayerMenu.targetType = targetType;

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sendOpenPlayerMenu);
    }
    public static void SendPartyInvite(int target, int index) {
        SendPartyInvite sendPartyInvite = new SendPartyInvite();

        sendPartyInvite.target = target;
        sendPartyInvite.index = index;

        server.sendToTCP(ServerVars.Accounts[target].getCID(), sendPartyInvite);
    }
    public static void SendPartyInfo(int index, int partyNum) {
        PartyInfo partyInfo = new PartyInfo();

        partyInfo.partyNum = partyNum;
        partyInfo.index = index;
        if (partyNum > 0) {
            partyInfo.party = ServerVars.Parties[partyNum];
        } else {
            partyInfo.party = new Party();
        }

        if (partyNum > 0) {
            for (int i = 1; i <= 3; i++) {
                if (ServerVars.Parties[partyNum].members[i] > 0) {
                    server.sendToTCP(ServerVars.Accounts[ServerVars.Parties[partyNum].members[i]].getCID(), partyInfo);
                    SendServerData.SendInvData(index, ServerVars.Parties[partyNum].members[i]);
                    SendServerData.SendSpellData(index, ServerVars.Parties[partyNum].members[i]);
                    SendServerData.SendPlayerData(index, ServerVars.Parties[partyNum].members[i]);
                }
            }
        } else {
            server.sendToTCP(ServerVars.Accounts[index].getCID(), partyInfo);
            SendServerData.SendInvData(index, index);
            SendServerData.SendSpellData(index, index);
            SendServerData.SendPlayerData(index, index);
        }
    }
    public static void SendMapSpells(int mapNum) {
        SendMapSpells sendMapSpells = new SendMapSpells();

        for (int i = 1; i <= ServerVars.MaxMapSpells; i++) {
            sendMapSpells.index = i;
            sendMapSpells.target = ServerVars.MapSpells[mapNum].Spell[i].getIndex();
            sendMapSpells.spellNum = ServerVars.MapSpells[mapNum].Spell[i].getSpellNum();
            sendMapSpells.x = ServerVars.MapSpells[mapNum].Spell[i].getX();
            sendMapSpells.y = ServerVars.MapSpells[mapNum].Spell[i].getY();
            sendMapSpells.type = ServerVars.MapSpells[mapNum].Spell[i].getType();

            for (int a = 1; a <= ServerVars.MaxPlayers; a++) {
                if (ServerVars.Players[a] != null) {
                    if (ServerVars.Players[a].getMap() == mapNum) {
                        server.sendToTCP(ServerVars.Accounts[a].getCID(), sendMapSpells);
                    }
                }
            }
        }
    }
    public static void SendCastTime(int index, int spellInvSlot, int castTime) {
        SendCastTime sendCastTime = new SendCastTime();

        sendCastTime.spellInvSlot = spellInvSlot;
        sendCastTime.castTime = castTime;

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sendCastTime);
    }
    public static void SendCoolDown(int index, int spellInvSlot, int coolDown) {

    }
    public static void SendInvalidBuildVersion(int CID) {
        InvalidBuildVersion invalidBuildVersion = new InvalidBuildVersion();

        invalidBuildVersion.isInvalid = true;

        server.sendToTCP(CID, invalidBuildVersion);
    }
}
