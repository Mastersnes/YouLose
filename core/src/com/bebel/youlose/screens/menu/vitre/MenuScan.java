package com.bebel.youlose.screens.menu.vitre;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.components.refound.event.ClickCatcher;
import com.bebel.youlose.screens.menu.MenuScreen;

import static com.badlogic.gdx.math.Interpolation.linear;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.bebel.youlose.screens.menu.MenuScreen.Screens.SLOT;

/**
 * Widget de scan du curseur
 */
public class MenuScan extends AbstractGroup {
    private final MenuScreen parent;

    private final ImageActor carre;
    private final ImageActor scan;

    public MenuScan(final MenuScreen parent) {
        super();
        this.parent = parent;
        manager.setContext("menu");

        putActor(carre = new ImageActor("vitre/atlas:carre"));
        setBounds(0, 0, carre.getWidth(), carre.getHeight());

        putActor(scan = new ImageActor("vitre/atlas:scan"))
            .move(scan.centerX(), 0)
            .setVisible(false);
        scan.setTouchable(Touchable.disabled);

        refresh();
    }

    @Override
    public void makeEvents() {
        final ClickCatcher clickCatcher = new ClickCatcher();
        carre.addListener(clickCatcher);
        clickCatcher.touchDown((x, y, pointer, button) -> {
            scan.setVisible(true);
            scan.addActions(
                    Actions.moveBy(0, -carre.getHeight()+13, 5, linear),
                    run(() -> parent.switchTo(SLOT))
            );
        });
        clickCatcher.touchUp((x, y, pointer, button) -> resetScan());
        clickCatcher.exit((x, y, pointer, button) -> resetScan());
    }

    /**
     * Repositionne le scan a sa position de depart
     */
    private void resetScan() {
        scan.stop();
        scan.setVisible(false);
        scan.move(scan.centerX(), 0);
    }
}
