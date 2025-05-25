package io.github.TwentyMinUtesTillDown.Controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Game;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Hero;
import io.github.TwentyMinUtesTillDown.View.GameView;

public class HudController {
    private Game game;
    private Texture heartTexture;
    private Texture xpBar;
    private Texture xpBarSlider;
    private Texture vignette;

    public HudController() {
        game = App.getCurrentGame();
        heartTexture = AssetManager.getHeartLvlUp_tex();
        xpBar = AssetManager.getProggresBar_tex();
        xpBarSlider = AssetManager.getProggresBarSlide_tex();
        vignette = new Texture("vignette.png");
    }

    public void updateHud() {
        Main.getBatch().draw(
            vignette,
            0,
            0,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );
        Hero hero = game.getHero();
        int heartSpacing = 25;
        int baseX = 20;
        int baseY = Gdx.graphics.getHeight() - 50;

        for (int i = 0; i < hero.getPlayerHealth(); i++) {
            Main.getBatch().draw(heartTexture, baseX + i * heartSpacing, baseY,40,40);
        }
        float oldScaleX = Main.getFont().getData().scaleX;
        float oldScaleY = Main.getFont().getData().scaleY;

        Main.getFont().getData().setScale(0.5f , 0.5f);
        Main.getFont().draw(Main.getBatch() , "Kill Count: "+hero.getKillCount() , baseX , baseY - 75);
        Main.getFont().draw(Main.getBatch() , "Level: "+hero.getLvl() , baseX , baseY - 120);
        Main.getFont().draw(Main.getBatch() , "Timer: "+game.getSecond() , (float) Gdx.graphics.getWidth() /2-100, baseY);
        Main.getFont().getData().setScale(oldScaleX, oldScaleY);
        int xp = hero.getXp();
        int xpNeededForNextLvl = hero.getLvl()*20;

        int barWidth = 200;
        int barHeight = 20;
        int barX = baseX;
        int barY = baseY - 50;

        Main.getBatch().draw(xpBar, barX, barY, barWidth, barHeight);


        float progressRatio = (float) xp / xpNeededForNextLvl;
        progressRatio = Math.min(progressRatio, 1f);


        int sliderX = (int) (barX + (barWidth - xpBarSlider.getWidth()) * progressRatio);
        int sliderY = barY;

        Main.getBatch().draw(xpBarSlider, sliderX, sliderY , 20 , 20);




    }

}
