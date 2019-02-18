package com.bebel.youlose.components.refound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.SnapshotArray;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.manager.AssetsManager;

import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Abstraction de group
 */
public abstract class AbstractActor extends Actor {
    protected final AssetsManager manager;

    /**
     * L'acteur est par defaut de la taille de l'ecran
     * @param manager
     */
    public AbstractActor(final AssetsManager manager) {
        this.manager = manager;
        setTouchable(Touchable.enabled);
        setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    }

    /**
     * Centre l'element horizontalement
     * @return
     */
    protected float centerX() {
        float parentWidth = getParent() == null ? WORLD_WIDTH : getParent().getWidth();
        return parentWidth/2 - getWidth()/2;
    }

    /**
     * Centre l'element verticalement
     * @return
     */
    protected float centerY() {
        float parentHeight = getParent() == null ? WORLD_HEIGHT : getParent().getHeight();
        return parentHeight/2 - getHeight()/2;
    }

    /**
     * Renvoit le manager de ressource
     * @return
     */
    public AssetsManager getManager() {
        return manager;
    }

    /**
     * Stop les actions du
     */
    public void stop() {
        stop(this);
    }

    /**
     * Stop les actions des acteurs transmis
     * @param listActor
     */
    public void stop(final SnapshotArray<Actor> listActor) {
        for (final Actor actor : listActor) {
            stop(actor);
        }
    }

    /**
     * Stop les actions de l'acteur
     * @param actor
     */
    public void stop(final Actor actor) {
        if (actor instanceof Group) stop(((Group) actor).getChildren());
        for (final Action action : actor.getActions()) {
            actor.removeAction(action);
        }
    }

    public void setDebug(final Actor toDebug) {
        toDebug.setDebug(true);
        toDebug.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.Z :
                        toDebug.moveBy(0, 1);
                        break;
                    case Input.Keys.Q :
                        toDebug.moveBy(-1, 0);
                        break;
                    case Input.Keys.S :
                        toDebug.moveBy(0, -1);
                        break;
                    case Input.Keys.D :
                        toDebug.moveBy(1, 0);
                        break;
                }

                float y = getHeight() - toDebug.getHeight() - toDebug.getY();
                Gdx.app.debug("MOVE", toDebug.getX() + ", " + y);

                return true;
            }
        });
    }
}