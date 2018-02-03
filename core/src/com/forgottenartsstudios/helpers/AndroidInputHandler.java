package com.forgottenartsstudios.helpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.forgottenartsstudios.data.InputAndroid;
import com.forgottenartsstudios.data.InputDesktop;
import com.forgottenartsstudios.gameworld.GameWorld;

/**
 * Created by forgo on 2/2/2018.
 */

public class AndroidInputHandler extends GestureDetector {

    public AndroidInputHandler(GestureListener listener) {
        super(listener);
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
    public boolean touchUp(float x, float y, int pointer, int button) {
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
        return super.touchUp(x, y, pointer, button);
    }
}
