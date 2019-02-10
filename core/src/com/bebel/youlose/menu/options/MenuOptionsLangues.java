package com.bebel.youlose.menu.options;

import com.bebel.youlose.components.refound.AbstractGroup;
import com.bebel.youlose.components.refound.ButtonActor;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

import static com.badlogic.gdx.utils.Align.right;
import static com.badlogic.gdx.utils.Align.top;

public class MenuOptionsLangues extends AbstractGroup implements Actionnable {
    private final MenuScreen parent;
    private final ButtonActor fr;
    private final ButtonActor en;
    private final ButtonActor eo;

    public MenuOptionsLangues(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        this.parent = parent;

        eo = putActor(new ButtonActor(manager, "lang-button/eo.png"));
        eo.move(127, 0);

        fr = putActor(new ButtonActor(manager, "lang-button/fr.png"));
        fr.move(centerX(fr) - 10, 0);

        en = putActor(new ButtonActor(manager, "lang-button/en.png"));
        en.move(145, 0, top | right);
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
    public boolean refresh() {
        final String language = manager.conf.getLanguage();
        fr.setDisabled(!"fr".equals(language));
        en.setDisabled(!"en".equals(language));
        eo.setDisabled(!"eo".equals(language));
        return true;
    }
}
