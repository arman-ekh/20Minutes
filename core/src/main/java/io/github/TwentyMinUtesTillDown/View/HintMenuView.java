package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.TwentyMinUtesTillDown.Controllers.HintMenuController;
import io.github.TwentyMinUtesTillDown.Controllers.PreGameMenuController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Enums.Language;
import io.github.TwentyMinUtesTillDown.Models.Enums.TextLanguage;
import io.github.TwentyMinUtesTillDown.Models.KeyBindings;

import java.util.ArrayList;
import java.util.Collections;

public class HintMenuView implements Screen {
    private Stage stage;
    private Table table;
    private Skin skin = AssetManager.getSkin();
    private HintMenuController controller;
    private TextButton goBack;

    private Texture heart = AssetManager.getHeartLvlUp_tex();
    private Texture speed = AssetManager.getSpeedLvlUp_tex();
    private Texture projectile = AssetManager.getProjectileLvlUp_tex();
    private Texture moreBullet = AssetManager.getMaxAmmoLvlUp_tex();
    private Texture damage = AssetManager.getDamageLvlUp_tex();

    private Table heroHintsBox;
    private Table keysBox;
    private Table cheatsBox;
    private Table abilitiesBox;
    private Table iconsTable;
    private ArrayList<String> currentLines;
    private Table infoDisplayBox;
    private boolean isInDetailMode = false;




    private BitmapFont font;


