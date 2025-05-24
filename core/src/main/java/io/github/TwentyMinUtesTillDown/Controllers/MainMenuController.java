package io.github.TwentyMinUtesTillDown.Controllers;

import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.Result;
import io.github.TwentyMinUtesTillDown.Models.User;
import io.github.TwentyMinUtesTillDown.View.MainMenuView;
import io.github.TwentyMinUtesTillDown.View.PreGameMenuView;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }
    public Result handleGuset(){
        if(view != null){
            if(view.getTextButtonGuest().isPressed()){
                return new Result(true , "salam gol morad");
            }
        }
        return new Result(false ,"");
    }
    public Result handleRegister(){
        if(view != null){
            if(view.getRegisterButton().isPressed()){
                return new Result(true , "salam morad gol");
            }
        }
        return new Result(false , "");
    }
    public Result handleLogin(){
        if(view != null){
            if(view.getLoginButton().isPressed()){
                return new Result(true , "morad gol salam");
            }
        }
        return new Result(false , "");
    }
}
