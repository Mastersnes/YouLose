package com.bebel.youlose.screens.menu.options;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.bebel.youlose.components.composition.SlideTextActor;
import com.bebel.youlose.components.refound.FontParameter;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.components.refound.actors.ui.CheckActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.screens.menu.MenuScreen;

/**
 * Ecran des options
 */
public class MenuOptions extends AbstractGroup {
    private final MenuScreen parent;

    private final MenuOptionsLangues langues;
    private final SlideTextActor musiques;
    private final SlideTextActor sounds;
    private final CheckActor fullscreen;
    private final ButtonActor valider;

    public MenuOptions(final MenuScreen parent) {
        super();
        this.parent = parent;
        setVisible(false);
        manager.setContext("menu");

        final BitmapFont font = manager.getFont("sector.ttf", new FontParameter(21, Color.valueOf("#AEA19A")));

//        addActor(new ImageActor(manager, "ref.png"));

        addActor(new ImageActor("options/other:fond"));

        langues = putActor(new MenuOptionsLangues(parent))
            .move(0, 170);

        musiques = putActor(new SlideTextActor(font, "musiques",
                "options/other:slide", "options/other:pointer"));
        musiques.move(musiques.centerX(), 406);
        musiques.setValue(manager.conf.getMusic());

        sounds = putActor(new SlideTextActor(font, "sounds",
                "options/other:slide", "options/other:pointer"));
        sounds.move(sounds.centerX(), 504);
        sounds.setValue(manager.conf.getSound());

        fullscreen = putActor(new CheckActor(font, "fullscreen",
                "options/other:case", "options/other:case_coche"));
        fullscreen.move(fullscreen.centerX(), 577);
        fullscreen.setChecked(manager.conf.isFullscreen());

        valider = putActor(new ButtonActor("options/buttons:valider"));
        valider.addHover("options/buttons:valider_hover");
        valider.move(valider.centerX(), 657);

        refresh(getColor());
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
    public void refresh(final Color color) {
        langues.refresh(color);
        musiques.refresh(color);
        sounds.refresh(color);
        fullscreen.refresh(color);
        fullscreen.setX(fullscreen.centerX());
        valider.refresh(color);
    }
}
