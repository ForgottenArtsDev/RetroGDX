package com.forgottenartsstudios.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.forgottenartsstudios.gameworld.GameRenderer;
import com.forgottenartsstudios.helpers.AssetLoader;
import com.forgottenartsstudios.helpers.Variables;

import static com.forgottenartsstudios.gameworld.GameRenderer.batcher;

/**
 * Created by forgo on 11/4/2017.
 */

public class RenderDesktop {
    public static float tapAlpha = 1f;
    public static float oldAlpha;
    public static boolean tapPlus;
    public static Color c;
    public static GlyphLayout layout = new GlyphLayout();

    ////////////////////
    // Routine Timers //
    ////////////////////
    private static final long UpdateTime_InputTimer = 50;
    private static final long UpdateTime_TapAnimTimer = 50;
    private static final long UpdateTime_BuyMsg = 1000;
    private static final long UpdateTime_Damage = 50;
    private static final long UpdateTime_LongPress = 1000;
    private static final long UpdateTime_Loading = 500;

    ////////////////////////////
    // Update Routines Checks //
    ////////////////////////////
    private static long LastUpdateTime_InputTimer;
    private static long LastUpdateTime_TapAnim;
    private static long LastUpdateTime_BuyMsg;
    private static long LastUpdateTime_Damage;
    private static long LastUpdateTime_LongPress;
    private static long LastUpdateTime_Loading;

    public static void gsTitleScreen(long tickCount) {
        if (LastUpdateTime_TapAnim < tickCount) {

            if (tapAlpha <= 0f) {
                tapPlus = true;
            } else if (tapAlpha >= 1f) {
                tapPlus = false;
            }

            if (tapPlus) {
                tapAlpha = tapAlpha + 0.1f;
            } else {
                tapAlpha = tapAlpha - 0.1f;
            }

            LastUpdateTime_TapAnim = tickCount + UpdateTime_TapAnimTimer;
        }

        batcher.draw(AssetLoader.mainMenuBG, 0, 0, 480, 854, 0, 0, 480, 854, false, true);

        // Tap Flash
        c = batcher.getColor();
        oldAlpha = c.a;

        c.a = tapAlpha;
        batcher.setColor(c);

        batcher.draw(AssetLoader.mainMenuTap, 0, 0, 480, 854, 0, 0, 480, 854, false, true);

        c.a = oldAlpha;
        batcher.setColor(c);

        c = batcher.getColor();
        oldAlpha = c.a;

        drawText("Server Status: ", 105, 300, Color.WHITE);
        if (Variables.serverOnline) {
            drawText("Online", 305, 300, Color.GREEN);
        } else {
            drawText("Offline", 305, 300, Color.RED);
        }
    }
    public static void gsLogin(long tickCount) {
        batcher.draw(AssetLoader.loginMenuBG, 0, 0, 800, 600, 0, 0, 800, 600, false, true);

        if (Variables.Login_ID != null && !Variables.Login_ID.isEmpty()) {
            String id = Variables.Login_ID;
            drawText(Variables.Login_ID, 225, 340, Color.WHITE);
        }
        if (Variables.Login_PW != null && !Variables.Login_PW.isEmpty()) {
            String starPW = "";
            for (int i = 1; i <= Variables.Login_PW.length(); i++) {
                starPW = starPW + "*";
            }
            drawText(starPW, 225, 408, Color.WHITE);
        }
        if (Variables.saveLogin) {
            drawText("x", 225, 444, Color.WHITE);
        }
        if (Variables.AccountNotFound) {
            drawText("Account Not Found", 220, 501, Color.RED);
        }
        if (Variables.PasswordReq) {
            drawText("Password Required", 220, 501, Color.RED);
        }
        if (Variables.IDReq) {
            drawText("ID Required", 220, 501, Color.RED);
        }
        if (Variables.AccountRegistered) {
            drawText("Account Registered", 220, 501, Color.YELLOW);
        }

        drawText("Server Status: ", 265, 230, Color.WHITE);
        if (Variables.serverOnline) {
            drawText("Online", 455, 230, Color.GREEN);
        } else {
            drawText("Offline", 455, 230, Color.RED);
        }
    }
    public static void gsCharSelect(long tickCount) {
        batcher.draw(AssetLoader.charSelectBG, 0, 0, 800, 600, 0, 0, 800, 600, false, true);

        if (Variables.MyAccount.chars[1].getName() != null && !Variables.MyAccount.chars[1].getName().isEmpty()) {
            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.MyAccount.chars[1].getSprite()], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 125, 290);

