package com.bebel.youlose.components.refound.abstrait;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.interfaces.Eventable;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.manager.AssetsManager;

import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Abstraction de group
 */
public abstract class AbstractGroup extends Group implements Refreshable, Eventable {
    protected final AssetsManager manager;
    protected Actor toDebug;

    /**
     * Le groupe est par defaut de la taille de l'ecran
     *
     * @param manager
     */
    public AbstractGroup(final AssetsManager manager) {
        this.manager = manager;
        setTouchable(Touchable.childrenOnly);
        setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    }

    /**
     * Permet d'ajouter un acteur et de le retourner
     *
     * @param actor
     * @param <ACTOR>
     * @return
     */
    public <ACTOR extends Actor> ACTOR putActor(final ACTOR actor) {
        super.addActor(actor);
        return actor;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        checkToDebug();
    }

    private void checkToDebug() {
        if (!isVisible()) return;
        if (toDebug == null || !toDebug.isTouchable()) return;

        boolean moved  = false;

        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            moved = true;
            toDebug.moveBy(0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            moved = true;
            toDebug.moveBy(-1, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moved = true;
            toDebug.moveBy(0, -1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moved = true;
            toDebug.moveBy(1, 0);
        }

        if (moved) {
            final float y = toDebug.getParent().getHeight() - toDebug.getY();
            Gdx.app.debug("MOVE", toDebug.getX() + ", " + y);
        }
    }

    @Override
    public boolean refresh() {
        for (final Actor actor : getChildren()) {
            if (actor instanceof Refreshable)
                ((Refreshable) actor).refresh();
        }
        return true;
    }

    @Override
    public void makeEvents() {
        for (final Actor actor : getChildren()) {
            if (actor instanceof Eventable)
                ((Eventable) actor).makeEvents();
        }
    }

    @Override
    public AssetsManager getManager() {
        return manager;
    }
}