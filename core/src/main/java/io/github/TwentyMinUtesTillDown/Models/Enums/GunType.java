package io.github.TwentyMinUtesTillDown.Models.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;

public enum GunType {
    SMG(1,25,16,AssetManager.getSmgTexture()),
    ShotGun(4,2,10,AssetManager.getShotGun_tex()),
    Revolver(1,6,20,AssetManager.getRevolver_tex());
    private int capacity,baseDamage,projectileNum;
    private transient Sprite sprite;
    private transient Texture texture;

    GunType(int projectileNum,int capacity, int baseDamage, Texture texture) {
        this.capacity = capacity;
        this.baseDamage = baseDamage;
        this.texture = texture;
        this.sprite =new Sprite(texture);
        this.projectileNum = projectileNum;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getProjectileNum() {
        return projectileNum;
    }
    public static GunType getTypeByString(String s) {
        try {
            return GunType.valueOf(s);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

}
