package io.github.TwentyMinUtesTillDown.Models.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;

public enum HeroType {
    Shana(AssetManager.getCharachter0_running_frames() , AssetManager.getCharacter0_idle_animation() , AssetManager.getCharacter0_idle0() , 4f,4f),
    Diamond(AssetManager.getCharachter1Running() , AssetManager.getCharachter1Idle() , AssetManager.getCharacter1_idle0() , 7f,1f),
    Scarlet(AssetManager.getCharachter2Running() , AssetManager.getCharachter2Idle() , AssetManager.getCharacter2_idle0() ,3f,5f ),
    Lilith(AssetManager.getCharachter3Running() , AssetManager.getCharachter3Idle() , AssetManager.getCharacter3_idle0() , 5f , 3f),
    Dasher(AssetManager.getCharachter4Running() , AssetManager.getCharachter4Idle() , AssetManager.getCharacter4_idle0() , 2f , 10f);
    private transient Animation<Texture> running , idle;
    private transient Texture texture;
    private float health , speed;

    HeroType(Animation<Texture> running, Animation<Texture> idle, Texture texture, float health, float speed) {
        this.running = running;
        this.idle = idle;
        this.texture = texture;
        this.health = health;
        this.speed = speed;
    }

    public Animation<Texture> getRunning() {
        return running;
    }

    public Animation<Texture> getIdle() {
        return idle;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }
}
