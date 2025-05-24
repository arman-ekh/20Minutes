package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterState;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterType;
import io.github.TwentyMinUtesTillDown.Models.Game;

public class SubNiggut extends Monster{
    private float attackTime;
    private float dashCooldown = 5f;
    private boolean isDashing = false;
    private Vector2 dashDirection = new Vector2();
    private float dashDistance = 1000f;
    private float dashedSoFar = 0f;
    private float animationTime = 0f;


    public SubNiggut(MonsterType type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
        health = type.getHealth();
        speed = type.getSpeed();
        monsterTexture = type.getTexture();
        monsterSprite = new Sprite(monsterTexture);
        monsterSprite.setSize(monsterTexture.getWidth() *3f , monsterTexture.getHeight()*3f);
        collisionRect = new CollisionRect(x , y ,monsterSprite.getWidth() ,monsterSprite.getHeight()/2);
        this.state = MonsterState.ALIVE;
        attackTime = 0f;
    }




    @Override
    public void moveTowardsPlayer() {
        Game game = App.getCurrentGame();
        Hero hero = game.getHero();
        float heroX = hero.getPosX();
        float heroY = hero.getPosY();

        float distanceX = heroX - x;
        float distanceY = heroY - y;
        float distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        float dx = 0;
        float dy = 0;
        if (distance > 400) {
            if (x < heroX) {
                dx = speed;
            } else if (x > heroX) {
                dx = -speed;
            }

            if (y < heroY) {
                dy = speed;
            } else if (y > heroY) {
                dy = -speed;
            }
        }

        x+=dx;
        y+=dy;

        monsterSprite.setX(x);
        monsterSprite.setY(y);
        collisionRect.setX(x);
        collisionRect.setY(y);
        if (collisionRect.collidesWith(hero.getRect())) {
            hero.takeDamage(type.getDamage());
        }
    }

    @Override
    public void updateMonster() {
        attackTime += Gdx.graphics.getDeltaTime();

        if (attackTime >= dashCooldown) {
            startDash();
            attackTime = 0;
        }

        if (isDashing) {
            performDash();
        } else {
            moveTowardsPlayer();
        }
    }
    private void startDash() {
        Game game = App.getCurrentGame();
        Hero hero = game.getHero();

        float dx = hero.getPosX() - getX();
        float dy = hero.getPosY() - getY();

        dashDirection.set(dx, dy).nor();
        isDashing = true;
        dashedSoFar = 0f;
    }

    private void performDash() {
        float dashSpeed = super.getSpeed() * 5;
        float frameDistance = dashSpeed ;

        float moveX = dashDirection.x * frameDistance;
        float moveY = dashDirection.y * frameDistance;

        setX(getX() + moveX);
        setY(getY() + moveY);

        getMonsterSprite().setPosition(getX(), getY());
        getCollisionRect().setX(getX());
        getCollisionRect().setY(getY());

        dashedSoFar += frameDistance;


        Hero hero = App.getCurrentGame().getHero();
        if (getCollisionRect().collidesWith(hero.getRect())) {
            hero.takeDamage(getType().getDamage());
            isDashing = false;
        }

        if (dashedSoFar >= dashDistance) {
            isDashing = false;
        }
    }




    public float getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(float attackTime) {
        this.attackTime = attackTime;
    }

    public boolean isDashing() {
        return isDashing;
    }
}
