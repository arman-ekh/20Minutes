package io.github.TwentyMinUtesTillDown.Controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterType;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Bullet;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Hero;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Monster;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GunController {
    private Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    private boolean isReloading = false;
    private float reloadTimer = 0f;
    private float reloadDuration = 1.5f;
    private float reloadStateTime = 0f;
    private Animation<Texture> reloadAnimation;

    public GunController(Weapon weapon) {
        this.weapon = weapon;
        weapon.setAmmoLeft(weapon.getMaxAmmo());
        this.reloadDuration = weapon.getType().getReloadTime();
        this.reloadAnimation = weapon.getType().getReloadAnimation();
    }


    public void update(Camera camera) {
        reloadDuration = weapon.getType().getReloadTime();
        if (Gdx.input.isKeyJustPressed(Input.Keys.R) && !isReloading && weapon.getAmmoLeft() < weapon.getMaxAmmo()) {
            startReload();
        }
        if (isReloading) {
            reloadTimer += Gdx.graphics.getDeltaTime();
            reloadStateTime += Gdx.graphics.getDeltaTime();

            Texture currentFrame = reloadAnimation.getKeyFrame(reloadStateTime, false);
            Main.getBatch().draw(currentFrame,
                weapon.getWeaponSprite().getX(),
                weapon.getWeaponSprite().getY(),
                weapon.getWeaponSprite().getWidth(),
                weapon.getWeaponSprite().getHeight());

            if (reloadTimer >= reloadDuration) {
                isReloading = false;
                reloadTimer = 0f;
                reloadStateTime = 0f;
                weapon.setAmmoLeft(weapon.getMaxAmmo());
            }
        } else {
            weapon.getWeaponSprite().draw(Main.getBatch());
        }

        updateBullets(camera);
    }

    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = weapon.getWeaponSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (3.14 - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(int x, int y) {
        Hero hero = App.getCurrentGame().getHero();
        if (isReloading || weapon.getAmmoLeft() <= 0) {
            if (!isReloading) startReload();
            return;
        }

        float centerX = (float) Gdx.graphics.getWidth() / 2;
        float centerY = (float) Gdx.graphics.getHeight() / 2;

        Vector2 baseDirection = new Vector2(x - centerX, centerY - y).nor();

        int numProjectiles = weapon.getProjectileNum();
        if (numProjectiles < 1) numProjectiles = 1;

        float spreadAngle = 15f;
        float angleStep = (numProjectiles > 1) ? spreadAngle / (numProjectiles - 1) : 0f;
        float startAngle = -spreadAngle / 2f;

        for (int i = 0; i < numProjectiles; i++) {
            float angleOffset = startAngle + i * angleStep;


            Vector2 bulletDirection = new Vector2(baseDirection).rotateDeg(angleOffset);

            Bullet bullet = new Bullet(
                weapon.getDamage(),
                hero.getPosX(),
                hero.getPosY(),
                bulletDirection
            );
            bullets.add(bullet);
        }

        weapon.setAmmoLeft(weapon.getAmmoLeft() - 1);
        if (weapon.getAmmoLeft() <= 0) {
            startReload();
        }
    }



    private void updateBullets(Camera camera) {
        Iterator<Bullet> iter = bullets.iterator();

        while (iter.hasNext()) {
            Bullet b = iter.next();


            b.move(b.getX() + b.getDirection().x * 10,b.getY() + b.getDirection().y * 10);
            b.getSprite().setX(b.getX());
            b.getSprite().setY(b.getY());

            Rectangle bounds = b.getSprite().getBoundingRectangle();
            boolean inView = camera.frustum.boundsInFrustum(
                bounds.getX() + bounds.getWidth() / 2f,
                bounds.getY() + bounds.getHeight() / 2f,
                0,
                bounds.getWidth() / 2f,
                bounds.getHeight() / 2f,
                1f
            );

            if (inView) {
                List<Monster> monsters = App.getCurrentGame().getMonsters();
                b.getSprite().draw(Main.getBatch());
                for (Monster monster : monsters) {
                    if (monster.getCollisionRect().collidesWith(b.getCollisionRect())) {
                        try {
                            iter.remove();
                        } catch (Exception e) {
                            // should not happen
                        }

                        monster.setHealth(monster.getHealth() - b.getDamage());

                        if(!monster.getType().equals(MonsterType.TreeMonster)){
                            float knockbackAmount = 20f;
                            Vector2 knockback = new Vector2(b.getDirection()).scl(knockbackAmount);
                            float newX = monster.getX() + knockback.x;
                            float newY = monster.getY() + knockback.y;
                            monster.setX(newX);
                            monster.setY(newY);
                            monster.getMonsterSprite().setPosition(newX, newY);
                            monster.getCollisionRect().setX(newX);
                            monster.getCollisionRect().setY(newY);
                        }
                    }
                }
            } else {
                iter.remove();
            }
        }
    }
    private void startReload() {
        isReloading = true;
        reloadTimer = 0f;
        reloadStateTime = 0f;
    }



}
