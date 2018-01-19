package com.forgottenartsstudios.helpers;

import com.forgottenartsstudios.data.AccountData;
import com.forgottenartsstudios.data.Damage_Struct;
import com.forgottenartsstudios.data.Item_Struct;
import com.forgottenartsstudios.data.MapItem;
import com.forgottenartsstudios.data.MapNPC;
import com.forgottenartsstudios.data.MapSpell;
import com.forgottenartsstudios.data.MapTiles;
import com.forgottenartsstudios.data.Message;
import com.forgottenartsstudios.data.Party;
import com.forgottenartsstudios.data.Player;
import com.forgottenartsstudios.data.Shop_Struct;
import com.forgottenartsstudios.data.Spell_Struct;
import com.forgottenartsstudios.data.SystemMsg_Struct;
import com.forgottenartsstudios.data.TempStats;
import com.forgottenartsstudios.data.TileLayer_Struct;
import com.forgottenartsstudios.data.mapData;

/**
 * Created by forgo on 10/6/2017.
 */

public class Variables {
    public static final String buildVersion = "v0.1.5";

    public static int AndroidScreenWidth = 480;
    public static int AndroidScreenHeight = 854;
    public static int DesktopScreenWidth = 800;
    public static int DesktopScreenHeight = 600;

    public static int Client_Mode;
    public static final int Client_Mode_Desktop = 1;
    public static final int Client_Mode_Android = 2;

    public static int Game_State;
    public static final int Game_State_TitleScreen = 1;
    public static final int Game_State_Login = 2;
    public static final int Game_State_CharSelect = 3;
    public static final int Game_State_CharCreate = 4;
    public static final int Game_State_Loading = 5;
    public static final int Game_State_InGame = 6;

    public static String chatInput;
    public static Message[] chatMessages = new Message[100 + 1];
    public static int chatMessageIndex;

    public static Party MyParty;
    public static int PartyLeader;
    public static boolean PartyInvite;

    // MOUSE INFO
    public static int CurX;
    public static int CurY;

    public static boolean inputOpen;

    public static boolean saveLogin;
    public static String Login_ID = "", Login_PW = "";

    public static String Server_IP =  "76.112.239.106";
    //public static String Server_IP =  "162.198.233.237";
    public static int Server_Port = 4001;
    public static boolean serverOnline = false;

    public static boolean pressUp, pressDown, pressLeft, pressRight, pressAttack, pickUpItem, pressHotKeyQ, pressHotKeyE;
    public static boolean pauseMovement;
    public static boolean dPad_Up, dPad_Down, dPad_Left, dPad_Right, bBtn, aBtn;
    public static boolean hotKeyQ, hotKeyE;
    public static boolean inMenu, inShop, inInventory, inStatus, inChat, inSpells;
    public static boolean buyItem, useItem, usePoint;
    public static int usePointTimer;
    public static boolean longPress, touchDown;
    public static boolean inputID, inputPW, inputName;
    public static int menuIndex, target;

    public static int MyIndex;
    public static int ShopNum;
    public static int longPressTimer;

    public static int selectedShopSlot, selectedInvSlot, selectedSpellSlot;

    public static final int MoveSize = 32;

    public static int inputTimer;

    public static final int MaxPlayers = 100;
    public static final int MaxMaps = 305;
    public static final int MaxSprites = 33;
    public static final int MaxIcons = 1696;
    public static final int MaxTiles = 1;
    public static final int MaxMapNPCs = 30;
    public static final int MaxMapItems = 20;
    public static final int MaxMapSpells = 20;
    public static final int MaxItems = 200;
    public static final int MaxSpells = 200;
    public static final int MaxSpellIcons = 61;
    public static final int MaxAnims = 10;

    public static final int JOB_WARRIOR = 1;
    public static final int JOB_WIZARD = 2;
    public static final int JOB_CLERIC = 3;
    public static final int JOB_RANGER = 4;
    public static final int JOB_ROGUE = 5;

    public static final int MONSTER_TYPE_NORMAL = 1;
    public static final int MONSTER_TYPE_WEAK = 2;
    public static final int MONSTER_TYPE_STRONG = 3;
    public static final int MONSTER_TYPE_MINION = 4;
    public static final int MONSTER_TYPE_BOSS = 5;

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

    public static final int MORAL_TYPE_UNSAFE = 0;
    public static final int MORAL_TYPE_SAFE = 1;

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

    // Item Types
    public static final int ITEM_TYPE_NONE = 0;
    public static final int ITEM_TYPE_CURRENCY = 1;
    public static final int ITEM_TYPE_WEAPON = 2;
    public static final int ITEM_TYPE_ARMOR = 3;
    public static final int ITEM_TYPE_OFFHAND = 4;
    public static final int ITEM_TYPE_HELMET = 5;
    public static final int ITEM_TYPE_POTION = 6;

    // Spell types
    public static final int SPELL_TYPE_DAMAGE = 1;
    public static final int SPELL_TYPE_HEAL = 2;
    public static final int SPELL_TYPE_REVIVE = 3;

    // Hot Keys
    public static final int HOT_KEY_Q = 1;
    public static final int HOT_KEY_E = 2;

    public static long tickCount;

    public static AccountData MyAccount;
    public static TempStats tempStats = new TempStats();
    public static mapData[] mapRender = new mapData[MaxMaps + 1];
    public static Player[] Players = new Player[MaxPlayers + 1];
    public static MapNPC[] MapNPCs = new MapNPC[MaxMapNPCs + 1];
    public static MapItem[] MapItems = new MapItem[MaxMapItems + 1];
    public static MapSpell[] MapSpells = new MapSpell[MaxMapSpells + 1];
    public static Item_Struct[] Items = new Item_Struct[MaxItems + 1];
    public static Spell_Struct[] Spells = new Spell_Struct[MaxSpells + 1];
    public static Shop_Struct Shop = new Shop_Struct();
    public static Damage_Struct[] DrawNPCDamage = new Damage_Struct[20 + 1];
    public static Damage_Struct[] DrawXP = new Damage_Struct[20 + 1];
    public static Damage_Struct[] DrawPlayerDamage = new Damage_Struct[20 + 1];
    public static Damage_Struct[] DrawHP = new Damage_Struct[20 + 1];
    public static SystemMsg_Struct[] DrawSystemMessage = new SystemMsg_Struct[20 + 1];
    public static SystemMsg_Struct[] DrawLevelUp = new SystemMsg_Struct[20 + 1];
    public static int CharIndex;

    public static int BoughtMsgTimer, NotEnoughGoldMsgTimer;

    public static final int DIR_UP = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;
    public static final int DIR_RIGHT = 4;

    public static int MinX;
    public static int MaxX;
    public static int MinY;
    public static int MaxY;

    public static boolean reloadingMap, loadingMap;

    public static MapTiles[] mapTiles = new MapTiles[MaxMaps + 1];

    // TEMP VARIABLES
    public static String TempName;
    public static int TempJob;
    public static int TempSprite;
    public static boolean AccountNotFound, AccountRegistered, AccountExists, PasswordReq, IDReq;
}
