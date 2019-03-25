package com.bebel.youlose.components.refound.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.bebel.youlose.components.refound.abstrait.AbstractActor;
import com.bebel.youlose.components.runnable.OneTimeRunnable;
import com.bebel.youlose.utils.IActor;

import java.util.HashMap;
import java.util.Map;

import static com.bebel.youlose.utils.RunnableUtils.oneTime;

/**
 * Acteur effectuant une animation
 */
public class AnimatedActor extends AbstractActor {
    private Map<Integer, OneTimeRunnable> runByFrame = new HashMap<>();

    private float speed, stateTime;
    private final Animation<TextureRegion> animation;
    private boolean flipH, flipV;
    private boolean working; private boolean finish;

    public AnimatedActor(final String key, final Animation.PlayMode playMode, final float fps) {
        super();
        setTouchable(Touchable.disabled);
        animation = manager.getAnimation(key, playMode, fps);
        speed = 1f;
    }

    public void setSpeed(final float speed) {
        this.speed = speed;
    }

    public AnimatedActor play() {
        return play(false, false);
    }

    public AnimatedActor play(final boolean flipH, final boolean flipV) {
        stateTime = 0f;
        working = true; finish = false;
        this.flipH = flipH; this.flipV = flipV;
        return this;
    }

    @Override
    public void stop() {
        super.stop();
        stateTime = 0f;
        working = false; finish = true;
        flipH = false; flipV = false;
        for (final OneTimeRunnable run : runByFrame.values()) run.restart();
    }

    public void finish() {
        stateTime = animation.getKeyFrames().length * animation.getFrameDuration();
        working = true;
    }

    @Override
    public void draw(final Batch batch, final float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (working) {
            stateTime += speed * Gdx.graphics.getDeltaTime();
            final int frameIndex = animation.getKeyFrameIndex(stateTime);
            final TextureRegion frame = animation.getKeyFrame(stateTime);
            frame.flip(flipH, flipV);
            batch.draw(frame, getX(), getY());
            frame.flip(flipH, flipV);

            finish = animation.isAnimationFinished(stateTime);

            final OneTimeRunnable run = runByFrame.get(frameIndex);
            if (run != null) run.run();
        }
    }

    public boolean isWorking() {
        return working;
    }
    public boolean isFinish() {
        return finish;
    }

    public void onFrame(final int frame, final Runnable runnable) {
        runByFrame.put(frame, oneTime(runnable));
    }
}