package com.bebel.youlose.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Menu extends AbstractScene {
    @Override
    public void createScene() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void makeEvents(final float delta) {
        if (Gdx.input.isTouched()) {
            SceneManager.getInstance().switchTo(Scenes.GAME);
        }
    }

    @Override
    public void disposeScene() {
    }
}
