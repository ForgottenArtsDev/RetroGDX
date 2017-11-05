package com.forgottenartsstudios.gameworld;

import com.forgottenartsstudios.helpers.Variables;

/**
 * Created by forgo on 10/6/2017.
 */

public class GameWorld {
    public GameWorld(int midPointY) {
        if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            Variables.Game_State = Variables.Game_State_TitleScreen;
        } else if (Variables.Client_Mode == Variables.Client_Mode_Android) {
            Variables.Game_State = Variables.Game_State_Loading;
        }
    }

    public void update(float delta) {

    }
}
