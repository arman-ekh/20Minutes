package io.github.TwentyMinUtesTillDown.Models;

import io.github.TwentyMinUtesTillDown.Models.Enums.GunType;
import io.github.TwentyMinUtesTillDown.Models.Enums.HeroType;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Hero;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Monster;
import io.github.TwentyMinUtesTillDown.Models.GameModels.World;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Xp;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int id;
    private World map;
    private int second;
    private List<Monster> monsters ;
    private List<Xp> xpList = new ArrayList<>();
    private Hero hero;
    private int fullTime;
    private boolean isLvlingUp;

    public Game(HeroType heroType, int time , GunType gunType) {
        this.id = App.getGameList().size()+1;
        map = new World();
        second = 0;
        monsters = new ArrayList<>();
        hero = new Hero(heroType , gunType);
        this.fullTime = time;
        isLvlingUp = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public World getMap() {
        return map;
    }

    public void setMap(World map) {
        this.map = map;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public int getFullTime() {
        return fullTime;
    }

    public List<Xp> getXpList() {
        return xpList;
    }

    public boolean isLvlingUp() {
        return isLvlingUp;
    }

    public void setLvlingUp(boolean lvlingUp) {
        isLvlingUp = lvlingUp;
    }
}
