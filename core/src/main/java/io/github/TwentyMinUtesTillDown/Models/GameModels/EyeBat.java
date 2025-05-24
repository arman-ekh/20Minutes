package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterState;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterType;
import io.github.TwentyMinUtesTillDown.Models.Game;

public class EyeBat extends Monster{
    private float attackTime;
    public EyeBat(MonsterType type, float x, float y) {
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
            } else if (x> heroX) {
                dx = -1*speed;
            }

            if (y < heroY) {
                dy = speed;
            } else if (y > heroY) {
                dy = -1*speed;
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
            health = 0;
        }
    }

    @Override
    public void updateMonster() {
        this.moveTowardsPlayer();
        attackTime += Gdx.graphics.getDeltaTime();

    }

    public float getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(float attackTime) {
        this.attackTime = attackTime;
    }

    public Bullet shootAtPlayer() {
        if (attackTime < 2f) return null;

        Game game = App.getCurrentGame();
        Hero hero = game.getHero();

        float heroX = hero.getPosX();
        float heroY = hero.getPosY();

        float monsterX = this.getX();
        float monsterY = this.getY();

        Vector2 direction = new Vector2(heroX - monsterX, heroY - monsterY).nor();

        attackTime = 0f;

        return new Bullet(10, monsterX, monsterY, direction);
    }


}
