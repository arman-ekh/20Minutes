package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.EndGameController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Enums.Language;
import io.github.TwentyMinUtesTillDown.Models.Enums.TextLanguage;

public class EndGameView implements Screen {
    private EndGameController controller;
    private Stage stage;
    private Table table;
    private Skin skin = AssetManager.getSkin();
    private TextButton textButton;
    private Animation<Texture> blinkingEye = AssetManager.getBlinkingEye();


    public EndGameView(EndGameController controller) {
        this.controller = controller;
        stage = new Stage();
        AnimatedActor animatedActor = new AnimatedActor(blinkingEye , 400 , 200);
        animatedActor.setPosition((float) Gdx.graphics.getWidth() /2 - 200, Gdx.graphics.getHeight() - 400);
        stage.addActor(animatedActor);

    }

    @Override
    public void show() {
        table = new Table();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.bottom().padBottom(100);
        if(App.getLanguage().equals(Language.Finglish)){
            textButton = new TextButton(TextLanguage.goBack.getFingilish(), skin);
        }else {
            textButton = new TextButton(TextLanguage.goBack.getEnglish(), skin);
        }

        table.add(textButton);
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        Color bgColor = App.getCurrentGame().getHero().getPlayerHealth() <= 0 ? Color.BLACK : Color.BLUE;
        ScreenUtils.clear(bgColor);
        Main.getBatch().begin();
        stage.act();
        controller.drawPicture();
        stage.draw();
        Main.getBatch().end();
        controller.setView(this);
        controller.goBack();
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

    public TextButton getTextButton() {
        return textButton;
    }
}
