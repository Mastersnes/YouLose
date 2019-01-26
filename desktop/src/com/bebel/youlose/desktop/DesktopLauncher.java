package com.bebel.youlose.desktop;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bebel.youlose.LaunchGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		final Graphics.DisplayMode displayMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
		config.setFromDisplayMode(displayMode);
		config.title = "YouLose";
		config.vSyncEnabled = true;
		new LwjglApplication(new LaunchGame(), config);
	}
}
