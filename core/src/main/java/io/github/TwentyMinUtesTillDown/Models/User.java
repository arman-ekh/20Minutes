package io.github.TwentyMinUtesTillDown.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class User {
    private String name , password , email;
    private int id ;
    private int gameId;
    private int totalScore;
    private int totalTimeSurvived;
    private int totalKillCount;
    private String portraitAddress;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = App.getUserList().size()+1;
        this.totalScore = 0;
        this.totalTimeSurvived = 0;
        this.totalKillCount = 0;
        portraitAddress = getRandomProfile();
    }

    public String getPortraitAddress() {
        return portraitAddress;
    }

    public void setPortraitAddress(String portraitAddress) {
        this.portraitAddress = portraitAddress;
    }
    public Texture getPortrait_tex(){
       try {
           return new Texture(Gdx.files.absolute(portraitAddress));
       }catch (Exception e){
           this.portraitAddress = getRandomProfile();
           return new Texture(portraitAddress);
       }
    }

    public int getTotalTimeSurvived() {
        return totalTimeSurvived;
    }

    public void setTotalTimeSurvived(int totalTimeSurvived) {
        this.totalTimeSurvived = totalTimeSurvived;
    }

    public int getTotalKillCount() {
        return totalKillCount;
    }

    public void setTotalKillCount(int totalKillCount) {
        this.totalKillCount = totalKillCount;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String message = "Name: "+name + " Score: "+totalScore + " Kill Count: "+getTotalKillCount() + " Survival Time: "+getTotalTimeSurvived();
        return message;
    }

    public static String getRandomProfile(){
        int rand = RandomNumber.getRandom().nextInt(0,3);
        String profile ;
        switch (rand){
            case 0:{
                profile = AssetManager.getDasherPortrait();
                break;
            } case 1:{
                profile = AssetManager.getDiamondPortrait();
                break;
            } default:{
                profile = AssetManager.getHasturPortrait();
                break;
            }
        }
        return profile;
    }
}
