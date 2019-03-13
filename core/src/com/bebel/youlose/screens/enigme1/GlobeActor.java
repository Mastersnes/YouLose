package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.AnimatedActor;
import com.bebel.youlose.components.refound.event.ClickCatcher;
import com.bebel.youlose.components.refound.shape.CircleShape;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

/**
 * Acteur constituant le globe central
 */
public class GlobeActor extends AbstractGroup {
    private final Enigme1 parent;
    private AnimatedActor globe;

    public GlobeActor(final Enigme1 parent) {
        super();
        this.parent = parent;
        setTouchable(Touchable.enabled);
        create();
    }

    public void create() {
        putActor(globe = new AnimatedActor("tourne/", LOOP, 24f)
        ).move(0, 0);
        globe.setSpeed(0.15f);
        globe.play();

        setHitbox(new CircleShape(406, 365, 143));

        refresh(getColor());
        debug();
    }

    @Override
    public void makeEvents() {
        super.makeEvents();
        ClickCatcher.onClick(this, (x, y, pointer, button) -> {
            Gdx.app.debug("TEST", "TEST");
        });
    }
}
