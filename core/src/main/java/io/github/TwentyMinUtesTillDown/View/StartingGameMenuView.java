package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.StartingGameController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;

public class StartingGameMenuView implements Screen {
    private StartingGameController controller;
    private final Skin skin = AssetManager.getSkin();
    private Table table;
    private Stage stage;

    public StartingGameMenuView(StartingGameController controller) {
        this.controller = controller;
        stage = new Stage();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.center();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.CORAL);
        Main.getBatch().begin();
        stage.act();
        stage.draw();
        Main.getBatch().end();

        controller.setView(this);

    }

    @Override
    public void resize(int i, int i1) {

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
}
