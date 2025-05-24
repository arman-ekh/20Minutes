package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;

public class Xp {
    private transient CollisionRect collisionRect;
    private float x ,y;
    private transient Texture texture = AssetManager.getXp();
    private transient Sprite sprite = new Sprite(texture);

    public Xp(float x, float y) {
        this.x = x;
        this.y = y;
        sprite.setSize(20 , 20);
        sprite.setX(x);
        sprite.setY(y);
        collisionRect = new CollisionRect(x , y ,sprite.getWidth() , sprite.getHeight());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

}
