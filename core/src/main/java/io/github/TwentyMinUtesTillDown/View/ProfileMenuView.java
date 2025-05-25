package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.PreGameMenuController;
import io.github.TwentyMinUtesTillDown.Controllers.ProfileMenuController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Result;

public class ProfileMenuView implements Screen{
    private Stage stage;
    private ProfileMenuController controller;
    private Table table;
    private TextField userNameField;
    private TextField passWordNameField;
    private TextButton changeUserName;
    private TextButton changePassWord;
    private TextButton deleteAccount;
    private TextButton goBack;
    private Skin skin = AssetManager.getSkin();
    private String text ;
    private ImageButton dasherPortraitButton;
    private ImageButton diamondPortraitButton;
    private ImageButton hasturPortraitButton;



    public ProfileMenuView(ProfileMenuController controller) {
        this.controller = controller;
        stage = new Stage();
        userNameField = new TextField("New User Name",skin);
        passWordNameField = new TextField("New Password",skin);
        changeUserName = new TextButton("change name",skin);
        changePassWord = new TextButton("change password",skin);
        deleteAccount = new TextButton("delete Account",skin);
        goBack = new TextButton("goBack",skin);
        dasherPortraitButton = new ImageButton(new TextureRegionDrawable(new Texture(AssetManager.getDasherPortrait())));
        diamondPortraitButton = new ImageButton(new TextureRegionDrawable(new Texture(AssetManager.getDiamondPortrait())));
        hasturPortraitButton = new ImageButton(new TextureRegionDrawable(new Texture(AssetManager.getHasturPortrait())));

    }

    @Override
    public void show() {
        controller.setView(this);
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(userNameField).width(300).padBottom(20);
        table.row();
        table.add(changeUserName).padBottom(20);
        table.row();
        table.add(passWordNameField).width(300).padBottom(20);
        table.row();
        table.add(changePassWord).padBottom(20);
        table.row();
        table.add(deleteAccount).padBottom(20);
        table.row();
        table.add(goBack);
        Table portraitTable = new Table();
        portraitTable.add(dasherPortraitButton).pad(10);
        portraitTable.add(diamondPortraitButton).pad(10);
        portraitTable.add(hasturPortraitButton).pad(10);

        table.row().padTop(20);
        table.add(portraitTable);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.NAVY);
        stage.act();
        stage.draw();

        Main.getBatch().begin();

        Result result;

        if (changePassWord.isPressed()) {
            result = controller.changePassWord(passWordNameField.getText());
            text = result.isSuccessful() ? null : result.message();
        }

        if (changeUserName.isPressed()) {
            result = controller.changeUserName(userNameField.getText());
            text = result.isSuccessful() ? null : result.message();
        }

        if (deleteAccount.isPressed()) {
            result = controller.deleteAccount();
            text = result.isSuccessful() ? null : result.message();
        }


        if (goBack.isPressed()) {
            Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController()));
        }
        if(App.getCurrentuser() != null){
            if (dasherPortraitButton.isPressed()) {
                App.getCurrentuser().setPortraitAddress(AssetManager.getDasherPortrait());
            }

            if (diamondPortraitButton.isPressed()) {
                App.getCurrentuser().setPortraitAddress(AssetManager.getDiamondPortrait());
            }

            if (hasturPortraitButton.isPressed()) {
                App.getCurrentuser().setPortraitAddress(AssetManager.getHasturPortrait());
            }
        }




        if (text != null && !text.isBlank()) {
            BitmapFont font = Main.getFont();
            GlyphLayout layout = new GlyphLayout(font, text);
            float textWidth = layout.width;
            float x = (Gdx.graphics.getWidth() - textWidth) / 2f;
            float y = Gdx.graphics.getHeight() - 100;
            font.draw(Main.getBatch(), layout, x, y);
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

    public TextField getPassWordNameField() {
        return passWordNameField;
    }

    public TextButton getChangeUserName() {
        return changeUserName;
    }

    public TextButton getChangePassWord() {
        return changePassWord;
    }

    public TextButton getDeleteAccount() {
        return deleteAccount;
    }

    public TextButton getGoBack() {
        return goBack;
    }
}
