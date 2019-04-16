package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.Gdx;
import com.bebel.youlose.components.composition.ZoneTextActor;
import com.bebel.youlose.components.interfaces.Startable;
import com.bebel.youlose.components.refound.actors.ui.TypingActor;
import com.bebel.youlose.manager.resources.ScreensManager;

import static com.badlogic.gdx.utils.Align.bottomLeft;
import static com.bebel.youlose.components.refound.event.ClickCatcher.onClick;
import static com.bebel.youloseClient.enums.Emplacement.ENIGME2;

/**
 * Acteur constituant la zone de texte sous le globe
 */
public class ZoneText extends ZoneTextActor implements Startable {
    private final Enigme1 parent;
    private TypingActor ok;

    public ZoneText(final Enigme1 parent) {
        super("atlas:bloc_texte", 30, 20);
        this.parent = parent;
        create();
    }

    public void create() {
        ok = new TypingActor("enigme1.ok");
        onClick(ok, (button, pointer, x, y) -> {
            Gdx.app.log("GAGNE !", "Vous avez gagné !");
        });
    }

    @Override
    public void start() {
        move(centerX(), 0, bottomLeft);
        clear();
        ok.restart(); ok.pause();
        // ajouter un delay
        add("enigme1.partie1");
        add(ok);
    }

    @Override
    public void makeSpecificEvents() {
        onClick(this, (x, y, button, pointer) -> {
            if (!isFinish()) finish();
            else {
                if (parent.getVictoire() < 0) parent.restart();
                else if (parent.getVictoire() > 0) ScreensManager.getInstance().switchTo(ENIGME2);
            }
        });
    }
}
