package com.bebel.youlose.components.refound.actors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Pools;
import com.bebel.youlose.components.interfaces.Refreshable;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.manager.AssetsManager;

import static com.bebel.youlose.utils.EventUtils.onClick;

/**
 * Abstraction de la checkbox
 */
public class CheckActor extends AbstractGroup implements Refreshable {
    private ImageActor on;
    private ImageActor off;
    private TextActor label;
    private boolean isChecked;
    private float spacing = 10;

    /**
     * Constructeur
     *
     * @param manager
     * @param font
     * @param text
     * @param offStr
     * @param onStr
     */
    public CheckActor(final AssetsManager manager, final BitmapFont font, final String text, final String offStr, final String onStr) {
        super(manager);
        this.label = putActor(new TextActor(manager, text, font));
        this.off = putActor(new ImageActor(manager, offStr));
        this.on = putActor(new ImageActor(manager, onStr));

        makeEvents();
        setName(text);
        refresh();
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
    public boolean refresh() {
        label.refresh();

        setBounds(getX(), getY(), label.getWidth() + on.getWidth() + spacing, on.getHeight());
        on.setX(label.getWidth() + spacing);
        off.setX(label.getWidth() + spacing);
        return true;
    }

    @Override
    public AssetsManager getManager() {
        return manager;
    }
}
