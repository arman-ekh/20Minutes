package io.github.TwentyMinUtesTillDown.Models;

import com.badlogic.gdx.Input;

public class KeyBindings {
    private int moveUp = Input.Keys.W;
    private int moveDown = Input.Keys.S;
    private int moveLeft = Input.Keys.A;
    private int moveRight = Input.Keys.D;
    private int attack = Input.Keys.SPACE;
    private int reload = Input.Keys.R;
    private int cheatTime = Input.Keys.T;
    private int cheatHealth = Input.Keys.H;
    private int cheatBossFight = Input.Keys.B;
    private int cheatLvl  = Input.Keys.Y;
    private int cheatDamage = Input.Keys.G;

    public int getReload() {
        return reload;
    }
    public void setReload(int reload) {
        this.reload = reload;
    }

    public int getCheatTime() {
        return cheatTime;
    }
    public void setCheatTime(int cheatTime) {
        this.cheatTime = cheatTime;
    }

    public int getCheatHealth() {
        return cheatHealth;
    }
    public void setCheatHealth(int cheatHealth) {
        this.cheatHealth = cheatHealth;
    }

    public int getCheatBossFight() {
        return cheatBossFight;
    }
    public void setCheatBossFight(int cheatBossFight) {
        this.cheatBossFight = cheatBossFight;
    }

    public int getCheatLvl() {
        return cheatLvl;
    }
    public void setCheatLvl(int cheatLvl) {
        this.cheatLvl = cheatLvl;
    }

    public int getCheatDamage() {
        return cheatDamage;
    }
    public void setCheatDamage(int cheatDamage) {
        this.cheatDamage = cheatDamage;
    }

    public int getMoveUp() { return moveUp; }
    public void setMoveUp(int moveUp) { this.moveUp = moveUp; }

    public int getMoveDown() { return moveDown; }
    public void setMoveDown(int moveDown) { this.moveDown = moveDown; }

    public int getMoveLeft() { return moveLeft; }
    public void setMoveLeft(int moveLeft) { this.moveLeft = moveLeft; }

    public int getMoveRight() { return moveRight; }
    public void setMoveRight(int moveRight) { this.moveRight = moveRight; }

    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }
}

