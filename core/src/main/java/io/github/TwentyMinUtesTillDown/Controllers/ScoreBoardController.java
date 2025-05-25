package io.github.TwentyMinUtesTillDown.Controllers;

import io.github.TwentyMinUtesTillDown.View.ScoreBoardView;


import io.github.TwentyMinUtesTillDown.Models.User;
import io.github.TwentyMinUtesTillDown.View.ScoreBoardView;
import io.github.TwentyMinUtesTillDown.Models.App;

import java.util.Comparator;
import java.util.List;

public class ScoreBoardController {
    private ScoreBoardView view;

    public void setView(ScoreBoardView view) {
        this.view = view;
    }

    public void sortUsersBy(String criterion) {
        List<User> users = App.getUserList();

        switch (criterion) {
            case "Kill Count":
                users.sort(Comparator.comparingInt(User::getTotalKillCount).reversed());
                break;
            case "Survival Time":
                users.sort(Comparator.comparingInt(User::getTotalTimeSurvived).reversed());
                break;
            case "Score":
                users.sort(Comparator.comparingInt(User::getTotalScore).reversed());
                break;
            case "User Name":
                users.sort(Comparator.comparing(User::getName));
                break;
        }

        view.updateTable(users);
    }
}

