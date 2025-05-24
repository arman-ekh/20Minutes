package io.github.TwentyMinUtesTillDown.Models.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;

public enum GunType {
    SMG(1,25,16,AssetManager.getSmgTexture(),AssetManager.getSmgReload(),0.75f),
    ShotGun(4,2,10,AssetManager.getShotGun_tex(),AssetManager.getShotGunReload(),1f),
    Revolver(1,6,20,AssetManager.getRevolver_tex(),AssetManager.getRevolverReload(),1.5f);
    private int capacity,baseDamage,projectileNum;
    private transient Sprite sprite;
    private transient Texture texture;
    private Animation<Texture> reloadAnimation ;
    private float reloadTime ;

    GunType(int projectileNum,int capacity, int baseDamage, Texture texture , Animation<Texture> reloadAnimation,float reloadTime) {
        this.capacity = capacity;
        this.baseDamage = baseDamage;
        this.texture = texture;
        this.sprite =new Sprite(texture);
        this.projectileNum = projectileNum;
        this.reloadAnimation = reloadAnimation;
        this.reloadTime = reloadTime;
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

    public float getReloadTime() {
        return reloadTime;
    }

    public Animation<Texture> getReloadAnimation() {
        return reloadAnimation;
    }
}
