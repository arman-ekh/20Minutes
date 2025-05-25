package io.github.TwentyMinUtesTillDown.Controllers;

import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.View.PreGameMenuView;
import io.github.TwentyMinUtesTillDown.View.SettingMenuView;

public class SettingMenuController {
    private SettingMenuView view;

    public void setView(SettingMenuView view) {
        this.view = view;
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
        Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController()));
    }

}
