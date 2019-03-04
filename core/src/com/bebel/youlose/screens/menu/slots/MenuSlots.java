package com.bebel.youlose.screens.menu.slots;

import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.AnimatedActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.screens.menu.MenuScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.bebel.youlose.screens.menu.MenuScreen.Screens.GAME;

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

        slotGauche = new SlotActor("slots/slots:slot_gauche");
        slotCentre = new SlotActor("slots/slots:slot_milieu");
        slotDroite = new SlotActor("slots/slots:slot_droite");

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

        refresh();
    }

    @Override
    public void makeEvents() {
        super.makeEvents();

        slotGauche.onClick((x, y, pointer, button) -> {
            final AnimatedActor animation = parent.getBackground().getAnimationCote();
            addBlockedActions(
                    slotGauche.open(),
                    parent.getBackground().play(animation, false),
                    run(() -> parent.switchTo(GAME))
            );
        });
        slotGauche.onErase((x, y, pointer, button) -> {
            addBlockedActions(
                    slotGauche.close()
            );
        });

        slotCentre.onClick((x, y, pointer, button) -> {
            final AnimatedActor animation = parent.getBackground().getAnimationCentre();
            addBlockedActions(
                    slotCentre.open(),
                    parent.getBackground().play(animation, false),
                    run(() -> parent.switchTo(GAME))
            );
        });
        slotCentre.onErase((x, y, pointer, button) -> {
            addBlockedActions(
                    slotCentre.close()
            );
        });

        slotDroite.onClick((x, y, pointer, button) -> {
            final AnimatedActor animation = parent.getBackground().getAnimationCote();
            addBlockedActions(
                    slotDroite.open(),
                    parent.getBackground().play(animation, true),
                    run(() -> parent.switchTo(GAME))
            );
        });
        slotDroite.onErase((x, y, pointer, button) -> {
            addBlockedActions(
                    slotDroite.close()
            );
        });
    }
}
