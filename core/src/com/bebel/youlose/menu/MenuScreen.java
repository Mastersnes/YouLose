package com.bebel.youlose.menu;

import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.ButtonActor;
import com.bebel.youlose.utils.AbstractStage;

import static com.bebel.youlose.utils.Constantes.WORLD_WIDTH;

public class MenuScreen extends AbstractStage {
    private MenuBackground background;
    private ButtonActor play;
    private ButtonActor options;
    private ButtonActor credits;

    public MenuScreen(final LaunchGame parent) {
        super(parent);
    }

    @Override
    public void create() {
        manager.loadMenu();
        manager.finishLoading();

        background = new MenuBackground(manager);
        addActor(background);

        play = new ButtonActor("play.png", "play_hover.png", manager);
        play.setPosition(0, 490);
        addActor(play);

        options = new ButtonActor("options.png", "options_hover.png", manager);
        options.setPosition(WORLD_WIDTH - options.getWidth(), 200);
        addActor(options);

        credits = new ButtonActor("credits.png", "credits_hover.png", manager);
        credits.setPosition(0, 20);
        addActor(credits);
    }

    @Override
    public void afterAct(float delta) {

    }
}
