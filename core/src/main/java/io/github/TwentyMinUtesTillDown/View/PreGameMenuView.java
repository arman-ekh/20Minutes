package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.PreGameMenuController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.User;

public class PreGameMenuView implements Screen {
    private Stage stage;
    private Table table;
    private final Skin skin = AssetManager.getSkin();
    private PreGameMenuController controller;
    private Texture texture;





    public PreGameMenuView(PreGameMenuController controller) {
        this.controller = controller;
        this.stage = new Stage();
        if(App.getCurrentuser() != null) {
            User user = App.getCurrentuser();
            this.texture = user.getPortrait_tex();
        }
    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.center();
        table.top().padTop(50);

        String[] labels = {"load game", "new game", "Score Board", "hint menu", "profile","setting", "logout"};

        for (int i = 0; i < labels.length; i++) {
            final int pageIndex = i;
            TextButton button = new TextButton(labels[i], skin);
            button.getLabel().setFontScale(0.8f);
            button.addListener(event -> {
                if (event.toString().equals("touchDown")) {
                    controller.onBookmarkClicked(pageIndex);
                }
                return true;
            });
            table.row().pad(10);
            table.add(button).width(300).height(40);
        }

        stage.addActor(table);
        controller.setView(this);
    }



    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.NAVY);
        Main.getBatch().begin();
        stage.act();
        stage.draw();

        controller.writeInfo();
        if(App.getCurrentuser() != null){
            Main.getBatch().draw(texture , Gdx.graphics.getWidth()/2-100 , 100 , 200 , 200);
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






}
