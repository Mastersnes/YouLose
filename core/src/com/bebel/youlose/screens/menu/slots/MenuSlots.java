package com.bebel.youlose.screens.menu.slots;

import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.manager.save.SaveManager;
import com.bebel.youlose.screens.menu.MenuScreen;

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

        slotGauche = new SlotActor("slots/slots:slot_gauche", SaveManager.getInstance().getGauche());
        slotCentre = new SlotActor("slots/slots:slot_milieu", SaveManager.getInstance().getCentre());
        slotDroite = new SlotActor("slots/slots:slot_droite", SaveManager.getInstance().getDroite());

        slotGauche.addNoir(this, 112, 246);
        slotCentre.addNoir(this, 386, 245);
        slotDroite.addNoir(this, 665, 248);

        slotGauche.addGrille(this, 134, 298);
        slotCentre.addGrille(this, 408, 298);
        slotDroite.addGrille(this, 687, 298);

        addActor(new ImageActor("slots/slots:fond"));

        putActor(slotGauche).move(117, 257);
        putActor(slotCentre).move(395, 257);
        putActor(slotDroite).move(679, 259);

        refresh(getColor());
    }

    @Override
    public void makeEvents() {
        super.makeEvents();
        slotGauche.makeEvents(parent);
        slotCentre.makeEvents(parent);
        slotDroite.makeEvents(parent);
    }
}
