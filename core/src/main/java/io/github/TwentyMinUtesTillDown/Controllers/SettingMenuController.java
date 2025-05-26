package io.github.TwentyMinUtesTillDown.Controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.View.ChangeKeyBindView;
import io.github.TwentyMinUtesTillDown.View.PreGameMenuView;
import io.github.TwentyMinUtesTillDown.View.SettingMenuView;

public class SettingMenuController {
    private SettingMenuView view;

    public void setView(SettingMenuView view) {
        this.view = view;
        view.getGrayGame().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                boolean isChecked = view.getGrayGame().isChecked();
                App.setGreyGame(isChecked);
            }
        });
        view.getAutoReload().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                boolean check = view.getAutoReload().isChecked();
                App.setAutoReload(check);
            }
        });
        view.getSfx().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                boolean sound = view.getSfx().isChecked();
                App.setSfxEnabled(sound);
            }
        });
        view.getChangeKeyBinds().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ChangeKeyBindView(new ChangeKeyBindController()));
            }
        });
    }
    private boolean usingChill = true;

    public void changeMusic() {
        if (usingChill) {
            io.github.TwentyMinUtesTillDown.Models.App.setMusic(
                io.github.TwentyMinUtesTillDown.Models.AssetManager.getWarSong()
            );
        } else {
            io.github.TwentyMinUtesTillDown.Models.App.setMusic(
                io.github.TwentyMinUtesTillDown.Models.AssetManager.getChillMusic()
            );
        }
        usingChill = !usingChill;
    }
    public void goBack(){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController()));
    }



}
