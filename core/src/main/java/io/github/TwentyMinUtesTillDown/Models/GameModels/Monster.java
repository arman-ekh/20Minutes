package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterState;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterType;
import io.github.TwentyMinUtesTillDown.Models.Game;

public  class Monster {
    protected MonsterType type;
    protected float x , y;
    protected float health;
    protected float speed;
    protected float time = 0f;
    protected transient Texture monsterTexture;
    protected transient Sprite monsterSprite;
    protected transient CollisionRect collisionRect;
    protected MonsterState state;
    protected float deathTime = 0f;

    public Monster(MonsterType type, float x, float y) {
    }

    public Monster() {
    }

    public float getDeathTime() {
        return deathTime;
    }

    public void setDeathTime(float deathTime) {
        this.deathTime = deathTime;
    }

    public MonsterState getState() {
        return state;
    }

    public void setState(MonsterState state) {
        this.state = state;
    }

    public MonsterType getType() {
        return type;
    }

    public void updateMonster(){
        this.moveTowardsPlayer();
    }

    public void moveTowardsPlayer(){

        Game game = App.getCurrentGame();
        Hero hero = game.getHero();
        float heroX =hero.getPosX();
        float heroY = hero.getPosY();
        float dx = speed;
        float dy = speed;
        if(x > heroX){
             dx = -1*speed;
        }
        if(y > heroY){
            dy *= -1;
        }
        x += dx;
        y += dy;
        monsterSprite.setX(x);
        monsterSprite.setY(y);
        collisionRect.setX(x);
        collisionRect.setY(y);

        if(collisionRect.collidesWith(hero.getRect())){
            hero.takeDamage(type.getDamage());
            if (type.equals(MonsterType.TreeMonster)){
                return;
            }
            health =0;
        }
    }

    public Sprite getMonsterSprite() {
        return monsterSprite;
    }

    public float getHealth() {
        return health;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public Texture getMonsterTexture() {
        return monsterTexture;
    }

    public void setMonsterTexture(Texture monsterTexture) {
        this.monsterTexture = monsterTexture;
    }

    public void setMonsterSprite(Sprite monsterSprite) {
        this.monsterSprite = monsterSprite;
    }
    public void setCollisionRect(CollisionRect rect) {
        this.collisionRect = rect;
    }

}
