package com.bebel.youlose.components.refound.actors.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Pools;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;

import static com.bebel.youlose.components.refound.event.ClickCatcher.onClick;

/**
 * Acteur representant une checkbox
 */
public class CheckActor extends AbstractGroup implements Refreshable {
    private boolean isChecked;
    private float spacing = 10;

    private ImageActor on;
    private ImageActor off;
    private TextActor label;

    public CheckActor(final BitmapFont font, final String text, final String offStr, final String onStr) {
        super();
        this.label = putActor(new TextActor(text, font));
        this.off = putActor(new ImageActor(offStr));
        this.on = putActor(new ImageActor(onStr));

        makeEvents();
        setName(text);
        refresh(getColor());
    }

    public void makeEvents() {
        onClick(this, (x, y, button, pointer) -> setChecked(!isChecked));
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;

        final ChangeListener.ChangeEvent changeEvent = Pools.obtain(ChangeListener.ChangeEvent.class);
        fire(changeEvent);
        Pools.free(changeEvent);

        this.on.setVisible(isChecked);
        this.off.setVisible(!isChecked);
    }

    public boolean isChecked() {
        return isChecked;
    }

    /**
     * Inverse l'ordre de la checkbox
     */
    public void onChange(final Runnable run) {
        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                run.run();
            }
        });
    }

    @Override
    public void refresh(final Color color) {
        label.refresh(color);
        off.refresh(color);
        on.refresh(color);

        setBounds(getX(), getY(), label.getWidth() + on.getWidth() + spacing, on.getHeight());
        on.setX(label.getWidth() + spacing);
        off.setX(label.getWidth() + spacing);
    }
}
