package io.github.TwentyMinUtesTillDown.Controllers;

import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.Result;
import io.github.TwentyMinUtesTillDown.Models.User;
import io.github.TwentyMinUtesTillDown.View.LoginMenuView;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }
    public Result login(){
        if(view.getLogin().isPressed()){
            if(!view.getNewPasswordField().isVisible()){
                String username = view.getUserNameField().getText();
                String password = view.getPasswordField().getText();
                User user = App.getUserByUsername(username);
                if(user == null){
                    return new Result(false , "user dose not exit with this username");
                }
                if(!user.getPassword().equals(password)){
                    return new Result(false , "wrong password");
                }
                App.setCurrentUserId(user.getId());
                return new Result(true , "successfully logged in");
            }
        }
        return null;
    }
    public Result goBack(){
        if(view.getSignUp().isPressed()){
            return new Result(true , "");
        }
        return null;
    }
    public Result forgetPasswordClicked(){
            if(view.getForgetPass().isPressed()){
                if(view.getNewPasswordField().isVisible()){
                    return new Result(false , "");
                }else {
                    return new Result(true,"");
                }
            }
            return null;
    }
    public Result forgetPassword(){
            String username = view.getUserNameField().getText();
            String email = view.getPasswordField().getText();
            String newPassword = view.getNewPasswordField().getText();
            User user = App.getUserByUsername(username);
            if(user ==null){
                return new Result(false ,"user with this user name dose not exist");
            }
            if(!user.getEmail().equals(email)){
                return new Result(false , "email is wrong");
            }
            if(user.getPassword().equals(newPassword)){
                return new Result(false ,"new password can not be the same as it was");
            }
            user.setPassword(newPassword);
            return new Result(true , "password successfully changed");

    }
}
