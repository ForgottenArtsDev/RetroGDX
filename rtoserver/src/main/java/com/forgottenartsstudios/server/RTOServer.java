package com.forgottenartsstudios.server;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.forgottenartsstudios.data.AccountData;
import com.forgottenartsstudios.data.General;
import com.forgottenartsstudios.data.Inventory_Struct;
import com.forgottenartsstudios.data.Item_Struct;
import com.forgottenartsstudios.data.MapItem;
import com.forgottenartsstudios.data.MapNPC;
import com.forgottenartsstudios.data.NPC_Struct;
import com.forgottenartsstudios.data.SaveData;
import com.forgottenartsstudios.data.Shop_Struct;
import com.forgottenartsstudios.data.mapData_Struct;
import com.forgottenartsstudios.networking.packetdata.HandleServerData;
import com.forgottenartsstudios.data.LoadData;
import com.forgottenartsstudios.data.Player;
import com.forgottenartsstudios.helpers.ServerVars;
import com.forgottenartsstudios.networking.packetdata.SendServerData;
import com.forgottenartsstudios.networking.packets.AccountNotFound;
import com.forgottenartsstudios.networking.packets.AccountRegistered;
import com.forgottenartsstudios.networking.packets.ChooseChar;
import com.forgottenartsstudios.networking.packets.Connect;
import com.forgottenartsstudios.networking.packets.CreateChar;
import com.forgottenartsstudios.networking.packets.DisconnectPlayer;
import com.forgottenartsstudios.networking.packets.ItemBoughtMsg;
import com.forgottenartsstudios.networking.packets.KeepAliveCheck;
import com.forgottenartsstudios.networking.packets.Login;
import com.forgottenartsstudios.networking.packets.MovePlayer;
import com.forgottenartsstudios.networking.packets.NewAccount;
import com.forgottenartsstudios.networking.packets.NotEnoughGoldMsg;
import com.forgottenartsstudios.networking.packets.Packet;
import com.forgottenartsstudios.networking.packets.PlayerData;
import com.forgottenartsstudios.networking.packets.SendBuyItem;
import com.forgottenartsstudios.networking.packets.SendDropItem;
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
import com.forgottenartsstudios.networking.packets.SendPickUpItem;
import com.forgottenartsstudios.networking.packets.SendPlayerDmg;
import com.forgottenartsstudios.networking.packets.SendPlayerWarp;
import com.forgottenartsstudios.networking.packets.SendRespawnNPC;
import com.forgottenartsstudios.networking.packets.SendSearch;
import com.forgottenartsstudios.networking.packets.SendShop;
import com.forgottenartsstudios.networking.packets.SendTryAttack;
import com.forgottenartsstudios.networking.packets.SendUseItem;
import com.forgottenartsstudios.networking.packets.SendUsePoint;
import com.forgottenartsstudios.networking.packets.SendVital;
import com.forgottenartsstudios.networking.packets.WarpCheck;
import com.sun.org.apache.xpath.internal.operations.Variable;

import java.util.Random;

public class RTOServer extends ApplicationAdapter {
    public static Server server = new Server(204800, 204800);
    public static Connection[] serverConnections;

