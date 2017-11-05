package com.forgottenartsstudios.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.forgottenartsstudios.data.CachedTile_Struct;
import com.forgottenartsstudios.data.Preferences;
import com.forgottenartsstudios.data.TileData_ServerStruct;
import com.forgottenartsstudios.data.TileLayer_Struct;
import com.forgottenartsstudios.data.Tile_ServerStruct;
import com.forgottenartsstudios.data.mapData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by forgo on 10/6/2017.
 */

public class AssetLoader {
    static String clientDir = "";
    public static Texture mainMenuBG, mainMenuTap, loginMenuBG, charSelectBG, charCreateBG, inGameBG, menuBG, eqBG, eqBGs, loadBG;
    public static Texture warriorEmb, wizardEmb, clericEmb, rangerEmb, rogueEmb;
    public static Texture warriorEmbT, wizardEmbT, clericEmbT, rangerEmbT, rogueEmbT;
    public static Texture male, female;
    public static Texture hpBar, mpBar, xpBar, emptyBar, sepBarV, sepBarH;
    public static Texture chatBar;
    public static Texture[] sprites, tiles, items;
    public static TextureRegion[] spritesUp1, spritesUp2, spritesUp3;
    public static TextureRegion[] spritesDown1, spritesDown2, spritesDown3;
    public static TextureRegion[] spritesLeft1, spritesLeft2, spritesLeft3;
    public static TextureRegion[] spritesRight1, spritesRight2, spritesRight3;
    public static BitmapFont font, nameFont;

    public static void load() {
        if (Variables.Client_Mode == Variables.Client_Mode_Desktop)
        {
            clientDir = "android/assets/";
        } else if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            clientDir = "";
        }

