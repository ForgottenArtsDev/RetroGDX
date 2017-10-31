package com.forgottenartsstudios.rtonline;

import android.os.Bundle;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.forgottenartsstudios.helpers.Variables;
import com.forgottenartsstudios.rtonline.RTOnline;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		Variables.Client_Mode = Variables.Client_Mode_Android;
		config.hideStatusBar = true;
		config.useImmersiveMode = true;
		initialize(new RTOnline(), config);
	}
}
