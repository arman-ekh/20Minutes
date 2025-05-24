package io.github.TwentyMinUtesTillDown.Models;

public class User {
    private String name , password , email;
    private int id ;
    private int gameId;

    public User() {}
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = App.getUserList().size()+1;
    }

    public User(String name, String password, String email, int id) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
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
}
