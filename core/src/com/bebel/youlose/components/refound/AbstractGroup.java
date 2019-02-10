package com.bebel.youlose.components.refound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.SnapshotArray;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.components.interfaces.Clickable;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.manager.AssetsManager;

import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Abstraction de group
 */
public abstract class AbstractGroup extends Group implements Movable, Actionnable {
    protected final AssetsManager manager;
    public Actor toDebug;

    /**
     * Le group est par defaut de la taille de l'ecran
     * @param manager
     */
    public AbstractGroup(final AssetsManager manager) {
        this.manager = manager;
        setTouchable(Touchable.childrenOnly);
        setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
    }

    /**
     * Permet d'ajouter un acteur et de le retourner
     * @param actor
     * @param <ACTOR>
     * @return
     */
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
     * Renvoit le manager de ressource
     * @return
     */
    public AssetsManager getManager() {
        return manager;
    }

    /**
     * Permet de raffraichir les acteurs conditionnées par la langue
     * @return
     */
    public abstract boolean refresh();

    /**
     * Stop les actions du groupe
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

    @Override
    public void act(float delta) {
        super.act(delta);

        if (toDebug != null) {
            boolean move = false;
            if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
                toDebug.moveBy(0, 1);
                move = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
                toDebug.moveBy(-1, 0);
                move = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                toDebug.moveBy(0, -1);
                move = true;
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                toDebug.moveBy(1, 0);
                move = true;
            }

            if (move) {
                float y = getHeight() - toDebug.getHeight() - toDebug.getY();
                Gdx.app.debug("MOVE", toDebug.getX() + ", " + y);
            }
        }
    }
}