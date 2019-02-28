package com.bebel.youlose.menu.options;

import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.menu.MenuScreen;

import static com.badlogic.gdx.utils.Align.topRight;

/**
 * Widget des langues
 */
public class MenuOptionsLangues extends AbstractGroup {
    private final MenuScreen parent;
    private final ButtonActor fr;
    private final ButtonActor en;
    private final ButtonActor eo;

    public MenuOptionsLangues(final MenuScreen parent) {
        super();
        this.parent = parent;

        eo = putActor(new ButtonActor("options/other:eo"))
            .move(127, 0);

        fr = putActor(new ButtonActor("options/other:fr"));
        fr.move(fr.centerX() - 10, 0);

        en = putActor(new ButtonActor("options/other:en"));
        en.move(145, 0, topRight);
        refresh();
    }

    public void makeEvents() {
        fr.onClick((x, y, button, pointer) -> {
            manager.reload("fr");
            parent.refresh();
        });

        en.onClick((x, y, button, pointer) -> {
            manager.reload("en");
            parent.refresh();
        });

        eo.onClick((x, y, button, pointer) -> {
            manager.reload("eo");
            parent.refresh();
        });
    }

    @Override
    public void refresh() {
        final String language = manager.conf.getLanguage();
        fr.setDisable(!"fr".equals(language));
        en.setDisable(!"en".equals(language));
        eo.setDisable(!"eo".equals(language));
    }
}
