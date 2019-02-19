package com.bebel.youlose.menu.options;

import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ButtonActor;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;

import static com.badlogic.gdx.utils.Align.right;
import static com.badlogic.gdx.utils.Align.top;
import static com.bebel.youlose.utils.ActorUtils.centerX;
import static com.bebel.youlose.utils.ActorUtils.move;
import static com.bebel.youlose.utils.EventUtils.onClick;

public class MenuOptionsLangues extends AbstractGroup {
    private final MenuScreen parent;
    private final ButtonActor fr;
    private final ButtonActor en;
    private final ButtonActor eo;

    public MenuOptionsLangues(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        this.parent = parent;

        eo = putActor(new ButtonActor(manager, "lang-button/eo.png"));
        move(eo, 127, 0);

        fr = putActor(new ButtonActor(manager, "lang-button/fr.png"));
        move(fr, centerX(fr) - 10, 0);

        en = putActor(new ButtonActor(manager, "lang-button/en.png"));
        move(en, 145, 0, top | right);
        refresh();
    }

    public void makeEvents() {
        onClick(fr, (x, y, button, pointer) -> {
            manager.reload("fr");
            parent.refresh();
        });

        onClick(en, (x, y, button, pointer) -> {
            manager.reload("en");
            parent.refresh();
        });

        onClick(eo, (x, y, button, pointer) -> {
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
