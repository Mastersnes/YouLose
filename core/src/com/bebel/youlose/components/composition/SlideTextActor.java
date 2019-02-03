package com.bebel.youlose.components.composition;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.bebel.youlose.components.refound.AbstractGroup;
import com.bebel.youlose.components.refound.SliderActor;
import com.bebel.youlose.components.refound.TextActor;
import com.bebel.youlose.manager.AssetsManager;

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

        label.move(centerX(label), 0);
        slide.move(centerX(slide), 49);

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

    @Override
    public boolean refresh() {
        label.refresh();
        label.setX(centerX(label));
        return true;
    }
}