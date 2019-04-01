package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.interfaces.Playable;
import com.bebel.youlose.components.interfaces.Startable;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.AnimatedActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.components.refound.actors.ui.TextActor;
import com.bebel.youlose.components.refound.event.ClickCatcher;
import com.bebel.youlose.components.refound.shape.CircleShape;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;
import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL;

/**
 * Acteur constituant le globe central
 */
public class GlobeActor extends AbstractGroup implements Startable, Playable {
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

        cassures.get(2).onFrame(8, () -> afterExplode());
        setHitbox(new CircleShape(406, 365, 143));
    }

    @Override
    public void start() {
        currentCassure = parent.getSave().getEnigme1().getCassure();
        globe.setVisible(true);
        for (int i=0; i<cassures.size(); i++) {
            final AnimatedActor cassure = cassures.get(i);
            initCassure(cassure);
            if (currentCassure > i) {
                cassure.setVisible(true);
                cassure.finish();
            }
        }
        if (currentCassure > cassures.size()) afterExplode();

        refresh(getColor());

        if (lose()) parent.lose(Enigme1.LoseType.CASSURE);
    }

    public void afterExplode() {
        cassures.get(0).setVisible(false);
        cassures.get(1).setVisible(false);
        globe.setVisible(false);
    }

    private AnimatedActor addCassure(final int index, final float fps) {
        final AnimatedActor cassure = new AnimatedActor("cassures/" + index + "/", NORMAL, fps);
        cassures.add(putActor(cassure));
        return cassure;
    }

    public void initCassure(final AnimatedActor cassure) {
        cassure.stop();
        cassure.setVisible(false);
    }

    @Override
    public void makeSpecificEvents() {
        ClickCatcher.onClick(this, (x, y, pointer, button) -> {
            if (currentCassure >= cassures.size()) return;
            cassures.get(currentCassure).setVisible(true);
            cassures.get(currentCassure).play();
            currentCassure++;
            parent.getSave().getEnigme1().setCassure(currentCassure);

            if (lose()) parent.lose(Enigme1.LoseType.CASSURE);
        });
    }

    @Override
    public boolean win() {
        return false;
    }

    @Override
    public boolean lose() {
        return currentCassure >= cassures.size();
    }
}
