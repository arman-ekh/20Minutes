package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.SettingMenuController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;

public class SettingMenuView implements Screen {
    private SettingMenuController controller;
    private Stage stage;
    private Skin skin = AssetManager.getSkin();
    private Table table;
    private TextButton goBack ;
    private CheckBox grayGame;
    private CheckBox autoReload;
    private CheckBox sfx;
    private TextButton changeKeyBinds;

    public SettingMenuView(SettingMenuController controller) {
        this.controller = controller;
        stage = new Stage();
        goBack = new TextButton("Go Back",skin);
        grayGame = new CheckBox("grey",skin);
        autoReload = new CheckBox("Auto Reload",skin);
        sfx = new CheckBox("sound effect",skin);
        changeKeyBinds = new TextButton("change keybinds",skin);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.center();

        Slider volumeSlider = new Slider(0, 1, 0.01f, false, skin);
        volumeSlider.setValue(io.github.TwentyMinUtesTillDown.Models.App.getVolume());
        volumeSlider.addListener(event -> {
            io.github.TwentyMinUtesTillDown.Models.App.setVolume(volumeSlider.getValue());
            return false;
        });


        TextButton changeSongButton = new TextButton("Change Music", skin);
        changeSongButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.changeMusic();
            }
        });


        table.add(volumeSlider).width(300).pad(20);
        table.row();
        table.add(changeSongButton).pad(20);
        table.row();
        table.add(grayGame).padBottom(20);
        grayGame.setChecked(App.isGreyGame());
        table.add(autoReload).pad(20);
        autoReload.setChecked(App.isAutoReload());
        table.add(sfx).pad(20);
        sfx.setChecked(App.isSfxEnabled());
        table.row();
        table.add(changeKeyBinds).width(400).padBottom(20);
        table.row();
        table.add(goBack).pad(20);


        stage.addActor(table);
        controller.setView(this);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.NAVY);
        Main.getBatch().begin();
        stage.act();
        if(goBack.isPressed()){
            controller.goBack();
        }
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
    public CheckBox getGrayGame() {
        return grayGame;
    }

    public CheckBox getAutoReload() {
        return autoReload;
    }

    public CheckBox getSfx() {
        return sfx;
    }

    public TextButton getChangeKeyBinds() {
        return changeKeyBinds;
    }
}
