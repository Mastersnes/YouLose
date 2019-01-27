package com.bebel.youlose.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.I18NBundle;
import com.bebel.youlose.components.ButtonActor;
import com.bebel.youlose.components.abstrait.actions.Actionnable;
import com.bebel.youlose.components.abstrait.actors.AbstractGroup;
import com.bebel.youlose.components.abstrait.actors.SpriteActor;
import com.bebel.youlose.components.abstrait.actors.TextActor;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.utils.FontParameter;

import static com.badlogic.gdx.utils.Align.right;
import static com.badlogic.gdx.utils.Align.top;

public class MenuOptions extends AbstractGroup implements Actionnable {
    private final AssetsManager manager;
    private final TextActor musiqueLabel;
    private final ButtonActor fr;
    private final SpriteActor en;
    private final SpriteActor es;

    public MenuOptions(final AssetsManager manager) {
        this.manager = manager;

        manager.setContext("menu");
        final BitmapFont font = manager.getFont("sector.ttf", new FontParameter(21, Color.WHITE));
        final I18NBundle i18n = manager.getI18n();

        manager.setContext("menu/options");
        addActor(new SpriteActor("ref.png", manager));

        es = putActor(new SpriteActor("es.png", manager));
        es.move(127, 180);

        fr = putActor(new ButtonActor("fr.png", null, manager));
        fr.move(centerX(fr)-10, 180);

        en = putActor(new SpriteActor("en.png", manager));
        en.move(145, 180, top | right);

        musiqueLabel = putActor(new TextActor("musiques", font));
        musiqueLabel.refreshText(i18n);
        musiqueLabel.move(centerX(musiqueLabel), 426);
    }

    public void makeEvents() {
        Gdx.app.debug("MAKE EVENTS", "Events");
        fr.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manager.setLanguage("fr");
                return refreshTexts();
            }
        });
        en.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.debug("CLICK EN", "Click EN");
                manager.setLanguage("en");
                return refreshTexts();
            }
        });
        es.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manager.setLanguage("esperanto");
                return refreshTexts();
            }
        });
    }

    private boolean refreshTexts() {
        Gdx.app.debug("REFRESH", "Refresh textes");
        musiqueLabel.refreshText(manager.getI18n());
        return true;
    }
}
