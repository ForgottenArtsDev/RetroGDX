package com.forgottenartsstudios.data;

import com.forgottenartsstudios.helpers.ServerVars;
import com.forgottenartsstudios.networking.packetdata.SendServerData;

/**
 * Created by forgo on 10/7/2017.
 */

public class General {
    public static void createWarrior(int index, int charSlot) {
        ServerVars.Accounts[index].chars[1].setLevel(1);

        ServerVars.Accounts[index].chars[charSlot].setHP((Jobs.Warrior.getVIT() * 2) * (Jobs.Warrior.getSTR() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMP((Jobs.Warrior.getMAG() * 2) * (Jobs.Warrior.getDEF() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMaxHP((Jobs.Warrior.getVIT() * 2) * (Jobs.Warrior.getSTR() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMaxMP((Jobs.Warrior.getMAG() * 2) * (Jobs.Warrior.getDEF() / 2));

        ServerVars.Accounts[index].chars[charSlot].setEXP(0);
        ServerVars.Accounts[index].chars[charSlot].setNextLVL(getNextLevel(0));

        ServerVars.Accounts[index].chars[charSlot].setSTR(Jobs.Warrior.getSTR());
        ServerVars.Accounts[index].chars[charSlot].setDEF(Jobs.Warrior.getDEF());
        ServerVars.Accounts[index].chars[charSlot].setVIT(Jobs.Warrior.getVIT());
        ServerVars.Accounts[index].chars[charSlot].setAGI(Jobs.Warrior.getAGI());
        ServerVars.Accounts[index].chars[charSlot].setMAG(Jobs.Warrior.getMAG());

        ServerVars.Accounts[index].chars[charSlot].setWeapon(0);
        ServerVars.Accounts[index].chars[charSlot].setOffhand(0);
        ServerVars.Accounts[index].chars[charSlot].setArmor(0);
        ServerVars.Accounts[index].chars[charSlot].setHelmet(0);
        ServerVars.Accounts[index].chars[charSlot].setAcc1(0);
        ServerVars.Accounts[index].chars[charSlot].setAcc2(0);

        ServerVars.Accounts[index].chars[charSlot].setMap(ServerVars.START_MAP);
        ServerVars.Accounts[index].chars[charSlot].setX(ServerVars.START_X);
        ServerVars.Accounts[index].chars[charSlot].setY(ServerVars.START_Y);
        ServerVars.Accounts[index].chars[charSlot].setDir(ServerVars.DIR_DOWN);

        ServerVars.Accounts[index].chars[charSlot].setPoints(0);
    }
    public static void createWizard(int index, int charSlot) {
        ServerVars.Accounts[index].chars[1].setLevel(1);

        ServerVars.Accounts[index].chars[charSlot].setHP((Jobs.Wizard.getVIT() * 2) * (Jobs.Wizard.getSTR() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMP((Jobs.Wizard.getMAG() * 2) * (Jobs.Wizard.getDEF() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMaxHP((Jobs.Wizard.getVIT() * 2) * (Jobs.Wizard.getSTR() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMaxMP((Jobs.Wizard.getMAG() * 2) * (Jobs.Wizard.getDEF() / 2));

        ServerVars.Accounts[index].chars[charSlot].setEXP(0);
        ServerVars.Accounts[index].chars[charSlot].setNextLVL(getNextLevel(0));

        ServerVars.Accounts[index].chars[charSlot].setSTR(Jobs.Wizard.getSTR());
        ServerVars.Accounts[index].chars[charSlot].setDEF(Jobs.Wizard.getDEF());
        ServerVars.Accounts[index].chars[charSlot].setVIT(Jobs.Wizard.getVIT());
        ServerVars.Accounts[index].chars[charSlot].setAGI(Jobs.Wizard.getAGI());
        ServerVars.Accounts[index].chars[charSlot].setMAG(Jobs.Wizard.getMAG());

        ServerVars.Accounts[index].chars[charSlot].setWeapon(0);
        ServerVars.Accounts[index].chars[charSlot].setOffhand(0);
        ServerVars.Accounts[index].chars[charSlot].setArmor(0);
        ServerVars.Accounts[index].chars[charSlot].setHelmet(0);
        ServerVars.Accounts[index].chars[charSlot].setAcc1(0);
        ServerVars.Accounts[index].chars[charSlot].setAcc2(0);

        ServerVars.Accounts[index].chars[charSlot].setMap(ServerVars.START_MAP);
        ServerVars.Accounts[index].chars[charSlot].setX(ServerVars.START_X);
        ServerVars.Accounts[index].chars[charSlot].setY(ServerVars.START_Y);
        ServerVars.Accounts[index].chars[charSlot].setDir(ServerVars.DIR_DOWN);

        ServerVars.Accounts[index].chars[charSlot].setPoints(0);

        ServerVars.Accounts[index].chars[charSlot].spells[1].setSpellNum(1);
        ServerVars.Accounts[index].chars[charSlot].spells[1].setCastTime(ServerVars.Spells[1].CastTime);
        ServerVars.Accounts[index].chars[charSlot].spells[1].setCoolDown(ServerVars.Spells[1].CoolDown);
        ServerVars.Accounts[index].chars[charSlot].spells[1].setCastTimeTimer(0);
        ServerVars.Accounts[index].chars[charSlot].spells[1].setCoolDownTimer(0);
    }
    public static void createCleric(int index, int charSlot) {
        ServerVars.Accounts[index].chars[1].setLevel(1);

        ServerVars.Accounts[index].chars[charSlot].setHP((Jobs.Cleric.getVIT() * 2) * (Jobs.Cleric.getSTR() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMP((Jobs.Cleric.getMAG() * 2) * (Jobs.Cleric.getDEF() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMaxHP((Jobs.Cleric.getVIT() * 2) * (Jobs.Cleric.getSTR() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMaxMP((Jobs.Cleric.getMAG() * 2) * (Jobs.Cleric.getDEF() / 2));

        ServerVars.Accounts[index].chars[charSlot].setEXP(0);
        ServerVars.Accounts[index].chars[charSlot].setNextLVL(getNextLevel(0));

        ServerVars.Accounts[index].chars[charSlot].setSTR(Jobs.Cleric.getSTR());
        ServerVars.Accounts[index].chars[charSlot].setDEF(Jobs.Cleric.getDEF());
        ServerVars.Accounts[index].chars[charSlot].setVIT(Jobs.Cleric.getVIT());
        ServerVars.Accounts[index].chars[charSlot].setAGI(Jobs.Cleric.getAGI());
        ServerVars.Accounts[index].chars[charSlot].setMAG(Jobs.Cleric.getMAG());

        ServerVars.Accounts[index].chars[charSlot].setWeapon(0);
        ServerVars.Accounts[index].chars[charSlot].setOffhand(0);
        ServerVars.Accounts[index].chars[charSlot].setArmor(0);
        ServerVars.Accounts[index].chars[charSlot].setHelmet(0);
        ServerVars.Accounts[index].chars[charSlot].setAcc1(0);
        ServerVars.Accounts[index].chars[charSlot].setAcc2(0);

        ServerVars.Accounts[index].chars[charSlot].setMap(ServerVars.START_MAP);
        ServerVars.Accounts[index].chars[charSlot].setX(ServerVars.START_X);
        ServerVars.Accounts[index].chars[charSlot].setY(ServerVars.START_Y);
        ServerVars.Accounts[index].chars[charSlot].setDir(ServerVars.DIR_DOWN);

        ServerVars.Accounts[index].chars[charSlot].setPoints(0);
    }
    public static void createRanger(int index, int charSlot) {
        ServerVars.Accounts[index].chars[1].setLevel(1);

        ServerVars.Accounts[index].chars[charSlot].setHP((Jobs.Ranger.getVIT() * 2) * (Jobs.Ranger.getSTR() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMP((Jobs.Ranger.getMAG() * 2) * (Jobs.Ranger.getDEF() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMaxHP((Jobs.Ranger.getVIT() * 2) * (Jobs.Ranger.getSTR() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMaxMP((Jobs.Ranger.getMAG() * 2) * (Jobs.Ranger.getDEF() / 2));

        ServerVars.Accounts[index].chars[charSlot].setEXP(0);
        ServerVars.Accounts[index].chars[charSlot].setNextLVL(getNextLevel(0));

        ServerVars.Accounts[index].chars[charSlot].setSTR(Jobs.Ranger.getSTR());
        ServerVars.Accounts[index].chars[charSlot].setDEF(Jobs.Ranger.getDEF());
        ServerVars.Accounts[index].chars[charSlot].setVIT(Jobs.Ranger.getVIT());
        ServerVars.Accounts[index].chars[charSlot].setAGI(Jobs.Ranger.getAGI());
        ServerVars.Accounts[index].chars[charSlot].setMAG(Jobs.Ranger.getMAG());

        ServerVars.Accounts[index].chars[charSlot].setWeapon(0);
        ServerVars.Accounts[index].chars[charSlot].setOffhand(0);
        ServerVars.Accounts[index].chars[charSlot].setArmor(0);
        ServerVars.Accounts[index].chars[charSlot].setHelmet(0);
        ServerVars.Accounts[index].chars[charSlot].setAcc1(0);
        ServerVars.Accounts[index].chars[charSlot].setAcc2(0);

        ServerVars.Accounts[index].chars[charSlot].setMap(ServerVars.START_MAP);
        ServerVars.Accounts[index].chars[charSlot].setX(ServerVars.START_X);
        ServerVars.Accounts[index].chars[charSlot].setY(ServerVars.START_Y);
        ServerVars.Accounts[index].chars[charSlot].setDir(ServerVars.DIR_DOWN);

        ServerVars.Accounts[index].chars[charSlot].setPoints(0);
    }
    public static void createRogue(int index, int charSlot) {
        ServerVars.Accounts[index].chars[1].setLevel(1);

        ServerVars.Accounts[index].chars[charSlot].setHP((Jobs.Rogue.getVIT() * 2) * (Jobs.Rogue.getSTR() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMP((Jobs.Rogue.getMAG() * 2) * (Jobs.Rogue.getDEF() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMaxHP((Jobs.Rogue.getVIT() * 2) * (Jobs.Rogue.getSTR() / 2));
        ServerVars.Accounts[index].chars[charSlot].setMaxMP((Jobs.Rogue.getMAG() * 2) * (Jobs.Rogue.getDEF() / 2));

        ServerVars.Accounts[index].chars[charSlot].setEXP(0);
        ServerVars.Accounts[index].chars[charSlot].setNextLVL(getNextLevel(0));

        ServerVars.Accounts[index].chars[charSlot].setSTR(Jobs.Rogue.getSTR());
        ServerVars.Accounts[index].chars[charSlot].setDEF(Jobs.Rogue.getDEF());
        ServerVars.Accounts[index].chars[charSlot].setVIT(Jobs.Rogue.getVIT());
        ServerVars.Accounts[index].chars[charSlot].setAGI(Jobs.Rogue.getAGI());
        ServerVars.Accounts[index].chars[charSlot].setMAG(Jobs.Rogue.getMAG());

        ServerVars.Accounts[index].chars[charSlot].setWeapon(0);
        ServerVars.Accounts[index].chars[charSlot].setOffhand(0);
        ServerVars.Accounts[index].chars[charSlot].setArmor(0);
        ServerVars.Accounts[index].chars[charSlot].setHelmet(0);
        ServerVars.Accounts[index].chars[charSlot].setAcc1(0);
        ServerVars.Accounts[index].chars[charSlot].setAcc2(0);

        ServerVars.Accounts[index].chars[charSlot].setMap(ServerVars.START_MAP);
        ServerVars.Accounts[index].chars[charSlot].setX(ServerVars.START_X);
        ServerVars.Accounts[index].chars[charSlot].setY(ServerVars.START_Y);
        ServerVars.Accounts[index].chars[charSlot].setDir(ServerVars.DIR_DOWN);

        ServerVars.Accounts[index].chars[charSlot].setPoints(0);
    }

    public static void SpawnAllNpcs() {
        ServerVars.MapNPCs = new mapData_Struct[ServerVars.MaxMaps + 1];
        for (int i = 1; i <= ServerVars.MaxMaps; i++)
        {
            ServerVars.MapNPCs[i] = new mapData_Struct();
            ServerVars.MapNPCs[i].Npc = new MapNPC[ServerVars.MaxMapNPCs + 1];
            for (int z = 1; z <= ServerVars.MaxMapNPCs; z++) {
                ServerVars.MapNPCs[i].Npc[z] = new MapNPC();
            }
            cacheMapNpcs(i);
        }
    }
    public static boolean TileIsOpen(int mapNum, int X, int Y) {
        // CHECK FOR PLAYERS ON MAP //
        //if (Static.PlayersOnMap[mapNum])
        //{
        for (int LoopI = 1; LoopI <= ServerVars.MaxPlayers; LoopI++) {
            if (ServerVars.Players[LoopI] != null) {
                if (ServerVars.Players[LoopI].getMap() == mapNum) {
                    if (ServerVars.Players[LoopI].getX() == X) {
                        if (ServerVars.Players[LoopI].getY() == Y) {
                            return false;
                        }
                    }
                }
            }
        }
        //}

        // CHECK FOR NPCS ON MAP //
        for (int LoopI = 1; LoopI <= ServerVars.MaxMapNPCs; LoopI++) {
            if (ServerVars.MapNPCs[mapNum].Npc[LoopI].getNum() > 0) {
                if (ServerVars.MapNPCs[mapNum].Npc[LoopI].getX() == X) {
                    if (ServerVars.MapNPCs[mapNum].Npc[LoopI].getY() == Y) {
                        if (!ServerVars.MapNPCs[mapNum].Npc[LoopI].isDead()) {
                            return false;
                        }
                    }
                }
            }
        }

        // CHECK TILE TYPE //
        if (ServerVars.mapData[mapNum].Tile[X][Y].Type != ServerVars.TILE_TYPE_WALKABLE) {
            if (ServerVars.mapData[mapNum].Tile[X][Y].Type != ServerVars.TILE_TYPE_NPCSPAWN) {
                if (ServerVars.mapData[mapNum].Tile[X][Y].Type != ServerVars.TILE_TYPE_ITEM) {
                    if (ServerVars.mapData[mapNum].Tile[X][Y].Type != ServerVars.TILE_TYPE_WARP) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    public static void cacheMapNpcs(int mapNum) {
        for (int X = 0; X <= ServerVars.mapData[mapNum].MaxX - 1; X++) {
            for (int Y = 0; Y <= ServerVars.mapData[mapNum].MaxY - 1; Y++) {
                if (ServerVars.mapData[mapNum].Tile[X][Y].Type == ServerVars.TILE_TYPE_NPCSPAWN) {
                    if (ServerVars.mapData[mapNum].Tile[X][Y].Data1 >= 0) {
                        for (int mapNpcNum = 1; mapNpcNum <= ServerVars.MaxMapNPCs; mapNpcNum++) {
                            if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum() == 0) {
                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setName(ServerVars.npcs[ServerVars.mapData[mapNum].Tile[X][Y].Data1].Name);
                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setNum(ServerVars.mapData[mapNum].Tile[X][Y].Data1);
                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setSprite(ServerVars.npcs[ServerVars.mapData[mapNum].Tile[X][Y].Data1].Sprite);
                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setDir(ServerVars.mapData[mapNum].Tile[X][Y].Data2);
                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setHP(ServerVars.npcs[ServerVars.mapData[mapNum].Tile[X][Y].Data1].Health);
                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setMaxHP(ServerVars.npcs[ServerVars.mapData[mapNum].Tile[X][Y].Data1].Health);
                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setX(X);
                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setY(Y);
                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setSpawnX(X);
                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setSpawnY(Y);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public static void PlayerWarp(int Index, int mapNum, int X, int Y) {
        int oldMap;

        // Check for subscript out of range
        if ((mapNum <= 0) | (mapNum > ServerVars.MaxMaps)) {
            return;
        }

        // Check if you are out of bounds
        if (X > ServerVars.mapData[mapNum].MaxX) { X = ServerVars.mapData[mapNum].MaxX; }
        if (Y > ServerVars.mapData[mapNum].MaxY) { Y = ServerVars.mapData[mapNum].MaxY; }

        // Save old map to send erase player data to
        oldMap = ServerVars.Players[Index].getMap();

        if (oldMap != mapNum)
        {
            // REASON I DONT NEED THIS YET IS BECAUSE WHEN I USE SENDJOINMAP,
            // I SEND TO ALL NOT JUST THE PLAYERS ON THE MAP.
            // THIS UPDATES ALL THE CLIENTS SO NO NEED TO LEAVE MAP.
            // HOW EVER THIS WILL BE ADDED IN.
            //SendLeaveMap(Index, OldMap)
        }

        // Set Player Position //
        ServerVars.Players[Index].setMap(mapNum);
        ServerVars.Players[Index].setX(X);
        ServerVars.Players[Index].setY(Y);

        SendServerData.SendPlayerWarp(Index, mapNum, X, Y);

        for (int z = 1; z <= ServerVars.MaxMapNPCs; z++) {
            SendServerData.SendSpawnNpc(ServerVars.Players[Index].getMap(), z, 0);
        }

        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            if (ServerVars.Players[i] != null) {
                if (ServerVars.Players[i].getMap() == mapNum) {
                    SendServerData.SendPlayerData(Index, i);
                    SendServerData.SendPlayerData(i, Index);
                } else if (ServerVars.Players[i].getMap() == oldMap) {
                    SendServerData.SendPlayerData(Index, i);
                }
            }
        }

        //sendData.sendCheckMap(Index, mapNum, ServerVars.mapData[mapNum].Revision);
    }
    public static void SpawnNpc(int MapNpcNum, int MapNum) {
        int NpcNum;
        int x, y;
        boolean Spawned;

        // Check for subscript out of range
        if (MapNpcNum <= 0 || MapNpcNum > ServerVars.MaxMapNPCs || MapNum <= 0 || MapNum > ServerVars.MaxMaps) {
            return;
        }

        Spawned = false;

        NpcNum = ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].getNum();
        if (NpcNum > 0) {
            ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].setNum(NpcNum);
            ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].setTarget(0);
            ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].setTargetType(0);

            ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].setHP(ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].getMaxHP());

            int dir = ServerVars.Rnd.nextInt(4 + 1);
            ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].setDir(dir);

            x = ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].getSpawnX();
            y = ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].getSpawnY();

            // Check if the tile is walkable
            if (ServerVars.mapData[MapNum].Tile[x][y].Type == ServerVars.TILE_TYPE_WALKABLE) {
                ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].setX(x);
                ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].setY(y);
                Spawned = true;
            }
        }

        //Didn' t spawn, so now we 'll just try to find a free tile
        if (!Spawned) {
            for (y = 0; y <= ServerVars.mapData[MapNum].MaxY; y++) {
                for (x = 0; x <= ServerVars.mapData[MapNum].MaxX; x++) {
                    if (ServerVars.mapData[MapNum].Tile[x][y].Type == ServerVars.TILE_TYPE_WALKABLE) {
                        ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].setX(x);
                        ServerVars.MapNPCs[MapNum].Npc[MapNpcNum].setY(y);
                        Spawned = true;
                    }
                }
            }
        }

        //If we succeeded in spawning then send it to everyone
        if (!Spawned) {
            SendServerData.SendSpawnNpc(MapNum, MapNpcNum, 0);
        }
    }

    public static int getNextLevel(int index) {
        int level;
        if (index > 0) {
            level = ServerVars.Players[index].getLevel() + 1;
        } else {
            level = 2;
        }
        return ((500 * (level * level)) - (500 * level));
    }
}
