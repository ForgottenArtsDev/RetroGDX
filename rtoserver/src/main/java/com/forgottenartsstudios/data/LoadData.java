package com.forgottenartsstudios.data;

import com.forgottenartsstudios.helpers.ServerVars;
import com.forgottenartsstudios.networking.packetdata.SendServerData;
import com.forgottenartsstudios.networking.packets.AccountNotFound;
import com.forgottenartsstudios.networking.packets.SendLogin;
import com.forgottenartsstudios.server.serverWindow;
import com.sun.org.apache.xpath.internal.operations.Variable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static com.forgottenartsstudios.server.RTOServer.server;

/**
 * Created by forgo on 10/6/2017.
 */

public class LoadData {
    public static void LoadAccount(String ID, String PW, int CID) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\accounts\\" + ID + ".dat";

        File f = new File(fileName);

        if (!f.exists()) {
            AccountNotFound anf = new AccountNotFound();

            server.sendToTCP(CID, anf);
            return;
        }

        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));
        } catch (IOException e) {
            System.out.println("There was a problem opening the file: " + e);
            System.exit(0);
        }

        AccountData account = null;

        account = new AccountData();
        account.chars = new Player[3 + 1];
        for (int i = 1; i <= 3; i++) {
            account.chars[i] = new Player();
            account.chars[i].inventory = new Inventory_Struct[60 + 1];
            account.chars[i].spells = new Spell_Inv_Struct[60 + 1];
            for (int a = 1; a <= 60; a++) {
                account.chars[i].inventory[a] = new Inventory_Struct();
                account.chars[i].spells[a] = new Spell_Inv_Struct();
            }
        }

        try {
            //account = (AccountData) inputStream.readObject();

            account.setID((String)inputStream.readObject());
            account.setPW((String)inputStream.readObject());

            account.setSfxOn((Boolean)inputStream.readObject());
            account.setMusicOn((Boolean)inputStream.readObject());

            for (int i = 1; i <= 3; i++) {
                account.chars[i].setName((String)inputStream.readObject());

                account.chars[i].setJob((Integer)inputStream.readObject());
                account.chars[i].setSprite((Integer)inputStream.readObject());
                account.chars[i].setLevel((Integer)inputStream.readObject());
                account.chars[i].setPoints((Integer)inputStream.readObject());

                account.chars[i].setHP((Integer)inputStream.readObject());
                account.chars[i].setMP((Integer)inputStream.readObject());
                account.chars[i].setMaxHP((Integer)inputStream.readObject());
                account.chars[i].setMaxMP((Integer)inputStream.readObject());

                account.chars[i].setEXP((Integer)inputStream.readObject());
                account.chars[i].setNextLVL((Integer)inputStream.readObject());

                account.chars[i].setMap((Integer)inputStream.readObject());
                account.chars[i].setX((Integer)inputStream.readObject());
                account.chars[i].setY((Integer)inputStream.readObject());
                account.chars[i].setDir((Integer)inputStream.readObject());

                account.chars[i].setSTR((Integer)inputStream.readObject());
                account.chars[i].setDEF((Integer)inputStream.readObject());
                account.chars[i].setVIT((Integer)inputStream.readObject());
                account.chars[i].setAGI((Integer)inputStream.readObject());
                account.chars[i].setMAG((Integer)inputStream.readObject());

                account.chars[i].setWeapon((Integer)inputStream.readObject());
                account.chars[i].setOffhand((Integer)inputStream.readObject());
                account.chars[i].setArmor((Integer)inputStream.readObject());
                account.chars[i].setHelmet((Integer)inputStream.readObject());
                account.chars[i].setAcc1((Integer)inputStream.readObject());
                account.chars[i].setAcc2((Integer)inputStream.readObject());

                account.chars[i].setHotKeyQ((Integer)inputStream.readObject());
                account.chars[i].setHotKeyE((Integer)inputStream.readObject());
                account.chars[i].setHotKeyR((Integer)inputStream.readObject());
                account.chars[i].setHotKeyF((Integer)inputStream.readObject());

                for (int a = 1; a <= 60; a++) {
                    account.chars[i].inventory[a].setItemNum((Integer)inputStream.readObject());
                    account.chars[i].inventory[a].setItemVal((Integer)inputStream.readObject());
                }

                for (int a = 1; a <= 60; a++) {
                    account.chars[i].spells[a].setSpellNum((Integer)inputStream.readObject());
                }
            }

            inputStream.close();
        } catch (Exception e) {
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }

        if (PW.equals(account.getPW())) {
            SendLogin sndLogin = new SendLogin();

            account.setCID(CID);
            for (int i = 1; i <= ServerVars.MaxPlayers; i++) {
                if (ServerVars.Accounts[i].getID() == "") {
                    ServerVars.Accounts[i].setID(account.getID());
                    ServerVars.Accounts[i].setPW(account.getPW());

                    ServerVars.Accounts[i].chars = new Player[3 + 1];
                    for (int a = 1; a <= 3; a++) {
                        ServerVars.Accounts[i].chars[a] = new Player();
                        ServerVars.Accounts[i].chars[a].inventory = new Inventory_Struct[60 + 1];
                        for (int z = 1; z <= 60; z++) {
                            ServerVars.Accounts[i].chars[a].inventory[z] = new Inventory_Struct();
                        }
                    }

                    for (int a = 1; a <= 3; a++) {
                        ServerVars.Accounts[i].chars[a] = account.chars[a];
                        for (int z = 1; z <= 60; z++) {
                            ServerVars.Accounts[i].chars[a].inventory[z] = account.chars[a].inventory[z];
                        }
                        for (int z = 1; z <= 60; z++) {
                            ServerVars.Accounts[i].chars[a].spells[z] = account.chars[a].spells[z];
                        }
                    }

                    sndLogin.Index = i;

                    sndLogin.accountData = new AccountData();
                    sndLogin.accountData.setID(ServerVars.Accounts[i].getID());
                    sndLogin.accountData.setPW(ServerVars.Accounts[i].getPW());

                    sndLogin.char1 = ServerVars.Accounts[i].chars[1];
                    sndLogin.char2 = ServerVars.Accounts[i].chars[2];
                    sndLogin.char3 = ServerVars.Accounts[i].chars[3];

                    sndLogin.char1.inventory = ServerVars.Accounts[i].chars[1].inventory;
                    sndLogin.char2.inventory = ServerVars.Accounts[i].chars[2].inventory;
                    sndLogin.char3.inventory = ServerVars.Accounts[i].chars[3].inventory;

                    sndLogin.char1.spells = ServerVars.Accounts[i].chars[1].spells;
                    sndLogin.char2.spells = ServerVars.Accounts[i].chars[2].spells;
                    sndLogin.char3.spells = ServerVars.Accounts[i].chars[3].spells;

                    break;
                } else if (i == ServerVars.MaxPlayers) {
                    serverWindow.svrMonitor.append("Max players reached." + "\n");
                }
            }

            if (sndLogin.Index > 0) {
                server.sendToTCP(CID, sndLogin);
            }
        }
    }
    public static void clearMap(int MapNum) {
        ServerVars.mapData[MapNum].Tileset = 1;
        ServerVars.mapData[MapNum].Name = "Map";
        ServerVars.mapData[MapNum].MaxX = 14;
        ServerVars.mapData[MapNum].MaxY = 14;

        // Restructure the Array!
        ServerVars.mapData[MapNum].Tile = new Tile_ServerStruct[ServerVars.mapData[MapNum].MaxX][ServerVars.mapData[MapNum].MaxY];
        ServerVars.mapData[MapNum].SoundID = new int[ServerVars.mapData[MapNum].MaxX][ServerVars.mapData[MapNum].MaxY];

        // Resize all Layers
        for (int x = 0; x <= ServerVars.mapData[MapNum].MaxX - 1; x++)
        {
            for (int y = 0; y <= ServerVars.mapData[MapNum].MaxY - 1; y++)
            {
                ServerVars.mapData[MapNum].Tile[x][y] = new Tile_ServerStruct();
                ServerVars.mapData[MapNum].Tile[x][y].Layer = new TileData_ServerStruct[5];
            }
        }

        for (int x = 0; x <= ServerVars.mapData[MapNum].MaxX - 1; x++)
        {
            for (int y = 0; y <= ServerVars.mapData[MapNum].MaxY - 1; y++)
            {
                for (int i = 0; i <= 4; i++)
                {
                    ServerVars.mapData[MapNum].Tile[x][y].Layer[i] = new TileData_ServerStruct();
                }
            }
        }

        // Resize all Layers
        for (int x = 0; x <= ServerVars.mapData[MapNum].MaxX - 1; x++)
        {
            for (int y = 0; y <= ServerVars.mapData[MapNum].MaxY - 1; y++)
            {
                ServerVars.mapData[MapNum].Tile[x][y].Autotile = new int[5];
            }
        }

        for (int x = 0; x <= ServerVars.mapData[MapNum].MaxX - 1; x++)
        {
            for (int y = 0; y <= ServerVars.mapData[MapNum].MaxY - 1; y++) {
                ServerVars.mapData[MapNum].SoundID[x][y] = 0;
            }
        }
        //Arrays.fill(ServerVars.mapData[MapNum].SoundID, null);

        // Clear Map Cache //
        //Types.MapCache[MapNum].Data = null;
    }
    public static void checkMaps() {
        ServerVars.mapData = new mapData[ServerVars.MaxMaps + 1];
        for (int i = 1; i <= ServerVars.MaxMaps; i++)
        {
            ServerVars.mapData[i] = new mapData();
        }
        for (int i = 1; i <= ServerVars.MaxMaps; i++)
        {
            String absoPath = new File("").getAbsolutePath();
            String fileName = absoPath + "\\rtoserver\\data\\maps\\" + i + ".dat";
            File f = new File(fileName);
            if(!f.exists() && !f.isDirectory()) {
                clearMap(i);
                SaveData.saveMap(i);
            }
        }
    }
    public static void loadMaps() {
        checkMaps();
        for (int LoopI = 1; LoopI <= ServerVars.MaxMaps; LoopI++)
        {
            clearMap(LoopI);
            loadMap(LoopI);
            //cacheMap(LoopI);
            //Application.DoEvents();
        }

    }
    public static void loadMap(int mapNum) {
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
            ServerVars.mapData[mapNum] = (mapData) inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
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
            ServerVars.npcs[npcNum] = (NPC_Struct) inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
    }
    public static void loadNPCs() {
        checkNPCs();
        for (int LoopI = 1; LoopI <= ServerVars.MaxNPCs; LoopI++)
        {
            clearNPC(LoopI);
            loadNPC(LoopI);
        }

    }
    public static void checkNPCs() {
        ServerVars.npcs = new NPC_Struct[ServerVars.MaxNPCs + 1];
        for (int i = 1; i <= ServerVars.MaxNPCs; i++)
        {
            ServerVars.npcs[i] = new NPC_Struct();
        }
        for (int i = 1; i <= ServerVars.MaxNPCs; i++)
        {
            String absoPath = new File("").getAbsolutePath();
            String fileName = absoPath + "\\rtoserver\\data\\npcs\\" + i + ".dat";
            File f = new File(fileName);
            if(!f.exists() && !f.isDirectory()) {
                clearNPC(i);
                SaveData.saveNPC(i);
            }
        }
    }
    public static void clearNPC(int npcNum) {
        ServerVars.npcs[npcNum].Name = "";

        ServerVars.npcs[npcNum].Sprite = 0;
        ServerVars.npcs[npcNum].Range = 0;
        ServerVars.npcs[npcNum].Behaviour = 0;
        ServerVars.npcs[npcNum].SpawnSecs = 0;
        ServerVars.npcs[npcNum].Health = 0;
        ServerVars.npcs[npcNum].Exp = 0;

        ServerVars.npcs[npcNum].STR = 0;
        ServerVars.npcs[npcNum].DEF = 0;
        ServerVars.npcs[npcNum].VIT = 0;
        ServerVars.npcs[npcNum].AGI = 0;
        ServerVars.npcs[npcNum].MAG = 0;

        ServerVars.npcs[npcNum].shopNum = 0;

        ServerVars.npcs[npcNum].weapon = 0;
        ServerVars.npcs[npcNum].armor = 0;
        ServerVars.npcs[npcNum].offhand = 0;
        ServerVars.npcs[npcNum].helmet = 0;
    }
    public static void checkItems() {
        ServerVars.Items = new Item_Struct[ServerVars.MaxItems + 1];
        for (int i = 1; i <= ServerVars.MaxItems; i++)
        {
            ServerVars.Items[i] = new Item_Struct();
        }
        for (int i = 1; i <= ServerVars.MaxItems; i++)
        {
            String absoPath = new File("").getAbsolutePath();
            String fileName = absoPath + "\\rtoserver\\data\\items\\" + i + ".dat";
            File f = new File(fileName);
            if(!f.exists() && !f.isDirectory()) {
                clearItem(i);
                SaveData.saveItem(i);
            }
        }
    }
    public static void clearItem(int itemNum) {
        ServerVars.Items[itemNum].Name = "";

        ServerVars.Items[itemNum].Icon = 0;
        ServerVars.Items[itemNum].itemType = 0;
        ServerVars.Items[itemNum].isStackable = 0;

        ServerVars.Items[itemNum].LVL = 0;
        ServerVars.Items[itemNum].HP = 0;
        ServerVars.Items[itemNum].MP = 0;

        ServerVars.Items[itemNum].STR = 0;
        ServerVars.Items[itemNum].DEF = 0;
        ServerVars.Items[itemNum].VIT = 0;
        ServerVars.Items[itemNum].AGI = 0;
        ServerVars.Items[itemNum].MAG = 0;

        ServerVars.Items[itemNum].Cost = 0;
    }
    public static void loadItems() {
        checkItems();
        for (int LoopI = 1; LoopI <= ServerVars.MaxItems; LoopI++)
        {
            clearItem(LoopI);
            loadItem(LoopI);
            //cacheMap(LoopI);
            //Application.DoEvents();
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
            ServerVars.Items[itemNum] = (Item_Struct) inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
    }
    public static void checkShops() {
        ServerVars.Shops = new Shop_Struct[ServerVars.MaxShops + 1];
        for (int i = 1; i <= ServerVars.MaxShops; i++) {
            ServerVars.Shops[i] = new Shop_Struct();
        }
        for (int i = 1; i <= ServerVars.MaxShops; i++) {
            String absoPath = new File("").getAbsolutePath();
            String fileName = absoPath + "\\rtoserver\\data\\shops\\" + i + ".dat";
            File f = new File(fileName);
            if(!f.exists() && !f.isDirectory()) {
                clearShop(i);
                SaveData.saveShop(i);
            }
        }
    }
    public static void clearShop(int shopNum) {
        ServerVars.Shops[shopNum].Name = "";

        ServerVars.Shops[shopNum].welcomeMsg = "";
        ServerVars.Shops[shopNum].goodbyeMsg = "";

        ServerVars.Shops[shopNum].salesTax = 0;

        for (int i = 1; i <= 20; i++) {
            ServerVars.Shops[shopNum].itemNum[i] = 0;
            ServerVars.Shops[shopNum].itemVal[i] = 0;
        }
    }
    public static void loadShops() {
        checkShops();
        for (int LoopI = 1; LoopI <= ServerVars.MaxShops; LoopI++)
        {
            clearShop(LoopI);
            loadShop(LoopI);
            //cacheMap(LoopI);
            //Application.DoEvents();
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
            ServerVars.Shops[shopNum] = (Shop_Struct) inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
    }
    public static void checkSpells() {
        ServerVars.Spells = new Spell_Struct[ServerVars.MaxSpells + 1];
        for (int i = 1; i <= ServerVars.MaxSpells; i++) {
            ServerVars.Spells[i] = new Spell_Struct();
        }
        for (int i = 1; i <= ServerVars.MaxSpells; i++) {
            String absoPath = new File("").getAbsolutePath();
            String fileName = absoPath + "\\rtoserver\\data\\spells\\" + i + ".dat";
            File f = new File(fileName);
            if(!f.exists() && !f.isDirectory()) {
                clearSpell(i);
                SaveData.saveSpell(i);
            }
        }
    }
    public static void clearSpell(int spellNum) {
        ServerVars.Spells[spellNum].Name = "";
        ServerVars.Spells[spellNum].Type = 0;
        ServerVars.Spells[spellNum].Icon = 0;

        ServerVars.Spells[spellNum].LevelReq = 0;
        ServerVars.Spells[spellNum].ClassReq = 0;

        ServerVars.Spells[spellNum].Animation = 0;
        ServerVars.Spells[spellNum].AnimSpeed = 0;

        ServerVars.Spells[spellNum].CastTime = 0;
        ServerVars.Spells[spellNum].CoolDown = 0;

        ServerVars.Spells[spellNum].MPCost = 0;
        ServerVars.Spells[spellNum].DmgHealAmt = 0;
    }
    public static void loadSpells() {
        checkSpells();
        for (int LoopI = 1; LoopI <= ServerVars.MaxSpells; LoopI++)
        {
            clearSpell(LoopI);
            loadSpell(LoopI);
        }
    }
    public static void loadSpell(int spellNum) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\spells\\" + spellNum + ".dat";

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
            ServerVars.Spells[spellNum] = (Spell_Struct) inputStream.readObject();

            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        }
    }
}
