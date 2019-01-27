package com.bebel.youlose.components.abstrait.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.bebel.youlose.components.abstrait.actions.Actionnable;
import com.bebel.youlose.menu.MenuBackground;

import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;
import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

/**
 * Abstraction de group
 */
public abstract class AbstractGroup extends Group implements Movable, Actionnable {
    public AbstractGroup() {
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
}