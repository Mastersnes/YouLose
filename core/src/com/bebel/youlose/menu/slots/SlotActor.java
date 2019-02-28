package com.bebel.youlose.menu.slots;

import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;

/**
 * Acteur representant un slot
 */
public class SlotActor extends AbstractGroup {
    private final ImageActor slot;
    private final ImageActor grille;


    public SlotActor(final String image) {
        setName(image);
        putActor(slot = new ImageActor(image));
        putActor(grille = new ImageActor("slots/slots:grille"))
                .setVisible(false);

        setBounds(0,0,slot.getWidth(), slot.getHeight());
    }
}
