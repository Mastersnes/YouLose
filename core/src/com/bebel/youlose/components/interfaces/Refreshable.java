package com.bebel.youlose.components.interfaces;

import com.bebel.youlose.manager.AssetsManager;

/**
 * Represente une entitée sensible au language et necessitant d'etre raffraichit
 */
public interface Refreshable {
    boolean refresh();
    AssetsManager getManager();
}