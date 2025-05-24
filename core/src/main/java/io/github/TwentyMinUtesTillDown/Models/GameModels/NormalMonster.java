package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterState;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterType;

public class NormalMonster extends Monster{
    public NormalMonster(MonsterType type, float x, float y) {
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
    }
}
