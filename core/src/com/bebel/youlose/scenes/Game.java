package com.bebel.youlose.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Game extends AbstractScene {
    private Texture bucketImage;
    private Rectangle bucket;

    private Texture dropImage;
    private Array<Rectangle> drops;
    private Sound dropSound;
    private long lastDrop;

    private Music rainMusic;

    @Override
    public void createScene() {
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));
        bucket = new Rectangle(800 / 2 - 64 / 2, 20, 64, 64);

        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        drops = new Array<Rectangle>();
        addDrop();

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        rainMusic.setLooping(true);
        rainMusic.play();
    }

    @Override
    public void render (final float delta) {
        batch.draw(bucketImage, bucket.x, bucket.y);

        for (final Iterator<Rectangle> iterator = drops.iterator(); iterator.hasNext();) {
            final Rectangle drop = iterator.next();

            batch.draw(dropImage, drop.x, drop.y);

            drop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (drop.y + drop.height < 0) iterator.remove();
            if (drop.overlaps(bucket)) {
                dropSound.play();
                iterator.remove();
            }
        }

        if (TimeUtils.nanoTime() - lastDrop > 1000000000) {
            addDrop();
        }
    }

    private void addDrop() {
        final Rectangle drop = new Rectangle();
        drop.x = MathUtils.random(0, 800 - dropImage.getWidth());
        drop.y = 480;
        drop.width = dropImage.getWidth();
        drop.height = dropImage.getHeight();
        drops.add(drop);
        lastDrop = TimeUtils.nanoTime();
    }

    @Override
    public void makeEvents(final float delta) {
        if (Gdx.input.isTouched()) {
            camera.unproject(mousePosition);
            bucket.x = mousePosition.x - bucket.width / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.Q))
            bucket.x -= 200 * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            bucket.x += 200 * delta;

        if (bucket.x < 0) bucket.x = 0;
        else if (bucket.x > 800 - bucket.width) bucket.x = 800 - bucket.width;
    }

    @Override
    public void disposeScene () {
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }
}
