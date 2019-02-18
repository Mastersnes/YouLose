package com.bebel.youlose.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
    public abstract boolean refresh();

    public <ACTOR extends Actor> ACTOR putActor(final ACTOR actor) {
        super.addActor(actor);
        return actor;
    }

    /**
     * Centre l'element horizontalement
     * @param element
     * @return
     */
    protected float centerX(final Actor element) {
        return getWidth()/2 - element.getWidth()/2;
    }

    /**
     * Centre l'element verticalement
     * @param element
     * @return
     */
    protected float centerY(final Actor element) {
        return getHeight()/2 - element.getHeight()/2;
    }

    /**
     * Permet d'ajouter des actions à la suite
     * @param actions
     * @return
     */
    protected boolean addActions(final Action... actions) {
        addAction(Actions.sequence(actions));
        return true;
    }
}
