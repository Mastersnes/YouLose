package com.bebel.youlose.menu.slots;

import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.manager.resources.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

/**
 * Ecran des slots
 */
public class MenuSlots extends AbstractGroup {
    private final MenuScreen parent;

    public MenuSlots(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        this.parent = parent;
        setVisible(false);
        manager.setContext("menu");

        addActor(new ImageActor(manager, "slots/slots:fond"));

        putActor(new ImageActor(manager, "slots/slots:slot_gauche"))
        .move(50, 50);

        refresh();
    }
}
