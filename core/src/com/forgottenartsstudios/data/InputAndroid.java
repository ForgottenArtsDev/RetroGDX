package com.forgottenartsstudios.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.forgottenartsstudios.gameworld.GameRenderer;
import com.forgottenartsstudios.helpers.AssetLoader;
import com.forgottenartsstudios.helpers.Variables;

/**
 * Created by forgo on 10/6/2017.
 */

public class InputAndroid {
    public static void inputTileScreen() {
        Variables.Game_State = Variables.Game_State_Loading;
    }
    public static void inputLogin(float screenX, float screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));

        // ID TEXT FIELD
        if (worldCoordinates.x >= 146 && worldCoordinates.x <= 439) {
            if (worldCoordinates.y >= 321 && worldCoordinates.y <= 346) {
                Gdx.input.getTextInput(new Input.TextInputListener() {

                    @Override
                    public void input(String text) {
                        Variables.Login_ID = text;
                    }

                    @Override
                    public void canceled() {
                    }
                }, "ID:", Variables.Login_ID, "");
            }
        }
        // PW TEXT FIELD
        if (worldCoordinates.x >= 146 && worldCoordinates.x <= 439) {
            if (worldCoordinates.y >= 379 && worldCoordinates.y <= 404) {
                Gdx.input.getTextInput(new Input.TextInputListener() {

                    @Override
                    public void input(String text) {
                        Variables.Login_PW = text;
                    }

                    @Override
                    public void canceled() {
                    }
                }, "PW:", Variables.Login_PW, "");
            }
        }
        // SAVE LOGIN
        if (worldCoordinates.x >= 146 && worldCoordinates.x <= 171) {
            if (worldCoordinates.y >= 435 && worldCoordinates.y <= 460) {
                if (!Variables.saveLogin) {
                    Variables.saveLogin = true;
                } else {
                    Variables.saveLogin = false;
                }
            }
        }
        // LOGIN BUTTON
        if (worldCoordinates.x >= 162 && worldCoordinates.x <= 324) {
            if (worldCoordinates.y >= 540 && worldCoordinates.y <= 584) {
                if (!Variables.InvalidBuildVersion) {
                    if (Variables.Login_ID != null && !Variables.Login_ID.isEmpty()) {
                        Variables.IDReq = false;
                        if (Variables.Login_PW != null && !Variables.Login_PW.isEmpty()) {
                            Variables.PasswordReq = false;
                            Variables.IDReq = false;
                            if (Variables.saveLogin) {
                                AssetLoader.saveLogin();
                            }
                            SendClientData.SendLogin(Variables.Login_ID, Variables.Login_PW);
                        } else {
                            Variables.PasswordReq = true;
                        }
                    } else {
                        Variables.IDReq = true;
                    }
                }
            }
        }
        // REGISTER BUTTON
        if (worldCoordinates.x >= 114 && worldCoordinates.x <= 380) {
            if (worldCoordinates.y >= 662 && worldCoordinates.y <= 705) {
                if (!Variables.InvalidBuildVersion) {
                    if (Variables.Login_ID != null && !Variables.Login_ID.isEmpty()) {
                        Variables.IDReq = false;
                        if (Variables.Login_PW != null && !Variables.Login_PW.isEmpty()) {
                            if (Variables.saveLogin) {
                                AssetLoader.saveLogin();
                            }
                            SendClientData.SendNewAccount(Variables.Login_ID, Variables.Login_PW);
                            Variables.PasswordReq = false;
                            Variables.IDReq = false;
                        } else {
                            Variables.PasswordReq = true;
                        }
                    } else {
                        Variables.IDReq = true;
                    }
                }
            }
        }
    }
    public static void inputCharSelect(float screenX, float screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));
        // Char 1
        if (worldCoordinates.x >= 8 && worldCoordinates.x <= 471) {
            if (worldCoordinates.y >= 304 && worldCoordinates.y <= 457) {
                Player char1 = Variables.MyAccount.chars[1];
                if (char1.getName() != null && !char1.getName().isEmpty()) {
                    SendClientData.SendChooseChar(1);
                } else {
                    Variables.CharIndex = 1;
                    Variables.TempJob = Variables.JOB_WARRIOR;
                    Variables.TempSprite = 1;
                    Variables.Game_State = Variables.Game_State_CharCreate;
                }
            }
        }
        // Char 2
        if (worldCoordinates.x >= 8 && worldCoordinates.x <= 471) {
            if (worldCoordinates.y >= 469 && worldCoordinates.y <= 622) {
                Player char2 = Variables.MyAccount.chars[2];
                if (char2.getName() != null && !char2.getName().isEmpty()) {
                    SendClientData.SendChooseChar(2);
                } else {
                    Variables.CharIndex = 2;
                    Variables.TempJob = Variables.JOB_WARRIOR;
                    Variables.TempSprite = 1;
                    Variables.Game_State = Variables.Game_State_CharCreate;
                }
            }
        }
        // Char 3
        if (worldCoordinates.x >= 8 && worldCoordinates.x <= 471) {
            if (worldCoordinates.y >= 634 && worldCoordinates.y <= 787) {
                Player char3 = Variables.MyAccount.chars[3];
                if (char3.getName() != null && !char3.getName().isEmpty()) {
                    SendClientData.SendChooseChar(3);
                } else {
                    Variables.CharIndex = 3;
                    Variables.TempJob = Variables.JOB_WARRIOR;
                    Variables.TempSprite = 1;
                    Variables.Game_State = Variables.Game_State_CharCreate;
                }
            }
        }
    }
    public static void inputCharCreate(float screenX, float screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));
        // NAME
        if (worldCoordinates.x >= 154 && worldCoordinates.x <= 445) {
            if (worldCoordinates.y >= 254 && worldCoordinates.y <= 279) {
                Gdx.input.getTextInput(new Input.TextInputListener() {

                    @Override
                    public void input(String text) { Variables.TempName = text; }

                    @Override
                    public void canceled() {
                    }
                }, "Name:", "", "");
            }
        }
        // WARRIOR
        if (worldCoordinates.x >= 11 && worldCoordinates.x <= 101) {
            if (worldCoordinates.y >= 327 && worldCoordinates.y <= 417) {
                Variables.TempJob = Variables.JOB_WARRIOR;
                Variables.TempSprite = 1;
            }
        }
        // WIZARD
        if (worldCoordinates.x >= 103 && worldCoordinates.x <= 193) {
            if (worldCoordinates.y >= 327 && worldCoordinates.y <= 417) {
                Variables.TempJob = Variables.JOB_WIZARD;
                Variables.TempSprite = 3;
            }
        }
        // CLERIC
        if (worldCoordinates.x >= 195 && worldCoordinates.x <= 285) {
            if (worldCoordinates.y >= 327 && worldCoordinates.y <= 417) {
                Variables.TempJob = Variables.JOB_CLERIC;
                Variables.TempSprite = 5;
            }
        }
        // RANGER
        if (worldCoordinates.x >= 287 && worldCoordinates.x <= 377) {
            if (worldCoordinates.y >= 327 && worldCoordinates.y <= 417) {
                Variables.TempJob = Variables.JOB_RANGER;
                Variables.TempSprite = 7;
            }
        }
        // ROGUE
        if (worldCoordinates.x >= 379 && worldCoordinates.x <= 469) {
            if (worldCoordinates.y >= 327 && worldCoordinates.y <= 417) {
                Variables.TempJob = Variables.JOB_ROGUE;
                Variables.TempSprite = 9;
            }
        }
        // MALE
        if (worldCoordinates.x >= 154 && worldCoordinates.x <= 214) {
            if (worldCoordinates.y >= 498 && worldCoordinates.y <= 558) {
                switch (Variables.TempSprite) {
                    case 2:
                        Variables.TempSprite = 1;
                        break;
                    case 4:
                        Variables.TempSprite = 3;
                        break;
                    case 6:
                        Variables.TempSprite = 5;
                        break;
                    case 8:
                        Variables.TempSprite = 7;
                        break;
                    case 10:
                        Variables.TempSprite = 9;
                        break;
                }
            }
        }
        // FEMALE
        if (worldCoordinates.x >= 266 && worldCoordinates.x <= 326) {
            if (worldCoordinates.y >= 498 && worldCoordinates.y <= 558) {
                switch (Variables.TempSprite) {
                    case 1:
                        Variables.TempSprite = 2;
                        break;
                    case 3:
                        Variables.TempSprite = 4;
                        break;
                    case 5:
                        Variables.TempSprite = 6;
                        break;
                    case 7:
                        Variables.TempSprite = 8;
                        break;
                    case 9:
                        Variables.TempSprite = 10;
                        break;
                }
            }
        }
        // CREATE CHAR
        if (worldCoordinates.x >= 138 && worldCoordinates.x <= 348) {
            if (worldCoordinates.y >= 751 && worldCoordinates.y <= 795) {
                SendClientData.SendCreateChar(Variables.MyIndex);
            }
        }
    }
    public static void inputInGame(float screenX, float screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));
        Variables.CurX = ((int)worldCoordinates.x) / Variables.MoveSize;
        Variables.CurY = ((int)worldCoordinates.y) / Variables.MoveSize;
        //if (!Variables.reloadingMap) {
            // UP
            if (worldCoordinates.x >= 77 && worldCoordinates.x <= 145) {
                if (worldCoordinates.y >= 569 && worldCoordinates.y <= 628) {
                    if (!Variables.dPad_Up) {
                        Variables.dPad_Up = true;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;
                    }
                }
            }
            // DOWN
            if (worldCoordinates.x >= 77 && worldCoordinates.x <= 145) {
                if (worldCoordinates.y >= 698 && worldCoordinates.y <= 756) {
                    if (!Variables.dPad_Down) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = true;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;
                    }
                }
            }
            // LEFT
            if (worldCoordinates.x >= 17 && worldCoordinates.x <= 76) {
                if (worldCoordinates.y >= 630 && worldCoordinates.y <= 697) {
                    if (!Variables.dPad_Left) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = true;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;
                    }
                }
            }
            // RIGHT
            if (worldCoordinates.x >= 147 && worldCoordinates.x <= 204) {
                if (worldCoordinates.y >= 630 && worldCoordinates.y <= 697) {
                    if (!Variables.dPad_Right) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = true;

                        Variables.aBtn = false;
                        Variables.bBtn = false;
                    }
                }
            }
            // MENU
            if (worldCoordinates.x >= 189 && worldCoordinates.x <= 252) {
                if (worldCoordinates.y >= 749 && worldCoordinates.y <= 772) {
                    if (!Variables.touchDown) {
                        Variables.touchDown = true;
                        if (Variables.inInventory) {
                            Variables.inInventory = false;
                            Variables.inMenu = true;
                            Variables.menuIndex = 1;
                            Variables.selectedInvSlot = 0;
                        }
                        if (Variables.inSpells) {
                            Variables.inSpells = false;
                            Variables.inMenu = true;
                            Variables.menuIndex = 2;
                            Variables.selectedSpellSlot = 0;
                        }
                        if (Variables.inMenu) {
                            Variables.inMenu = false;
                        } else if (!Variables.inInventory && !Variables.inShop && !Variables.inStatus && !Variables.inChat && !Variables.inSpells) {
                            Variables.inMenu = true;
                            Variables.menuIndex = 1;
                        }
                        if (Variables.inStatus) {
                            Variables.inStatus = false;
                            Variables.inMenu = true;
                            Variables.menuIndex = 1;
                        }
                        if (Variables.inShop) {
                            Variables.inShop = false;
                            Variables.inMenu = true;
                            Variables.menuIndex = 1;
                            Variables.selectedShopSlot = 0;
                        }
                    }
                }
            }
            // A BUTTON
            if (worldCoordinates.x >= 267 && worldCoordinates.x <= 350) {
                if (worldCoordinates.y >= 665 && worldCoordinates.y <= 750) {
                    if (!Variables.bBtn) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = true;
                        Variables.bBtn = false;
                    }
                }
            }
            // B BUTTON
            if (worldCoordinates.x >= 360 && worldCoordinates.x <= 445) {
                if (worldCoordinates.y >= 575 && worldCoordinates.y <= 659) {
                    if (!Variables.bBtn) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = true;
                    }
                }
            }
            // HOT KEY BUTTONS
            if (worldCoordinates.x >= 290 && worldCoordinates.x <= 338) {
                if (worldCoordinates.y >= 595 && worldCoordinates.y <= 643) {
                    if (!Variables.hotKeyQ) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;
                        Variables.hotKeyQ = true;
                        Variables.hotKeyE = false;
                    }
                }
            }
            if (worldCoordinates.x >= 378 && worldCoordinates.x <= 426) {
                if (worldCoordinates.y >= 684 && worldCoordinates.y <= 732) {
                    if (!Variables.hotKeyE) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;
                        Variables.hotKeyQ = false;
                        Variables.hotKeyE = true;
                    }
                }
            }
            if (worldCoordinates.x >= 29 && worldCoordinates.x <= 60) {
                if (worldCoordinates.y >= 581 && worldCoordinates.y <= 612) {
                    if (!Variables.hotKeyR) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;
                        Variables.hotKeyQ = false;
                        Variables.hotKeyE = false;
                        Variables.hotKeyR = true;
                        Variables.hotKeyF = false;
                    }
                }
            }
            if (worldCoordinates.x >= 163 && worldCoordinates.x <= 194) {
                if (worldCoordinates.y >= 581 && worldCoordinates.y <= 612) {
                    if (!Variables.hotKeyF) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;
                        Variables.hotKeyQ = false;
                        Variables.hotKeyE = false;
                        Variables.hotKeyR = false;
                        Variables.hotKeyF = true;
                    }
                }
            }
            if (Variables.inMenu) {
                if (worldCoordinates.x >= 24 && worldCoordinates.x <= 74) {
                    if (worldCoordinates.y >= 438 && worldCoordinates.y <= 468) {
                        if (!Variables.inMenu || !Variables.inShop) {
                            if (!Variables.touchDown) {
                                Variables.touchDown = true;
                                Variables.inMenu = false;
                                Variables.inShop = false;
                            }
                        }
                    }
                }
            }
            if (Variables.inMenu) {
                if (worldCoordinates.x >= 360 && worldCoordinates.x <= 432) {
                    if (worldCoordinates.y >= 27 && worldCoordinates.y <= 51) {
                        if (!Variables.inInventory) {
                            Variables.inMenu = false;
                            Variables.inStatus = false;
                            Variables.inInventory = true;
                            Variables.inSpells = false;
                        }
                    }
                }
                if (worldCoordinates.x >= 360 && worldCoordinates.x <= 432) {
                    if (worldCoordinates.y >= 68 && worldCoordinates.y <= 91) {
                        if (!Variables.inInventory) {
                            Variables.inMenu = false;
                            Variables.inStatus = false;
                            Variables.inInventory = false;
                            Variables.inSpells = true;
                        }
                    }
                }
                if (worldCoordinates.x >= 358 && worldCoordinates.x <= 442) {
                    if (worldCoordinates.y >= 111 && worldCoordinates.y <= 131) {
                        if (!Variables.inStatus) {
                            Variables.inMenu = false;
                            Variables.inInventory = false;
                            Variables.inStatus = true;
                            Variables.inSpells = false;

                            Variables.tempStats.Points = Variables.Players[Variables.MyIndex].getPoints();
                            Variables.tempStats.STR = 0;
                            Variables.tempStats.DEF = 0;
                            Variables.tempStats.VIT = 0;
                            Variables.tempStats.AGI = 0;
                            Variables.tempStats.MAG = 0;
                        }
                    }
                }
            }
            if (!Variables.inStatus && !Variables.inInventory && !Variables.inMenu && !Variables.inShop && !Variables.inSpells) {
                if (worldCoordinates.x >= 16 && worldCoordinates.x <= 463) {
                    if (worldCoordinates.y >= 472 && worldCoordinates.y <= 535) {
                        Variables.inChat = true;
                    }
                }
            }
            if (worldCoordinates.x >= 136 && worldCoordinates.x <= 336) {
                if (worldCoordinates.y >= 91 && worldCoordinates.y <= 391) {
                    // Party
                    if (worldCoordinates.x >= 166 && worldCoordinates.x <= 317) {
                        if (worldCoordinates.y >= 123 && worldCoordinates.y <= 151) {
                            SendClientData.SendPartyInvite();
                            Variables.target = 0;
                        }
                    }
                    // Trade
                    if (worldCoordinates.x >= 201 && worldCoordinates.x <= 281) {
                        if (worldCoordinates.y >= 196 && worldCoordinates.y <= 226) {

                        }
                    }
                } else { Variables.playerMenu = false; }
            } else { Variables.playerMenu = false; }

            if (Variables.PartyInvite) {
                if (worldCoordinates.x >= 146 && worldCoordinates.x <= 198) {
                    if (worldCoordinates.y >= 267 && worldCoordinates.y <= 284) {
                        SendClientData.SendPartyDecision(true);
                        Variables.PartyInvite = false;
                        Variables.PartyLeader = 0;
                    }
                }
                if (worldCoordinates.x >= 272 && worldCoordinates.x <= 327) {
                    if (worldCoordinates.y >= 267 && worldCoordinates.y <= 284) {
                        SendClientData.SendPartyDecision(false);
                        Variables.PartyInvite = false;
                        Variables.PartyLeader = 0;
                    }
                }
            }

            if (Variables.Players[Variables.MyIndex].getParty() > 0) {
                if (worldCoordinates.x >= 554 && worldCoordinates.x <= 643) {
                    if (worldCoordinates.y >= 420 && worldCoordinates.y <= 464) {
                        if (!Variables.inMenu) {
                            Variables.inMenu = true;
                        } else {
                            Variables.inMenu = false;
                        }
                    }
                }

                if (Variables.MyParty.leader == Variables.MyIndex) {
                    // Disband
                    if (worldCoordinates.x >= 280 && worldCoordinates.x <= 312) {
                        if (worldCoordinates.y >= 43 && worldCoordinates.y <= 75) {
                            SendClientData.SendDisbandParty();
                        }
                    }
                    // Leave
                    if (worldCoordinates.x >= 280 && worldCoordinates.x <= 312) {
                        if (worldCoordinates.y >= 78 && worldCoordinates.y <= 110) {
                            SendClientData.SendLeaveParty();
                        }
                    }
                    if (Variables.MyParty.members[2] > 0) {
                        // Appoint
                        if (worldCoordinates.x >= 280 && worldCoordinates.x <= 312) {
                            if (worldCoordinates.y >= 163 && worldCoordinates.y <= 195) {
                                SendClientData.SendAppointLeader(2);
                            }
                        }
                        // Kick
                        if (worldCoordinates.x >= 280 && worldCoordinates.x <= 312) {
                            if (worldCoordinates.y >= 198 && worldCoordinates.y <= 230) {
                                SendClientData.SendKickMember(2);
                            }
                        }
                    }
                    if (Variables.MyParty.members[3] > 0) {
                        // Appoint
                        if (worldCoordinates.x >= 280 && worldCoordinates.x <= 312) {
                            if (worldCoordinates.y >= 283 && worldCoordinates.y <= 315) {
                                SendClientData.SendAppointLeader(3);
                            }
                        }
                        // Kick
                        if (worldCoordinates.x >= 280 && worldCoordinates.x <= 312) {
                            if (worldCoordinates.y >= 318 && worldCoordinates.y <= 350) {
                                SendClientData.SendKickMember(3);
                            }
                        }
                    }
                    // Drop Sort Method
                    // Round Robin
                    if (worldCoordinates.x >= 363 && worldCoordinates.x <= 452) {
                        if (worldCoordinates.y >= 427 && worldCoordinates.y <= 441) {
                            Variables.MyParty.dropType = Variables.DROP_SORT_ROUNDROBIN;
                            SendClientData.SendUpdateDropType(Variables.DROP_SORT_ROUNDROBIN);
                        }
                    }
                    // Free For All
                    if (worldCoordinates.x >= 363 && worldCoordinates.x <= 442) {
                        if (worldCoordinates.y >= 447 && worldCoordinates.y <= 461) {
                            Variables.MyParty.dropType = Variables.DROP_SORT_FREEFORALL;
                            SendClientData.SendUpdateDropType(Variables.DROP_SORT_FREEFORALL);
                        }
                    }
                } else {
                    if (Variables.MyParty.members[2] == Variables.MyIndex) {
                        // Leave
                        if (worldCoordinates.x >= 280 && worldCoordinates.x <= 312) {
                            if (worldCoordinates.y >= 191 && worldCoordinates.y <= 223) {
                                SendClientData.SendLeaveParty();
                            }
                        }
                    } else if (Variables.MyParty.members[3] == Variables.MyIndex) {
                        // Leave
                        if (worldCoordinates.x >= 280 && worldCoordinates.x <= 312) {
                            if (worldCoordinates.y >= 321 && worldCoordinates.y <= 353) {
                                SendClientData.SendLeaveParty();
                            }
                        }
                    }
                }
            }

            if (Variables.inShop) { handleShop(worldCoordinates); }
            if (Variables.inInventory) { handleInventory(worldCoordinates); }
            if (Variables.inSpells) { handleSpells(worldCoordinates); }
            if (Variables.inStatus) { handleStatus(worldCoordinates); }
            if (Variables.inChat) { handleChat(worldCoordinates); }
        //}
    }

    public static void checkAttack(long tickCount) {
        if (Variables.pressAttack) {
            if (Variables.Players[Variables.MyIndex].getAttackTimer() + 500 < tickCount) {
                if (Variables.Players[Variables.MyIndex].getAttacking() == 0) {
                    Variables.Players[Variables.MyIndex].setAttacking(1);
                    Variables.Players[Variables.MyIndex].setAttackTimer(tickCount);
                    SendClientData.SendAttack();
                }
            }
        }
    }
    public static void checkPickUp() {
        if (Variables.pickUpItem) {
            SendClientData.SendPickUpItem();
            Variables.pickUpItem = false;
        }
    }
    public static void checkMovement() {
        if (!Variables.pauseMovement) {
            if (Variables.pressUp) {
                if (Variables.Players[Variables.MyIndex].getMoving() == 0) {
                    int mapNum = Variables.Players[Variables.MyIndex].getMap();
                    int x = Variables.Players[Variables.MyIndex].getX();
                    int y = Variables.Players[Variables.MyIndex].getY();
                    SendClientData.SendMovePlayer(Variables.DIR_UP);
                }
            } else if (Variables.pressDown) {
                if (Variables.Players[Variables.MyIndex].getMoving() == 0) {
                    int mapNum = Variables.Players[Variables.MyIndex].getMap();
                    int x = Variables.Players[Variables.MyIndex].getX();
                    int y = Variables.Players[Variables.MyIndex].getY();
                    SendClientData.SendMovePlayer(Variables.DIR_DOWN);
                }
            } else if (Variables.pressLeft) {
                if (Variables.Players[Variables.MyIndex].getMoving() == 0) {
                    int mapNum = Variables.Players[Variables.MyIndex].getMap();
                    int x = Variables.Players[Variables.MyIndex].getX();
                    int y = Variables.Players[Variables.MyIndex].getY();
                    SendClientData.SendMovePlayer(Variables.DIR_LEFT);
                }
            } else if (Variables.pressRight) {
                if (Variables.Players[Variables.MyIndex].getMoving() == 0) {
                    int mapNum = Variables.Players[Variables.MyIndex].getMap();
                    int x = Variables.Players[Variables.MyIndex].getX();
                    int y = Variables.Players[Variables.MyIndex].getY();
                    SendClientData.SendMovePlayer(Variables.DIR_RIGHT);
                }
            }
        }
    }

    public static void handleInput() {
        if (!Variables.inChat){// && !Variables.reloadingMap) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                Variables.pressUp = true;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                Variables.pressUp = false;
                Variables.pressLeft = true;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = true;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = true;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = true;
                Variables.pickUpItem = false;
            } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = true;
            } else {
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            }
        }
    }
    public static void handleAndroidInput() {
        if (!Variables.inChat) {// && !Variables.reloadingMap) {
            if (Variables.dPad_Up) {
                if (!Variables.touchDown) {
                    Variables.touchDown = true;
                    if (Variables.inInventory) {
                        if (Variables.selectedInvSlot > 10) {
                            Variables.selectedInvSlot = Variables.selectedInvSlot - 10;
                        }
                    }
                    if (Variables.inSpells) {
                        if (Variables.selectedSpellSlot > 10) {
                            Variables.selectedSpellSlot = Variables.selectedSpellSlot - 10;
                        }
                    }
                    if (Variables.inMenu) {
                        Variables.menuIndex--;
                        if (Variables.menuIndex == 0) {
                            Variables.menuIndex = 3;
                        }
                    }
                }
                Variables.pressUp = true;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Variables.dPad_Down) {
                if (!Variables.touchDown) {
                    Variables.touchDown = true;
                    if (Variables.inInventory) {
                        if (Variables.selectedInvSlot < 51) {
                            Variables.selectedInvSlot = Variables.selectedInvSlot + 10;
                        }
                    }
                    if (Variables.inSpells) {
                        if (Variables.selectedSpellSlot < 51) {
                            Variables.selectedSpellSlot = Variables.selectedSpellSlot + 10;
                        }
                    }
                    if (Variables.inMenu) {
                        Variables.menuIndex++;
                        if (Variables.menuIndex == 4) {
                            Variables.menuIndex = 1;
                        }
                    }
                }
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = true;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Variables.dPad_Left) {
                if (!Variables.touchDown) {
                    Variables.touchDown = true;
                    if (Variables.inInventory) {
                        if (Variables.selectedInvSlot > 1) {
                            Variables.selectedInvSlot = Variables.selectedInvSlot - 1;
                        }
                    }
                    if (Variables.inSpells) {
                        if (Variables.selectedSpellSlot > 1) {
                            Variables.selectedSpellSlot = Variables.selectedSpellSlot - 1;
                        }
                    }
                }
                Variables.pressUp = false;
                Variables.pressLeft = true;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Variables.dPad_Right) {
                if (!Variables.touchDown) {
                    Variables.touchDown = true;
                    if (Variables.inInventory) {
                        if (Variables.selectedInvSlot < 60) {
                            Variables.selectedInvSlot = Variables.selectedInvSlot + 1;
                        }
                    }
                    if (Variables.inSpells) {
                        if (Variables.selectedSpellSlot < 60) {
                            Variables.selectedSpellSlot = Variables.selectedSpellSlot + 1;
                        }
                    }
                }
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = true;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Variables.bBtn) {
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = false;
                if (!Variables.touchDown) {
                    Variables.touchDown = true;
                    if (Variables.inInventory) {
                        SendClientData.SendUseItem();
                    } else if (Variables.inSpells) {

                    } else if (Variables.inMenu) {
                        if (Variables.menuIndex == 1) {
                            Variables.inInventory = true;
                            Variables.inMenu = false;
                        } else if (Variables.menuIndex == 2) {
                            Variables.inSpells = true;
                            Variables.inMenu = false;
                        } else if (Variables.menuIndex == 3) {
                            Variables.inStatus = true;
                            Variables.inMenu = false;
                        }
                    } else {
                        Variables.pressAttack = true;
                    }
                }
                Variables.pickUpItem = false;
            } else if (Variables.aBtn) {
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                if (!Variables.touchDown) {
                    Variables.touchDown = true;
                    if (Variables.inInventory) {
                        Variables.inInventory = false;
                        Variables.inMenu = true;
                    } else if (Variables.inSpells) {

                    } else if (Variables.inMenu) {
                        Variables.inMenu = false;
                    } else {
                        Variables.pickUpItem = true;
                    }
                }
            } else if (Variables.hotKeyQ) {
                if (Variables.Players[Variables.MyIndex].getHotKeyQ() > 0) {
                    int spellNum = Variables.Players[Variables.MyIndex].getHotKeyQ();
                    if (Variables.Players[Variables.MyIndex].spells[spellNum].getCastTime() == 0) {
                        SendClientData.SendUseHotKey(Variables.HOT_KEY_Q);
                        Variables.hotKeyQ = false;
                    }
                }
            } else if (Variables.hotKeyE) {
                if (Variables.Players[Variables.MyIndex].getHotKeyE() > 0) {
                    int spellNum = Variables.Players[Variables.MyIndex].getHotKeyE();
                    if (Variables.Players[Variables.MyIndex].spells[spellNum].getCastTime() == 0) {
                        SendClientData.SendUseHotKey(Variables.HOT_KEY_E);
                        Variables.hotKeyE = false;
                    }
                }
            } else if (Variables.hotKeyR) {
                if (Variables.Players[Variables.MyIndex].getHotKeyR() > 0) {
                    Variables.selectedInvSlot = Variables.Players[Variables.MyIndex].getHotKeyR();
                    SendClientData.SendUseItem();
                    if (Variables.Players[Variables.MyIndex].inventory[Variables.selectedInvSlot].getItemVal() <= 0) {
                        Variables.Players[Variables.MyIndex].setHotKeyR(0);
                    }
                    Variables.hotKeyR = false;
                }
            } else if (Variables.hotKeyF) {
                if (Variables.Players[Variables.MyIndex].getHotKeyF() > 0) {
                    Variables.selectedInvSlot = Variables.Players[Variables.MyIndex].getHotKeyF();
                    SendClientData.SendUseItem();
                    if (Variables.Players[Variables.MyIndex].inventory[Variables.selectedInvSlot].getItemVal() <= 0) {
                        Variables.Players[Variables.MyIndex].setHotKeyF(0);
                    }
                    Variables.hotKeyF = false;
                }
            } else {
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
                Variables.hotKeyQ = false;
                Variables.hotKeyE = false;
            }
        }
    }

    public static void handleChat(Vector3 worldCoordinates) {
        // Input
        if (worldCoordinates.x >= 16 && worldCoordinates.x <= 464) {
            if (worldCoordinates.y >= 403 && worldCoordinates.y <= 427) {
                if (!Variables.inputOpen) {
                    Variables.inputOpen = true;
                    Gdx.input.getTextInput(new Input.TextInputListener() {

                        @Override
                        public void input(String text) {
                            Variables.chatInput = text;
                            Variables.inputOpen = false;
                        }

                        @Override
                        public void canceled() {
                            Variables.inputOpen = false;
                        }
                    }, "Chat:", "", "");
                }
            }
        }
        // Send Msg Button
        if (worldCoordinates.x >= 400 && worldCoordinates.x <= 450) {
            if (worldCoordinates.y >= 438 && worldCoordinates.y <= 468) {
                if (Variables.chatInput != null && !Variables.chatInput.isEmpty()) {
                    SendClientData.SendMessage();
                }
            }
        }
        // Back
        if (worldCoordinates.x >= 24 && worldCoordinates.x <= 74) {
            if (worldCoordinates.y >= 438 && worldCoordinates.y <= 468) {
                Variables.inChat = false;
            }
        }
    }
    public static void handleInventory(Vector3 worldCoordinates) {
        if (Variables.longPressMenu) {
            // Use
            if (worldCoordinates.x >= Variables.longPressMenuX + 15 && worldCoordinates.x <= Variables.longPressMenuX + 49) {
                if (worldCoordinates.y >= Variables.longPressMenuY + 8 && worldCoordinates.y <= Variables.longPressMenuY + 24) {
                    SendClientData.SendUseItem();
                    Variables.longPressMenu = false;
                }
            }
            // Drop
            if (worldCoordinates.x >= Variables.longPressMenuX + 8 && worldCoordinates.x <= Variables.longPressMenuX + 56) {
                if (worldCoordinates.y >= Variables.longPressMenuY + 48 && worldCoordinates.y <= Variables.longPressMenuY + 64) {
                    SendClientData.SendDropItem(Variables.selectedInvSlot);
                    Variables.longPressMenu = false;
                }
            }
            // Trash
            if (worldCoordinates.x >= Variables.longPressMenuX + 4 && worldCoordinates.x <= Variables.longPressMenuX + 58) {
                if (worldCoordinates.y >= Variables.longPressMenuY + 138 && worldCoordinates.y <= Variables.longPressMenuY + 154) {

                }
            }
        }

        int x = 36;
        int y = 36;
        for (int i = 1; i <= 60; i++) {
            if (i == 11) {
                x = 36;
                y = 77;
                if (Variables.Players[Variables.MyIndex].inventory[i] != null) {
                    if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.inputTimer == 0) {
                                        if (Variables.selectedInvSlot != i) {
                                            Variables.selectedInvSlot = i;
                                        }
                                        if (Variables.tapCount == 2) {
                                            SendClientData.SendUseItem();
                                        }
                                        if (Variables.longPressMenu) {
                                            Variables.longPressMenu = false;
                                        }
                                        Variables.longPressMenuX = x + 32;
                                        Variables.longPressMenuY = y;
                                        Variables.inputTimer = 1;
                                    }
                                    Variables.tapCount = 0;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 21) {
                x = 36;
                y = 118;
                if (Variables.Players[Variables.MyIndex].inventory[i] != null) {
                    if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.inputTimer == 0) {
                                        if (Variables.selectedInvSlot != i) {
                                            Variables.selectedInvSlot = i;
                                        }
                                        if (Variables.tapCount == 2) {
                                            SendClientData.SendUseItem();
                                        }
                                        if (Variables.longPressMenu) {
                                            Variables.longPressMenu = false;
                                        }
                                        Variables.longPressMenuX = x + 32;
                                        Variables.longPressMenuY = y;
                                        Variables.inputTimer = 1;
                                    }
                                    Variables.tapCount = 0;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 31) {
                x = 36;
                y = 159;
                if (Variables.Players[Variables.MyIndex].inventory[i] != null) {
                    if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.inputTimer == 0) {
                                        if (Variables.selectedInvSlot != i) {
                                            Variables.selectedInvSlot = i;
                                        }
                                        if (Variables.tapCount == 2) {
                                            SendClientData.SendUseItem();
                                        }
                                        if (Variables.longPressMenu) {
                                            Variables.longPressMenu = false;
                                        }
                                        Variables.longPressMenuX = x + 32;
                                        Variables.longPressMenuY = y;
                                        Variables.inputTimer = 1;
                                    }
                                    Variables.tapCount = 0;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 41) {
                x = 36;
                y = 200;
                if (Variables.Players[Variables.MyIndex].inventory[i] != null) {
                    if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.inputTimer == 0) {
                                        if (Variables.selectedInvSlot != i) {
                                            Variables.selectedInvSlot = i;
                                        }
                                        if (Variables.tapCount == 2) {
                                            SendClientData.SendUseItem();
                                        }
                                        if (Variables.longPressMenu) {
                                            Variables.longPressMenu = false;
                                        }
                                        Variables.longPressMenuX = x + 32;
                                        Variables.longPressMenuY = y;
                                        Variables.inputTimer = 1;
                                    }
                                    Variables.tapCount = 0;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 51) {
                x = 36;
                y = 241;
                if (Variables.Players[Variables.MyIndex].inventory[i] != null) {
                    if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.inputTimer == 0) {
                                        if (Variables.selectedInvSlot != i) {
                                            Variables.selectedInvSlot = i;
                                        }
                                        if (Variables.tapCount == 2) {
                                            SendClientData.SendUseItem();
                                        }
                                        if (Variables.longPressMenu) {
                                            Variables.longPressMenu = false;
                                        }
                                        Variables.longPressMenuX = x + 32;
                                        Variables.longPressMenuY = y;
                                        Variables.inputTimer = 1;
                                    }
                                    Variables.tapCount = 0;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else {
                if (Variables.Players[Variables.MyIndex].inventory[i] != null) {
                    if (Variables.Players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.inputTimer == 0) {
                                        if (Variables.selectedInvSlot != i) {
                                            Variables.selectedInvSlot = i;
                                        }
                                        if (Variables.tapCount == 2) {
                                            SendClientData.SendUseItem();
                                        }
                                        if (Variables.longPressMenu) {
                                            Variables.longPressMenu = false;
                                        }
                                        Variables.longPressMenuX = x + 32;
                                        Variables.longPressMenuY = y;
                                        Variables.inputTimer = 1;
                                    }
                                    Variables.tapCount = 0;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            }
        }

        if (Variables.selectedInvSlot > 0) {
            if (Variables.Players[Variables.MyIndex].inventory[Variables.selectedInvSlot] != null) {
                if (Variables.Items[Variables.Players[Variables.MyIndex].inventory[Variables.selectedInvSlot].getItemNum()].itemType == Variables.ITEM_TYPE_POTION) {
                    // R Hotkey
                    if (worldCoordinates.x >= 29 && worldCoordinates.x <= 60) {
                        if (worldCoordinates.y >= 581 && worldCoordinates.y <= 612) {
                            Variables.Players[Variables.MyIndex].setHotKeyR(Variables.selectedInvSlot);
                            SendClientData.SendSetHotKey(Variables.HOT_KEY_R, Variables.selectedInvSlot);
                        }
                    }
                    // F Hotkey
                    if (worldCoordinates.x >= 163 && worldCoordinates.x <= 194) {
                        if (worldCoordinates.y >= 581 && worldCoordinates.y <= 612) {
                            Variables.Players[Variables.MyIndex].setHotKeyF(Variables.selectedInvSlot);
                            SendClientData.SendSetHotKey(Variables.HOT_KEY_F, Variables.selectedInvSlot);
                        }
                    }
                }
            }
        }

        if (worldCoordinates.x >= 413 && worldCoordinates.x <= 460) {
            if (worldCoordinates.y >= 434 && worldCoordinates.y <= 456) {
                if (Variables.selectedInvSlot > 0) {
                    if (!Variables.touchDown) {
                        Variables.touchDown = true;
                        SendClientData.SendUseItem();
                    }
                }
            }
        }

        if (worldCoordinates.x >= 24 && worldCoordinates.x <= 74) {
            if (worldCoordinates.y >= 438 && worldCoordinates.y <= 468) {
                if (!Variables.touchDown) {
                    Variables.touchDown = true;
                    Variables.inInventory = false;
                    Variables.selectedInvSlot = 0;
                    Variables.inMenu = true;
                    Variables.menuIndex = 1;
                    Variables.longPressMenu = false;
                }
            }
        }
    }
    public static void handleSpells(Vector3 worldCoordinates) {
        int x = 36;
        int y = 36;
        for (int i = 1; i <= 60; i++) {
            if (i == 11) {
                x = 36;
                y = 77;
                if (Variables.Players[Variables.MyIndex].spells[i] != null) {
                    if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.selectedSpellSlot != i) {
                                        Variables.selectedSpellSlot = i;
                                    }
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 21) {
                x = 36;
                y = 118;
                if (Variables.Players[Variables.MyIndex].spells[i] != null) {
                    if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.selectedSpellSlot != i) {
                                        Variables.selectedSpellSlot = i;
                                    }
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 31) {
                x = 36;
                y = 159;
                if (Variables.Players[Variables.MyIndex].spells[i] != null) {
                    if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.selectedSpellSlot != i) {
                                        Variables.selectedSpellSlot = i;
                                    }
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 41) {
                x = 36;
                y = 200;
                if (Variables.Players[Variables.MyIndex].spells[i] != null) {
                    if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.selectedSpellSlot != i) {
                                        Variables.selectedSpellSlot = i;
                                    }
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 51) {
                x = 36;
                y = 241;
                if (Variables.Players[Variables.MyIndex].spells[i] != null) {
                    if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.selectedSpellSlot != i) {
                                        Variables.selectedSpellSlot = i;
                                    }
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else {
                if (Variables.Players[Variables.MyIndex].spells[i] != null) {
                    if (Variables.Players[Variables.MyIndex].spells[i].getSpellNum() > 0) {
                        if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                            if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                                if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                    if (Variables.selectedSpellSlot != i) {
                                        Variables.selectedSpellSlot = i;
                                    }
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            }
        }

        if (Variables.selectedSpellSlot > 0) {
            // Q Hotkey
            if (worldCoordinates.x >= 290 && worldCoordinates.x <= 338) {
                if (worldCoordinates.y >= 595 && worldCoordinates.y <= 643) {
                    Variables.Players[Variables.MyIndex].setHotKeyQ(Variables.selectedSpellSlot);
                    SendClientData.SendSetHotKey(Variables.HOT_KEY_Q, Variables.selectedSpellSlot);
                }
            }
            // E Hotkey
            if (worldCoordinates.x >= 378 && worldCoordinates.x <= 426) {
                if (worldCoordinates.y >= 684 && worldCoordinates.y <= 732) {
                    Variables.Players[Variables.MyIndex].setHotKeyE(Variables.selectedSpellSlot);
                    SendClientData.SendSetHotKey(Variables.HOT_KEY_E, Variables.selectedSpellSlot);
                }
            }
        }

        if (worldCoordinates.x >= 24 && worldCoordinates.x <= 74) {
            if (worldCoordinates.y >= 438 && worldCoordinates.y <= 468) {
                Variables.inInventory = false;
                Variables.inStatus = false;
                Variables.inSpells = false;
                Variables.selectedSpellSlot = 0;
                Variables.inMenu = true;
            }
        }
    }
    public static void handleStatus(Vector3 worldCoordinates) {
        // Add Points
        // STR
        if (worldCoordinates.x >= 43 && worldCoordinates.x <= 126) {
            if (worldCoordinates.y >= 77 && worldCoordinates.y <= 100) {
                if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
                    if (!Variables.usePoint) {
                        Gen.tempStats(1);
                    }
                }
            }
        }
        // DEF
        if (worldCoordinates.x >= 43 && worldCoordinates.x <= 126) {
            if (worldCoordinates.y >= 108 && worldCoordinates.y <= 129) {
                if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
                    if (!Variables.usePoint) {
                        Gen.tempStats(2);
                    }
                }
            }
        }
        // VIT
        if (worldCoordinates.x >= 43 && worldCoordinates.x <= 126) {
            if (worldCoordinates.y >= 138 && worldCoordinates.y <= 159) {
                if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
                    if (!Variables.usePoint) {
                        Gen.tempStats(3);
                    }
                }
            }
        }
        // AGI
        if (worldCoordinates.x >= 43 && worldCoordinates.x <= 126) {
            if (worldCoordinates.y >= 167 && worldCoordinates.y <= 189) {
                if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
                    if (!Variables.usePoint) {
                        Gen.tempStats(4);
                    }
                }
            }
        }
        // MAG
        if (worldCoordinates.x >= 43 && worldCoordinates.x <= 126) {
            if (worldCoordinates.y >= 198 && worldCoordinates.y <= 218) {
                if (Variables.Players[Variables.MyIndex].getPoints() > 0) {
                    if (!Variables.usePoint) {
                        Gen.tempStats(5);
                    }
                }
            }
        }

        // Back
        if (worldCoordinates.x >= 24 && worldCoordinates.x <= 74) {
            if (worldCoordinates.y >= 438 && worldCoordinates.y <= 468) {
                if (!Variables.touchDown) {
                    Variables.touchDown = true;
                    if (!Variables.inMenu) {
                        Variables.inInventory = false;
                        Variables.inStatus = false;
                        Variables.selectedInvSlot = 0;
                        Variables.inMenu = true;
                        Variables.menuIndex = 1;
                    }
                }
            }
        }

        // Confirm
        if (worldCoordinates.x >= 347 && worldCoordinates.x <= 450) {
            if (worldCoordinates.y >= 427 && worldCoordinates.y <= 450) {
                SendClientData.SendUsePoint();
            }
        }
    }
    public static void handleShop(Vector3 worldCoordinates) {
        int x = 36;
        int y = 122 - 8;
        for (int i = 1; i <= 20; i++) {
            if (i == 11) {
                x = 36;
                y = 163 - 8;
                if (Variables.Shop.itemNum[i] > 0) {
                    if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                        if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                            if (Variables.selectedShopSlot != i) {
                                Variables.selectedShopSlot = i;
                            }
                        }
                    }
                }
                x = x + 41;
            } else {
                if (Variables.Shop.itemNum[i] > 0) {
                    if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                        if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                            if (Variables.selectedShopSlot != i) {
                                Variables.selectedShopSlot = i;
                            }
                        }
                    }
                }
                x = x + 41;
            }
        }

        if (worldCoordinates.x >= 413 && worldCoordinates.x <= 460) {
            if (worldCoordinates.y >= 434 && worldCoordinates.y <= 456) {
                if (!Variables.buyItem) {
                    if (Variables.BoughtMsgTimer == 0) {
                        SendClientData.SendBuyItem();
                    }
                }
            }

        }

        // Buy multiple of an item
        // Buy less
        if (worldCoordinates.x >= 190 && worldCoordinates.x <= 220) {
            if (worldCoordinates.y >= 372 && worldCoordinates.y <= 402) {
                if (Variables.shopBuyAmt > 1) {
                    Variables.shopBuyAmt--;
                }
            }
        }
        // Buy more
        if (worldCoordinates.x >= 270 && worldCoordinates.x <= 300) {
            if (worldCoordinates.y >= 372 && worldCoordinates.y <= 402) {
                if (Variables.shopBuyAmt < 100) {
                    Variables.shopBuyAmt++;
                }
            }
        }

        if (worldCoordinates.x >= 24 && worldCoordinates.x <= 74) {
            if (worldCoordinates.y >= 438 && worldCoordinates.y <= 468) {
                Variables.inInventory = false;
                Variables.inStatus = false;
                Variables.inSpells = false;
                Variables.selectedSpellSlot = 0;
                Variables.inMenu = false;
                Variables.inShop = false;
            }
        }
    }
}
