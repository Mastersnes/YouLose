package com.bebel.youlose.menu.options;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.bebel.youlose.components.refound.AbstractGroup;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.components.composition.SlideTextActor;
import com.bebel.youlose.components.refound.CheckActor;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;
import com.bebel.youlose.utils.FontParameter;

public class MenuOptions extends AbstractGroup implements Actionnable {
    private final MenuOptionsLangues langues;
    private final SlideTextActor musiques;
    private final SlideTextActor sounds;
    private final CheckActor fullscreen;

    public MenuOptions(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        setVisible(false);
        manager.setContext("menu");

        final BitmapFont font = manager.getFont("sector.ttf", new FontParameter(20, Color.WHITE));

//        addActor(new ImageActor("ref.png", manager));

        langues = putActor(new MenuOptionsLangues(parent, manager));

        musiques = putActor(new SlideTextActor(manager, font, "musiques", "slide.png", "pointer.png"));
        musiques.move(centerX(musiques), 426);

        sounds = putActor(new SlideTextActor(manager, font, "sounds", "slide.png", "pointer.png"));
        sounds.move(centerX(sounds), 540);

        fullscreen = putActor(new CheckActor(manager, font, "fullscreen", "case.png", "case_coche.png"));
        fullscreen.move(centerX(fullscreen), 560);
        refresh();
    }

    public void makeEvents() {
        langues.makeEvents();
        musiques.onChange(() -> manager.sound.setMusic((int)musiques.getValue()));
        sounds.onChange(() -> manager.sound.setSound((int)sounds.getValue()));
    }

    @Override
    public boolean refresh() {
        langues.refresh();
        musiques.refresh();
        sounds.refresh();
        return true;
    }
}
