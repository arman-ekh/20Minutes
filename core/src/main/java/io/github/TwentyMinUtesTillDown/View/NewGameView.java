package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.TwentyMinUtesTillDown.Controllers.GameController;
import io.github.TwentyMinUtesTillDown.Controllers.NewGameController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Enums.GunType;
import io.github.TwentyMinUtesTillDown.Models.Enums.HeroType;

public class NewGameView implements Screen {
    private Stage stage;
    private final Skin skin = AssetManager.getSkin();
    private NewGameController controller;
    private Table table;
    private SpriteBatch batch;
    private float stateTime;
    private ButtonGroup<CheckBox> gunGroup;
    private CheckBox[] gunCheckBoxes = new CheckBox[GunType.values().length];
    private Image[] gunImages = new Image[GunType.values().length];
    private TextButton startButton ;

    private SelectBox<Integer> timeSelectBox;


    private final HeroType[] heroes = HeroType.values();
    private final Animation<Texture>[] idleAnimations = new Animation[heroes.length];
    private final CheckBox[] checkBoxes = new CheckBox[heroes.length];

    public NewGameView(NewGameController controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stateTime = 0f;

        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        controller = new NewGameController();
        controller.setView(this);

        table = new Table();
        table.setFillParent(true);
        table.center().bottom().padBottom(50);

        startButton = new TextButton("Start",skin);
        table.add(startButton).center().right();
        ButtonGroup<CheckBox> buttonGroup = new ButtonGroup<>();
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(1);

        for (int i = 0; i < heroes.length; i++) {
            idleAnimations[i] = heroes[i].getIdle();

            AnimatedActor animActor = new AnimatedActor(idleAnimations[i]);

            checkBoxes[i] = new CheckBox(heroes[i].name(), skin);
            buttonGroup.add(checkBoxes[i]);

            VerticalGroup group = new VerticalGroup();
            group.addActor(animActor);
            group.addActor(checkBoxes[i]);
            group.space(10f);
            group.pad(10f);
            group.center();

            table.add(group).pad(20f);
        }

        HorizontalGroup gunsGroup = new HorizontalGroup();
        gunsGroup.space(10);
        gunsGroup.pad(20).padBottom(200);
        gunsGroup.center();

        gunGroup = new ButtonGroup<>();
        gunGroup.setMaxCheckCount(1);
        gunGroup.setMinCheckCount(1);

        int index = 0;
        for (GunType gun : GunType.values()) {
            TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(gun.getTexture()));

            Image gunImage = new Image(drawable);
            gunImage.setScaling(Scaling.fit);
            Container<Image> gunContainer = new Container<>(gunImage);
            gunContainer.size(128, 96);


            gunImages[index] = gunImage;

            gunCheckBoxes[index] = new CheckBox(gun.name(), skin);
            gunGroup.add(gunCheckBoxes[index]);

            VerticalGroup singleGun = new VerticalGroup();
            singleGun.addActor(gunContainer);
            singleGun.addActor(gunCheckBoxes[index]);
            singleGun.space(5);
            singleGun.center();


            gunsGroup.addActor(singleGun);
            index++;
        }


        Label timeLabel = new Label("Game Duration (minutes):", skin);
        timeSelectBox = new SelectBox<>(skin);
        timeSelectBox.setItems(2, 10, 15, 20);
        timeSelectBox.setSelected(20);

        VerticalGroup timeGroup = new VerticalGroup();
        timeGroup.addActor(timeLabel);
        timeGroup.addActor(timeSelectBox);
        timeGroup.space(10);
        timeGroup.pad(20).padBottom(400);
        timeGroup.top();


        VerticalGroup mainGroup = new VerticalGroup();
        mainGroup.setFillParent(true);
        mainGroup.center().bottom().padBottom(20);
        mainGroup.space(40);

        mainGroup.addActor(table);
        mainGroup.addActor(gunsGroup);
        mainGroup.addActor(timeGroup);

        stage.addActor(mainGroup);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.4f, 0.2f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
        controller.setView(this);
        if(controller.startGame()){
            Main.getMain().setScreen(new GameView(new GameController()));
        }
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    public HeroType getSelectedHero() {
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                return heroes[i];
            }
        }
        return null;
    }
    public int getSelectedMinute(){
        return timeSelectBox.getSelected();
    }
    public GunType getSelectedGunType() {
        for (int i = 0; i < gunCheckBoxes.length; i++) {
            if (gunCheckBoxes[i].isChecked()) {
                String gunName = gunCheckBoxes[i].getText().toString();
                return GunType.getTypeByString(gunName);
            }
        }
        return null;
    }

    public TextButton getStartButton() {
        return startButton;
    }
}
