package com.bebel.youlose.screens.menu.options;

import com.badlogic.gdx.graphics.Color;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.screens.menu.MenuScreen;

import static com.badlogic.gdx.utils.Align.topRight;
import static com.bebel.youlose.utils.Constantes.*;

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
        refresh(getColor());
    }

    public void makeSpecificEvents() {
        fr.onClick((x, y, button, pointer) -> {
            manager.reload(FR_LANGUAGE);
            parent.refresh();
        });

        en.onClick((x, y, button, pointer) -> {
            manager.reload(EN_LANGUAGE);
            parent.refresh();
        });

        eo.onClick((x, y, button, pointer) -> {
            manager.reload(EO_LANGUAGE);
            parent.refresh();
        });
    }

    @Override
    public void refresh(final Color color) {
        final String language = manager.conf.getLanguage();

        fr.refresh(color);
        fr.setDisable(!FR_LANGUAGE.equals(language));

        en.refresh(color);
        en.setDisable(!EN_LANGUAGE.equals(language));

        eo.refresh(color);
        eo.setDisable(!EO_LANGUAGE.equals(language));
    }
}
