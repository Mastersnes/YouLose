package com.bebel.youlose.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;
import java.util.Locale;
import java.util.Properties;

/**
 * Manager de configuration
 */
public class ConfigManager {
    private AssetsManager parent;
    private String language;
    private float sound;
    private float music;
    private boolean fullscreen;

    public ConfigManager(final AssetsManager manager) {
        this.parent = manager;
        final FileHandle file = Gdx.files.external("/youlose/youlose.conf");
        try (final InputStream in = file.read()) {
            final Properties prop = new Properties();
            prop.load(in);
            language = prop.getProperty("language", Locale.getDefault().getLanguage());
            sound = Float.valueOf(prop.getProperty("sound", "50"));
            music = Float.valueOf(prop.getProperty("music", "50"));
            setFullscreen(Boolean.valueOf(prop.getProperty("fullscreen", String.valueOf(true))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sauvegarde les configurations
     */
    public void save() {
        final FileHandle file = Gdx.files.external("/youlose/youlose.conf");
        try (final OutputStreamWriter out = (OutputStreamWriter) file.writer(false)) {
            final Properties prop = new Properties();
            prop.setProperty("language", language);
            prop.setProperty("sound", String.valueOf(sound));
            prop.setProperty("music", String.valueOf(music));
            prop.setProperty("fullscreen", String.valueOf(fullscreen));
            prop.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getMusic() {
        return music;
    }
    public void setMusic(float music) {
        this.music = music;
    }

    public float getSound() {
        return sound;
    }
    public void setSound(float sound) {
        this.sound = sound;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(final boolean fullscreen) {
        this.fullscreen = fullscreen;

        if (!Gdx.graphics.supportsDisplayModeChange()) return;

        if (!fullscreen) {
            final Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
            Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
        } else Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    }
}