    public HintMenuView(HintMenuController controller) {
        this.controller = controller;
        stage = new Stage();
        goBack = new TextButton("Go Back", skin);
        goBack.setVisible(false);
        this.font = Main.getFont();
        this.currentLines = new ArrayList<>();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        ScreenUtils.clear(Color.NAVY);

        table = new Table(skin);
        table.setFillParent(true);
        table.pad(30);
        table.defaults().pad(20);

        heroHintsBox = createBox("Hero Hints",
            "Diamond is the toughest material in the world, but speed was never her strength.\n" +
                "Dasher moves as swiftly as a deer, graceful and quick.\n" +
                "Shana is known far and wide for her unwavering reliability.");
        keysBox = createBox("Game Keys Display", "Currently configured game keys");
        cheatsBox = createBox("Cheat Codes Display", "Cheat codes and their effects");
        abilitiesBox = createBox("Ability Information", "Information about game abilities");

        table.add(heroHintsBox).width(1200).height(150);
        table.row();
        table.add(keysBox).width(300).height(150);
        table.row();
        table.add(cheatsBox).width(300).height(150);
        table.row();
        table.add(abilitiesBox).width(300).height(150).colspan(3);
        table.row();

        iconsTable = new Table();
        iconsTable.defaults().pad(10);
        iconsTable.add(new Image(heart)).size(48);
        iconsTable.add(new Image(speed)).size(48);
        iconsTable.add(new Image(projectile)).size(48);
        iconsTable.add(new Image(moreBullet)).size(48);
        iconsTable.add(new Image(damage)).size(48);
        table.add(iconsTable).colspan(3).center().padBottom(200);
        table.row();

        goBack.setPosition(Gdx.graphics.getWidth() / 2f - goBack.getWidth() / 2f, 50);
        goBack.setVisible(true);
        stage.addActor(goBack);

        addClickListener(heroHintsBox);
        addClickListener(keysBox);
        addClickListener(cheatsBox);
        addClickListener(abilitiesBox);

        goBack.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isInDetailMode) {
                    Main.getMain().setScreen(new HintMenuView(new HintMenuController()));
                } else {
                    Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController()));
                }
                return true;
            }
        });

        infoDisplayBox = new Table(skin);
        infoDisplayBox.setSize(800, 180);
        infoDisplayBox.setPosition(Gdx.graphics.getWidth() / 2f - 400, Gdx.graphics.getHeight() - 200);
        infoDisplayBox.setVisible(false);
        stage.addActor(infoDisplayBox);


        stage.addActor(table);
        controller.setView(this);

    }

    private void addClickListener(final Table box) {
        box.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hideOtherBoxes(box);
                return true;
            }
        });
    }

    private void hideOtherBoxes(Table visibleBox) {
        isInDetailMode = true;

        String description = "";
        switch (visibleBox.getName()) {
            case "Hero Hints":
                if(App.getLanguage().equals(Language.English)){
                    description = "so you want to know your hero better";
                }else {
                    description = TextLanguage.heroDescription.getFingilish();
                }

                if(App.getLanguage().equals(Language.English)){
                    setCurrentLines(
                        "Diamond is the toughest material in the world\nbut speed was never her strength.(hp:7 speed:1)\n",
                        "Dasher moves as swiftly as a deer,\ngraceful and quick(hp:2 speed:10).",
                        "Shana is known far and wide for her\nunwavering reliability(hp:4 speed:4)."
                    );
                }else {
                    setCurrentLines(
                        TextLanguage.diamondHint.getFingilish(),
                        TextLanguage.dasherHint.getFingilish(),
                        TextLanguage.shanaHint.getFingilish()
                    );
                }


                break;
            case "Game Keys Display":
                description = "Currently configured game keys";
                KeyBindings kb = App.keyBindings;
                setCurrentLines(
                    "Move Up: " + KeyBindings.keyCodeToString(kb.getMoveUp()),
                    "Move Down: " + KeyBindings.keyCodeToString(kb.getMoveDown()),
                    "Move Left: " + KeyBindings.keyCodeToString(kb.getMoveLeft()),
                    "Move Right: " + KeyBindings.keyCodeToString(kb.getMoveRight()),
                    "Reload: "+KeyBindings.keyCodeToString(kb.getReload()),
                    "Aim Assist: "+KeyBindings.keyCodeToString(kb.getAttack())
                );

                break;
            case "Cheat Codes Display":
                description = "Display cheat codes and their effects";
                 kb = App.keyBindings;
                setCurrentLines(
                    "Cheat Time: " + KeyBindings.keyCodeToString(kb.getCheatTime()),
                    "Cheat LVL: " + KeyBindings.keyCodeToString(kb.getCheatLvl()),
                    "Cheat Boss Fight: " + KeyBindings.keyCodeToString(kb.getCheatBossFight()),
                    "Cheat Damage Perk: " + KeyBindings.keyCodeToString(kb.getCheatDamage()),
                    "Cheat Health: "+KeyBindings.keyCodeToString(kb.getCheatHealth())
                );
                break;
            case "Ability Information":
                if(App.getLanguage().equals(Language.English)){
                    setCurrentLines(
                        "Health Perk increases your maximum health by 1,\nallowing you to survive longer in battle",
                        "Damage Perk doubles your attack power for 10 seconds,\nmaking every hit count",
                        "Speed Perk boosts your movement speed by 2× for 10 seconds,\nletting you dodge and dash with ease.",
                        "Projectile Perk adds one extra projectile to your attacks,\nincreasing your firepower.",
                        "Max Capacity Perk expands your weapon's ammo capacity by 5,\ngiving you more shots before reloading."
                    );
                }else {
                    setCurrentLines(
                        TextLanguage.healthPerkDescription.getFingilish(),
                        TextLanguage.damagePerkDescription.getFingilish(),
                        TextLanguage.speedPerkDescription.getFingilish() ,
                        TextLanguage.projectilePerkDescription.getFingilish(),
                        TextLanguage.maxAmmoPerkDescription.getFingilish()
                    );
                }

                description = "Display information about game abilities";
                break;
        }

        showInfoDisplayBox(visibleBox.getName(), description);

        heroHintsBox.setVisible(false);
        keysBox.setVisible(false);
        cheatsBox.setVisible(false);
        abilitiesBox.setVisible(false);
        iconsTable.setVisible(false);

        goBack.setVisible(true);
    }




    private Table createBox(String title, String description) {
        Table box = new Table(skin);
        box.setName(title);

        Label titleLabel = new Label(title, skin, "title");
        titleLabel.setColor(1, 0.3f, 0.3f, 1);
        titleLabel.setFontScale(0.9f);
        titleLabel.setAlignment(Align.center);

        box.add(titleLabel).padBottom(10).row();
        return box;
    }
    public void setCurrentLines(String... lines) {
        currentLines.clear();
        Collections.addAll(currentLines, lines);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.NAVY);
        stage.act(delta);
        stage.draw();

        Main.getBatch().begin();

        float originalScaleX = font.getData().scaleX;
        float originalScaleY = font.getData().scaleY;

        font.getData().setScale(0.6f);

        float y = Gdx.graphics.getHeight() - 350;
        for (String line : currentLines) {
            font.draw(Main.getBatch(), line, 50, y);
            y -= 80;
        }

        font.getData().setScale(originalScaleX, originalScaleY);

        Main.getBatch().end();
    }

    private void showInfoDisplayBox(String title, String description) {
        infoDisplayBox.clear();

        Label titleLabel = new Label(title, skin, "title");
        titleLabel.setColor(1, 0.3f, 0.3f, 1);
        titleLabel.setFontScale(0.9f);
        titleLabel.setAlignment(Align.center);

        Label descLabel = new Label(description, skin);
        descLabel.setWrap(true);
        descLabel.setAlignment(Align.center);

        infoDisplayBox.add(titleLabel).padBottom(10).row();
        infoDisplayBox.add(descLabel).width(780);

        infoDisplayBox.setVisible(true);
    }




    @Override
    public void resize(int width, int height) {
        goBack.setPosition(width / 2f - goBack.getWidth() / 2f, 50);
    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
