package com.forgottenartsstudios.networking.packetdata;

import com.esotericsoftware.kryonet.Connection;
import com.forgottenartsstudios.data.AccountData;
import com.forgottenartsstudios.data.General;
import com.forgottenartsstudios.data.Inventory_Struct;
import com.forgottenartsstudios.data.LoadData;
import com.forgottenartsstudios.data.MapItem;
import com.forgottenartsstudios.data.Player;
import com.forgottenartsstudios.data.SaveData;
import com.forgottenartsstudios.helpers.ServerVars;
import com.forgottenartsstudios.networking.packets.ChooseChar;
import com.forgottenartsstudios.networking.packets.Connect;
import com.forgottenartsstudios.networking.packets.CreateChar;
import com.forgottenartsstudios.networking.packets.KeepAliveCheck;
import com.forgottenartsstudios.networking.packets.Login;
import com.forgottenartsstudios.networking.packets.MovePlayer;
import com.forgottenartsstudios.networking.packets.NewAccount;
import com.forgottenartsstudios.networking.packets.PlayerData;
import com.forgottenartsstudios.networking.packets.SendBuyItem;
import com.forgottenartsstudios.networking.packets.SendDropItem;
import com.forgottenartsstudios.networking.packets.SendMapItems;
import com.forgottenartsstudios.networking.packets.SendMessage;
import com.forgottenartsstudios.networking.packets.SendPickUpItem;
import com.forgottenartsstudios.networking.packets.SendSearch;
import com.forgottenartsstudios.networking.packets.SendTryAttack;
import com.forgottenartsstudios.networking.packets.SendUseItem;
import com.forgottenartsstudios.networking.packets.SendUsePoint;
import com.forgottenartsstudios.networking.packets.WarpCheck;
import com.forgottenartsstudios.server.RTOServer;
import com.forgottenartsstudios.server.serverWindow;
import com.sun.org.apache.xpath.internal.operations.Variable;

import static com.forgottenartsstudios.server.RTOServer.server;

/**
 * Created by forgo on 10/8/2017.
 */

public class HandleServerData {
    public static void HandleConnect(Object object) {
        Connect cnt = (Connect) object;
        int client_mode = cnt.client_mode;

        if (client_mode == 1) {
            serverWindow.svrMonitor.append("Desktop client connected" + "\n");
        } else if (client_mode == 2) {
            serverWindow.svrMonitor.append("Android client connected" + "\n");
        }
        serverWindow.svrMonitor.setCaretPosition(serverWindow.svrMonitor.getDocument().getLength());
    }
    public static void HandleNewAccount(Object object, Connection connection) {
        NewAccount newAccount = (NewAccount) object;

        AccountData accountData = new AccountData();

        accountData.setID(newAccount.ID);
        accountData.setPW(newAccount.PW);

        Player player1 = new Player();

        player1.setName("");
        player1.setJob(0);
        player1.setSprite(0);
        player1.setLevel(1);
        player1.setPoints(0);
        player1.setHP(0);
        player1.setMP(0);
        player1.setMaxHP(0);
        player1.setMaxMP(0);

        player1.setEXP(0);
        player1.setNextLVL(0);

        player1.setMap(0);
        player1.setX(0);
        player1.setY(0);
        player1.setDir(0);

        player1.setSTR(0);
        player1.setDEF(0);
        player1.setVIT(0);
        player1.setAGI(0);
        player1.setMAG(0);

        player1.setWeapon(0);
        player1.setOffhand(0);
        player1.setArmor(0);
        player1.setHelmet(0);
        player1.setAcc1(0);
        player1.setAcc2(0);

        player1.inventory = new Inventory_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            player1.inventory[i] = new Inventory_Struct();
            player1.inventory[i].setItemNum(0);
            player1.inventory[i].setItemVal(0);
        }

        Player player2 = new Player();

        player2.setName("");
        player2.setJob(0);
        player2.setSprite(0);
        player2.setLevel(1);
        player2.setPoints(0);
        player2.setHP(0);
        player2.setMP(0);
        player2.setMaxHP(0);
        player2.setMaxMP(0);

        player2.setEXP(0);
        player2.setNextLVL(0);

        player2.setMap(0);
        player2.setX(0);
        player2.setY(0);
        player2.setDir(0);

        player2.setSTR(0);
        player2.setDEF(0);
        player2.setVIT(0);
        player2.setAGI(0);
        player2.setMAG(0);

        player2.setWeapon(0);
        player2.setOffhand(0);
        player2.setArmor(0);
        player2.setHelmet(0);
        player2.setAcc1(0);
        player2.setAcc2(0);

