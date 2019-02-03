package com.bebel.youlose.menu.options;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private final ButtonActor es;

    public MenuOptionsLangues(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        this.parent = parent;

        es = putActor(new ButtonActor(manager, "es.png"));
        es.move(127, 180);

        fr = putActor(new ButtonActor(manager, "fr.png"));
        fr.move(centerX(fr) - 10, 180);

        en = putActor(new ButtonActor(manager, "en.png"));
        en.move(145, 180, top | right);
        refresh();
    }

    public void makeEvents() {
        fr.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manager.langue.setLanguage("fr", true);
                parent.switchTo(MenuScreen.Screens.MENU);
                return parent.refresh();
            }
        });
        en.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manager.langue.setLanguage("en", true);
                parent.switchTo(MenuScreen.Screens.MENU);
                return parent.refresh();
            }
        });
        es.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manager.langue.setLanguage("eo", true);
                parent.switchTo(MenuScreen.Screens.MENU);
                return parent.refresh();
            }
        });
    }

    @Override
    public boolean refresh() {
        final String language = manager.langue.getLanguage();
        fr.setDisabled(!"fr".equals(language));
        en.setDisabled(!"en".equals(language));
        es.setDisabled(!"eo".equals(language));
        return true;
    }
}
