package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.MainMenuController;
import io.github.TwentyMinUtesTillDown.Controllers.PreGameMenuController;
import io.github.TwentyMinUtesTillDown.Controllers.RegisterMenuController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Result;

public class RegisterMenuView implements Screen {
    private RegisterMenuController controller;
    private Table tableField;
    private Table tableButton;
    private Stage stage;
    private TextField userNameField;
    private TextField emailField;
    private TextField passwordField;
    private Skin skin = AssetManager.getSkin();
    private TextButton register;
    private TextButton goBack;
    private BitmapFont text;
    private String errorMessage = null;


    public RegisterMenuView(RegisterMenuController controller) {
        this.controller = controller;
        stage = new Stage();
        this.userNameField = new TextField("username",skin);
        this.passwordField = new TextField("password",skin);
        this.emailField = new TextField("email" , skin);
        this.register = new TextButton("register", skin);
        this.goBack = new TextButton("main menu" , skin);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        this.tableField = new Table();
        this.tableButton = new Table();
        text = new BitmapFont();
        text.setColor(Color.WHITE);
        text.getData().setScale(2,2);
        tableField.setFillParent(true);
        tableField.add(userNameField).width(600);
        tableField.row().pad(20,0,20,0);
        tableField.add(passwordField).width(600);
        tableField.row().pad(20,0,20,0);
        tableField.add(emailField).width(600);
        tableField.row().pad(20,0,20,0);
        tableField.center();

        tableButton.setFillParent(true);
        tableButton.add(register);
        tableButton.add(goBack);
        tableButton.bottom();
        tableButton.pad(0,0,100,0);

        stage.addActor(tableButton);
        stage.addActor(tableField);
        errorMessage = null;
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.NAVY);
        Main.getBatch().begin();
        stage.act();
        stage.draw();

        controller.setView(this);
        Result result = controller.register();
        if(result != null){
            if(result.isSuccessful()){
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController()));
            }else {
                errorMessage = result.message();
            }
        }

        result = controller.goBack();
        if(result.isSuccessful()){
            Main.getMain().setScreen(new MainMenuView(new MainMenuController()));
        }


        if (errorMessage != null) {
            GlyphLayout layout = new GlyphLayout(text, errorMessage);
            float textWidth = layout.width;
            text.draw(Main.getBatch(), errorMessage, (Gdx.graphics.getWidth() - textWidth) / 2, Gdx.graphics.getHeight() - 100);
        }

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

    public TextField getUserNameField() {
        return userNameField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getRegister() {
        return register;
    }

    public TextButton getGoBack() {
        return goBack;
    }
}
