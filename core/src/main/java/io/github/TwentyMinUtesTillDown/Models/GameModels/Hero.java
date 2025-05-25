package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Enums.GunType;
import io.github.TwentyMinUtesTillDown.Models.Enums.HeroType;

public class Hero {
    private transient Texture playerTexture;
    private transient Sprite playerSprite;
    private HeroType type;
    private float posX;
    private float posY;
    private float playerHealth;
    private float maxPlayerHealth ;
    private CollisionRect rect;
    private float time = 0f;
    private float speed = 4f;
    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;
    private Weapon weapon;
    private boolean isDamaged;
    private int xp , lvl;
    private int cheatExistedFor;
    private boolean speedCheat;
    private int killCount;
    private int nextKillAmountNeededForHealth;
    private transient Animation<Texture> takeDamageAnimation;
    private float takeDamageAnimElapsedTime = 0f;
    private boolean isPlayingTakeDamageAnimation = false;



    public float getTakeDamageAnimationTime() {
        return takeDamageAnimElapsedTime;
    }

    public void setTakeDamageAnimationTime(float takeDamageAnimationTime) {
        this.takeDamageAnimElapsedTime = takeDamageAnimationTime;
    }

    public Hero(HeroType type , GunType gunType) {
        this.type = type;
        takeDamageAnimElapsedTime =0f;
        lvl = 1;
        playerTexture = type.getTexture();
        this.playerHealth = type.getHealth();
        this.speed = type.getSpeed();
        posX = 0;
        posY = 0;
        playerSprite = new Sprite(playerTexture);
        playerSprite.setSize(playerTexture.getWidth() * 3f, playerTexture.getHeight() * 3f);
        playerSprite.setPosition(posX, posY);
        rect = new CollisionRect(posX, posY, playerSprite.getWidth(), playerSprite.getHeight());
        weapon = new Weapon(gunType);
        isDamaged = false;
        maxPlayerHealth = type.getHealth();
    }

    private float invincibleTime = 0f;
    private static final float INVINCIBLE_DURATION = 2f;

    public void update(float delta) {
        if (invincibleTime > 0) {
            invincibleTime -= delta;
            if (invincibleTime < 0) {
                invincibleTime = 0;
            }
        }

        if (isPlayingTakeDamageAnimation && takeDamageAnimation != null) {
            takeDamageAnimElapsedTime += delta;
            Texture currentFrame = takeDamageAnimation.getKeyFrame(takeDamageAnimElapsedTime, false);
            playerSprite.setTexture(currentFrame);

            if (takeDamageAnimation.isAnimationFinished(takeDamageAnimElapsedTime)) {
                isPlayingTakeDamageAnimation = false;
                playerSprite.setTexture(playerTexture);
            }
        }
    }


    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        App.playSound(AssetManager.getLevelUpSound());
        this.lvl = lvl;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void takeDamage(float damage) {
        if (invincibleTime <= 0) {
            App.playSound(AssetManager.getDamageSound());
            setPlayerHealth(getPlayerHealth() - damage);
            invincibleTime = INVINCIBLE_DURATION;
            isDamaged = true;

            takeDamageAnimation = AssetManager.getFireBallExplosion();
            takeDamageAnimElapsedTime = 0f;
            isPlayingTakeDamageAnimation = true;
        }
    }



    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
        playerSprite.setX(posX);
        rect.setX(posX);
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
        playerSprite.setY(posY);
        rect.setY(posY);
    }

    public HeroType getType() {
        return type;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }


    public float getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(float playerHealth) {
        float oldHealth = this.playerHealth;
        if(playerHealth < oldHealth){
            isDamaged = true;
        }
        if(playerHealth > maxPlayerHealth){
            return;
        }
        this.playerHealth = playerHealth;
    }

    public CollisionRect getRect() {
        return rect;
    }


    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }



    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }


    public float getSpeed() {
        if(speedCheat){
            return speed * 2;
        }
        return speed;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getMaxPlayerHealth() {
        return maxPlayerHealth;
    }

    public void setMaxPlayerHealth(float maxPlayerHealth) {
        this.maxPlayerHealth = maxPlayerHealth;
    }

    public int getCheatExistedFor() {
        return cheatExistedFor;
    }

    public void setCheatExistedFor(int cheatExistedFor) {
        this.cheatExistedFor = cheatExistedFor;
        if(this.cheatExistedFor == 10){
            this.cheatExistedFor =0;
            speedCheat = false;
        }
    }

    public boolean isSpeedCheat() {
        return speedCheat;
    }

    public void setSpeedCheat(boolean speedCheat) {
        this.speedCheat = speedCheat;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }
}
