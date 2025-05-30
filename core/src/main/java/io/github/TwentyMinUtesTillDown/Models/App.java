package io.github.TwentyMinUtesTillDown.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.TwentyMinUtesTillDown.Models.Enums.Language;
import io.github.TwentyMinUtesTillDown.Models.GameModels.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    private static int currentUserId;
    private static Language language = Language.English;
    private static int currentGameId;
    private static List<User> userList = new ArrayList<>();
    private static List<Game> gameList = new ArrayList<>();
    public static KeyBindings keyBindings= new KeyBindings();
    private static boolean greyGame = false;
    private static Music music = AssetManager.getChillMusic();
    private static boolean sfxEnabled = true;
    private static boolean autoReload = true;

    public static boolean isAutoReload() {
        return autoReload;
    }

    public static void setAutoReload(boolean autoReload) {
        App.autoReload = autoReload;
    }

    public static void setSfxEnabled(boolean enabled) {
        sfxEnabled = enabled;
    }

    public static boolean isSfxEnabled() {
        return sfxEnabled;
    }



    public static void playMusic() {
        if (music != null && !music.isPlaying()) {
            music.setLooping(true);
            music.play();
        }
    }

    public static void stopMusic() {
        if (music != null && music.isPlaying()) {
            music.stop();
        }
    }

    public static void pauseMusic() {
        if (music != null && music.isPlaying()) {
            music.pause();
        }
    }

    public static void setMusic(Music newMusic) {
        if (music != null && music.isPlaying()) music.stop();
        music = newMusic;
        music.setLooping(true);
        music.play();
    }

    public static void setVolume(float volume) {
        if (music != null) music.setVolume(Math.max(0, Math.min(1, volume)));
    }

    public static float getVolume() {
        return music != null ? music.getVolume() : 0;
    }

    public static List<User> getUserList() {
        return userList;
    }
    public static List<Game> getGameList() {
        return gameList;
    }
    public static void addGameToGameList(Game game){
        gameList.add(game);
    }
    public static void addUserToUserList(User user){
        userList.add(user);
    }
    public static User getUserById(int id){
        for (User user : userList) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public static void saveApp() {
        Gson gson = new GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(Monster.class, new MonsterAdapter()).create();

        FileHandle userFile = Gdx.files.local("userData.json");
        userFile.writeString(gson.toJson(userList), false);

        FileHandle gameFile = Gdx.files.local("gameData.json");
        gameFile.writeString(gson.toJson(gameList), false);
    }

    public static void loadApp() {
        Gson gson = new GsonBuilder().setPrettyPrinting()
        .registerTypeAdapter(Monster.class, new MonsterAdapter()).create();


        FileHandle userFile = Gdx.files.local("userData.json");
        if (userFile.exists()) {
            String userJson = userFile.readString();
            Type userListType = new TypeToken<List<User>>() {}.getType();
            userList = gson.fromJson(userJson, userListType);
        } else {
            System.out.println("user file not found");
        }

        FileHandle gameFile = Gdx.files.local("gameData.json");
        if (gameFile.exists()) {
            String gameJson = gameFile.readString();
            Type gameListType = new TypeToken<List<Game>>() {}.getType();
            gameList = gson.fromJson(gameJson, gameListType);
            for (Game game : gameList) {
                Hero hero = game.getHero();


                hero.setPlayerTexture(hero.getType().getTexture());
                hero.setPlayerSprite(new Sprite(hero.getPlayerTexture()));
                hero.getPlayerSprite().setSize(hero.getPlayerTexture().getWidth() * 3f, hero.getPlayerTexture().getHeight() * 3f);
                hero.getPlayerSprite().setPosition(hero.getPosX(), hero.getPosY());

                Weapon weapon = hero.getWeapon();
                weapon.setWeaponSprite(weapon.getType().getSprite());
                weapon.getWeaponSprite().setX(0);
                weapon.getWeaponSprite().setY(0);
                weapon.getWeaponSprite().setSize(50, 50);
                for (Monster monster : game.getMonsters()) {
                    monster.setMonsterTexture(monster.getType().getTexture());
                    monster.setMonsterSprite(new Sprite(monster.getMonsterTexture()));
                    monster.getMonsterSprite().setSize(monster.getMonsterTexture().getWidth() * 3f,
                        monster.getMonsterTexture().getHeight() * 3f);
                    monster.getMonsterSprite().setPosition(monster.getX(), monster.getY());
                    monster.setCollisionRect(new CollisionRect(monster.getX(), monster.getY(),
                        monster.getMonsterSprite().getWidth(), monster.getMonsterSprite().getHeight() / 2f));
                }
                for (Xp xp : game.getXpList()) {
                    xp.setTexture(AssetManager.getXp());
                    xp.setSprite(new Sprite(xp.getTexture()));
                    xp.getSprite().setSize(20,20);
                    xp.getSprite().setX(xp.getX());
                    xp.getSprite().setY(xp.getY());
                    xp.setCollisionRect(new CollisionRect(xp.getX(), xp.getY()
                        , xp.getSprite().getWidth() ,xp.getSprite().getHeight()));
                }
                Map<String, Tile> tiles = game.getMap().getTiles();
                for (Tile tile : tiles.values()) {
                    tile.setTexture(tile.getTexturePath().getTexture());
                }
            }
        } else {
            System.out.println("game file not found");
        }
    }
    public static User getUserByUsername(String username){
        for (User user : userList) {
            if(user.getName().equals(username)){
                return user;
            }
        }
        return null;
    }
    public static User getCurrentuser(){
        for (User user : userList) {
            if(user.getId() == currentUserId){
                return user;
            }
        }
        return null;
    }

    public static Game getCurrentGame(){
        for (Game game : gameList) {
            if(game.getId()==currentGameId){
                return game;
            }
        }
        return null;
    }

    public static void setCurrentUserId(int currentUserId) {
        App.currentUserId = currentUserId;
    }

    public static void setCurrentGameId(int currentGameId) {
        App.currentGameId = currentGameId;
    }

    public static boolean isGreyGame() {
        return greyGame;
    }

    public static void setGreyGame(boolean greyGame) {
        App.greyGame = greyGame;
    }
    public static void playSound(Sound sound) {
        if (sfxEnabled) {
            sound.play(music.getVolume());
        }
    }

    public static Language getLanguage() {
        return language;
    }

    public static void setLanguage(Language language) {
        App.language = language;
    }
}
