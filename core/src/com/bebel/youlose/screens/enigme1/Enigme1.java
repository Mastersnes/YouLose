package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.LaunchGame;
import com.bebel.youlose.components.refound.abstrait.AbstractScreen;
import com.bebel.youlose.components.refound.actors.ui.ButtonActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.manager.resources.ScreensManager;
import com.bebel.youlose.manager.save.SaveInstance;
import com.bebel.youlose.manager.save.SaveManager;
import com.bebel.youlose.screens.menu.MenuScreen;

import static com.badlogic.gdx.utils.Align.bottomRight;

/**
 * Ecran de la premiere enigme
 */
public class Enigme1 extends AbstractScreen {
    public static final String NAME = "enigme1";

    private ButtonActor quitter;
    private FeuillesGroup feuilles;
    private ImageActor cadre;
    private GlobeActor globe;
    private ZoneText zoneText;

    private ImageActor titre;

    private SaveInstance save;
    private int victoire;

    public Enigme1(final LaunchGame parent) {super(parent);}

    @Override
    public void create() {
        save = SaveManager.getInstance().getCurrent();
        addActor(new ImageActor("atlas:fond"));

        putActor(feuilles = new FeuillesGroup(this));

        putActor(cadre = new ImageActor("atlas:cadre_bois"))
                .setTouchable(Touchable.disabled);

        feuilles.create(cadre);

        putActor(globe = new GlobeActor(this));

        putActor(titre = new ImageActor("button.png"))
                .setTouchable(Touchable.disabled);

        putActor(zoneText = new ZoneText(this));

        putActor(quitter = new ButtonActor("general/quitter.png"))
            .scaleBy(-0.5f);
    }

    @Override
    public void start() {
        victoire = 0;
        save = SaveManager.getInstance().getCurrent();
        cadre.move(220, 49);
        globe.move(114, -75);
        quitter.move(10, 10, bottomRight);
        titre.move(titre.centerX(), 198);

        setDisable(false);
    }

    public void restart() {
        save.getEnigme1().init();
        save.save();
        startScreen();
    }

    @Override
    public void makeEvents() {
        quitter.onClick((x, y, button, pointer) -> ScreensManager.getInstance().switchTo(MenuScreen.class));
    }

    @Override
    protected String context() {
        return NAME;
    }

    public SaveInstance getSave() {
        return save;
    }

    public void lose(final LoseType type) {
        victoire = type.type;
        switch (type) {
            case FEUILLE:
                zoneText.clear();
                zoneText.add("enigme1.feuilles");
                break;
            case CASSURE:
                zoneText.clear();
                zoneText.add("enigme1.cassure");
                break;
        }
        setDisable(true);
    }

    public void win() {
        victoire = 1;
        setDisable(true);
    }

    public void setDisable(final boolean disable) {
        quitter.setDisable(disable);
        if (disable) {
            quitter.setTouchable(Touchable.disabled);
            feuilles.setTouchable(Touchable.disabled);
            globe.setTouchable(Touchable.disabled);
            zoneText.setTouchable(Touchable.enabled);
        }else {
            quitter.setTouchable(Touchable.enabled);
            feuilles.setTouchable(Touchable.childrenOnly);
            globe.setTouchable(Touchable.enabled);
            zoneText.setTouchable(Touchable.childrenOnly);
        }
    }

    enum LoseType {
        FEUILLE(-1), CASSURE(-2);

        private final int type;
        LoseType(final int type) {
            this.type = type;
        }
    }

    public int getVictoire() {
        return victoire;
    }
}
