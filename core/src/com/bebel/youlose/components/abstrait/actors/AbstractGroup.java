package com.bebel.youlose.components.abstrait.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.bebel.youlose.components.abstrait.actions.Actionnable;

/**
 * Abstraction de group
 */
public abstract class AbstractGroup extends Group implements Movable, Actionnable {
    public <ACTOR extends SpriteActor> ACTOR addActor(final ACTOR actor) {
        super.addActor(actor);
        return actor;
    }
}