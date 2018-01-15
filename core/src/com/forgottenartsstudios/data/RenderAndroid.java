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
 * Created by forgo on 10/6/2017.
 */

public class RenderAndroid {
    public static float tapAlpha = 1f;
    public static float oldAlpha;
    public static boolean tapPlus;
    public static Color c;
    public static GlyphLayout layout = new GlyphLayout();

    ////////////////////
    // Routine Timers //
    ////////////////////
    private static final long UpdateTime_InputTimer = 500;
    private static final long UpdateTime_TapAnimTimer = 50;
    private static final long UpdateTime_BuyMsg = 1000;
    private static final long UpdateTime_Damage = 50;
    private static final long UpdateTime_LongPress = 1000;
    private static final long UpdateTime_Loading = 500;
    private static final long UpdateTime_CastTime = 1000;

    ////////////////////////////
    // Update Routines Checks //
    ////////////////////////////
    private static long LastUpdateTime_InputTimer;
    private static long LastUpdateTime_TouchDown;
    private static long LastUpdateTime_TapAnim;
    private static long LastUpdateTime_BuyMsg;
    private static long LastUpdateTime_Damage;
    private static long LastUpdateTime_LongPress;
    private static long LastUpdateTime_Loading;
    private static long LastUpdateTime_CastTime;

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

