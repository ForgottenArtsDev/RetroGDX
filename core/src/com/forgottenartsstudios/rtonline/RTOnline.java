package com.forgottenartsstudios.rtonline;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.forgottenartsstudios.data.AccountData;
import com.forgottenartsstudios.data.Damage_Struct;
import com.forgottenartsstudios.data.HandleClientData;
import com.forgottenartsstudios.data.Inventory_Struct;
import com.forgottenartsstudios.data.Item_Struct;
import com.forgottenartsstudios.data.MapItem;
import com.forgottenartsstudios.data.MapNPC;
import com.forgottenartsstudios.data.Message;
import com.forgottenartsstudios.data.Player;
import com.forgottenartsstudios.data.Shop_Struct;
import com.forgottenartsstudios.data.SystemMsg_Struct;
import com.forgottenartsstudios.helpers.AssetLoader;
import com.forgottenartsstudios.helpers.Variables;
import com.forgottenartsstudios.networking.packets.*;
import com.forgottenartsstudios.screens.GameScreen;

import java.io.IOException;

public class RTOnline extends Game {

	public static Client client = new Client(204800, 204800);
	
	@Override
	public void create () {
		AssetLoader.load();

        initPackets();

        new Thread(client).start();

        try {
            client.connect(5000, Variables.Server_IP, Variables.Server_Port, Variables.Server_Port + 1); //192.168.1.16
            Variables.serverOnline = true;
        } catch (IOException e) {
            Variables.serverOnline = false;
        }

        Connect cnt = new Connect();
        cnt.client_mode = Variables.Client_Mode;
        client.sendTCP(cnt);

        Variables.players = new Player[Variables.MaxPlayers + 1];
        for (int i = 1; i <= Variables.MaxPlayers; i++) {
            Variables.players[i] = new Player();
            Variables.players[i].inventory = new Inventory_Struct[60 + 1];
            for (int a = 1; a <= 60; a++) {
                Variables.players[i].inventory[a] = new Inventory_Struct();
            }
        }

        Variables.chatMessages = new Message[100 + 1];
        for (int i = 1; i <= 100; i++) {
            Variables.chatMessages[i] = new Message();
            Variables.chatMessages[i].setMsg("");
            Variables.chatMessages[i].setType(0);
        }

        Variables.DrawNPCDamage = new Damage_Struct[20 + 1];
        Variables.DrawPlayerDamage = new Damage_Struct[20 + 1];
        Variables.DrawXP = new Damage_Struct[20 + 1];
        Variables.DrawSystemMessage = new SystemMsg_Struct[20 + 1];
        Variables.DrawLevelUp = new SystemMsg_Struct[20 + 1];
        Variables.DrawHP = new Damage_Struct[20 + 1];
        for (int i = 1; i <= 20; i++) {
            Variables.DrawNPCDamage[i] = new Damage_Struct();
            Variables.DrawPlayerDamage[i] = new Damage_Struct();
            Variables.DrawXP[i] = new Damage_Struct();
            Variables.DrawSystemMessage[i] = new SystemMsg_Struct();
            Variables.DrawLevelUp[i] = new SystemMsg_Struct();
            Variables.DrawHP[i] = new Damage_Struct();
        }

        Variables.MapNPCs = new MapNPC[Variables.MaxMapNPCs + 1];
        for (int i = 1; i <= Variables.MaxMapNPCs; i++) {
            Variables.MapNPCs[i] = new MapNPC();
        }
        Variables.MapItems = new MapItem[Variables.MaxMapItems + 1];
        for (int i = 1; i <= Variables.MaxMapItems; i++) {
            Variables.MapItems[i] = new MapItem();
        }

        new Thread(client).setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                // SEND DISCONNECTION PACKET
                //SendClientData.sendLeftGame(Variables.MyIndex);
            }
        });

        client.addListener(new Listener() {
                               public void received(Connection connection, Object object) {
                                   if (object instanceof Packet) {
                                       checkPackets(object, connection);
                                   }
                               }
                           });

        setScreen(new GameScreen());

        AssetLoader.loadLogin();

        if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            Variables.Game_State = Variables.Game_State_TitleScreen;
        } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
            Variables.Game_State = Variables.Game_State_Loading;
        }
	}
	
	@Override
	public void dispose () {  }

	public static void initPackets() {
        client.getKryo().register(Packet.class);
        client.getKryo().register(Connect.class);
        client.getKryo().register(NewAccount.class);
        client.getKryo().register(Login.class);
        client.getKryo().register(SendLogin.class);
        client.getKryo().register(AccountData.class);
        client.getKryo().register(Player.class);
        client.getKryo().register(Player[].class);
        client.getKryo().register(CreateChar.class);
        client.getKryo().register(AccountNotFound.class);
        client.getKryo().register(AccountRegistered.class);
        client.getKryo().register(ChooseChar.class);
        client.getKryo().register(PlayerData.class);
        client.getKryo().register(MovePlayer.class);
        client.getKryo().register(SendNPCSpawn.class);
        client.getKryo().register(KeepAliveCheck.class);
        client.getKryo().register(DisconnectPlayer.class);
        client.getKryo().register(SendNPCDead.class);
        client.getKryo().register(SendNPCDir.class);
        client.getKryo().register(SendNPCMove.class);
        client.getKryo().register(SendPlayerWarp.class);
        client.getKryo().register(SendVital.class);
        client.getKryo().register(WarpCheck.class);
        client.getKryo().register(SendItems.class);
        client.getKryo().register(Item_Struct.class);
        client.getKryo().register(Shop_Struct.class);
        client.getKryo().register(SendSearch.class);
        client.getKryo().register(SendShop.class);
        client.getKryo().register(int[].class);
        client.getKryo().register(SendBuyItem.class);
        client.getKryo().register(Inventory_Struct.class);
        client.getKryo().register(Inventory_Struct[].class);
        client.getKryo().register(SendInventory.class);
        client.getKryo().register(SendUseItem.class);
        client.getKryo().register(ItemBoughtMsg.class);
        client.getKryo().register(SendTryAttack.class);
        client.getKryo().register(SendKillNPC.class);
        client.getKryo().register(SendNPCDmg.class);
        client.getKryo().register(SendRespawnNPC.class);
        client.getKryo().register(SendMapNPCs.class);
        client.getKryo().register(MapNPC.class);
        client.getKryo().register(MapNPC[].class);
        client.getKryo().register(SendNPCXP.class);
        client.getKryo().register(SendDropItem.class);
        client.getKryo().register(SendMapItems.class);
        client.getKryo().register(MapItem.class);
        client.getKryo().register(MapItem[].class);
        client.getKryo().register(SendPickUpItem.class);
        client.getKryo().register(NotEnoughGoldMsg.class);
        client.getKryo().register(SendUsePoint.class);
        client.getKryo().register(SendPlayerDmg.class);
        client.getKryo().register(SendMessage.class);
        client.getKryo().register(SendSystemMessage.class);
        client.getKryo().register(Color.class);
        client.getKryo().register(SendHPRegen.class);
        client.getKryo().register(SendOpenPlayerMenu.class);
    }
    public static void checkPackets(Object object, Connection connection) {
        if (object instanceof SendLogin) { HandleClientData.HandleSendLogin(object); }
        if (object instanceof AccountNotFound) { HandleClientData.HandleAccountNotFound(); }
        if (object instanceof AccountRegistered) { HandleClientData.HandleAccountRegistered(); }
        if (object instanceof PlayerData) { HandleClientData.HandlePlayerData(object); }
        if (object instanceof MovePlayer) { HandleClientData.HandleMovePlayer(object); }
        if (object instanceof SendNPCSpawn) { HandleClientData.HandleSpawnNPC(object); }
        if (object instanceof KeepAliveCheck) { HandleClientData.HandleKeepAliveCheck(object); }
        if (object instanceof DisconnectPlayer) { HandleClientData.HandleDisconnectPlayer(object); }
        if (object instanceof SendNPCDead) { HandleClientData.HandleNPCDead(object); }
        if (object instanceof SendNPCDir) { HandleClientData.HandleNPCDir(object); }
        if (object instanceof SendNPCMove) { HandleClientData.HandleNPCMove(object); }
        if (object instanceof SendPlayerWarp) { HandleClientData.HandlePlayerWarp(object); }
        if (object instanceof SendVital) { HandleClientData.HandleVital(object); }
        if (object instanceof SendItems) { HandleClientData.HandleItems(object); }
        if (object instanceof SendShop) { HandleClientData.HandleSendShop(object); }
        if (object instanceof SendInventory) { HandleClientData.HandleSendInventory(object); }
        if (object instanceof ItemBoughtMsg) { HandleClientData.HandleBoughtItemMsg(); }
        if (object instanceof SendKillNPC) { HandleClientData.HandleKillNPC(object); }
        if (object instanceof SendNPCDmg) { HandleClientData.HandleDmgNPC(object); }
        if (object instanceof SendRespawnNPC) { HandleClientData.HandleRespawnNPC(object); }
        if (object instanceof SendMapNPCs) { HandleClientData.HandleMapNPCs(object); }
        if (object instanceof SendNPCXP) { HandleClientData.HandleNPCXP(object); }
        if (object instanceof SendMapItems) { HandleClientData.HandleMapItems(object); }
        if (object instanceof NotEnoughGoldMsg) { HandleClientData.HandleNotEnoughGoldMsg(); }
        if (object instanceof SendPlayerDmg) { HandleClientData.HandlePlayerDmg(object); }
        if (object instanceof SendMessage) { HandleClientData.HandleSendMessage(object); }
        if (object instanceof SendSystemMessage) { HandleClientData.HandleSendSystemMessage(object); }
        if (object instanceof SendHPRegen) { HandleClientData.HandleHPRegen(object); }
        if (object instanceof SendOpenPlayerMenu) { HandleClientData.HandleOpenPlayerMenu(object); }
    }
}
