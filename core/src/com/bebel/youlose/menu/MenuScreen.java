package com.bebel.youlose.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.utils.AbstractStage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class MenuScreen extends AbstractStage {
    private MenuBackground background;
    private MenuOptions options;
    private MenuButtons buttons;

    public MenuScreen(final LaunchGame parent) {
        super(parent);
    }

    @Override
    public void create() {
        manager.loadMenu();

        options = new MenuOptions(manager);
        addActor(options);

        background = new MenuBackground(manager);
        addActor(background);

        buttons = new MenuButtons(manager);
        addActor(buttons);

        /**
         * Click sur Play
         */
        buttons.getPlay().addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return buttons.addActions(buttons.disappair(),background.open(), background.close(), buttons.appair());
            }
        });

        /**
         * Click sur option
         */
        buttons.getOptions().addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        buttons.getCredits().addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }

    @Override
    public void afterAct(float delta) {

    }
}
