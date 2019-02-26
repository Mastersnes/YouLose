package com.bebel.youlose.components.refound.abstrait;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.interfaces.Eventable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.resources.AssetsManager;

import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Template d'ecran
 */
public abstract class AbstractScreen extends Stage implements Screen, Refreshable, Eventable {
    protected final LaunchGame parent;
    protected final AssetsManager manager;

    public AbstractScreen(final LaunchGame parent) {
        super(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT)), parent.batch);
        getRoot().setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        this.manager = parent.manager;
        this.parent = parent;
        createStage();
    }

    public void createStage() {
        create();
        makeEvents();
    }
    public abstract void create();

    public <ACTOR extends Actor> ACTOR putActor(final ACTOR actor) {
        super.addActor(actor);
        return actor;
    }

    @Override
    public void refresh() {
        for (final Actor actor : getActors()) {
            if (actor instanceof Refreshable)
                ((Refreshable) actor).refresh();
        }
    }

    @Override
    public void makeEvents() {
        for (final Actor actor : getActors()) {
            if (actor instanceof Eventable)
                ((Eventable) actor).makeEvents();
        }
    }

    @Override
    public AssetsManager getManager() {
        return manager;
    }

    //--Screen
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        act(Gdx.graphics.getDeltaTime());
        draw();
    }

    @Override
    public void resize(final int width, final int height) {
        getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
