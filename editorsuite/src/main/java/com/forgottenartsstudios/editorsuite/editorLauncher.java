package com.forgottenartsstudios.editorsuite;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Created by Perfekt on 8/31/2016.
 */
public class editorLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "RT Online - Editor Suite - Press 'T' for ToolBox, 'N' for NPC Editor, 'O' for Shop Editor, or 'I' for Item Editor";
        config.width = 448;
        config.height = 448;
        config.resizable = false;
        new LwjglApplication(new editorSuite(), config);
    }
}
