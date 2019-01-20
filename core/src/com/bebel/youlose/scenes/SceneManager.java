package com.bebel.youlose.scenes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * SINGLETON
 * Manager permettant de switcher entre les scenes
 */
public class SceneManager {
    private static SceneManager instance;
    private AbstractScene currentScene;

    private SceneManager() {
    }

    public static SceneManager getInstance() {
        if (instance == null)
            instance = new SceneManager();
        return instance;
    }

    public AbstractScene getCurrentScene() {
        return currentScene;
    }

    public void switchTo(final Scenes scenes) {
        try {
            if (currentScene != null)
                currentScene.dispose();
            currentScene = scenes.get();
            currentScene.create();
        } catch (IllegalAccessException e) {
            Gdx.app.error("Access", "Erreur d'acces à la scene : " + scenes.getType(), e);
        } catch (InstantiationException e) {
            Gdx.app.error("Instanciation", "Erreur lors de l'instanciation de la scene : " + scenes.getType(), e);
        }
    }
}
