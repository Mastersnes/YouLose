package com.bebel.youlose.components.refound.abstrait;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.interfaces.Eventable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.AssetsManager;

import java.sql.Ref;

import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Template de stage
 */
public abstract class AbstractStage extends Stage implements Refreshable, Eventable {
    protected final LaunchGame parent;
    protected final AssetsManager manager;

    public AbstractStage(final LaunchGame parent) {
        super(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, new OrthographicCamera()), parent.batch);
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
    public boolean refresh() {
        for (final Actor actor : getActors()) {
            if (actor instanceof Refreshable)
                ((Refreshable) actor).refresh();
        }
        return true;
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
}
