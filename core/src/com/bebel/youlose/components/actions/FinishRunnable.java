package com.bebel.youlose.components.actions;

import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

/**
 * Runnable avec condition de fin
 * FINISH DOIT ETRE APPELER A LA FIN DE L'OPERATION !
 */
public abstract class FinishRunnable implements Runnable {
    boolean finish = false;

    public RunnableAction finish() {
        return Actions.run(() -> finish = true);
    }

    /**
     * Verifie si l'operation est terminée
     * @return
     */
    public boolean isFinish() {
        return finish;
    }
}