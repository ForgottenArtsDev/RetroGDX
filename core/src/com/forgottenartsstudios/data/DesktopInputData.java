package com.forgottenartsstudios.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.forgottenartsstudios.gameworld.GameRenderer;
import com.forgottenartsstudios.helpers.AssetLoader;
import com.forgottenartsstudios.helpers.Variables;

/**
 * Created by forgo on 11/4/2017.
 */

public class DesktopInputData {
    public static void inputTileScreen() {
        Variables.Game_State = Variables.Game_State_Loading;
    }
    public static void inputLogin(int screenX, int screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));

        // ID TEXT FIELD
        if (worldCoordinates.x >= 220 && worldCoordinates.x <= 579) {
            if (worldCoordinates.y >= 338 && worldCoordinates.y <= 361) {
                Variables.inputID = true;
                Variables.inputPW = false;
            }
        }
        // PW TEXT FIELD
        if (worldCoordinates.x >= 220 && worldCoordinates.x <= 579) {
            if (worldCoordinates.y >= 408 && worldCoordinates.y <= 431 ) {
                Variables.inputPW = true;
                Variables.inputID = false;
            }
        }
        // SAVE LOGIN
        if (worldCoordinates.x >= 220 && worldCoordinates.x <= 243) {
            if (worldCoordinates.y >= 444 && worldCoordinates.y <= 467 ) {
                if (!Variables.saveLogin) {
                    Variables.saveLogin = true;
                } else {
                    Variables.saveLogin = false;
                }
            }
        }
        // LOGIN BUTTON
        if (worldCoordinates.x >= 658 && worldCoordinates.x <= 779) {
            if (worldCoordinates.y >= 465 && worldCoordinates.y <= 497) {
                if (Variables.Login_ID != null && !Variables.Login_ID.isEmpty()) {
                    Variables.IDReq = false;
                    if (Variables.Login_PW != null && !Variables.Login_PW.isEmpty()) {
                        Variables.PasswordReq = false;
                        Variables.IDReq = false;
                        if (Variables.saveLogin) {
                            AssetLoader.saveLogin();
                        }
                        SendClientData.SendLogin(Variables.Login_ID, Variables.Login_PW);
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
        // REGISTER BUTTON
        if (worldCoordinates.x >= 578 && worldCoordinates.x <= 779) {
            if (worldCoordinates.y >= 505 && worldCoordinates.y <= 537) {
                if (Variables.Login_ID != null && !Variables.Login_ID.isEmpty()) {
                    Variables.IDReq = false;
                    if (Variables.Login_PW != null && !Variables.Login_PW.isEmpty()) {
                        Variables.PasswordReq = false;
                        Variables.IDReq = false;
                        if (Variables.saveLogin) {
                            AssetLoader.saveLogin();
                        }
                        SendClientData.SendNewAccount(Variables.Login_ID, Variables.Login_PW);
                    } else {
                        Variables.PasswordReq = true;
                    }
                } else {
                    Variables.IDReq = true;
                }
            }
        }
        // QUIT BUTTON
        if (worldCoordinates.x >= 684 && worldCoordinates.x <= 779) {
            if (worldCoordinates.y >= 545 && worldCoordinates.y <= 577) {

            }
        }
    }
    public static void inputCharSelect(int screenX, int screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));
        // Char 1
        if (worldCoordinates.x >= 39 && worldCoordinates.x <= 238) {
            if (worldCoordinates.y >= 265 && worldCoordinates.y <= 564) {
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
        if (worldCoordinates.x >= 299 && worldCoordinates.x <= 498) {
            if (worldCoordinates.y >= 265 && worldCoordinates.y <= 564) {
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
        if (worldCoordinates.x >= 560 && worldCoordinates.x <= 759) {
            if (worldCoordinates.y >= 265 && worldCoordinates.y <= 564) {
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
    public static void inputCharCreate(int screenX, int screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));
        // NAME
        if (worldCoordinates.x >= 225 && worldCoordinates.x <= 579) {
            if (worldCoordinates.y >= 299 && worldCoordinates.y <= 320) {
                Variables.inputName = true;
            }
        }
        // WARRIOR
        if (worldCoordinates.x >= 155 && worldCoordinates.x <= 245) {
            if (worldCoordinates.y >= 327 && worldCoordinates.y <= 417) {
                Variables.TempJob = Variables.JOB_WARRIOR;
                Variables.TempSprite = 1;
            }
        }
        // WIZARD
        if (worldCoordinates.x >= 255 && worldCoordinates.x <= 345) {
            if (worldCoordinates.y >= 327 && worldCoordinates.y <= 417) {
                Variables.TempJob = Variables.JOB_WIZARD;
                Variables.TempSprite = 3;
            }
        }
        // CLERIC
        if (worldCoordinates.x >= 355 && worldCoordinates.x <= 445) {
            if (worldCoordinates.y >= 327 && worldCoordinates.y <= 417) {
                Variables.TempJob = Variables.JOB_CLERIC;
                Variables.TempSprite = 5;
            }
        }
        // RANGER
        if (worldCoordinates.x >= 455 && worldCoordinates.x <= 545) {
            if (worldCoordinates.y >= 327 && worldCoordinates.y <= 417) {
                Variables.TempJob = Variables.JOB_RANGER;
                Variables.TempSprite = 7;
            }
        }
        // ROGUE
        if (worldCoordinates.x >= 555 && worldCoordinates.x <= 645) {
            if (worldCoordinates.y >= 327 && worldCoordinates.y <= 417) {
                Variables.TempJob = Variables.JOB_ROGUE;
                Variables.TempSprite = 9;
            }
        }
        // MALE
        if (worldCoordinates.x >= 414 && worldCoordinates.x <= 474) {
            if (worldCoordinates.y >= 480 && worldCoordinates.y <= 540) {
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
        if (worldCoordinates.x >= 526 && worldCoordinates.x <= 586) {
            if (worldCoordinates.y >= 480 && worldCoordinates.y <= 540) {
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
        if (worldCoordinates.x >= 615 && worldCoordinates.x <= 780) {
            if (worldCoordinates.y >= 540 && worldCoordinates.y <= 580) {
                SendClientData.SendCreateChar(Variables.MyIndex);
            }
        }
    }
    public static void inputInGame(int screenX, int screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));
        Variables.CurX = (int)worldCoordinates.x / Variables.MoveSize;
        Variables.CurY = (int)worldCoordinates.y / Variables.MoveSize;
        if (worldCoordinates.x >= 675 && worldCoordinates.x <= 710) {
            if (worldCoordinates.y >= 420 && worldCoordinates.y <= 460) {
                if (!Variables.inInventory) {
                    Variables.inInventory = true;
                    Variables.inStatus = false;
                    Variables.inShop = false;
                } else {
                    Variables.inInventory = false;
                    Variables.inStatus = false;
                    Variables.inShop = false;
                }
            }
        }
        if (worldCoordinates.x >= 635 && worldCoordinates.x <= 669) {
            if (worldCoordinates.y >= 420 && worldCoordinates.y <= 460) {
                if (!Variables.inStatus) {
                    Variables.inStatus = true;
                    Variables.inInventory = false;
                    Variables.inShop = false;
                } else {
                    Variables.inStatus = false;
                    Variables.inInventory = false;
                    Variables.inShop = false;
                }
            }
        }
        if (!Variables.reloadingMap) {
            if (Variables.inShop) { handleShop(worldCoordinates); }
            if (Variables.inInventory) { handleInventory(worldCoordinates); }
            if (Variables.inStatus) { handleStatus(worldCoordinates); }
            handleChat(worldCoordinates);
        }
    }

    public static void checkAttack(long tickCount) {
        if (Variables.pressAttack) {
            if (Variables.players[Variables.MyIndex].getAttackTimer() + 500 < tickCount) {
                if (Variables.players[Variables.MyIndex].getAttacking() == 0) {
                    Variables.players[Variables.MyIndex].setAttacking(1);
                    Variables.players[Variables.MyIndex].setAttackTimer(tickCount);
                    SendClientData.SendAttack();
                }
            }
        }
    }
    public static void checkPickUp() {
        if (Variables.pickUpItem) {
            SendClientData.SendPickUpItem();
        }
    }
    public static void checkMovement() {
        if (Variables.pressUp) {
            if (Variables.players[Variables.MyIndex].getMoving() == 0) {
                int mapNum = Variables.players[Variables.MyIndex].getMap();
                int x = Variables.players[Variables.MyIndex].getX();
                int y = Variables.players[Variables.MyIndex].getY();
                SendClientData.SendMovePlayer(Variables.DIR_UP);
            }
        } else if (Variables.pressDown) {
            if (Variables.players[Variables.MyIndex].getMoving() == 0) {
                int mapNum = Variables.players[Variables.MyIndex].getMap();
                int x = Variables.players[Variables.MyIndex].getX();
                int y = Variables.players[Variables.MyIndex].getY();
                SendClientData.SendMovePlayer(Variables.DIR_DOWN);
            }
        } else if (Variables.pressLeft) {
            if (Variables.players[Variables.MyIndex].getMoving() == 0) {
                int mapNum = Variables.players[Variables.MyIndex].getMap();
                int x = Variables.players[Variables.MyIndex].getX();
                int y = Variables.players[Variables.MyIndex].getY();
                SendClientData.SendMovePlayer(Variables.DIR_LEFT);
            }
        } else if (Variables.pressRight) {
            if (Variables.players[Variables.MyIndex].getMoving() == 0) {
                int mapNum = Variables.players[Variables.MyIndex].getMap();
                int x = Variables.players[Variables.MyIndex].getX();
                int y = Variables.players[Variables.MyIndex].getY();
                SendClientData.SendMovePlayer(Variables.DIR_RIGHT);
            }
        }
    }
    public static void handleInput() {
        if (!Variables.inChat && !Variables.reloadingMap) {
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
            } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                if (Variables.chatInput != null && !Variables.chatInput.isEmpty()) {
                    SendClientData.SendMessage();
                }
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
        if (!Variables.inChat && !Variables.reloadingMap) {
            if (Variables.dPad_Up) {
                Variables.pressUp = true;
                Variables.pressLeft = false;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Variables.dPad_Down) {
                Variables.pressUp = false;
                Variables.pressLeft = false;
                Variables.pressDown = true;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Variables.dPad_Left) {
                Variables.pressUp = false;
                Variables.pressLeft = true;
                Variables.pressDown = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
            } else if (Variables.dPad_Right) {
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
                Variables.pressAttack = true;
                Variables.pickUpItem = false;
            } else if (Variables.aBtn) {
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

    public static void handleChat(Vector3 worldCoordinates) {
        // Input
        if (worldCoordinates.x >= 9 && worldCoordinates.x <= 790) {
            if (worldCoordinates.y >= 567 && worldCoordinates.y <= 690) {
                Variables.inChat = true;
            }
        }
    }
    public static void handleInventory(Vector3 worldCoordinates) {
        int x = 28;
        int y = 28;
        for (int i = 1; i <= 60; i++) {
            if (i == 11) {
                x = 28;
                y = 69;
                if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                    if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                        if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                            if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                                    if (Variables.selectedInvSlot != i) {
                                        Variables.selectedInvSlot = i;
                                    } else {
                                        SendClientData.SendUseItem();
                                    }
                                } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                                    if (Variables.selectedInvSlot == i) {
                                        SendClientData.SendDropItem(i);
                                    }
                                }
                            } else if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                if (Variables.selectedInvSlot != i) {
                                    Variables.selectedInvSlot = i;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 21) {
                x = 28;
                y = 110;
                if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                    if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                        if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                            if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                                    if (Variables.selectedInvSlot != i) {
                                        Variables.selectedInvSlot = i;
                                    } else {
                                        SendClientData.SendUseItem();
                                    }
                                } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                                    if (Variables.selectedInvSlot == i) {
                                        SendClientData.SendDropItem(i);
                                    }
                                }
                            } else if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                if (Variables.selectedInvSlot != i) {
                                    Variables.selectedInvSlot = i;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 31) {
                x = 28;
                y = 151;
                if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                    if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                        if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                            if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                                    if (Variables.selectedInvSlot != i) {
                                        Variables.selectedInvSlot = i;
                                    } else {
                                        SendClientData.SendUseItem();
                                    }
                                } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                                    if (Variables.selectedInvSlot == i) {
                                        SendClientData.SendDropItem(i);
                                    }
                                }
                            } else if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                if (Variables.selectedInvSlot != i) {
                                    Variables.selectedInvSlot = i;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 41) {
                x = 28;
                y = 192;
                if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                    if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                        if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                            if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                                    if (Variables.selectedInvSlot != i) {
                                        Variables.selectedInvSlot = i;
                                    } else {
                                        SendClientData.SendUseItem();
                                    }
                                } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                                    if (Variables.selectedInvSlot == i) {
                                        SendClientData.SendDropItem(i);
                                    }
                                }
                            } else if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                if (Variables.selectedInvSlot != i) {
                                    Variables.selectedInvSlot = i;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else if (i == 51) {
                x = 28;
                y = 233;
                if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                    if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                        if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                            if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                                    if (Variables.selectedInvSlot != i) {
                                        Variables.selectedInvSlot = i;
                                    } else {
                                        SendClientData.SendUseItem();
                                    }
                                } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                                    if (Variables.selectedInvSlot == i) {
                                        SendClientData.SendDropItem(i);
                                    }
                                }
                            } else if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                if (Variables.selectedInvSlot != i) {
                                    Variables.selectedInvSlot = i;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            } else {
                if (Variables.players[Variables.MyIndex].inventory[i].getItemNum() > 0) {
                    if (worldCoordinates.x >= x && worldCoordinates.x <= x + 36) {
                        if (worldCoordinates.y >= y && worldCoordinates.y <= y + 36) {
                            if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
                                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                                    if (Variables.selectedInvSlot != i) {
                                        Variables.selectedInvSlot = i;
                                    } else {
                                        SendClientData.SendUseItem();
                                    }
                                } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                                    if (Variables.selectedInvSlot == i) {
                                        SendClientData.SendDropItem(i);
                                    }
                                }
                            } else if (Variables.Client_Mode == Variables.Client_Mode_Android) {
                                if (Variables.selectedInvSlot != i) {
                                    Variables.selectedInvSlot = i;
                                }
                            }
                        }
                    }
                }
                x = x + 41;
            }
        }

        if (worldCoordinates.x >= 405 && worldCoordinates.x <= 455) {
            if (worldCoordinates.y >= 429 && worldCoordinates.y <= 451) {
                if (Variables.selectedInvSlot > 0) {
                    if (!Variables.useItem) {
                        SendClientData.SendUseItem();
                    }
                }
            }
        }

        if (worldCoordinates.x >= 16 && worldCoordinates.x <= 66) {
            if (worldCoordinates.y >= 430 && worldCoordinates.y <= 460) {
                Variables.inInventory = false;
                Variables.inStatus = false;
                Variables.selectedInvSlot = 0;
                Variables.inMenu = false;
            }
        }
    }
    public static void handleStatus(Vector3 worldCoordinates) {
// Add Points
        // STR
        if (worldCoordinates.x >= 35 && worldCoordinates.x <= 118) {
            if (worldCoordinates.y >= 68 && worldCoordinates.y <= 92) {
                if (Variables.players[Variables.MyIndex].getPoints() > 0) {
                    if (!Variables.usePoint) {
                        SendClientData.SendUsePoint(1);
                    }
                }
            }
        }
        // DEF
        if (worldCoordinates.x >= 35 && worldCoordinates.x <= 118) {
            if (worldCoordinates.y >= 100 && worldCoordinates.y <= 121) {
                if (Variables.players[Variables.MyIndex].getPoints() > 0) {
                    if (!Variables.usePoint) {
                        SendClientData.SendUsePoint(2);
                    }
                }
            }
        }
        // VIT
        if (worldCoordinates.x >= 35 && worldCoordinates.x <= 118) {
            if (worldCoordinates.y >= 130 && worldCoordinates.y <= 151) {
                if (Variables.players[Variables.MyIndex].getPoints() > 0) {
                    if (!Variables.usePoint) {
                        SendClientData.SendUsePoint(3);
                    }
                }
            }
        }
        // AGI
        if (worldCoordinates.x >= 35 && worldCoordinates.x <= 118) {
            if (worldCoordinates.y >= 159 && worldCoordinates.y <= 181) {
                if (Variables.players[Variables.MyIndex].getPoints() > 0) {
                    if (!Variables.usePoint) {
                        SendClientData.SendUsePoint(4);
                    }
                }
            }
        }
        // MAG
        if (worldCoordinates.x >= 35 && worldCoordinates.x <= 118) {
            if (worldCoordinates.y >= 190 && worldCoordinates.y <= 210) {
                if (Variables.players[Variables.MyIndex].getPoints() > 0) {
                    if (!Variables.usePoint) {
                        SendClientData.SendUsePoint(5);
                    }
                }
            }
        }

        // Back
        if (worldCoordinates.x >= 16 && worldCoordinates.x <= 66) {
            if (worldCoordinates.y >= 430 && worldCoordinates.y <= 460) {
                if (!Variables.inMenu) {
                    Variables.inInventory = false;
                    Variables.inStatus = false;
                    Variables.selectedInvSlot = 0;
                    Variables.inMenu = false;
                }
            }
        }
    }
    public static void handleShop(Vector3 worldCoordinates) {
        int x = 28;
        int y = 114 - 8;
        for (int i = 1; i <= 20; i++) {
            if (i == 11) {
                x = 28;
                y = 155 - 8;
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

        if (worldCoordinates.x >= 405 && worldCoordinates.x <= 452) {
            if (worldCoordinates.y >= 426 && worldCoordinates.y <= 448) {
                if (!Variables.buyItem) {
                    if (Variables.BoughtMsgTimer == 0) {
                        SendClientData.SendBuyItem();
                    }
                }
            }

        }
    }
}
