package com.bebel.youlose.components.refound.abstrait;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.interfaces.Eventable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.resources.AssetsManager;

import java.util.Arrays;
import java.util.List;

import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Template d'ecran
 */
public abstract class AbstractScreen extends Stage implements Screen, Eventable {
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
        manager.finishLoading(context());
        create();
        makeEvents();
        for (final String nextScreen : nextScreens()) {
            manager.loadContext(nextScreen);
        }
    }
    public abstract void create();

    public <ACTOR extends Actor> ACTOR putActor(final ACTOR actor) {
        super.addActor(actor);
        return actor;
    }

    public void refresh() {
        for (final Actor actor : getActors()) {
            if (actor instanceof Refreshable)
                ((Refreshable) actor).refresh(actor.getColor());
        }
    }

    @Override
    public void makeEvents() {
        for (final Actor actor : getActors()) {
            if (actor instanceof Eventable)
                ((Eventable) actor).makeEvents();
        }
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

    @Override
    public void dispose() {
        super.dispose();
        manager.unloadContext(context());
        Gdx.app.debug(context(), "DISPOSE");
    }

    protected abstract String context();
    protected List<String> nextScreens() {
        return Arrays.asList();
    }
}
