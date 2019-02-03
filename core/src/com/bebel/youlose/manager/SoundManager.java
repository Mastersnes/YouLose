package com.bebel.youlose.manager;

public class SoundManager {
    private AssetsManager parent;
    private int music;
    private int sound;

    public SoundManager(final AssetsManager manager) {
        parent = manager;
    }

    public int getMusic() {
        return music;
    }
    public void setMusic(int music) {
        this.music = music;
    }

    public int getSound() {
        return sound;
    }
    public void setSound(int sound) {
        this.sound = sound;
    }
}
