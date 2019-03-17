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
import com.bebel.youlose.screens.menu.MenuSubscreen;

/**
 * Ecran des options
 */
public class MenuOptions extends MenuSubscreen {
    private MenuOptionsLangues langues;
    private SlideTextActor musiques;
    private SlideTextActor sounds;
    private CheckActor fullscreen;
    private ButtonActor valider;

    public MenuOptions(final MenuScreen parent) {
        super(parent);
    }

    @Override
    public void create() {
        setVisible(false);

        final BitmapFont font = manager.getFont("sector.ttf", new FontParameter(21, Color.valueOf("#AEA19A")));

        addActor(new ImageActor("options/other:fond"));

        langues = putActor(new MenuOptionsLangues(parent));
        musiques = putActor(new SlideTextActor(font, "musiques",
                "options/other:slide", "options/other:pointer"));

        sounds = putActor(new SlideTextActor(font, "sounds",
                "options/other:slide", "options/other:pointer"));

        fullscreen = putActor(new CheckActor(font, "fullscreen",
                "options/other:case", "options/other:case_coche"));

        valider = putActor(new ButtonActor("options/buttons:valider"));
        valider.addHover("options/buttons:valider_hover");
    }

    @Override
    public void startSubscreen() {
        langues.move(0, 170);

        musiques.move(musiques.centerX(), 406);
        musiques.setValue(manager.conf.getMusic());

        sounds.move(sounds.centerX(), 504);
        sounds.setValue(manager.conf.getSound());

        fullscreen.move(fullscreen.centerX(), 577);
        fullscreen.setChecked(manager.conf.isFullscreen());

        valider.move(valider.centerX(), 657);
        refresh(getColor());
    }

    @Override
    public void makeSpecificEvents() {
        musiques.onChange(() -> manager.conf.setMusic((int) musiques.getValue()));
        sounds.onChange(() -> manager.conf.setSound((int) sounds.getValue()));
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
