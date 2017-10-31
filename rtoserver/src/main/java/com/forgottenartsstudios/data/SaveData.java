package com.forgottenartsstudios.data;

import com.forgottenartsstudios.helpers.ServerVars;
import com.forgottenartsstudios.networking.packets.NewAccount;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by forgo on 10/6/2017.
 */

public class SaveData {
    public static void SaveAccount(AccountData account) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\accounts\\" + account.getID() + ".dat";

        //creating the space in memory for the file
        ObjectOutputStream outputStream = null;

        try{
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        }catch(IOException e){
            System.out.println("Could not open the file." + e);
            System.exit(0);
        }

        try{
            outputStream.writeObject(account.getID());
            outputStream.writeObject(account.getPW());

            for (int i = 1; i <= 3; i++) {
                outputStream.writeObject(account.chars[i].getName());

                outputStream.writeObject(account.chars[i].getJob());
                outputStream.writeObject(account.chars[i].getSprite());
                outputStream.writeObject(account.chars[i].getLevel());
                outputStream.writeObject(account.chars[i].getPoints());

                outputStream.writeObject(account.chars[i].getHP());
                outputStream.writeObject(account.chars[i].getMP());
                outputStream.writeObject(account.chars[i].getMaxHP());
                outputStream.writeObject(account.chars[i].getMaxMP());

                outputStream.writeObject(account.chars[i].getEXP());
                outputStream.writeObject(account.chars[i].getNextLVL());

                outputStream.writeObject(account.chars[i].getMap());
                outputStream.writeObject(account.chars[i].getX());
                outputStream.writeObject(account.chars[i].getY());
                outputStream.writeObject(account.chars[i].getDir());

                outputStream.writeObject(account.chars[i].getSTR());
                outputStream.writeObject(account.chars[i].getDEF());
                outputStream.writeObject(account.chars[i].getVIT());
                outputStream.writeObject(account.chars[i].getAGI());
                outputStream.writeObject(account.chars[i].getMAG());

                outputStream.writeObject(account.chars[i].getWeapon());
                outputStream.writeObject(account.chars[i].getOffhand());
                outputStream.writeObject(account.chars[i].getArmor());
                outputStream.writeObject(account.chars[i].getHelmet());
                outputStream.writeObject(account.chars[i].getAcc1());
                outputStream.writeObject(account.chars[i].getAcc2());

                for (int a = 1; a <= 60; a++) {
                    outputStream.writeObject(account.chars[i].inventory[a].getItemNum());
                    outputStream.writeObject(account.chars[i].inventory[a].getItemVal());
                }
            }

            outputStream.close();

        }catch(IOException e){
            System.out.println("Writing error: " + e);
            System.exit(0);
        }
    }
    public static void saveMap(int mapNum) {
        String absoPath = new File("").getAbsolutePath();
        String fileName = absoPath + "\\rtoserver\\data\\maps\\" + mapNum + ".dat";

        ObjectOutputStream  outputStream = null;

        try{
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        }catch(IOException e){
            System.out.println("Could not open the file." + e);
            System.exit(0);
        }

        try{
            outputStream.writeObject(ServerVars.mapData[mapNum]);

            outputStream.close();

        }catch(IOException e){
            System.out.println("Writing error: " + e);
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
            outputStream.writeObject(ServerVars.npcs[npcNum]);

            outputStream.close();
        }catch(IOException e){
            System.out.println("Writing error: " + e);
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
            outputStream.writeObject(ServerVars.Items[itemNum]);

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
            outputStream.writeObject(ServerVars.Shops[shopNum]);

            outputStream.close();
        }catch(IOException e){
            System.out.println("Writing error: " + e);
            System.exit(0);
        }
    }
}
