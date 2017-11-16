package com.forgottenartsstudios.networking.packetdata;

import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryonet.Connection;
import com.forgottenartsstudios.data.AccountData;
import com.forgottenartsstudios.data.General;
import com.forgottenartsstudios.data.Inventory_Struct;
import com.forgottenartsstudios.data.MapItem;
import com.forgottenartsstudios.data.Player;
import com.forgottenartsstudios.helpers.ServerVars;
import com.forgottenartsstudios.networking.packets.*;
import com.sun.org.apache.xpath.internal.operations.Variable;

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
        if (ServerVars.Players[index] == null) { return; }
        if (ServerVars.Players[indexTo] == null) { return; }
        PlayerData plData = new PlayerData();
        plData.playerData = new Player();
        plData.playerData.inventory = new Inventory_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            plData.playerData.inventory[i] = new Inventory_Struct();
        }
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

        server.sendToTCP(ServerVars.Accounts[indexTo].getCID(), plData);

        SendInvData(index);
    }
    public static void SendAccountRegistered(Connection connection) {
        AccountRegistered ar = new AccountRegistered();
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
        for (int i = 1; i <= 60; i++) {
            sndLogin.char1.inventory[i] = new Inventory_Struct();
            sndLogin.char1.inventory[i].setItemNum(ServerVars.Accounts[index].chars[1].inventory[i].getItemNum());
            sndLogin.char1.inventory[i].setItemVal(ServerVars.Accounts[index].chars[1].inventory[i].getItemVal());
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
        for (int i = 1; i <= 60; i++) {
            sndLogin.char2.inventory[i] = new Inventory_Struct();
            sndLogin.char2.inventory[i].setItemNum(ServerVars.Accounts[index].chars[2].inventory[i].getItemNum());
            sndLogin.char2.inventory[i].setItemVal(ServerVars.Accounts[index].chars[2].inventory[i].getItemVal());
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
        for (int i = 1; i <= 60; i++) {
            sndLogin.char3.inventory[i] = new Inventory_Struct();
            sndLogin.char3.inventory[i].setItemNum(ServerVars.Accounts[index].chars[3].inventory[i].getItemNum());
            sndLogin.char3.inventory[i].setItemVal(ServerVars.Accounts[index].chars[3].inventory[i].getItemVal());
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
    public static void SendInvData(int Index) {
        SendInventory sInv = new SendInventory();

        for (int i = 1; i <= 60; i++) {
            sInv.invData[i] = ServerVars.Players[Index].inventory[i];
        }

        server.sendToTCP(ServerVars.Accounts[Index].getCID(), sInv);
    }
    public static void SendBoughtItemMsg(int Index) {
        ItemBoughtMsg sBoughtMsg = new ItemBoughtMsg();

        server.sendToTCP(ServerVars.Accounts[Index].getCID(), sBoughtMsg);
    }
    public static void SendKillNPC(int index, int mapNpcNum, boolean giveXP) {
        // Add the XP and check if they leveled up
        if (giveXP) {
            int mapNum = ServerVars.Players[index].getMap();
            int XP = ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum()].Exp;
            SendServerData.SendNPCXP(index, XP);
            if (XP > 0) {
                ServerVars.Players[index].setEXP(ServerVars.Players[index].getEXP() + XP);
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

                    for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                        if (ServerVars.Players[i] != null) {
                            if (ServerVars.Players[i].getMap() == ServerVars.Players[index].getMap()) {
                                SendServerData.SendSystemMessage(index, i, "Level Up!", Color.GREEN);
                            }
                        }
                    }
                }
                SendServerData.SendVital(index);
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
    public static void SendOpenPlayerMenu(int index, int targetIndex) {
        SendOpenPlayerMenu sendOpenPlayerMenu = new SendOpenPlayerMenu();

        sendOpenPlayerMenu.index = index;
        sendOpenPlayerMenu.targetIndex = targetIndex;

        server.sendToTCP(ServerVars.Accounts[index].getCID(), sendOpenPlayerMenu);
    }
    public static void SendPartyInvite(int target, int index) {
        SendPartyInvite sendPartyInvite = new SendPartyInvite();

        sendPartyInvite.target = target;
        sendPartyInvite.index = index;

        server.sendToTCP(ServerVars.Accounts[target].getCID(), sendPartyInvite);
    }
    public static void SendPartyInfo(int partyNum) {
        PartyInfo partyInfo = new PartyInfo();

        partyInfo.party = ServerVars.Parties[partyNum];

        for (int i = 1; i <= 3; i++) {
            if (ServerVars.Parties[partyNum].members[i] > 0) {
                server.sendToTCP(ServerVars.Accounts[ServerVars.Parties[partyNum].members[i]].getCID(), partyInfo);
            }
        }
    }
}
