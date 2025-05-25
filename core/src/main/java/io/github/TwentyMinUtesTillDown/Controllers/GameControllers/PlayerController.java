package io.github.TwentyMinUtesTillDown.Controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Game;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Hero;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Xp;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {
    private Hero player;
    private OrthographicCamera camera;

    private BitmapFont font ;
    private GlyphLayout layout ;


    public PlayerController(Hero player ,OrthographicCamera camera ){
        this.player = player;
        this.camera = camera;
        font = new BitmapFont();
        layout = new GlyphLayout();
    }

    public void update(){
        camera.position.set(player.getPosX() + player.getPlayerSprite().getWidth() / 2f,
            player.getPosY() + player.getPlayerSprite().getHeight() / 2f,
            0);
        camera.update();

        player.getPlayerSprite().draw(Main.getBatch());

        if(player.isPlayerIdle()){
            idleAnimation();
        }
        if(player.isPlayerRunning()){
            runningAnimation();
        }
        player.update(Gdx.graphics.getDeltaTime());
        drawAmmoCount();
        handlePlayerInput();
    }
    private void drawAmmoCount() {
        int ammo = player.getWeapon().getAmmoLeft();
        int maxAmmo = player.getWeapon().getMaxAmmo();
        String ammoText = ammo + " / " + maxAmmo;

        float x = player.getPosX() + 10;
        float y = player.getPosY() + player.getPlayerSprite().getHeight() + 20;

        font.setColor(Color.WHITE);
        font.getData().setScale(1.2f);
        layout.setText(font, ammoText);
        font.draw(Main.getBatch(), layout, x, y);
    }



    public void handlePlayerInput() {
        float dx = 0;
        float dy = 0;
        player.setPlayerRunning(false);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dy += player.getSpeed();
            player.setPlayerRunning(true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dy -= player.getSpeed();
            player.setPlayerRunning(true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx += player.getSpeed();
            player.setPlayerRunning(true);


            if (player.getPlayerSprite().isFlipX()) {
                player.getPlayerSprite().flip(true, false);
                player.setPlayerRunning(true);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx -= player.getSpeed();
            player.setPlayerRunning(true);

            if (!player.getPlayerSprite().isFlipX()) {
                player.getPlayerSprite().flip(true, false);
            }
        }

        player.setPosX(player.getPosX() + dx);
        player.setPosY(player.getPosY() + dy);


        float playerX = player.getPosX();
        float playerY = player.getPosY();
        player.getPlayerSprite().setPosition(playerX, playerY);
        player.getWeapon().getWeaponSprite().setPosition(playerX+5,playerY+10);
        List<Xp> XpList = App.getCurrentGame().getXpList();
        List<Xp> XpListToRemove = new ArrayList<>();
        for (Xp xp : XpList) {
            if(xp.getCollisionRect().collidesWith(player.getRect())){
                XpListToRemove.add(xp);
                gatherXp();
            }
        }
        App.getCurrentGame().getXpList().removeAll(XpListToRemove);
    }


    private void gatherXp(){
        player.setXp(player.getXp() + 3);
        if(player.getXp() >= player.getLvl() * 20){
            player.setLvl(player.getLvl()+1);
            player.setXp(0);
            App.getCurrentGame().setLvlingUp(true);
        }
    }

    public void idleAnimation(){
        Animation<Texture> animation = player.getType().getIdle();

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {

            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }
    public void runningAnimation(){
        Animation<Texture> animation = player.getType().getRunning();

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Hero getHero() {
        return player;
    }

    public void setHero(Hero player) {
        this.player = player;
    }
    public boolean playerIsDead(){
        return player.getPlayerHealth() <= 0;
    }


    public void cheatHealth(){
        player.setPlayerHealth(player.getPlayerHealth()+1);
    }
    public void cheatLvl(){
        player.setLvl(player.getLvl() + 1);
        App.getCurrentGame().setLvlingUp(true);
    }
}
