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
import io.github.TwentyMinUtesTillDown.Controllers.LoginMenuController;
import io.github.TwentyMinUtesTillDown.Controllers.MainMenuController;
import io.github.TwentyMinUtesTillDown.Controllers.PreGameMenuController;
import io.github.TwentyMinUtesTillDown.Controllers.RegisterMenuController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Enums.Language;
import io.github.TwentyMinUtesTillDown.Models.Enums.TextLanguage;
import io.github.TwentyMinUtesTillDown.Models.Result;
import io.github.TwentyMinUtesTillDown.Models.User;

public class MainMenuView implements Screen {
    private Stage stage;
    private final TextButton textButtonGuest;
    private final TextButton registerButton;
    private final TextButton loginButton;
    private final Label label;
    private Table table;
    private final Skin skin = AssetManager.getSkin();
    private final MainMenuController controller;
    private final CheckBox englishCheckbox;
    private final CheckBox fingilishCheckbox;


    public MainMenuView(MainMenuController controller) {
        this.controller = controller;
        this.stage = new Stage();
        if(App.getLanguage().equals(Language.Finglish)){
            this.textButtonGuest = new TextButton(TextLanguage.playAsGuest.getFingilish(), skin);
            this.label = new Label("20 minutes till dawn", skin);
            this.loginButton = new TextButton(TextLanguage.login.getFingilish(), skin);
            this.registerButton = new TextButton(TextLanguage.register.getFingilish(), skin);
        }else {
            this.textButtonGuest = new TextButton("play as guest", skin);
            this.label = new Label("20 minutes till dawn", skin);
            this.loginButton = new TextButton("Login",skin);
            this.registerButton = new TextButton("Register",skin);
        }


        this.englishCheckbox = new CheckBox("English", skin);
        this.fingilishCheckbox = new CheckBox("Fingilish", skin);

        if (App.getLanguage() == Language.English) {
            englishCheckbox.setChecked(true);
            fingilishCheckbox.setChecked(false);
        } else {
            englishCheckbox.setChecked(false);
            fingilishCheckbox.setChecked(true);
        }



        englishCheckbox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (englishCheckbox.isChecked()) {
                    fingilishCheckbox.setChecked(false);
                    App.setLanguage(Language.English);
                    Main.getMain().setScreen(new MainMenuView(new MainMenuController())); // Refresh
                } else {
                    englishCheckbox.setChecked(true);
                }
            }
        });

        fingilishCheckbox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (fingilishCheckbox.isChecked()) {
                    englishCheckbox.setChecked(false);
                    App.setLanguage(Language.Finglish);
                    Main.getMain().setScreen(new MainMenuView(new MainMenuController()));
                } else {
                    fingilishCheckbox.setChecked(true);
                }
            }
        });

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(label);
        table.row().pad(10, 0, 10, 0);
        table.add(textButtonGuest);
        table.row().pad(10, 0, 10, 0);
        table.add(loginButton);
        table.row().pad(10, 0, 10, 0);
        table.add(registerButton);
        table.row().pad(10, 0, 10, 0);
        table.row().pad(10, 0, 10, 0);
        table.add(englishCheckbox).pad(20);
        table.row();
        table.add(fingilishCheckbox);

        stage.addActor(table);
        App.playMusic();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.CORAL);
        Main.getBatch().begin();
        stage.act();
        stage.draw();
        Main.getBatch().end();

        controller.setView(this);
        Result resultForGuset = controller.handleGuset();
        if(resultForGuset.isSuccessful()){
            Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController()));
        }
        Result resultForRegister = controller.handleRegister();
        if(resultForRegister.isSuccessful()){
            Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController()));
        }
        Result resultForLogIn = controller.handleLogin();
        if(resultForLogIn.isSuccessful()){
            Main.getMain().setScreen(new LoginMenuView(new LoginMenuController()));
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
        stage.dispose();
    }

    public TextButton getTextButtonGuest() {
        return textButtonGuest;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public Label getLabel() {
        return label;
    }
}
