package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimatedActor extends Actor {
    private final Animation<Texture> animation;
    private float stateTime = 0f;

    public AnimatedActor(Animation<Texture> animation) {
        this.animation = animation;
        setSize(96, 128);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Texture frame = animation.getKeyFrame(stateTime, true);
        batch.draw(frame, getX(), getY(), getWidth(), getHeight());
    }
}

