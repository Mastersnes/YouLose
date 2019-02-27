package com.bebel.youlose.menu.slots;

import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.manager.resources.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

/**
 * Acteur representant un slot
 */
public class SlotActor extends AbstractGroup {
    /**
     * Le groupe est par defaut de la taille de l'ecran
     *
     * @param manager
     */
    public SlotActor(AssetsManager manager) {
        super(manager);
    }
}
