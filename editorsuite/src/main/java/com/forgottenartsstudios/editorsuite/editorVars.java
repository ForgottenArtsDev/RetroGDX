package com.forgottenartsstudios.editorsuite;

import com.forgottenartsstudios.data.*;
import com.forgottenartsstudios.data.Shop_Struct;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Created by Perfekt on 8/31/2016.
 */
public class editorVars {

    public static editorPlayerData Player;

    public static JFrame toolBox, npcEditor, npcSpawn, itemEditor, itemSpawn, warpTile, shopEditor;
    public static JLabel tileSet, tileBox;
    public static int MaxTiles = 1;
    public static int MaxSprites = 10;
    public static int currentTileSet = 1;
    public static int maxMaps = 20;
    public static int maxNPCs = 200;
    public static int maxItems = 200;
    public static int maxShops = 200;

    public static int LastScrollHorizontal, LastScrollVertical;
    public static Point LastViewport;

    public static int EditorTileX, EditorTileStartX;
    public static int EditorTileY, EditorTileStartY;
    public static int EditorTileWidth;
    public static int EditorTileHeight;
    public static int autoTileIndex = 0;
    public static int layerIndex = 0;
    public static int tilesetIndex = 1;
    public static int npcIndex;
    public static int itemIndex;
    public static int shopIndex;
    public static int shopItemIndex;

    public static int currentUndo = 0;

    public static TileLayer_Struct[] TileLayer; //32x32 Cache

    public static Rectangle SelectedTile;

    public static boolean firstTilesetChange = true;

    public static mapData mapRender = new mapData();
    public static mapData[] undoMapRender = new mapData[3 + 1];
    public static mapData tempMap = new mapData();
    public static mapData oldMapRender = new mapData();
    public static NPC_Struct[] npcs;
    public static Item_Struct[] items;
    public static Shop_Struct[] shops;

    public static boolean reloadingMap;

    // DIRECTION
    public static final int Dir_Up = 1;
    public static final int Dir_Down = 2;
    public static final int Dir_Left = 3;
    public static final int Dir_Right = 4;

    public static final int MoveSize = 32;

    // Scrolling Map //
    public static int MinX;
    public static int MaxX;
    public static int MinY;
    public static int MaxY;
    public static int ScreenMinY;   //Start Y pos on current screen
    public static int ScreenMaxY;   //End Y pos on current screen
    public static int ScreenMinX;   //Start X pos on current screen
    public static int ScreenMaxX;   //End X pos on current screen
    public static int LastTileX;
    public static int LastTileY;

    //public static int mapZoom = 32;

    // Map Co-ords //
    /*public static byte MAX_MAPX = 40;
    public static byte MAX_MAPY = 23;
    public static int HalfX = 20;
    public static int HalfY = 12;
    public static int ScreenX = (40) * 32;
    public static int ScreenY = (23) * 32;*/

    // Map Co-ords //
    public static int ScreenX = 448;
    public static int ScreenY = 448;
    public static int MAX_MAPX = (448 / 32) - 1;
    public static int MAX_MAPY = (448 / 32) - 1;
    public static int HalfX = MAX_MAPX / 2 - 1;
    public static int HalfY = MAX_MAPY / 2 - 1;
    public static int disX = 0;
    public static int disY = 0;

    public static int multiTileStartX, multiTileStartY;

    // editorAutotiles
    public static final int AUTO_INNER = 1;
    public static final int AUTO_OUTER = 2;
    public static final int AUTO_HORIZONTAL = 3;
    public static final int AUTO_VERTICAL = 4;
    public static final int AUTO_FILL = 5;

    // Autotile Types
    public static final int AUTOTILE_NONE = 0;
    public static final int AUTOTILE_NORMAL = 1;
    public static final int AUTOTILE_FAKE = 2;
    public static final int AUTOTILE_ANIM = 3;
    public static final int AUTOTILE_CLIFF = 4;
    public static final int AUTOTILE_WATERFALL = 5;

    // Map Animation
    public static byte MapAnim;
    public static byte WaterfallAnim;
    public static byte AutoTileAnim;

    public static int multiTileX;

    ///////////////////
    // Tile Consants //
    ///////////////////
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
    public static final int TILE_TYPE_CHAT = 14;

    // Item Types //
    public static final int ITEM_TYPE_NONE = 0;
    public static final int ITEM_TYPE_CURRENCY = 1;
    public static final int ITEM_TYPE_WEAPON = 2;
    public static final int ITEM_TYPE_ARMOR = 3;
    public static final int ITEM_TYPE_OFFHAND = 4;
    public static final int ITEM_TYPE_HELMET = 5;
    public static final int ITEM_TYPE_POTION = 6;

    // Map Tile Editor //
    public static int selectedTileType;
    public static int selectedData1;
    public static int selectedData2;
    public static int selectedData3;
}
