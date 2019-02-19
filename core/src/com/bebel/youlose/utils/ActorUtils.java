package com.bebel.youlose.utils;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.utils.Align.*;
import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Outils pour les acteurs
 */
public class ActorUtils {
    public synchronized static <ACTOR extends Actor> ACTOR move(final ACTOR actor, final float x, final float y) {
        return move(actor, x, y, top | left);
    }
    public synchronized static <ACTOR extends Actor> ACTOR move(final ACTOR actor, final float x, final float y, final int align) {
        float x2 = x; float y2 = y;
        float parentWidth = actor.getParent() != null ? actor.getParent().getWidth() : WORLD_WIDTH;
        float parentHeight = actor.getParent() != null ? actor.getParent().getHeight() : WORLD_HEIGHT;

        if ((align & right) != 0) x2 = parentWidth - actor.getWidth() - x;
        if ((align & top) != 0) y2 = parentHeight - actor.getHeight() - y;
        actor.setPosition(x2, y2);

        return actor;
    }

    /**
     * Ajoute une suite d'actions
     * @param actor
     * @param actions
     */
    public synchronized static boolean addActions(final Actor actor, final Action... actions) {
        actor.addAction(Actions.sequence(actions));
        return true;
    }

    /**
     * Ajoute une suite d'actions durant laquel l'interaction est interdite
     * @param actions
     */
    public synchronized  static boolean addBlockedActions(final Actor actor, final Action... actions) {
        actor.setTouchable(Touchable.disabled);
        final List<Action> listAction = new ArrayList<>(Arrays.asList(actions));
        listAction.add(run(() -> actor.setTouchable(Touchable.enabled)));

        addActions(actor, listAction.toArray(new Action[listAction.size()]));
        return true;
    }

    /**
     * Stop les actions de l'acteur
     * @param actor
     */
    public synchronized static void stop(final Actor actor) {
        if (actor instanceof Group) {
            for (final Actor child : ((Group) actor).getChildren()) {
                stop(child);
            }
        }
        for (final Action action : actor.getActions()) {
            actor.removeAction(action);
        }
    }

    public synchronized static float centerX(final Actor actor) {
        final float pw = actor.getParent() != null ? actor.getParent().getWidth() : WORLD_WIDTH;
        return pw / 2 - actor.getWidth() / 2;
    }
    public synchronized static float centerY(final Actor actor) {
        final float ph = actor.getParent() != null ? actor.getParent().getHeight() : WORLD_HEIGHT;
        return ph / 2 - actor.getHeight() / 2;
    }
}