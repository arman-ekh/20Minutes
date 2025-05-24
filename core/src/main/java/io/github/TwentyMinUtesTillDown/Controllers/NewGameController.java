package io.github.TwentyMinUtesTillDown.Controllers;

import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.Enums.GunType;
import io.github.TwentyMinUtesTillDown.Models.Enums.HeroType;
import io.github.TwentyMinUtesTillDown.Models.Game;
import io.github.TwentyMinUtesTillDown.View.NewGameView;

public class NewGameController {
    private NewGameView view;

    public void setView(NewGameView view) {
        this.view = view;
    }
    public boolean startGame(){
        if(view.getStartButton().isPressed()){
            GunType gunType = view.getSelectedGunType();
            HeroType heroType = view.getSelectedHero();
            int time = view.getSelectedMinute();
            Game game = new Game(heroType , time*60 , gunType);
            game.setHero(game.getHero());
            App.addGameToGameList(game);
            App.setCurrentGameId(game.getId());
            if(App.getCurrentuser() != null){
                App.getCurrentuser().setGameId(game.getId());
            }
            return true;
        }
        return false;
    }
}
