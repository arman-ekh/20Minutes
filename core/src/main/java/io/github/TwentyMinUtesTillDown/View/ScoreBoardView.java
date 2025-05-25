package io.github.TwentyMinUtesTillDown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.TwentyMinUtesTillDown.Controllers.PreGameMenuController;
import io.github.TwentyMinUtesTillDown.Controllers.ScoreBoardController;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.User;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardView implements Screen {
    private ScoreBoardController controller;
    private Stage stage;
    private SelectBox<String> sortTypeBox;
    private Skin skin = AssetManager.getSkin();
    private List<User> currentSortedUsers = new ArrayList<>();
    private int currentPage = 0;
    private final int USERS_PER_PAGE = 10;
    private TextButton nextButton;
    private TextButton prevButton;
    private User currentUser;

    public ScoreBoardView(ScoreBoardController controller) {
        this.controller = controller;
        this.stage = new Stage(new ScreenViewport());
        currentUser = App.getCurrentuser();
    }

    @Override
    public void show() {

        controller.setView(this);
        Gdx.input.setInputProcessor(stage);

        sortTypeBox = new SelectBox<>(skin);
        sortTypeBox.setItems("Kill Count", "Survival Time", "Score", "User Name");
        sortTypeBox.setSelected("Kill Count");
        sortTypeBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.sortUsersBy(sortTypeBox.getSelected());
            }
        });

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController()));
            }
        });

        Table topBar = new Table();
        topBar.bottom().padBottom(20);
        topBar.setFillParent(true);
        topBar.add(sortTypeBox).left().padRight(30).expandX();
        topBar.add(backButton).right();
        stage.addActor(topBar);

        Table bottomBar = new Table();
        bottomBar.bottom().padBottom(20);
        bottomBar.setFillParent(true);

        prevButton = new TextButton("Previous", skin);
        nextButton = new TextButton("Next", skin);

        prevButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (currentPage > 0) {
                    currentPage--;
                    updatePagination();
                }
            }
        });

        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int maxPage = (currentSortedUsers.size() - 1) / USERS_PER_PAGE;
                if (currentPage < maxPage) {
                    currentPage++;
                    updatePagination();
                }
            }
        });

        bottomBar.add(prevButton).padRight(30);
        bottomBar.add(nextButton);
        stage.addActor(bottomBar);

        controller.sortUsersBy("Kill Count");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.NAVY);
        stage.act(delta);
        stage.draw();

        BitmapFont font = Main.getFont();
        GlyphLayout layout = new GlyphLayout();

        float originalScaleX = font.getData().scaleX;
        float originalScaleY = font.getData().scaleY;
        font.getData().setScale(0.7f);

        Main.getBatch().begin();

        float y = Gdx.graphics.getHeight() - 150;
        int start = currentPage * USERS_PER_PAGE;
        int end = Math.min(start + USERS_PER_PAGE, currentSortedUsers.size());


        for (int i = start; i < end; i++) {
            User user = currentSortedUsers.get(i);
            int rank = i + 1;


            String text = rank + ". " + user.toString();
            if(currentUser != null){
                if(user.getId() == currentUser.getId()){
                    font.setColor(Color.FIREBRICK);
                }
            }
            switch (rank) {
                case 1: font.setColor(Color.GOLD); break;
                case 2: font.setColor(Color.LIGHT_GRAY); break;
                case 3: font.setColor(new Color(0.8f, 0.5f, 0.2f, 1)); break;
                default: font.setColor(Color.WHITE); break;
            }



            layout.setText(font, text);
            font.draw(Main.getBatch(), layout, 100, y);
            y -= layout.height + 25;
        }

        Main.getBatch().end();

        font.getData().setScale(originalScaleX, originalScaleY);
        Main.getFont().setColor(Color.WHITE);
    }

    public void updateTable(List<User> users) {
        this.currentSortedUsers = users;
        this.currentPage = 0;
        updatePagination();
    }

    private void updatePagination() {
        prevButton.setDisabled(currentPage == 0);
        int maxPage = (currentSortedUsers.size() - 1) / USERS_PER_PAGE;
        nextButton.setDisabled(currentPage >= maxPage);
    }

    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() { stage.dispose(); }
}
