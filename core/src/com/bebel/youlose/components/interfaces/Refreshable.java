package com.bebel.youlose.components.interfaces;

import com.badlogic.gdx.graphics.Color;
import com.bebel.youlose.manager.resources.AssetsManager;

/**
 * Represente une entitée necessitant d'etre raffraichit
 */
public interface Refreshable {
    void refresh(final Color color);
    AssetsManager getManager();
}