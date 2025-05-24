package io.github.TwentyMinUtesTillDown.Controllers;

import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.View.*;

public class PreGameMenuController{
    private PreGameMenuView view;
    public void setView(PreGameMenuView view) {
        this.view = view;
    }
    public void onBookmarkClicked(int index) {
        switch (index) {
            case 5:
                App.setCurrentUserId(0);
                Main.getMain().setScreen(new MainMenuView(new MainMenuController()));
                break;
            case 0:{
                //Load Game


                if(Main.getCurrentGameView() != null){
                    Main.getMain().setScreen(Main.getCurrentGameView());
                    break;
                }
                if(App.getCurrentuser() == null){
                    Main.getMain().setScreen(new NewGameView(new NewGameController()));
                    break;
                }
                if(App.getCurrentuser().getGameId() != 0){
                    App.setCurrentGameId(App.getCurrentuser().getGameId());
                    Main.getMain().setScreen(new GameView(new GameController()));
                    break;
                }
                Main.getMain().setScreen(new NewGameView(new NewGameController()));
                break;
            } case 1:{
                //New Game
                Main.getMain().setScreen(new NewGameView(new NewGameController()));
                break;
            } case 2:{

                break;
            }

        }
    }





}
