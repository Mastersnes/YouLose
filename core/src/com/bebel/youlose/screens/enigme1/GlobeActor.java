package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.interfaces.Startable;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.AnimatedActor;
import com.bebel.youlose.components.refound.event.ClickCatcher;
import com.bebel.youlose.components.refound.shape.CircleShape;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;
import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL;

/**
 * Acteur constituant le globe central
 */
public class GlobeActor extends AbstractGroup implements Startable {
    private final Enigme1 parent;
    private AnimatedActor globe;

    private List<AnimatedActor> cassures = new ArrayList<>();
    private int currentCassure;

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

        addCassure(1, 24f)
            .move(276, -236);
        addCassure(2, 24f)
            .move(276, -236);
        addCassure(3, 30f)
            .move(-124, 72);

        cassures.get(2).onFrame(8, () -> {
            cassures.get(0).setVisible(false);
            cassures.get(1).setVisible(false);
            globe.setVisible(false);
        });
        setHitbox(new CircleShape(406, 365, 143));
    }

    @Override
    public void start() {
        globe.setVisible(true);
        for (final AnimatedActor cassure : cassures) {
            cassure.stop();
        }
        currentCassure = 0;
        refresh(getColor());
    }

    private AnimatedActor addCassure(final int index, final float fps) {
        final AnimatedActor cassure = new AnimatedActor("cassures/" + index + "/", NORMAL, fps);
        cassure.setVisible(false);
        cassure.move(0, 0);
        cassures.add(putActor(cassure));
        return cassure;
    }

    @Override
    public void makeSpecificEvents() {
        ClickCatcher.onClick(this, (x, y, pointer, button) -> {
            if (currentCassure >= cassures.size()) return;
            cassures.get(currentCassure).setVisible(true);
            cassures.get(currentCassure).play();
            currentCassure++;
        });
    }
}
