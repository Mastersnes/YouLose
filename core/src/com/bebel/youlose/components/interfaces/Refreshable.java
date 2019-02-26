package com.bebel.youlose.components.interfaces;

import com.bebel.youlose.manager.resources.AssetsManager;

/**
 * Represente une entitée sensible au language et necessitant d'etre raffraichit
 */
public interface Refreshable {
    void refresh();
    AssetsManager getManager();
}