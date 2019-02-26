package com.bebel.youlose.components.composition;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.SliderActor;
import com.bebel.youlose.components.refound.actors.ui.TextActor;
import com.bebel.youlose.manager.resources.AssetsManager;

import static com.bebel.youlose.utils.ActorUtils.move;

/**
 * Composition d'un texte et d'un slider
 */
public class SlideTextActor extends AbstractGroup {
    private final TextActor label;
    private final SliderActor slide;

    public SlideTextActor(final AssetsManager manager, final BitmapFont font, final String text, final String slider, final String cursor) {
        this(new TextActor(manager, text, font), new SliderActor(manager, slider, cursor));
    }

    public SlideTextActor(final TextActor label, final SliderActor slide) {
        super(label.getManager());
        this.label = putActor(label);
        this.slide = putActor(slide);

        setBounds(0, 0, slide.getWidth(), label.getHeight() + slide.getHeight() + 20);

        label.move(label.centerX(), 0);
        slide.move(slide.centerX(), 45);
        slide.setRange(0, 100);

        refresh();
    }

    public void onChange(final Runnable onChangeAction) {
        slide.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                onChangeAction.run();
            }
        });
    }

    public float getValue() {
        return slide.getValue();
    }
    public void setValue(final float value) {
        slide.setValue(value);
    }


    @Override
    public void refresh() {
        label.refresh();
        label.setX(label.centerX());
    }
}
