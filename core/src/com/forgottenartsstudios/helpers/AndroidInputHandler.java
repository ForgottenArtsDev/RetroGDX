package com.forgottenartsstudios.helpers;

import com.badlogic.gdx.input.GestureDetector;

/**
 * Created by forgo on 2/2/2018.
 */

public class AndroidInputHandler extends GestureDetector {

    public AndroidInputHandler(GestureListener listener) {
        super(listener);
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
