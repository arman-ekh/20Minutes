package io.github.TwentyMinUtesTillDown.Controllers;

import java.util.HashMap;
import java.util.Map;

import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.KeyBindings;
import io.github.TwentyMinUtesTillDown.View.ChangeKeyBindView;
import io.github.TwentyMinUtesTillDown.View.PreGameMenuView;

public class ChangeKeyBindController {
    private ChangeKeyBindView view;
    private final Map<String, Integer> tempBindings = new HashMap<>();

    public void setView(ChangeKeyBindView view) {
        this.view = view;
        loadCurrentBindings();
    }

    public void setTempKey(String action, int keyCode) {
        tempBindings.put(action, keyCode);
    }

    public void applyKeyChanges() {
        KeyBindings kb = App.keyBindings;

        for (Map.Entry<String, Integer> entry : tempBindings.entrySet()) {
            switch (entry.getKey()) {
                case "Move Up": kb.setMoveUp(entry.getValue()); break;
                case "Move Down": kb.setMoveDown(entry.getValue()); break;
                case "Move Left": kb.setMoveLeft(entry.getValue()); break;
                case "Move Right": kb.setMoveRight(entry.getValue()); break;
                case "Attack": kb.setAttack(entry.getValue()); break;
                case "Reload": kb.setReload(entry.getValue()); break;
                case "Cheat Time": kb.setCheatTime(entry.getValue()); break;
                case "Cheat Health": kb.setCheatHealth(entry.getValue()); break;
                case "Cheat BossFight": kb.setCheatBossFight(entry.getValue()); break;
                case "Cheat Level": kb.setCheatLvl(entry.getValue()); break;
                case "Cheat Damage": kb.setCheatDamage(entry.getValue()); break;
            }
        }
    }

    public void loadCurrentBindings() {
        KeyBindings kb = App.keyBindings;
        tempBindings.put("Move Up", kb.getMoveUp());
        tempBindings.put("Move Down", kb.getMoveDown());
        tempBindings.put("Move Left", kb.getMoveLeft());
        tempBindings.put("Move Right", kb.getMoveRight());
        tempBindings.put("Attack", kb.getAttack());
        tempBindings.put("Reload", kb.getReload());
        tempBindings.put("Cheat Time", kb.getCheatTime());
        tempBindings.put("Cheat Health", kb.getCheatHealth());
        tempBindings.put("Cheat BossFight", kb.getCheatBossFight());
        tempBindings.put("Cheat Level", kb.getCheatLvl());
        tempBindings.put("Cheat Damage", kb.getCheatDamage());
    }

    public int getKeyCodeForAction(String action) {
        return tempBindings.getOrDefault(action, -1);
    }

    public String getKeyForAction(String action) {
        return action;
    }
    public void goBack(){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController()));
    }
}
