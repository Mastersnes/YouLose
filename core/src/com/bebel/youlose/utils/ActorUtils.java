package com.bebel.youlose.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;

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
    public synchronized static <T extends Actor> T move(final T actor, final float x, final float y) {
        return move(actor, x, y, topLeft);
    }
    public synchronized static <T extends Actor> T move(final T actor, final float x, final float y, final int align) {
        float x2 = x; float y2 = y;
        float parentW = WORLD_WIDTH, parentH = WORLD_HEIGHT;
        final Group parent = actor.getParent();
        if (parent != null) {
            parentW = parent.getWidth() * parent.getScaleX();
            parentH = parent.getHeight() * parent.getScaleY();
        }

        float actorW = actor.getWidth() * actor.getScaleX();
        float actorH = actor.getHeight() * actor.getScaleY();

        if ((align & right) != 0) x2 = parentW - actorW - x;
        if ((align & top) != 0) y2 = parentH - actorH - y;
        actor.setPosition(x2, y2);

        return actor;
    }

    public synchronized static <T extends Actor> T setAlpha(final T actor, final int alpha) {
        actor.getColor().a = alpha;
        return actor;
    }
    public synchronized static <T extends Actor> float getAlpha(final T actor) {
        return actor.getColor().a;
    }

    public synchronized static <T extends Actor> void setColor(final T actor, final Color color) {
        actor.setColor(color.r, color.g, color.b, actor.getColor().a);
    }

    public synchronized static boolean addActions(final Actor actor, final Action... actions) {
        actor.addAction(Actions.sequence(actions));
        return true;
    }

    public synchronized  static boolean addBlockedActions(final Actor actor, final Action... actions) {
        actor.setTouchable(Touchable.disabled);
        final List<Action> listAction = new ArrayList<>(Arrays.asList(actions));
        listAction.add(run(() ->  {
            if (actor instanceof AbstractGroup || actor instanceof Group)
                actor.setTouchable(Touchable.childrenOnly);
            else actor.setTouchable(Touchable.enabled);
        }));

        addActions(actor, listAction.toArray(new Action[listAction.size()]));
        return true;
    }

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

    public synchronized static void hide(final Actor... actors) {
        for (final Actor actor : actors) {
            actor.setVisible(false);
        }
    }
}
