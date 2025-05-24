package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;

public class Bullet {
    private int damage;
    private CollisionRect collisionRect;
    private float x ,y;
    private Texture texture = new Texture(AssetManager.getBullet());
    private Sprite sprite = new Sprite(texture);
    private Vector2 direction;

    public Bullet(int damage, float x, float y, Vector2 direction) {
        this.damage = damage;
        sprite.setSize(20 , 20);
        this.x = x;
        this.y = y;
        sprite.setX((App.getCurrentGame().getHero().getPosX()));
        sprite.setY((App.getCurrentGame().getHero().getPosY()));
        this.direction =direction;
        collisionRect = new CollisionRect(x , y ,sprite.getWidth() , sprite.getHeight());
    }

    public int getDamage() {
        return damage;
    }

    public float getX() {
        return x;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public float getY() {
        return y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public void move(float x , float y){
        this.x = x;
        this.y = y;
        collisionRect.setX(x);
        collisionRect.setY(y);
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }
}
