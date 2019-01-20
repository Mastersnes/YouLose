package com.bebel.youlose.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bebel.youlose.LaunchGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "YouLose";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new LaunchGame(), config);
	}
}
