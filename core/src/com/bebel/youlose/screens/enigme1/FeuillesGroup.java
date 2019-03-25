package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.actions.Actions;
import com.bebel.youlose.components.interfaces.Playable;
import com.bebel.youlose.components.interfaces.Startable;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.components.refound.event.ClickCatcher;
import com.bebel.youlose.manager.save.Enigme1Save;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.Interpolation.fastSlow;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.bebel.youlose.utils.Constantes.WORLD_HEIGHT;

/**
 * Groupe composé d'un ensemble de feuilles
 */
public class FeuillesGroup extends AbstractGroup implements Startable, Playable {
    private final Enigme1 parent;
    private Actor ref;
    private final List<ImageActor> feuilles = new ArrayList<>();
    private Enigme1Save save;

    public FeuillesGroup(final Enigme1 parent) {
        super();
        this.parent = parent;

        refresh(getColor());
    }

    public void create(final Actor ref) {
        this.save = parent.getSave().getEnigme1();
        this.ref = ref;

        for (int i=0; i<13; i++) {
            addFeuille(); // Feuilles de gauche
        }
        for (int i=0; i<12; i++) {
            addFeuille().flipH(); // Feuilles de droite
        }
    }

    private ImageActor addFeuille() {
        int index = feuilles.size();
        final ImageActor feuille = putActor(new ImageActor("atlas:feuille"));
        feuilles.add(feuille);

        ClickCatcher.onClick(feuille, (mx, my, pointer, button) -> {
            save.getFeuilles().add(index);
            feuille.setTouchable(Touchable.disabled);
            float rand = MathUtils.random(1f, 4f);
            feuille.addActions(
                    Actions.moveBy(rand * -100, -WORLD_HEIGHT, 5f, fastSlow),
                    Actions.hide(),
                    run(() -> feuille.setVisible(false))
            );
            if (lose()) parent.lose(Enigme1.LoseType.FEUILLE);
        });

        return feuille;
    }

    @Override
    public void start() {
        this.save = parent.getSave().getEnigme1();
        setBounds(ref.getX(), ref.getY(), ref.getWidth(), ref.getHeight());

        int i=0;
        // Partie de gauche
        //Tout en haut
        setFeuille(i++, 197, -6, -49); // Feuille de gauche

        // Au dessus à gauche du P de premiere
        setFeuille(i++, 110, 88, -49); // Feuille du centre
        setFeuille(i++, 140, 73, -58); // Feuille du haut
        setFeuille(i++, 92, 131, -27); // Feuille du bas

        // En bas à gauche du P de premiere
        setFeuille(i++, 28, 202, 0); // Feuille du centre
        setFeuille(i++, 43, 163, -18); // Feuille du haut
        setFeuille(i++, 45, 243, 22); // Feuille du bas

        // En bas à gauche du E de Enigme
        setFeuille(i++, 142, 363, 71); // Feuille du centre
        setFeuille(i++, 71, 332, 24); // Feuille du haut
        setFeuille(i++, 183, 366, 82); // Feuille du bas

        // Tout en bas à gauche
        setFeuille(i++, 248, 460, 63); // Feuille du haut
        setFeuille(i++, 310, 486, 89); // Feuille du bas
        setFeuille(i++, 283, 488, 92); // Feuille du centre

        // Partie de droite
        // Tout en haut
        setFeuille(i++, 286, 40, 16); // Feuille de droite

        // Au dessus du é de premiere
        setFeuille(i++, 357, 102, 1); // Feuille du centre
        setFeuille(i++, 330, 89, 11); // Feuille du haut
        setFeuille(i++, 360, 125, -6); // Feuille du bas

        // A droite du e de premiere
        setFeuille(i++, 468, 219, 20); // Feuille du centre
        setFeuille(i++, 442, 198, 13); // Feuille du haut
        setFeuille(i++, 468, 223, -4); // Feuille du bas

        // A droite du e de enigme
        setFeuille(i++, 450, 259, -90); // Feuille du bas
        setFeuille(i++, 447, 260, -51); // Feuille du haut

        // Tout en bas à droite
        setFeuille(i++, 351, 365, -66); // Feuille du haut
        setFeuille(i++, 347, 372, -116); // Feuille du bas
        setFeuille(i++, 355, 366, -96); // Feuille du centre

        if (lose()) parent.lose(Enigme1.LoseType.FEUILLE);
    }

    private void setFeuille(int index, float x, float y, float rotation) {
        if (index >= feuilles.size()) return;
        final ImageActor feuille = feuilles.get(index);
        if (feuille == null) return;

        if (save.getFeuilles().contains(index)) {
            feuille.setVisible(false);
        }else {
            feuille.setVisible(true);
            feuille.setTouchable(Touchable.enabled);
            feuille.move(x, y);
            feuille.setRotation(rotation);
            feuille.stop();
        }
    }

    @Override
    public void makeSpecificEvents() {
    }

    @Override
    public boolean lose() {
        return save.getFeuilles().size() >= 25;
    }

    @Override
    public boolean win() {
        return false;
    }
}