        player2.inventory = new Inventory_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            player2.inventory[i] = new Inventory_Struct();
            player2.inventory[i].setItemNum(0);
            player2.inventory[i].setItemVal(0);
        }

        Player player3 = new Player();

        player3.setName("");
        player3.setJob(0);
        player3.setSprite(0);
        player3.setLevel(1);
        player3.setPoints(0);
        player3.setHP(0);
        player3.setMP(0);
        player3.setMaxHP(0);
        player3.setMaxMP(0);

        player3.setEXP(0);
        player3.setNextLVL(0);

        player3.setMap(0);
        player3.setX(0);
        player3.setY(0);
        player3.setDir(0);

        player3.setSTR(0);
        player3.setDEF(0);
        player3.setVIT(0);
        player3.setAGI(0);
        player3.setMAG(0);

        player3.setWeapon(0);
        player3.setOffhand(0);
        player3.setArmor(0);
        player3.setHelmet(0);
        player3.setAcc1(0);
        player3.setAcc2(0);

        player3.inventory = new Inventory_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            player3.inventory[i] = new Inventory_Struct();
            player3.inventory[i].setItemNum(0);
            player3.inventory[i].setItemVal(0);
        }

        //accountData.chars = new Player[3 + 1];
        for (int i = 1; i <= 3; i++) {
            accountData.chars[i] = new Player();
        }

        accountData.chars[1] = player1;
        accountData.chars[2] = player2;
        accountData.chars[3] = player3;

        SaveData.SaveAccount(accountData);

        SendServerData.SendAccountRegistered(connection);

        serverWindow.svrMonitor.append("Account (" + accountData.getID() + ") created." + "\n");
    }
    public static void HandleLogin(Object object, Connection connection) {
        Login login = (Login) object;

        String ID = login.ID;
        String PW = login.PW;

        LoadData.LoadAccount(ID, PW, connection.getID());

        serverWindow.svrMonitor.append("Account (" + ID + ") has logged in." + "\n");
    }
    public static void HandleCreateChar(Object object, Connection connection) {
        CreateChar crChar = (CreateChar) object;

        String Name = crChar.Name;
        int Job = crChar.Job;
        int Sprite = crChar.Sprite;
        int charSlot = crChar.CharSlot;
        int index = crChar.Index;

        ServerVars.Accounts[index].setCID(connection.getID());

        ServerVars.Accounts[index].chars[charSlot].setName(Name);
        ServerVars.Accounts[index].chars[charSlot].setJob(Job);
        ServerVars.Accounts[index].chars[charSlot].setSprite(Sprite);

        if (Job == 1) {
            General.createWarrior(index, charSlot);
        } else if (Job == 2) {
            General.createWizard(index, charSlot);
        } else if (Job == 3) {
            General.createCleric(index, charSlot);
        } else if (Job == 4) {
            General.createRanger(index, charSlot);
        } else if (Job == 5) {
            General.createRogue(index, charSlot);
        }

        for (int i = 1; i <= 60; i++) {
            ServerVars.Accounts[index].chars[charSlot].inventory[i].setItemNum(0);
            ServerVars.Accounts[index].chars[charSlot].inventory[i].setItemVal(0);
        }

        SaveData.SaveAccount(ServerVars.Accounts[index]);

        SendServerData.SendLogin(index);
    }
    public static void HandleChooseChar(Object object, Connection connection) {
        ChooseChar chChar = (ChooseChar) object;
        int index = chChar.index;
        int charSlot = chChar.charSlot;

        ServerVars.Accounts[index].setCID(connection.getID());

        ServerVars.Players[index] = ServerVars.Accounts[index].chars[charSlot];

        /*ServerVars.Players[index].inventory = new Inventory_Struct[60 + 1];
        for (int i = 1; i <= 60; i++) {
            ServerVars.Players[index].inventory[i] = new Inventory_Struct();
        }

        ServerVars.Players[index].inventory = ServerVars.Accounts[index].chars[charSlot].inventory;*/

        SendServerData.SendPlayerData(index, index);

        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            if (ServerVars.Players[i] != null) {
                if (i != index) {
                    if (ServerVars.Players[i].getMap() == ServerVars.Players[index].getMap()) {
                        SendServerData.SendPlayerData(i, index);
                    }
                }
            }
        }

        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            if (ServerVars.Players[i] != null) {
                if (i != index) {
                    if (ServerVars.Players[i].getMap() == ServerVars.Players[index].getMap()) {
                        SendServerData.SendPlayerData(index, i);
                    }
                }
            }
        }

        for (int i = 1; i <= ServerVars.MaxMapNPCs; i++) {
            SendServerData.SendSpawnNpc(ServerVars.Players[index].getMap(), i, 0);
        }

        for (int i = 1; i <= ServerVars.MaxItems; i++) {
            SendServerData.SendItems(index, i);
        }

        SendServerData.SendMapItems(ServerVars.Players[index].getMap());
    }
    public static void HandleMovePlayer(Object object) {
        MovePlayer mPlayer = (MovePlayer) object;

        int Index = mPlayer.Index;
        int Map = mPlayer.Map;
        int X = mPlayer.X;
        int Y = mPlayer.Y;
        int Dir = mPlayer.Dir;

        boolean canMove;

        switch (Dir) {
            case ServerVars.DIR_UP:
                if (Y <= 0 || (Y - 1) < 0) {
                    if (ServerVars.mapData[Map].Up > 0) {
                        int newMap = ServerVars.mapData[Map].Up;
                        int newX = ServerVars.Players[Index].getX();
                        int newY = ServerVars.mapData[ServerVars.mapData[Map].Up].MaxY - 1;
                        General.PlayerWarp(Index, newMap, newX, newY);
                    }
                } else {
                    canMove = General.TileIsOpen(Map, X, Y - 1);
                    if (canMove) {
                        ServerVars.Players[Index].setX(X);
                        ServerVars.Players[Index].setY(Y - 1);
                        ServerVars.Players[Index].setDir(Dir);
                    } else {
                        ServerVars.Players[Index].setDir(Dir);
                    }
                    for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                        if (ServerVars.Players[i] != null) {
                            if (i != Index) {
                                if (ServerVars.Players[i].getMap() == Map) {
                                    SendServerData.SendMovePlayer(Index, Map, X, Y, Dir, canMove);
                                }
                            }
                        }
                    }
                    ServerVars.Players[Index].setMoving(1);
                    ServerVars.Players[Index].setOffsetY(32);
                }
                break;
            case ServerVars.DIR_DOWN:
                if (Y >= ServerVars.mapData[Map].MaxY - 1 || (Y + 1) > ServerVars.mapData[Map].MaxY - 1) {
                    if (ServerVars.mapData[Map].Down > 0) {
                        int newMap = ServerVars.mapData[Map].Down;
                        int newX = ServerVars.Players[Index].getX();
                        int newY = 0;
                        General.PlayerWarp(Index, newMap, newX, newY);
                    }
                } else {
                    canMove = General.TileIsOpen(Map, X, Y + 1);
                    if (canMove) {
                        ServerVars.Players[Index].setX(X);
                        ServerVars.Players[Index].setY(Y + 1);
                        ServerVars.Players[Index].setDir(Dir);
                    } else {
                        ServerVars.Players[Index].setDir(Dir);
                    }
                    for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                        if (ServerVars.Players[i] != null) {
                            if (i != Index) {
                                if (ServerVars.Players[i].getMap() == Map) {
                                    SendServerData.SendMovePlayer(Index, Map, X, Y, Dir, canMove);
                                }
                            }
                        }
                    }
                    ServerVars.Players[Index].setMoving(1);
                    ServerVars.Players[Index].setOffsetY(32 * -1);
                }
                break;
            case ServerVars.DIR_LEFT:
                if (X <= 0 || (X - 1) < 0) {
                    if (ServerVars.mapData[Map].Left > 0) {
                        int newMap = ServerVars.mapData[Map].Left;
                        int newX = ServerVars.mapData[ServerVars.mapData[Map].Left].MaxX - 1;
                        int newY = ServerVars.Players[Index].getY();
                        General.PlayerWarp(Index, newMap, newX, newY);
                    }
                } else {
                    canMove = General.TileIsOpen(Map, X - 1, Y);
                    if (canMove) {
                        ServerVars.Players[Index].setX(X - 1);
                        ServerVars.Players[Index].setY(Y);
                        ServerVars.Players[Index].setDir(Dir);
                    } else {
                        ServerVars.Players[Index].setDir(Dir);
                    }
                    for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                        if (ServerVars.Players[i] != null) {
                            if (i != Index) {
                                if (ServerVars.Players[i].getMap() == Map) {
                                    SendServerData.SendMovePlayer(Index, Map, X, Y, Dir, canMove);
                                }
                            }
                        }
                    }
                    ServerVars.Players[Index].setMoving(1);
                    ServerVars.Players[Index].setOffsetX(32);
                }
                break;
            case ServerVars.DIR_RIGHT:
                if (X >= ServerVars.mapData[Map].MaxX - 1 || (X + 1) > ServerVars.mapData[Map].MaxX - 1) {
                    if (ServerVars.mapData[Map].Right > 0) {
                        int newMap = ServerVars.mapData[Map].Right;
                        int newX = 0;
                        int newY = ServerVars.Players[Index].getY();
                        General.PlayerWarp(Index, newMap, newX, newY);
                    }
                } else {
                    canMove = General.TileIsOpen(Map, X + 1, Y);
                    if (canMove) {
                        ServerVars.Players[Index].setX(X + 1);
                        ServerVars.Players[Index].setY(Y);
                        ServerVars.Players[Index].setDir(Dir);
                    } else {
                        ServerVars.Players[Index].setDir(Dir);
                    }
                    for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                        if (ServerVars.Players[i] != null) {
                            if (i != Index) {
                                if (ServerVars.Players[i].getMap() == Map) {
                                    SendServerData.SendMovePlayer(Index, Map, X, Y, Dir, canMove);
                                }
                            }
                        }
                    }
                    ServerVars.Players[Index].setMoving(1);
                    ServerVars.Players[Index].setOffsetX(32 * -1);
                }
                break;
        }

        // Check for warp tile
        switch (Dir) {
            case ServerVars.DIR_UP:
                if (Y - 1 <= 0) { return; }
                if (ServerVars.mapData[Map].Tile[X][Y - 1].Type == ServerVars.TILE_TYPE_WARP) {
                    int newMap = ServerVars.mapData[Map].Tile[X][Y - 1].Data1;
                    int newX = ServerVars.mapData[Map].Tile[X][Y - 1].Data2;
                    int newY = ServerVars.mapData[Map].Tile[X][Y - 1].Data3;
                    General.PlayerWarp(Index, newMap, newX, newY);
                }
                break;
            case ServerVars.DIR_DOWN:
                if (Y + 1 >= ServerVars.mapData[Map].MaxY) { return; }
                if (ServerVars.mapData[Map].Tile[X][Y + 1].Type == ServerVars.TILE_TYPE_WARP) {
                    int newMap = ServerVars.mapData[Map].Tile[X][Y + 1].Data1;
                    int newX = ServerVars.mapData[Map].Tile[X][Y + 1].Data2;
                    int newY = ServerVars.mapData[Map].Tile[X][Y + 1].Data3;
                    General.PlayerWarp(Index, newMap, newX, newY);
                }
                break;
            case ServerVars.DIR_LEFT:
                if (X - 1 <= 0) { return; }
                if (ServerVars.mapData[Map].Tile[X - 1][Y].Type == ServerVars.TILE_TYPE_WARP) {
                    int newMap = ServerVars.mapData[Map].Tile[X - 1][Y].Data1;
                    int newX = ServerVars.mapData[Map].Tile[X - 1][Y].Data2;
                    int newY = ServerVars.mapData[Map].Tile[X - 1][Y].Data3;
                    General.PlayerWarp(Index, newMap, newX, newY);
                }
                break;
            case ServerVars.DIR_RIGHT:
                if (X + 1 >= ServerVars.mapData[Map].MaxX) { return; }
                if (ServerVars.mapData[Map].Tile[X + 1][Y].Type == ServerVars.TILE_TYPE_WARP) {
                    int newMap = ServerVars.mapData[Map].Tile[X + 1][Y].Data1;
                    int newX = ServerVars.mapData[Map].Tile[X + 1][Y].Data2;
                    int newY = ServerVars.mapData[Map].Tile[X + 1][Y].Data3;
                    General.PlayerWarp(Index, newMap, newX, newY);
                }
                break;
        }
    }
    public static void HandleKeepAliveCheck(Object object) {
        KeepAliveCheck keepAliveCheck = (KeepAliveCheck) object;
        int Index = keepAliveCheck.index;

        if (ServerVars.Accounts[Index] != null) {
            ServerVars.Accounts[Index].setKeepAlive(keepAliveCheck.keepAlive);
            ServerVars.Accounts[Index].setKeepCount(0);
        }
    }
    public static void HandleWarpCheck(Object object) {
        WarpCheck wCheck = (WarpCheck) object;
        int Index = wCheck.index;
        int X = ServerVars.Players[Index].getX();
        int Y = ServerVars.Players[Index].getY();
        int mapNum = ServerVars.Players[Index].getMap();

        if (mapNum < 1 || mapNum > ServerVars.MaxMaps) { return; }
        if (X < 0 || X > ServerVars.mapData[mapNum].MaxX) { return; }
        if (Y < 0 || Y > ServerVars.mapData[mapNum].MaxY) { return; }

        if (ServerVars.mapData[mapNum].Tile[X][Y].Type == ServerVars.TILE_TYPE_WARP) {
            int wMap = ServerVars.mapData[mapNum].Tile[X][Y].Data1;
            int wX = ServerVars.mapData[mapNum].Tile[X][Y].Data2;
            int wY = ServerVars.mapData[mapNum].Tile[X][Y].Data3;
            General.PlayerWarp(Index, wMap, wX, wY);
        }
    }
    public static void HandleSearch(Object object) {
        SendSearch sndSearch = (SendSearch) object;

        int index = sndSearch.index;
        int i = sndSearch.npcIndex;
        int searchType = sndSearch.searchType;
        int x = sndSearch.x;
        int y = sndSearch.y;
        int mapNum = sndSearch.mapNum;

        switch (searchType) {
            case ServerVars.SEARCH_TYPE_NPC:
            if (i <= 0) { return; }
            if (ServerVars.MapNPCs[mapNum] == null) { return; }
            if (ServerVars.MapNPCs[mapNum].Npc == null) { return; }
            if (ServerVars.MapNPCs[mapNum].Npc[i] == null) { return; }
            if (ServerVars.MapNPCs[mapNum].Npc[i].getNum() <= 0) { return; }
                if (ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].Behaviour == ServerVars.NPC_BEHAVIOUR_SELLER_STANDING) {
                    SendServerData.SendOpenShop(index, ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].shopNum);
                }
                break;
        }
    }
    public static void HandleBuyItem(Object object) {
        SendBuyItem sBuyItem = (SendBuyItem) object;

        int index = sBuyItem.index;
        int shopNum = sBuyItem.shopNum;
        int shopSlot = sBuyItem.shopSlot;

        if (index <= 0) { return; }
        if (shopNum <= 0) { return; }
        if (shopSlot <= 0) { return; }

        int itemNum = ServerVars.Shops[shopNum].itemNum[shopSlot];
        int itemVal = ServerVars.Shops[shopNum].itemVal[shopSlot];

        if (itemNum <= 0) { return; }
        if (itemVal <= 0) { return; }

        int goldTotal = 0;
        for (int i = 1; i <= 60; i++) {
            if (ServerVars.Players == null) { return; }
            if (ServerVars.Players[index] == null) { return; }
            if (ServerVars.Players[index].inventory == null) { return; }
            if (ServerVars.Players[index].inventory[i] == null) { return; }
            if (ServerVars.Players[index].inventory[i].getItemNum() == 1) {
                goldTotal += ServerVars.Players[index].inventory[i].getItemVal();
                break;
            }
        }
        if (ServerVars.Items[itemNum].Cost > 0) {
            if (goldTotal >= ServerVars.Items[itemNum].Cost) {
                goldTotal -= ServerVars.Items[itemNum].Cost;
                for (int i = 1; i <= 60; i++) {
                    if (ServerVars.Players[index].inventory[i].getItemNum() == 0) {
                        ServerVars.Players[index].inventory[i].setItemNum(itemNum);
                        ServerVars.Players[index].inventory[i].setItemVal(itemVal);
                        SendServerData.SendBoughtItemMsg(index);
                        break;
                    }
                }
                for (int i = 1; i <= 60; i++) {
                    if (ServerVars.Players[index].inventory[i].getItemNum() == 1) {
                        ServerVars.Players[index].inventory[i].setItemVal(goldTotal);
                        break;
                    }
                }
            } else {
                SendServerData.SendNotEnoughGoldMsg(index);
            }
        } else {
            for (int i = 1; i <= 60; i++) {
                if (ServerVars.Players[index].inventory[i].getItemNum() == 0) {
                    ServerVars.Players[index].inventory[i].setItemNum(itemNum);
                    ServerVars.Players[index].inventory[i].setItemVal(itemVal);
                    SendServerData.SendBoughtItemMsg(index);
                    break;
                }
            }
        }

        SendServerData.SendVital(index);
        SendServerData.SendInvData(index);
    }
    public static void HandleUseItem(Object object) {
        SendUseItem sUseItem = (SendUseItem) object;

        int index = sUseItem.index;
        int invSlot = sUseItem.invSlot;
        if (ServerVars.Players[index].inventory[invSlot] == null) { return; }
        int itemNum = ServerVars.Players[index].inventory[invSlot].getItemNum();

        if (itemNum > 0) {
            int itemType = ServerVars.Items[itemNum].itemType;
            if (ServerVars.Players[index].getLevel() >= ServerVars.Items[itemNum].LVL) {
                switch (itemType) {
                    case ServerVars.ITEM_TYPE_WEAPON:
                        if (ServerVars.Players[index].getWeapon() != invSlot) {
                            if (ServerVars.Players[index].getSTR() >= ServerVars.Items[itemNum].STR) {
                                ServerVars.Players[index].setWeapon(invSlot);
                            }
                        } else {
                            ServerVars.Players[index].setWeapon(0);
                        }
                        break;
                    case ServerVars.ITEM_TYPE_ARMOR:
                        if (ServerVars.Players[index].getArmor() != invSlot) {
                            if (ServerVars.Players[index].getDEF() >= ServerVars.Items[itemNum].DEF) {
                                ServerVars.Players[index].setArmor(invSlot);
                            }
                        } else {
                            ServerVars.Players[index].setArmor(0);
                        }
                        break;
                    case ServerVars.ITEM_TYPE_HELMET:
                        if (ServerVars.Players[index].getHelmet() != invSlot) {
                            if (ServerVars.Players[index].getVIT() >= ServerVars.Items[itemNum].VIT) {
                                ServerVars.Players[index].setHelmet(invSlot);
                            }
                        } else {
                            ServerVars.Players[index].setHelmet(0);
                        }
                        break;
                    case ServerVars.ITEM_TYPE_OFFHAND:
                        if (ServerVars.Players[index].getOffhand() != invSlot) {
                            if (ServerVars.Players[index].getDEF() >= ServerVars.Items[itemNum].DEF) {
                                ServerVars.Players[index].setOffhand(invSlot);
                            }
                        } else {
                            ServerVars.Players[index].setOffhand(0);
                        }
                        break;
                }
            }

            int pSTR = ServerVars.Players[index].getSTR();
            int pDEF = ServerVars.Players[index].getDEF();
            int pVIT = ServerVars.Players[index].getVIT();
            int pAGI = ServerVars.Players[index].getAGI();
            int pMAG = ServerVars.Players[index].getMAG();
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

            if (ServerVars.Players[index].getLevel() >= ServerVars.Items[itemNum].LVL) {
                SendServerData.SendVital(index);
                SendServerData.SendInvData(index);
            }
        }
    }
    public static void HandleTryAttack(Object object) {
        SendTryAttack hTAtk = (SendTryAttack) object;

        int index = hTAtk.index;

        int mapNum = ServerVars.Players[index].getMap();
        int x = ServerVars.Players[index].getX();
        int y = ServerVars.Players[index].getY();
        int dir = ServerVars.Players[index].getDir();

        // Check for NPC
        switch (dir) {
            case ServerVars.DIR_UP:
                for (int i = 1; i <= ServerVars.MaxMapNPCs; i++) {
                    if (ServerVars.MapNPCs[mapNum].Npc[i].getX() == x && ServerVars.MapNPCs[mapNum].Npc[i].getY() == y - 1) {
                        if (ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].Behaviour < 2 || ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].Behaviour == 10) {
                            if (!ServerVars.MapNPCs[mapNum].Npc[i].isDead()) {
                                // Damage Algorithm
                                double diff = RTOServer.GetPlayerDamage(index) - RTOServer.GetNpcProtection(i);
                                if (diff < 0) diff = 0;

                                // Increase up to 10% for minimum damage
                                double minDam = diff - Math.round(diff * 0.1);
                                double maxDam = diff + Math.round(diff * 0.1);
                                if (maxDam < minDam) minDam = maxDam;
                                int Damage = ServerVars.Rnd.nextInt((int)maxDam - (int)minDam + 1) + (int)minDam;

                                if (Damage > 0) {
                                    ServerVars.MapNPCs[mapNum].Npc[i].setHP(ServerVars.MapNPCs[mapNum].Npc[i].getHP() - Damage);
                                    ServerVars.MapNPCs[mapNum].Npc[i].setTarget(index);
                                    ServerVars.MapNPCs[mapNum].Npc[i].setTargetType(ServerVars.TARGET_TYPE_PLAYER);
                                    SendServerData.SendNPCDmg(index, i, Damage);
                                    if (ServerVars.MapNPCs[mapNum].Npc[i].getHP() <= 0) {
                                        ServerVars.MapNPCs[mapNum].Npc[i].setHP(0);
                                        ServerVars.MapNPCs[mapNum].Npc[i].setDead(true);
                                        ServerVars.MapNPCs[mapNum].Npc[i].setSpawnWait(ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].SpawnSecs);

                                        SendServerData.SendKillNPC(index, i, true);
                                        for (int a = 1; a <= ServerVars.MaxPlayers; a++) {
                                            if (a != index) {
                                                if (ServerVars.Players[a] != null) {
                                                    if (mapNum == ServerVars.Players[a].getMap()) {
                                                        SendServerData.SendKillNPC(a, i, false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    SendServerData.SendNPCDmg(index, i, 0);
                                }
                            }
                        }
                    }
                }
                break;
            case ServerVars.DIR_DOWN:
                for (int i = 1; i <= ServerVars.MaxMapNPCs; i++) {
                    if (ServerVars.MapNPCs[mapNum].Npc[i].getX() == x && ServerVars.MapNPCs[mapNum].Npc[i].getY() == y + 1) {
                        if (ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].Behaviour < 2 || ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].Behaviour == 10) {
                            if (!ServerVars.MapNPCs[mapNum].Npc[i].isDead()) {
                                // Damage Algorithm
                                double diff = RTOServer.GetPlayerDamage(index) - RTOServer.GetNpcProtection(i);
                                if (diff < 0) diff = 0;

                                // Increase up to 10% for minimum damage
                                double minDam = diff - Math.round(diff * 0.1);
                                double maxDam = diff + Math.round(diff * 0.1);
                                if (maxDam < minDam) minDam = maxDam;
                                int Damage = ServerVars.Rnd.nextInt((int)maxDam - (int)minDam + 1) + (int)minDam;

                                if (Damage > 0) {
                                    ServerVars.MapNPCs[mapNum].Npc[i].setHP(ServerVars.MapNPCs[mapNum].Npc[i].getHP() - Damage);
                                    ServerVars.MapNPCs[mapNum].Npc[i].setTarget(index);
                                    ServerVars.MapNPCs[mapNum].Npc[i].setTargetType(ServerVars.TARGET_TYPE_PLAYER);
                                    SendServerData.SendNPCDmg(index, i, Damage);
                                    if (ServerVars.MapNPCs[mapNum].Npc[i].getHP() <= 0) {
                                        ServerVars.MapNPCs[mapNum].Npc[i].setHP(0);
                                        ServerVars.MapNPCs[mapNum].Npc[i].setDead(true);
                                        ServerVars.MapNPCs[mapNum].Npc[i].setSpawnWait(ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].SpawnSecs);

                                        SendServerData.SendKillNPC(index, i, true);
                                        for (int a = 1; a <= ServerVars.MaxPlayers; a++) {
                                            if (a != index) {
                                                if (ServerVars.Players[a] != null) {
                                                    if (mapNum == ServerVars.Players[a].getMap()) {
                                                        SendServerData.SendKillNPC(a, i, false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    SendServerData.SendNPCDmg(index, i, 0);
                                }
                            }
                        }
                    }
                }
                break;
            case ServerVars.DIR_LEFT:
                for (int i = 1; i <= ServerVars.MaxMapNPCs; i++) {
                    if (ServerVars.MapNPCs[mapNum].Npc[i].getX() == x - 1 && ServerVars.MapNPCs[mapNum].Npc[i].getY() == y) {
                        if (ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].Behaviour < 2 || ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].Behaviour == 10) {
                            if (!ServerVars.MapNPCs[mapNum].Npc[i].isDead()) {
                                // Damage Algorithm
                                double diff = RTOServer.GetPlayerDamage(index) - RTOServer.GetNpcProtection(i);
                                if (diff < 0) diff = 0;

                                // Increase up to 10% for minimum damage
                                double minDam = diff - Math.round(diff * 0.1);
                                double maxDam = diff + Math.round(diff * 0.1);
                                if (maxDam < minDam) minDam = maxDam;
                                int Damage = ServerVars.Rnd.nextInt((int)maxDam - (int)minDam + 1) + (int)minDam;

                                if (Damage > 0) {
                                    ServerVars.MapNPCs[mapNum].Npc[i].setHP(ServerVars.MapNPCs[mapNum].Npc[i].getHP() - Damage);
                                    ServerVars.MapNPCs[mapNum].Npc[i].setTarget(index);
                                    ServerVars.MapNPCs[mapNum].Npc[i].setTargetType(ServerVars.TARGET_TYPE_PLAYER);
                                    SendServerData.SendNPCDmg(index, i, Damage);
                                    if (ServerVars.MapNPCs[mapNum].Npc[i].getHP() <= 0) {
                                        ServerVars.MapNPCs[mapNum].Npc[i].setHP(0);
                                        ServerVars.MapNPCs[mapNum].Npc[i].setDead(true);
                                        ServerVars.MapNPCs[mapNum].Npc[i].setSpawnWait(ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].SpawnSecs);

                                        SendServerData.SendKillNPC(index, i, true);
                                        for (int a = 1; a <= ServerVars.MaxPlayers; a++) {
                                            if (a != index) {
                                                if (ServerVars.Players[a] != null) {
                                                    if (mapNum == ServerVars.Players[a].getMap()) {
                                                        SendServerData.SendKillNPC(a, i, false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    SendServerData.SendNPCDmg(index, i, 0);
                                }
                            }
                        }
                    }
                }
                break;
            case ServerVars.DIR_RIGHT:
                for (int i = 1; i <= ServerVars.MaxMapNPCs; i++) {
                    if (ServerVars.MapNPCs[mapNum].Npc[i].getX() == x + 1 && ServerVars.MapNPCs[mapNum].Npc[i].getY() == y) {
                        if (ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].Behaviour < 2 || ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].Behaviour == 10) {
                            if (!ServerVars.MapNPCs[mapNum].Npc[i].isDead()) {
                                // Damage Algorithm
                                double diff = RTOServer.GetPlayerDamage(index) - RTOServer.GetNpcProtection(i);
                                if (diff < 0) diff = 0;

                                // Increase up to 10% for minimum damage
                                double minDam = diff - Math.round(diff * 0.1);
                                double maxDam = diff + Math.round(diff * 0.1);
                                if (maxDam < minDam) minDam = maxDam;
                                int Damage = ServerVars.Rnd.nextInt((int)maxDam - (int)minDam + 1) + (int)minDam;

                                if (Damage > 0) {
                                    ServerVars.MapNPCs[mapNum].Npc[i].setHP(ServerVars.MapNPCs[mapNum].Npc[i].getHP() - Damage);
                                    ServerVars.MapNPCs[mapNum].Npc[i].setTarget(index);
                                    ServerVars.MapNPCs[mapNum].Npc[i].setTargetType(ServerVars.TARGET_TYPE_PLAYER);
                                    SendServerData.SendNPCDmg(index, i, Damage);
                                    if (ServerVars.MapNPCs[mapNum].Npc[i].getHP() <= 0) {
                                        ServerVars.MapNPCs[mapNum].Npc[i].setHP(0);
                                        ServerVars.MapNPCs[mapNum].Npc[i].setDead(true);
                                        ServerVars.MapNPCs[mapNum].Npc[i].setSpawnWait(ServerVars.npcs[ServerVars.MapNPCs[mapNum].Npc[i].getNum()].SpawnSecs);

                                        SendServerData.SendKillNPC(index, i, true);
                                        for (int a = 1; a <= ServerVars.MaxPlayers; a++) {
                                            if (a != index) {
                                                if (ServerVars.Players[a] != null) {
                                                    if (mapNum == ServerVars.Players[a].getMap()) {
                                                        SendServerData.SendKillNPC(a, i, false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    SendServerData.SendNPCDmg(index, i, 0);
                                }
                            }
                        }
                    }
                }
                break;
        }
    }
    public static void HandleDropItem(Object object) {
        SendDropItem sendDropItem = (SendDropItem) object;

        int index = sendDropItem.index;
        int mapNum = ServerVars.Players[index].getMap();
        int invSlot = sendDropItem.invSlot;
        int x = sendDropItem.x;
        int y = sendDropItem.y;

        if (ServerVars.Players[index] != null) {
            if (ServerVars.Players[index].inventory[invSlot] != null) {
                if (ServerVars.Players[index].inventory[invSlot].getItemNum() > 0) {
                    for (int i = 1; i <= ServerVars.MaxMapItems; i++) {
                        if (ServerVars.MapItems == null) { break; }
                        if (ServerVars.MapItems[mapNum] == null) { break; }
                        if (ServerVars.MapItems[mapNum].Item == null) { break; }
                        if (ServerVars.MapItems[mapNum].Item[i] == null) { break; }
                        if (ServerVars.MapItems[mapNum].Item[i].itemNum <= 0) {
                            ServerVars.MapItems[mapNum].Item[i].itemNum = ServerVars.Players[index].inventory[invSlot].getItemNum();
                            ServerVars.MapItems[mapNum].Item[i].itemVal = ServerVars.Players[index].inventory[invSlot].getItemVal();
                            ServerVars.MapItems[mapNum].Item[i].x = x;
                            ServerVars.MapItems[mapNum].Item[i].y = y;
                            ServerVars.MapItems[mapNum].Item[i].lifeTimer = 0;

                            ServerVars.Players[index].inventory[invSlot].setItemNum(0);
                            ServerVars.Players[index].inventory[invSlot].setItemVal(0);

                            if (ServerVars.Players[index].getWeapon() == invSlot) {
                                ServerVars.Players[index].setWeapon(0);
                            } else if (ServerVars.Players[index].getOffhand() == invSlot) {
                                ServerVars.Players[index].setOffhand(0);
                            } else if (ServerVars.Players[index].getArmor() == invSlot) {
                                ServerVars.Players[index].setArmor(0);
                            } else if (ServerVars.Players[index].getHelmet() == invSlot) {
                                ServerVars.Players[index].setHelmet(0);
                            } else if (ServerVars.Players[index].getAcc1() == invSlot) {
                                ServerVars.Players[index].setAcc1(0);
                            } else if (ServerVars.Players[index].getAcc2() == invSlot) {
                                ServerVars.Players[index].setAcc2(0);
                            }

                            SendServerData.SendPlayerData(index, index);

                            SendServerData.SendMapItems(mapNum);
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void HandlePickUpItem(Object object) {
        SendPickUpItem sendPickUpItem = (SendPickUpItem) object;

        System.out.println("HandlePickUpItem");

        int index = sendPickUpItem.index;

        for (int i = 1; i <= ServerVars.MaxMapItems; i++) {
            int mapNum = ServerVars.Players[index].getMap();
            if (ServerVars.MapItems == null) { break; }
            if (ServerVars.MapItems[mapNum] == null) { break; }
            if (ServerVars.MapItems[mapNum].Item == null) { break; }
            if (ServerVars.MapItems[mapNum].Item[i] == null) { break; }
            int x = ServerVars.MapItems[mapNum].Item[i].x;
            int y = ServerVars.MapItems[mapNum].Item[i].y;
            int pX = ServerVars.Players[index].getX();
            int pY = ServerVars.Players[index].getY();
            boolean itemPlaced = false;
            if (x == pX && y == pY) {
                System.out.println("Standing on item");
                if (ServerVars.Items[ServerVars.MapItems[mapNum].Item[i].itemNum].isStackable == 1) {
                    for (int a = 1; a <= 60; a++) {
                        if (ServerVars.Players[index].inventory[a].getItemNum() == ServerVars.MapItems[mapNum].Item[i].itemNum) {
                            ServerVars.Players[index].inventory[a].setItemVal(ServerVars.Players[index].inventory[a].getItemVal() + ServerVars.MapItems[mapNum].Item[i].itemVal);

                            ServerVars.MapItems[mapNum].Item[i] = new MapItem();

                            SendServerData.SendMapItems(mapNum);
                            SendServerData.SendPlayerData(index, index);
                            itemPlaced = true;
                            break;
                        }
                    }
                    if (!itemPlaced) {
                        for (int a = 1; a <= 60; a++) {
                            if (ServerVars.Players[index].inventory[a].getItemNum() <= 0) {
                                ServerVars.Players[index].inventory[a].setItemNum(ServerVars.MapItems[mapNum].Item[i].itemNum);
                                ServerVars.Players[index].inventory[a].setItemVal(ServerVars.MapItems[mapNum].Item[i].itemVal);

                                ServerVars.MapItems[mapNum].Item[i] = new MapItem();

                                SendServerData.SendMapItems(mapNum);
                                SendServerData.SendPlayerData(index, index);
                                break;
                            }
                        }
                    }
                } else {
                    for (int a = 1; a <= 60; a++) {
                        if (ServerVars.Players[index].inventory[a].getItemNum() <= 0) {
                            ServerVars.Players[index].inventory[a].setItemNum(ServerVars.MapItems[mapNum].Item[i].itemNum);
                            ServerVars.Players[index].inventory[a].setItemVal(ServerVars.MapItems[mapNum].Item[i].itemVal);

                            ServerVars.MapItems[mapNum].Item[i] = new MapItem();

                            SendServerData.SendMapItems(mapNum);
                            SendServerData.SendPlayerData(index, index);
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void HandleUsePoint(Object object) {
        SendUsePoint sendUsePoint = (SendUsePoint) object;

        int index = sendUsePoint.index;
        int statNum = sendUsePoint.statNum;

        if (ServerVars.Players[index].getPoints() > 0) {
            switch (statNum) {
                case 1: // STR
                    ServerVars.Players[index].setSTR(ServerVars.Players[index].getSTR() + 1);
                    ServerVars.Players[index].setPoints(ServerVars.Players[index].getPoints() - 1);
                    break;
                case 2: // DEF
                    ServerVars.Players[index].setDEF(ServerVars.Players[index].getDEF() + 1);
                    ServerVars.Players[index].setPoints(ServerVars.Players[index].getPoints() - 1);
                    break;
                case 3: // VIT
                    ServerVars.Players[index].setVIT(ServerVars.Players[index].getVIT() + 1);
                    ServerVars.Players[index].setPoints(ServerVars.Players[index].getPoints() - 1);
                    break;
                case 4: // AGI
                    ServerVars.Players[index].setAGI(ServerVars.Players[index].getAGI() + 1);
                    ServerVars.Players[index].setPoints(ServerVars.Players[index].getPoints() - 1);
                    break;
                case 5: // MAG
                    ServerVars.Players[index].setMAG(ServerVars.Players[index].getMAG() + 1);
                    ServerVars.Players[index].setPoints(ServerVars.Players[index].getPoints() - 1);
                    break;
            }
        }

        int pSTR = ServerVars.Players[index].getSTR();
        int pDEF = ServerVars.Players[index].getDEF();
        int pVIT = ServerVars.Players[index].getVIT();
        int pAGI = ServerVars.Players[index].getAGI();
        int pMAG = ServerVars.Players[index].getMAG();
        if (ServerVars.Players[index].getWeapon() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getWeapon()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
        }
        if (ServerVars.Players[index].getOffhand() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getOffhand()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
        }
        if (ServerVars.Players[index].getArmor() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getArmor()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
        }
        if (ServerVars.Players[index].getHelmet() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getHelmet()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
        }
        if (ServerVars.Players[index].getAcc1() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getAcc1()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
        }
        if (ServerVars.Players[index].getAcc2() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getAcc2()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
            pVIT = pVIT + ServerVars.Items[itemNum].VIT;
            pAGI = pAGI + ServerVars.Items[itemNum].AGI;
            pMAG = pMAG + ServerVars.Items[itemNum].MAG;
        }

        ServerVars.Players[index].setMaxHP((pVIT * 2) * (pSTR / 2));
        ServerVars.Players[index].setMaxMP((pMAG * 2) * (pDEF / 2));

        SendServerData.SendVital(index);
        SaveData.SaveAccount(ServerVars.Accounts[index]);
    }
    public static void HandleSendMessage(Object object) {
        SendMessage sendMessage = (SendMessage) object;
        int index = sendMessage.index;
        int type = sendMessage.type;
        String msg = sendMessage.msg;

        if (index <= 0 || index > ServerVars.MaxPlayers) { return; }
        if (type < ServerVars.MESSAGE_TYPE_MAP || type > ServerVars.MESSAGE_TYPE_WHISPER) { return; }
        if (msg == null || msg.isEmpty()) { return; }

        switch (type) {
            case ServerVars.MESSAGE_TYPE_MAP:
                int mapNum = ServerVars.Players[index].getMap();
                for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                    if (ServerVars.Players[i] != null) {
                        if (ServerVars.Players[i].getMap() == mapNum) {
                            SendServerData.SendMessage(i, type, ServerVars.Players[index].getName() + ": " + msg);
                        }
                    }
                }
                break;
            case ServerVars.MESSAGE_TYPE_GLOBAL:
                msg = msg.substring(1);
                for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                    if (ServerVars.Players[i] != null) {
                        SendServerData.SendMessage(i, type, ServerVars.Players[index].getName() + ": " + msg);
                    }
                }
                break;
            case ServerVars.MESSAGE_TYPE_WHISPER:
                msg = msg.substring(1);
                String[] whisper = msg.split(" ");
                for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                    if (ServerVars.Players[i] != null) {
                        System.out.println(whisper[0]);
                        if (ServerVars.Players[i].getName() != null && !ServerVars.Players[i].getName().isEmpty()) {
                            if (ServerVars.Players[i].getName().equals(whisper[0])) {
                                String whisp = msg.substring(msg.indexOf(" ") + 1);
                                SendServerData.SendMessage(i, type, ServerVars.Players[index].getName() + "->" + ServerVars.Players[i].getName() + ": " + whisp);
                                SendServerData.SendMessage(index, type, ServerVars.Players[index].getName() + "->" + ServerVars.Players[i].getName() + ": " + whisp);
                                break;
                            }
                        }
                    }
                }
                break;
        }
    }
}
