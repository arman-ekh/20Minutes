package io.github.TwentyMinUtesTillDown.Controllers;

import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.Enums.RegisterMenuCommands;
import io.github.TwentyMinUtesTillDown.Models.Result;
import io.github.TwentyMinUtesTillDown.Models.User;
import io.github.TwentyMinUtesTillDown.View.MainMenuView;
import io.github.TwentyMinUtesTillDown.View.ProfileMenuView;

import java.util.regex.Matcher;

public class ProfileMenuController {
    private ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public Result changeUserName(String newUsername) {
        if (App.getCurrentuser() == null) {
            return new Result(false, "you need to login first");
        }

        Matcher matcher = RegisterMenuCommands.USERNAME_PATTERN.getMatcher(newUsername);
        if (matcher == null) {
            return new Result(false, "wrong user name format");
        }

        if (App.getUserByUsername(newUsername) != null) {
            return new Result(false, "name already taken");
        }

        User user = App.getCurrentuser();
        if (user != null) {
            user.setName(newUsername);
        }

        return new Result(true, "Username changed successfully");
    }

    public Result changePassWord(String newPassword) {
        if (App.getCurrentuser() == null) {
            return new Result(false, "you need to login first");
        }

        Matcher matcher = RegisterMenuCommands.USERNAME_PATTERN.getMatcher(newPassword);
        if (matcher == null) {
            return new Result(false, "weak password format");
        }

        User user = App.getCurrentuser();
        if (user != null) {
            user.setPassword(newPassword);
        }

        return new Result(true, "Password changed successfully");
    }

    public Result deleteAccount() {
        if (App.getCurrentuser() == null) {
            return new Result(false, "you need to login first");
        }

        User user = App.getCurrentuser();
        App.getUserList().remove(user);
        App.setCurrentUserId(0);

        // تغییر صفحه به منوی اصلی
        Main.getMain().setScreen(new MainMenuView(new MainMenuController()));

        return new Result(true, "Account deleted");
    }
}
