package com.bebel.youlose;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.bebel.youlose.scenes.SceneManager;
import com.bebel.youlose.scenes.Scenes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LaunchGame extends ApplicationAdapter {
	@Override
	public void create () {
		SceneManager.getInstance().switchTo(Scenes.MENU);
	}

	@Override
	public void render () {
		SceneManager.getInstance().getCurrentScene().render();
	}

	@Override
	public void dispose () {
		SceneManager.getInstance().getCurrentScene().dispose();
	}
}
