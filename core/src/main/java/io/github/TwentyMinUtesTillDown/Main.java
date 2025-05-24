package io.github.TwentyMinUtesTillDown;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.MainMenuController;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.View.MainMenuView;


public class Main extends Game {
    private static SpriteBatch batch;
    private static Main main ;

    private static ShapeRenderer shapeRenderer;
    public static ShapeRenderer getShapeRenderer() { return shapeRenderer; }

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        App.loadApp();
        main = this;
        batch = new SpriteBatch();
        main.setScreen(new MainMenuView(new MainMenuController()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public  void dispose() {
        App.saveApp();
        batch.dispose();
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static Main getMain() {
        return main;
    }
}
