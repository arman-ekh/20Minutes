package io.github.TwentyMinUtesTillDown.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.TwentyMinUtesTillDown.Controllers.GameControllers.GunController;
import io.github.TwentyMinUtesTillDown.Controllers.GameControllers.MonstersController;
import io.github.TwentyMinUtesTillDown.Controllers.GameControllers.PlayerController;
import io.github.TwentyMinUtesTillDown.Controllers.GameControllers.WorldController;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.Enums.LevelUpType;
import io.github.TwentyMinUtesTillDown.Models.Game;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Hero;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Monster;
import io.github.TwentyMinUtesTillDown.Models.Result;
import io.github.TwentyMinUtesTillDown.View.GameView;

import java.util.List;

import static io.github.TwentyMinUtesTillDown.Models.Enums.LevelUpType.MAX_AMMO;

public class GameController {
    private OrthographicCamera camera;

    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private GunController weaponController;
    private MonstersController monstersController;
    private Game game;
    private int frameCounter;
    private boolean isMouseDown = false;
    private float shootTimer = 0f;
    private final float SHOOT_INTERVAL = 0.1f;
    private boolean isAiming = false;
    private boolean isLevelUpMenuActive = false;
    private int second;
    private float accumulatedTime = 0f;



    public void setAiming(boolean aiming) {
        isAiming = aiming;
    }


    public void setView(GameView view,Hero hero) {
        this.view = view;
        playerController = new PlayerController(hero , view.getCamera());
        worldController = new WorldController(playerController);
        monstersController = new MonstersController();
        game = App.getCurrentGame();
        frameCounter = 0;
        weaponController = new GunController(game.getHero().getWeapon());
        second =0;
    }

    public boolean isLevelUpMenuActive() {
        return isLevelUpMenuActive;
    }

    public void setLevelUpMenuActive(boolean levelUpMenuActive) {
        isLevelUpMenuActive = levelUpMenuActive;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }


    public void updateGame() {
        if (view != null) {
            float delta = Gdx.graphics.getDeltaTime();

            if (isLevelUpMenuActive) {
                return;
            }

            if (game.isLvlingUp()) {
                isLevelUpMenuActive = true;
                game.setLvlingUp(false);
                view.showLevelUpMenu();
                return;
            }

            worldController.update();
            playerController.update();
            weaponController.update(camera);
            monstersController.monstersUpdate(camera);




            shootTimer += delta;
            if (isMouseDown && shootTimer >= SHOOT_INTERVAL) {
                int mouseX = Gdx.input.getX();
                int mouseY = Gdx.input.getY();
                weaponController.handleWeaponShoot(mouseX, mouseY);
                shootTimer = 0f;
            }

            accumulatedTime += delta;
            while (accumulatedTime >= 1f) {
                game.setSecond(game.getSecond() + 1);
                everySecondCycle(camera);
                accumulatedTime -= 1f;
            }

            if (isAiming) {
                aimAndShootAtNearestMonster();
            }

        }
    }
    private void aimAndShootAtNearestMonster() {
        Hero hero = game.getHero();
        Vector2 heroPos = new Vector2(hero.getPosX(), hero.getPosY());

        List<Monster> monsters = App.getCurrentGame().getMonsters();
        if (monsters.isEmpty()) return;

        Monster closest = null;
        float minDist = Float.MAX_VALUE;

        for (Monster m : monsters) {
            float dist = heroPos.dst(m.getX(), m.getY());
            if (dist < minDist) {
                minDist = dist;
                closest = m;
            }
        }

        if (closest != null) {
            Vector3 monsterPos3 = new Vector3(closest.getX()+32, closest.getY()+32, 0);
            camera.project(monsterPos3);
            int screenX = (int) monsterPos3.x;
            int screenY = Gdx.graphics.getHeight() - (int) monsterPos3.y-32;


            weaponController.handleWeaponRotation(screenX, screenY);

            if (shootTimer >= SHOOT_INTERVAL) {
                weaponController.handleWeaponShoot(screenX, screenY);
                shootTimer = 0f;
            }
        }
    }



    public void handleLevelUpOption(LevelUpType option) {
        Hero hero = game.getHero();

        switch (option) {
            case SPEED:
                hero.setSpeedCheat(true);
                break;
            case HEALTH:
                hero.setMaxPlayerHealth(hero.getMaxPlayerHealth() + 1);
                break;
            case DAMAGE:
                hero.getWeapon().setWeaponDamageCheat(true);
                break;
            case PROJECTILE:
                hero.getWeapon().setProjectileNum(hero.getWeapon().getProjectileNum() + 1);
                break;
            case MAX_AMMO:
                hero.getWeapon().setMaxAmmo(hero.getWeapon().getMaxAmmo() + 5);
                break;
        }

        isLevelUpMenuActive = false;

        if (view != null) {
            view.hideLevelUpMenu();
        }
        game.getHero().setPlayerHealth(game.getHero().getPlayerHealth() + 1);
    }

    public Result gameIsOver(){
        boolean gameOver = playerController.playerIsDead();
        boolean gameWon = game.getSecond() >= game.getFullTime();
        if(gameOver || gameWon ){
            return new Result(true ,"");
        }
        return new Result(false , "");
    }







    private void everySecondCycle(Camera camera){
        monstersController.spawnMonster(camera);
        Hero hero = App.getCurrentGame().getHero();
        if(hero.getWeapon().isWeaponDamageCheat()){
            hero.getWeapon().setCheatExistedFor(hero.getWeapon().getCheatExistedFor() + 1);
        }
        if(hero.isSpeedCheat()){
            hero.setCheatExistedFor(hero.getCheatExistedFor() + 1);
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public GunController getWeaponController() {
        return weaponController;
    }

    public boolean isMouseDown() {
        return isMouseDown;
    }

    public void setMouseDown(boolean mouseDown) {
        isMouseDown = mouseDown;
    }

    public float getShootTimer() {
        return shootTimer;
    }

    public void setShootTimer(float shootTimer) {
        this.shootTimer = shootTimer;
    }

    public float getSHOOT_INTERVAL() {
        return SHOOT_INTERVAL;
    }

    public void cheatTime(int amount){
        App.getCurrentGame().setSecond(amount);
    }
}
