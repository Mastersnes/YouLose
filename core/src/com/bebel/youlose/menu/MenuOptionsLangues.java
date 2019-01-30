package com.bebel.youlose.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.actors.AbstractGroup;
import com.bebel.youlose.components.actors.ButtonActor;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.manager.AssetsManager;

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

        es = putActor(new ButtonActor("es.png", manager));
        es.move(127, 180);

        fr = putActor(new ButtonActor("fr.png", manager));
        fr.move(centerX(fr) - 10, 180);

        en = putActor(new ButtonActor("en.png", manager));
        en.move(145, 180, top | right);
    }

    public void makeEvents() {
        fr.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manager.setLanguage("fr", true);
                return parent.refresh();
            }
        });
        en.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manager.setLanguage("en", true);
                return parent.refresh();
            }
        });
        es.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manager.setLanguage("esperanto", true);
                return parent.refresh();
            }
        });
    }

    @Override
    public boolean refresh() {
        return true;
    }
}
