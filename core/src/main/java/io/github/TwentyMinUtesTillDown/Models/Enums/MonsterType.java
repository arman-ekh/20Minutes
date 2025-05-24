package io.github.TwentyMinUtesTillDown.Models.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;

public enum MonsterType {
    TentacleMonster(AssetManager.getBrainMonster0_tex() , 25f,2.5f,1f),
    TreeMonster(AssetManager.getTreeMonsterTex() , Float.MAX_VALUE , 0,0.5f),
    EyeBat(AssetManager.getEyeBat_tex() , 50f ,2, 1f),
    SubNigget(AssetManager.getSubNiggetTEx() , 400f , 2.5f , 1.5f);

    private transient Texture texture;
    private  transient Sprite sprite;
    private float health , speed ,damage;

    MonsterType(Texture texture, float health, float speed,float damage) {
        this.texture = texture;
        this.health = health;
        this.speed = speed;
        this.damage = damage;
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

    public Sprite getSprite() {
        return sprite;
    }

    public float getDamage() {
        return damage;
    }
}
