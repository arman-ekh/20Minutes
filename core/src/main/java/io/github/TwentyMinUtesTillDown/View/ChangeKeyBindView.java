package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;
import java.util.Map;

import io.github.TwentyMinUtesTillDown.Controllers.ChangeKeyBindController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.KeyBindings;

public class ChangeKeyBindView implements Screen {
    private Stage stage;
    private ChangeKeyBindController controller;
    private Table table;
    private Skin skin = io.github.TwentyMinUtesTillDown.Models.AssetManager.getSkin();
    private TextButton save;

    private String waitingForKey = null;

    private final Map<String, TextButton> keyButtons = new HashMap<>();

    private final String[] actions = {
        "Move Up", "Move Down", "Move Left", "Move Right",
        "Attack", "Reload",
        "Cheat Time", "Cheat Health", "Cheat BossFight", "Cheat Level", "Cheat Damage"
    };

    public ChangeKeyBindView(ChangeKeyBindController controller) {
        this.controller = controller;
        this.stage = new Stage();
        this.save = new TextButton("Save Changes", skin);
        controller.setView(this);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();

        KeyBindings kb = App.keyBindings;
        int i =0;
        for (String action : actions) {
            String keyName = controller.getKeyForAction(action);
            TextButton keyButton = new TextButton(KeyBindings.keyCodeToString(controller.getKeyCodeForAction(action)), skin);

            keyButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    waitingForKey = action;
                    keyButton.setText("Press any key...");
                }
            });

            keyButtons.put(action, keyButton);
            table.add(new Label(action, skin)).pad(10);
            table.add(keyButton).width(150).pad(10);
            i++;
            if(i %4 ==0){
                table.row();
            }
        }

        table.add(save).colspan(2).padTop(30);

        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.applyKeyChanges();
                controller.goBack();
            }
        });

        stage.addActor(table);
        controller.setView(this);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.NAVY);
        stage.act(delta);
        stage.draw();

        if (waitingForKey != null && Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            for (int i = 0; i < 256; i++) {
                if (Gdx.input.isKeyJustPressed(i)) {
                    controller.setTempKey(waitingForKey, i);
                    keyButtons.get(waitingForKey).setText(KeyBindings.keyCodeToString(i));
                    waitingForKey = null;
                    break;
                }
            }
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
