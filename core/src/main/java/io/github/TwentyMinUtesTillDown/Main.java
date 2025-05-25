package io.github.TwentyMinUtesTillDown;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.MainMenuController;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.View.GameView;
import io.github.TwentyMinUtesTillDown.View.MainMenuView;


public class Main extends Game {
    private static SpriteBatch batch;
    private static Main main ;

    private static ShapeRenderer shapeRenderer;
    public static ShapeRenderer getShapeRenderer() { return shapeRenderer; }
    private static GameView currentGameView;
    private static ShaderProgram grayscaleShader;


    public static void setCurrentGameView(GameView view) {
        currentGameView = view;
    }

    public static GameView getCurrentGameView() {
        return currentGameView;
    }


    @Override
    public void create() {

        Pixmap pixmap = AssetManager.getCursorPixmap();

        int hotspotX = pixmap.getWidth() / 2;
        int hotspotY = pixmap.getHeight() / 2;

        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pixmap, hotspotX, hotspotY));

        shapeRenderer = new ShapeRenderer();
        App.loadApp();
        main = this;
        batch = new SpriteBatch();
        AssetManager.load();
        main.setScreen(new MainMenuView(new MainMenuController()));
        ShaderProgram.pedantic = false;


        grayscaleShader = new ShaderProgram(
            Gdx.files.internal("shaders/grayscale.vert"),
            Gdx.files.internal("shaders/grayscale.frag")
        );

        if (!grayscaleShader.isCompiled()) {
            Gdx.app.error("Shader", "Shader compile error:\n" + grayscaleShader.getLog());
            System.exit(0);
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public  void dispose() {
        App.saveApp();
        batch.setShader(null);
        batch.dispose();
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static Main getMain() {
        return main;
    }
    public static BitmapFont getFont() {
        return AssetManager.getFont();
    }

    public static ShaderProgram getGrayscaleShader() {
        return grayscaleShader;
    }
}
