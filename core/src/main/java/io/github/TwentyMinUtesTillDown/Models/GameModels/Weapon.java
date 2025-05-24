package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.TwentyMinUtesTillDown.Models.Enums.GunType;

public class Weapon {

    private Bullet bullet;
    private int ammoLeft , maxAmmo;
    private int baseDamage;
    private int projectileNum;
    private GunType type;
    private transient Sprite weaponSprite;
    private boolean weaponDamageCheat;
    private int cheatExistedFor;

    public Weapon(GunType gunType) {
        this.type = gunType;
        weaponSprite = gunType.getSprite();
        baseDamage = gunType.getBaseDamage();
        weaponSprite.setX(0);
        weaponSprite.setY(0);
        weaponSprite.setSize(50,50);
        cheatExistedFor = 0;
        weaponDamageCheat = false;
        projectileNum = type.getProjectileNum();
        this.maxAmmo  = type.getCapacity();
    }

    public int getAmmoLeft() {
        return ammoLeft;
    }

    public void setAmmoLeft(int ammo) {
        this.ammoLeft = ammo;
    }
    public int getDamage(){
        if(weaponDamageCheat){
            return (baseDamage * 5 )/ 4;
        }
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public Sprite getWeaponSprite() {
        return weaponSprite;
    }

    public boolean isWeaponDamageCheat() {
        return weaponDamageCheat;
    }

    public void setWeaponDamageCheat(boolean weaponDamageCheat) {
        this.weaponDamageCheat = weaponDamageCheat;
    }

    public int getCheatExistedFor() {
        return cheatExistedFor;
    }

    public void setCheatExistedFor(int cheatExistedFor) {
        this.cheatExistedFor = cheatExistedFor;
        if(this.cheatExistedFor == 10){
            this.cheatExistedFor = 0;
            weaponDamageCheat = false;
        }
    }

    public int getProjectileNum() {
        return projectileNum;
    }

    public void setProjectileNum(int projectileNum) {
        this.projectileNum = projectileNum;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public void setWeaponSprite(Sprite weaponSprite) {
        this.weaponSprite = weaponSprite;
    }

    public GunType getType() {
        return type;
    }
}
