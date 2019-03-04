package com.bebel.youlose.screens.menu.slots;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.actions.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.FontParameter;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.components.refound.actors.ui.TextActor;
import com.bebel.youlose.components.refound.event.ClickCatcher;

import static com.badlogic.gdx.math.Interpolation.linear;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.bebel.youlose.components.actions.Actions.emptyRun;
import static com.bebel.youlose.components.actions.Actions.finishRun;

/**
 * Acteur representant un slot
 */
public class SlotActor extends AbstractGroup {
    private final ImageActor slot;
    private ImageActor grille;
    private final TextActor texte;
    private boolean open;


    public SlotActor(final String image) {
        setName(image);
        setTouchable(Touchable.enabled);

        slot = new ImageActor(image);
        setBounds(0,0,slot.getWidth(), slot.getHeight());

        putActor(slot);

        final BitmapFont font = manager.getFont("sector.ttf", new FontParameter(15, Color.valueOf("#AEA19A")));
        putActor(texte = new TextActor("delete", font))
            .move(texte.centerX(), -20)
            .setVisible(false);
    }

    public void addNoir(final MenuSlots menuSlots, final float x, final float y) {
        menuSlots.putActor(new ImageActor("slots/slots:noir"))
                .move(x, y);
    }

    public void addGrille(final MenuSlots menuSlots, final float x, final float y) {
        menuSlots.putActor(grille = new ImageActor("slots/slots:grille"))
                .move(x, y);
    }

    public void onClick(final ClickCatcher.ClickEvent runnable) {
        slot.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                runnable.run(x, y, pointer, button);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    public void onErase(final ClickCatcher.ClickEvent runnable) {
        texte.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                runnable.run(x, y, pointer, button);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }


    /**
     * Ouvre la grille
     * @return
     */
    public FinishRunnableAction open() {
        if (open) return emptyRun();
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                grille.addAction(
                    parallel(
                        Actions.moveBy(-grille.getWidth() / 2, 0, 1.5f, linear),
                        Actions.sizeTo( 0.5f, grille.getHeight(), 1.5f, linear),
                        delay(1, finish(() -> open = true))
                    )
                );
            }
        });
    }

    /**
     * Ferme la grille
     * @return
     */
    public FinishRunnableAction close() {
        if (!open) return emptyRun();
        return finishRun(new FinishRunnable() {
            @Override
            public void run() {
                grille.addAction(
                        sequence(
                                parallel(
                                        Actions.moveBy(grille.getWidth() / 2, 0, 1.5f, linear),
                                        Actions.sizeTo( 1f, grille.getHeight(), 1.5f, linear)
                                ),
                                finish(() -> open = true)
                        )
                );
            }
        });
    }

}