            layout.setText(AssetLoader.font, Variables.MyAccount.chars[1].getName());
            float width = layout.width;// contains the width of the current set text
            drawText(Variables.MyAccount.chars[1].getName(), 139 - (width / 2), 335, Color.WHITE);
            if (Variables.MyAccount.chars[1].getJob() == Variables.JOB_WARRIOR) {
                layout.setText(AssetLoader.font, "Warrior");
                width = layout.width;// contains the width of the current set text
                drawText("Warrior", 139 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[1].getJob() == Variables.JOB_WIZARD) {
                layout.setText(AssetLoader.font, "Mage");
                width = layout.width;// contains the width of the current set text
                drawText("Mage", 139 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[1].getJob() == Variables.JOB_CLERIC) {
                layout.setText(AssetLoader.font, "Cleric");
                width = layout.width;// contains the width of the current set text
                drawText("Cleric", 139 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[1].getJob() == Variables.JOB_RANGER) {
                layout.setText(AssetLoader.font, "Ranger");
                width = layout.width;// contains the width of the current set text
                drawText("Ranger", 139 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[1].getJob() == Variables.JOB_ROGUE) {
                layout.setText(AssetLoader.font, "Rogue");
                width = layout.width;// contains the width of the current set text
                drawText("Rogue", 139 - (width / 2), 355, Color.WHITE);
            }
            layout.setText(AssetLoader.font, "Lvl: " + Variables.MyAccount.chars[1].getLevel());
            width = layout.width;// contains the width of the current set text
            drawText("Lvl: " + Variables.MyAccount.chars[1].getLevel(), 139 - (width / 2), 375, Color.WHITE);
            layout.setText(AssetLoader.font, "HP: " + Variables.MyAccount.chars[1].getHP() + "/" + Variables.MyAccount.chars[1].getMaxHP());
            width = layout.width;// contains the width of the current set text
            drawText("HP: " + Variables.MyAccount.chars[1].getHP() + "/" + Variables.MyAccount.chars[1].getMaxHP(), 139 - (width / 2), 395, Color.WHITE);
            layout.setText(AssetLoader.font, "MP: " + Variables.MyAccount.chars[1].getMP() + "/" + Variables.MyAccount.chars[1].getMaxMP());
            width = layout.width;// contains the width of the current set text
            drawText("MP: " + Variables.MyAccount.chars[1].getMP() + "/" + Variables.MyAccount.chars[1].getMaxMP(), 139 - (width / 2), 415, Color.WHITE);

            // STATS
            layout.setText(AssetLoader.font, "STR: " + Variables.MyAccount.chars[1].getSTR());
            width = layout.width;// contains the width of the current set text
            drawText("STR: " + Variables.MyAccount.chars[1].getSTR(), 139 - (width / 2), 435, Color.WHITE);
            layout.setText(AssetLoader.font, "DEF: " + Variables.MyAccount.chars[1].getDEF());
            width = layout.width;// contains the width of the current set text
            drawText("DEF: " + Variables.MyAccount.chars[1].getDEF(), 139 - (width / 2), 455, Color.WHITE);
            layout.setText(AssetLoader.font, "VIT: " + Variables.MyAccount.chars[1].getVIT());
            width = layout.width;// contains the width of the current set text
            drawText("VIT: " + Variables.MyAccount.chars[1].getVIT(), 139 - (width / 2), 475, Color.WHITE);
            layout.setText(AssetLoader.font, "AGI: " + Variables.MyAccount.chars[1].getAGI());
            width = layout.width;// contains the width of the current set text
            drawText("AGI: " + Variables.MyAccount.chars[1].getAGI(), 139 - (width / 2), 495, Color.WHITE);
            layout.setText(AssetLoader.font, "MAG: " + Variables.MyAccount.chars[1].getMAG());
            width = layout.width;// contains the width of the current set text
            drawText("MAG: " + Variables.MyAccount.chars[1].getMAG(), 139 - (width / 2), 515, Color.WHITE);
        } else {
            layout.setText(AssetLoader.font, "Create");
            float width = layout.width;// contains the width of the current set text
            drawText("Create", 139 - (width / 2), 405, Color.WHITE);
        }
        if (Variables.MyAccount.chars[2].getName() != null && !Variables.MyAccount.chars[2].getName().isEmpty()) {
            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.MyAccount.chars[2].getSprite()], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 385, 290);

            layout.setText(AssetLoader.font, Variables.MyAccount.chars[1].getName());
            float width = layout.width;// contains the width of the current set text
            drawText(Variables.MyAccount.chars[2].getName(), 399 - (width / 2), 335, Color.WHITE);
            if (Variables.MyAccount.chars[2].getJob() == Variables.JOB_WARRIOR) {
                layout.setText(AssetLoader.font, "Warrior");
                width = layout.width;// contains the width of the current set text
                drawText("Warrior", 399 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[2].getJob() == Variables.JOB_WIZARD) {
                layout.setText(AssetLoader.font, "Mage");
                width = layout.width;// contains the width of the current set text
                drawText("Mage", 399 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[2].getJob() == Variables.JOB_CLERIC) {
                layout.setText(AssetLoader.font, "Cleric");
                width = layout.width;// contains the width of the current set text
                drawText("Cleric", 399 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[2].getJob() == Variables.JOB_RANGER) {
                layout.setText(AssetLoader.font, "Ranger");
                width = layout.width;// contains the width of the current set text
                drawText("Ranger", 399 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[2].getJob() == Variables.JOB_ROGUE) {
                layout.setText(AssetLoader.font, "Rogue");
                width = layout.width;// contains the width of the current set text
                drawText("Rogue", 399 - (width / 2), 355, Color.WHITE);
            }
            layout.setText(AssetLoader.font, "Lvl: " + Variables.MyAccount.chars[2].getLevel());
            width = layout.width;// contains the width of the current set text
            drawText("Lvl: " + Variables.MyAccount.chars[2].getLevel(), 399 - (width / 2), 375, Color.WHITE);
            layout.setText(AssetLoader.font, "HP: " + Variables.MyAccount.chars[2].getHP() + "/" + Variables.MyAccount.chars[2].getMaxHP());
            width = layout.width;// contains the width of the current set text
            drawText("HP: " + Variables.MyAccount.chars[2].getHP() + "/" + Variables.MyAccount.chars[2].getMaxHP(), 399 - (width / 2), 395, Color.WHITE);
            layout.setText(AssetLoader.font, "MP: " + Variables.MyAccount.chars[2].getMP() + "/" + Variables.MyAccount.chars[2].getMaxMP());
            width = layout.width;// contains the width of the current set text
            drawText("MP: " + Variables.MyAccount.chars[2].getMP() + "/" + Variables.MyAccount.chars[2].getMaxMP(), 399 - (width / 2), 415, Color.WHITE);

            // STATS
            layout.setText(AssetLoader.font, "STR: " + Variables.MyAccount.chars[2].getSTR());
            width = layout.width;// contains the width of the current set text
            drawText("STR: " + Variables.MyAccount.chars[2].getSTR(), 399 - (width / 2), 435, Color.WHITE);
            layout.setText(AssetLoader.font, "DEF: " + Variables.MyAccount.chars[2].getDEF());
            width = layout.width;// contains the width of the current set text
            drawText("DEF: " + Variables.MyAccount.chars[2].getDEF(), 399 - (width / 2), 455, Color.WHITE);
            layout.setText(AssetLoader.font, "VIT: " + Variables.MyAccount.chars[2].getVIT());
            width = layout.width;// contains the width of the current set text
            drawText("VIT: " + Variables.MyAccount.chars[2].getVIT(), 399 - (width / 2), 475, Color.WHITE);
            layout.setText(AssetLoader.font, "AGI: " + Variables.MyAccount.chars[2].getAGI());
            width = layout.width;// contains the width of the current set text
            drawText("AGI: " + Variables.MyAccount.chars[2].getAGI(), 399 - (width / 2), 495, Color.WHITE);
            layout.setText(AssetLoader.font, "MAG: " + Variables.MyAccount.chars[2].getMAG());
            width = layout.width;// contains the width of the current set text
            drawText("MAG: " + Variables.MyAccount.chars[2].getMAG(), 399 - (width / 2), 515, Color.WHITE);
        } else {
            layout.setText(AssetLoader.font, "Create");
            float width = layout.width;// contains the width of the current set text
            drawText("Create", 399 - (width / 2), 405, Color.WHITE);
        }
        if (Variables.MyAccount.chars[3].getName() != null && !Variables.MyAccount.chars[3].getName().isEmpty()) {
            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.MyAccount.chars[3].getSprite()], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 646, 290);

            layout.setText(AssetLoader.font, Variables.MyAccount.chars[3].getName());
            float width = layout.width;// contains the width of the current set text
            drawText(Variables.MyAccount.chars[3].getName(), 660 - (width / 2), 335, Color.WHITE);
            if (Variables.MyAccount.chars[3].getJob() == Variables.JOB_WARRIOR) {
                layout.setText(AssetLoader.font, "Warrior");
                width = layout.width;// contains the width of the current set text
                drawText("Warrior", 660 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[3].getJob() == Variables.JOB_WIZARD) {
                layout.setText(AssetLoader.font, "Mage");
                width = layout.width;// contains the width of the current set text
                drawText("Mage", 660 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[3].getJob() == Variables.JOB_CLERIC) {
                layout.setText(AssetLoader.font, "Cleric");
                width = layout.width;// contains the width of the current set text
                drawText("Cleric", 660 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[3].getJob() == Variables.JOB_RANGER) {
                layout.setText(AssetLoader.font, "Ranger");
                width = layout.width;// contains the width of the current set text
                drawText("Ranger", 660 - (width / 2), 355, Color.WHITE);
            } else if (Variables.MyAccount.chars[3].getJob() == Variables.JOB_ROGUE) {
                layout.setText(AssetLoader.font, "Rogue");
                width = layout.width;// contains the width of the current set text
                drawText("Rogue", 660 - (width / 2), 355, Color.WHITE);
            }
            layout.setText(AssetLoader.font, "Lvl: " + Variables.MyAccount.chars[3].getLevel());
            width = layout.width;// contains the width of the current set text
            drawText("Lvl: " + Variables.MyAccount.chars[3].getLevel(), 660 - (width / 2), 375, Color.WHITE);
            layout.setText(AssetLoader.font, "HP: " + Variables.MyAccount.chars[3].getHP() + "/" + Variables.MyAccount.chars[3].getMaxHP());
            width = layout.width;// contains the width of the current set text
            drawText("HP: " + Variables.MyAccount.chars[3].getHP() + "/" + Variables.MyAccount.chars[3].getMaxHP(), 660 - (width / 2), 395, Color.WHITE);
            layout.setText(AssetLoader.font, "MP: " + Variables.MyAccount.chars[3].getMP() + "/" + Variables.MyAccount.chars[3].getMaxMP());
            width = layout.width;// contains the width of the current set text
            drawText("MP: " + Variables.MyAccount.chars[3].getMP() + "/" + Variables.MyAccount.chars[3].getMaxMP(), 660 - (width / 2), 415, Color.WHITE);

            // STATS
            layout.setText(AssetLoader.font, "STR: " + Variables.MyAccount.chars[3].getSTR());
            width = layout.width;// contains the width of the current set text
            drawText("STR: " + Variables.MyAccount.chars[3].getSTR(), 660 - (width / 2), 435, Color.WHITE);
            layout.setText(AssetLoader.font, "DEF: " + Variables.MyAccount.chars[3].getDEF());
            width = layout.width;// contains the width of the current set text
            drawText("DEF: " + Variables.MyAccount.chars[3].getDEF(), 660 - (width / 2), 455, Color.WHITE);
            layout.setText(AssetLoader.font, "VIT: " + Variables.MyAccount.chars[3].getVIT());
            width = layout.width;// contains the width of the current set text
            drawText("VIT: " + Variables.MyAccount.chars[3].getVIT(), 660 - (width / 2), 475, Color.WHITE);
            layout.setText(AssetLoader.font, "AGI: " + Variables.MyAccount.chars[3].getAGI());
            width = layout.width;// contains the width of the current set text
            drawText("AGI: " + Variables.MyAccount.chars[3].getAGI(), 660 - (width / 2), 495, Color.WHITE);
            layout.setText(AssetLoader.font, "MAG: " + Variables.MyAccount.chars[3].getMAG());
            width = layout.width;// contains the width of the current set text
            drawText("MAG: " + Variables.MyAccount.chars[3].getMAG(), 660 - (width / 2), 515, Color.WHITE);
        } else {
            layout.setText(AssetLoader.font, "Create");
            float width = layout.width;// contains the width of the current set text
            drawText("Create", 660 - (width / 2), 405, Color.WHITE);
        }
    }
    public static void gsCharCreate(long tickCount) {
        batcher.draw(AssetLoader.charCreateBG, 0, 0, 800, 600, 0, 0, 800, 600, false, true);
        if (Variables.TempName != null && !Variables.TempName.isEmpty()) {
            if (Variables.inputName) {
                drawText(Variables.TempName + "|", 225, 299, Color.WHITE);
            } else {
                drawText(Variables.TempName, 225, 299, Color.WHITE);
            }
        } else {
            if (Variables.inputName) {
                drawText("|", 225, 299, Color.WHITE);
            }
        }

        if (Variables.TempJob == Variables.JOB_WARRIOR) {
            batcher.draw(AssetLoader.warriorEmb, 155, 327, 90, 90, 0, 0, 90, 90, false, true);
            layout.setText(AssetLoader.font, "Job: Warrior");
            float width = layout.width;

            drawText("Job: Warrior", 300 - ((int)width / 2), 425, Color.WHITE);

            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.TempSprite], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 500 - 16, 490);

            // HP-MP
            layout.setText(AssetLoader.font, "HP: " + (Jobs.Warrior.getVIT() * 2) * (Jobs.Warrior.getSTR() / 2));
            width = layout.width;
            drawText("HP: " + (Jobs.Warrior.getVIT() * 2) * (Jobs.Warrior.getSTR() / 2), 300 - (width / 2), 445, Color.WHITE);
            layout.setText(AssetLoader.font, "MP: " + (Jobs.Warrior.getMAG() * 2) * (Jobs.Warrior.getDEF() / 2));
            width = layout.width;
            drawText("MP: " + (Jobs.Warrior.getMAG() * 2) * (Jobs.Warrior.getDEF() / 2), 300 - (width / 2), 465, Color.WHITE);

            // STATS
            layout.setText(AssetLoader.font, "STR: " + Jobs.Warrior.getSTR());
            width = layout.width;
            drawText("STR: " + Jobs.Warrior.getSTR(), 300 - (width / 2), 485, Color.WHITE);
            layout.setText(AssetLoader.font, "DEF: " + Jobs.Warrior.getDEF());
            width = layout.width;
            drawText("DEF: " + Jobs.Warrior.getDEF(), 300 - (width / 2), 505, Color.WHITE);
            layout.setText(AssetLoader.font, "VIT: " + Jobs.Warrior.getVIT());
            width = layout.width;
            drawText("VIT: " + Jobs.Warrior.getVIT(), 300 - (width / 2), 525, Color.WHITE);
            layout.setText(AssetLoader.font, "AGI: " + Jobs.Warrior.getAGI());
            width = layout.width;
            drawText("AGI: " + Jobs.Warrior.getAGI(), 300 - (width / 2), 545, Color.WHITE);
            layout.setText(AssetLoader.font, "MAG: " + Jobs.Warrior.getMAG());
            width = layout.width;
            drawText("MAG: " + Jobs.Warrior.getMAG(), 300 - (width / 2), 565, Color.WHITE);
        } else {
            batcher.draw(AssetLoader.warriorEmbT, 155, 327, 90, 90, 0, 0, 90, 90, false, true);
        }
        if (Variables.TempJob == Variables.JOB_WIZARD) {
            batcher.draw(AssetLoader.wizardEmb, 255, 327, 90, 90, 0, 0, 90, 90, false, true);
            layout.setText(AssetLoader.font, "Job: Wizard");
            float width = layout.width;

            drawText("Job: Wizard", 300 - ((int)width / 2), 425, Color.WHITE);

            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.TempSprite], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 500 - 16, 490);

            // HP-MP
            layout.setText(AssetLoader.font, "HP: " + (Jobs.Wizard.getVIT() * 2) * (Jobs.Wizard.getSTR() / 2));
            width = layout.width;
            drawText("HP: " + (Jobs.Wizard.getVIT() * 2) * (Jobs.Wizard.getSTR() / 2), 300 - (width / 2), 445, Color.WHITE);
            layout.setText(AssetLoader.font, "MP: " + (Jobs.Wizard.getMAG() * 2) * (Jobs.Wizard.getDEF() / 2));
            width = layout.width;
            drawText("MP: " + (Jobs.Wizard.getMAG() * 2) * (Jobs.Wizard.getDEF() / 2), 300 - (width / 2), 465, Color.WHITE);

            // STATS
            layout.setText(AssetLoader.font, "STR: " + Jobs.Wizard.getSTR());
            width = layout.width;
            drawText("STR: " + Jobs.Wizard.getSTR(), 300 - (width / 2), 485, Color.WHITE);
            layout.setText(AssetLoader.font, "DEF: " + Jobs.Wizard.getDEF());
            width = layout.width;
            drawText("DEF: " + Jobs.Wizard.getDEF(), 300 - (width / 2), 505, Color.WHITE);
            layout.setText(AssetLoader.font, "VIT: " + Jobs.Wizard.getVIT());
            width = layout.width;
            drawText("VIT: " + Jobs.Wizard.getVIT(), 300 - (width / 2), 525, Color.WHITE);
            layout.setText(AssetLoader.font, "AGI: " + Jobs.Wizard.getAGI());
            width = layout.width;
            drawText("AGI: " + Jobs.Wizard.getAGI(), 300 - (width / 2), 545, Color.WHITE);
            layout.setText(AssetLoader.font, "MAG: " + Jobs.Wizard.getMAG());
            width = layout.width;
            drawText("MAG: " + Jobs.Wizard.getMAG(), 300 - (width / 2), 565, Color.WHITE);
        } else {
            batcher.draw(AssetLoader.wizardEmbT, 255, 327, 90, 90, 0, 0, 90, 90, false, true);
        }
        if (Variables.TempJob == Variables.JOB_CLERIC) {
            batcher.draw(AssetLoader.clericEmb, 355, 327, 90, 90, 0, 0, 90, 90, false, true);
            layout.setText(AssetLoader.font, "Job: Cleric");
            float width = layout.width;

            drawText("Job: Cleric", 300 - ((int)width / 2), 425, Color.WHITE);

            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.TempSprite], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 500 - 16, 490);

            // HP-MP
            layout.setText(AssetLoader.font, "HP: " + (Jobs.Cleric.getVIT() * 2) * (Jobs.Cleric.getSTR() / 2));
            width = layout.width;
            drawText("HP: " + (Jobs.Cleric.getVIT() * 2) * (Jobs.Cleric.getSTR() / 2), 300 - (width / 2), 445, Color.WHITE);
            layout.setText(AssetLoader.font, "MP: " + (Jobs.Cleric.getMAG() * 2) * (Jobs.Cleric.getDEF() / 2));
            width = layout.width;
            drawText("MP: " + (Jobs.Cleric.getMAG() * 2) * (Jobs.Cleric.getDEF() / 2), 300 - (width / 2), 465, Color.WHITE);

            // STATS
            layout.setText(AssetLoader.font, "STR: " + Jobs.Cleric.getSTR());
            width = layout.width;
            drawText("STR: " + Jobs.Cleric.getSTR(), 300 - (width / 2), 485, Color.WHITE);
            layout.setText(AssetLoader.font, "DEF: " + Jobs.Cleric.getDEF());
            width = layout.width;
            drawText("DEF: " + Jobs.Cleric.getDEF(), 300 - (width / 2), 505, Color.WHITE);
            layout.setText(AssetLoader.font, "VIT: " + Jobs.Cleric.getVIT());
            width = layout.width;
            drawText("VIT: " + Jobs.Cleric.getVIT(), 300 - (width / 2), 525, Color.WHITE);
            layout.setText(AssetLoader.font, "AGI: " + Jobs.Cleric.getAGI());
            width = layout.width;
            drawText("AGI: " + Jobs.Cleric.getAGI(), 300 - (width / 2), 545, Color.WHITE);
            layout.setText(AssetLoader.font, "MAG: " + Jobs.Cleric.getMAG());
            width = layout.width;
            drawText("MAG: " + Jobs.Cleric.getMAG(), 300 - (width / 2), 565, Color.WHITE);
        } else {
            batcher.draw(AssetLoader.clericEmbT, 355, 327, 90, 90, 0, 0, 90, 90, false, true);
        }
        if (Variables.TempJob == Variables.JOB_RANGER) {
            batcher.draw(AssetLoader.wizardEmb, 455, 327, 90, 90, 0, 0, 90, 90, false, true);
            layout.setText(AssetLoader.font, "Job: Ranger");
            float width = layout.width;

            drawText("Job: Ranger", 300 - ((int)width / 2), 425, Color.WHITE);

            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.TempSprite], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 500 - 16, 490);

            // HP-MP
            layout.setText(AssetLoader.font, "HP: " + (Jobs.Ranger.getVIT() * 2) * (Jobs.Ranger.getSTR() / 2));
            width = layout.width;
            drawText("HP: " + (Jobs.Ranger.getVIT() * 2) * (Jobs.Ranger.getSTR() / 2), 300 - (width / 2), 445, Color.WHITE);
            layout.setText(AssetLoader.font, "MP: " + (Jobs.Ranger.getMAG() * 2) * (Jobs.Ranger.getDEF() / 2));
            width = layout.width;
            drawText("MP: " + (Jobs.Ranger.getMAG() * 2) * (Jobs.Ranger.getDEF() / 2), 300 - (width / 2), 465, Color.WHITE);

            // STATS
            layout.setText(AssetLoader.font, "STR: " + Jobs.Ranger.getSTR());
            width = layout.width;
            drawText("STR: " + Jobs.Ranger.getSTR(), 300 - (width / 2), 485, Color.WHITE);
            layout.setText(AssetLoader.font, "DEF: " + Jobs.Ranger.getDEF());
            width = layout.width;
            drawText("DEF: " + Jobs.Ranger.getDEF(), 300 - (width / 2), 505, Color.WHITE);
            layout.setText(AssetLoader.font, "VIT: " + Jobs.Ranger.getVIT());
            width = layout.width;
            drawText("VIT: " + Jobs.Ranger.getVIT(), 300 - (width / 2), 525, Color.WHITE);
            layout.setText(AssetLoader.font, "AGI: " + Jobs.Ranger.getAGI());
            width = layout.width;
            drawText("AGI: " + Jobs.Ranger.getAGI(), 300 - (width / 2), 545, Color.WHITE);
            layout.setText(AssetLoader.font, "MAG: " + Jobs.Ranger.getMAG());
            width = layout.width;
            drawText("MAG: " + Jobs.Ranger.getMAG(), 300 - (width / 2), 565, Color.WHITE);
        } else {
            batcher.draw(AssetLoader.rangerEmbT, 455, 327, 90, 90, 0, 0, 90, 90, false, true);
        }
        if (Variables.TempJob == Variables.JOB_ROGUE) {
            batcher.draw(AssetLoader.rogueEmb, 555, 327, 90, 90, 0, 0, 90, 90, false, true);
            layout.setText(AssetLoader.font, "Job: Rogue");
            float width = layout.width;

            drawText("Job: Rogue", 300 - ((int)width / 2), 425, Color.WHITE);

            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.TempSprite], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 500 - 16, 490);

            // HP-MP
            layout.setText(AssetLoader.font, "HP: " + (Jobs.Rogue.getVIT() * 2) * (Jobs.Rogue.getSTR() / 2));
            width = layout.width;
            drawText("HP: " + (Jobs.Rogue.getVIT() * 2) * (Jobs.Rogue.getSTR() / 2), 300 - (width / 2), 445, Color.WHITE);
            layout.setText(AssetLoader.font, "MP: " + (Jobs.Rogue.getMAG() * 2) * (Jobs.Rogue.getDEF() / 2));
            width = layout.width;
            drawText("MP: " + (Jobs.Rogue.getMAG() * 2) * (Jobs.Rogue.getDEF() / 2), 300 - (width / 2), 465, Color.WHITE);

            // STATS
            layout.setText(AssetLoader.font, "STR: " + Jobs.Rogue.getSTR());
            width = layout.width;
            drawText("STR: " + Jobs.Rogue.getSTR(), 300 - (width / 2), 485, Color.WHITE);
            layout.setText(AssetLoader.font, "DEF: " + Jobs.Rogue.getDEF());
            width = layout.width;
            drawText("DEF: " + Jobs.Rogue.getDEF(), 300 - (width / 2), 505, Color.WHITE);
            layout.setText(AssetLoader.font, "VIT: " + Jobs.Rogue.getVIT());
            width = layout.width;
            drawText("VIT: " + Jobs.Rogue.getVIT(), 300 - (width / 2), 525, Color.WHITE);
            layout.setText(AssetLoader.font, "AGI: " + Jobs.Rogue.getAGI());
            width = layout.width;
            drawText("AGI: " + Jobs.Rogue.getAGI(), 300 - (width / 2), 545, Color.WHITE);
            layout.setText(AssetLoader.font, "MAG: " + Jobs.Rogue.getMAG());
            width = layout.width;
            drawText("MAG: " + Jobs.Rogue.getMAG(), 300 - (width / 2), 565, Color.WHITE);
        } else {
            batcher.draw(AssetLoader.rogueEmbT, 555, 327, 90, 90, 0, 0, 90, 90, false, true);
        }
        batcher.draw(AssetLoader.male, 414, 480, 60, 60, 0, 0, 60, 60, false, true);
        batcher.draw(AssetLoader.female, 526, 480, 60, 60, 0, 0, 60, 60, false, true);
    }
    private static int firstTick = 0;
    public static void gsLoading(long tickCount) {
        batcher.draw(AssetLoader.loadBG, 0, 0, 800, 600, 0, 0, 800, 600, false, true);

        if (firstTick == 0) {
            LastUpdateTime_Loading = tickCount;
            firstTick++;
        }

        drawText("Server Status: ", 265, 230, Color.WHITE);
        if (Variables.serverOnline) {
            drawText("Online", 455, 230, Color.GREEN);
        } else {
            drawText("Offline", 455, 230, Color.RED);
        }

        if (LastUpdateTime_Loading < tickCount) {
            //if (firstTick > 0) {
            AssetLoader.checkMaps();
            for (int i = 1; i <= Variables.MaxMaps; i++) {
                AssetLoader.loadMap(i);
                AssetLoader.cacheTiles(i);
            }
            Variables.Game_State = Variables.Game_State_Login;
            LastUpdateTime_Loading = tickCount + LastUpdateTime_Loading;
            //} else {
            //    firstTick++;
            //}
        }
    }
    public static void gsInGame(long tickCount) {
        // Search for NPC
        tryNPCSearch();

        // Timers
        buyMsgTimer(tickCount);
        longPressTimer(tickCount);
        drawDmgTimer(tickCount);

        if (LastUpdateTime_InputTimer < tickCount) {
            if (Variables.players[Variables.MyIndex].getMoving() == 0) {
                if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                    DesktopInputData.handleInput();
                } else if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                    DesktopInputData.handleAndroidInput();
                }
            }
            LastUpdateTime_InputTimer = tickCount + UpdateTime_InputTimer;
        }

        for (int i = 1; i <= Variables.MaxPlayers; i++) {
            processMovement(i);
        }
        for (int i = 1; i <= Variables.MaxMapNPCs; i++) {
            processNPCMovement(i);
        }

        DesktopInputData.checkMovement();
        DesktopInputData.checkAttack(tickCount);
        DesktopInputData.checkPickUp();

        batcher.draw(AssetLoader.inGameBG, 0, 0, 800, 600, 0, 0, 800, 600, false, true);
        renderMap_Lower();
        drawItems();
        drawPlayersAndNPCs(tickCount);
        renderMap_Upper();
        drawNames();
        drawUI();

        // FPS
        drawText("" + Gdx.graphics.getFramesPerSecond(), 12, 12, Color.WHITE);

        drawChat();

        if (Variables.inMenu) { drawMenu(); }
        if (Variables.inShop) { drawShop(); }
        if (Variables.inStatus) { drawStatus(); }
        if (Variables.inInventory) { drawInventory(); }
    }

    public static void drawText(String text, float X, float Y, Color color) {
        layout.setText(AssetLoader.font, text);
        float width = layout.width;// contains the width of the current set text

        //float nameX = (X - (int)width) - 20;
        //float nameY = Y + 20;

        AssetLoader.font.setColor(Color.BLACK);
        AssetLoader.font.draw(batcher, text, X - 2, Y);
        AssetLoader.font.draw(batcher, text, X + 2, Y);
        AssetLoader.font.draw(batcher, text, X, Y - 2);
        AssetLoader.font.draw(batcher, text, X, Y + 2);
        AssetLoader.font.setColor(color);
        AssetLoader.font.draw(batcher, text, X, Y);
    }
    public static void drawName(String text, float X, float Y, Color color) {
        layout.setText(AssetLoader.nameFont, text);
        float width = layout.width;// contains the width of the current set text

        //float nameX = (X - (int)width) - 20;
        //float nameY = Y + 20;

        AssetLoader.nameFont.setColor(Color.BLACK);
        AssetLoader.nameFont.draw(batcher, text, X - 2, Y);
        AssetLoader.nameFont.draw(batcher, text, X + 2, Y);
        AssetLoader.nameFont.draw(batcher, text, X, Y - 2);
        AssetLoader.nameFont.draw(batcher, text, X, Y + 2);
        AssetLoader.nameFont.setColor(color);
        AssetLoader.nameFont.draw(batcher, text, X, Y);
    }

    public static void renderPlayer(int i, float x, float y, long tickCount) {
        if (Variables.players[i] != null) {

            // Check for animation
            if (Variables.players[i].getAttacking() == 1) {
                if (Variables.players[i].getAttackTimer() + 500 > tickCount) {
                    Variables.players[i].setStep(2);
                }
            }

            if (Variables.players[i].getAttackTimer() + 1000 < tickCount) {
                Variables.players[i].setAttacking(0);
                Variables.players[i].setAttackTimer(0);
            }

            if (Variables.players[i].getSprite() <= 0) {
                return;
            }

            switch (Variables.players[i].getDir()) {
                case Variables.DIR_UP:
                    switch (Variables.players[i].getStep()) {
                        case 0:
                            GameRenderer.batcher.draw(AssetLoader.spritesUp1[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                        case 1:
                            GameRenderer.batcher.draw(AssetLoader.spritesUp2[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                        case 2:
                            GameRenderer.batcher.draw(AssetLoader.spritesUp3[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                    }
                    return;
                case Variables.DIR_DOWN:
                    switch (Variables.players[i].getStep()) {
                        case 0:
                            GameRenderer.batcher.draw(AssetLoader.spritesDown1[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                        case 1:
                            GameRenderer.batcher.draw(AssetLoader.spritesDown2[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                        case 2:
                            GameRenderer.batcher.draw(AssetLoader.spritesDown3[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                    }
                    return;
                case Variables.DIR_LEFT:
                    switch (Variables.players[i].getStep()) {
                        case 0:
                            GameRenderer.batcher.draw(AssetLoader.spritesLeft1[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                        case 1:
                            GameRenderer.batcher.draw(AssetLoader.spritesLeft2[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                        case 2:
                            GameRenderer.batcher.draw(AssetLoader.spritesLeft3[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                    }
                    return;
                case Variables.DIR_RIGHT:
                    switch (Variables.players[i].getStep()) {
                        case 0:
                            GameRenderer.batcher.draw(AssetLoader.spritesRight1[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                        case 1:
                            GameRenderer.batcher.draw(AssetLoader.spritesRight2[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                        case 2:
                            GameRenderer.batcher.draw(AssetLoader.spritesRight3[Variables.players[i].getSprite()], x + 8, y + 3, 32, 36);
                            break;
                    }
                    return;
            }
        }
    }
    public static void renderNPC(int mapNpcNum, float x, float y, long tickCount) {
        GameRenderer.batcher.enableBlending();

        // Check for animation
        if (Variables.MapNPCs[mapNpcNum].getAttacking() == 1) {
            if (Variables.MapNPCs[mapNpcNum].getAttackTimer() + 500 > tickCount) {
                Variables.MapNPCs[mapNpcNum].setStep(2);
            }
        }

        if (Variables.MapNPCs[mapNpcNum].getAttackTimer() + 1000 < tickCount) {
            Variables.MapNPCs[mapNpcNum].setAttacking(0);
            Variables.MapNPCs[mapNpcNum].setAttackTimer(0);
        }

        if (Variables.MapNPCs[mapNpcNum].getHP() <= 0) { return; }
        if (Variables.MapNPCs[mapNpcNum].getSprite() <= 0) { return; }
        if (Variables.MapNPCs[mapNpcNum] == null) { return; }

        switch (Variables.MapNPCs[mapNpcNum].getDir()) {
            case Variables.DIR_UP:
                switch (Variables.MapNPCs[mapNpcNum].getStep()) {
                    case 0:
                        GameRenderer.batcher.draw(AssetLoader.spritesUp1[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                    case 1:
                        GameRenderer.batcher.draw(AssetLoader.spritesUp2[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                    case 2:
                        GameRenderer.batcher.draw(AssetLoader.spritesUp3[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                }
                return;
            case Variables.DIR_DOWN:
                switch (Variables.MapNPCs[mapNpcNum].getStep()) {
                    case 0:
                        GameRenderer.batcher.draw(AssetLoader.spritesDown1[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                    case 1:
                        GameRenderer.batcher.draw(AssetLoader.spritesDown2[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                    case 2:
                        GameRenderer.batcher.draw(AssetLoader.spritesDown3[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                }
                return;
            case Variables.DIR_LEFT:
                switch (Variables.MapNPCs[mapNpcNum].getStep()) {
                    case 0:
                        GameRenderer.batcher.draw(AssetLoader.spritesLeft1[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                    case 1:
                        GameRenderer.batcher.draw(AssetLoader.spritesLeft2[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                    case 2:
                        GameRenderer.batcher.draw(AssetLoader.spritesLeft3[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                }
                return;
            case Variables.DIR_RIGHT:
                switch (Variables.MapNPCs[mapNpcNum].getStep()) {
                    case 0:
                        GameRenderer.batcher.draw(AssetLoader.spritesRight1[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                    case 1:
                        GameRenderer.batcher.draw(AssetLoader.spritesRight2[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                    case 2:
                        GameRenderer.batcher.draw(AssetLoader.spritesRight3[Variables.MapNPCs[mapNpcNum].getSprite()], x + 8, y + 3, 32, 36);
                        break;
                }
                return;
        }
    }
    public static void renderMap_Lower() {
        if (!Variables.reloadingMap) {
            int Text = 0;

            int mapNum = Variables.players[Variables.MyIndex].getMap();

            for (int x = Variables.MinX; x <= Variables.MaxX - 1; x++) {
                for (int y = Variables.MinY; y <= Variables.MaxY - 1; y++) {
                    for (int LoopL = 0; LoopL <= 2; LoopL++) {
                        if (Variables.mapRender == null) { break; }
                        if (Variables.mapRender[mapNum] == null) { break; }
                        if (Variables.mapRender[mapNum].Tile == null) { break; }
                        if (Variables.mapRender[mapNum].Tile[x][y] == null) { break; }
                        if (Variables.mapRender[mapNum].Tile[x][y].Layer == null) { break; }
                        if (Variables.mapRender[mapNum].Tile[x][y].Layer[LoopL] == null) { break; }
                        if (Variables.mapRender[mapNum].Tile[x][y].TileNum == null) { break; }
                        Text = Variables.mapRender[mapNum].Tile[x][y].TileNum[LoopL];
                        int tileSet = Variables.mapRender[mapNum].Tile[x][y].Layer[LoopL].Tileset;
                        int tileX = Variables.mapRender[mapNum].Tile[x][y].Layer[LoopL].X;
                        int tileY = Variables.mapRender[mapNum].Tile[x][y].Layer[LoopL].Y;
                        if (tileSet > 0) {
                            if (Variables.mapTiles[mapNum].TileLayer == null) { break; }
                            if (Variables.mapTiles[mapNum].TileLayer[LoopL] == null) { break; }
                            if (Variables.mapTiles[mapNum].TileLayer[LoopL].Tile == null) { break; }
                            if (Variables.mapTiles[mapNum].TileLayer[LoopL].Tile[Text] == null) { break; }
                            batcher.draw(AssetLoader.tiles[tileSet],
                                    Variables.mapTiles[mapNum].TileLayer[LoopL].Tile[Text].PixelPosX + 8,
                                    Variables.mapTiles[mapNum].TileLayer[LoopL].Tile[Text].PixelPosY + 8,
                                    32,
                                    32,
                                    tileX,
                                    tileY,
                                    32,
                                    32,
                                    false,
                                    true);
                        }
                    }
                }
            }
        }
    }
    public static void renderMap_Upper() {
        if (!Variables.reloadingMap) {
            int Text = 0;

            int mapNum = Variables.players[Variables.MyIndex].getMap();

            for (int x = Variables.MinX; x <= Variables.MaxX - 1; x++) {
                for (int y = Variables.MinY; y <= Variables.MaxY - 1; y++) {
                    for (int LoopL = 3; LoopL <= 4; LoopL++) {
                        if (Variables.mapRender == null) { break; }
                        if (Variables.mapRender[mapNum] == null) { break; }
                        if (Variables.mapRender[mapNum].Tile == null) { break; }
                        if (Variables.mapRender[mapNum].Tile[x][y] == null) { break; }
                        if (Variables.mapRender[mapNum].Tile[x][y].TileNum == null) { break; }
                        Text = Variables.mapRender[mapNum].Tile[x][y].TileNum[LoopL];
                        if (Variables.mapRender[mapNum].Tile[x][y].Layer == null) { break; }
                        if (Variables.mapRender[mapNum].Tile[x][y].Layer[LoopL] == null) { break; }
                        int tileSet = Variables.mapRender[mapNum].Tile[x][y].Layer[LoopL].Tileset;
                        int tileX = Variables.mapRender[mapNum].Tile[x][y].Layer[LoopL].X;
                        int tileY = Variables.mapRender[mapNum].Tile[x][y].Layer[LoopL].Y;
                        if (tileSet > 0) {
                            if (Variables.mapTiles[mapNum].TileLayer == null) { break; }
                            if (Variables.mapTiles[mapNum].TileLayer[LoopL] == null) { break; }
                            if (Variables.mapTiles[mapNum].TileLayer[LoopL].Tile == null) { break; }
                            if (Variables.mapTiles[mapNum].TileLayer[LoopL].Tile[Text] == null) { break; }
                            batcher.draw(AssetLoader.tiles[tileSet],
                                    Variables.mapTiles[mapNum].TileLayer[LoopL].Tile[Text].PixelPosX + 8,
                                    Variables.mapTiles[mapNum].TileLayer[LoopL].Tile[Text].PixelPosY + 8,
                                    32,
                                    32,
                                    tileX,
                                    tileY,
                                    32,
                                    32,
                                    false,
                                    true);
                        }
                    }
                    Text++;
                }
            }
        }
    }

    // Game State In Game
    public static void drawShop() {
        batcher.draw(AssetLoader.menuBG, 8, 8, 448, 448, 0, 0, 448, 448, false, true);

        layout.setText(AssetLoader.font, Variables.Shop.Name);
        float width = layout.width;// contains the width of the current set text

        float nameX = 232 - ((int)width / 2);
        float nameY = 24 - 8;
        drawText(Variables.Shop.Name, nameX, nameY, Color.WHITE);

        layout.setText(AssetLoader.nameFont, Variables.Shop.welcomeMsg);
        width = layout.width;// contains the width of the current set text

        nameX = 2232 - ((int)width / 2);
        nameY = 74 - 8;
        drawName(Variables.Shop.welcomeMsg, nameX, nameY, Color.WHITE);

        int x = 28;
        int y = 114 - 8;
        for (int i = 1; i <= 20; i++) {
            if (i == 11) {
                x = 28;
                y = 155 - 8;
                if (Variables.selectedShopSlot == i) {
                    batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                } else {
                    batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                }
                if (Variables.Shop.itemNum[i] > 0) {
                    batcher.draw(AssetLoader.items[Variables.Items[Variables.Shop.itemNum[i]].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                }
                x = x + 41;
            } else {
                if (Variables.selectedShopSlot == i) {
                    batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                } else {
                    batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                }
                if (Variables.Shop.itemNum[i] > 0) {
                    batcher.draw(AssetLoader.items[Variables.Items[Variables.Shop.itemNum[i]].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                }
                x = x + 41;
            }
        }

        if (Variables.selectedShopSlot > 0) {
            layout.setText(AssetLoader.font, Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].Name);
            width = layout.width;// contains the width of the current set text

            nameX = 232 - ((int)width / 2);
            nameY = 225 - 8;
            drawText(Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].Name, nameX, nameY, Color.WHITE);

            drawText("Lvl: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].LVL, 114, 274, Color.WHITE);
            drawText("HP: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].HP, 204, 274, Color.WHITE);
            drawText("MP: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].MP, 284, 274, Color.WHITE);

            drawText("STR: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].STR, 24, 304, Color.WHITE);
            drawText("DEF: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].DEF, 114, 304, Color.WHITE);
            drawText("VIT: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].VIT, 204, 304, Color.WHITE);
            drawText("AGI: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].AGI, 284, 304, Color.WHITE);
            drawText("MAG: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].MAG, 364, 304, Color.WHITE);

            layout.setText(AssetLoader.nameFont, "Cost: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].Cost + " Gold");
            width = layout.width;// contains the width of the current set text

            nameX = 232 - ((int)width / 2);
            nameY = 334;
            drawName("Cost: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].Cost + " Gold", nameX, nameY, Color.YELLOW);
        }

        if (Variables.BoughtMsgTimer > 0) {
            layout.setText(AssetLoader.font, "Purchase successful!");
            width = layout.width;// contains the width of the current set text

            nameX = 232 - ((int)width / 2);
            nameY = 362;
            drawText("Purchase successful!", nameX, nameY, Color.YELLOW);
        }
        if (Variables.NotEnoughGoldMsgTimer > 0) {
            layout.setText(AssetLoader.font, "You don't have enough gold.");
            width = layout.width;// contains the width of the current set text

            nameX = 232 - ((int)width / 2);
            nameY = 342;
            drawText("You don't have enough gold.", nameX, nameY, Color.RED);
        }

        // Back Button
        drawText("Back", 16, 429, Color.WHITE);

        // Buy Button
        drawText("Buy", 410, 429, Color.WHITE);
    }
    public static void drawInventory() {
        batcher.draw(AssetLoader.menuBG, 8, 8, 448, 448, 0, 0, 448, 448, false, true);

        int x = 28;
        int y = 28;
        for (int i = 1; i <= 60; i++) {
            if (Variables.players[Variables.MyIndex].inventory != null) {
                if (Variables.players[Variables.MyIndex].inventory[i] != null) {
                    if (i == 11) {
                        x = 28;
                        y = 69;
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.players[Variables.MyIndex].getWeapon() || i == Variables.players[Variables.MyIndex].getArmor() || i == Variables.players[Variables.MyIndex].getHelmet() || i == Variables.players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    } else if (i == 21) {
                        x = 28;
                        y = 110;
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.players[Variables.MyIndex].getWeapon() || i == Variables.players[Variables.MyIndex].getArmor() || i == Variables.players[Variables.MyIndex].getHelmet() || i == Variables.players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    } else if (i == 31) {
                        x = 28;
                        y = 151;
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.players[Variables.MyIndex].getWeapon() || i == Variables.players[Variables.MyIndex].getArmor() || i == Variables.players[Variables.MyIndex].getHelmet() || i == Variables.players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    } else if (i == 41) {
                        x = 28;
                        y = 192;
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.players[Variables.MyIndex].getWeapon() || i == Variables.players[Variables.MyIndex].getArmor() || i == Variables.players[Variables.MyIndex].getHelmet() || i == Variables.players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    } else if (i == 51) {
                        x = 28;
                        y = 233;
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.players[Variables.MyIndex].getWeapon() || i == Variables.players[Variables.MyIndex].getArmor() || i == Variables.players[Variables.MyIndex].getHelmet() || i == Variables.players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    } else {
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.players[Variables.MyIndex].getWeapon() || i == Variables.players[Variables.MyIndex].getArmor() || i == Variables.players[Variables.MyIndex].getHelmet() || i == Variables.players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    }

                    // Selected Item Info
                    if (Variables.selectedInvSlot > 0) {
                        int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.selectedInvSlot].getItemNum();
                        if (itemNum > 0) {
                            layout.setText(AssetLoader.font, Variables.Items[itemNum].Name);
                            float width = layout.width;// contains the width of the current set text

                            float nameX = 240 - ((int) width / 2);
                            float nameY = 325;
                            drawText(Variables.Items[itemNum].Name, nameX, nameY, Color.WHITE);

                            layout.setText(AssetLoader.nameFont, "Lvl: " + Variables.Items[itemNum].LVL + "     HP: " + Variables.Items[itemNum].HP + "     MP: " + Variables.Items[itemNum].MP);
                            width = layout.width;// contains the width of the current set text

                            nameX = 240 - ((int) width / 2);
                            nameY = 350;
                            drawName("Lvl: " + Variables.Items[itemNum].LVL + "     HP: " + Variables.Items[itemNum].HP + "     MP: " + Variables.Items[itemNum].MP, nameX, nameY, Color.WHITE);

                            layout.setText(AssetLoader.nameFont, "STR: " + Variables.Items[itemNum].STR + "     DEF: " + Variables.Items[itemNum].DEF + "     VIT: " + Variables.Items[itemNum].VIT + "     AGI: " + Variables.Items[itemNum].AGI + "     MAG: " + Variables.Items[itemNum].MAG);
                            width = layout.width;// contains the width of the current set text

                            nameX = 240 - ((int) width / 2);
                            nameY = 365;
                            drawName("STR: " + Variables.Items[itemNum].STR + "     DEF: " + Variables.Items[itemNum].DEF + "     VIT: " + Variables.Items[itemNum].VIT + "     AGI: " + Variables.Items[itemNum].AGI + "     MAG: " + Variables.Items[itemNum].MAG, nameX, nameY, Color.WHITE);

                            if (Variables.Items[itemNum].isStackable == 1) {
                                layout.setText(AssetLoader.nameFont, "Amount: " + Variables.players[Variables.MyIndex].inventory[Variables.selectedInvSlot].getItemVal());
                                width = layout.width;// contains the width of the current set text

                                nameX = 240 - ((int) width / 2);
                                nameY = 380;

                                drawName("Amount: " + Variables.players[Variables.MyIndex].inventory[Variables.selectedInvSlot].getItemVal(), nameX, nameY, Color.YELLOW);
                            }
                        }
                    }
                }
            }
        }

        // Back Button
        drawText("Back", 16, 429, Color.WHITE);

        // Use Button
        drawText("Use", 410, 429, Color.WHITE);
    }
    public static void drawStatus() {
        batcher.draw(AssetLoader.menuBG, 8, 8, 448, 448, 0, 0, 448, 448, false, true);

        layout.setText(AssetLoader.font, Variables.players[Variables.MyIndex].getName());
        float width = layout.width;// contains the width of the current set text

        float nameX = 232 - ((int)width / 2);
        float nameY = 42;

        drawText(Variables.players[Variables.MyIndex].getName(), nameX, nameY, Color.WHITE);

        int pSTR = 0;
        int pDEF = 0;
        int pVIT = 0;
        int pAGI = 0;
        int pMAG = 0;
        if (Variables.players[Variables.MyIndex].getWeapon() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getWeapon()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getOffhand() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getOffhand()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getArmor() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getArmor()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getHelmet() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getHelmet()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getAcc1() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getAcc1()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getAcc2() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getAcc2()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }

        nameX = 67;
        nameY = 72;
        drawText("STR: " + Variables.players[Variables.MyIndex].getSTR(), nameX, nameY, Color.WHITE);
        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
            drawText("+", nameX - 25, nameY, Color.YELLOW);
        }
        if (pSTR > 0) {
            layout.setText(AssetLoader.font, "STR: " + Variables.players[Variables.MyIndex].getSTR());
            width = layout.width;// contains the width of the current set text
            drawText("(+" + pSTR + ")", nameX + (width + 10), nameY, Color.GREEN);
        }
        nameX = 67;
        nameY = 102;
        drawText("DEF: " + Variables.players[Variables.MyIndex].getDEF(), nameX, nameY, Color.WHITE);
        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
            drawText("+", nameX - 25, nameY, Color.YELLOW);
        }
        if (pDEF > 0) {
            layout.setText(AssetLoader.font, "DEF: " + Variables.players[Variables.MyIndex].getDEF());
            width = layout.width;// contains the width of the current set text
            drawText("(+" + pDEF + ")", nameX + (width + 10), nameY, Color.GREEN);
        }
        nameX = 67;
        nameY = 132;
        drawText("VIT: " + Variables.players[Variables.MyIndex].getVIT(), nameX, nameY, Color.WHITE);
        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
            drawText("+", nameX - 25, nameY, Color.YELLOW);
        }
        if (pVIT > 0) {
            layout.setText(AssetLoader.font, "VIT: " + Variables.players[Variables.MyIndex].getVIT());
            width = layout.width;// contains the width of the current set text
            drawText("(+" + pVIT + ")", nameX + (width + 10), nameY, Color.GREEN);
        }
        nameX = 69;
        nameY = 162;
        drawText("AGI: " + Variables.players[Variables.MyIndex].getAGI(), nameX, nameY, Color.WHITE);
        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
            drawText("+", nameX - 25, nameY, Color.YELLOW);
        }
        if (pAGI > 0) {
            layout.setText(AssetLoader.font, "AGI: " + Variables.players[Variables.MyIndex].getAGI());
            width = layout.width;// contains the width of the current set text
            drawText("(+" + pAGI + ")", nameX + (width + 10), nameY, Color.GREEN);
        }
        nameX = 69;
        nameY = 192;
        drawText("MAG: " + Variables.players[Variables.MyIndex].getMAG(), nameX, nameY, Color.WHITE);
        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
            drawText("+", nameX - 25, nameY, Color.YELLOW);
        }
        if (pMAG > 0) {
            layout.setText(AssetLoader.font, "MAG: " + Variables.players[Variables.MyIndex].getMAG());
            width = layout.width;// contains the width of the current set text
            drawText("(+" + pMAG + ")", nameX + (width + 10), nameY, Color.GREEN);
        }
        nameX = 69;
        nameY = 222;
        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
            drawName("Points: " + Variables.players[Variables.MyIndex].getPoints(), nameX, nameY, Color.YELLOW);
        } else {
            drawName("Points: " + Variables.players[Variables.MyIndex].getPoints(), nameX, nameY, Color.WHITE);
        }

        // Back Button
        drawText("Back", 16, 429, Color.WHITE);
    }
    public static void drawMenu() {
        batcher.draw(AssetLoader.menuBG, 16, 16, 448, 448, 0, 0, 448, 448, false, true);

        // Player Sprite
        batcher.draw(AssetLoader.spritesDown2[Variables.players[Variables.MyIndex].getSprite()], 63, 59, 32, 36);
        // Player Equipment
        batcher.draw(AssetLoader.eqBG, 39, 21, 36, 36);
        if (Variables.players[Variables.MyIndex].getWeapon() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getWeapon()].getItemNum();
            if (itemNum > 0) {
                batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 39 + 5, 21 + 5, 24, 24, 0, 0, 24, 24, false, true);
            }
        }
        batcher.draw(AssetLoader.eqBG, 21, 58, 36, 36);
        if (Variables.players[Variables.MyIndex].getArmor() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getArmor()].getItemNum();
            if (itemNum > 0) {
                batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 21 + 5, 58 + 5, 24, 24, 0, 0, 24, 24, false, true);
            }
        }
        batcher.draw(AssetLoader.eqBG, 76, 21, 36, 36);
        if (Variables.players[Variables.MyIndex].getOffhand() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getOffhand()].getItemNum();
            if (itemNum > 0) {
                batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 76 + 5, 21 + 5, 24, 24, 0, 0, 24, 24, false, true);
            }
        }
        batcher.draw(AssetLoader.eqBG, 100, 58, 36, 36);
        if (Variables.players[Variables.MyIndex].getHelmet() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getHelmet()].getItemNum();
            if (itemNum > 0) {
                batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 100 + 5, 58 + 5, 24, 24, 0, 0, 24, 24, false, true);
            }
        }
        batcher.draw(AssetLoader.eqBG, 39, 95, 36, 36);
        batcher.draw(AssetLoader.eqBG, 76, 95, 36, 36);

        // Player Info
        drawName("Name: " + Variables.players[Variables.MyIndex].getName(), 156, 21, Color.WHITE);
        drawName("Lvl: " + Variables.players[Variables.MyIndex].getLevel(), 156, 36, Color.WHITE);
        drawName("HP: " + Variables.players[Variables.MyIndex].getHP() + "/" + Variables.players[Variables.MyIndex].getMaxHP(), 156, 51, Color.WHITE);
        drawName("MP: " + Variables.players[Variables.MyIndex].getMP() + "/" + Variables.players[Variables.MyIndex].getMaxMP(), 156, 66, Color.WHITE);
        drawName("EXP: " + Variables.players[Variables.MyIndex].getEXP() + "/" + Variables.players[Variables.MyIndex].getNextLVL(), 156, 81, Color.WHITE);

        int pSTR = Variables.players[Variables.MyIndex].getSTR();
        int pDEF = Variables.players[Variables.MyIndex].getDEF();
        int pVIT = Variables.players[Variables.MyIndex].getVIT();
        int pAGI = Variables.players[Variables.MyIndex].getAGI();
        int pMAG = Variables.players[Variables.MyIndex].getMAG();
        if (Variables.players[Variables.MyIndex].getWeapon() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getWeapon()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getOffhand() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getOffhand()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getArmor() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getArmor()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getHelmet() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getHelmet()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getAcc1() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getAcc1()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getAcc2() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getAcc2()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }

        if (pSTR > Variables.players[Variables.MyIndex].getSTR()) {
            drawName("STR: " + pSTR, 156, 96, Color.GREEN);
        } else {
            drawName("STR: " + pSTR, 156, 96, Color.WHITE);
        }
        if (pDEF > Variables.players[Variables.MyIndex].getDEF()) {
            drawName("DEF: " + pDEF, 156, 111, Color.GREEN);
        } else {
            drawName("DEF: " + pDEF, 156, 111, Color.WHITE);
        }
        if (pVIT > Variables.players[Variables.MyIndex].getVIT()) {
            drawName("VIT: " + pVIT, 156, 126, Color.GREEN);
        } else {
            drawName("VIT: " + pVIT, 156, 126, Color.WHITE);
        }
        if (pAGI > Variables.players[Variables.MyIndex].getAGI()) {
            drawName("AGI: " + pAGI, 226, 96, Color.GREEN);
        } else {
            drawName("AGI: " + pAGI, 226, 96, Color.WHITE);
        }
        if (pMAG > Variables.players[Variables.MyIndex].getMAG()) {
            drawName("MAG: " + pMAG, 226, 111, Color.GREEN);
        } else {
            drawName("MAG: " + pMAG, 226, 111, Color.WHITE);
        }
        drawName("Points: " + Variables.players[Variables.MyIndex].getPoints(), 226, 126, Color.WHITE);

        batcher.draw(AssetLoader.sepBarV, 320, 8, 9, 464);

        drawText("Items", 365, 31, Color.WHITE);
        drawText("Spells", 364, 71, Color.WHITE);
        drawText("Status", 358, 111, Color.WHITE);
        int goldTotal = 0;
        for (int i = 1; i <= 60; i++) {
            if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() == 1) {
                goldTotal += Variables.players[Variables.MyIndex].inventory[i].getItemVal();
            }
        }

        layout.setText(AssetLoader.nameFont, "Gold: " + goldTotal);
        float width = layout.width;// contains the width of the current set text

        float nameX = 460 - (int)width;
        float nameY = 447;
        drawName("Gold: " + goldTotal, nameX, nameY, Color.YELLOW);

        batcher.draw(AssetLoader.sepBarH, 320, 370, 152, 9);

        // Back Button
        drawText("Back", 24, 437, Color.WHITE);
    }
    public static void drawUI() {
        // Empty Bar
        batcher.draw(AssetLoader.emptyBar, 532, 150, 192, 12, 0, 0, 192, 12, false, true);
        batcher.draw(AssetLoader.emptyBar, 532, 167, 192, 12, 0, 0, 192, 12, false, true);
        batcher.draw(AssetLoader.emptyBar, 532, 184, 192, 12, 0, 0, 192, 12, false, true);
        // HP Bar
        if (Variables.players[Variables.MyIndex].getHP() > Variables.players[Variables.MyIndex].getMaxHP()) { Variables.players[Variables.MyIndex].setHP(Variables.players[Variables.MyIndex].getMaxHP()); }
        double maxWidth = ((((double) Variables.players[Variables.MyIndex].getHP()) / 192) / ((double) Variables.players[Variables.MyIndex].getMaxHP() / 192) * 192);
        batcher.draw(AssetLoader.hpBar, 532, 150, (int)maxWidth, 12, 0, 0, (int)maxWidth, 12, false, true);
        drawName("HP", 533, 150, Color.WHITE);
        // MP Bar
        if (Variables.players[Variables.MyIndex].getMP() > Variables.players[Variables.MyIndex].getMaxMP()) { Variables.players[Variables.MyIndex].setMP(Variables.players[Variables.MyIndex].getMaxMP()); }
        maxWidth = ((((double) Variables.players[Variables.MyIndex].getMP()) / 192) / ((double) Variables.players[Variables.MyIndex].getMaxMP() / 192) * 192);
        batcher.draw(AssetLoader.mpBar, 532, 167, (int)maxWidth, 12, 0, 0, (int)maxWidth, 12, false, true);
        drawName("MP", 533, 167, Color.WHITE);
        // XP Bar
        maxWidth = ((((double) Variables.players[Variables.MyIndex].getEXP()) / 192) / ((double) Variables.players[Variables.MyIndex].getNextLVL() / 192) * 192);
        batcher.draw(AssetLoader.xpBar, 532, 184, (int)maxWidth, 12, 0, 0, (int)maxWidth, 12, false, true);
        drawName("XP", 533, 184, Color.WHITE);

        // Player Sprite
        batcher.draw(AssetLoader.spritesDown2[Variables.players[Variables.MyIndex].getSprite()], 613, 260, 32, 36);
        // Player Equipment
        batcher.draw(AssetLoader.eqBG, 592, 222, 36, 36);
        if (Variables.players[Variables.MyIndex].getWeapon() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getWeapon()].getItemNum();
            if (itemNum > 0) {
                batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 592 + 5, 222 + 5, 24, 24, 0, 0, 24, 24, false, true);
            }
        }
        batcher.draw(AssetLoader.eqBG, 576, 259, 36, 36);
        if (Variables.players[Variables.MyIndex].getArmor() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getArmor()].getItemNum();
            if (itemNum > 0) {
                batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 576 + 5, 259 + 5, 24, 24, 0, 0, 24, 24, false, true);
            }
        }
        batcher.draw(AssetLoader.eqBG, 629, 222, 36, 36);
        if (Variables.players[Variables.MyIndex].getOffhand() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getOffhand()].getItemNum();
            if (itemNum > 0) {
                batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 629 + 5, 222 + 5, 24, 24, 0, 0, 24, 24, false, true);
            }
        }
        batcher.draw(AssetLoader.eqBG, 648, 259, 36, 36);
        if (Variables.players[Variables.MyIndex].getHelmet() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getHelmet()].getItemNum();
            if (itemNum > 0) {
                batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 648 + 5, 259 + 5, 24, 24, 0, 0, 24, 24, false, true);
            }
        }
        batcher.draw(AssetLoader.eqBG, 592, 296, 36, 36);
        batcher.draw(AssetLoader.eqBG, 629, 296, 36, 36);

        int pSTR = Variables.players[Variables.MyIndex].getSTR();
        int pDEF = Variables.players[Variables.MyIndex].getDEF();
        int pVIT = Variables.players[Variables.MyIndex].getVIT();
        int pAGI = Variables.players[Variables.MyIndex].getAGI();
        int pMAG = Variables.players[Variables.MyIndex].getMAG();
        if (Variables.players[Variables.MyIndex].getWeapon() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getWeapon()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getOffhand() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getOffhand()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getArmor() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getArmor()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getHelmet() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getHelmet()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getAcc1() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getAcc1()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.players[Variables.MyIndex].getAcc2() > 0) {
            int itemNum = Variables.players[Variables.MyIndex].inventory[Variables.players[Variables.MyIndex].getAcc2()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }

        drawName("Level: " + Variables.players[Variables.MyIndex].getLevel(), 553, 347, Color.WHITE);
        if (pSTR > Variables.players[Variables.MyIndex].getSTR()) {
            drawName("STR: " + pSTR, 553, 362, Color.GREEN);
        } else {
            drawName("STR: " + pSTR, 553, 362, Color.WHITE);
        }
        if (pDEF > Variables.players[Variables.MyIndex].getDEF()) {
            drawName("DEF: " + pDEF, 553, 377, Color.GREEN);
        } else {
            drawName("DEF: " + pDEF, 553, 377, Color.WHITE);
        }
        if (pVIT > Variables.players[Variables.MyIndex].getVIT()) {
            drawName("VIT: " + pVIT, 553, 392, Color.GREEN);
        } else {
            drawName("VIT: " + pVIT, 553, 392, Color.WHITE);
        }
        if (pAGI > Variables.players[Variables.MyIndex].getAGI()) {
            drawName("AGI: " + pAGI, 643, 362, Color.GREEN);
        } else {
            drawName("AGI: " + pAGI, 643, 362, Color.WHITE);
        }
        if (pMAG > Variables.players[Variables.MyIndex].getMAG()) {
            drawName("MAG: " + pMAG, 643, 377, Color.GREEN);
        } else {
            drawName("MAG: " + pMAG, 643, 377, Color.WHITE);
        }
        drawName("Points: " + Variables.players[Variables.MyIndex].getPoints(), 643, 392, Color.WHITE);

        int goldTotal = 0;
        for (int i = 1; i <= 60; i++) {
            if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() == 1) {
                goldTotal += Variables.players[Variables.MyIndex].inventory[i].getItemVal();
            }
        }
        drawName("Gold: " + goldTotal, 465, 441, Color.YELLOW);
    }
    public static void drawNames() {
        // RenderAndroid NPC Names
        for (int i = 1; i <= Variables.MaxMapNPCs; i++) {
            if (Variables.MapNPCs == null) { break; }
            if (Variables.MapNPCs[i] == null) { break; }
            if (Variables.MapNPCs[i].getNum() > 0) {
                float PlayerX = ((Variables.MapNPCs[i].getX() * Variables.MoveSize) + Variables.MapNPCs[i].getOffsetX());
                float PlayerY = ((Variables.MapNPCs[i].getY() * Variables.MoveSize) + Variables.MapNPCs[i].getOffsetY());

                if (Variables.MapNPCs[i].getName() == null) { break; }
                layout.setText(AssetLoader.nameFont, Variables.MapNPCs[i].getName());
                float width = layout.width;// contains the width of the current set text

                float nameX = PlayerX - ((int)width / 2) + 24;
                float nameY = PlayerY - 16;
                if (Variables.MapNPCs[i].getHP() > 0) {
                    if (Variables.MapNPCs[i].getName() != null) {
                        drawName(Variables.MapNPCs[i].getName(), nameX, nameY, Color.WHITE);
                    }
                }

                for (int a = 1; a <= 20; a++) {
                    if (Variables.DrawNPCDamage[a].getTimer() > 0) {
                        if (Variables.DrawNPCDamage[a].getMapNpcNum() > 0) {
                            if (Variables.DrawNPCDamage[a].getMapNpcNum() == i) {
                                drawName(Variables.DrawNPCDamage[a].getDamage() + "", Variables.DrawNPCDamage[a].getX(), Variables.DrawNPCDamage[a].getY(), Color.WHITE);
                            }
                        }
                    }
                }
            }
        }
        // RenderAndroid Player Names
        for (int i = 1; i <= Variables.MaxPlayers; i++) {
            if (Variables.players[i] != null) {
                if (Variables.players[i].getMap() == Variables.players[Variables.MyIndex].getMap()) {
                    float PlayerX = ((Variables.players[i].getX() * Variables.MoveSize) + Variables.players[i].getOffsetX());
                    float PlayerY = ((Variables.players[i].getY() * Variables.MoveSize) + Variables.players[i].getOffsetY());

                    if (Variables.players[i].getName() == null) { return; }
                    layout.setText(AssetLoader.nameFont, Variables.players[i].getName());
                    float width = layout.width;// contains the width of the current set text

                    float nameX = PlayerX - ((int)width / 2) + 24;
                    float nameY = PlayerY - 16;
                    drawName(Variables.players[i].getName(), nameX, nameY, Color.WHITE);

                    for (int a = 1; a <= 20; a++) {
                        if (Variables.DrawPlayerDamage[a].getTimer() > 0) {
                            if (Variables.DrawPlayerDamage[a].getMapNpcNum() > 0) {
                                if (Variables.DrawPlayerDamage[a].getMapNpcNum() == i) {
                                    drawName(Variables.DrawPlayerDamage[a].getDamage() + "", Variables.DrawPlayerDamage[a].getX(), Variables.DrawPlayerDamage[a].getY(), Color.RED);
                                }
                            }
                        }
                    }

                    for (int a = 1; a <= 20; a++) {
                        if (Variables.DrawXP[a].getTimer() > 0) {
                            if (Variables.DrawXP[a].getMapNpcNum() == Variables.MyIndex) {
                                drawName(Variables.DrawXP[a].getDamage() + "", Variables.DrawXP[a].getX(), Variables.DrawXP[a].getY(), Color.YELLOW);
                            }
                        }
                    }
                }
            }
        }
    }
    public static void drawPlayersAndNPCs(long tickCount) {
        for (int LoopY = Variables.MinY; LoopY <= Variables.MaxY - 1; LoopY++) {
            for (int i = 1; i <= Variables.MaxMapNPCs; i++) {
                if (Variables.MapNPCs[i].getNum() > 0) {
                    if (Variables.MapNPCs[i].getY() == LoopY) {
                        int MovementSpeed = 8;

                        switch (Variables.MapNPCs[i].getDir()) {
                            case Variables.DIR_UP:
                                Variables.MapNPCs[i].setOffsetY(Variables.MapNPCs[i].getOffsetY() - MovementSpeed);
                                if (Variables.MapNPCs[i].getOffsetY() < 0) {
                                    Variables.MapNPCs[i].setOffsetY(0);
                                    Variables.MapNPCs[i].setMoving(0);
                                }
                                break;
                            case Variables.DIR_DOWN:
                                Variables.MapNPCs[i].setOffsetY(Variables.MapNPCs[i].getOffsetY() + MovementSpeed);
                                if (Variables.MapNPCs[i].getOffsetY() > 0) {
                                    Variables.MapNPCs[i].setOffsetY(0);
                                    Variables.MapNPCs[i].setMoving(0);
                                }
                                break;
                            case Variables.DIR_LEFT:
                                Variables.MapNPCs[i].setOffsetX(Variables.MapNPCs[i].getOffsetX() - MovementSpeed);
                                if (Variables.MapNPCs[i].getOffsetX() < 0) {
                                    Variables.MapNPCs[i].setOffsetX(0);
                                    Variables.MapNPCs[i].setMoving(0);
                                }
                                break;
                            case Variables.DIR_RIGHT:
                                Variables.MapNPCs[i].setOffsetX(Variables.MapNPCs[i].getOffsetX() + MovementSpeed);
                                if (Variables.MapNPCs[i].getOffsetX() > 0) {
                                    Variables.MapNPCs[i].setOffsetX(0);
                                    Variables.MapNPCs[i].setMoving(0);
                                }
                                break;
                        }
                        float NPCX = ((Variables.MapNPCs[i].getX() * Variables.MoveSize) + Variables.MapNPCs[i].getOffsetX());
                        float NPCY = ((Variables.MapNPCs[i].getY() * Variables.MoveSize) + Variables.MapNPCs[i].getOffsetY());
                        renderNPC(i, NPCX, NPCY, tickCount);
                    }
                }
            }
            for (int i = 1; i <= Variables.MaxPlayers; i++) {
                if (Variables.players[i] != null) {
                    int mapNum = Variables.players[i].getMap();
                    if (Variables.players[i].getMap() == Variables.players[Variables.MyIndex].getMap()) {
                        if (Variables.players[i].getY() == LoopY) {

                            int MovementSpeed = 4;

                            switch (Variables.players[i].getDir()) {
                                case Variables.DIR_UP:
                                    Variables.players[i].setOffsetY(Variables.players[i].getOffsetY() - MovementSpeed);
                                    if (Variables.players[i].getOffsetY() < 0) {
                                        Variables.players[i].setOffsetY(0);
                                        Variables.players[i].setMoving(0);
                                        if (i == Variables.MyIndex) {
                                            int X = Variables.players[i].getX();
                                            int Y = Variables.players[i].getY();
                                            if (Variables.mapRender[mapNum].Tile[X][Y].Type == Variables.TILE_TYPE_WARP) {
                                                SendClientData.SendWarpCheck();
                                            }
                                        }
                                    }
                                    break;
                                case Variables.DIR_DOWN:
                                    Variables.players[i].setOffsetY(Variables.players[i].getOffsetY() + MovementSpeed);
                                    if (Variables.players[i].getOffsetY() > 0) {
                                        Variables.players[i].setOffsetY(0);
                                        Variables.players[i].setMoving(0);
                                        if (i == Variables.MyIndex) {
                                            int X = Variables.players[i].getX();
                                            int Y = Variables.players[i].getY();
                                            if (Variables.mapRender[mapNum].Tile[X][Y].Type == Variables.TILE_TYPE_WARP) {
                                                SendClientData.SendWarpCheck();
                                            }
                                        }
                                    }
                                    break;
                                case Variables.DIR_LEFT:
                                    Variables.players[i].setOffsetX(Variables.players[i].getOffsetX() - MovementSpeed);
                                    if (Variables.players[i].getOffsetX() < 0) {
                                        Variables.players[i].setOffsetX(0);
                                        Variables.players[i].setMoving(0);
                                        if (i == Variables.MyIndex) {
                                            int X = Variables.players[i].getX();
                                            int Y = Variables.players[i].getY();
                                            if (Variables.mapRender[mapNum].Tile[X][Y].Type == Variables.TILE_TYPE_WARP) {
                                                SendClientData.SendWarpCheck();
                                            }
                                        }
                                    }
                                    break;
                                case Variables.DIR_RIGHT:
                                    Variables.players[i].setOffsetX(Variables.players[i].getOffsetX() + MovementSpeed);
                                    if (Variables.players[i].getOffsetX() > 0) {
                                        Variables.players[i].setOffsetX(0);
                                        Variables.players[i].setMoving(0);
                                        if (i == Variables.MyIndex) {
                                            int X = Variables.players[i].getX();
                                            int Y = Variables.players[i].getY();
                                            if (Variables.mapRender[mapNum].Tile[X][Y].Type == Variables.TILE_TYPE_WARP) {
                                                SendClientData.SendWarpCheck();
                                            }
                                        }
                                    }
                                    break;
                            }
                            float PlayerX = ((Variables.players[i].getX() * Variables.MoveSize) + Variables.players[i].getOffsetX());
                            float PlayerY = ((Variables.players[i].getY() * Variables.MoveSize) + Variables.players[i].getOffsetY());
                            renderPlayer(i, PlayerX, PlayerY, tickCount);
                        }
                    }
                }
            }
        }
    }
    public static void drawItems() {
        for (int i = 1; i <= Variables.MaxMapItems; i++) {
            if (Variables.MapItems[i] != null) {
                if (Variables.MapItems[i].itemNum > 0) {
                    float itemX = (Variables.MapItems[i].x * Variables.MoveSize) + 8;
                    float itemY = (Variables.MapItems[i].y * Variables.MoveSize) + 8;
                    batcher.draw(AssetLoader.items[Variables.Items[Variables.MapItems[i].itemNum].Icon], itemX, itemY, 24, 24, 0, 0, 24, 24, false, true);
                }
            }
        }
    }
    public static void drawChat() {
        if (Variables.chatInput != null && !Variables.chatInput.isEmpty()) {
            String chat = Variables.chatInput;
            if (Variables.inChat) {
                chat = chat + "|";
            }
            drawName(chat, 13, 576, Color.WHITE);
        } else {
            String chat = "";
            if (Variables.inChat) {
                chat = chat + "|";
            }
            drawName(chat, 13, 576, Color.WHITE);
        }

        if (Variables.chatMessageIndex < 7) {
            for (int i = 1; i <= 100; i++) {
                if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_MAP) {
                    drawName(Variables.chatMessages[i].getMsg(), 13, 455 + (14 * i), Color.WHITE);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_GLOBAL) {
                    drawName(Variables.chatMessages[i].getMsg(), 13, 455 + (14 * i), Color.ORANGE);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_WHISPER) {
                    drawName(Variables.chatMessages[i].getMsg(), 13, 455 + (14 * i), Color.PINK);
                }
            }
        } else {
            int line = 1;
            for (int i = Variables.chatMessageIndex - 6; i <= Variables.chatMessageIndex; i++) {
                if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_MAP) {
                    drawName(Variables.chatMessages[i].getMsg(), 13, 455 + (14 * line), Color.WHITE);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_GLOBAL) {
                    drawName(Variables.chatMessages[i].getMsg(), 13, 455 + (14 * line), Color.ORANGE);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_WHISPER) {
                    drawName(Variables.chatMessages[i].getMsg(), 13, 455 + (14 * line), Color.PINK);
                }
                line++;
            }
        }
    }

    public static void tryNPCSearch() {
        if (!Variables.inShop) {
            if (Variables.CurX > 0 && Variables.CurY > 0) {
                for (int i = 1; i <= Variables.MaxMapNPCs; i++) {
                    int NpcX = ((Variables.MapNPCs[i].getX() * Variables.MoveSize) + Variables.MapNPCs[i].getOffsetX());
                    int NpcY = ((Variables.MapNPCs[i].getY() * Variables.MoveSize) + Variables.MapNPCs[i].getOffsetY());
                    NpcX = NpcX / Variables.MoveSize;
                    NpcY = NpcY / Variables.MoveSize;
                    if (Variables.CurX == NpcX && Variables.CurY == NpcY) {
                        //if (lastClicked == 1) {
                        SendClientData.SendSearch(NpcX, NpcY, Variables.SEARCH_TYPE_NPC, i);
                        Variables.CurX = 0;
                        Variables.CurY = 0;
                        break;
                        //}
                    }
                }
            }
        }
    }

    public static void buyMsgTimer(long tickCount) {
        // Buy Msg Timer //
        if (LastUpdateTime_BuyMsg < tickCount) {
            if (Variables.BoughtMsgTimer > 0) {
                Variables.BoughtMsgTimer--;
            }
            if (Variables.NotEnoughGoldMsgTimer > 0) {
                Variables.NotEnoughGoldMsgTimer--;
            }
            LastUpdateTime_BuyMsg = tickCount + UpdateTime_BuyMsg;
        }
    }
    public static void longPressTimer(long tickCount) {
        if (LastUpdateTime_LongPress < tickCount) {
            if (Variables.touchDown) {
                if (Variables.longPressTimer < 1) {
                    Variables.longPressTimer++;
                    if (Variables.longPressTimer == 1) {
                        // Long Press
                        SendClientData.SendDropItem(Variables.selectedInvSlot);
                        Variables.longPressTimer = 0;
                        Variables.touchDown = false;
                    }
                }
            }
            if (Variables.usePoint) {
                if (Variables.usePointTimer > 0) {
                    Variables.usePointTimer--;
                } else {
                    Variables.usePoint = false;
                }
            }
            LastUpdateTime_LongPress = tickCount + UpdateTime_LongPress;
        }
    }
    public static void drawDmgTimer(long tickCount) {
        // Damage Timers //
        if (LastUpdateTime_Damage < tickCount) {
            for (int i = 1; i <= 20; i++) {
                if (Variables.DrawNPCDamage[i].getTimer() > 0) {
                    Variables.DrawNPCDamage[i].setY(Variables.DrawNPCDamage[i].getY() - 2);
                    Variables.DrawNPCDamage[i].setTimer(Variables.DrawNPCDamage[i].getTimer() - 1);
                    if (Variables.DrawNPCDamage[i].getTimer() <= 0) {
                        Variables.DrawNPCDamage[i] = new Damage_Struct();
                    }
                }
            }
            for (int i = 1; i <= 20; i++) {
                if (Variables.DrawPlayerDamage[i].getTimer() > 0) {
                    Variables.DrawPlayerDamage[i].setY(Variables.DrawPlayerDamage[i].getY() - 2);
                    Variables.DrawPlayerDamage[i].setTimer(Variables.DrawPlayerDamage[i].getTimer() - 1);
                    if (Variables.DrawPlayerDamage[i].getTimer() <= 0) {
                        Variables.DrawPlayerDamage[i] = new Damage_Struct();
                    }
                }
            }
            for (int i = 1; i <= 20; i++) {
                if (Variables.DrawXP[i].getTimer() > 0) {
                    Variables.DrawXP[i].setY(Variables.DrawXP[i].getY() - 2);
                    Variables.DrawXP[i].setTimer(Variables.DrawXP[i].getTimer() - 1);
                    if (Variables.DrawXP[i].getTimer() <= 0) {
                        Variables.DrawXP[i] = new Damage_Struct();
                    }
                }
            }
            LastUpdateTime_Damage = tickCount + UpdateTime_Damage;
        }
    }
    public static void processMovement(int index) {
        if (Variables.players[index].getMoving() > 0) {
            if (Variables.players[index].getOffsetX() == 0 && Variables.players[index].getOffsetY() == 0) {
                Variables.players[index].setMoving(0);
                if (Variables.players[index].getStep() == 0) {
                    Variables.players[index].setStep(1);
                    Variables.players[index].setLastStep(0);
                } else if (Variables.players[index].getStep() == 1) {
                    if (Variables.players[index].getLastStep() == 0) {
                        Variables.players[index].setStep(2);
                    } else if (Variables.players[index].getLastStep() == 2) {
                        Variables.players[index].setStep(0);
                    }
                } else if (Variables.players[index].getStep() == 2) {
                    Variables.players[index].setStep(1);
                    Variables.players[index].setLastStep(2);
                }
            }
        } else {
            Variables.players[index].setStep(1);
            Variables.players[index].setLastStep(0);
        }
    }
    public static void processNPCMovement(int index) {
        if (Variables.MapNPCs[index].getIsMoving() > 0) {
            if (Variables.MapNPCs[index].getOffsetX() == 0 && Variables.MapNPCs[index].getOffsetY() == 0) {
                Variables.MapNPCs[index].setMoving(0);
                if (Variables.MapNPCs[index].getStep() == 0) {
                    Variables.MapNPCs[index].setStep(1);
                    Variables.MapNPCs[index].setLastStep(0);
                } else if (Variables.MapNPCs[index].getStep() == 0) {
                    if (Variables.MapNPCs[index].getLastStep() == 0) {
                        Variables.MapNPCs[index].setStep(2);
                    } else if (Variables.MapNPCs[index].getLastStep() == 2) {
                        Variables.MapNPCs[index].setStep(0);
                    }
                } else if (Variables.MapNPCs[index].getStep() == 2) {
                    Variables.MapNPCs[index].setStep(1);
                    Variables.MapNPCs[index].setLastStep(2);
                }
            }
        } else {
            Variables.MapNPCs[index].setStep(1);
            Variables.MapNPCs[index].setLastStep(0);
        }
    }
    //
}
