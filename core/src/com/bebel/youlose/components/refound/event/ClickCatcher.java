package com.bebel.youlose.components.refound.event;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Outil permettant d'assigner des callback aux actions de souris
 */
public class ClickCatcher extends ClickListener {
    private ClickEvent touchDownCallback;
    private ClickEvent touchDraggedCallback;
    private ClickEvent touchUpCallback;
    private ClickEvent enterCallback;
    private ClickEvent exitCallback;
    private ClickEvent clickedCallback;

    public void touchDown(final ClickEvent touchDownCallback) {
        this.touchDownCallback = touchDownCallback;
    }
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        if (touchDownCallback != null) touchDownCallback.run(x, y, pointer, button);
        return super.touchDown(event, x, y, pointer, button);
    }

    public void touchDragged(final ClickEvent touchDraggedCallback) {
        this.touchDraggedCallback = touchDraggedCallback;
    }
    public void touchDragged (InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
        if (touchDraggedCallback != null) touchDraggedCallback.run(x, y, pointer, 0);
    }

    public void touchUp(final ClickEvent touchUpCallback) {
        this.touchUpCallback = touchUpCallback;
    }
    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        if (touchUpCallback != null) touchUpCallback.run(x, y, pointer, button);
    }

    public void enter(final ClickEvent enterCallback) {
        this.enterCallback = enterCallback;
    }
    public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);
        if (enterCallback != null) enterCallback.run(x, y, pointer, 0);
    }

    public void exit(final ClickEvent exitCallback) {
        this.exitCallback = exitCallback;
    }
    public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);
        if (exitCallback != null) exitCallback.run(x, y, pointer, 0);
    }

    public void clicked(final ClickEvent clickedCallback) {
        this.clickedCallback = clickedCallback;
    }
    public void clicked (InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        if (clickedCallback != null) clickedCallback.run(x, y, 0, 0);
    }

    public interface ClickEvent {
        void run(final float x, final float y, final int pointer, final int button);
    }

    public synchronized static void onClick(final Actor actor, final ClickCatcher.ClickEvent run) {
        actor.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                run.run(x, y, pointer, button);
                return true;
            }
        });
    }
}
