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

    public static String keyCodeToString(int keyCode) {
        switch (keyCode) {
            case Input.Keys.A: return "A";
            case Input.Keys.B: return "B";
            case Input.Keys.C: return "C";
            case Input.Keys.D: return "D";
            case Input.Keys.E: return "E";
            case Input.Keys.F: return "F";
            case Input.Keys.G: return "G";
            case Input.Keys.H: return "H";
            case Input.Keys.I: return "I";
            case Input.Keys.J: return "J";
            case Input.Keys.K: return "K";
            case Input.Keys.L: return "L";
            case Input.Keys.M: return "M";
            case Input.Keys.N: return "N";
            case Input.Keys.O: return "O";
            case Input.Keys.P: return "P";
            case Input.Keys.Q: return "Q";
            case Input.Keys.R: return "R";
            case Input.Keys.S: return "S";
            case Input.Keys.T: return "T";
            case Input.Keys.U: return "U";
            case Input.Keys.V: return "V";
            case Input.Keys.W: return "W";
            case Input.Keys.X: return "X";
            case Input.Keys.Y: return "Y";
            case Input.Keys.Z: return "Z";

            case Input.Keys.NUM_0: return "0";
            case Input.Keys.NUM_1: return "1";
            case Input.Keys.NUM_2: return "2";
            case Input.Keys.NUM_3: return "3";
            case Input.Keys.NUM_4: return "4";
            case Input.Keys.NUM_5: return "5";
            case Input.Keys.NUM_6: return "6";
            case Input.Keys.NUM_7: return "7";
            case Input.Keys.NUM_8: return "8";
            case Input.Keys.NUM_9: return "9";

            case Input.Keys.SPACE: return "SPACE";
            case Input.Keys.ENTER: return "ENTER";
            case Input.Keys.ESCAPE: return "ESC";
            case Input.Keys.BACKSPACE: return "BACKSPACE";
            case Input.Keys.TAB: return "TAB";
            case Input.Keys.SHIFT_LEFT: return "LEFT SHIFT";
            case Input.Keys.SHIFT_RIGHT: return "RIGHT SHIFT";
            case Input.Keys.CONTROL_LEFT: return "LEFT CTRL";
            case Input.Keys.CONTROL_RIGHT: return "RIGHT CTRL";
            case Input.Keys.ALT_LEFT: return "LEFT ALT";
            case Input.Keys.ALT_RIGHT: return "RIGHT ALT";
            case Input.Keys.UP: return "UP ARROW";
            case Input.Keys.DOWN: return "DOWN ARROW";
            case Input.Keys.LEFT: return "LEFT ARROW";
            case Input.Keys.RIGHT: return "RIGHT ARROW";

            case Input.Keys.F1: return "F1";
            case Input.Keys.F2: return "F2";
            case Input.Keys.F3: return "F3";
            case Input.Keys.F4: return "F4";
            case Input.Keys.F5: return "F5";
            case Input.Keys.F6: return "F6";
            case Input.Keys.F7: return "F7";
            case Input.Keys.F8: return "F8";
            case Input.Keys.F9: return "F9";
            case Input.Keys.F10: return "F10";
            case Input.Keys.F11: return "F11";
            case Input.Keys.F12: return "F12";


            default:
                return "KeyCode " + keyCode;
        }
    }

}

