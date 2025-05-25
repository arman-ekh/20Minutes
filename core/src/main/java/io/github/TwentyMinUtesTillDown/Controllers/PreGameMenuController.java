package io.github.TwentyMinUtesTillDown.Controllers;

import com.badlogic.gdx.Gdx;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.User;
import io.github.TwentyMinUtesTillDown.View.*;

public class PreGameMenuController{
    private PreGameMenuView view;
    public void setView(PreGameMenuView view) {
        this.view = view;
    }
    public void onBookmarkClicked(int index) {
        switch (index) {
            case 0:{
                //Load Game

                if(Main.getCurrentGameView() != null){
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(Main.getCurrentGameView());
                    break;
                }
                if(App.getCurrentuser() == null){
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new NewGameView(new NewGameController()));
                    break;
                }
                if(App.getCurrentuser().getGameId() != 0){
                    Main.getMain().getScreen().dispose();
                    App.setCurrentGameId(App.getCurrentuser().getGameId());
                    Main.getMain().setScreen(new GameView(new GameController()));
                    break;
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new NewGameView(new NewGameController()));
                break;
            } case 1:{
                //New Game
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new NewGameView(new NewGameController()));
                break;
            } case 2:{
                //score board
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ScoreBoardView(new ScoreBoardController()));
                break;
            } case 3:{
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new HintMenuView(new HintMenuController()));
                break;
            } case 4:{
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController()));
                break;
            }
            case 6:
                //log out
                App.setCurrentUserId(0);
                App.setCurrentGameId(0);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController()));
                break;


        }
    }
    public void writeInfo(){
        if(App.getCurrentuser() != null){
            User user = App.getCurrentuser();
            Main.getFont().draw(Main.getBatch() ,"User Name: "+user.getName() ,450 , (float) Gdx.graphics.getHeight() /2);
            Main.getFont().draw(Main.getBatch() , "User Score: "+user.getTotalScore() , 450 ,(float) Gdx.graphics.getHeight() /2 - 100 );
        }
    }




}
