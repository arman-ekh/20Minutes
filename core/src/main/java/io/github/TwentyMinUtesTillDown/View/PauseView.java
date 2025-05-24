package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.PauseController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;


public class PauseView implements Screen {
    private PauseController controller;
    private Stage stage;
    private Table table;
    private Skin skin = AssetManager.getSkin();
    private TextButton resume;
    private TextButton giveUp;
    private TextButton saveAndExit;

    public PauseView(PauseController controller) {
        this.controller = controller;
        stage = new Stage();
        resume = new TextButton("Resume",skin);
        giveUp = new TextButton("Give up",skin);
        saveAndExit = new TextButton("Save and Exit",skin);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(resume).padBottom(20);
        table.row();
        table.add(saveAndExit).padBottom(20);
        table.row();
        table.add(giveUp).padBottom(20);
        stage.addActor(table);
        controller.setView(this);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.NAVY);
        Main.getBatch().begin();
        stage.act();
        stage.draw();
        Main.getBatch().end();

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

    public TextButton getResume() {
        return resume;
    }

    public void setResume(TextButton resume) {
        this.resume = resume;
    }

    public TextButton getGiveUp() {
        return giveUp;
    }

    public void setGiveUp(TextButton giveUp) {
        this.giveUp = giveUp;
    }

    public TextButton getSaveAndExit() {
        return saveAndExit;
    }

    public void setSaveAndExit(TextButton saveAndExit) {
        this.saveAndExit = saveAndExit;
    }

}
