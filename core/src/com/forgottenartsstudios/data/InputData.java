package com.forgottenartsstudios.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.forgottenartsstudios.gameworld.GameRenderer;
import com.forgottenartsstudios.helpers.AssetLoader;
import com.forgottenartsstudios.helpers.Variables;
import com.forgottenartsstudios.networking.packets.ChooseChar;
import com.forgottenartsstudios.networking.packets.CreateChar;
import com.forgottenartsstudios.networking.packets.NewAccount;

import static com.forgottenartsstudios.gameworld.GameRenderer.batcher;
import static com.forgottenartsstudios.rtonline.RTOnline.client;

/**
 * Created by forgo on 10/6/2017.
 */

public class InputData {
    public static void inputTileScreen() {
        Variables.Game_State = Variables.Game_State_Loading;
    }
    public static void inputLogin(int screenX, int screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));

        // ID TEXT FIELD
        if (worldCoordinates.x >= 141 && worldCoordinates.x <= 440) {
            if (worldCoordinates.y >= 395 && worldCoordinates.y <= 424) {
                Gdx.input.getTextInput(new Input.TextInputListener() {

                    @Override
                    public void input(String text) {
                        Variables.Login_ID = text;
                    }

                    @Override
                    public void canceled() {
                    }
                }, "ID:", "", "");
            }
        }
        // PW TEXT FIELD
        if (worldCoordinates.x >= 141 && worldCoordinates.x <= 440) {
            if (worldCoordinates.y >= 455 && worldCoordinates.y <= 484) {
                Gdx.input.getTextInput(new Input.TextInputListener() {

                    @Override
                    public void input(String text) {
                        Variables.Login_PW = text;
                    }

                    @Override
                    public void canceled() {
                    }
                }, "PW:", "", "");
            }
        }
        // LOGIN BUTTON
        if (worldCoordinates.x >= 165 && worldCoordinates.x <= 315) {
            if (worldCoordinates.y >= 560 && worldCoordinates.y <= 615) {
                if (Variables.Login_ID != null && !Variables.Login_ID.isEmpty()) {
                    Variables.IDReq = false;
                    if (Variables.Login_PW != null && !Variables.Login_PW.isEmpty()) {
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
        if (worldCoordinates.x >= 115 && worldCoordinates.x <= 320) {
            if (worldCoordinates.y >= 660 && worldCoordinates.y <= 720) {
                if (Variables.Login_ID != null && !Variables.Login_ID.isEmpty()) {
                    Variables.IDReq = false;
                    if (Variables.Login_PW != null && !Variables.Login_PW.isEmpty()) {
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
    public static void inputCharSelect(int screenX, int screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));
        // Char 1
        if (worldCoordinates.x >= 7 && worldCoordinates.x <= 470) {
            if (worldCoordinates.y >= 310 && worldCoordinates.y <= 460) {
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
        if (worldCoordinates.x >= 7 && worldCoordinates.x <= 470) {
            if (worldCoordinates.y >= 470 && worldCoordinates.y <= 620) {
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
        if (worldCoordinates.x >= 7 && worldCoordinates.x <= 470) {
            if (worldCoordinates.y >= 632 && worldCoordinates.y <= 778) {
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
        if (worldCoordinates.x >= 158 && worldCoordinates.x <= 460) {
            if (worldCoordinates.y >= 372 && worldCoordinates.y <= 400) {
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
            if (worldCoordinates.y >= 477 && worldCoordinates.y <= 567) {
                Variables.TempJob = Variables.JOB_WARRIOR;
                Variables.TempSprite = 1;
            }
        }
        // WIZARD
        if (worldCoordinates.x >= 103 && worldCoordinates.x <= 193) {
            if (worldCoordinates.y >= 477 && worldCoordinates.y <= 567) {
                Variables.TempJob = Variables.JOB_WIZARD;
                Variables.TempSprite = 3;
            }
        }
        // CLERIC
        if (worldCoordinates.x >= 195 && worldCoordinates.x <= 285) {
            if (worldCoordinates.y >= 477 && worldCoordinates.y <= 567) {
                Variables.TempJob = Variables.JOB_CLERIC;
                Variables.TempSprite = 5;
            }
        }
        // RANGER
        if (worldCoordinates.x >= 287 && worldCoordinates.x <= 377) {
            if (worldCoordinates.y >= 477 && worldCoordinates.y <= 567) {
                Variables.TempJob = Variables.JOB_RANGER;
                Variables.TempSprite = 7;
            }
        }
        // ROGUE
        if (worldCoordinates.x >= 379 && worldCoordinates.x <= 469) {
            if (worldCoordinates.y >= 477 && worldCoordinates.y <= 567) {
                Variables.TempJob = Variables.JOB_ROGUE;
                Variables.TempSprite = 9;
            }
        }
        // MALE
        if (worldCoordinates.x >= 154 && worldCoordinates.x <= 214) {
            if (worldCoordinates.y >= 648 && worldCoordinates.y <= 708) {
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
            if (worldCoordinates.y >= 648 && worldCoordinates.y <= 708) {
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
        if (worldCoordinates.x >= 160 && worldCoordinates.x <= 320) {
            if (worldCoordinates.y >= 730 && worldCoordinates.y <= 775) {
                SendClientData.SendCreateChar(Variables.MyIndex);
            }
        }
    }
    public static void inputInGame(int screenX, int screenY) {
        Vector3 worldCoordinates = GameRenderer.cam.unproject(new Vector3(screenX, screenY, 0));
        Variables.CurX = (int)worldCoordinates.x / Variables.MoveSize;
        Variables.CurY = (int)worldCoordinates.y / Variables.MoveSize;
        if (!Variables.reloadingMap) {
            // UP
            if (worldCoordinates.x >= 110 && worldCoordinates.x <= 158) {
                if (worldCoordinates.y >= 517 && worldCoordinates.y <= 598) {
                    if (!Variables.dPad_Up) {
                        Variables.dPad_Up = true;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;

                        Variables.inMenu = false;
                    }
                }
            }
            // DOWN
            if (worldCoordinates.x >= 110 && worldCoordinates.x <= 158) {
                if (worldCoordinates.y >= 642 && worldCoordinates.y <= 723) {
                    if (!Variables.dPad_Down) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = true;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;

                        Variables.inMenu = false;
                    }
                }
            }
            // LEFT
            if (worldCoordinates.x >= 29 && worldCoordinates.x <= 110) {
                if (worldCoordinates.y >= 598 && worldCoordinates.y <= 646) {
                    if (!Variables.dPad_Left) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = true;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;

                        Variables.inMenu = false;
                    }
                }
            }
            // RIGHT
            if (worldCoordinates.x >= 154 && worldCoordinates.x <= 235) {
                if (worldCoordinates.y >= 598 && worldCoordinates.y <= 646) {
                    if (!Variables.dPad_Right) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = true;

                        Variables.aBtn = false;
                        Variables.bBtn = false;

                        Variables.inMenu = false;
                    }
                }
            }
            // MENU
            if (worldCoordinates.x >= 200 && worldCoordinates.x <= 285) {
                if (worldCoordinates.y >= 731 && worldCoordinates.y <= 762) {
                    if (!Variables.inMenu) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = false;
                        Variables.bBtn = false;

                        if (!Variables.inMenu) {
                            Variables.inMenu = true;
                            Variables.inShop = false;
                            Variables.inInventory = false;
                        } else {
                            Variables.inMenu = false;
                            Variables.inShop = false;
                            Variables.inInventory = false;
                        }
                        Variables.selectedInvSlot = 0;
                    }
                }
            }
            // A BUTTON
            if (worldCoordinates.x >= 303 && worldCoordinates.x <= 373) {
                if (worldCoordinates.y >= 636 && worldCoordinates.y <= 706) {
                    if (!Variables.bBtn) {
                        Variables.dPad_Up = false;
                        Variables.dPad_Down = false;
                        Variables.dPad_Left = false;
                        Variables.dPad_Right = false;

                        Variables.aBtn = true;
                        Variables.bBtn = false;

                        if (Variables.inMenu) {
                            Variables.inMenu = false;
                        }
                        if (Variables.inInventory) {
                            Variables.inInventory = false;
                            Variables.inMenu = true;
                        }
                        if (Variables.inShop) {
                            Variables.inShop = false;
                        }
                        if (Variables.inStatus) {
                            Variables.inStatus = false;
                            Variables.inMenu = true;
                        }
                    }
                }
            }
            // B BUTTON
            if (worldCoordinates.x >= 374 && worldCoordinates.x <= 445) {
                if (worldCoordinates.y >= 564 && worldCoordinates.y <= 632) {
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
            if (Variables.inMenu || Variables.inShop) {
                if (worldCoordinates.x >= 24 && worldCoordinates.x <= 74) {
                    if (worldCoordinates.y >= 438 && worldCoordinates.y <= 468) {
                        if (!Variables.inMenu || !Variables.inShop) {
                            Variables.inMenu = false;
                            Variables.inShop = false;
                        }
                    }
                }
            } else if (Variables.inInventory) {
                if (worldCoordinates.x >= 24 && worldCoordinates.x <= 74) {
                    if (worldCoordinates.y >= 438 && worldCoordinates.y <= 468) {
                        if (!Variables.inMenu) {
                            Variables.inInventory = false;
                            Variables.inStatus = false;
                            Variables.selectedInvSlot = 0;
                            Variables.inMenu = true;
                        }
                    }
                }
            } else if (Variables.inStatus) {
                // Add Points
                // STR
                if (worldCoordinates.x >= 43 && worldCoordinates.x <= 126) {
                    if (worldCoordinates.y >= 77 && worldCoordinates.y <= 100) {
                        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
                            if (!Variables.usePoint) {
                                SendClientData.SendUsePoint(1);
                            }
                        }
                    }
                }
                // DEF
                if (worldCoordinates.x >= 43 && worldCoordinates.x <= 126) {
                    if (worldCoordinates.y >= 108 && worldCoordinates.y <= 129) {
                        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
                            if (!Variables.usePoint) {
                                SendClientData.SendUsePoint(2);
                            }
                        }
                    }
                }
                // VIT
                if (worldCoordinates.x >= 43 && worldCoordinates.x <= 126) {
                    if (worldCoordinates.y >= 138 && worldCoordinates.y <= 159) {
                        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
                            if (!Variables.usePoint) {
                                SendClientData.SendUsePoint(3);
                            }
                        }
                    }
                }
                // AGI
                if (worldCoordinates.x >= 43 && worldCoordinates.x <= 126) {
                    if (worldCoordinates.y >= 167 && worldCoordinates.y <= 189) {
                        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
                            if (!Variables.usePoint) {
                                SendClientData.SendUsePoint(4);
                            }
                        }
                    }
                }
                // MAG
                if (worldCoordinates.x >= 43 && worldCoordinates.x <= 126) {
                    if (worldCoordinates.y >= 198 && worldCoordinates.y <= 218) {
                        if (Variables.players[Variables.MyIndex].getPoints() > 0) {
                            if (!Variables.usePoint) {
                                SendClientData.SendUsePoint(5);
                            }
                        }
                    }
                }

                // Back
                if (worldCoordinates.x >= 24 && worldCoordinates.x <= 74) {
                    if (worldCoordinates.y >= 438 && worldCoordinates.y <= 468) {
                        if (!Variables.inMenu) {
                            Variables.inInventory = false;
                            Variables.inStatus = false;
                            Variables.selectedInvSlot = 0;
                            Variables.inMenu = true;
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
                        }
                    }
                }
                if (worldCoordinates.x >= 358 && worldCoordinates.x <= 442) {
                    if (worldCoordinates.y >= 111 && worldCoordinates.y <= 131) {
                        if (!Variables.inStatus) {
                            Variables.inMenu = false;
                            Variables.inInventory = false;
                            Variables.inStatus = true;
                        }
                    }
                }
            }
            if (Variables.inShop) {
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
            }

            if (Variables.inInventory) {
                int x = 36;
                int y = 36;
                for (int i = 1; i <= 60; i++) {
                    if (i == 11) {
                        x = 36;
                        y = 77;
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
                        x = 36;
                        y = 118;
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
                        x = 36;
                        y = 159;
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
                        x = 36;
                        y = 200;
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
                        x = 36;
                        y = 241;
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

                if (worldCoordinates.x >= 413 && worldCoordinates.x <= 460) {
                    if (worldCoordinates.y >= 434 && worldCoordinates.y <= 456) {
                        if (Variables.selectedInvSlot > 0) {
                            if (!Variables.useItem) {
                                SendClientData.SendUseItem();
                            }
                        }
                    }
                }
            }
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
            if (Variables.players[Variables.MyIndex].getOffsetX() == 0) {
                if (Variables.players[Variables.MyIndex].getOffsetY() == 0) {
                    int mapNum = Variables.players[Variables.MyIndex].getMap();
                    int x = Variables.players[Variables.MyIndex].getX();
                    int y = Variables.players[Variables.MyIndex].getY();
                    if (Variables.mapRender[mapNum].Tile[x][y].Type == Variables.TILE_TYPE_WARP) {
                        SendClientData.SendWarpCheck();
                    } else {
                        SendClientData.SendMovePlayer(Variables.DIR_UP);
                    }
                }
            }
        } else if (Variables.pressDown) {
            if (Variables.players[Variables.MyIndex].getOffsetX() == 0) {
                if (Variables.players[Variables.MyIndex].getOffsetY() == 0) {
                    int mapNum = Variables.players[Variables.MyIndex].getMap();
                    int x = Variables.players[Variables.MyIndex].getX();
                    int y = Variables.players[Variables.MyIndex].getY();
                    if (Variables.mapRender[mapNum].Tile[x][y].Type == Variables.TILE_TYPE_WARP) {
                        SendClientData.SendWarpCheck();
                    } else {
                        SendClientData.SendMovePlayer(Variables.DIR_DOWN);
                    }
                }
            }
        } else if (Variables.pressLeft) {
            if (Variables.players[Variables.MyIndex].getOffsetX() == 0) {
                if (Variables.players[Variables.MyIndex].getOffsetY() == 0) {
                    int mapNum = Variables.players[Variables.MyIndex].getMap();
                    int x = Variables.players[Variables.MyIndex].getX();
                    int y = Variables.players[Variables.MyIndex].getY();
                    if (Variables.mapRender[mapNum].Tile[x][y].Type == Variables.TILE_TYPE_WARP) {
                        SendClientData.SendWarpCheck();
                    } else {
                        SendClientData.SendMovePlayer(Variables.DIR_LEFT);
                    }
                }
            }
        } else if (Variables.pressRight) {
            if (Variables.players[Variables.MyIndex].getOffsetX() == 0) {
                if (Variables.players[Variables.MyIndex].getOffsetY() == 0) {
                    int mapNum = Variables.players[Variables.MyIndex].getMap();
                    int x = Variables.players[Variables.MyIndex].getX();
                    int y = Variables.players[Variables.MyIndex].getY();
                    if (Variables.mapRender[mapNum].Tile[x][y].Type == Variables.TILE_TYPE_WARP) {
                        SendClientData.SendWarpCheck();
                    } else {
                        SendClientData.SendMovePlayer(Variables.DIR_RIGHT);
                    }
                }
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
        if (!Variables.inChat) {
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
}
