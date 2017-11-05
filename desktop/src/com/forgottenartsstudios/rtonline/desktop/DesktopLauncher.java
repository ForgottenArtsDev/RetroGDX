package com.forgottenartsstudios.rtonline.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.forgottenartsstudios.helpers.Variables;
import com.forgottenartsstudios.rtonline.RTOnline;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Retro Tales Online";
		config.width = Variables.DesktopScreenWidth;
		config.height = Variables.DesktopScreenHeight;
		config.resizable = false;
		Variables.Client_Mode = Variables.Client_Mode_Desktop;
		new LwjglApplication(new RTOnline(), config);
	}
}
