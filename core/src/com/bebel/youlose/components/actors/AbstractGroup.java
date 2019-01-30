package com.bebel.youlose.components.actors;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.SnapshotArray;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.manager.AssetsManager;

import java.util.List;

import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Abstraction de group
 */
public abstract class AbstractGroup extends Group implements Movable, Actionnable {
    protected final AssetsManager manager;

    public AbstractGroup(final AssetsManager manager) {
        this.manager = manager;
        setTouchable(Touchable.childrenOnly);
        setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    }

    public <ACTOR extends Actor> ACTOR putActor(final ACTOR actor) {
        super.addActor(actor);
        return actor;
    }

    protected float centerX(final Actor element) {
        return getWidth()/2 - element.getWidth()/2;
    }
    protected float centerY(final Actor element) {
        return getHeight()/2 - element.getHeight()/2;
    }

    public AssetsManager getManager() {
        return manager;
    }

    /**
     * Permet de raffraichir les acteurs conditionnées par la langue
     * @return
     */
    public abstract boolean refresh();

    public void stop() {
        stop(this);
    }

    public void stop(final SnapshotArray<Actor> listActor) {
        for (final Actor actor : listActor) {
            stop(actor);
        }
    }

    public void stop(final Actor actor) {
        if (actor instanceof Group) stop(((Group) actor).getChildren());
        for (final Action action : actor.getActions()) {
            actor.removeAction(action);
        }
    }
}