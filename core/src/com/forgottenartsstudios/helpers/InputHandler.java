package com.forgottenartsstudios.helpers;

import com.badlogic.gdx.InputProcessor;
import com.forgottenartsstudios.data.InputData;
import com.forgottenartsstudios.gameworld.GameWorld;

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
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        switch (Variables.Game_State) {
            case Variables.Game_State_TitleScreen:
                InputData.inputTileScreen();
                break;
            case Variables.Game_State_Login:
                InputData.inputLogin(screenX, screenY);
                break;
            case Variables.Game_State_CharSelect:
                InputData.inputCharSelect(screenX, screenY);
                break;
            case Variables.Game_State_CharCreate:
                InputData.inputCharCreate(screenX, screenY);
                break;
            case Variables.Game_State_InGame:
                Variables.touchDown = true;
                InputData.inputInGame(screenX, screenY);
                break;
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
                InputData.inputInGame(screenX, screenY);
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
