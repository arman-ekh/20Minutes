package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.LoginMenuController;
import io.github.TwentyMinUtesTillDown.Controllers.PreGameMenuController;
import io.github.TwentyMinUtesTillDown.Controllers.RegisterMenuController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Result;

public class LoginMenuView implements Screen {
    private LoginMenuController controller;
    private Stage stage;
    private final Skin skin = AssetManager.getSkin();
    private Table tableButtons;
    private TextButton forgetPass ;
    private TextButton login;
    private TextButton signUp;
    private Table tableText;
    private TextField userNameField;
    private TextField passwordField;
    private BitmapFont text;
    private String errorMessage = null;
    private TextField newPasswordField;
    private boolean forgetPassClickedLastFrame = false;
    private boolean loginClickedLastFrame = false;




    public LoginMenuView(LoginMenuController controller) {
        this.controller = controller;
        stage = new Stage();
        forgetPass = new TextButton("Forgot your password?", skin);
        login = new TextButton("Login", skin);
        signUp = new TextButton("Signup first", skin);
        userNameField = new TextField("username",skin);
        passwordField = new TextField("password",skin);
        text = new BitmapFont();
        text.setColor(Color.WHITE);
        text.getData().setScale(2,2);
        newPasswordField = new TextField("new password",skin);
        newPasswordField.setVisible(false);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        this.tableButtons = new Table();
        tableButtons.setFillParent(true);
        this.tableText = new Table();
        tableText.setFillParent(true);


        tableText.top().pad(100,0,0,0);
        tableText.add(userNameField).width(500);
        tableText.row().pad(30,0,30,0);
        tableText.add(passwordField).width(500);
        tableText.row().pad(30,0,0,0);
        tableText.add(newPasswordField).width(500);

        tableButtons.add(login).width(200).height(80).padBottom(10);
        tableButtons.row().pad(10,0,10,0);
        tableButtons.add(forgetPass).width(600).height(80).padBottom(10);
        tableButtons.row().pad(10,0,10,0);
        tableButtons.add(signUp).width(350).height(80);
        tableButtons.bottom();
        tableButtons.padBottom(20);

        stage.addActor(tableText);
        stage.addActor(tableButtons);
        errorMessage = null;
    }

    @Override
    public void render(float v) {
        Main.getBatch().begin();
        ScreenUtils.clear(Color.CORAL);

        stage.act();
        stage.draw();


        controller.setView(this);
        Result result =controller.login();
        if(result != null ){
            if(result.isSuccessful()){
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController()));
            }else {
                errorMessage = result.message();
            }
        }
        result = controller.goBack();
        if(result!=null){
            Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController()));
        }


        boolean clickedNow = forgetPass.isPressed();

        if (clickedNow && !forgetPassClickedLastFrame) {
            result = controller.forgetPasswordClicked();
            if (result != null) {
                if (result.isSuccessful()) {
                    newPasswordField.setVisible(true);
                    passwordField.setText("email");
                    login.setText("confirm");
                    forgetPass.setText("try again");
                } else {
                    newPasswordField.setVisible(false);
                    passwordField.setText("Password");
                    login.setText("Login");
                    forgetPass.setText("Forgot your password?");
                }
            }
        }

        forgetPassClickedLastFrame = clickedNow;


        boolean loginNow = login.isPressed();

        if (loginNow && !loginClickedLastFrame) {
            if (newPasswordField.isVisible()) {
                result = controller.forgetPassword();
                if (result != null) {
                    errorMessage = result.message();
                }
            }
        }

        loginClickedLastFrame = loginNow;



        if (errorMessage != null) {
            GlyphLayout layout = new GlyphLayout(text, errorMessage);
            float textWidth = layout.width;
            text.draw(Main.getBatch(), errorMessage, (Gdx.graphics.getWidth() - textWidth) / 2, Gdx.graphics.getHeight() - 450);
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

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getForgetPass() {
        return forgetPass;
    }

    public TextButton getLogin() {
        return login;
    }

    public TextButton getSignUp() {
        return signUp;
    }

    public TextField getNewPasswordField() {
        return newPasswordField;
    }
}
