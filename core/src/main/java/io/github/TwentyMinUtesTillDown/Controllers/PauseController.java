package io.github.TwentyMinUtesTillDown.Controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.View.PauseView;
import io.github.TwentyMinUtesTillDown.View.EndGameView;
import io.github.TwentyMinUtesTillDown.View.PreGameMenuView;

public class PauseController {
    private PauseView view;

    public void setView(PauseView view) {
        this.view = view;

        view.getResume().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(Main.getCurrentGameView());
            }
        });

        view.getGiveUp().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.getCurrentGame().getHero().setPlayerHealth(0);
                Main.getMain().setScreen(new EndGameView( new EndGameController()));
            }
        });

        view.getSaveAndExit().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.saveApp();
                Main.getMain().setScreen( new PreGameMenuView(new PreGameMenuController()));
            }
        });
    }
}
