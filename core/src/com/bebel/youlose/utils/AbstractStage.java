package com.bebel.youlose.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.manager.AssetsManager;

import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Template de stage
 */
public abstract class AbstractStage extends Stage {
    protected final LaunchGame parent;
    protected final AssetsManager manager;

    public AbstractStage(final LaunchGame parent) {
        super(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, new OrthographicCamera()), parent.batch);
        this.manager = parent.manager;
        this.parent = parent;
        createStage();
    }
    public void createStage() {
        create();
        makeEvents();
    }
    public abstract void create();
    public abstract void makeEvents();

    @Override
    public void draw() {
        beforeDraw();
        super.draw();
        afterDraw();
    }
    public void beforeDraw(){}
    public void afterDraw(){}

    @Override
    public void act(float delta) {
        beforeAct(delta);
        super.act(delta);
        afterAct(delta);
    }
    public void beforeAct(final float delta){}
    public abstract void afterAct(final float delta);

    public <ACTOR extends Actor> ACTOR putActor(final ACTOR actor) {
        super.addActor(actor);
        return actor;
    }
}
