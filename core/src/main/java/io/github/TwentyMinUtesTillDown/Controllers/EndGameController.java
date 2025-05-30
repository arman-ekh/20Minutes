package io.github.TwentyMinUtesTillDown.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Enums.Language;
import io.github.TwentyMinUtesTillDown.Models.Enums.TextLanguage;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Hero;
import io.github.TwentyMinUtesTillDown.Models.User;
import io.github.TwentyMinUtesTillDown.View.EndGameView;
import io.github.TwentyMinUtesTillDown.View.MainMenuView;
import io.github.TwentyMinUtesTillDown.View.PreGameMenuView;

public class EndGameController {
    private EndGameView view;
    private boolean userInfoUpToDate = false;







    public void setView(EndGameView view) {
        this.view = view;
    }

    public void goBack(){
        if(view.getTextButton().isPressed()){
            Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController()));
            App.getGameList().remove(App.getCurrentGame());
            App.setCurrentGameId(-1);
            if(App.getCurrentuser() != null){
                App.getCurrentuser().setGameId(0);
            }
        }
    }


    public void drawPicture() {


        Hero hero = App.getCurrentGame().getHero();
        int timeSurvived = App.getCurrentGame().getSecond();
        int killCount = hero.getKillCount();
        int score = timeSurvived * killCount;

        if(!userInfoUpToDate){
            if(App.getCurrentuser() != null){
                User user = App.getCurrentuser();
                user.setTotalScore(user.getTotalScore() + score);
                user.setTotalKillCount(killCount + user.getTotalKillCount());
                user.setTotalTimeSurvived(timeSurvived + user.getTotalTimeSurvived());
                userInfoUpToDate = true;
            }
        }

        String endMessage;
        if (hero.getPlayerHealth() <= 0) {
            if(App.getLanguage().equals(Language.Finglish)){
                endMessage = TextLanguage.deathMessage.getFingilish();
            }else {
                endMessage = TextLanguage.deathMessage.getEnglish();
            }
        } else if (App.getCurrentGame().getSecond() >= App.getCurrentGame().getFullTime()) {
            if(App.getLanguage().equals(Language.Finglish)){
                endMessage = TextLanguage.winDeathMessage.getFingilish();
            }else {
                endMessage = TextLanguage.winDeathMessage.getEnglish();
            }
        } else {
            endMessage = "GAME OVER";
        }

        Main.getFont().draw(Main.getBatch(), endMessage, 300, 500);
        Main.getFont().draw(Main.getBatch(), "Time Survived: " + timeSurvived + " seconds", 300, 370);
        Main.getFont().draw(Main.getBatch(), "Kill Count: " + killCount, 300, 290);
        Main.getFont().draw(Main.getBatch(), "Score: " + score, 300, 210);
        if(App.getCurrentuser() != null){
            Main.getFont().draw(Main.getBatch() , App.getCurrentuser().getName() , Gdx.graphics.getWidth()/2 - 30 , 900);
        }else {
            if(App.getLanguage().equals(Language.Finglish)){
                Main.getFont().draw(Main.getBatch() , "mehmoon yek rooz do rooz" ,Gdx.graphics.getWidth()/2 -220 , 900);
            }else {
                Main.getFont().draw(Main.getBatch() , "You are guest" ,Gdx.graphics.getWidth()/2 -220 , 900);
            }

        }
        Main.setCurrentGameView(null);
    }


}
