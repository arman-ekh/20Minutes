package io.github.TwentyMinUtesTillDown.Models.Enums;

public enum TextLanguage {

    playAsGuest("play as guest" , "mehmoon ma bash"),
    login("login" , "vorood"),
    register("register" , "sabt nam"),
    loadGame("load game" , "edame bazi ghabl"),
    newGame("new game","bazi jadid"),
    scoreBoard("score board","takht emtiaz"),
    hintMenu("hint menu","etelaat"),
    profile("profile","namagar"),
    setting("setting","tanzimat"),
    logout("logout","khorooj"),
    gameDuration("Game Duration","tool modat zaman bazi"),
    resume("Resume" ,"edame"),
    save("Save and Exit" , "ali parvin boro dige"),
    giveUp("give up" , "taslim"),
    grey("grey" , "goth"),
    cheatFastForward("Fast Forward Time", "zaman ro mibare jelo"),
    cheatFullHealth("Full Health", "jooneto ziad mikone"),
    cheatBossFight("Boss Fight", "bara oonai ke shish mahe be donya oomadn"),
    cheatLevelUp("Level Up", "bime abalfasl"),
    cheatMaxDamage("Max Damage", "goodrat"),
    deathMessage("FALLEN THIS TIME\nBUT STRONGER IN SPIRIT TRY AGAIN, HERO","Noob bad bakht"),
    diamondHint("Diamond is the toughest material in the world\nbut speed was never her strength.(hp:7 speed:1)\n","sag joon va kond\n(hp:7 speed:1)"),
    dasherHint("Dasher moves as swiftly as a deer,\ngraceful and quick(hp:2 speed:10).","nim mesghal vazn dare\n(hp:2 speed:10)"),
    shanaHint("Shana is known far and wide for her\nunwavering reliability(hp:4 speed:4)." , "noramleshoon ine\n(hp:4 speed:4)"),
    heroDescription("so you want to know your hero better" , "zendegi name ina"),
    healthPerkDescription("Health Perk increases your maximum health by 1,\nallowing you to survive longer in battle","Health perk: max joonet yeki ziad mishe"),
    damagePerkDescription("Damage Perk doubles your attack power for 10 seconds,\nmaking every hit count","goodrat teriak"),
    speedPerkDescription("Speed Perk boosts your movement speed by 2× for 10 seconds,\nletting you dodge and dash with ease.","speed speede dige chi mikhay az joon man"),
    projectilePerkDescription("Projectile Perk adds one extra projectile to your attacks,\nincreasing your firepower.","yek doone tir bishtar miazne"),
    maxAmmoPerkDescription("Max Capacity Perk expands your weapon's ammo capacity by 5,\ngiving you more shots before reloading.","5 ta tir too kheshabet bishtar ja mishe"),
    goBack("Back" , "Bargard"),
    winDeathMessage("YOU SURVIVED TO DIE ANOTHER DAY","oho na khosham oomad"),
    ;
    private String english , fingilish;

    TextLanguage(String english, String fingilish) {
        this.english = english;
        this.fingilish = fingilish;
    }

    public String getEnglish() {
        return english;
    }

    public String getFingilish() {
        return fingilish;
    }
}
