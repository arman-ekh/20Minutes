package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.TwentyMinUtesTillDown.Controllers.EndGameController;
import io.github.TwentyMinUtesTillDown.Controllers.GameController;
import io.github.TwentyMinUtesTillDown.Controllers.GameControllers.HudController;
import io.github.TwentyMinUtesTillDown.Controllers.PauseController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Enums.LevelUpType;
import io.github.TwentyMinUtesTillDown.Models.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    private HudController hudController;
    private OrthographicCamera camera;
    private OrthographicCamera hudCamera;
    private Stage levelUpStage;
    private boolean isLevelUpMenuVisible = false;
    private boolean cheatActivated=false;
    private boolean bossFightCheatActivated=false;





    public OrthographicCamera getCamera() {
        return camera;
    }

    public GameView(GameController controller) {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.controller = controller;
        controller.setView(this,App.getCurrentGame().getHero());
        levelUpStage = new Stage(new ScreenViewport());
        createLevelUpMenu();
        Main.setCurrentGameView(this);
        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudController = new HudController();
    }


    private List<LevelUpType> currentLevelUpOptions = new ArrayList<>();

    private void createLevelUpMenu() {
        levelUpStage.clear();


        List<LevelUpType> allOptions = new ArrayList<>(Arrays.asList(LevelUpType.values()));
        Collections.shuffle(allOptions);
        currentLevelUpOptions = allOptions.subList(0, 3);

        List<Image> options = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            final LevelUpType type = currentLevelUpOptions.get(i);
            Image img = new Image(type.getTexture());
            img.setSize(100, 100);

            img.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    controller.handleLevelUpOption(type);
                    hideLevelUpMenu();
                }
            });
            options.add(img);
        }

        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;
        float spacing = 150;

        options.get(0).setPosition(centerX - spacing - 50, centerY - 50);
        options.get(1).setPosition(centerX - 50, centerY - 50);
        options.get(2).setPosition(centerX + spacing - 50, centerY - 50);

        for (Image option : options) {
            levelUpStage.addActor(option);
        }
    }


    public void hideLevelUpMenu() {
        isLevelUpMenuVisible = false;
        Gdx.input.setInputProcessor(this);
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this);

        controller.setCamera(camera);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        if(App.isGreyGame()){
            Main.getBatch().setShader(Main.getGrayscaleShader());
        }
        Main.getBatch().setProjectionMatrix(camera.combined);
        Main.getBatch().begin();
        controller.updateGame();
        Main.getBatch().end();

        Main.getBatch().setProjectionMatrix(hudCamera.combined);
        Main.getBatch().begin();
        hudController.updateHud();
        Main.getBatch().end();
        Main.getBatch().setShader(null);

        if (isLevelUpMenuVisible) {
            levelUpStage.act(delta);
            levelUpStage.draw();
        } else {
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
        }

        if(controller.gameIsOver().isSuccessful()) {
            Main.getMain().setScreen(new EndGameView(new EndGameController()));
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == App.keyBindings.getAttack()) {
            controller.setAiming(true);
            return true;
        } else if (keycode == Input.Keys.ESCAPE) {
            Main.setCurrentGameView(this);
            Main.getMain().setScreen(new PauseView(new PauseController()));
        } else if (keycode == App.keyBindings.getCheatHealth()) {
            if(!cheatActivated){
                controller.getPlayerController().cheatHealth();
                cheatActivated = true;
            }
        } else if (keycode == App.keyBindings.getCheatBossFight()) {
            int halfTime = App.getCurrentGame().getFullTime()/2;
            if(!cheatActivated && !bossFightCheatActivated && App.getCurrentGame().getSecond() < halfTime ){
                controller.cheatTime(halfTime);
                cheatActivated = true;
                bossFightCheatActivated = true;
            }
        } else if (keycode == App.keyBindings.getCheatTime()) {
            if(!cheatActivated){
                controller.cheatTime(App.getCurrentGame().getSecond() + 60);
            }
        } else if (keycode == App.keyBindings.getCheatLvl()) {
            if(!cheatActivated){
                controller.getPlayerController().cheatLvl();
            }
        } else if (keycode == App.keyBindings.getCheatDamage()) {
            if(!cheatActivated){
                controller.handleLevelUpOption(LevelUpType.DAMAGE);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == App.keyBindings.getAttack()) {
            controller.setAiming(false);
            return true;
        } else if (
            keycode == App.keyBindings.getCheatHealth()
            || keycode == App.keyBindings.getCheatTime()
            || keycode == App.keyBindings.getCheatDamage()
            || keycode == App.keyBindings.getCheatLvl()
            || keycode == App.keyBindings.getCheatBossFight()
        ) {
            cheatActivated = false;
        }
        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        controller.setMouseDown(true);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        controller.setMouseDown(false);
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public GameController getController() {
        return controller;
    }
    public void showLevelUpMenu() {
        createLevelUpMenu();
        isLevelUpMenuVisible = true;
        Gdx.input.setInputProcessor(levelUpStage);
    }



}
