package com.bebel.youlose.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.actors.AbstractGroup;
import com.bebel.youlose.components.actors.SpriteActor;
import com.bebel.youlose.components.actors.TextActor;
import com.bebel.youlose.manager.AssetsManager;

import static com.badlogic.gdx.utils.Align.bottom;
import static com.badlogic.gdx.utils.Align.left;

public class SlideActor extends AbstractGroup {
    private Runnable onChangeAction;
    private int percent;
    private final TextActor label;
    private final SpriteActor slide;
    private final SpriteActor cursor;

    public SlideActor(final AssetsManager manager, final BitmapFont font, final String labelStr) {
        super(manager);
        this.label = putActor(new TextActor(labelStr, font));
        slide = putActor(new SpriteActor("slide.png", manager));
        cursor = putActor(new SpriteActor("pointer.png", manager));

        setBounds(0, 0, slide.getWidth(), this.label.getHeight() + slide.getHeight() + 20);

        this.label.move(centerX(this.label), 0);
        slide.move(centerX(slide), 49);
        cursor.move(centerX(cursor), 0, left | bottom);

        refresh();
    }

    public void makeEvents() {
        slide.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setPercent(x);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                setPercent(x);
            }
        });
    }

    public void onChange(final Runnable run) {
        this.onChangeAction = run;
    }

    @Override
    public boolean refresh() {
        label.refresh(this);
        label.setX(centerX(label));
        return true;
    }

    private void setPercent(final float mouseX) {
        if (mouseX >= 0 && mouseX <= slide.getWidth()) {
            cursor.setX(slide.getX() + mouseX - (cursor.getWidth() / 2));
            if (onChangeAction != null) onChangeAction.run();

            percent = Math.round((mouseX * 100f) / slide.getWidth());
        }
    }

    public int getPercent() {
        return percent < 5 ? 0 : percent > 95 ? 100 : percent;
    }
}
