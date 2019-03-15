package com.bebel.youlose.screens.menu.slots;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.runnable.FinishRunnable;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.refound.FontParameter;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.AnimatedActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.components.refound.actors.ui.TextActor;
import com.bebel.youlose.manager.save.SaveInstance;
import com.bebel.youlose.screens.menu.MenuScreen;

import static com.badlogic.gdx.math.Interpolation.linear;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.bebel.youlose.components.actions.Actions.emptyRun;
import static com.bebel.youlose.components.actions.Actions.finishRun;
import static com.bebel.youlose.manager.save.SaveManager.SaveEnum.CENTRE;
import static com.bebel.youlose.manager.save.SaveManager.SaveEnum.DROITE;

/**
 * Acteur representant un slot
 */
public class SlotActor extends AbstractGroup {
    private final SaveInstance save;

    private final ImageActor slot;
    private ImageActor grille;
    private final TextActor texte;

    //-- Actions
    private boolean open;


    public SlotActor(final String image, final SaveInstance save) {
        this.save = save;
        setName(image);
        setTouchable(Touchable.enabled);

        slot = new ImageActor(image);
        setBounds(0,0,slot.getWidth(), slot.getHeight());

        putActor(slot);

        final BitmapFont font = manager.getFont("sector.ttf", new FontParameter(15, Color.valueOf("#AEA19A")));
        putActor(texte = new TextActor("delete", font))
            .move(texte.centerX(), -20)
            .setVisible(save.isUsed());
    }

    @Override
    public void refresh(Color color) {
        super.refresh(color);
        texte.setX(texte.centerX());
    }

    public void addNoir(final MenuSlots menuSlots, final float x, final float y) {
        menuSlots.putActor(new ImageActor("slots/slots:noir"))
                .move(x, y);
    }

    public void addGrille(final MenuSlots menuSlots, final float x, final float y) {
        menuSlots.putActor(grille = new ImageActor("slots/slots:grille"))
                .move(x, y);
        if (save.isUsed()) {
            open = true;
            grille.moveBy(-grille.getWidth() / 2, 0);
            grille.setScaleX(0.5f);
        }
    }

    public void makeEvents(final MenuScreen parent) {
        super.makeEvents();

        texte.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                addBlockedActions(
                        close(),
                        run(() -> save.delete())
                );
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        slot.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                AnimatedActor animation = parent.getBackground().getAnimationCote();
                if (save.getType() == CENTRE)
                    animation = parent.getBackground().getAnimationCentre();

                addBlockedActions(
                        open(),
                        parent.getBackground().play(animation, save.getType() == DROITE),
                        run(() -> parent.launchGame(save))
                );
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
                        Actions.scaleTo( 0.5f, 1f, 1.5f, linear),
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
                                        Actions.scaleTo( 1f, 1f, 1.5f, linear)
                                ),
                                finish(() -> {
                                    texte.setVisible(false);
                                    open = true;
                                })
                        )
                );
            }
        });
    }

}
