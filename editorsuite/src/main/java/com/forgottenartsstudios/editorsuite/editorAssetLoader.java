package com.forgottenartsstudios.editorsuite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.forgottenartsstudios.data.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Perfekt on 8/31/2016.
 */
public class editorAssetLoader {

    static String clientDir = "";
    public static Texture[] sprites, tiles;
    public static TextureRegion[] spritesUp1, spritesUp2, spritesUp3;
    public static TextureRegion[] spritesDown1, spritesDown2, spritesDown3;
    public static TextureRegion[] spritesLeft1, spritesLeft2, spritesLeft3;
    public static TextureRegion[] spritesRight1, spritesRight2, spritesRight3;
    public static BitmapFont font;

    public static void load() {
        clientDir = "android/assets/";

        tiles = new Texture[editorVars.MaxTiles + 1];
        for (int i = 1; i <= editorVars.MaxTiles; i++) {
            tiles[i]= new Texture(Gdx.files.internal(clientDir + "data/tiles/" + i + ".png"));
            tiles[i].setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }

        sprites = new Texture[editorVars.MaxSprites + 1];

        spritesUp1 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesDown1 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesLeft1 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesRight1 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesUp2 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesDown2 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesLeft2 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesRight2 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesUp3 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesDown3 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesLeft3 = new TextureRegion[editorVars.MaxSprites + 1];
        spritesRight3 = new TextureRegion[editorVars.MaxSprites + 1];

        for (int i = 1; i <= editorVars.MaxSprites; i++)
        {
            sprites[i] = new Texture(Gdx.files.internal(clientDir + "data/sprites/" + i + ".png"));
            sprites[i].setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

            spritesUp1[i] = new TextureRegion(sprites[i], 0, 0, 32, 36);
            spritesUp1[i].flip(false, true);
            spritesUp2[i] = new TextureRegion(sprites[i], 32, 0, 32, 36);
            spritesUp2[i].flip(false, true);
            spritesUp3[i] = new TextureRegion(sprites[i], 64, 0, 32, 36);
            spritesUp3[i].flip(false, true);

            spritesDown1[i] = new TextureRegion(sprites[i], 0, 72, 32, 36);
            spritesDown1[i].flip(false, true);
            spritesDown2[i] = new TextureRegion(sprites[i], 32, 72, 32, 36);
            spritesDown2[i].flip(false, true);
            spritesDown3[i] = new TextureRegion(sprites[i], 64, 72, 32, 36);
            spritesDown3[i].flip(false, true);

            spritesLeft1[i] = new TextureRegion(sprites[i], 0, 108, 32, 36);
            spritesLeft1[i].flip(false, true);
            spritesLeft2[i] = new TextureRegion(sprites[i], 32, 108, 32, 36);
            spritesLeft2[i].flip(false, true);
            spritesLeft3[i] = new TextureRegion(sprites[i], 64, 108, 32, 36);
            spritesLeft3[i].flip(false, true);

            spritesRight1[i] = new TextureRegion(sprites[i], 0, 36, 32, 36);
            spritesRight1[i].flip(false, true);
            spritesRight2[i] = new TextureRegion(sprites[i], 32, 36, 32, 36);
            spritesRight2[i].flip(false, true);
            spritesRight3[i] = new TextureRegion(sprites[i], 64, 36, 32, 36);
            spritesRight3[i].flip(false, true);
        }

        font = new BitmapFont(Gdx.files.internal(clientDir + "data/fonts/font.fnt"));
        font.getData().setScale(1f,-1f);
    }

