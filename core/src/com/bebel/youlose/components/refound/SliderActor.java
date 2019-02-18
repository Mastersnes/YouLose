package com.bebel.youlose.components.refound;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.Pool;
import com.bebel.youlose.components.interfaces.Movable;
import com.bebel.youlose.manager.AssetsManager;

/**
 * Specification de l'acteur Slider
 */
public class SliderActor extends Slider implements Movable {
    /**
     * Constructeur
     * @param manager
     * @param slide
     * @param cursor
     */
    public SliderActor(final AssetsManager manager, final String slide, final String cursor) {
        this(new SliderStyle(manager.getDrawable(slide), manager.getDrawable(cursor)));
    }

    /**
     * Constructeur
     * @param style
     */
    public SliderActor(final SliderStyle style) {
        super(0, style.background.getMinWidth(), 1, false, style);
    }

    @Override
    public float getPrefWidth() {
        if (getStyle().background != null)
            return getStyle().background.getMinWidth();
        return super.getPrefWidth();
    }
}