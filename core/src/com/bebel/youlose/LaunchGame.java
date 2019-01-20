package com.bebel.youlose;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.manager.ScreensManager;
import com.bebel.youlose.menu.MenuScreen;

public class LaunchGame extends Game {
	public SpriteBatch batch;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Logger.DEBUG);
		batch = new SpriteBatch();
		AssetsManager.getInstance();
		ScreensManager.init(this);
		ScreensManager.getInstance().switchTo(MenuScreen.class);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		if (getScreen() != null)
			getScreen().dispose();
		AssetsManager.getInstance().dispose();
		batch.dispose();
	}
}