    public static void cacheTiles() {
        byte Layer;
        int X;
        int Y;

        editorVars.TileLayer = new TileLayer_Struct[5];

        for (int i = 0; i <= 4; i++) {
            editorVars.TileLayer[i] = new TileLayer_Struct();
            editorVars.TileLayer[i].Tile = new CachedTile_Struct[(editorVars.mapRender.MaxY * editorVars.mapRender.MaxX) + 1];

            for (int z = 0; z <= (editorVars.mapRender.MaxY * editorVars.mapRender.MaxX); z++) {
                editorVars.TileLayer[i].Tile[z] = new CachedTile_Struct();
            }
        }

        //Loop through each layer and check which tiles there are that will need to be drawn

        //Loop through all the tiles within the buffer's range
        for (X = 0; X <= editorVars.mapRender.MaxX - 1; X++)
        {
            for (Y = 0; Y <= editorVars.mapRender.MaxY - 1; Y++)
            {
                for (Layer = 0; Layer <= 4; Layer++)
                {
                    // Destination Positioning //
                    editorVars.TileLayer[Layer].Tile[editorVars.TileLayer[Layer].NumTiles].dx = X;      // Store Destination X
                    editorVars.TileLayer[Layer].Tile[editorVars.TileLayer[Layer].NumTiles].dy = Y;      // Store Destination Y
                    editorVars.TileLayer[Layer].Tile[editorVars.TileLayer[Layer].NumTiles].PixelPosX = X * 32;
                    editorVars.TileLayer[Layer].Tile[editorVars.TileLayer[Layer].NumTiles].PixelPosY = Y * 32;

                    // Source Positioning //
                    editorVars.TileLayer[Layer].Tile[editorVars.TileLayer[Layer].NumTiles].sRect
                            = new Rectangle
                            (editorVars.mapRender.Tile[X][Y].Layer[Layer].X * 32,
                                    editorVars.mapRender.Tile[X][Y].Layer[Layer].Y * 32, 32, 32);

                    // The tile is valid to be used, so raise the count //
                    editorVars.mapRender.Tile[X][Y].TileNum[Layer] = editorVars.TileLayer[Layer].NumTiles;
                    editorVars.TileLayer[Layer].NumTiles = editorVars.TileLayer[Layer].NumTiles + 1;
                }
            }
        }

    }

