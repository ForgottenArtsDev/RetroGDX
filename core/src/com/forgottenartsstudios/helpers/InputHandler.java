package com.forgottenartsstudios.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.forgottenartsstudios.data.AndroidInputData;
import com.forgottenartsstudios.data.DesktopInputData;
import com.forgottenartsstudios.data.SendClientData;
import com.forgottenartsstudios.gameworld.GameWorld;
import com.forgottenartsstudios.networking.packetdata.SendServerData;

/**
 * Created by forgo on 10/6/2017.
 */

public class InputHandler implements InputProcessor {

    private GameWorld myWorld;

    public InputHandler(GameWorld myWorld) {
        this.myWorld = myWorld;
    }
    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (!Variables.inChat) {
                Variables.inChat = true;
            } else {
                Variables.inChat = false;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Variables.pressUp = false;
        Variables.pressDown = false;
        Variables.pressLeft = false;
        Variables.pressRight = false;
        Variables.pressAttack = false;
        Variables.pickUpItem = false;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        switch (Variables.Game_State) {
            case Variables.Game_State_Login:
                if (Variables.inputID) {
                    String key = Character.toString(character);
                    int keyInt = (int)character;
                    if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
                        if (Variables.Login_ID != null && !Variables.Login_ID.isEmpty()) {
                            Variables.Login_ID = Variables.Login_ID.substring(0, Variables.Login_ID.length() - 1);
                        }
                    } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                        if (!Variables.inputID) {
                            Variables.inputID = false;
                            Variables.inputPW = true;
                        }
                    } else {
                        if (key != null) {
                            if (keyInt > 0) {
                                Variables.Login_ID = Variables.Login_ID + key;
                            }
                        }
                    }
                }
                if (Variables.inputPW) {
                    String key = Character.toString(character);
                    int keyInt = (int)character;
                    if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
                        if (Variables.Login_PW != null && !Variables.Login_PW.isEmpty()) {
                            Variables.Login_PW = Variables.Login_PW.substring(0, Variables.Login_PW.length() - 1);
                        }
                    } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                        if (!Variables.inputPW) {
                            Variables.inputPW = false;
                            SendServerData.SendLogin(Variables.MyIndex);
                        }
                    } else {
                        if (key != null) {
                            if (keyInt > 0) {
                                Variables.Login_PW = Variables.Login_PW + key;
                            }
                        }
                    }
                }
                break;
            case Variables.Game_State_CharCreate:
                if (Variables.inputName) {
                    String key = Character.toString(character);
                    int keyInt = (int)character;
                    if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
                        if (Variables.TempName != null && !Variables.TempName.isEmpty()) {
                            Variables.TempName = Variables.TempName.substring(0, Variables.TempName.length() - 1);
                        }
                    } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                        if (!Variables.inputName) {
                            Variables.inputName = false;
                        }
                    } else {
                        if (key != null) {
                            if (keyInt > 0) {
                                if (Variables.TempName == null) { Variables.TempName = ""; }
                                Variables.TempName = Variables.TempName + key;
                            }
                        }
                    }
                }
                break;
            case Variables.Game_State_InGame:
                if (Variables.inChat) {
                    String key = Character.toString(character);
                    int keyInt = (int)character;
                    if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
                        if (Variables.chatInput != null && !Variables.chatInput.isEmpty()) {
                            Variables.chatInput = Variables.chatInput.substring(0, Variables.chatInput.length() - 1);
                        }
                    } else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                        if (Variables.chatInput != null && !Variables.chatInput.isEmpty()) {
                            SendClientData.SendMessage();
                        }
                    } else {
                        if (key != null) {
                            if (keyInt > 0) {
                                Variables.chatInput = Variables.chatInput + key;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            switch (Variables.Game_State) {
                case Variables.Game_State_TitleScreen:
                    AndroidInputData.inputTileScreen();
                    break;
                case Variables.Game_State_Login:
                    AndroidInputData.inputLogin(screenX, screenY);
                    break;
                case Variables.Game_State_CharSelect:
                    AndroidInputData.inputCharSelect(screenX, screenY);
                    break;
                case Variables.Game_State_CharCreate:
                    AndroidInputData.inputCharCreate(screenX, screenY);
                    break;
                case Variables.Game_State_InGame:
                    if (Variables.touchDown) {
                        AndroidInputData.inputInGame(screenX, screenY);
                        Variables.touchDown = true;
                    }
                    break;
            }
        } else if (Variables.Client_Mode == Variables.Client_Mode_Desktop) {
            switch (Variables.Game_State) {
                case Variables.Game_State_TitleScreen:
                    DesktopInputData.inputTileScreen();
                    break;
                case Variables.Game_State_Login:
                    DesktopInputData.inputLogin(screenX, screenY);
                    break;
                case Variables.Game_State_CharSelect:
                    DesktopInputData.inputCharSelect(screenX, screenY);
                    break;
                case Variables.Game_State_CharCreate:
                    DesktopInputData.inputCharCreate(screenX, screenY);
                    break;
                case Variables.Game_State_InGame:
                    Variables.touchDown = true;
                    DesktopInputData.inputInGame(screenX, screenY);
                    break;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        switch (Variables.Game_State) {
            case Variables.Game_State_InGame:
                Variables.dPad_Up = false;
                Variables.dPad_Down = false;
                Variables.dPad_Left = false;
                Variables.dPad_Right = false;
                Variables.aBtn = false;
                Variables.bBtn = false;
                Variables.pressUp = false;
                Variables.pressDown = false;
                Variables.pressLeft = false;
                Variables.pressRight = false;
                Variables.pressAttack = false;
                Variables.pickUpItem = false;
                Variables.longPress = false;
                Variables.touchDown = false;
                Variables.longPressTimer = 0;
                break;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        switch (Variables.Game_State) {
            case Variables.Game_State_InGame:
                Variables.touchDown = true;
                AndroidInputData.inputInGame(screenX, screenY);
                break;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
