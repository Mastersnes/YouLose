package com.bebel.youlose.components.refound.abstrait;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.interfaces.Eventable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.resources.AssetsManager;
import com.bebel.youlose.utils.ActorUtils;
import com.bebel.youlose.utils.IActor;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys.*;
import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Template de group
 */
public abstract class AbstractGroup extends Group implements Refreshable, Eventable, IActor {
    protected final AssetsManager manager;
    private List<Actor> debugList = new ArrayList<>();

    /**
     * Le groupe est par defaut de la taille de l'ecran
     *
     */
    public AbstractGroup() {
        this.manager = AssetsManager.getInstance();
        setTouchable(Touchable.childrenOnly);
        setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    }

    public <ACTOR extends Actor> ACTOR putActor(final ACTOR actor) {
        super.addActor(actor);
        return actor;
    }

    @Override
    public void refresh() {
        for (final Actor actor : getChildren()) {
            if (actor instanceof Refreshable)
                ((Refreshable) actor).refresh();
        }
    }

    @Override
    public void makeEvents() {
        for (final Actor actor : getChildren()) {
            if (actor instanceof Eventable)
                ((Eventable) actor).makeEvents();
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        actDebug();
    }

    public void addDebug(final Actor... actors) {
        for (final Actor actor : actors) {
            debugList.add(actor.debug());
        }
    }
    private void actDebug() {
        if (debugList.isEmpty()) return;
        int debugX=0, debugY=0;
        if (Gdx.input.isKeyPressed(UP)) debugY=1;
        else if (Gdx.input.isKeyPressed(LEFT)) debugX=-1;
        else if (Gdx.input.isKeyPressed(DOWN)) debugY=-1;
        else if (Gdx.input.isKeyPressed(RIGHT)) debugX=1;

        if (debugX + debugY != 0) {
            for (final Actor toDebug : debugList) {
                toDebug.moveBy(debugX, debugY);
                float parentHeight = toDebug.getParent() != null ? toDebug.getParent().getHeight() : WORLD_HEIGHT;
                float y = parentHeight - toDebug.getHeight() - toDebug.getY();
                Gdx.app.debug("MOVE:"+toDebug.getName(), toDebug.getX() + ", " + y);
            }
        }
    }

    @Override
    public AssetsManager getManager() {
        return manager;
    }

    //-- ActorUtils
    @Override
    public <T extends Actor> T move(final float x, final float y) {
        return (T) ActorUtils.move(this, x, y);
    }
    @Override
    public <T extends Actor> T move(final float x, final float y, final int align) {
        return (T) ActorUtils.move(this, x, y, align);
    }
    @Override
    public <T extends Actor> T setAlpha(int alpha) {
        return (T) ActorUtils.setAlpha(this, alpha);
    }
    @Override
    public float getAlpha() {
        return ActorUtils.getAlpha(this);
    }
    @Override
    public boolean addActions(final Action... actions) {
        return ActorUtils.addActions(this, actions);
    }
    @Override
    public boolean addBlockedActions(final Action... actions) {
        return ActorUtils.addBlockedActions(this, actions);
    }
    @Override
    public void stop() {
        ActorUtils.stop(this);
    }
    @Override
    public float centerX() {
        return ActorUtils.centerX(this);
    }
    @Override
    public float centerY() {
        return ActorUtils.centerY(this);
    }
}