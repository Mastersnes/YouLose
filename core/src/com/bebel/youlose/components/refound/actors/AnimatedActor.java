package com.bebel.youlose.components.refound.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.interfaces.Animable;
import com.bebel.youlose.components.refound.abstrait.AbstractActor;
import com.bebel.youlose.components.runnable.OneTimeRunnable;

import java.util.HashMap;
import java.util.Map;

import static com.bebel.youlose.utils.RunnableUtils.oneTime;

/**
 * Acteur effectuant une animation
 */
public class AnimatedActor extends AbstractActor implements Animable {
    private Map<Integer, OneTimeRunnable> runByFrame = new HashMap<>();
    private OneTimeRunnable onFinishRun;

    private float speed, progress;
    private final Animation<TextureRegion> animation;
    private boolean flipH, flipV;
    private boolean pause;

    public AnimatedActor(final String key, final Animation.PlayMode playMode, final float fps) {
        super();
        setName(key);
        setTouchable(Touchable.disabled);
        animation = manager.getAnimation(key, playMode, fps);
        speed = 1f;
        pause();
    }

    @Override
    public void setSpeed(final float speed) {
        this.speed = speed;
    }

    @Override
    public void pause() {
        pause = true;
    }

    @Override
    public void resume() {
        pause = false;
    }

    public void play() {
        play(false, false);
    }
    public void play(final boolean flipH, final boolean flipV) {
        restart(flipH, flipV);
    }

    @Override
    public void restart() {
        restart(false, false);
    }
    public void restart(final boolean flipH, final boolean flipV) {
        progress = 0f;
        pause = false;
        this.flipH = flipH;
        this.flipV = flipV;
        for (final OneTimeRunnable run : runByFrame.values()) run.restart();
        if (onFinishRun != null) onFinishRun.restart();
    }

    @Override
    public void stop() {
        super.stop();
        progress = 0f;
        pause = true;
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (!pause) {
            progress += speed * Gdx.graphics.getDeltaTime();
            if (isFinish()) finish();
        }

        final int frameIndex = animation.getKeyFrameIndex(progress);
            final TextureRegion frame = animation.getKeyFrame(progress);
            frame.flip(flipH, flipV);
            batch.draw(frame, getX(), getY());
            frame.flip(flipH, flipV);

        if (!pause) {
            final OneTimeRunnable run = runByFrame.get(frameIndex);
            if (run != null)run.run();

            if (isFinish() && onFinishRun != null) onFinishRun.run();
        }
    }

    @Override
    public void finish() {
        progress = animation.getKeyFrames().length * animation.getFrameDuration();
    }

    @Override
    public boolean isFinish() {
        return progress >= animation.getKeyFrames().length * animation.getFrameDuration();
    }

    public boolean isWorking() {
        return !pause && !isFinish();
    }

    @Override
    public void onFinish(final Runnable run) {
        onFinishRun = oneTime(run);
    }

    public void onFrame(final int frame, final Runnable runnable) {
        runByFrame.put(frame, oneTime(runnable));
    }
}