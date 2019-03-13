package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.components.refound.event.ClickCatcher;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.Interpolation.fastSlow;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;

/**
 * Groupe composé d'un ensemble de feuilles
 */
public class FeuillesGroup extends AbstractGroup {
    private final Enigme1 parent;
    private final List<ImageActor> feuilles = new ArrayList<>();

    public FeuillesGroup(final Enigme1 parent) {
        super();
        this.parent = parent;

        refresh(getColor());
    }

    public void create(final Actor ref) {
        setBounds(ref.getX(), ref.getY(), ref.getWidth(), ref.getHeight());

        // Partie de gauche
        //Tout en haut
        addFeuille(197, -6, -49); // Feuille de gauche

        // Au dessus à gauche du P de premiere
        addFeuille(110, 88, -49); // Feuille du centre
        addFeuille(140, 73, -58); // Feuille du haut
        addFeuille(92, 131, -27); // Feuille du bas

        // En bas à gauche du P de premiere
        addFeuille(28, 202, 0); // Feuille du centre
        addFeuille(43, 163, -18); // Feuille du haut
        addFeuille(45, 243, 22); // Feuille du bas

        // En bas à gauche du E de Enigme
        addFeuille(142, 363, 71); // Feuille du centre
        addFeuille(71, 332, 24); // Feuille du haut
        addFeuille(183, 366, 82); // Feuille du bas

        // Tout en bas à gauche
        addFeuille(248, 460, 63); // Feuille du haut
        addFeuille(310, 486, 89); // Feuille du bas
        addFeuille(283, 488, 92); // Feuille du centre

        // Partie de droite
        // Tout en haut
        addFeuille(286, 40, 16).flipH(); // Feuille de droite

        // Au dessus du é de premiere
        addFeuille(357, 102, 1).flipH(); // Feuille du centre
        addFeuille(330, 89, 11).flipH(); // Feuille du haut
        addFeuille(360, 125, -6).flipH(); // Feuille du bas

        // A droite du e de premiere
        addFeuille(468, 219, 20).flipH(); // Feuille du centre
        addFeuille(442, 198, 13).flipH(); // Feuille du haut
        addFeuille(468, 223, -4).flipH(); // Feuille du bas

        // A droite du e de enigme
        addFeuille(450, 259, -90).flipH(); // Feuille du bas
        addFeuille(447, 260, -51).flipH(); // Feuille du haut

        // Tout en bas à droite
        addFeuille(351, 365, -66).flipH(); // Feuille du haut
        addFeuille(347, 372, -116).flipH(); // Feuille du bas
        addFeuille(355, 366, -96).flipH(); // Feuille du centre


    }

    private ImageActor addFeuille(float x, float y, float rotation, boolean debug) {
        final ImageActor feuille = addFeuille(x, y, rotation);
        if (debug) addDebug(feuille);
        return feuille;
    }

    private ImageActor addFeuille(float x, float y, float rotation) {
        final ImageActor feuille;
        putActor(feuille = new ImageActor("atlas:feuille"));
        feuille.move(x, y);
        feuille.rotateBy(rotation);
        feuilles.add(feuille);

        ClickCatcher.onClick(feuille, (mx, my, pointer, button) -> {
            feuille.setTouchable(Touchable.disabled);
            float rand = MathUtils.random(1f, 4f);
            feuille.addActions(
                    Actions.moveBy(rand * -100, -WORLD_HEIGHT, 5f, fastSlow),
                    Actions.hide(),
                    run(() -> {
                        removeActor(feuille);
                        feuilles.remove(feuille);
                    })
            );
        });

        return feuille;
    }
}
