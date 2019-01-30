package com.bebel.youlose.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.bebel.youlose.components.actors.AbstractGroup;
import com.bebel.youlose.components.actors.SpriteActor;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.utils.FontParameter;

public class MenuOptions extends AbstractGroup implements Actionnable {
    private final MenuOptionsLangues langues;
    private final SlideActor musiques;
    private final SlideActor sounds;

    public MenuOptions(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        setVisible(false);
        manager.setContext("menu");

        final BitmapFont font = manager.getFont("sector.ttf", new FontParameter(20, Color.WHITE));

//        addActor(new SpriteActor("ref.png", manager));

        langues = putActor(new MenuOptionsLangues(parent, manager));

        musiques = putActor(new SlideActor(manager, font, "musiques"));
        musiques.move(centerX(musiques), 426);

        sounds = putActor(new SlideActor(manager, font, "sounds"));
        sounds.move(centerX(sounds), 540);
        refresh();
    }

    public void makeEvents() {
        langues.makeEvents();
        musiques.makeEvents();
        sounds.makeEvents();
        musiques.onChange(() -> manager.setMusic(musiques.getPercent()));
        sounds.onChange(() -> manager.setSound(sounds.getPercent()));
    }

    @Override
    public boolean refresh() {
        langues.refresh();
        musiques.refresh();
        sounds.refresh();
        return true;
    }
}