    public void create () {

    }
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // your code here
    }
    public static void main (String[] args) throws Exception {

        serverWindow.create();
        ServerVars.serverWindow.setVisible(true);

        ServerVars.MapNPCs = new mapData_Struct[ServerVars.MaxMaps + 1];
        ServerVars.MapItems = new mapData_Struct[ServerVars.MaxMaps + 1];
        for (int i = 1; i <= ServerVars.MaxMaps; i++) {
            ServerVars.MapNPCs[i] = new mapData_Struct();
            ServerVars.MapItems[i] = new mapData_Struct();

        }
        for (int i = 1; i <= ServerVars.MaxMaps; i++) {
            ServerVars.MapNPCs[i].Npc = new MapNPC[ServerVars.MaxMapNPCs + 1];
            ServerVars.MapItems[i].Item = new MapItem[ServerVars.MaxMapItems + 1];
            for (int a = 1; a <= ServerVars.MaxMapNPCs; a++) {
                ServerVars.MapNPCs[i].Npc[a] = new MapNPC();
                ServerVars.MapItems[i].Item[a] = new MapItem();
            }
        }

        serverWindow.svrMonitor.append("Loading maps.." + "\n");
        LoadData.loadMaps();
        serverWindow.svrMonitor.append("Loading npcs.." + "\n");
        LoadData.loadNPCs();
        serverWindow.svrMonitor.append("Loading items.." + "\n");
        LoadData.loadItems();
        serverWindow.svrMonitor.append("Loading shops.." + "\n");
        LoadData.loadShops();
        serverWindow.svrMonitor.append("Spawning NPCs.." + "\n");
        ServerVars.MapNPCs = new mapData_Struct[ServerVars.MaxMaps + 1];
        General.SpawnAllNpcs();
        /*
        ServerVars.mapItems = new mapData_Struct[ServerVars.maxMaps + 1];
        General.SpawnAllItems();
        serverWindow.svrMonitor.setCaretPosition(serverWindow.svrMonitor.getDocument().getLength());

        registerClasses();
        initPlayers(); */

        ServerVars.Accounts = new AccountData[ServerVars.MaxPlayers + 1];
        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            ServerVars.Accounts[i] = new AccountData();

            ServerVars.Accounts[i].setID("");
            ServerVars.Accounts[i].setPW("");
            ServerVars.Accounts[i].setCID(0);

            ServerVars.Accounts[i].chars = new Player[3 + 1];
            for (int z = 1; z <= 3; z++) {
                ServerVars.Accounts[i].chars[z] = new Player();
                ServerVars.Accounts[i].chars[z].inventory = new Inventory_Struct[60 + 1];
                for (int a = 1; a <= 60; a++) {
                    ServerVars.Accounts[i].chars[z].inventory[a] = new Inventory_Struct();
                    ServerVars.Accounts[i].chars[z].inventory[a].setItemNum(0);
                    ServerVars.Accounts[i].chars[z].inventory[a].setItemVal(0);
                }
            }
        }

        initPackets();

        server.start();
        server.bind(4001, 4002);

        serverWindow.svrMonitor.append("Server initialized" + "\n");
        serverWindow.svrMonitor.append("Listening for connections.." + "\n");
        serverWindow.svrMonitor.setCaretPosition(serverWindow.svrMonitor.getDocument().getLength());

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof Packet) {
                    checkPackets(object, connection);
                }
            }
        });
        serverLoop();
    }

    private static final long UpdateTime_KeepAlive = 5000;
    private static final long UpdateTime_KeepAliveCount = 1000;
    private static final long UpdateTime_GameAI = 500;
    private static final long UpdateTime_SavePlayers = 60000 * 5;
    private static final long UpdateTime_RespawnNPCs = 1000;
    private static final long UpdateTime_RegenPlayers = 5000;
    private static final long UpdateTime_ClearMapItems = 1000;

    private static long LastUpdateTime_KeepAlive;
    private static long LastUpdateTime_KeepAliveCount;
    private static long LastUpdateTime_GameAI;
    private static long LastUpdateTime_SavePlayers;
    private static long LastUpdateTime_RespawnNPCs;
    private static long LastUpdateTime_RegenPlayers;
    private static long LastUpdateTime_ClearMapItems;

    private static boolean UpdateNpcMovement = false;
    private static boolean UpdateNpcRecover = false;

    private static void serverLoop() {
        new Thread(new Runnable()
        {
            public void run()
            {
                boolean UpdatePlayer = false;
                boolean UpdateNpc = false;

                while (ServerVars.ServerRunning) {
                    long tickCount = System.currentTimeMillis();

                    int MovementSpeed = 4;

                    for (int mapNum = 1; mapNum <= ServerVars.MaxMaps; mapNum++) {
                        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                            if (ServerVars.Players[i] != null) {
                                switch (ServerVars.Players[i].getDir()) {
                                    case ServerVars.DIR_UP:
                                        ServerVars.Players[i].setOffsetY(ServerVars.Players[i].getOffsetY() - MovementSpeed);
                                        System.out.println("OffsetY: " + ServerVars.Players[i].getOffsetY());
                                        if (ServerVars.Players[i].getOffsetY() < 0) {
                                            ServerVars.Players[i].setOffsetY(0);
                                            ServerVars.Players[i].setMoving(0);
                                        }
                                        break;
                                    case ServerVars.DIR_DOWN:
                                        ServerVars.Players[i].setOffsetY(ServerVars.Players[i].getOffsetY() + MovementSpeed);
                                        if (ServerVars.Players[i].getOffsetY() > 0) {
                                            ServerVars.Players[i].setOffsetY(0);
                                            ServerVars.Players[i].setMoving(0);
                                        }
                                        break;
                                    case ServerVars.DIR_LEFT:
                                        ServerVars.Players[i].setOffsetX(ServerVars.Players[i].getOffsetX() - MovementSpeed);
                                        if (ServerVars.Players[i].getOffsetX() < 0) {
                                            ServerVars.Players[i].setOffsetX(0);
                                            ServerVars.Players[i].setMoving(0);
                                        }
                                        break;
                                    case ServerVars.DIR_RIGHT:
                                        ServerVars.Players[i].setOffsetX(ServerVars.Players[i].getOffsetX() + MovementSpeed);
                                        if (ServerVars.Players[i].getOffsetX() > 0) {
                                            ServerVars.Players[i].setOffsetX(0);
                                            ServerVars.Players[i].setMoving(0);
                                        }
                                        break;
                                }
                            }
                        }
                    }

                    if (LastUpdateTime_KeepAlive < tickCount) {

                        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                            if (ServerVars.Accounts[i] != null) {
                                ServerVars.Accounts[i].setKeepAlive(false);
                                SendServerData.SendKeepAliveCheck(i);
                            }
                        }

                        LastUpdateTime_KeepAlive = tickCount + UpdateTime_KeepAlive;
                    }

                    if (LastUpdateTime_ClearMapItems < tickCount) {
                        for (int i = 1; i <= ServerVars.MaxMaps; i++) {
                            for (int a = 1; a <= ServerVars.MaxMapItems; a++) {
                                if (ServerVars.MapItems != null) {
                                    if (ServerVars.MapItems[i] != null) {
                                        if (ServerVars.MapItems[i].Item != null) {
                                            if (ServerVars.MapItems[i].Item[a] != null) {
                                                if (ServerVars.MapItems[i].Item[a].itemNum > 0) {
                                                    if (ServerVars.MapItems[i].Item[a].lifeTimer < 600000) { // 10 minutes
                                                        ServerVars.MapItems[i].Item[a].lifeTimer += 1000;
                                                        if (ServerVars.MapItems[i].Item[a].lifeTimer >= 600000) {
                                                            ServerVars.MapItems[i].Item[a] = new MapItem();
                                                            SendServerData.SendMapItems(i);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        LastUpdateTime_ClearMapItems = tickCount + UpdateTime_ClearMapItems;
                    }

                    // HP & MP Regen //
                    if (LastUpdateTime_RegenPlayers < tickCount) {
                        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                            if (ServerVars.Players[i] != null) {
                                if (ServerVars.Players[i].getHP() < ServerVars.Players[i].getMaxHP() || ServerVars.Players[i].getMP() < ServerVars.Players[i].getMaxMP()) {
                                    // HP //
                                    ServerVars.Players[i].setHP(ServerVars.Players[i].getHP() + 10);
                                    if (ServerVars.Players[i].getHP() > ServerVars.Players[i].getMaxHP()) {
                                        ServerVars.Players[i].setHP(ServerVars.Players[i].getMaxHP());
                                    }
                                    // MP //
                                    ServerVars.Players[i].setMP(ServerVars.Players[i].getMP() + 5);
                                    if (ServerVars.Players[i].getMP() > ServerVars.Players[i].getMaxMP()) {
                                        ServerVars.Players[i].setMP(ServerVars.Players[i].getMaxMP());
                                    }
                                    // Update Player //
                                    SendServerData.SendPlayerData(i, i);
                                }
                            }
                        }
                        LastUpdateTime_RegenPlayers = tickCount + UpdateTime_RegenPlayers;
                    }

                    // Respawn NPCs //
                    if (LastUpdateTime_RespawnNPCs < tickCount) {
                        for (int i = 1; i <= ServerVars.MaxMaps; i++) {
                            for (int a = 1; a <= ServerVars.MaxMapNPCs; a++) {
                                if (ServerVars.MapNPCs[i].Npc[a].getSpawnWait() > 0) {
                                    if (ServerVars.MapNPCs[i].Npc[a].isDead()) {
                                        if (ServerVars.MapNPCs[i].Npc[a].getHP() <= 0) {
                                            ServerVars.MapNPCs[i].Npc[a].setSpawnWait(ServerVars.MapNPCs[i].Npc[a].getSpawnWait() - 1);
                                            if (ServerVars.MapNPCs[i].Npc[a].getSpawnWait() <= 0) {
                                                int npcNum = ServerVars.MapNPCs[i].Npc[a].getNum();
                                                if (npcNum > 0) {
                                                    ServerVars.MapNPCs[i].Npc[a].setHP(ServerVars.MapNPCs[i].Npc[a].getMaxHP());
                                                    ServerVars.MapNPCs[i].Npc[a].setDead(false);
                                                    ServerVars.MapNPCs[i].Npc[a].setX(ServerVars.MapNPCs[i].Npc[a].getSpawnX());
                                                    ServerVars.MapNPCs[i].Npc[a].setY(ServerVars.MapNPCs[i].Npc[a].getSpawnY());
                                                    ServerVars.MapNPCs[i].Npc[a].setTargetType(ServerVars.TARGET_TYPE_NONE);
                                                    ServerVars.MapNPCs[i].Npc[a].setTarget(0);
                                                    SendServerData.SendRespawnNPC(i, a);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        LastUpdateTime_RespawnNPCs = tickCount + UpdateTime_RespawnNPCs;
                    }

                    if (LastUpdateTime_KeepAliveCount < tickCount) {

                        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                            if (ServerVars.Accounts[i].getCID() > 0) {
                                if (!ServerVars.Accounts[i].isKeepAlive()) {
                                    ServerVars.Accounts[i].setKeepCount(ServerVars.Accounts[i].getKeepCount() + 1);
                                    if (ServerVars.Accounts[i].getKeepCount() >= 5) {
                                        if (ServerVars.Accounts[i].getCID() > 0) {
                                            SaveData.SaveAccount(ServerVars.Accounts[i]);

                                            serverWindow.svrMonitor.append("Account (" + ServerVars.Accounts[i].getID() + ") Saved" + "\n");
                                            SendServerData.SendDisconnectPlayer(i);

                                            serverWindow.svrMonitor.append("Account (" + ServerVars.Accounts[i].getID() + ") Disconnected" + "\n");
                                        }

                                        ServerVars.Accounts[i] = new AccountData();
                                        ServerVars.Players[i] = new Player();
                                    }
                                }
                            }
                        }

                        LastUpdateTime_KeepAliveCount = tickCount + UpdateTime_KeepAliveCount;
                    }

                    // Save Players //
                    if (LastUpdateTime_SavePlayers < tickCount) {
                        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                            if (ServerVars.Accounts[i] != null) {
                                if (ServerVars.Accounts[i].getCID() > 0) {
                                    SaveData.SaveAccount(ServerVars.Accounts[i]);
                                }
                            }
                        }
                        serverWindow.svrMonitor.append("Saved players" + "\n");
                        LastUpdateTime_SavePlayers = tickCount + UpdateTime_SavePlayers;
                    }

                    // Npc AI //
                    if (LastUpdateTime_GameAI < tickCount) {
                        UpdateNpcMovement = true;
                        LastUpdateTime_GameAI = tickCount + UpdateTime_GameAI;
                        UpdateNpc = true;
                    }

                    // Update NPC AI //
                    if (UpdateNpc) {
                        UpdateNpcAI(tickCount);
                        UpdateNpc = false;
                    }
                }
            }
        }).start();
    }

    private static void initPackets() {
        server.getKryo().register(Packet.class);
        server.getKryo().register(Connect.class);
        server.getKryo().register(NewAccount.class);
        server.getKryo().register(Login.class);
        server.getKryo().register(SendLogin.class);
        server.getKryo().register(AccountData.class);
        server.getKryo().register(Player.class);
        server.getKryo().register(Player[].class);
        server.getKryo().register(CreateChar.class);
        server.getKryo().register(AccountNotFound.class);
        server.getKryo().register(AccountRegistered.class);
        server.getKryo().register(ChooseChar.class);
        server.getKryo().register(PlayerData.class);
        server.getKryo().register(MovePlayer.class);
        server.getKryo().register(SendNPCSpawn.class);
        server.getKryo().register(KeepAliveCheck.class);
        server.getKryo().register(DisconnectPlayer.class);
        server.getKryo().register(SendNPCDead.class);
        server.getKryo().register(SendNPCDir.class);
        server.getKryo().register(SendNPCMove.class);
        server.getKryo().register(SendPlayerWarp.class);
        server.getKryo().register(SendVital.class);
        server.getKryo().register(WarpCheck.class);
        server.getKryo().register(SendItems.class);
        server.getKryo().register(Item_Struct.class);
        server.getKryo().register(Shop_Struct.class);
        server.getKryo().register(SendSearch.class);
        server.getKryo().register(SendShop.class);
        server.getKryo().register(int[].class);
        server.getKryo().register(SendBuyItem.class);
        server.getKryo().register(Inventory_Struct.class);
        server.getKryo().register(Inventory_Struct[].class);
        server.getKryo().register(SendInventory.class);
        server.getKryo().register(SendUseItem.class);
        server.getKryo().register(ItemBoughtMsg.class);
        server.getKryo().register(SendTryAttack.class);
        server.getKryo().register(SendKillNPC.class);
        server.getKryo().register(SendNPCDmg.class);
        server.getKryo().register(SendRespawnNPC.class);
        server.getKryo().register(SendMapNPCs.class);
        server.getKryo().register(MapNPC.class);
        server.getKryo().register(MapNPC[].class);
        server.getKryo().register(SendNPCXP.class);
        server.getKryo().register(SendDropItem.class);
        server.getKryo().register(SendMapItems.class);
        server.getKryo().register(MapItem.class);
        server.getKryo().register(MapItem[].class);
        server.getKryo().register(SendPickUpItem.class);
        server.getKryo().register(NotEnoughGoldMsg.class);
        server.getKryo().register(SendUsePoint.class);
        server.getKryo().register(SendPlayerDmg.class);
        server.getKryo().register(SendMessage.class);
    }
    private static void checkPackets(Object object, Connection connection) {
        if (object instanceof Connect) { HandleServerData.HandleConnect(object); }
        if (object instanceof NewAccount) { HandleServerData.HandleNewAccount(object, connection); }
        if (object instanceof Login) { HandleServerData.HandleLogin(object, connection); }
        if (object instanceof CreateChar) { HandleServerData.HandleCreateChar(object, connection); }
        if (object instanceof ChooseChar) { HandleServerData.HandleChooseChar(object, connection); }
        if (object instanceof MovePlayer) { HandleServerData.HandleMovePlayer(object); }
        if (object instanceof KeepAliveCheck) { HandleServerData.HandleKeepAliveCheck(object); }
        if (object instanceof WarpCheck) { HandleServerData.HandleWarpCheck(object); }
        if (object instanceof SendSearch) { HandleServerData.HandleSearch(object); }
        if (object instanceof SendBuyItem) { HandleServerData.HandleBuyItem(object); }
        if (object instanceof SendUseItem) { HandleServerData.HandleUseItem(object); }
        if (object instanceof SendTryAttack) { HandleServerData.HandleTryAttack(object); }
        if (object instanceof SendDropItem) { HandleServerData.HandleDropItem(object); }
        if (object instanceof SendPickUpItem) { HandleServerData.HandlePickUpItem(object); }
        if (object instanceof SendUsePoint) { HandleServerData.HandleUsePoint(object); }
        if (object instanceof SendMessage) { HandleServerData.HandleSendMessage(object); }
    }

    private static void UpdateNpcAI(long tickCount) {
        int N;
        //int TickCount = Environment.TickCount;
        int NpcNum;
        int Target = 0;
        int TargetType = 0;
        boolean DidWalk = false;
        int TargetX = 0;
        int TargetY = 0;
        boolean hasTarget;

        ///////////////////////////////////////
        ////// Loop Through All The Maps //////
        ///////////////////////////////////////
        for (int mapNum = 1; mapNum <= ServerVars.MaxMaps; mapNum++) {
            //if (Static.PlayersOnMap[mapNum])
            //{
            //TickCount = GetTickCount;
            for (int mapNpcNum = 1; mapNpcNum <= ServerVars.MaxMapNPCs; mapNpcNum++) {

                if (UpdateNpcMovement) {
                    NpcNum = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum();

                    /////////////////////////////////////////////////
                    ////// This is used for ATTACKING ON SIGHT //////
                    /////////////////////////////////////////////////

                    // Make sure theres a npc with the map
                    if (NpcNum > 0) {
                        // If the npc is a attack on sight, search for a player on the map
                        if ((ServerVars.npcs[NpcNum].Behaviour == ServerVars.NPC_BEHAVIOUR_ATTACK_ROAMING) || (ServerVars.npcs[NpcNum].Behaviour == ServerVars.NPC_BEHAVIOUR_ATTACK_STANDING)) {
                            /////////////////////////////
                            ////// PLAYER IN RANGE //////
                            /////////////////////////////
                            for (int LoopI = 1; LoopI <= ServerVars.MaxPlayers; LoopI++) {
                                if (ServerVars.Players[LoopI] != null) {
                                    if (ServerVars.Players[LoopI].getMap() == mapNum && ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getTarget() == 0) {
                                        // ****** Set Range ******
                                        N = ServerVars.npcs[NpcNum].Range;

                                        int DistanceX = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() - ServerVars.Players[LoopI].getX();
                                        int DistanceY = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() - ServerVars.Players[LoopI].getY();

                                        // Make sure we get a positive value
                                        if (DistanceX < 0) {
                                            DistanceX = DistanceX * -1;
                                        }
                                        if (DistanceY < 0) {
                                            DistanceY = DistanceY * -1;
                                        }

                                        // Are they in range?  if so GET'M!
                                        if ((DistanceX <= N) && (DistanceY <= N)) {
                                            if (ServerVars.npcs[NpcNum].Behaviour == ServerVars.NPC_BEHAVIOUR_ATTACK_ROAMING || ServerVars.npcs[NpcNum].Behaviour == ServerVars.NPC_BEHAVIOUR_ATTACK_STANDING) {
                                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTargetType(ServerVars.TARGET_TYPE_PLAYER);
                                                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTarget(LoopI);
                                            }
                                        }
                                    }
                                }
                            }

                            //////////////////////////
                            ////// NPC IN RANGE //////
                            //////////////////////////
                            /*if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getTarget() == 0) {
                                for (int LoopI = 1; LoopI <= ServerVars.MaxMapNPCs; LoopI++) {
                                    if (LoopI != mapNpcNum) {
                                        if (ServerVars.MapNPCs[mapNum].Npc[LoopI].getNum() > 0) {

                                            N = ServerVars.npcs[NpcNum].Range;

                                            int DistanceX = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() - ServerVars.MapNPCs[mapNum].Npc[LoopI].getX();
                                            int DistanceY = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() - ServerVars.MapNPCs[mapNum].Npc[LoopI].getY();

                                            // Make sure we get a positive value
                                            if (DistanceX < 0) DistanceX = DistanceX * -1;
                                            if (DistanceY < 0) DistanceY = DistanceY * -1;

                                            // Are they in range?  if so GET'M!
                                            if ((DistanceX <= N) && (DistanceY <= N)) {
                                                if (ServerVars.npcs[NpcNum].Behaviour == ServerVars.NPC_BEHAVIOUR_ATTACK_ROAMING) {
                                                    if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum() != NpcNum) {
                                                        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTargetType(ServerVars.TARGET_TYPE_NPC);
                                                        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTarget(LoopI);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }*/
                        }
                    }

                    hasTarget = false;

                    /////////////////////////////////////////////
                    // This is used for NPC walking/targeting //
                    /////////////////////////////////////////////

                    // Make sure theres a npc with the map
                    if (NpcNum > 0) {
                        // ****** Walk/Target ******
                        Target = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getTarget();
                        TargetType = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getTargetType();

                        // Check to see if its time for the npc to walk
                        if (ServerVars.npcs[NpcNum].Behaviour != ServerVars.NPC_BEHAVIOUR_SELLER_ROAMING || ServerVars.npcs[NpcNum].Behaviour != ServerVars.NPC_BEHAVIOUR_SELLER_STANDING) {
                            if (TargetType == ServerVars.TARGET_TYPE_PLAYER) {
                                if (Target > 0) {
                                    if (ServerVars.Players[Target].getMap() == mapNum) {
                                        DidWalk = false;
                                        hasTarget = true;
                                        TargetY = ServerVars.Players[Target].getY();
                                        TargetX = ServerVars.Players[Target].getX();
                                    } else {
                                        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTargetType(ServerVars.TARGET_TYPE_NONE);
                                        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTarget(ServerVars.TARGET_TYPE_NONE);
                                    }
                                }
                            } else if (TargetType == ServerVars.TARGET_TYPE_NPC) {
                                if (Target > 0) {
                                    if (ServerVars.MapNPCs[mapNum].Npc[Target].getNum() > 0) {
                                        DidWalk = false;
                                        hasTarget = true;
                                        TargetY = ServerVars.MapNPCs[mapNum].Npc[Target].getY();
                                        TargetX = ServerVars.MapNPCs[mapNum].Npc[Target].getX();
                                    } else {
                                        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTargetType(ServerVars.TARGET_TYPE_NONE);
                                        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTarget(ServerVars.TARGET_TYPE_NONE);
                                    }
                                }
                            }

                            if (hasTarget) {
                                if (!DidWalk) {
                                    DidWalk = AI_NpcMove(mapNum, mapNpcNum, TargetX, TargetY);
                                }
                                if (!DidWalk) {
                                    AI_NpcCollision(mapNum, mapNpcNum, Target);
                                }
                            } else {
                                if (ServerVars.Rnd.nextInt(4 + 1) <= 2) {
                                    int Dir = (ServerVars.Rnd.nextInt(4 + 1));
                                    if (CanNpcMove(mapNum, mapNpcNum, Dir)) {
                                        NpcMove(mapNum, mapNpcNum, Dir);
                                    }
                                }
                            }
                        }
                    }

                    /////////////////////////////////////////////
                    // This is used for npcs to attack targets //
                    /////////////////////////////////////////////
                    if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getNum() > 0) {
                        Target = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getTarget();
                        TargetType = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getTargetType();

                        if (Target > 0) {
                            switch (TargetType) {
                                case ServerVars.TARGET_TYPE_PLAYER:
                                    if (ServerVars.Players[Target].getMap() == mapNum) {
                                        NpcAttackPlayer(mapNpcNum, Target);
                                    } else {
                                        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTarget(0);        // Player left map or game, set target to 0
                                        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTargetType(0);    // clear
                                    }
                                    break;
                                case ServerVars.TARGET_TYPE_NPC:
                                    if (ServerVars.MapNPCs[mapNum].Npc[Target].getNum() > 0) {

                                    } else {
                                        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTarget(0);        // Player left map or game, set target to 0
                                        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setTargetType(0);    // clear
                                    }
                                    break;
                            }
                        }
                    }
                }

                // DO EVENTS //
                //Application.DoEvents();
            }

            //}
        }

        UpdateNpcMovement = false;
        UpdateNpcRecover = false;
    }
    private static void AI_NpcCollision(int mapNum, int mapNpcNum, int Target) {
        boolean keepMoving = true;
        if (Target <= 0 || Target > ServerVars.MaxPlayers) { return; }
        if (mapNum <= 0 || mapNum > ServerVars.MaxMaps) { return; }
        if (mapNpcNum <= 0 || mapNpcNum > ServerVars.MaxMapNPCs) { return; }
        if (ServerVars.Players == null) { return; }
        if (ServerVars.Players[Target] == null) { return; }
        if (ServerVars.MapNPCs == null) { return; }
        if (ServerVars.MapNPCs[mapNum] == null) { return; }
        if (ServerVars.MapNPCs[mapNum].Npc == null) { return; }
        if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum] == null) { return; }
        // Check if we can't move and if player is behind something and if we can just switch dirs
        if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() - 1 == ServerVars.Players[Target].getX() && ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() == ServerVars.Players[Target].getY())
        {
            if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getDir() != ServerVars.DIR_LEFT) NpcDir(mapNum, mapNpcNum, ServerVars.DIR_LEFT);
            keepMoving = false;
        }

        if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() + 1 == ServerVars.Players[Target].getX() && ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() == ServerVars.Players[Target].getY())
        {
            if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getDir() != ServerVars.DIR_RIGHT) NpcDir(mapNum, mapNpcNum, ServerVars.DIR_RIGHT);
            keepMoving = false;
        }

        if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() == ServerVars.Players[Target].getX() && ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() - 1 == ServerVars.Players[Target].getY())
        {
            if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getDir() != ServerVars.DIR_UP) NpcDir(mapNum, mapNpcNum, ServerVars.DIR_UP);
            keepMoving = false;
        }

        if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() == ServerVars.Players[Target].getX() && ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() + 1 == ServerVars.Players[Target].getY())
        {
            if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getDir() != ServerVars.DIR_DOWN) NpcDir(mapNum, mapNpcNum, ServerVars.DIR_DOWN);
            keepMoving = false;
        }

        // We could not move so player must be behind something, walk randomly.
        if (keepMoving) {
            Random rnd = new Random(2);
            if (rnd.nextInt() == 1) {
                int Dir = (byte) (ServerVars.Rnd.nextInt(4 + 1));
                if (CanNpcMove(mapNum, mapNpcNum, Dir)) {
                    NpcMove(mapNum, mapNpcNum, Dir);
                }
            }
        }
    }
    private static void NpcDir(int mapNum, int mapNpcNum, int Dir) {
        // Checks //
        if (mapNum <= 0 || mapNum > ServerVars.MaxMaps - 1 || mapNpcNum <= 0 || mapNpcNum > ServerVars.MaxMapNPCs || Dir < ServerVars.DIR_UP || Dir > ServerVars.DIR_RIGHT) {
            return;
        }

        // Set Values
        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setDir(Dir);

        // Move Npc //
        SendServerData.SendNPCDir(mapNum, mapNpcNum);
    }
    private static boolean AI_NpcMove(int mapNum, int mapNpcNum, int TargetX, int TargetY) {
        // Lets move the npc //
        //ServerVars.Rnd = new Random(4);
        switch (ServerVars.Rnd.nextInt(4 + 1)) {
            case 1:
                ////// NORTH //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() > TargetY) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_UP)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_UP);
                        return true;
                    }
                }

                ////// SOUTH //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() < TargetY) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_DOWN)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_DOWN);
                        return true;
                    }
                }

                ////// WEST //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() > TargetX) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_RIGHT)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_RIGHT);
                        return true;
                    }
                }

                ////// EAST //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() < TargetX) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_LEFT)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_LEFT);
                        return true;
                    }
                }
                break;


            case 2:

                ////// EAST //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() < TargetX) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_RIGHT)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_RIGHT);
                        return true;
                    }
                }

                ////// WEST //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() > TargetX) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_LEFT)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_LEFT);
                        return true;
                    }
                }

                ////// SOUTH //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() < TargetY) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_DOWN)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_DOWN);
                        return true;
                    }
                }

                ////// NORTH //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() > TargetY) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_UP)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_UP);
                        return true;
                    }
                }

                break;

            case 3:

                ////// SOUTH //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() < TargetY) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_DOWN)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_DOWN);
                        return true;
                    }
                }

                ////// NORTH //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() > TargetY) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_UP)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_UP);
                        return true;
                    }
                }

                ////// EAST //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() < TargetX) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_RIGHT)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_RIGHT);
                        return true;
                    }
                }

                ////// WEST //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() > TargetX) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_LEFT)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_LEFT);
                        return true;
                    }
                }

                break;

            case 4:

                ////// WEST //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() > TargetX) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_LEFT)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_LEFT);
                        return true;
                    }
                }

                ////// EAST //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() < TargetX) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_RIGHT)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_RIGHT);
                        return true;
                    }
                }

                ////// NORTH //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() > TargetY) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_UP)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_UP);
                        return true;
                    }
                }

                ////// SOUTH //////
                if (ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() < TargetY) {
                    if (CanNpcMove(mapNum, mapNpcNum, ServerVars.DIR_DOWN)) {
                        NpcMove(mapNum, mapNpcNum, ServerVars.DIR_DOWN);
                        return true;
                    }
                }

                break;
        }

        return false;
    }
    private static void NpcMove(int mapNum, int mapNpcNum, int Dir) {
        // Check for subscript out of range
        if (mapNum <= 0 || mapNum > ServerVars.MaxMaps - 1) return;

        if (mapNpcNum <= 0 || mapNpcNum > ServerVars.MaxMapNPCs) return;

        ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setDir(Dir);

        switch (Dir)
        {
            case ServerVars.DIR_UP:
                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setY(ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() - 1); break;
            case ServerVars.DIR_DOWN:
                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setY(ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY() + 1); break;
            case ServerVars.DIR_LEFT:
                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setX(ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() - 1); break;
            case ServerVars.DIR_RIGHT:
                ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].setX(ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX() + 1); break;
        }

        // Move Npc //
        SendServerData.SendNpcMove(mapNum, mapNpcNum, 1);
    }
    private static boolean CanNpcMove(int mapNum, int mapNpcNum, int Dir) {
        int NewX = 0;
        int NewY = 0;

        // Check for subscript out of range
        if (mapNum <= 0 || mapNum > ServerVars.MaxMaps || mapNpcNum <= 0 || mapNpcNum > ServerVars.MaxMapNPCs || Dir < ServerVars.DIR_UP || Dir > ServerVars.DIR_RIGHT) {
            //Log "Subscript Out of Range: Sub CanNpcMove", General '//\\LOGLINE//\\
            return false;
        }

        boolean canMove = true;
        int X = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getX();
        int Y = ServerVars.MapNPCs[mapNum].Npc[mapNpcNum].getY();

        switch (Dir) {
            case ServerVars.DIR_UP:
                if (Y > 0) {
                    NewX = X;
                    NewY = Y - 1;
                } else {
                    canMove = false;
                }
                break;
            case ServerVars.DIR_DOWN:
                if (Y < ServerVars.mapData[mapNum].MaxY) {
                    NewX = X;
                    NewY = Y + 1;
                } else {
                    canMove = false;
                }
                break;
            case ServerVars.DIR_LEFT:
                if (X > 0) {
                    NewX = X - 1;
                    NewY = Y;
                } else {
                    canMove = false;
                }
                break;
            case ServerVars.DIR_RIGHT:
                if (X < ServerVars.mapData[mapNum].MaxX) {
                    NewX = X + 1;
                    NewY = Y;
                } else {
                    canMove = false;
                }
                break;
        }

        if (NewX < 0) { canMove = false; }
        if (NewY < 0) { canMove = false; }
        if (NewX > ServerVars.mapData[mapNum].MaxX - 1) { canMove = false; }
        if (NewY > ServerVars.mapData[mapNum].MaxY - 1)  {canMove = false; }

        ////// Check Boundries //////
        if (!canMove) {
            //Log "Npc_Boundry_Check = " & False, CodeTracker
            return false;
        }

        // Check Map Tiles //
        switch (ServerVars.mapData[mapNum].Tile[NewX][NewY].Type) {
            case ServerVars.TILE_TYPE_BLOCKED:
                return false;
            case ServerVars.TILE_TYPE_NPCAVOID:
                return false;
            case ServerVars.TILE_TYPE_WARP:
                return false;
        }

        // Check Players //
        for (int i = 1; i <= 100; i++) {
            if (ServerVars.Players[i] != null) {
                if (ServerVars.Players[i].getMap() == mapNum) {
                    if (ServerVars.Players[i].getX() == NewX) {
                        if (ServerVars.Players[i].getY() == NewY) {
                            return false;
                        }
                    }
                }
            }
        }

        // Check Npcs //
        for (int i = 1; i <= ServerVars.MaxMapNPCs; i++) {
            if (i != mapNpcNum) {
                if (ServerVars.MapNPCs[mapNum].Npc[i].getSprite() > 0) {
                    if (ServerVars.MapNPCs[mapNum].Npc[i].getX() == NewX) {
                        if (ServerVars.MapNPCs[mapNum].Npc[i].getY() == NewY) {
                            return false;
                        }
                    }
                }
            }
        }

        ////// Can Move //////
        return true;

    }
    private static boolean CanNpcAttackPlayer(int Attacker, int Victim) {
        // Prevent subscript out of range
        if (Attacker <= 0 || Attacker > ServerVars.MaxMapNPCs) {
            return false;
        }

        // set
        int mapNum = ServerVars.Players[Victim].getMap();
        int npcNum = ServerVars.MapNPCs[mapNum].Npc[Attacker].getNum();

        // Make sure the npc isn't already dead
        if (ServerVars.MapNPCs[mapNum].Npc[Attacker].getHP() <= 0) {
            return false;
        }

        // Make sure npcs dont attack more then once a second
        if (System.currentTimeMillis() < ServerVars.MapNPCs[mapNum].Npc[Attacker].getAttackTimer() + 1000) {
            return false;
        }

        if (npcNum > 0) {
            int X = 0;
            int Y = 0;
            switch (ServerVars.MapNPCs[mapNum].Npc[Attacker].getDir()) {
                case ServerVars.DIR_UP:
                    X = ServerVars.MapNPCs[mapNum].Npc[Attacker].getX();
                    Y = ServerVars.MapNPCs[mapNum].Npc[Attacker].getY() - 1;
                    break;
                case ServerVars.DIR_DOWN:
                    X = ServerVars.MapNPCs[mapNum].Npc[Attacker].getX();
                    Y = ServerVars.MapNPCs[mapNum].Npc[Attacker].getY() + 1;
                    break;
                case ServerVars.DIR_LEFT:
                    X = ServerVars.MapNPCs[mapNum].Npc[Attacker].getX() - 1;
                    Y = ServerVars.MapNPCs[mapNum].Npc[Attacker].getY();
                    break;
                case ServerVars.DIR_RIGHT:
                    X = ServerVars.MapNPCs[mapNum].Npc[Attacker].getX() + 1;
                    Y = ServerVars.MapNPCs[mapNum].Npc[Attacker].getY();
                    break;
            }
            if ((ServerVars.Players[Victim].getY() == Y) && (ServerVars.Players[Victim].getX() == X)) {
                ServerVars.MapNPCs[mapNum].Npc[Attacker].setAttackTimer((int)System.currentTimeMillis());
                return true;
            }
        }

        return false;
    }
    private static void NpcAttackPlayer(int Attacker, int Victim) {
        if (CanNpcAttackPlayer(Attacker, Victim))
        {
            int mapNum = ServerVars.Players[Victim].getMap();
            int npcNum = ServerVars.MapNPCs[mapNum].Npc[Attacker].getNum();

            // Damage Algorithm
            double diff = GetNpcDamage(npcNum) - GetPlayerProtection(Victim);
            if (diff < 0) diff = 0;

            // Increase up to 10% for minimum damage
            double minDam = diff - Math.round(diff * 0.1);
            double maxDam = diff + Math.round(diff * 0.1);
            if (maxDam < minDam) minDam = maxDam;
            int Damage = ServerVars.Rnd.nextInt((int)maxDam - (int)minDam + 1) + (int)minDam;
            if (Damage > 0)
            {
                NpcDamagePlayer(Attacker, Victim, Damage);
                //ServerTCP.SendPlayerMsg(Victim, "You got hit for " + Damage + " points of damage");
                SendServerData.SendPlayerDmg(Victim, Damage);
                SendServerData.SendPlayerData(Victim, Victim);
            }
        }
    }
    private static void NpcDamagePlayer(int Attacker, int Victim, int Damage) {
        // Check for subscript out of range
        if (Attacker <= 0 || Attacker > ServerVars.MaxMapNPCs)
            return;

        // set
        int mapNum = ServerVars.Players[Victim].getMap();
        int npcNum = ServerVars.MapNPCs[mapNum].Npc[Attacker].getNum();

        if (Damage >= ServerVars.Players[Victim].getHP())
        {
            // Clear Npc Target
            ServerVars.MapNPCs[mapNum].Npc[Attacker].setTarget(0);
            ServerVars.MapNPCs[mapNum].Npc[Attacker].setTargetType(0);

            // Erase Map Npc
            ErasePlayer(Victim);
        }
        else
        {
            // Do Damage
            ServerVars.Players[Victim].setHP(ServerVars.Players[Victim].getHP() - Damage);
            SendServerData.SendVital(Victim);
        }

    }
    private static void ErasePlayer(int Victim) {
        // Revive
        ServerVars.Players[Victim].setHP(ServerVars.Players[Victim].getMaxHP());
        ServerVars.Players[Victim].setMP(ServerVars.Players[Victim].getMaxMP());

        // Respawn
        General.PlayerWarp(Victim, ServerVars.START_MAP, ServerVars.START_X, ServerVars.START_Y);

        // Take 10% of XP cause they punk-ass died
        int expLoss = (int)(ServerVars.Players[Victim].getEXP() * (10.0f/100.0f));
        ServerVars.Players[Victim].setEXP(ServerVars.Players[Victim].getEXP() - expLoss);

        // Send Vitals
        SendServerData.SendPlayerData(Victim, Victim);

        // Refresh
        for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
            if (ServerVars.Players[i] != null) {
                if (ServerVars.Players[Victim] != null) {
                    if (i != Victim) {
                        if (ServerVars.Players[i].getMap() == ServerVars.Players[Victim].getMap()) {
                            SendServerData.SendPlayerData(Victim, i);
                        }
                    }
                }
            }
        }
    }
    public static int GetNpcDamage(int NpcNum) {
        // ****** Clear Data******
        int GetNpcDamage = 0;

        // ****** Subscript Out Of Range ******
        if (NpcNum <= 0 || NpcNum > ServerVars.MaxNPCs)
            return 0;

        int Damage = 2;

        int nSTR = ServerVars.npcs[NpcNum].STR;
        if (ServerVars.npcs[NpcNum].weapon > 0) {
            int itemNum = ServerVars.npcs[NpcNum].weapon;
            nSTR = nSTR + ServerVars.Items[itemNum].STR;
        }
        if (ServerVars.npcs[NpcNum].offhand > 0) {
            int itemNum = ServerVars.npcs[NpcNum].weapon;
            nSTR = nSTR + ServerVars.Items[itemNum].STR;
        }
        if (ServerVars.npcs[NpcNum].armor > 0) {
            int itemNum = ServerVars.npcs[NpcNum].weapon;
            nSTR = nSTR + ServerVars.Items[itemNum].STR;
        }
        if (ServerVars.npcs[NpcNum].helmet > 0) {
            int itemNum = ServerVars.npcs[NpcNum].weapon;
            nSTR = nSTR + ServerVars.Items[itemNum].STR;
        }

        // ****** Initial Damage ******
        GetNpcDamage = Damage + (int)(((Damage) * 2.5) * (nSTR));
        return GetNpcDamage;
    }
    public static int GetNpcProtection(int NpcNum) {

        // ****** Clear Data******
        int GetNpcProtection = 0;

        // ****** Subscript Out Of Range ******
        if (NpcNum <= 0 || NpcNum > ServerVars.MaxMapNPCs)
            return 0;

        int Defence = 2;

        int nDEF = ServerVars.npcs[NpcNum].DEF;
        if (ServerVars.npcs[NpcNum].weapon > 0) {
            int itemNum = ServerVars.npcs[NpcNum].weapon;
            nDEF = nDEF + ServerVars.Items[itemNum].DEF;
        }
        if (ServerVars.npcs[NpcNum].offhand > 0) {
            int itemNum = ServerVars.npcs[NpcNum].weapon;
            nDEF = nDEF + ServerVars.Items[itemNum].DEF;
        }
        if (ServerVars.npcs[NpcNum].armor > 0) {
            int itemNum = ServerVars.npcs[NpcNum].weapon;
            nDEF = nDEF + ServerVars.Items[itemNum].DEF;
        }
        if (ServerVars.npcs[NpcNum].helmet > 0) {
            int itemNum = ServerVars.npcs[NpcNum].weapon;
            nDEF = nDEF + ServerVars.Items[itemNum].DEF;
        }

        // ****** Retrive END ******
        GetNpcProtection = Defence + (int)(((Defence) * 1.5) * (nDEF));
        return GetNpcProtection;
    }
    public static int GetPlayerDamage(int index) {
        // ****** Clear Data******
        int GetPlayerDamage;

        // Prevent subscript out of range
        if (index <= 0 || index > ServerVars.MaxPlayers)
            return 0;

        int Damage = 2;

        // ****** Initial Damage ******
        int pSTR = ServerVars.Players[index].getSTR();
        if (ServerVars.Players[index].getWeapon() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getWeapon()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
        }
        if (ServerVars.Players[index].getOffhand() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getOffhand()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
        }
        if (ServerVars.Players[index].getArmor() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getArmor()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
        }
        if (ServerVars.Players[index].getHelmet() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getHelmet()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
        }
        if (ServerVars.Players[index].getAcc1() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getAcc1()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
        }
        if (ServerVars.Players[index].getAcc2() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getAcc2()].getItemNum();
            pSTR = pSTR + ServerVars.Items[itemNum].STR;
        }
        GetPlayerDamage = Damage + (int)(((Damage) * 2.5) * (pSTR));
        return GetPlayerDamage;
    }
    public static int GetPlayerProtection(int index) {
        // ****** Clear Data******
        int GetPlayerProtection = 0;

        // Prevent subscript out of range
        if (index <= 0 || index > 100)
            return 0;

        int Defence = 2;

        // ****** Initial Protection ******
        int pDEF = ServerVars.Players[index].getDEF();
        if (ServerVars.Players[index].getWeapon() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getWeapon()].getItemNum();
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
        }
        if (ServerVars.Players[index].getOffhand() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getOffhand()].getItemNum();
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
        }
        if (ServerVars.Players[index].getArmor() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getArmor()].getItemNum();
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
        }
        if (ServerVars.Players[index].getHelmet() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getHelmet()].getItemNum();
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
        }
        if (ServerVars.Players[index].getAcc1() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getAcc1()].getItemNum();
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
        }
        if (ServerVars.Players[index].getAcc2() > 0) {
            int itemNum = ServerVars.Players[index].inventory[ServerVars.Players[index].getAcc2()].getItemNum();
            pDEF = pDEF + ServerVars.Items[itemNum].DEF;
        }

        GetPlayerProtection = Defence + (int)(((Defence) * 1.5) * (pDEF));
        return GetPlayerProtection;
    }
}