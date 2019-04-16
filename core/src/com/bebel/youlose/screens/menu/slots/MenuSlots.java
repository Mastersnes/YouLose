package com.bebel.youlose.screens.menu.slots;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.manager.save.SaveManager;
import com.bebel.youlose.screens.menu.MenuScreen;
import com.bebel.youlose.screens.menu.MenuSubscreen;
import com.bebel.youloseClient.enums.SaveType;

import static com.bebel.youloseClient.enums.SaveType.*;

/**
 * Ecran des slots
 */
public class MenuSlots extends MenuSubscreen {
    private SlotActor slotGauche;
    private SlotActor slotCentre;
    private SlotActor slotDroite;

    public MenuSlots(final MenuScreen parent) {
        super(parent);
    }

    @Override
    public void create() {
        setVisible(false);

        slotGauche = new SlotActor(this, "slots/slots:slot_gauche", SaveManager.getInstance().get(GAUCHE));
        slotCentre = new SlotActor(this, "slots/slots:slot_milieu", SaveManager.getInstance().get(CENTRE));
        slotDroite = new SlotActor(this, "slots/slots:slot_droite", SaveManager.getInstance().get(DROITE));

        addActor(slotGauche.getNoir());
        addActor(slotGauche.getGrille());

        addActor(slotCentre.getNoir());
        addActor(slotCentre.getGrille());

        addActor(slotDroite.getNoir());
        addActor(slotDroite.getGrille());

        addActor(new ImageActor("slots/slots:fond"));

        putActor(slotGauche);
        putActor(slotCentre);
        putActor(slotDroite);
    }

    @Override
    public void startSubscreen() {
        slotGauche.move(117, 257);
        slotGauche.getNoir().move(112, 246);
        slotGauche.getGrille().move(134, 298);

        slotCentre.move(395, 257);
        slotCentre.getNoir().move(386, 245);
        slotCentre.getGrille().move(408, 298);

        slotDroite.move(679, 259);
        slotDroite.getNoir().move(665, 248);
        slotDroite.getGrille().move(687, 298);

        refresh(getColor());
    }

    @Override
    public void makeSpecificEvents() {
    }

    @Override
    public void setTouchable(Touchable touchable) {
        super.setTouchable(touchable);
        if (slotGauche != null) slotGauche.setTouchable(touchable);
        if (slotCentre != null) slotCentre.setTouchable(touchable);
        if (slotDroite != null) slotDroite.setTouchable(touchable);
    }
}
