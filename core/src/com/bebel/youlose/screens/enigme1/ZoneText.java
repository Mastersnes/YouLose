package com.bebel.youlose.screens.enigme1;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.interfaces.Startable;
import com.bebel.youlose.components.refound.FontParameter;
import com.bebel.youlose.components.refound.abstrait.AbstractGroup;
import com.bebel.youlose.components.refound.actors.ui.ImageActor;
import com.bebel.youlose.components.refound.actors.ui.TypingActor;

import static com.badlogic.gdx.utils.Align.bottomLeft;

/**
 * Acteur constituant la zone de texte sous le globe
 */
public class ZoneText extends AbstractGroup implements Startable {
    private final Enigme1 parent;
    private ImageActor cadre;
    private TypingActor texte;
//    private TypingActor ok;

    public ZoneText(final Enigme1 parent) {
        super();
        this.parent = parent;
        setTouchable(Touchable.childrenOnly);
        create();
    }

    public void create() {
        putActor(cadre = new ImageActor("atlas:bloc_texte"))
            .move(cadre.centerX(), 0, bottomLeft);

        final BitmapFont font = manager.getFont("general/arial.ttf", new FontParameter(20, Color.valueOf("#6b8467")));

        setBounds(0, 0, cadre.getWidth(), cadre.getHeight());
        putActor(texte = new TypingActor("", font));
//        putActor(ok = new TypingActor("enigme1.ok", font)).setVisible(false);
    }

    @Override
    public void start() {
        setTexte("enigme1.partie1", true);
    }


    @Override
    public void refresh(Color color) {
        super.refresh(color);
        texte.move(40, 20);
//        ok.move(texte.getX() + texte.getWidth() + 5, 20);
//        ok.pause();
    }

    public void setTexte(final String key, boolean showOk) {
        texte.restart(key);

//        ok.restart();
//        ok.pause();
//        ok.setVisible(showOk);

        refresh(getColor());
    }

    @Override
    public void makeSpecificEvents() {
//        texte.setTypingListener(new TypingListener() {
//            @Override
//            public void event(final String event) {
//            }
//            @Override
//            public void end() {
//                ok.restart();
//            }
//            @Override
//            public String replaceVariable(String variable) {
//                return null;
//            }
//            @Override
//            public void onChar(Character ch) {
//            }
//        });
    }
}
