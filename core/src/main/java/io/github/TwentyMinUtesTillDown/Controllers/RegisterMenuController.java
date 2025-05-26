package io.github.TwentyMinUtesTillDown.Controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.Enums.RegisterMenuCommands;
import io.github.TwentyMinUtesTillDown.Models.Result;
import io.github.TwentyMinUtesTillDown.Models.User;
import io.github.TwentyMinUtesTillDown.View.RegisterMenuView;

import java.util.regex.Matcher;

public class RegisterMenuController {
    private RegisterMenuView view;

    public void setView(RegisterMenuView view) {
        this.view = view;
        view.getSecurityQuestion().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.getSecurityQuestion().setText(null);
            }
        });
    }
    public Result register(){
        if(view.getRegister().isPressed()){
        String username = view.getUserNameField().getText();
        String password = view.getPasswordField().getText();
        String email = view.getEmailField().getText();

        Matcher userMatcher = RegisterMenuCommands.USERNAME_PATTERN.getMatcher(username);
        Matcher passwordMatcher = RegisterMenuCommands.PASSWORD_PATTERN.getMatcher(password);
        Matcher emailMatcher = RegisterMenuCommands.EMAIL_PATTERN.getMatcher(email);
        User checkUser = App.getUserByUsername(username);



            if(userMatcher == null){
                return new Result(false,"user name can only have number and lower/upper case");
            }
            if(checkUser != null){
                return new Result(false , "user with this name already exists");
            }
            if(passwordMatcher == null){
                return new Result(false , "password is weak it should contain lower " +
                    "& upper case and numbers and at least one of @#$%^&*");
            }
            if(emailMatcher == null){
                return new Result(false , "email dose not match");
            }
            User user = new User(username , password , email);
            App.addUserToUserList(user);
            App.setCurrentUserId(user.getId());
            if(view.getImplementSecureQuestion().isChecked()){
                user.setSecurityQuestion(view.getSecurityQuestion().getText());
            }
            return new Result(true , "successfully added user");
        }else {
            return null;
        }
    }
    public Result goBack(){
        if(view != null){
            if(view.getGoBack().isPressed()){
                return new Result(true , "salam");
            }
        }
        return new Result(false , "");
    }
}
