package com.bebel.youlose.menu.slots;

import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.manager.resources.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

/**
 * Acteur representant un slot
 */
public class SlotActor extends AbstractGroup {
    private final MenuScreen parent;

    private final SlotActor slot1;
    private final SlotActor slot2;
    private final SlotActor slot3;

    public SlotActor(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        this.parent = parent;
        setVisible(false);
        manager.setContext("menu");

        putActor(slot1 = new SlotActor());

        refresh();
    }
}
