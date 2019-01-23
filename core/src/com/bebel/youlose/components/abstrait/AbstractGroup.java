package com.bebel.youlose.components.abstrait;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Abstraction de group
 */
public abstract class AbstractGroup extends Group {
    public <ACTOR extends SpriteActor> ACTOR addActor(final ACTOR actor) {
        super.addActor(actor);
        return actor;
    }
}