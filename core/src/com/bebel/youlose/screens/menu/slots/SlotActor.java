package com.bebel.youlose.screens.menu.slots;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bebel.youlose.components.actions.FinishRunnableAction;
import com.bebel.youlose.components.interfaces.Startable;
import com.bebel.youlose.components.refound.FontParameter;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.AnimatedActor;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.components.refound.actors.ui.TextActor;
import com.bebel.youlose.components.runnable.FinishRunnable;
import com.bebel.youlose.manager.save.GameSave;
import com.bebel.youlose.screens.menu.MenuScreen;

import static com.badlogic.gdx.math.Interpolation.linear;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.bebel.youlose.components.actions.Actions.emptyRun;
import static com.bebel.youlose.components.actions.Actions.finishRun;
import static com.bebel.youloseClient.enums.SaveType.CENTRE;
import static com.bebel.youloseClient.enums.SaveType.DROITE;

/**
 * Acteur representant un slot
 */
public class SlotActor extends AbstractGroup implements Startable {
    private final MenuScreen menuScreen;
    private final MenuSlots menuSlots;
    private final GameSave save;

    private final ImageActor slot;
    private ImageActor noir;
    private ImageActor grille;
    private final TextActor texte;

    //-- Actions
    private boolean open;


    public SlotActor(final MenuSlots parent, final String image, final GameSave save) {
        this.menuScreen = parent.getScreen();
        this.menuSlots = parent;

        this.save = save;
        setName(image);
        setTouchable(Touchable.enabled);

        slot = new ImageActor(image);
        setBounds(0,0,slot.getWidth(), slot.getHeight());

        putActor(slot);

        final BitmapFont font = manager.getFont("sector.ttf", new FontParameter(15, Color.valueOf("#AEA19A")));
        putActor(texte = new TextActor("label.delete", font));
    }

    public void start() {
        texte.move(texte.centerX(), -20)
            .setVisible(save.isUsed());

        if (save.isUsed()) {
            open = true;
            grille.moveBy(-grille.getWidth() / 2, 0);
            grille.setScaleX(0.5f);
        }
    }

    @Override
    public void refresh(Color color) {
        super.refresh(color);
        texte.setX(texte.centerX());
    }

    public ImageActor getGrille() {
        if (grille == null)
            grille = new ImageActor("slots/slots:grille");
        return grille;
    }
    public ImageActor getNoir() {
        if (noir == null)
            noir = new ImageActor("slots/slots:noir");
        return noir;
    }

    public void makeSpecificEvents() {
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
                AnimatedActor animation = menuScreen.getBackground().getAnimationCote();
                if (save.getType() == CENTRE)
                    animation = menuScreen.getBackground().getAnimationCentre();

                menuSlots.addBlockedActions(
                        open(),
                        menuScreen.getBackground().play(animation, save.getType() == DROITE),
                        run(() -> menuScreen.launchGame(save))
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
                                    open = false;
                                })
                        )
                );
            }
        });
    }

}
