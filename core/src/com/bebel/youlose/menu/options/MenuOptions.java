package com.bebel.youlose.menu.options;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.bebel.youlose.components.composition.SlideTextActor;
import com.bebel.youlose.components.interfaces.Actionnable;
import com.bebel.youlose.components.refound.AbstractGroup;
import com.bebel.youlose.components.refound.ButtonActor;
import com.bebel.youlose.components.refound.CheckActor;
import com.bebel.youlose.manager.AssetsManager;
import com.bebel.youlose.menu.MenuScreen;
import com.bebel.youlose.utils.FontParameter;

public class MenuOptions extends AbstractGroup implements Actionnable {
    private final MenuScreen parent;

    private final MenuOptionsLangues langues;
    private final SlideTextActor musiques;
    private final SlideTextActor sounds;
    private final CheckActor fullscreen;
    private final ButtonActor valider;

    public MenuOptions(final MenuScreen parent, final AssetsManager manager) {
        super(manager);
        this.parent = parent;
        setVisible(false);
        manager.setContext("menu");

        final BitmapFont font = manager.getFont("sector.ttf", new FontParameter(21, Color.valueOf("#AEA19A")));

//        addActor(new ImageActor(manager, "ref.png"));

        langues = putActor(new MenuOptionsLangues(parent, manager));
        langues.move(0, 170);

        musiques = putActor(new SlideTextActor(manager, font, "musiques",
                "slide-button/slide.png", "slide-button/pointer.png"));
        musiques.move(centerX(musiques), 406);
        musiques.setValue(manager.conf.getMusic());

        sounds = putActor(new SlideTextActor(manager, font, "sounds",
                "slide-button/slide.png", "slide-button/pointer.png"));
        sounds.move(centerX(sounds), 504);
        sounds.setValue(manager.conf.getSound());

        fullscreen = putActor(new CheckActor(manager, font, "fullscreen",
                "check-button/case.png", "check-button/case_coche.png"));
        fullscreen.move(centerX(fullscreen), 577);
        fullscreen.setChecked(manager.conf.isFullscreen());

        valider = putActor(new ButtonActor(manager, "text-button/valider.png"));
        valider.addHover("text-button/valider_hover.png");
        valider.move(centerX(valider), 657);

        refresh();
    }

    public void makeEvents() {
        langues.makeEvents();
        musiques.onChange(() -> {
            manager.conf.setMusic((int) musiques.getValue());
            manager.sounds.play("ferrets.wav");
        });
        sounds.onChange(() -> {
            manager.conf.setSound((int) sounds.getValue());
            manager.musiques.play("ferrets.wav");
        });
        fullscreen.onChange(() -> manager.conf.setFullscreen(fullscreen.isChecked()));

        valider.onClick((x, y, button, pointer) -> {
            manager.conf.save();
            parent.switchTo(MenuScreen.Screens.MENU);
        });
    }

    @Override
    public boolean refresh() {
        langues.refresh();
        musiques.refresh();
        sounds.refresh();
        fullscreen.refresh();
        fullscreen.setX(centerX(fullscreen));
        valider.refresh();
        return true;
    }
}
