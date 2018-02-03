package com.forgottenartsstudios.helpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.forgottenartsstudios.data.InputAndroid;
import com.forgottenartsstudios.data.InputDesktop;
import com.forgottenartsstudios.gameworld.GameWorld;

/**
 * Created by forgo on 2/2/2018.
 */

public class AndroidInputHandler implements GestureListener, InputProcessor {
    private GameWorld myWorld;

    public AndroidInputHandler(GameWorld myWorld) {
        this.myWorld = myWorld;
    }

    @Override
    public boolean touchDown(float screenX, float screenY, int pointer, int button) {
        if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            switch (Variables.Game_State) {
                case Variables.Game_State_TitleScreen:
                    InputAndroid.inputTileScreen();
                    break;
                case Variables.Game_State_Login:
                    InputAndroid.inputLogin(screenX, screenY);
                    break;
                case Variables.Game_State_CharSelect:
                    InputAndroid.inputCharSelect(screenX, screenY);
                    break;
                case Variables.Game_State_CharCreate:
                    InputAndroid.inputCharCreate(screenX, screenY);
                    break;
                case Variables.Game_State_InGame:
                    if (!Variables.touchDown) {
                        InputAndroid.inputInGame(screenX, screenY);
                    }
                    break;
            }
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (Variables.inInventory) {
            Variables.tapCount = count;
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        System.out.println("LONGPRESS!");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
                Variables.longPressTimer = 0;

                Variables.touchDown = false;
                break;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