        drawText("Server Status: ", 105, 200, Color.WHITE);
        if (Variables.serverOnline) {
            drawText("Online", 305, 200, Color.GREEN);
        } else {
            drawText("Offline", 305, 200, Color.RED);
        }
    }
    public static void gsLogin(long tickCount) {
        batcher.draw(AssetLoader.loginMenuBG, 0, 0, 480, 854, 0, 0, 480, 854, false, true);

        if (Variables.Login_ID != null && !Variables.Login_ID.isEmpty()) {
            drawText(Variables.Login_ID, 151, 325, Color.WHITE);
        }
        if (Variables.Login_PW != null && !Variables.Login_PW.isEmpty()) {
            String starPW = "";
            for (int i = 1; i <= Variables.Login_PW.length(); i++) {
                starPW = starPW + "x";
            }
            drawText(starPW, 151, 381, Color.WHITE);
        }
        if (Variables.AccountNotFound) {
            drawText("Account Not Found", 132, 501, Color.RED);
        }
        if (Variables.PasswordReq) {
            drawText("Password Required", 132, 501, Color.RED);
        }
        if (Variables.IDReq) {
            drawText("ID Required", 177, 501, Color.RED);
        }
        if (Variables.AccountRegistered) {
            drawText("Account Registered", 122, 501, Color.YELLOW);
        }

        if (Variables.saveLogin) {
            drawText("x", 151, 437, Color.WHITE);
        }

        drawText("Server Status: ", 105, 200, Color.WHITE);
        if (Variables.serverOnline) {
            drawText("Online", 285, 200, Color.GREEN);
        } else {
            drawText("Offline", 285, 200, Color.RED);
        }
    }
    public static void gsCharSelect(long tickCount) {
        batcher.draw(AssetLoader.charSelectBG, 0, 0, 480, 854, 0, 0, 480, 854, false, true);

        if (Variables.MyAccount.chars[1].getName() != null && !Variables.MyAccount.chars[1].getName().isEmpty()) {
            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.MyAccount.chars[1].getSprite()], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 40, 370);

            drawText("Name: " + Variables.MyAccount.chars[1].getName(), 110, 330, Color.WHITE);
            if (Variables.MyAccount.chars[1].getJob() == Variables.JOB_WARRIOR) {
                drawText("Job: Warrior", 110, 350, Color.WHITE);
            } else if (Variables.MyAccount.chars[1].getJob() == Variables.JOB_WIZARD) {
                drawText("Job: Mage", 110, 350, Color.WHITE);
            } else if (Variables.MyAccount.chars[1].getJob() == Variables.JOB_CLERIC) {
                drawText("Job: Cleric", 110, 350, Color.WHITE);
            } else if (Variables.MyAccount.chars[1].getJob() == Variables.JOB_RANGER) {
                drawText("Job: Ranger", 110, 350, Color.WHITE);
            } else if (Variables.MyAccount.chars[1].getJob() == Variables.JOB_ROGUE) {
                drawText("Job: Rogue", 110, 350, Color.WHITE);
            }
            drawText("Lvl: " + Variables.MyAccount.chars[1].getLevel(), 110, 370, Color.WHITE);
            drawText("HP: " + Variables.MyAccount.chars[1].getHP() + "/" + Variables.MyAccount.chars[1].getMaxHP(), 110, 390, Color.WHITE);
            drawText("MP: " + Variables.MyAccount.chars[1].getMP() + "/" + Variables.MyAccount.chars[1].getMaxMP(), 110, 410, Color.WHITE);

            // STATS
            drawText("STR: " + Variables.MyAccount.chars[1].getSTR(), 360, 330, Color.WHITE);
            drawText("DEF: " + Variables.MyAccount.chars[1].getDEF(), 360, 350, Color.WHITE);
            drawText("VIT: " + Variables.MyAccount.chars[1].getVIT(), 360, 370, Color.WHITE);
            drawText("AGI: " + Variables.MyAccount.chars[1].getAGI(), 360, 390, Color.WHITE);
            drawText("MAG: " + Variables.MyAccount.chars[1].getMAG(), 360, 410, Color.WHITE);
        } else {
            drawText("Create Character", 135, 370, Color.WHITE);
        }
        if (Variables.MyAccount.chars[2].getName() != null && !Variables.MyAccount.chars[2].getName().isEmpty()) {
            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.MyAccount.chars[2].getSprite()], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 40, 530);

            drawText("Name: " + Variables.MyAccount.chars[2].getName(), 110, 495, Color.WHITE);
            if (Variables.MyAccount.chars[2].getJob() == Variables.JOB_WARRIOR) {
                drawText("Job: Warrior", 110, 515, Color.WHITE);
            } else if (Variables.MyAccount.chars[2].getJob() == Variables.JOB_WIZARD) {
                drawText("Job: Mage", 110, 515, Color.WHITE);
            } else if (Variables.MyAccount.chars[2].getJob() == Variables.JOB_CLERIC) {
                drawText("Job: Cleric", 110, 515, Color.WHITE);
            } else if (Variables.MyAccount.chars[2].getJob() == Variables.JOB_RANGER) {
                drawText("Job: Ranger", 110, 515, Color.WHITE);
            } else if (Variables.MyAccount.chars[2].getJob() == Variables.JOB_ROGUE) {
                drawText("Job: Rogue", 110, 515, Color.WHITE);
            }
            drawText("Lvl: " + Variables.MyAccount.chars[2].getLevel(), 110, 535, Color.WHITE);
            drawText("HP: " + Variables.MyAccount.chars[2].getHP() + "/" + Variables.MyAccount.chars[2].getMaxHP(), 110, 555, Color.WHITE);
            drawText("MP: " + Variables.MyAccount.chars[2].getMP() + "/" + Variables.MyAccount.chars[2].getMaxMP(), 110, 575, Color.WHITE);

            // STATS
            drawText("STR: " + Variables.MyAccount.chars[2].getSTR(), 360, 495, Color.WHITE);
            drawText("DEF: " + Variables.MyAccount.chars[2].getDEF(), 360, 515, Color.WHITE);
            drawText("VIT: " + Variables.MyAccount.chars[2].getVIT(), 360, 535, Color.WHITE);
            drawText("AGI: " + Variables.MyAccount.chars[2].getAGI(), 360, 555, Color.WHITE);
            drawText("MAG: " + Variables.MyAccount.chars[2].getMAG(), 360, 575, Color.WHITE);
        } else {
            drawText("Create Character", 135, 535, Color.WHITE);
        }
        if (Variables.MyAccount.chars[3].getName() != null && !Variables.MyAccount.chars[3].getName().isEmpty()) {
            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.MyAccount.chars[3].getSprite()], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 40, 695);

            drawText("Name: " + Variables.MyAccount.chars[3].getName(), 110, 660, Color.WHITE);
            if (Variables.MyAccount.chars[3].getJob() == Variables.JOB_WARRIOR) {
                drawText("Job: Warrior", 110, 680, Color.WHITE);
            } else if (Variables.MyAccount.chars[3].getJob() == Variables.JOB_WIZARD) {
                drawText("Job: Mage", 110, 680, Color.WHITE);
            } else if (Variables.MyAccount.chars[3].getJob() == Variables.JOB_CLERIC) {
                drawText("Job: Cleric", 110, 680, Color.WHITE);
            } else if (Variables.MyAccount.chars[3].getJob() == Variables.JOB_RANGER) {
                drawText("Job: Ranger", 110, 680, Color.WHITE);
            } else if (Variables.MyAccount.chars[3].getJob() == Variables.JOB_ROGUE) {
                drawText("Job: Rogue", 110, 680, Color.WHITE);
            }
            drawText("Lvl: " + Variables.MyAccount.chars[3].getLevel(), 110, 700, Color.WHITE);
            drawText("HP: " + Variables.MyAccount.chars[3].getHP() + "/" + Variables.MyAccount.chars[3].getMaxHP(), 110, 720, Color.WHITE);
            drawText("MP: " + Variables.MyAccount.chars[3].getMP() + "/" + Variables.MyAccount.chars[3].getMaxMP(), 110, 740, Color.WHITE);

            // STATS
            drawText("STR: " + Variables.MyAccount.chars[3].getSTR(), 360, 660, Color.WHITE);
            drawText("DEF: " + Variables.MyAccount.chars[3].getDEF(), 360, 680, Color.WHITE);
            drawText("VIT: " + Variables.MyAccount.chars[3].getVIT(), 360, 700, Color.WHITE);
            drawText("AGI: " + Variables.MyAccount.chars[3].getAGI(), 360, 720, Color.WHITE);
            drawText("MAG: " + Variables.MyAccount.chars[3].getMAG(), 360, 740, Color.WHITE);
        } else {
            drawText("Create Character", 135, 700, Color.WHITE);
        }
    }
    public static void gsCharCreate(long tickCount) {
        batcher.draw(AssetLoader.charCreateBG, 0, 0, 480, 854, 0, 0, 480, 854, false, true);
        if (Variables.TempName != null && !Variables.TempName.isEmpty()) {
            drawText(Variables.TempName, 159, 257, Color.WHITE);
        }

        if (Variables.TempJob == Variables.JOB_WARRIOR) {
            batcher.draw(AssetLoader.warriorEmb, 11, 327, 90, 90, 0, 0, 90, 90, false, true);
            layout.setText(AssetLoader.font, "Job: Warrior");
            float width = layout.width;

            drawText("Job: Warrior", 240 - ((int)width / 2), 435, Color.WHITE);

            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.TempSprite], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 224, 510);

            // HP-MP
            drawText("HP: " + (Jobs.Warrior.getVIT() * 2) * (Jobs.Warrior.getSTR() / 2), 80, 610, Color.WHITE);
            drawText("MP: " + (Jobs.Warrior.getMAG() * 2) * (Jobs.Warrior.getDEF() / 2), 80, 630, Color.WHITE);

            // STATS
            drawText("STR: " + Jobs.Warrior.getSTR(), 315, 610, Color.WHITE);
            drawText("DEF: " + Jobs.Warrior.getDEF(), 315, 630, Color.WHITE);
            drawText("VIT: " + Jobs.Warrior.getVIT(), 315, 650, Color.WHITE);
            drawText("AGI: " + Jobs.Warrior.getAGI(), 315, 670, Color.WHITE);
            drawText("MAG: " + Jobs.Warrior.getMAG(), 315, 690, Color.WHITE);
        } else {
            batcher.draw(AssetLoader.warriorEmbT, 11, 327, 90, 90, 0, 0, 90, 90, false, true);
        }
        if (Variables.TempJob == Variables.JOB_WIZARD) {
            batcher.draw(AssetLoader.wizardEmb, 103, 327, 90, 90, 0, 0, 90, 90, false, true);
            layout.setText(AssetLoader.font, "Job: Mage");
            float width = layout.width;

            drawText("Job: Mage", 240 - ((int)width / 2), 435, Color.WHITE);

            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.TempSprite], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 224, 510);

            // HP-MP
            drawText("HP: " + (Jobs.Wizard.getVIT() * 2) * (Jobs.Wizard.getSTR() / 2), 80, 610, Color.WHITE);
            drawText("MP: " + (Jobs.Wizard.getMAG() * 2) * (Jobs.Wizard.getDEF() / 2), 80, 630, Color.WHITE);

            // STATS
            drawText("STR: " + Jobs.Wizard.getSTR(), 315, 610, Color.WHITE);
            drawText("DEF: " + Jobs.Wizard.getDEF(), 315, 630, Color.WHITE);
            drawText("VIT: " + Jobs.Wizard.getVIT(), 315, 650, Color.WHITE);
            drawText("AGI: " + Jobs.Wizard.getAGI(), 315, 670, Color.WHITE);
            drawText("MAG: " + Jobs.Wizard.getMAG(), 315, 690, Color.WHITE);
        } else {
            batcher.draw(AssetLoader.wizardEmbT, 103, 327, 90, 90, 0, 0, 90, 90, false, true);
        }
        if (Variables.TempJob == Variables.JOB_CLERIC) {
            batcher.draw(AssetLoader.clericEmb, 195, 327, 90, 90, 0, 0, 90, 90, false, true);
            layout.setText(AssetLoader.font, "Job: Cleric");
            float width = layout.width;

            drawText("Job: Cleric", 240 - ((int)width / 2), 435, Color.WHITE);

            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.TempSprite], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 224, 510);

            // HP-MP
            drawText("HP: " + (Jobs.Cleric.getVIT() * 2) * (Jobs.Cleric.getSTR() / 2), 80, 610, Color.WHITE);
            drawText("MP: " + (Jobs.Cleric.getMAG() * 2) * (Jobs.Cleric.getDEF() / 2), 80, 630, Color.WHITE);

            // STATS
            drawText("STR: " + Jobs.Cleric.getSTR(), 315, 610, Color.WHITE);
            drawText("DEF: " + Jobs.Cleric.getDEF(), 315, 630, Color.WHITE);
            drawText("VIT: " + Jobs.Cleric.getVIT(), 315, 650, Color.WHITE);
            drawText("AGI: " + Jobs.Cleric.getAGI(), 315, 670, Color.WHITE);
            drawText("MAG: " + Jobs.Cleric.getMAG(), 315, 690, Color.WHITE);
        } else {
            batcher.draw(AssetLoader.clericEmbT, 195, 327, 90, 90, 0, 0, 90, 90, false, true);
        }
        if (Variables.TempJob == Variables.JOB_RANGER) {
            batcher.draw(AssetLoader.rangerEmb, 287, 327, 90, 90, 0, 0, 90, 90, false, true);
            layout.setText(AssetLoader.font, "Job: Ranger");
            float width = layout.width;

            drawText("Job: Ranger", 240 - ((int)width / 2), 435, Color.WHITE);

            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.TempSprite], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 224, 510);

            // HP-MP
            drawText("HP: " + (Jobs.Ranger.getVIT() * 2) * (Jobs.Ranger.getSTR() / 2), 80, 610, Color.WHITE);
            drawText("MP: " + (Jobs.Ranger.getMAG() * 2) * (Jobs.Ranger.getDEF() / 2), 80, 630, Color.WHITE);

            // STATS
            drawText("STR: " + Jobs.Ranger.getSTR(), 315, 610, Color.WHITE);
            drawText("DEF: " + Jobs.Ranger.getDEF(), 315, 630, Color.WHITE);
            drawText("VIT: " + Jobs.Ranger.getVIT(), 315, 650, Color.WHITE);
            drawText("AGI: " + Jobs.Ranger.getAGI(), 315, 670, Color.WHITE);
            drawText("MAG: " + Jobs.Ranger.getMAG(), 315, 690, Color.WHITE);
        } else {
            batcher.draw(AssetLoader.rangerEmbT, 287, 327, 90, 90, 0, 0, 90, 90, false, true);
        }
        if (Variables.TempJob == Variables.JOB_ROGUE) {
            batcher.draw(AssetLoader.rogueEmb, 379, 327, 90, 90, 0, 0, 90, 90, false, true);
            layout.setText(AssetLoader.font, "Job: Rogue");
            float width = layout.width;

            drawText("Job: Rogue", 240 - ((int)width / 2), 435, Color.WHITE);

            TextureRegion spriteTex = new TextureRegion(AssetLoader.sprites[Variables.TempSprite], 32, 72, 32, 36);
            spriteTex.flip(false, true);

            batcher.draw(spriteTex, 224, 510);

            // HP-MP
            drawText("HP: " + (Jobs.Rogue.getVIT() * 2) * (Jobs.Rogue.getSTR() / 2), 80, 610, Color.WHITE);
            drawText("MP: " + (Jobs.Rogue.getMAG() * 2) * (Jobs.Rogue.getDEF() / 2), 80, 630, Color.WHITE);

            // STATS
            drawText("STR: " + Jobs.Rogue.getSTR(), 315, 610, Color.WHITE);
            drawText("DEF: " + Jobs.Rogue.getDEF(), 315, 630, Color.WHITE);
            drawText("VIT: " + Jobs.Rogue.getVIT(), 315, 650, Color.WHITE);
            drawText("AGI: " + Jobs.Rogue.getAGI(), 315, 670, Color.WHITE);
            drawText("MAG: " + Jobs.Rogue.getMAG(), 315, 690, Color.WHITE);
        } else {
            batcher.draw(AssetLoader.rogueEmbT, 379, 327, 90, 90, 0, 0, 90, 90, false, true);
        }
        batcher.draw(AssetLoader.male, 154, 498, 60, 60, 0, 0, 60, 60, false, true);
        batcher.draw(AssetLoader.female, 266, 498, 60, 60, 0, 0, 60, 60, false, true);
    }
    private static int firstTick = 0;
    public static void gsLoading(long tickCount) {
        batcher.draw(AssetLoader.loadBG, 0, 0, 480, 854, 0, 0, 480, 854, false, true);

        if (firstTick == 0) {
            LastUpdateTime_Loading = tickCount;
            firstTick++;
        }

        drawText("Server Status: ", 105, 200, Color.WHITE);
        if (Variables.serverOnline) {
            drawText("Online", 305, 200, Color.GREEN);
        } else {
            drawText("Offline", 305, 200, Color.RED);
        }

        if (LastUpdateTime_Loading < tickCount) {
            //if (firstTick > 0) {
                AssetLoader.checkMaps();
                for (int i = 1; i <= Variables.MaxMaps; i++) {
                    AssetLoader.clearMap(i);
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

        if (Variables.Players[Variables.MyIndex].getMoving() == 0) {
            if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                InputAndroid.handleInput();
            } else if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                InputAndroid.handleAndroidInput();
            }
        }

        if (LastUpdateTime_CastTime < tickCount) {
            for (int a = 1; a <= 60; a++) {
                if (Variables.Players[Variables.MyIndex] != null) {
                    if (Variables.Players[Variables.MyIndex].spells != null) {
                        if (Variables.Players[Variables.MyIndex].spells[a] != null) {
                            if (Variables.Spells != null) {
                                if (Variables.Spells[Variables.Players[Variables.MyIndex].spells[a].getSpellNum()] != null) {
                                    if (Variables.Players[Variables.MyIndex].spells[a].getSpellNum() > 0) {
                                        if (Variables.Players[Variables.MyIndex].spells[a].getCastTimeTimer() < Variables.Players[Variables.MyIndex].spells[a].getCastTime()) {
                                            Variables.Players[Variables.MyIndex].spells[a].setCastTimeTimer(Variables.Players[Variables.MyIndex].spells[a].getCastTimeTimer() + 1);
                                        } else {
                                            if (Variables.Players[Variables.MyIndex].spells[a].getCastTime() > 0) {
                                                Variables.Players[Variables.MyIndex].spells[a].setCoolDown(Variables.Spells[Variables.Players[Variables.MyIndex].spells[a].getSpellNum()].CoolDown);
                                                Variables.Players[Variables.MyIndex].spells[a].setCoolDownTimer(0);
                                            }
                                            Variables.Players[Variables.MyIndex].spells[a].setCastTime(0);
                                            Variables.Players[Variables.MyIndex].spells[a].setCastTimeTimer(0);
                                        }
                                        if (Variables.Players[Variables.MyIndex].spells[a].getCoolDownTimer() < Variables.Players[Variables.MyIndex].spells[a].getCoolDown()) {
                                            Variables.Players[Variables.MyIndex].spells[a].setCoolDownTimer(Variables.Players[Variables.MyIndex].spells[a].getCoolDownTimer() + 1);
                                        } else {
                                            Variables.Players[Variables.MyIndex].spells[a].setCoolDown(0);
                                            Variables.Players[Variables.MyIndex].spells[a].setCoolDownTimer(0);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            LastUpdateTime_CastTime = tickCount + UpdateTime_CastTime;
        }

        for (int i = 1; i <= Variables.MaxMapSpells; i++) {
            if (Variables.MapSpells[i] != null) {
                if (Variables.MapSpells[i].getSpellNum() > 0) {
                    if (Variables.MapSpells[i].getTimer() < tickCount) {
                        if (Variables.MapSpells[i].getFrame() < Variables.Spells[Variables.MapSpells[i].getSpellNum()].AnimFrames) {
                            Variables.MapSpells[i].setFrame(Variables.MapSpells[i].getFrame() + 1);
                            Variables.MapSpells[i].setTimer(tickCount + Variables.Spells[Variables.MapSpells[i].getSpellNum()].AnimSpeed);
                        } else {
                            Variables.MapSpells[i] = new MapSpell();
                        }
                    }
                }
            }
        }

        if (LastUpdateTime_InputTimer < tickCount) {
            if (Variables.pauseMovement) {
                Variables.pauseMovement = false;
            }
            if (Variables.usePoint) {
                if (Variables.usePointTimer == 0) {
                    Variables.usePoint = false;
                }
                Variables.usePointTimer--;
            }
            LastUpdateTime_InputTimer = tickCount + UpdateTime_InputTimer;
        }

        /*if (LastUpdateTime_TouchDown < tickCount) {
            if (Variables.inInventory) {
                if (Variables.touchDown) {
                    if (Variables.touchDownTimer == 0) {
                        Variables.touchDown = false;
                    }
                    Variables.touchDownTimer--;
                }
            }
            LastUpdateTime_TouchDown = tickCount + UpdateTime_TouchDownTimer;
        }*/

        for (int i = 1; i <= Variables.MaxPlayers; i++) {
            processMovement(i);
        }
        for (int i = 1; i <= Variables.MaxMapNPCs; i++) {
            processNPCMovement(i);
        }

        if (!Variables.inMenu && !Variables.inInventory && !Variables.inChat && !Variables.inStatus && !Variables.inShop) {
            InputAndroid.checkMovement();
            InputAndroid.checkAttack(tickCount);
            InputAndroid.checkPickUp();
        }

        batcher.draw(AssetLoader.inGameBG, 0, 0, 480, 854, 0, 0, 480, 854, false, true);
        renderMap_Lower();
        drawItems();
        drawPlayersAndNPCs(tickCount);
        renderMap_Upper();
        drawNames();
        drawUI();

        // FPS
        drawText("" + Gdx.graphics.getFramesPerSecond(), 20, 20, Color.WHITE);

        if (Variables.inMenu) { drawMenu(); }
        if (Variables.inShop) { drawShop(); }
        if (Variables.inStatus) { drawStatus(); }
        if (Variables.inInventory) { drawInventory(); }
        if (Variables.inChat) { drawChat(); }
        if (Variables.inSpells) { drawSpellInventory(); }
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
        if (Variables.Players[i] != null) {

            // Check for animation
            if (Variables.Players[i].getAttacking() == 1) {
                if (Variables.Players[i].getAttackTimer() + 500 > tickCount) {
                    Variables.Players[i].setStep(2);
                }
            }

            if (Variables.Players[i].getAttackTimer() + 1000 < tickCount) {
                Variables.Players[i].setAttacking(0);
                Variables.Players[i].setAttackTimer(0);
            }

            if (Variables.Players[i].getSprite() <= 0) {
                return;
            }

            switch (Variables.Players[i].getDir()) {
                case Variables.DIR_UP:
                    switch (Variables.Players[i].getStep()) {
                        case 0:
                            GameRenderer.batcher.draw(AssetLoader.spritesUp1[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                        case 1:
                            GameRenderer.batcher.draw(AssetLoader.spritesUp2[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                        case 2:
                            GameRenderer.batcher.draw(AssetLoader.spritesUp3[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                    }
                    return;
                case Variables.DIR_DOWN:
                    switch (Variables.Players[i].getStep()) {
                        case 0:
                            GameRenderer.batcher.draw(AssetLoader.spritesDown1[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                        case 1:
                            GameRenderer.batcher.draw(AssetLoader.spritesDown2[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                        case 2:
                            GameRenderer.batcher.draw(AssetLoader.spritesDown3[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                    }
                    return;
                case Variables.DIR_LEFT:
                    switch (Variables.Players[i].getStep()) {
                        case 0:
                            GameRenderer.batcher.draw(AssetLoader.spritesLeft1[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                        case 1:
                            GameRenderer.batcher.draw(AssetLoader.spritesLeft2[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                        case 2:
                            GameRenderer.batcher.draw(AssetLoader.spritesLeft3[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                    }
                    return;
                case Variables.DIR_RIGHT:
                    switch (Variables.Players[i].getStep()) {
                        case 0:
                            GameRenderer.batcher.draw(AssetLoader.spritesRight1[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                        case 1:
                            GameRenderer.batcher.draw(AssetLoader.spritesRight2[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                        case 2:
                            GameRenderer.batcher.draw(AssetLoader.spritesRight3[Variables.Players[i].getSprite()], x + 16, y + 11, 32, 36);
                            break;
                    }
                    return;
            }
            GameRenderer.batcher.draw(AssetLoader.emptyMapBar, x, y + 34, 32, 4);

            double maxWidth = ((((double) Variables.Players[Variables.MyIndex].getHP()) / 192) / ((double) Variables.Players[Variables.MyIndex].getMaxHP() / 192) * 192);
            batcher.draw(AssetLoader.hpBar, 532, 150, (int)maxWidth, 12, 0, 0, (int)maxWidth, 12, false, true);
            GameRenderer.batcher.draw(AssetLoader.hpMapBar, x, y + 34, (int)maxWidth, 4);

            if (Variables.Players[Variables.MyIndex].getParty() > 0) {
                for (int a = 1; a <= 3; a++) {
                    if (Variables.MyParty.members[a] > 0) {
                        if (Variables.Players[Variables.MyParty.members[a]].getMap() == Variables.Players[Variables.MyIndex].getMap()) {
                            GameRenderer.batcher.draw(AssetLoader.emptyMapBar, x, y + 34, 32, 4);

                            maxWidth = ((((double) Variables.Players[Variables.MyParty.members[a]].getHP() / 32) / ((double) Variables.Players[Variables.MyParty.members[a]].getMaxHP() / 32) * 32));
                            batcher.draw(AssetLoader.hpBar, 532, 150, (int)maxWidth, 12, 0, 0, (int)maxWidth, 12, false, true);

                            x = ((Variables.Players[Variables.MyParty.members[a]].getX() * Variables.MoveSize) + Variables.Players[Variables.MyParty.members[a]].getOffsetX());
                            y = ((Variables.Players[Variables.MyParty.members[a]].getY() * Variables.MoveSize) + Variables.Players[Variables.MyParty.members[a]].getOffsetY());
                            GameRenderer.batcher.draw(AssetLoader.hpMapBar, x, y + 34, (int)maxWidth, 4);
                        }
                    }
                }
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
                        GameRenderer.batcher.draw(AssetLoader.spritesUp1[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                    case 1:
                        GameRenderer.batcher.draw(AssetLoader.spritesUp2[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                    case 2:
                        GameRenderer.batcher.draw(AssetLoader.spritesUp3[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                }
                return;
            case Variables.DIR_DOWN:
                switch (Variables.MapNPCs[mapNpcNum].getStep()) {
                    case 0:
                        GameRenderer.batcher.draw(AssetLoader.spritesDown1[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                    case 1:
                        GameRenderer.batcher.draw(AssetLoader.spritesDown2[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                    case 2:
                        GameRenderer.batcher.draw(AssetLoader.spritesDown3[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                }
                return;
            case Variables.DIR_LEFT:
                switch (Variables.MapNPCs[mapNpcNum].getStep()) {
                    case 0:
                        GameRenderer.batcher.draw(AssetLoader.spritesLeft1[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                    case 1:
                        GameRenderer.batcher.draw(AssetLoader.spritesLeft2[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                    case 2:
                        GameRenderer.batcher.draw(AssetLoader.spritesLeft3[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                }
                return;
            case Variables.DIR_RIGHT:
                switch (Variables.MapNPCs[mapNpcNum].getStep()) {
                    case 0:
                        GameRenderer.batcher.draw(AssetLoader.spritesRight1[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                    case 1:
                        GameRenderer.batcher.draw(AssetLoader.spritesRight2[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                    case 2:
                        GameRenderer.batcher.draw(AssetLoader.spritesRight3[Variables.MapNPCs[mapNpcNum].getSprite()], x + 16, y + 11, 32, 36);
                        break;
                }
                return;
        }
    }
    public static void renderMap_Lower() {
        if (!Variables.reloadingMap) {
            int Text = 0;

            int mapNum = Variables.Players[Variables.MyIndex].getMap();

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
                                    Variables.mapTiles[mapNum].TileLayer[LoopL].Tile[Text].PixelPosX + 16,
                                    Variables.mapTiles[mapNum].TileLayer[LoopL].Tile[Text].PixelPosY + 16,
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

            int mapNum = Variables.Players[Variables.MyIndex].getMap();

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
                                    Variables.mapTiles[mapNum].TileLayer[LoopL].Tile[Text].PixelPosX + 16,
                                    Variables.mapTiles[mapNum].TileLayer[LoopL].Tile[Text].PixelPosY + 16,
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
        batcher.draw(AssetLoader.menuBG, 16, 16, 448, 448, 0, 0, 448, 448, false, true);

        layout.setText(AssetLoader.font, Variables.Shop.Name);
        float width = layout.width;// contains the width of the current set text

        float nameX = 240 - ((int)width / 2);
        float nameY = 32 - 8;
        drawText(Variables.Shop.Name, nameX, nameY, Color.WHITE);

        layout.setText(AssetLoader.nameFont, Variables.Shop.welcomeMsg);
        width = layout.width;// contains the width of the current set text

        nameX = 240 - ((int)width / 2);
        nameY = 82 - 8;
        drawName(Variables.Shop.welcomeMsg, nameX, nameY, Color.WHITE);

        int x = 36;
        int y = 122 - 8;
        for (int i = 1; i <= 20; i++) {
            if (i == 11) {
                x = 36;
                y = 163 - 8;
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

            nameX = 240 - ((int)width / 2);
            nameY = 233 - 8;
            drawText(Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].Name, nameX, nameY, Color.WHITE);

            drawText("Lvl: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].LVL, 122, 282, Color.WHITE);
            drawText("HP: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].HP, 212, 282, Color.WHITE);
            drawText("MP: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].MP, 292, 282, Color.WHITE);

            drawText("STR: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].STR, 32, 312, Color.WHITE);
            drawText("DEF: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].DEF, 122, 312, Color.WHITE);
            drawText("VIT: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].VIT, 212, 312, Color.WHITE);
            drawText("AGI: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].AGI, 292, 312, Color.WHITE);
            drawText("MAG: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].MAG, 372, 312, Color.WHITE);

            layout.setText(AssetLoader.nameFont, "Cost: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].Cost + " Gold");
            width = layout.width;// contains the width of the current set text

            nameX = 240 - ((int)width / 2);
            nameY = 342;
            drawName("Cost: " + Variables.Items[Variables.Shop.itemNum[Variables.selectedShopSlot]].Cost + " Gold", nameX, nameY, Color.YELLOW);
        }

        if (Variables.BoughtMsgTimer > 0) {
            layout.setText(AssetLoader.font, "Purchase successful!");
            width = layout.width;// contains the width of the current set text

            nameX = 240 - ((int)width / 2);
            nameY = 370;
            drawText("Purchase successful!", nameX, nameY, Color.YELLOW);
        }
        if (Variables.NotEnoughGoldMsgTimer > 0) {
            layout.setText(AssetLoader.font, "You don't have enough gold.");
            width = layout.width;// contains the width of the current set text

            nameX = 240 - ((int)width / 2);
            nameY = 350;
            drawText("You don't have enough gold.", nameX, nameY, Color.RED);
        }

        // Back Button
        drawText("Back", 24, 437, Color.WHITE);

        // Buy Button
        drawText("Buy", 418, 437, Color.WHITE);
    }
    public static void drawInventory() {
        batcher.draw(AssetLoader.menuBG, 16, 16, 448, 448, 0, 0, 448, 448, false, true);

        int x = 36;
        int y = 36;
        for (int i = 1; i <= 60; i++) {
            if (Variables.Players[Variables.MyIndex].inventory != null) {
                if (Variables.Players[Variables.MyIndex].inventory[i] != null) {
                    if (i == 11) {
                        x = 36;
                        y = 77;
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.Players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.Players[Variables.MyIndex].getWeapon() || i == Variables.Players[Variables.MyIndex].getArmor() || i == Variables.Players[Variables.MyIndex].getHelmet() || i == Variables.Players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    } else if (i == 21) {
                        x = 36;
                        y = 118;
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.Players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.Players[Variables.MyIndex].getWeapon() || i == Variables.Players[Variables.MyIndex].getArmor() || i == Variables.Players[Variables.MyIndex].getHelmet() || i == Variables.Players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    } else if (i == 31) {
                        x = 36;
                        y = 159;
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.Players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.Players[Variables.MyIndex].getWeapon() || i == Variables.Players[Variables.MyIndex].getArmor() || i == Variables.Players[Variables.MyIndex].getHelmet() || i == Variables.Players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    } else if (i == 41) {
                        x = 36;
                        y = 200;
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.Players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.Players[Variables.MyIndex].getWeapon() || i == Variables.Players[Variables.MyIndex].getArmor() || i == Variables.Players[Variables.MyIndex].getHelmet() || i == Variables.Players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    } else if (i == 51) {
                        x = 36;
                        y = 241;
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.Players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.Players[Variables.MyIndex].getWeapon() || i == Variables.Players[Variables.MyIndex].getArmor() || i == Variables.Players[Variables.MyIndex].getHelmet() || i == Variables.Players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    } else {
                        if (Variables.selectedInvSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[Variables.Players[Variables.MyIndex].inventory[i].getItemNum()].Icon], x + 5, y + 5, 24, 24, 0, 0, 24, 24, false, true);
                        }
                        if (i == Variables.Players[Variables.MyIndex].getWeapon() || i == Variables.Players[Variables.MyIndex].getArmor() || i == Variables.Players[Variables.MyIndex].getHelmet() || i == Variables.Players[Variables.MyIndex].getOffhand()) {
                            drawName("E", x + 24, y + 20, Color.WHITE);
                        }
                        x = x + 41;
                    }

                    // Selected Item Info
                    if (Variables.selectedInvSlot > 0) {
                        int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.selectedInvSlot].getItemNum();
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
                                layout.setText(AssetLoader.nameFont, "Amount: " + Variables.Players[Variables.MyIndex].inventory[Variables.selectedInvSlot].getItemVal());
                                width = layout.width;// contains the width of the current set text

                                nameX = 240 - ((int) width / 2);
                                nameY = 380;

                                drawName("Amount: " + Variables.Players[Variables.MyIndex].inventory[Variables.selectedInvSlot].getItemVal(), nameX, nameY, Color.YELLOW);
                            }
                        }
                    }
                }
            }
        }

        // Back Button
        drawText("Back", 24, 437, Color.WHITE);

        // Use Button
        drawText("Use", 418, 437, Color.WHITE);
    }
    public static void drawSpellInventory() {
        batcher.draw(AssetLoader.menuBG, 16, 16, 448, 448, 0, 0, 448, 448, false, true);

        int x = 36;
        int y = 36;
        for (int i = 1; i <= 60; i++) {
            if (Variables.Players[Variables.MyIndex].spells != null) {
                if (Variables.Players[Variables.MyIndex].spells[i] != null) {
                    if (i == 11) {
                        x = 36;
                        y = 77;
                        if (Variables.selectedSpellSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                            batcher.draw(AssetLoader.icons[Variables.Spells[Variables.Players[Variables.MyIndex].spells[i].getSpellNum()].Icon], x + 2, y + 2, 32, 32, 0, 0, 56, 56, false, true);
                        }
                        x = x + 41;
                    } else if (i == 21) {
                        x = 36;
                        y = 118;
                        if (Variables.selectedSpellSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                            batcher.draw(AssetLoader.icons[Variables.Spells[Variables.Players[Variables.MyIndex].spells[i].getSpellNum()].Icon], x + 2, y + 2, 32, 32, 0, 0, 56, 56, false, true);
                        }
                        x = x + 41;
                    } else if (i == 31) {
                        x = 36;
                        y = 159;
                        if (Variables.selectedSpellSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                            batcher.draw(AssetLoader.icons[Variables.Spells[Variables.Players[Variables.MyIndex].spells[i].getSpellNum()].Icon], x + 2, y + 2, 32, 32, 0, 0, 56, 56, false, true);
                        }
                        x = x + 41;
                    } else if (i == 41) {
                        x = 36;
                        y = 200;
                        if (Variables.selectedSpellSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                            batcher.draw(AssetLoader.icons[Variables.Spells[Variables.Players[Variables.MyIndex].spells[i].getSpellNum()].Icon], x + 2, y + 2, 32, 32, 0, 0, 56, 56, false, true);
                        }
                        x = x + 41;
                    } else if (i == 51) {
                        x = 36;
                        y = 241;
                        if (Variables.selectedSpellSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                            batcher.draw(AssetLoader.icons[Variables.Spells[Variables.Players[Variables.MyIndex].spells[i].getSpellNum()].Icon], x + 2, y + 2, 32, 32, 0, 0, 56, 56, false, true);
                        }
                        x = x + 41;
                    } else {
                        if (Variables.selectedSpellSlot == i) {
                            batcher.draw(AssetLoader.eqBGs, x, y, 36, 36);
                        } else {
                            batcher.draw(AssetLoader.eqBG, x, y, 36, 36);
                        }
                        if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                            batcher.draw(AssetLoader.icons[Variables.Spells[Variables.Players[Variables.MyIndex].spells[i].getSpellNum()].Icon], x + 2, y + 2, 32, 32, 0, 0, 56, 56, false, true);
                        }
                        x = x + 41;
                    }

                    // Selected Item Info
                    if (Variables.selectedSpellSlot > 0) {
                        int spellNum = Variables.Players[Variables.MyIndex].spells[Variables.selectedSpellSlot].getSpellNum();
                        if (spellNum > 0) {
                            layout.setText(AssetLoader.font, Variables.Spells[spellNum].Name);
                            float width = layout.width;// contains the width of the current set text

                            float nameX = 240 - ((int) width / 2);
                            float nameY = 325;
                            drawText(Variables.Spells[spellNum].Name, nameX, nameY, Color.WHITE);

                            layout.setText(AssetLoader.nameFont, "Lvl: " + Variables.Spells[spellNum].LevelReq);
                            width = layout.width;// contains the width of the current set text

                            nameX = 240 - ((int) width / 2);
                            nameY = 350;
                            drawName("Lvl: " + Variables.Spells[spellNum].LevelReq, nameX, nameY, Color.WHITE);

                            layout.setText(AssetLoader.nameFont, "Strength: " + Variables.Spells[spellNum].DmgHealAmt + "     Cost: " + Variables.Spells[spellNum].MPCost);
                            width = layout.width;// contains the width of the current set text

                            nameX = 240 - ((int) width / 2);
                            nameY = 365;
                            drawName("Strength: " + Variables.Spells[spellNum].DmgHealAmt + "     Cost: " + Variables.Spells[spellNum].MPCost, nameX, nameY, Color.WHITE);
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
        batcher.draw(AssetLoader.menuBG, 16, 16, 448, 448, 0, 0, 448, 448, false, true);

        layout.setText(AssetLoader.font, Variables.Players[Variables.MyIndex].getName());
        float width = layout.width;// contains the width of the current set text

        float nameX = 240 - ((int)width / 2);
        float nameY = 50;

        drawText(Variables.Players[Variables.MyIndex].getName(), nameX, nameY, Color.WHITE);

        int pSTR = 0;
        int pDEF = 0;
        int pVIT = 0;
        int pAGI = 0;
        int pMAG = 0;
        if (Variables.Players[Variables.MyIndex].getWeapon() > 0) {
            int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getWeapon()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.Players[Variables.MyIndex].getOffhand() > 0) {
            int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getOffhand()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.Players[Variables.MyIndex].getArmor() > 0) {
            int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getArmor()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.Players[Variables.MyIndex].getHelmet() > 0) {
            int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getHelmet()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.Players[Variables.MyIndex].getAcc1() > 0) {
            int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getAcc1()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }
        if (Variables.Players[Variables.MyIndex].getAcc2() > 0) {
            int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getAcc2()].getItemNum();
            if (itemNum <= 0) { return; }
            pSTR = pSTR + Variables.Items[itemNum].STR;
            pDEF = pDEF + Variables.Items[itemNum].DEF;
            pVIT = pVIT + Variables.Items[itemNum].VIT;
            pAGI = pAGI + Variables.Items[itemNum].AGI;
            pMAG = pMAG + Variables.Items[itemNum].MAG;
        }

        nameX = 75;
        nameY = 80;
        if (Variables.tempStats.STR > 0) {
            int STR = Variables.Players[Variables.MyIndex].getSTR() + Variables.tempStats.STR;
            drawText("STR: " + STR, nameX, nameY, Color.YELLOW);
        } else {
            drawText("STR: " + Variables.Players[Variables.MyIndex].getSTR(), nameX, nameY, Color.WHITE);
        }
        if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
            drawText("+", nameX - 25, nameY, Color.YELLOW);
        }
        if (pSTR > 0) {
            layout.setText(AssetLoader.font, "STR: " + Variables.Players[Variables.MyIndex].getSTR());
            width = layout.width;// contains the width of the current set text
            drawText("(+" + pSTR + ")", nameX + (width + 10), nameY, Color.GREEN);
        }
        nameX = 75;
        nameY = 110;
        if (Variables.tempStats.DEF > 0) {
            int DEF = Variables.Players[Variables.MyIndex].getDEF() + Variables.tempStats.DEF;
            drawText("DEF: " + DEF, nameX, nameY, Color.YELLOW);
        } else {
            drawText("DEF: " + Variables.Players[Variables.MyIndex].getDEF(), nameX, nameY, Color.WHITE);
        }
        if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
            drawText("+", nameX - 25, nameY, Color.YELLOW);
        }
        if (pDEF > 0) {
            layout.setText(AssetLoader.font, "DEF: " + Variables.Players[Variables.MyIndex].getDEF());
            width = layout.width;// contains the width of the current set text
            drawText("(+" + pDEF + ")", nameX + (width + 10), nameY, Color.GREEN);
        }
        nameX = 75;
        nameY = 140;
        if (Variables.tempStats.VIT > 0) {
            int VIT = Variables.Players[Variables.MyIndex].getVIT() + Variables.tempStats.VIT;
            drawText("VIT: " + VIT, nameX, nameY, Color.YELLOW);
        } else {
            drawText("VIT: " + Variables.Players[Variables.MyIndex].getVIT(), nameX, nameY, Color.WHITE);
        }
        if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
            drawText("+", nameX - 25, nameY, Color.YELLOW);
        }
        if (pVIT > 0) {
            layout.setText(AssetLoader.font, "VIT: " + Variables.Players[Variables.MyIndex].getVIT());
            width = layout.width;// contains the width of the current set text
            drawText("(+" + pVIT + ")", nameX + (width + 10), nameY, Color.GREEN);
        }
        nameX = 75;
        nameY = 170;
        if (Variables.tempStats.AGI > 0) {
            int MAG = Variables.Players[Variables.MyIndex].getAGI() + Variables.tempStats.AGI;
            drawText("AGI: " + MAG, nameX, nameY, Color.YELLOW);
        } else {
            drawText("AGI: " + Variables.Players[Variables.MyIndex].getAGI(), nameX, nameY, Color.WHITE);
        }
        if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
            drawText("+", nameX - 25, nameY, Color.YELLOW);
        }
        if (pAGI > 0) {
            layout.setText(AssetLoader.font, "AGI: " + Variables.Players[Variables.MyIndex].getAGI());
            width = layout.width;// contains the width of the current set text
            drawText("(+" + pAGI + ")", nameX + (width + 10), nameY, Color.GREEN);
        }
        nameX = 75;
        nameY = 200;
        if (Variables.tempStats.MAG > 0) {
            int MAG = Variables.Players[Variables.MyIndex].getMAG() + Variables.tempStats.MAG;
            drawText("MAG: " + MAG, nameX, nameY, Color.YELLOW);
        } else {
            drawText("MAG: " + Variables.Players[Variables.MyIndex].getMAG(), nameX, nameY, Color.WHITE);
        }
        if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
            drawText("+", nameX - 25, nameY, Color.YELLOW);
        }
        if (pMAG > 0) {
            layout.setText(AssetLoader.font, "MAG: " + Variables.Players[Variables.MyIndex].getMAG());
            width = layout.width;// contains the width of the current set text
            drawText("(+" + pMAG + ")", nameX + (width + 10), nameY, Color.GREEN);
        }
        nameX = 75;
        nameY = 230;
        if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
            drawName("Points: " + Variables.Players[Variables.MyIndex].getPoints(), nameX, nameY, Color.YELLOW);
        } else {
            drawName("Points: " + Variables.Players[Variables.MyIndex].getPoints(), nameX, nameY, Color.WHITE);
        }

        // Confirm Button
        if (Variables.tempStats.Points < Variables.Players[Variables.MyIndex].getPoints()) {
            drawText("Confirm", 349, 429, Color.WHITE);
        }

        // Back Button
        drawText("Back", 24, 437, Color.WHITE);
    }
    public static void drawMenu() {
        batcher.draw(AssetLoader.menuBG, 16, 16, 448, 448, 0, 0, 448, 448, false, true);

        if (Variables.Players[Variables.MyIndex].getParty() > 0) {
            for (int i = 1; i <= 3; i++) {
                if (Variables.MyParty.members[i] > 0) {
                    if (Variables.MyIndex == Variables.MyParty.leader) {
                        // Disband/Leave
                        batcher.draw(AssetLoader.disbandBtn, 280, 43, 32, 32, 0, 0, 32, 32, false, true);
                        batcher.draw(AssetLoader.leaveBtn, 280, 78, 32, 32, 0, 0, 32, 32, false, true);

                        // Appoint/Kick
                        if (Variables.MyParty.members[2] > 0) {
                            batcher.draw(AssetLoader.appointBtn, 280, 163, 32, 32, 0, 0, 32, 32, false, true);
                            batcher.draw(AssetLoader.kickBtn, 280, 198, 32, 32, 0, 0, 32, 32, false, true);
                        }
                        if (Variables.MyParty.members[3] > 0) {
                            batcher.draw(AssetLoader.appointBtn, 280, 283, 32, 32, 0, 0, 32, 32, false, true);
                            batcher.draw(AssetLoader.kickBtn, 280, 318, 32, 32, 0, 0, 32, 32, false, true);
                        }

                        drawName("Drop Sorting:", 363, 409, Color.YELLOW);
                        if (Variables.MyParty.dropType == Variables.DROP_SORT_ROUNDROBIN) {
                            drawName("Round Robin", 363, 429, Color.GREEN);
                        } else {
                            drawName("Round Robin", 363, 429, Color.WHITE);
                        }
                        if (Variables.MyParty.dropType == Variables.DROP_SORT_FREEFORALL) {
                            drawName("Free For All", 363, 449, Color.GREEN);
                        } else {
                            drawName("Free For All", 363, 449, Color.WHITE);
                        }
                    } else {
                        if (Variables.MyParty.members[2] == Variables.MyIndex) {
                            batcher.draw(AssetLoader.leaveBtn, 280, 191, 32, 32, 0, 0, 32, 32, false, true);
                        } else if (Variables.MyParty.members[3] == Variables.MyIndex) {
                            batcher.draw(AssetLoader.leaveBtn, 280, 321, 32, 32, 0, 0, 32, 32, false, true);
                        }
                    }
                    // Player Sprite
                    batcher.draw(AssetLoader.spritesDown2[Variables.Players[Variables.MyParty.members[i]].getSprite()], 63, 59 + ((i - 1) * 125), 32, 36);
                    // Player Equipment
                    batcher.draw(AssetLoader.eqBG, 39, 21 + ((i - 1) * 125), 36, 36);
                    if (Variables.Players[Variables.MyParty.members[i]].getWeapon() > 0) {
                        int itemNum = Variables.Players[Variables.MyParty.members[i]].inventory[Variables.Players[Variables.MyParty.members[i]].getWeapon()].getItemNum();
                        if (itemNum > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 39 + 5, 21 + 5 + ((i - 1) * 125), 24, 24, 0, 0, 24, 24, false, true);
                        }
                    }
                    batcher.draw(AssetLoader.eqBG, 21, 58 + ((i - 1) * 125), 36, 36);
                    if (Variables.Players[Variables.MyParty.members[i]].getArmor() > 0) {
                        int itemNum = Variables.Players[Variables.MyParty.members[i]].inventory[Variables.Players[Variables.MyParty.members[i]].getArmor()].getItemNum();
                        if (itemNum > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 21 + 5, 58 + 5 + ((i - 1) * 125), 24, 24, 0, 0, 24, 24, false, true);
                        }
                    }
                    batcher.draw(AssetLoader.eqBG, 76, 21 + ((i - 1) * 125), 36, 36);
                    if (Variables.Players[Variables.MyParty.members[i]].getOffhand() > 0) {
                        int itemNum = Variables.Players[Variables.MyParty.members[i]].inventory[Variables.Players[Variables.MyParty.members[i]].getOffhand()].getItemNum();
                        if (itemNum > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 76 + 5, 21 + 5 + ((i - 1) * 125), 24, 24, 0, 0, 24, 24, false, true);
                        }
                    }
                    batcher.draw(AssetLoader.eqBG, 100, 58 + ((i - 1) * 125), 36, 36);
                    if (Variables.Players[Variables.MyParty.members[i]].getHelmet() > 0) {
                        int itemNum = Variables.Players[Variables.MyParty.members[i]].inventory[Variables.Players[Variables.MyParty.members[i]].getHelmet()].getItemNum();
                        if (itemNum > 0) {
                            batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 100 + 5, 58 + 5 + ((i - 1) * 125), 24, 24, 0, 0, 24, 24, false, true);
                        }
                    }
                    batcher.draw(AssetLoader.eqBG, 39, 95 + ((i - 1) * 125), 36, 36);
                    batcher.draw(AssetLoader.eqBG, 76, 95 + ((i - 1) * 125), 36, 36);

                    // Player Info
                    drawName("Name: " + Variables.Players[Variables.MyParty.members[i]].getName(), 156, 21 + ((i - 1) * 125), Color.WHITE);
                    drawName("Lvl: " + Variables.Players[Variables.MyParty.members[i]].getLevel(), 156, 36 + ((i - 1) * 125), Color.WHITE);
                    drawName("HP: " + Variables.Players[Variables.MyParty.members[i]].getHP() + "/" + Variables.Players[Variables.MyParty.members[i]].getMaxHP(), 156, 51 + ((i - 1) * 125), Color.WHITE);
                    drawName("MP: " + Variables.Players[Variables.MyParty.members[i]].getMP() + "/" + Variables.Players[Variables.MyParty.members[i]].getMaxMP(), 156, 66 + ((i - 1) * 125), Color.WHITE);
                    drawName("EXP: " + Variables.Players[Variables.MyParty.members[i]].getEXP() + "/" + Variables.Players[Variables.MyParty.members[i]].getNextLVL(), 156, 81 + ((i - 1) * 125), Color.WHITE);

                    int pSTR = Variables.Players[Variables.MyParty.members[i]].getSTR();
                    int pDEF = Variables.Players[Variables.MyParty.members[i]].getDEF();
                    int pVIT = Variables.Players[Variables.MyParty.members[i]].getVIT();
                    int pAGI = Variables.Players[Variables.MyParty.members[i]].getAGI();
                    int pMAG = Variables.Players[Variables.MyParty.members[i]].getMAG();
                    if (Variables.Players[Variables.MyParty.members[i]].getWeapon() > 0) {
                        int itemNum = Variables.Players[Variables.MyParty.members[i]].inventory[Variables.Players[Variables.MyParty.members[i]].getWeapon()].getItemNum();
                        if (itemNum <= 0) {
                            return;
                        }
                        pSTR = pSTR + Variables.Items[itemNum].STR;
                        pDEF = pDEF + Variables.Items[itemNum].DEF;
                        pVIT = pVIT + Variables.Items[itemNum].VIT;
                        pAGI = pAGI + Variables.Items[itemNum].AGI;
                        pMAG = pMAG + Variables.Items[itemNum].MAG;
                    }
                    if (Variables.Players[Variables.MyParty.members[i]].getOffhand() > 0) {
                        int itemNum = Variables.Players[Variables.MyParty.members[i]].inventory[Variables.Players[Variables.MyParty.members[i]].getOffhand()].getItemNum();
                        if (itemNum <= 0) {
                            return;
                        }
                        pSTR = pSTR + Variables.Items[itemNum].STR;
                        pDEF = pDEF + Variables.Items[itemNum].DEF;
                        pVIT = pVIT + Variables.Items[itemNum].VIT;
                        pAGI = pAGI + Variables.Items[itemNum].AGI;
                        pMAG = pMAG + Variables.Items[itemNum].MAG;
                    }
                    if (Variables.Players[Variables.MyParty.members[i]].getArmor() > 0) {
                        int itemNum = Variables.Players[Variables.MyParty.members[i]].inventory[Variables.Players[Variables.MyParty.members[i]].getArmor()].getItemNum();
                        if (itemNum <= 0) {
                            return;
                        }
                        pSTR = pSTR + Variables.Items[itemNum].STR;
                        pDEF = pDEF + Variables.Items[itemNum].DEF;
                        pVIT = pVIT + Variables.Items[itemNum].VIT;
                        pAGI = pAGI + Variables.Items[itemNum].AGI;
                        pMAG = pMAG + Variables.Items[itemNum].MAG;
                    }
                    if (Variables.Players[Variables.MyParty.members[i]].getHelmet() > 0) {
                        int itemNum = Variables.Players[Variables.MyParty.members[i]].inventory[Variables.Players[Variables.MyParty.members[i]].getHelmet()].getItemNum();
                        if (itemNum <= 0) {
                            return;
                        }
                        pSTR = pSTR + Variables.Items[itemNum].STR;
                        pDEF = pDEF + Variables.Items[itemNum].DEF;
                        pVIT = pVIT + Variables.Items[itemNum].VIT;
                        pAGI = pAGI + Variables.Items[itemNum].AGI;
                        pMAG = pMAG + Variables.Items[itemNum].MAG;
                    }
                    if (Variables.Players[Variables.MyParty.members[i]].getAcc1() > 0) {
                        int itemNum = Variables.Players[Variables.MyParty.members[i]].inventory[Variables.Players[Variables.MyParty.members[i]].getAcc1()].getItemNum();
                        if (itemNum <= 0) {
                            return;
                        }
                        pSTR = pSTR + Variables.Items[itemNum].STR;
                        pDEF = pDEF + Variables.Items[itemNum].DEF;
                        pVIT = pVIT + Variables.Items[itemNum].VIT;
                        pAGI = pAGI + Variables.Items[itemNum].AGI;
                        pMAG = pMAG + Variables.Items[itemNum].MAG;
                    }
                    if (Variables.Players[Variables.MyParty.members[i]].getAcc2() > 0) {
                        int itemNum = Variables.Players[Variables.MyParty.members[i]].inventory[Variables.Players[Variables.MyParty.members[i]].getAcc2()].getItemNum();
                        if (itemNum <= 0) {
                            return;
                        }
                        pSTR = pSTR + Variables.Items[itemNum].STR;
                        pDEF = pDEF + Variables.Items[itemNum].DEF;
                        pVIT = pVIT + Variables.Items[itemNum].VIT;
                        pAGI = pAGI + Variables.Items[itemNum].AGI;
                        pMAG = pMAG + Variables.Items[itemNum].MAG;
                    }

                    if (pSTR > Variables.Players[Variables.MyParty.members[i]].getSTR()) {
                        drawName("STR: " + pSTR, 156, 96 + ((i - 1) * 125), Color.GREEN);
                    } else {
                        drawName("STR: " + pSTR, 156, 96 + ((i - 1) * 125), Color.WHITE);
                    }
                    if (pDEF > Variables.Players[Variables.MyParty.members[i]].getDEF()) {
                        drawName("DEF: " + pDEF, 156, 111 + ((i - 1) * 125), Color.GREEN);
                    } else {
                        drawName("DEF: " + pDEF, 156, 111 + ((i - 1) * 125), Color.WHITE);
                    }
                    if (pVIT > Variables.Players[Variables.MyParty.members[i]].getVIT()) {
                        drawName("VIT: " + pVIT, 156, 126 + ((i - 1) * 125), Color.GREEN);
                    } else {
                        drawName("VIT: " + pVIT, 156, 126 + ((i - 1) * 125), Color.WHITE);
                    }
                    if (pAGI > Variables.Players[Variables.MyParty.members[i]].getAGI()) {
                        drawName("AGI: " + pAGI, 226, 96 + ((i - 1) * 125), Color.GREEN);
                    } else {
                        drawName("AGI: " + pAGI, 226, 96 + ((i - 1) * 125), Color.WHITE);
                    }
                    if (pMAG > Variables.Players[Variables.MyParty.members[i]].getMAG()) {
                        drawName("MAG: " + pMAG, 226, 111 + ((i - 1) * 125), Color.GREEN);
                    } else {
                        drawName("MAG: " + pMAG, 226, 111 + ((i - 1) * 125), Color.WHITE);
                    }
                    drawName("Points: " + Variables.Players[Variables.MyParty.members[i]].getPoints(), 226, 126 + ((i - 1) * 125), Color.WHITE);
                }
            }
        } else {
            // Player Sprite
            batcher.draw(AssetLoader.spritesDown2[Variables.Players[Variables.MyIndex].getSprite()], 63, 59, 32, 36);
            // Player Equipment
            batcher.draw(AssetLoader.eqBG, 39, 21, 36, 36);
            if (Variables.Players[Variables.MyIndex].getWeapon() > 0) {
                int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getWeapon()].getItemNum();
                if (itemNum > 0) {
                    batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 39 + 5, 21 + 5, 24, 24, 0, 0, 24, 24, false, true);
                }
            }
            batcher.draw(AssetLoader.eqBG, 21, 58, 36, 36);
            if (Variables.Players[Variables.MyIndex].getArmor() > 0) {
                int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getArmor()].getItemNum();
                if (itemNum > 0) {
                    batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 21 + 5, 58 + 5, 24, 24, 0, 0, 24, 24, false, true);
                }
            }
            batcher.draw(AssetLoader.eqBG, 76, 21, 36, 36);
            if (Variables.Players[Variables.MyIndex].getOffhand() > 0) {
                int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getOffhand()].getItemNum();
                if (itemNum > 0) {
                    batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 76 + 5, 21 + 5, 24, 24, 0, 0, 24, 24, false, true);
                }
            }
            batcher.draw(AssetLoader.eqBG, 100, 58, 36, 36);
            if (Variables.Players[Variables.MyIndex].getHelmet() > 0) {
                int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getHelmet()].getItemNum();
                if (itemNum > 0) {
                    batcher.draw(AssetLoader.items[Variables.Items[itemNum].Icon], 100 + 5, 58 + 5, 24, 24, 0, 0, 24, 24, false, true);
                }
            }
            batcher.draw(AssetLoader.eqBG, 39, 95, 36, 36);
            batcher.draw(AssetLoader.eqBG, 76, 95, 36, 36);

            // Player Info
            drawName("Name: " + Variables.Players[Variables.MyIndex].getName(), 156, 21, Color.WHITE);
            drawName("Lvl: " + Variables.Players[Variables.MyIndex].getLevel(), 156, 36, Color.WHITE);
            drawName("HP: " + Variables.Players[Variables.MyIndex].getHP() + "/" + Variables.Players[Variables.MyIndex].getMaxHP(), 156, 51, Color.WHITE);
            drawName("MP: " + Variables.Players[Variables.MyIndex].getMP() + "/" + Variables.Players[Variables.MyIndex].getMaxMP(), 156, 66, Color.WHITE);
            drawName("EXP: " + Variables.Players[Variables.MyIndex].getEXP() + "/" + Variables.Players[Variables.MyIndex].getNextLVL(), 156, 81, Color.WHITE);

            int pSTR = Variables.Players[Variables.MyIndex].getSTR();
            int pDEF = Variables.Players[Variables.MyIndex].getDEF();
            int pVIT = Variables.Players[Variables.MyIndex].getVIT();
            int pAGI = Variables.Players[Variables.MyIndex].getAGI();
            int pMAG = Variables.Players[Variables.MyIndex].getMAG();
            if (Variables.Players[Variables.MyIndex].getWeapon() > 0) {
                int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getWeapon()].getItemNum();
                if (itemNum <= 0) {
                    return;
                }
                pSTR = pSTR + Variables.Items[itemNum].STR;
                pDEF = pDEF + Variables.Items[itemNum].DEF;
                pVIT = pVIT + Variables.Items[itemNum].VIT;
                pAGI = pAGI + Variables.Items[itemNum].AGI;
                pMAG = pMAG + Variables.Items[itemNum].MAG;
            }
            if (Variables.Players[Variables.MyIndex].getOffhand() > 0) {
                int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getOffhand()].getItemNum();
                if (itemNum <= 0) {
                    return;
                }
                pSTR = pSTR + Variables.Items[itemNum].STR;
                pDEF = pDEF + Variables.Items[itemNum].DEF;
                pVIT = pVIT + Variables.Items[itemNum].VIT;
                pAGI = pAGI + Variables.Items[itemNum].AGI;
                pMAG = pMAG + Variables.Items[itemNum].MAG;
            }
            if (Variables.Players[Variables.MyIndex].getArmor() > 0) {
                int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getArmor()].getItemNum();
                if (itemNum <= 0) {
                    return;
                }
                pSTR = pSTR + Variables.Items[itemNum].STR;
                pDEF = pDEF + Variables.Items[itemNum].DEF;
                pVIT = pVIT + Variables.Items[itemNum].VIT;
                pAGI = pAGI + Variables.Items[itemNum].AGI;
                pMAG = pMAG + Variables.Items[itemNum].MAG;
            }
            if (Variables.Players[Variables.MyIndex].getHelmet() > 0) {
                int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getHelmet()].getItemNum();
                if (itemNum <= 0) {
                    return;
                }
                pSTR = pSTR + Variables.Items[itemNum].STR;
                pDEF = pDEF + Variables.Items[itemNum].DEF;
                pVIT = pVIT + Variables.Items[itemNum].VIT;
                pAGI = pAGI + Variables.Items[itemNum].AGI;
                pMAG = pMAG + Variables.Items[itemNum].MAG;
            }
            if (Variables.Players[Variables.MyIndex].getAcc1() > 0) {
                int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getAcc1()].getItemNum();
                if (itemNum <= 0) {
                    return;
                }
                pSTR = pSTR + Variables.Items[itemNum].STR;
                pDEF = pDEF + Variables.Items[itemNum].DEF;
                pVIT = pVIT + Variables.Items[itemNum].VIT;
                pAGI = pAGI + Variables.Items[itemNum].AGI;
                pMAG = pMAG + Variables.Items[itemNum].MAG;
            }
            if (Variables.Players[Variables.MyIndex].getAcc2() > 0) {
                int itemNum = Variables.Players[Variables.MyIndex].inventory[Variables.Players[Variables.MyIndex].getAcc2()].getItemNum();
                if (itemNum <= 0) {
                    return;
                }
                pSTR = pSTR + Variables.Items[itemNum].STR;
                pDEF = pDEF + Variables.Items[itemNum].DEF;
                pVIT = pVIT + Variables.Items[itemNum].VIT;
                pAGI = pAGI + Variables.Items[itemNum].AGI;
                pMAG = pMAG + Variables.Items[itemNum].MAG;
            }

            if (pSTR > Variables.Players[Variables.MyIndex].getSTR()) {
                drawName("STR: " + pSTR, 156, 96, Color.GREEN);
            } else {
                drawName("STR: " + pSTR, 156, 96, Color.WHITE);
            }
            if (pDEF > Variables.Players[Variables.MyIndex].getDEF()) {
                drawName("DEF: " + pDEF, 156, 111, Color.GREEN);
            } else {
                drawName("DEF: " + pDEF, 156, 111, Color.WHITE);
            }
            if (pVIT > Variables.Players[Variables.MyIndex].getVIT()) {
                drawName("VIT: " + pVIT, 156, 126, Color.GREEN);
            } else {
                drawName("VIT: " + pVIT, 156, 126, Color.WHITE);
            }
            if (pAGI > Variables.Players[Variables.MyIndex].getAGI()) {
                drawName("AGI: " + pAGI, 226, 96, Color.GREEN);
            } else {
                drawName("AGI: " + pAGI, 226, 96, Color.WHITE);
            }
            if (pMAG > Variables.Players[Variables.MyIndex].getMAG()) {
                drawName("MAG: " + pMAG, 226, 111, Color.GREEN);
            } else {
                drawName("MAG: " + pMAG, 226, 111, Color.WHITE);
            }
            drawName("Points: " + Variables.Players[Variables.MyIndex].getPoints(), 226, 126, Color.WHITE);
        }

        batcher.draw(AssetLoader.sepBarV, 320, 13, 9, 454);

        if (Variables.menuIndex == 1) {
            drawText("Items", 365, 31, Color.YELLOW);
        } else {
            drawText("Items", 365, 31, Color.WHITE);
        }
        if (Variables.menuIndex == 2) {
            drawText("Spells", 364, 71, Color.YELLOW);
        } else {
            drawText("Spells", 364, 71, Color.WHITE);
        }
        if (Variables.menuIndex == 3) {
            drawText("Status", 358, 111, Color.YELLOW);
        } else {
            drawText("Status", 358, 111, Color.WHITE);
        }
        int goldTotal = 0;
        for (int i = 1; i <= 60; i++) {
            if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() == 1) {
                goldTotal += Variables.Players[Variables.MyIndex].inventory[i].getItemVal();
            }
        }

        layout.setText(AssetLoader.nameFont, "Gold: " + goldTotal);
        float width = layout.width;// contains the width of the current set text

        float nameX = 460 - (int)width;
        float nameY = 447;
        drawName("Gold: " + goldTotal, nameX, nameY, Color.YELLOW);

        batcher.draw(AssetLoader.sepBarH, 320, 370, 147, 9);

        // Back Button
        drawText("Back", 24, 437, Color.WHITE);
    }
    public static void drawUI() {
        // Empty Bar
        batcher.draw(AssetLoader.emptyBar, 86, 0, 96, 12, 0, 0, 96, 12, false, true);
        batcher.draw(AssetLoader.emptyBar, 192, 0, 96, 12, 0, 0, 96, 12, false, true);
        batcher.draw(AssetLoader.emptyBar, 298, 0, 96, 12, 0, 0, 96, 12, false, true);
        // HP Bar
        if (Variables.Players[Variables.MyIndex].getHP() > Variables.Players[Variables.MyIndex].getMaxHP()) { Variables.Players[Variables.MyIndex].setHP(Variables.Players[Variables.MyIndex].getMaxHP()); }
        double maxWidth = ((((double) Variables.Players[Variables.MyIndex].getHP()) / 96) / ((double) Variables.Players[Variables.MyIndex].getMaxHP() / 96) * 96);
        batcher.draw(AssetLoader.hpBar, 86, 0, (int)maxWidth, 12, 0, 0, (int)maxWidth, 12, false, true);
        drawName("HP", 87, 1, Color.WHITE);
        // MP Bar
        if (Variables.Players[Variables.MyIndex].getMP() > Variables.Players[Variables.MyIndex].getMaxMP()) { Variables.Players[Variables.MyIndex].setMP(Variables.Players[Variables.MyIndex].getMaxMP()); }
        maxWidth = ((((double) Variables.Players[Variables.MyIndex].getMP()) / 96) / ((double) Variables.Players[Variables.MyIndex].getMaxMP() / 96) * 96);
        batcher.draw(AssetLoader.mpBar, 192, 0, (int)maxWidth, 12, 0, 0, (int)maxWidth, 12, false, true);
        drawName("MP", 193, 1, Color.WHITE);
        // MP Bar
        maxWidth = ((((double) Variables.Players[Variables.MyIndex].getEXP()) / 96) / ((double) Variables.Players[Variables.MyIndex].getNextLVL() / 96) * 96);
        batcher.draw(AssetLoader.xpBar, 298, 0, (int)maxWidth, 12, 0, 0, (int)maxWidth, 12, false, true);
        drawName("XP", 299, 1, Color.WHITE);
        // Chat Bar
        //batcher.draw(AssetLoader.chatBar, 16, 472, 448, 24, 0, 0, 448, 24, false, true);
        if (!Variables.inChat) {
            if (Variables.chatMessageIndex > 0) {
                if (Variables.chatMessageIndex < 5) {
                    for (int i = 1; i <= Variables.chatMessageIndex; i++) {
                        int type = Variables.chatMessages[i].getType();
                        if (type == Variables.MESSAGE_TYPE_MAP) {
                            drawName(Variables.chatMessages[i].getMsg(), 20, 462 + (14 * i), Color.WHITE);
                        } else if (type == Variables.MESSAGE_TYPE_GLOBAL) {
                            drawName(Variables.chatMessages[i].getMsg(), 20, 462 + (14 * i), Color.ORANGE);
                        } else if (type == Variables.MESSAGE_TYPE_WHISPER) {
                            drawName(Variables.chatMessages[i].getMsg(), 20, 462 + (14 * i), Color.PINK);
                        }
                    }
                } else if (Variables.chatMessageIndex > 4) {
                    for (int i = Variables.chatMessageIndex - 4; i <= Variables.chatMessageIndex; i++) {
                        int type = Variables.chatMessages[i].getType();
                        if (type == Variables.MESSAGE_TYPE_MAP) {
                            drawName(Variables.chatMessages[i].getMsg(), 20, 462 + (14 * i), Color.WHITE);
                        } else if (type == Variables.MESSAGE_TYPE_GLOBAL) {
                            drawName(Variables.chatMessages[i].getMsg(), 20, 462 + (14 * i), Color.ORANGE);
                        } else if (type == Variables.MESSAGE_TYPE_WHISPER) {
                            drawName(Variables.chatMessages[i].getMsg(), 20, 462 + (14 * i), Color.PINK);
                        }
                    }
                }
            }
        }
        if (Variables.target > 0) {
            if (Variables.target != Variables.MyIndex) {
                batcher.draw(AssetLoader.playerMenu, 136, 91, 200, 300, 0, 0, 200, 300, false, true);
            }
        }
        // Hot Keys
        if (Variables.Players[Variables.MyIndex].getHotKeyQ() > 0) {
            for (int i = 1; i <= 60; i++) {
                if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() == Variables.Players[Variables.MyIndex].getHotKeyQ()) {
                    if (Variables.Players[Variables.MyIndex].spells[i].getCoolDown() > 0) {
                        if (Variables.Spells[Variables.Players[Variables.MyIndex].getHotKeyQ()] != null) {
                            batcher.setColor(Color.RED);
                            batcher.draw(AssetLoader.icons[Variables.Spells[Variables.Players[Variables.MyIndex].getHotKeyQ()].Icon], 290, 595, 48, 48, 0, 0, 56, 56, false, true);
                            batcher.setColor(Color.WHITE);
                            long timer = (Variables.Players[Variables.MyIndex].spells[i].getCoolDown() - Variables.Players[Variables.MyIndex].spells[i].getCoolDownTimer()) + 1;
                            drawName(timer + "", 282, 617, Color.WHITE);
                        }
                    } else {
                        if (Variables.Spells[Variables.Players[Variables.MyIndex].getHotKeyQ()] != null) {
                            batcher.draw(AssetLoader.icons[Variables.Spells[Variables.Players[Variables.MyIndex].getHotKeyQ()].Icon], 290, 595, 48, 48, 0, 0, 56, 56, false, true);
                        }
                    }
                }
            }
        }

        if (Variables.Players[Variables.MyIndex].getHotKeyE() > 0) {
            for (int i = 1; i <= 60; i++) {
                if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() == Variables.Players[Variables.MyIndex].getHotKeyE()) {
                    if (Variables.Players[Variables.MyIndex].spells[i].getCoolDown() > 0) {
                        if (Variables.Spells[Variables.Players[Variables.MyIndex].getHotKeyE()] != null) {
                            batcher.setColor(Color.RED);
                            batcher.draw(AssetLoader.icons[Variables.Spells[Variables.Players[Variables.MyIndex].getHotKeyE()].Icon], 378, 684, 48, 48, 0, 0, 56, 56, false, true);
                            batcher.setColor(Color.WHITE);
                            long timer = (Variables.Players[Variables.MyIndex].spells[i].getCoolDown() - Variables.Players[Variables.MyIndex].spells[i].getCoolDownTimer()) + 1;
                            drawName(timer + "", 370, 706, Color.WHITE);
                        }
                    } else {
                        if (Variables.Spells[Variables.Players[Variables.MyIndex].getHotKeyE()] != null) {
                            batcher.draw(AssetLoader.icons[Variables.Spells[Variables.Players[Variables.MyIndex].getHotKeyE()].Icon], 378, 684, 48, 48, 0, 0, 56, 56, false, true);
                        }
                    }
                }
            }
        }

        if (Variables.PartyInvite) {
            batcher.draw(AssetLoader.partyInvite, 136, 191, 200, 100, 0, 0, 200, 100, false, true);
            drawName(Variables.Players[Variables.PartyLeader].getName(), 213, 240, Color.GREEN);
        }
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
                if (Variables.Players[Variables.MyIndex].getTarget() == i) {
                    if (Variables.Players[Variables.MyIndex].getTargetType() == Variables.SEARCH_TYPE_NPC) {
                        layout.setText(AssetLoader.nameFont, ">" + Variables.MapNPCs[i].getName() + "<");
                    } else {
                        layout.setText(AssetLoader.nameFont, Variables.MapNPCs[i].getName());
                    }
                } else {
                    layout.setText(AssetLoader.nameFont, Variables.MapNPCs[i].getName());
                }
                float width = layout.width;// contains the width of the current set text

                float nameX = PlayerX - ((int)width / 2) + 32;
                float nameY = PlayerY - 24;
                if (Variables.MapNPCs[i].getHP() > 0) {
                    if (Variables.MapNPCs[i].getName() != null) {
                        if (Variables.Players[Variables.MyIndex].getTarget() == i) {
                            if (Variables.Players[Variables.MyIndex].getTargetType() == Variables.SEARCH_TYPE_NPC) {
                                drawName(">" + Variables.MapNPCs[i].getName() + "<", nameX, nameY, Color.YELLOW);
                            } else {
                                drawName(Variables.MapNPCs[i].getName(), nameX, nameY, Color.WHITE);
                            }
                        } else {
                            drawName(Variables.MapNPCs[i].getName(), nameX, nameY, Color.WHITE);
                        }
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
        // Render Player Names
        for (int i = 1; i <= Variables.MaxPlayers; i++) {
            if (Variables.Players[i] != null) {
                if (Variables.Players[i].getMap() == Variables.Players[Variables.MyIndex].getMap()) {
                    float PlayerX = ((Variables.Players[i].getX() * Variables.MoveSize) + Variables.Players[i].getOffsetX());
                    float PlayerY = ((Variables.Players[i].getY() * Variables.MoveSize) + Variables.Players[i].getOffsetY());

                    if (Variables.Players[i].getName() == null) { return; }
                    layout.setText(AssetLoader.nameFont, Variables.Players[i].getName());
                    float width = layout.width;// contains the width of the current set text

                    float nameX = PlayerX - ((int)width / 2) + 32;
                    float nameY = PlayerY - 8;
                    if (Variables.Players[i].getParty() > 0) {
                        if (Variables.Players[i].getParty() == Variables.Players[Variables.MyIndex].getParty()) {
                            drawName(Variables.Players[i].getName(), nameX, nameY, toRGB(0, 162, 232));
                        } else {
                            drawName(Variables.Players[i].getName(), nameX, nameY, Color.WHITE);
                        }
                    } else {
                        drawName(Variables.Players[i].getName(), nameX, nameY, Color.WHITE);
                    }

                    if (i == Variables.MyIndex) {
                        GameRenderer.batcher.draw(AssetLoader.emptyMapBar, PlayerX + 16, PlayerY + 48, 32, 4);

                        double maxWidth = ((((double) Variables.Players[Variables.MyIndex].getHP()) / 32) / ((double) Variables.Players[Variables.MyIndex].getMaxHP() / 32) * 32);
                        //batcher.draw(AssetLoader.hpBar, 540, 158, (int) maxWidth, 12, 0, 0, (int) maxWidth, 12, false, true);
                        GameRenderer.batcher.draw(AssetLoader.hpMapBar, PlayerX + 16, PlayerY + 48, (int) maxWidth, 4);

                        // Casting bar for when casting spells
                        for (int a = 1; a <= 60; a++) {
                            if (Variables.Players[Variables.MyIndex].spells[a].getSpellNum() > 0) {
                                if (Variables.Players[Variables.MyIndex].spells[a].getCastTime() > 0) {
                                    GameRenderer.batcher.draw(AssetLoader.emptyMapBar, PlayerX + 16, PlayerY + 52, 32, 4);
                                    maxWidth = ((((double) Variables.Players[Variables.MyIndex].spells[a].getCastTimeTimer()) / 32) / ((double) Variables.Players[Variables.MyIndex].spells[a].getCastTime() / 32) * 32);

                                    GameRenderer.batcher.draw(AssetLoader.ctMapBar, PlayerX + 16, PlayerY + 52, (int) maxWidth, 4);
                                    break;
                                }
                            }
                        }
                    }

                    // Render Party Stuff
                    if (Variables.Players[Variables.MyIndex].getParty() > 0) {
                        if (Variables.Players[i].getParty() > 0) {
                            if (Variables.Players[i].getParty() == Variables.Players[Variables.MyIndex].getParty()) {
                                for (int a = 1; a <= 3; a++) {
                                    if (Variables.MyParty.members[a] > 0) {
                                        if (Variables.Players[Variables.MyParty.members[a]].getMap() == Variables.Players[Variables.MyIndex].getMap()) {
                                            PlayerX = ((Variables.Players[Variables.MyParty.members[a]].getX() * Variables.MoveSize) + Variables.Players[Variables.MyParty.members[a]].getOffsetX());
                                            PlayerY = ((Variables.Players[Variables.MyParty.members[a]].getY() * Variables.MoveSize) + Variables.Players[Variables.MyParty.members[a]].getOffsetY());
                                            GameRenderer.batcher.draw(AssetLoader.emptyMapBar, PlayerX + 16, PlayerY + 48, 32, 4);

                                            double maxWidth = ((((double) Variables.Players[Variables.MyParty.members[a]].getHP() / 32) / ((double) Variables.Players[Variables.MyParty.members[a]].getMaxHP() / 32) * 32));
                                            GameRenderer.batcher.draw(AssetLoader.hpMapBar, PlayerX + 16, PlayerY + 48, (int) maxWidth, 4);

                                            if (Variables.MyParty.members[a] == Variables.MyParty.leader) {
                                                layout.setText(AssetLoader.nameFont, Variables.Players[Variables.MyParty.leader].getName());
                                                width = layout.width;// contains the width of the current set text
                                                PlayerX = ((Variables.Players[Variables.MyParty.leader].getX() * Variables.MoveSize) + Variables.Players[Variables.MyParty.leader].getOffsetX());
                                                PlayerY = ((Variables.Players[Variables.MyParty.leader].getY() * Variables.MoveSize) + Variables.Players[Variables.MyParty.leader].getOffsetY());
                                                nameX = PlayerX - ((int) width / 2) + 24;
                                                nameY = PlayerY - 16;
                                                batcher.draw(AssetLoader.crown, nameX - 3, nameY - 2, 16, 16, 0, 0, 16, 16, false, true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

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

                    for (int a = 1; a <= 20; a++) {
                        if (Variables.DrawSystemMessage[a].getTimer() > 0) {
                            drawName(Variables.DrawSystemMessage[a].getMsg(), Variables.DrawSystemMessage[a].getX(), Variables.DrawSystemMessage[a].getY(), Variables.DrawSystemMessage[a].getColor());
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
                        int MovementSpeed = 2;

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
                        RenderAndroid.renderNPC(i, NPCX, NPCY, tickCount);
                    }
                }
            }
            for (int i = 1; i <= Variables.MaxPlayers; i++) {
                if (Variables.Players[i] != null) {
                    int mapNum = Variables.Players[i].getMap();
                    if (Variables.Players[i].getMap() == Variables.Players[Variables.MyIndex].getMap()) {
                        if (Variables.Players[i].getY() == LoopY) {

                            int MovementSpeed = 4;

                            switch (Variables.Players[i].getDir()) {
                                case Variables.DIR_UP:
                                    Variables.Players[i].setOffsetY(Variables.Players[i].getOffsetY() - MovementSpeed);
                                    if (Variables.Players[i].getOffsetY() < 0) {
                                        Variables.Players[i].setOffsetY(0);
                                        Variables.Players[i].setMoving(0);
                                    }
                                    break;
                                case Variables.DIR_DOWN:
                                    Variables.Players[i].setOffsetY(Variables.Players[i].getOffsetY() + MovementSpeed);
                                    if (Variables.Players[i].getOffsetY() > 0) {
                                        Variables.Players[i].setOffsetY(0);
                                        Variables.Players[i].setMoving(0);
                                    }
                                    break;
                                case Variables.DIR_LEFT:
                                    Variables.Players[i].setOffsetX(Variables.Players[i].getOffsetX() - MovementSpeed);
                                    if (Variables.Players[i].getOffsetX() < 0) {
                                        Variables.Players[i].setOffsetX(0);
                                        Variables.Players[i].setMoving(0);
                                    }
                                    break;
                                case Variables.DIR_RIGHT:
                                    Variables.Players[i].setOffsetX(Variables.Players[i].getOffsetX() + MovementSpeed);
                                    if (Variables.Players[i].getOffsetX() > 0) {
                                        Variables.Players[i].setOffsetX(0);
                                        Variables.Players[i].setMoving(0);
                                    }
                                    break;
                            }
                            float PlayerX = ((Variables.Players[i].getX() * Variables.MoveSize) + Variables.Players[i].getOffsetX());
                            float PlayerY = ((Variables.Players[i].getY() * Variables.MoveSize) + Variables.Players[i].getOffsetY());
                            renderPlayer(i, PlayerX, PlayerY, tickCount);
                        }
                    }
                }
            }
            for (int i = 1; i <= Variables.MaxMapSpells; i++) {
                int spellNum = Variables.MapSpells[i].getSpellNum();
                if (spellNum > 0) {
                    if (Variables.MapSpells[i].getY() == LoopY) {
                        int index = Variables.MapSpells[i].getIndex();
                        int anim = Variables.Spells[spellNum].Animation;
                        int x = Variables.MapSpells[i].getX();
                        int y = Variables.MapSpells[i].getY();

                        if (Variables.MapSpells[i].getType() == Variables.SEARCH_TYPE_NPC) {
                            x = ((Variables.MapNPCs[index].getX() * Variables.MoveSize) + Variables.MapNPCs[index].getOffsetX());
                            y = ((Variables.MapNPCs[index].getY() * Variables.MoveSize) + Variables.MapNPCs[index].getOffsetY());
                        } else if (Variables.MapSpells[i].getType() == Variables.SEARCH_TYPE_PLAYER) {
                            x = ((Variables.Players[index].getX() * Variables.MoveSize) + Variables.Players[index].getOffsetX());
                            y = ((Variables.Players[index].getY() * Variables.MoveSize) + Variables.Players[index].getOffsetY());
                        }

                        GameRenderer.batcher.draw(AssetLoader.anims[anim], x + 16, y + 11, 32, 32, Variables.MapSpells[i].getFrame() * 32, 0, 32, 32, false, true);
                    }
                }
            }
        }
    }
    public static void drawItems() {
        for (int i = 1; i <= Variables.MaxMapItems; i++) {
            if (Variables.MapItems[i] != null) {
                if (Variables.MapItems[i].itemNum > 0) {
                    float itemX = (Variables.MapItems[i].x * Variables.MoveSize) + 24;
                    float itemY = (Variables.MapItems[i].y * Variables.MoveSize) + 24;
                    batcher.draw(AssetLoader.items[Variables.Items[Variables.MapItems[i].itemNum].Icon], itemX, itemY, 24, 24, 0, 0, 24, 24, false, true);
                }
            }
        }
    }
    public static void drawChat() {
        batcher.draw(AssetLoader.menuBG, 16, 16, 448, 448, 0, 0, 448, 448, false, true);
        batcher.draw(AssetLoader.chatBar, 16, 403, 448, 24, 0, 0, 448, 24, false, true);

        if (Variables.chatInput != null && !Variables.chatInput.isEmpty()) {
            drawName(Variables.chatInput, 24, 409, Color.WHITE);
        }

        if (Variables.chatMessageIndex < 21) {
            for (int i = 1; i <= 100; i++) {
                if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_MAP) {
                    drawName(Variables.chatMessages[i].getMsg(), 24, (18 * i) + 10, Color.WHITE);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_GLOBAL) {
                    drawName(Variables.chatMessages[i].getMsg(), 24, (18 * i) + 10, Color.ORANGE);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_WHISPER) {
                    drawName(Variables.chatMessages[i].getMsg(), 24, (18 * i) + 10, Color.PINK);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_PARTY) {
                    drawName(Variables.chatMessages[i].getMsg(), 24, (18 * i) + 10, Color.SKY);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_SYSTEM) {
                    drawName(Variables.chatMessages[i].getMsg(), 24, (18 * i) + 10, Color.GOLD);
                }
            }
        } else {
            int line = 1;
            for (int i = Variables.chatMessageIndex - 20; i <= Variables.chatMessageIndex; i++) {
                if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_MAP) {
                    drawName(Variables.chatMessages[i].getMsg(), 24, (18 * line) + 10, Color.WHITE);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_GLOBAL) {
                    drawName(Variables.chatMessages[i].getMsg(), 24, (18 * line) + 10, Color.ORANGE);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_WHISPER) {
                    drawName(Variables.chatMessages[i].getMsg(), 24, (18 * line) + 10, Color.PINK);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_PARTY) {
                    drawName(Variables.chatMessages[i].getMsg(), 24, (18 * line) + 10, Color.SKY);
                } else if (Variables.chatMessages[i].getType() == Variables.MESSAGE_TYPE_SYSTEM) {
                    drawName(Variables.chatMessages[i].getMsg(), 24, (18 * line) + 10, Color.GOLD);
                }
                line++;
            }
        }

        // Back Button
        drawText("Back", 24, 437, Color.WHITE);
        // Send Msg Button
        drawText("Send", 400, 437, Color.WHITE);
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
                for (int i = 1; i <= Variables.MaxPlayers; i++) {
                    int PlayerX = ((Variables.Players[i].getX() * Variables.MoveSize) + Variables.Players[i].getOffsetX());
                    int PlayerY = ((Variables.Players[i].getY() * Variables.MoveSize) + Variables.Players[i].getOffsetY());
                    PlayerX = PlayerX / Variables.MoveSize;
                    PlayerY = PlayerY / Variables.MoveSize;
                    if (Variables.CurX == PlayerX && Variables.CurY == PlayerY) {
                        //if (lastClicked == 1) {
                        SendClientData.SendSearch(PlayerX, PlayerY, Variables.SEARCH_TYPE_PLAYER, i);
                        Variables.CurX = 0;
                        Variables.CurY = 0;
                        break;
                        //}
                    }
                }
            }
        }
    }
    public static Color toRGB(int r, int g, int b) {
        float RED = r / 255.0f;
        float GREEN = g / 255.0f;
        float BLUE = b / 255.0f;
        return new Color(RED, GREEN, BLUE, 1);
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
            if (Variables.inputTimer > 0) {
                Variables.inputTimer--;
            }
            LastUpdateTime_BuyMsg = tickCount + UpdateTime_BuyMsg;
        }
    }
    public static void longPressTimer(long tickCount) {
        if (LastUpdateTime_LongPress < tickCount) {
            if (Variables.touchDown) {
                if (Variables.longPressTimer < 1) {
                    if (Variables.longPressTimer == 1) {
                        // Long Press
                        //SendClientData.SendDropItem(Variables.selectedInvSlot);
                        Variables.longPressTimer = 0;
                    }
                    Variables.longPressTimer++;
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
            for (int i = 1; i <= 20; i++) {
                if (Variables.DrawSystemMessage[i].getTimer() > 0) {
                    Variables.DrawSystemMessage[i].setY(Variables.DrawSystemMessage[i].getY() - 2);
                    Variables.DrawSystemMessage[i].setTimer(Variables.DrawSystemMessage[i].getTimer() - 1);
                    if (Variables.DrawSystemMessage[i].getTimer() <= 0) {
                        Variables.DrawSystemMessage[i] = new SystemMsg_Struct();
                    }
                }
            }
            LastUpdateTime_Damage = tickCount + UpdateTime_Damage;
        }
    }
    public static void processMovement(int index) {
        if (Variables.Players[index].getMoving() > 0) {
            if (Variables.Players[index].getOffsetX() == 0 && Variables.Players[index].getOffsetY() == 0) {
                Variables.Players[index].setMoving(0);
                if (Variables.Players[index].getStep() == 0) {
                    Variables.Players[index].setStep(1);
                    Variables.Players[index].setLastStep(0);
                } else if (Variables.Players[index].getStep() == 1) {
                    if (Variables.Players[index].getLastStep() == 0) {
                        Variables.Players[index].setStep(2);
                    } else if (Variables.Players[index].getLastStep() == 2) {
                        Variables.Players[index].setStep(0);
                    }
                } else if (Variables.Players[index].getStep() == 2) {
                    Variables.Players[index].setStep(1);
                    Variables.Players[index].setLastStep(2);
                }
            }
        } else {
            Variables.Players[index].setStep(1);
            Variables.Players[index].setLastStep(0);
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
}
