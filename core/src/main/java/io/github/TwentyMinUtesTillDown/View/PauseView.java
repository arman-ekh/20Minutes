package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.PauseController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Hero;
import io.github.TwentyMinUtesTillDown.Models.KeyBindings;

import javax.swing.*;


public class PauseView implements Screen {
    private PauseController controller;
    private Stage stage;
    private Table table;
    private Skin skin = AssetManager.getSkin();
    private TextButton resume;
    private TextButton giveUp;
    private TextButton saveAndExit;
    private CheckBox grayGame;
    private Texture heart;
    private Texture speed;
    private Texture moreBullet;
    private Texture damage;
    private Texture projectile;

    public PauseView(PauseController controller) {
        this.controller = controller;
        stage = new Stage();
        resume = new TextButton("Resume",skin);
        giveUp = new TextButton("Give up",skin);
        saveAndExit = new TextButton("Save and Exit",skin);
        grayGame = new CheckBox("grey",skin);
        heart = AssetManager.getHeartLvlUp_tex();
        speed = AssetManager.getSpeedLvlUp_tex();
        projectile = AssetManager.getProjectileLvlUp_tex();
        moreBullet = AssetManager.getMaxAmmoLvlUp_tex();
        damage = AssetManager.getDamageLvlUp_tex();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(resume).padBottom(20);
        table.row();
        table.add(saveAndExit).padBottom(20);
        table.row();
        table.add(giveUp).padBottom(20);
        table.row();
        table.add(grayGame).padBottom(20);
        grayGame.setChecked(App.isGreyGame());
        stage.addActor(table);
        controller.setView(this);
    }

    @Override
    public void render(float v) {
        Hero hero = App.getCurrentGame().getHero();

        ScreenUtils.clear(Color.NAVY);
        Main.getBatch().begin();
        stage.act();
        stage.draw();

        float iconX = Gdx.graphics.getWidth() - 64;
        float iconY = Gdx.graphics.getHeight() - 64;
        float spacing = 70f;

        if(hero.getMaxPlayerHealth() > hero.getType().getHealth()){
            Main.getBatch().draw(heart, iconX, iconY, 48, 48);
            iconY -= spacing;
        }
        if(hero.getWeapon().getMaxAmmo() > hero.getWeapon().getType().getCapacity()){
            Main.getBatch().draw(moreBullet, iconX, iconY, 48, 48);
            iconY -= spacing;
        }
        if(hero.getWeapon().getDamage() > hero.getWeapon().getType().getBaseDamage()){
            Main.getBatch().draw(damage, iconX, iconY, 48, 48);
            iconY -= spacing;
        }
        if(hero.getSpeed() > hero.getType().getSpeed()){
            Main.getBatch().draw(speed, iconX, iconY, 48, 48);
            iconY -= spacing;
        }
        if(hero.getWeapon().getProjectileNum() > hero.getWeapon().getType().getProjectileNum()){
            Main.getBatch().draw(projectile, iconX, iconY, 48, 48);
            iconY -= spacing;
        }

        BitmapFont font = Main.getFont();
        float originalScaleX = font.getData().scaleX;
        float originalScaleY = font.getData().scaleY;

        font.getData().setScale(0.6f);

        float textX = 20;
        float textY = Gdx.graphics.getHeight() - 20;
        float lineHeight = 40;

        KeyBindings keys = App.keyBindings;
        font.draw(Main.getBatch(), Input.Keys.toString(keys.getCheatTime()) + ": Fast Forward Time", textX, textY);
        textY -= lineHeight;
        font.draw(Main.getBatch(), Input.Keys.toString(keys.getCheatHealth()) + ": Full Health", textX, textY);
        textY -= lineHeight;
        font.draw(Main.getBatch(), Input.Keys.toString(keys.getCheatBossFight()) + ": Boss Fight", textX, textY);
        textY -= lineHeight;
        font.draw(Main.getBatch(), Input.Keys.toString(keys.getCheatLvl()) + ": Level Up", textX, textY);
        textY -= lineHeight;
        font.draw(Main.getBatch(), Input.Keys.toString(keys.getCheatDamage()) + ": Max Damage", textX, textY);

        font.getData().setScale(originalScaleX, originalScaleY);

        Main.getBatch().end();
    }


    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public TextButton getResume() {
        return resume;
    }

    public void setResume(TextButton resume) {
        this.resume = resume;
    }

    public TextButton getGiveUp() {
        return giveUp;
    }

    public void setGiveUp(TextButton giveUp) {
        this.giveUp = giveUp;
    }

    public TextButton getSaveAndExit() {
        return saveAndExit;
    }

    public void setSaveAndExit(TextButton saveAndExit) {
        this.saveAndExit = saveAndExit;
    }

    public CheckBox getGrayGame() {
        return grayGame;
    }
}
