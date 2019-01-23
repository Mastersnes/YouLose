package com.bebel.youlose;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.manager.ScreensManager;
import com.bebel.youlose.menu.MenuScreen;

public class LaunchGame extends Game {
	public SpriteBatch batch;
	public AssetsManager manager;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Logger.DEBUG);
		batch = new SpriteBatch();
		manager = new AssetsManager();
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
		manager.dispose();
		batch.dispose();
	}
}
