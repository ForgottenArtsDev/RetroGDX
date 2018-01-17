package com.forgottenartsstudios.helpers;

import com.forgottenartsstudios.data.AccountData;
import com.forgottenartsstudios.data.Item_Struct;
import com.forgottenartsstudios.data.NPC_Struct;
import com.forgottenartsstudios.data.Party;
import com.forgottenartsstudios.data.Player;
import com.forgottenartsstudios.data.Shop_Struct;
import com.forgottenartsstudios.data.Spell_Struct;
import com.forgottenartsstudios.data.mapData;
import com.forgottenartsstudios.data.mapData_Struct;

import java.util.Random;

import javax.swing.JFrame;

/**
 * Created by forgo on 10/6/2017.
 */

public class ServerVars {
    public static JFrame serverWindow;
    public static boolean ServerRunning = true;

    public static final int MaxPlayers = 100;
    public static final int MaxMaps = 200;
    public static final int MaxNPCs = 200;
    public static final int MaxItems = 200;
    public static final int MaxShops = 200;
    public static final int MaxSpells = 200;
    public static final int MaxMapNPCs = 5;
    public static final int MaxMapItems = 20;
    public static final int MaxMapSpells = 20;
    public static final int MaxParties = 100;

    public static AccountData[] Accounts = new AccountData[MaxPlayers + 1];
    public static Player[] Players = new Player[MaxPlayers + 1];
    public static mapData[] mapData = new mapData[MaxMaps + 1];
    public static mapData_Struct[] MapNPCs = new mapData_Struct[MaxMapNPCs + 1];
    public static mapData_Struct[] MapItems = new mapData_Struct[MaxMapItems + 1];
    public static mapData_Struct[] MapSpells = new mapData_Struct[MaxMapSpells + 1];
    public static NPC_Struct[] npcs = new NPC_Struct[MaxMaps + 1];
    public static Item_Struct[] Items = new Item_Struct[MaxItems + 1];
    public static Shop_Struct[] Shops = new Shop_Struct[MaxShops + 1];
    public static Spell_Struct[] Spells = new Spell_Struct[MaxSpells + 1];
    public static Party[] Parties = new Party[MaxParties + 1];
    public static Random Rnd = new Random();

    public static long tickCount;

    public static final int vitalHP = 0;
    public static final int vitalMP = 1;
    public static final int vitalSP = 2;

    public static final int DIR_UP = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;
    public static final int DIR_RIGHT = 4;

    public static final int JOB_WARRIOR = 1;
    public static final int JOB_MAGE = 2;
    public static final int JOB_CLERIC = 3;
    public static final int JOB_RANGER = 4;
    public static final int JOB_ROGUE = 5;

    public static final int TILE_TYPE_WALKABLE = 0;
    public static final int TILE_TYPE_BLOCKED = 1;
    public static final int TILE_TYPE_WARP = 2;
    public static final int TILE_TYPE_ITEM = 3;
    public static final int TILE_TYPE_NPCAVOID = 4;
    public static final int TILE_TYPE_KEY = 5;
    public static final int TILE_TYPE_KEYOPEN = 6;
    public static final int TILE_TYPE_RESOURCE = 7;
    public static final int TILE_TYPE_DOOR = 8;
    public static final int TILE_TYPE_NPCSPAWN = 9;
    public static final int TILE_TYPE_SHOP = 10;
    public static final int TILE_TYPE_BANK = 11;
    public static final int TILE_TYPE_HEAL = 12;
    public static final int TILE_TYPE_TRAP = 13;
    public static final int TILE_TYPE_SIGN = 14;

    // Spell types
    public static final int SPELL_TYPE_DAMAGE = 1;
    public static final int SPELL_TYPE_HEAL = 2;
    public static final int SPELL_TYPE_REVIVE = 3;

    // Message types
    public static final int MESSAGE_TYPE_MAP = 1;
    public static final int MESSAGE_TYPE_GLOBAL = 2;
    public static final int MESSAGE_TYPE_WHISPER = 3;
    public static final int MESSAGE_TYPE_SYSTEM = 4;
    public static final int MESSAGE_TYPE_PARTY = 5;
    public static final int MESSAGE_TYPE_DEATH = 6;

    // Party - Drop Sort Methods
    public static final int DROP_SORT_ROUNDROBIN = 1;
    public static final int DROP_SORT_FREEFORALL = 2;

    // Search types
    public static final int SEARCH_TYPE_NONE = 0;
    public static final int SEARCH_TYPE_PLAYER = 1;
    public static final int SEARCH_TYPE_NPC = 2;
    public static final int SEARCH_TYPE_ITEM = 3;

    // Item Types //
    public static final int ITEM_TYPE_NONE = 0;
    public static final int ITEM_TYPE_CURRENCY = 1;
    public static final int ITEM_TYPE_WEAPON = 2;
    public static final int ITEM_TYPE_ARMOR = 3;
    public static final int ITEM_TYPE_OFFHAND = 4;
    public static final int ITEM_TYPE_HELMET = 5;
    public static final int ITEM_TYPE_POTION = 6;

    // NPC Constants //
    public static final int NPC_BEHAVIOUR_ATTACK_ROAMING = 0;
    public static final int NPC_BEHAVIOUR_ATTACK_STANDING = 1;
    public static final int NPC_BEHAVIOUR_GUARD_ROAMING = 2;
    public static final int NPC_BEHAVIOUR_GUARD_STANDING = 3;
    public static final int NPC_BEHAVIOUR_FRIENDLY_ROAMING = 4;
    public static final int NPC_BEHAVIOUR_FRIENDLY_STANDING = 5;
    public static final int NPC_BEHAVIOUR_SELLER_ROAMING = 6;
    public static final int NPC_BEHAVIOUR_SELLER_STANDING = 7;
    public static final int NPC_BEHAVIOUR_QUEST_ROAMING = 8;
    public static final int NPC_BEHAVIOUR_QUEST_STANDING = 9;
    public static final int NPC_BEHAVIOUR_ONATTACK_ROAMING = 10;

    // Hot keys
    public static final int HOT_KEY_Q = 1;
    public static final int HOT_KEY_E = 2;

    // NPC Speeds //
    public static int NPC_SPEED;
    public static final int NPC_TOWN = 1;
    public static final int NPC_ENEMY = 2;
    public static final int NPC_AGGRESSIVE = 4;

    // Target Type constants //
    public static final int TARGET_TYPE_NONE = 0;
    public static final int TARGET_TYPE_PLAYER = 1;
    public static final int TARGET_TYPE_NPC = 2;
    public static final int NPC_TARGET_PLAYER = 3;
    public static final int NPC_TARGET_NPC = 4;

    public static final int START_MAP = 1;
    public static final int START_X = 5;
    public static final int START_Y = 5;
}