    public static void loadMap() {
        int mapNum = editorVars.Player.getMap();
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\maps\\" + mapNum + ".dat";

        File f = new File(fileName);
        if(!f.exists() && !f.isDirectory()) {
            return;
        }

        ObjectInputStream inputStream = null;
        try{
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
        }catch(IOException e){
            System.out.println("There was a problem opening the file: " + e);
            System.exit(0);
        }

        try{
            editorVars.mapRender = (mapData)inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
    }
    public static void loadTempMap(int mapNum) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\maps\\" + mapNum + ".dat";

        File f = new File(fileName);
        if(!f.exists() && !f.isDirectory()) {
            return;
        }

        ObjectInputStream inputStream = null;
        try{
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
        }catch(IOException e){
            System.out.println("There was a problem opening the file: " + e);
            System.exit(0);
        }

        try{
            editorVars.tempMap = (mapData)inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
    }
    public static void copyMap() {
        editorVars.oldMapRender.Tile = new Tile_ServerStruct[editorVars.mapRender.MaxX][editorVars.mapRender.MaxY];
        editorVars.oldMapRender.SoundID = new int[editorVars.mapRender.MaxX][editorVars.mapRender.MaxY];

        for (int x = 0; x <= editorVars.mapRender.MaxX - 1; x++) {
            for (int y = 0; y <= editorVars.mapRender.MaxY - 1; y++) {
                editorVars.oldMapRender.Tile[x][y] = new Tile_ServerStruct();
                editorVars.oldMapRender.Tile[x][y].Layer = new TileData_ServerStruct[4 + 1];
                editorVars.oldMapRender.Tile[x][y].Autotile = new int[4 + 1];
                for (int i = 0; i <= 4; i++) {
                    editorVars.oldMapRender.Tile[x][y].Layer[i] = new TileData_ServerStruct();
                    editorVars.oldMapRender.Tile[x][y].Autotile[i] = 0;
                }
            }
        }

        editorVars.oldMapRender.Revision = editorVars.mapRender.Revision;
        editorVars.oldMapRender.Music = editorVars.mapRender.Music;
        editorVars.oldMapRender.Moral = editorVars.mapRender.Moral;
        editorVars.oldMapRender.Weather = editorVars.mapRender.Weather;
        editorVars.oldMapRender.Tileset = editorVars.mapRender.Tileset;
        editorVars.oldMapRender.Up = editorVars.mapRender.Up;
        editorVars.oldMapRender.Down = editorVars.mapRender.Down;
        editorVars.oldMapRender.Left = editorVars.mapRender.Left;
        editorVars.oldMapRender.Right = editorVars.mapRender.Right;
        editorVars.oldMapRender.BootMap = editorVars.mapRender.BootMap;
        editorVars.oldMapRender.BootX = editorVars.mapRender.BootX;
        editorVars.oldMapRender.BootY = editorVars.mapRender.BootY;
        editorVars.oldMapRender.MaxX = editorVars.mapRender.MaxX;
        editorVars.oldMapRender.MaxY = editorVars.mapRender.MaxY;

        int MaxX = editorVars.oldMapRender.MaxX;
        int MaxY = editorVars.oldMapRender.MaxY;

        for (int x = 0; x <= MaxX - 1; x++)
        {
            for (int y = 0; y <= MaxY - 1; y++)
            {
                for (int i = 0; i <= 4; i++) {
                    editorVars.oldMapRender.Tile[x][y].Layer[i].Tileset = editorVars.mapRender.Tile[x][y].Layer[i].Tileset;
                    editorVars.oldMapRender.Tile[x][y].Layer[i].X = editorVars.mapRender.Tile[x][y].Layer[i].X;
                    editorVars.oldMapRender.Tile[x][y].Layer[i].Y = editorVars.mapRender.Tile[x][y].Layer[i].Y;

                    editorVars.oldMapRender.Tile[x][y].Autotile[i] = editorVars.mapRender.Tile[x][y].Autotile[i];
                }

                editorVars.oldMapRender.Tile[x][y].Type = editorVars.mapRender.Tile[x][y].Type;
                editorVars.oldMapRender.Tile[x][y].Data1 = editorVars.mapRender.Tile[x][y].Data1;
                editorVars.oldMapRender.Tile[x][y].Data2 = editorVars.mapRender.Tile[x][y].Data2;
                editorVars.oldMapRender.Tile[x][y].Data3 = editorVars.mapRender.Tile[x][y].Data3;
                editorVars.oldMapRender.Tile[x][y].DirBlock = editorVars.mapRender.Tile[x][y].DirBlock;
            }
        }

        for (int x = 0; x <= MaxX - 1; x++)
        {
            for (int y = 0; y <= MaxY - 1; y++)
            {
                editorVars.oldMapRender.SoundID[x][y] = editorVars.mapRender.SoundID[x][y];
            }
        }

        //Loop through all the tiles within the buffer's range
        int numTile = 0;
        //for (Y = editorVars.MinY; Y <= (editorVars.MaxY); Y++)
        for (int X = 0; X <= editorVars.oldMapRender.MaxX - 1; X++)
        {
            //for (X = editorVars.MinX; X <= (editorVars.MaxX); X++)
            for (int Y = 0; Y <= editorVars.oldMapRender.MaxY - 1; Y++)
            {
                for(int Layer = 0; Layer <= 4; Layer++) {
                    if (X >= 0) {
                        if (Y >= 0) {
                            if (X <= editorVars.oldMapRender.MaxX - 1) {
                                if (Y <= editorVars.oldMapRender.MaxY - 1) {
                                    // The tile is valid to be used, so raise the count //
                                    numTile++;
                                    editorVars.oldMapRender.Tile[X][Y].TileNum[Layer] = numTile;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void reloadMap() {
        int oldMaxX = editorVars.oldMapRender.MaxX;
        int oldMaxY = editorVars.oldMapRender.MaxY;
        int newMaxX = editorVars.mapRender.MaxX;
        int newMaxY = editorVars.mapRender.MaxY;
        int MaxX = 0;
        int MaxY = 0;

        if (newMaxX < oldMaxX) {
            MaxX = newMaxX;
        } else {
            MaxX = oldMaxX;
        }
        if (newMaxY < oldMaxY) {
            MaxY = newMaxY;
        } else {
            MaxY = oldMaxY;
        }

        for (int x = 0; x <= MaxX - 1; x++)
        {
            for (int y = 0; y <= MaxY - 1; y++)
            {
                for (int i = 0; i <= 4; i++) {
                    editorVars.mapRender.Tile[x][y].Layer[i].Tileset = editorVars.oldMapRender.Tile[x][y].Layer[i].Tileset;
                    editorVars.mapRender.Tile[x][y].Layer[i].X = editorVars.oldMapRender.Tile[x][y].Layer[i].X;
                    editorVars.mapRender.Tile[x][y].Layer[i].Y = editorVars.oldMapRender.Tile[x][y].Layer[i].Y;

                    editorVars.mapRender.Tile[x][y].Autotile[i] = editorVars.oldMapRender.Tile[x][y].Autotile[i];
                }

                editorVars.mapRender.Tile[x][y].Type = editorVars.oldMapRender.Tile[x][y].Type;
                editorVars.mapRender.Tile[x][y].Data1 = editorVars.oldMapRender.Tile[x][y].Data1;
                editorVars.mapRender.Tile[x][y].Data2 = editorVars.oldMapRender.Tile[x][y].Data2;
                editorVars.mapRender.Tile[x][y].Data3 = editorVars.oldMapRender.Tile[x][y].Data3;
                editorVars.mapRender.Tile[x][y].DirBlock = editorVars.oldMapRender.Tile[x][y].DirBlock;
            }
        }

        for (int x = 0; x <= MaxX - 1; x++)
        {
            for (int y = 0; y <= MaxY - 1; y++)
            {
                editorVars.mapRender.SoundID[x][y] = editorVars.oldMapRender.SoundID[x][y];
            }
        }

        //Loop through all the tiles within the buffer's range
        int numTile = 0;
        //for (Y = editorVars.MinY; Y <= (editorVars.MaxY); Y++)
        for (int X = 0; X <= editorVars.mapRender.MaxX - 1; X++)
        {
            //for (X = editorVars.MinX; X <= (editorVars.MaxX); X++)
            for (int Y = 0; Y <= editorVars.mapRender.MaxY - 1; Y++)
            {
                for (int Layer = 0; Layer <= 4; Layer++) {
                    if (X >= 0) {
                        if (Y >= 0) {
                            if (X <= editorVars.mapRender.MaxX - 1) {
                                if (Y <= editorVars.mapRender.MaxY - 1) {
                                    // The tile is valid to be used, so raise the count //
                                    numTile++;
                                    editorVars.mapRender.Tile[X][Y].TileNum[Layer] = numTile;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void saveMap() {
        int mapNum = editorVars.Player.getMap();
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\maps\\" + mapNum + ".dat";

        ObjectOutputStream outputStream = null;

        try{
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        }catch(IOException e){
            System.out.println("Could not open the file." + e);
            System.exit(0);
        }

        try{
            outputStream.writeObject(editorVars.mapRender);

            outputStream.close();

        }catch(IOException e){
            System.out.println("Writing error: " + e);
            System.exit(0);
        }
    }
    public static void saveShop(int shopNum) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\shops\\" + shopNum + ".dat";

        ObjectOutputStream outputStream = null;

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        }catch(IOException e){
            System.out.println("Could not open the file." + e);
            System.exit(0);
        }

        try {
            outputStream.writeObject(editorVars.shops[shopNum]);

            outputStream.close();
        }catch(IOException e){
            System.out.println("Writing error: " + e);
            System.exit(0);
        }
    }
    public static void loadShop(int shopNum) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\shops\\" + shopNum + ".dat";

        File f = new File(fileName);
        if(!f.exists() && !f.isDirectory()) {
            return;
        }

        ObjectInputStream inputStream = null;
        try{
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
        }catch(IOException e){
            System.out.println("There was a problem opening the file: " + e);
            System.exit(0);
        }

        try{
            editorVars.shops[shopNum] = (com.forgottenartsstudios.data.Shop_Struct) inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
    }
    public static void saveItem(int itemNum) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\items\\" + itemNum + ".dat";

        ObjectOutputStream outputStream = null;

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        }catch(IOException e){
            System.out.println("Could not open the file." + e);
            System.exit(0);
        }

        try {
            outputStream.writeObject(editorVars.items[itemNum]);

            outputStream.close();
        }catch(IOException e){
            System.out.println("Writing error: " + e);
            System.exit(0);
        }
    }
    public static void loadItem(int itemNum) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\items\\" + itemNum + ".dat";

        File f = new File(fileName);
        if(!f.exists() && !f.isDirectory()) {
            return;
        }

        ObjectInputStream inputStream = null;
        try{
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
        }catch(IOException e){
            System.out.println("There was a problem opening the file: " + e);
            System.exit(0);
        }

        try{
            editorVars.items[itemNum] = (Item_Struct) inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
    }
    public static void saveNPC(int npcNum) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\npcs\\" + npcNum + ".dat";

        ObjectOutputStream outputStream = null;

        try{
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        }catch(IOException e){
            System.out.println("Could not open the file." + e);
            System.exit(0);
        }

        try{
            outputStream.writeObject(editorVars.npcs[npcNum]);

            outputStream.close();
        }catch(IOException e){
            System.out.println("Writing error: " + e);
            System.exit(0);
        }
    }
    public static void loadNPC(int npcNum) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\npcs\\" + npcNum + ".dat";

        File f = new File(fileName);
        if(!f.exists() && !f.isDirectory()) {
            return;
        }

        ObjectInputStream inputStream = null;
        try{
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
        }catch(IOException e){
            System.out.println("There was a problem opening the file: " + e);
            System.exit(0);
        }

        try{
            editorVars.npcs[npcNum] = (NPC_Struct) inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
    }
}
