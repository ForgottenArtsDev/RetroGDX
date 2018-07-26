package com.forgottenartsstudios.editorsuite;

import com.forgottenartsstudios.data.TileData_ServerStruct;
import com.forgottenartsstudios.data.Tile_ServerStruct;

/**
 * Created by Perfekt on 8/31/2016.
 */
public class editorLoadData {
    public static void clearMap(int MaxX, int MaxY)
    {
        editorVars.mapRender.Tileset = 1;
        editorVars.mapRender.Name = "Map";
        editorVars.mapRender.MaxX = MaxX;
        editorVars.mapRender.MaxY = MaxY;

        // Restructure the Array!
        editorVars.mapRender.Tile = new Tile_ServerStruct[editorVars.mapRender.MaxX][editorVars.mapRender.MaxY];
        editorVars.mapRender.SoundID = new int[editorVars.mapRender.MaxX][editorVars.mapRender.MaxY];

        editorVars.TileLayer = new TileLayer_Struct[5];
        for (int i = 0; i <= 4; i++) {
            editorVars.TileLayer[i] = new TileLayer_Struct();
            editorVars.TileLayer[i].Tile = new CachedTile_Struct[editorVars.mapRender.MaxX * editorVars.mapRender.MaxY + 1];
            for (int z = 0; z <= editorVars.mapRender.MaxX * editorVars.mapRender.MaxY; z++) {
                editorVars.TileLayer[i].Tile[z] = new CachedTile_Struct();
            }
        }

        // Resize all Layers
        for (int x = 0; x <= editorVars.mapRender.MaxX - 1; x++)
        {
            for (int y = 0; y <= editorVars.mapRender.MaxY - 1; y++)
            {
                editorVars.mapRender.Tile[x][y] = new Tile_ServerStruct();
                editorVars.mapRender.Tile[x][y].Layer = new TileData_ServerStruct[5];
            }
        }

        for (int x = 0; x <= editorVars.mapRender.MaxX - 1; x++)
        {
            for (int y = 0; y <= editorVars.mapRender.MaxY - 1; y++)
            {
                for (int i = 0; i <= 4; i++)
                {
                    editorVars.mapRender.Tile[x][y].Layer[i] = new TileData_ServerStruct();
                }
            }
        }

        // Resize all Layers
        for (int x = 0; x <= editorVars.mapRender.MaxX - 1; x++)
        {
            for (int y = 0; y <= editorVars.mapRender.MaxY - 1; y++)
            {
                editorVars.mapRender.Tile[x][y].Autotile = new int[4 + 1];
            }
        }

        for (int x = 0; x <= editorVars.mapRender.MaxX - 1; x++)
        {
            for (int y = 0; y <= editorVars.mapRender.MaxY - 1; y++) {
                editorVars.mapRender.SoundID[x][y] = 0;
            }
        }
        //Arrays.fill(ServerVars.mapData[MapNum].SoundID, null);

        // Clear Map Cache //
        //Types.MapCache[MapNum].Data = null;
    }
    public static void clearNPC(int npcNum) {
        editorVars.npcs[npcNum].Name = "Empty Slot";

        editorVars.npcs[npcNum].Sprite = 0;
        editorVars.npcs[npcNum].Range = 0;
        editorVars.npcs[npcNum].Behaviour = 0;
        editorVars.npcs[npcNum].SpawnSecs = 0;
        editorVars.npcs[npcNum].Health = 0;
        editorVars.npcs[npcNum].Exp = 0;

        editorVars.npcs[npcNum].STR = 0;
        editorVars.npcs[npcNum].DEF = 0;
        editorVars.npcs[npcNum].VIT = 0;
        editorVars.npcs[npcNum].AGI = 0;
        editorVars.npcs[npcNum].MAG = 0;
    }

    public static void clearItem(int itemNum) {
        editorVars.items[itemNum].Name = "Empty Slot";

        editorVars.items[itemNum].Icon = 0;
        editorVars.items[itemNum].itemType = 0;
        editorVars.items[itemNum].isStackable = 0;

        editorVars.items[itemNum].LVL = 0;
        editorVars.items[itemNum].HP = 0;
        editorVars.items[itemNum].MP = 0;

        editorVars.items[itemNum].STR = 0;
        editorVars.items[itemNum].DEF = 0;
        editorVars.items[itemNum].VIT = 0;
        editorVars.items[itemNum].AGI = 0;
        editorVars.items[itemNum].MAG = 0;
    }

    public static void clearShop(int shopNum) {
        editorVars.shops[shopNum].Name = "Empty Slot";

        editorVars.shops[shopNum].welcomeMsg = "";
        editorVars.shops[shopNum].goodbyeMsg = "";

        editorVars.shops[shopNum].salesTax = 0;

        for (int i = 1; i <= 20; i++) {
            editorVars.shops[shopNum].itemNum[i] = 0;
            editorVars.shops[shopNum].itemVal[i] = 0;
        }

        editorVars.shops[shopNum].canRepair = false;
    }

    public static void clearSpell(int spellNum) {
        editorVars.spells[spellNum].Name = "Empty Slot";
        editorVars.spells[spellNum].Type = 0;
        editorVars.spells[spellNum].Icon = 0;

        editorVars.spells[spellNum].LevelReq = 0;
        editorVars.spells[spellNum].ClassReq = 0;

        editorVars.spells[spellNum].Animation = 0;
        editorVars.spells[spellNum].AnimSpeed = 0;

        editorVars.spells[spellNum].CastTime = 0;
        editorVars.spells[spellNum].CoolDown = 0;

        editorVars.spells[spellNum].MPCost = 0;
        editorVars.spells[spellNum].DmgHealAmt = 0;
    }
}
