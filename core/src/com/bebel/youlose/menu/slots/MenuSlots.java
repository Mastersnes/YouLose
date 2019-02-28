package com.bebel.youlose.menu.slots;

import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.menu.MenuScreen;

/**
 * Ecran des slots
 */
public class MenuSlots extends AbstractGroup {
    private final MenuScreen parent;

    private final SlotActor slotGauche;
    private final SlotActor slotCentre;
    private final SlotActor slotDroite;

    public MenuSlots(final MenuScreen parent) {
        super();
        this.parent = parent;
        setVisible(false);
        manager.setContext("menu");

        addActor(new ImageActor("slots/slots:fond"));
        addActor(new ImageActor("slots/ref.png"));

        putActor(slotGauche = new SlotActor("slots/slots:slot_gauche"))
            .move(113, 256);
        putActor(slotCentre = new SlotActor("slots/slots:slot_milieu"))
            .move(389, 248);
        putActor(slotDroite = new SlotActor("slots/slots:slot_droite"))
            .move(674, 256);

        addDebug(slotDroite);

        refresh();
    }
}