        if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            loadAndroid();
        } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
            loadDesktop();
        }
    }
    public static void loadAndroid() {
        mainMenuBG = new Texture(Gdx.files.internal(clientDir + "data/ui/android/main_menu_bg.png"));
        mainMenuBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        mainMenuTap = new Texture(Gdx.files.internal(clientDir + "data/ui/android/main_menu_tap.png"));
        mainMenuTap.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        loginMenuBG = new Texture(Gdx.files.internal(clientDir + "data/ui/android/login_menu_bg.png"));
        loginMenuBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        charSelectBG = new Texture(Gdx.files.internal(clientDir + "data/ui/android/char_select_bg.png"));
        charSelectBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        charCreateBG = new Texture(Gdx.files.internal(clientDir + "data/ui/android/char_create_bg.png"));
        charCreateBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        inGameBG = new Texture(Gdx.files.internal(clientDir + "data/ui/android/in_game_bg.png"));
        inGameBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        menuBG = new Texture(Gdx.files.internal(clientDir + "data/ui/android/menu_bg.png"));
        menuBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        eqBG = new Texture(Gdx.files.internal(clientDir + "data/ui/android/eq_bg.png"));
        eqBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        eqBGs = new Texture(Gdx.files.internal(clientDir + "data/ui/android/eq_bg_s.png"));
        eqBGs.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        loadBG = new Texture(Gdx.files.internal(clientDir + "data/ui/android/loading_bg.png"));
        loadBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        hpBar = new Texture(Gdx.files.internal(clientDir + "data/ui/android/hp_bar.png"));
        hpBar.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        mpBar = new Texture(Gdx.files.internal(clientDir + "data/ui/android/mp_bar.png"));
        mpBar.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        xpBar = new Texture(Gdx.files.internal(clientDir + "data/ui/android/xp_bar.png"));
        xpBar.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        emptyBar = new Texture(Gdx.files.internal(clientDir + "data/ui/android/empty_bar.png"));
        emptyBar.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        sepBarV = new Texture(Gdx.files.internal(clientDir + "data/ui/android/separate_bar_v.png"));
        sepBarV.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        sepBarH = new Texture(Gdx.files.internal(clientDir + "data/ui/android/separate_bar_h.png"));
        sepBarH.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        chatBar = new Texture(Gdx.files.internal(clientDir + "data/ui/android/chat_bar.png"));
        chatBar.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        male = new Texture(Gdx.files.internal(clientDir + "data/ui/android/male.png"));
        male.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        female = new Texture(Gdx.files.internal(clientDir + "data/ui/android/female.png"));
        female.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        warriorEmb = new Texture(Gdx.files.internal(clientDir + "data/ui/android/warrior_emblem.png"));
        warriorEmb.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        warriorEmbT = new Texture(Gdx.files.internal(clientDir + "data/ui/android/warrior_emblem_tran.png"));
        warriorEmbT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        wizardEmb = new Texture(Gdx.files.internal(clientDir + "data/ui/android/wizard_emblem.png"));
        wizardEmb.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        wizardEmbT = new Texture(Gdx.files.internal(clientDir + "data/ui/android/wizard_emblem_tran.png"));
        wizardEmbT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        clericEmb = new Texture(Gdx.files.internal(clientDir + "data/ui/android/cleric_emblem.png"));
        clericEmb.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        clericEmbT = new Texture(Gdx.files.internal(clientDir + "data/ui/android/cleric_emblem_tran.png"));
        clericEmbT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rangerEmb = new Texture(Gdx.files.internal(clientDir + "data/ui/android/ranger_emblem.png"));
        rangerEmb.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rangerEmbT = new Texture(Gdx.files.internal(clientDir + "data/ui/android/ranger_emblem_tran.png"));
        rangerEmbT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rogueEmb = new Texture(Gdx.files.internal(clientDir + "data/ui/android/rogue_emblem.png"));
        rogueEmb.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rogueEmbT = new Texture(Gdx.files.internal(clientDir + "data/ui/android/rogue_emblem_tran.png"));
        rogueEmbT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sprites = new Texture[Variables.MaxSprites + 1];

        spritesUp1 = new TextureRegion[Variables.MaxSprites + 1];
        spritesDown1 = new TextureRegion[Variables.MaxSprites + 1];
        spritesLeft1 = new TextureRegion[Variables.MaxSprites + 1];
        spritesRight1 = new TextureRegion[Variables.MaxSprites + 1];
        spritesUp2 = new TextureRegion[Variables.MaxSprites + 1];
        spritesDown2 = new TextureRegion[Variables.MaxSprites + 1];
        spritesLeft2 = new TextureRegion[Variables.MaxSprites + 1];
        spritesRight2 = new TextureRegion[Variables.MaxSprites + 1];
        spritesUp3 = new TextureRegion[Variables.MaxSprites + 1];
        spritesDown3 = new TextureRegion[Variables.MaxSprites + 1];
        spritesLeft3 = new TextureRegion[Variables.MaxSprites + 1];
        spritesRight3 = new TextureRegion[Variables.MaxSprites + 1];

        for (int i = 1; i <= Variables.MaxSprites; i++) {
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
        tiles = new Texture[Variables.MaxTiles + 1];
        for (int i = 1; i <= Variables.MaxTiles; i++) {
            tiles[i] = new Texture(Gdx.files.internal(clientDir + "data/tiles/" + i + ".png"));
            tiles[i].setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }
        items = new Texture[Variables.MaxIcons + 1];
        for (int i = 1; i <= Variables.MaxIcons; i++) {
            items[i] = new Texture(Gdx.files.internal(clientDir + "data/items/" + i + ".png"));
            items[i].setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }

        font = new BitmapFont(Gdx.files.internal(clientDir + "data/fonts/font.fnt"));
        font.getData().setScale(1f, -1f);
        nameFont = new BitmapFont(Gdx.files.internal(clientDir + "data/fonts/nameFont.fnt"));
        nameFont.getData().setScale(1f, -1f);
    }
    public static void loadDesktop() {
        loginMenuBG = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/login_menu_bg.png"));
        loginMenuBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        charSelectBG = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/char_select_bg.png"));
        charSelectBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        charCreateBG = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/char_create_bg.png"));
        charCreateBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        inGameBG = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/in_game_bg.png"));
        inGameBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        menuBG = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/menu_bg.png"));
        menuBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        eqBG = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/eq_bg.png"));
        eqBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        eqBGs = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/eq_bg_s.png"));
        eqBGs.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        loadBG = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/loading_bg.png"));
        loadBG.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        hpBar = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/hp_bar.png"));
        hpBar.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        mpBar = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/mp_bar.png"));
        mpBar.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        xpBar = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/xp_bar.png"));
        xpBar.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        emptyBar = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/empty_bar.png"));
        emptyBar.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        chatBar = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/chat_bar.png"));
        chatBar.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        male = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/male.png"));
        male.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        female = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/female.png"));
        female.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        warriorEmb = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/warrior_emblem.png"));
        warriorEmb.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        warriorEmbT = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/warrior_emblem_tran.png"));
        warriorEmbT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        wizardEmb = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/wizard_emblem.png"));
        wizardEmb.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        wizardEmbT = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/wizard_emblem_tran.png"));
        wizardEmbT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        clericEmb = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/cleric_emblem.png"));
        clericEmb.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        clericEmbT = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/cleric_emblem_tran.png"));
        clericEmbT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rangerEmb = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/ranger_emblem.png"));
        rangerEmb.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rangerEmbT = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/ranger_emblem_tran.png"));
        rangerEmbT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rogueEmb = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/rogue_emblem.png"));
        rogueEmb.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        rogueEmbT = new Texture(Gdx.files.internal(clientDir + "data/ui/desktop/rogue_emblem_tran.png"));
        rogueEmbT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sprites = new Texture[Variables.MaxSprites + 1];

        spritesUp1 = new TextureRegion[Variables.MaxSprites + 1];
        spritesDown1 = new TextureRegion[Variables.MaxSprites + 1];
        spritesLeft1 = new TextureRegion[Variables.MaxSprites + 1];
        spritesRight1 = new TextureRegion[Variables.MaxSprites + 1];
        spritesUp2 = new TextureRegion[Variables.MaxSprites + 1];
        spritesDown2 = new TextureRegion[Variables.MaxSprites + 1];
        spritesLeft2 = new TextureRegion[Variables.MaxSprites + 1];
        spritesRight2 = new TextureRegion[Variables.MaxSprites + 1];
        spritesUp3 = new TextureRegion[Variables.MaxSprites + 1];
        spritesDown3 = new TextureRegion[Variables.MaxSprites + 1];
        spritesLeft3 = new TextureRegion[Variables.MaxSprites + 1];
        spritesRight3 = new TextureRegion[Variables.MaxSprites + 1];

        for (int i = 1; i <= Variables.MaxSprites; i++) {
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
        tiles = new Texture[Variables.MaxTiles + 1];
        for (int i = 1; i <= Variables.MaxTiles; i++) {
            tiles[i] = new Texture(Gdx.files.internal(clientDir + "data/tiles/" + i + ".png"));
            tiles[i].setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }
        items = new Texture[Variables.MaxIcons + 1];
        for (int i = 1; i <= Variables.MaxIcons; i++) {
            items[i] = new Texture(Gdx.files.internal(clientDir + "data/items/" + i + ".png"));
            items[i].setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }

        font = new BitmapFont(Gdx.files.internal(clientDir + "data/fonts/font.fnt"));
        font.getData().setScale(1f, -1f);
        nameFont = new BitmapFont(Gdx.files.internal(clientDir + "data/fonts/nameFont.fnt"));
        nameFont.getData().setScale(1f, -1f);
    }
    public static void cacheTiles(int mapNum) {
        byte Layer;
        int X;
        int Y;

        Variables.mapTiles[mapNum].TileLayer = new TileLayer_Struct[5];

        for (int i = 0; i <= 4; i++) {
            Variables.mapTiles[mapNum].TileLayer[i] = new TileLayer_Struct();
            Variables.mapTiles[mapNum].TileLayer[i].Tile = new CachedTile_Struct[(Variables.mapRender[mapNum].MaxY * Variables.mapRender[mapNum].MaxX) + 1];

            for (int z = 0; z <= (Variables.mapRender[mapNum].MaxY * Variables.mapRender[mapNum].MaxX); z++) {
                Variables.mapTiles[mapNum].TileLayer[i].Tile[z] = new CachedTile_Struct();
            }
        }

        //Loop through each layer and check which tiles there are that will need to be drawn

        //Loop through all the tiles within the buffer's range
        for (X = 0; X <= Variables.mapRender[mapNum].MaxX - 1; X++)
        {
            for (Y = 0; Y <= Variables.mapRender[mapNum].MaxY - 1; Y++)
            {
                for (Layer = 0; Layer <= 4; Layer++)
                {
                    // Destination Positioning //
                    Variables.mapTiles[mapNum].TileLayer[Layer].Tile[Variables.mapTiles[mapNum].TileLayer[Layer].NumTiles].dx = X;      // Store Destination X
                    Variables.mapTiles[mapNum].TileLayer[Layer].Tile[Variables.mapTiles[mapNum].TileLayer[Layer].NumTiles].dy = Y;      // Store Destination Y
                    Variables.mapTiles[mapNum].TileLayer[Layer].Tile[Variables.mapTiles[mapNum].TileLayer[Layer].NumTiles].PixelPosX = X * 32;
                    Variables.mapTiles[mapNum].TileLayer[Layer].Tile[Variables.mapTiles[mapNum].TileLayer[Layer].NumTiles].PixelPosY = Y * 32;

                    // Source Positioning //
                    Variables.mapTiles[mapNum].TileLayer[Layer].Tile[Variables.mapTiles[mapNum].TileLayer[Layer].NumTiles].sRect
                            = new Rectangle
                            (Variables.mapRender[mapNum].Tile[X][Y].Layer[Layer].X * 32,
                                    Variables.mapRender[mapNum].Tile[X][Y].Layer[Layer].Y * 32, 32, 32);

                    // The tile is valid to be used, so raise the count //
                    Variables.mapRender[mapNum].Tile[X][Y].TileNum[Layer] = Variables.mapTiles[mapNum].TileLayer[Layer].NumTiles;
                    Variables.mapTiles[mapNum].TileLayer[Layer].NumTiles = Variables.mapTiles[mapNum].TileLayer[Layer].NumTiles + 1;
                }
            }
        }

    }
    public static void loadMap(int mapNum) {
        Variables.loadingMap = true;

        FileHandle mapFile = null;

        if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            mapFile = Gdx.files.local(clientDir + "data/maps/" + mapNum + ".dat");
        } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
            mapFile = Gdx.files.internal(clientDir + "data/maps/" + mapNum + ".dat");
        }

        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(mapFile.file()));
        } catch (IOException e) {
            System.out.println("There was a problem opening the file: " + e);
            System.exit(0);
        }

        try{
            Variables.mapRender[mapNum] = (mapData)inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
        Variables.loadingMap = false;
    }
    public static void checkMaps() {
        for (int i = 1; i <= Variables.MaxMaps; i++) {
            int mapNum = i;
            FileHandle mapFile = Gdx.files.internal("data/maps/" + mapNum + ".dat");

            if (mapFile.exists()) {
                FileHandle newMapFile = Gdx.files.local("data/maps/" + mapNum + ".dat");
                //if (!newMapFile.exists()) {
                    mapFile.copyTo(newMapFile);
                //}
            }
        }
    }
    public static void clearMap(int mapNum) {
        Variables.mapRender[mapNum].Tileset = 1;
        Variables.mapRender[mapNum].Name = "Map";
        Variables.mapRender[mapNum].MaxX = 14;
        Variables.mapRender[mapNum].MaxY = 14;

        // Restructure the Array!
        Variables.mapRender[mapNum].Tile = new Tile_ServerStruct[Variables.mapRender[mapNum].MaxX][Variables.mapRender[mapNum].MaxY];
        Variables.mapRender[mapNum].SoundID = new int[Variables.mapRender[mapNum].MaxX][Variables.mapRender[mapNum].MaxY];

        // Resize all Layers
        for (int x = 0; x <= Variables.mapRender[mapNum].MaxX - 1; x++)
        {
            for (int y = 0; y <= Variables.mapRender[mapNum].MaxY - 1; y++)
            {
                Variables.mapRender[mapNum].Tile[x][y] = new Tile_ServerStruct();
                Variables.mapRender[mapNum].Tile[x][y].Layer = new TileData_ServerStruct[5];
            }
        }

        for (int x = 0; x <= Variables.mapRender[mapNum].MaxX - 1; x++)
        {
            for (int y = 0; y <= Variables.mapRender[mapNum].MaxY - 1; y++)
            {
                for (int i = 0; i <= 4; i++)
                {
                    Variables.mapRender[mapNum].Tile[x][y].Layer[i] = new TileData_ServerStruct();
                }
            }
        }

        // Resize all Layers
        for (int x = 0; x <= Variables.mapRender[mapNum].MaxX - 1; x++)
        {
            for (int y = 0; y <= Variables.mapRender[mapNum].MaxY - 1; y++)
            {
                Variables.mapRender[mapNum].Tile[x][y].Autotile = new int[5];
            }
        }

        for (int x = 0; x <= Variables.mapRender[mapNum].MaxX - 1; x++)
        {
            for (int y = 0; y <= Variables.mapRender[mapNum].MaxY - 1; y++) {
                Variables.mapRender[mapNum].SoundID[x][y] = 0;
            }
        }
    }
    public static void saveLogin() {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\android\\assets\\data\\preferences.dat";

        System.out.println("saveLogin check 1");

        ObjectOutputStream  outputStream = null;

        try{
            System.out.println("saveLogin check 2");
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        }catch(IOException e){
            System.out.println("Could not open the file." + e);
            System.exit(0);
        }

        try{
            System.out.println("saveLogin check 3");
            Preferences prefs = new Preferences();

            prefs.saveLogin = Variables.saveLogin;
            prefs.ID = Variables.Login_ID;
            prefs.PW = Variables.Login_PW;

            outputStream.writeObject(prefs);

            outputStream.close();

        }catch(IOException e){
            System.out.println("Writing error: " + e);
            System.exit(0);
        }
    }
    public static void loadLogin() {
        System.out.println("loadLogin check 1");
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\android\\assets\\data\\preferences.dat";

        File f = new File(fileName);
        if(!f.exists() && !f.isDirectory()) {
            System.out.println("loadLogin check 2");
            return;
        }

        ObjectInputStream inputStream = null;
        try{
            System.out.println("loadLogin check 3");
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
        }catch(IOException e){
            System.out.println("There was a problem opening the file: " + e);
            System.exit(0);
        }

        try{
            System.out.println("loadLogin check 4");
            Preferences prefs;
            prefs = (Preferences) inputStream.readObject();

            if (prefs.saveLogin) {
                System.out.println("loadLogin check 5");
                Variables.saveLogin = prefs.saveLogin;
                Variables.Login_ID = prefs.ID;
                Variables.Login_PW = prefs.PW;
            }

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
    }
}
