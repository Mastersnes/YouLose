package com.bebel.youlose.components.interfaces;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Represente une entitée pouvant etre actionnée
 */
public interface Refreshable {
    boolean refresh();
    AssetsManager getManager();
}