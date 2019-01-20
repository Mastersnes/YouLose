package com.bebel.youlose.scenes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Scene
 */
public abstract class AbstractScene extends ApplicationAdapter {
    protected final SpriteBatch batch;
    protected final OrthographicCamera camera;
    protected final Vector3 mousePosition;
    protected boolean ready;

    public AbstractScene() {
        ready = false;
        this.batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mousePosition = new Vector3();
    }

    @Override
    public void create() {
        createScene();
        ready = true;
    }
    public abstract void createScene();

    @Override
    public void render() {
        if (!ready) return;
        mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        render(Gdx.graphics.getDeltaTime());
        batch.end();

        makeEvents(Gdx.graphics.getDeltaTime());
    }

    public abstract void render(final float delta);
    public abstract void makeEvents(final float delta);

    @Override
    public void dispose() {
        batch.dispose();
        disposeScene();
    }

    public abstract void disposeScene();
}
