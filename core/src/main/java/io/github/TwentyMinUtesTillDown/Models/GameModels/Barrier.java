package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;

public class Barrier {
    private Rectangle bounds;
    private float shrinkSpeed = 20f;
    private boolean active = true;
    private float minWidth = 20f;


    public Barrier(float screenWidth, float screenHeight) {
        this.bounds = new Rectangle(
            App.getCurrentGame().getHero().getPosX() ,
            App.getCurrentGame().getHero().getPosY() ,
            screenWidth,
            screenHeight
        );
    }

    public void update(float delta) {
        if (!active) return;

        bounds.setWidth(Math.max(bounds.getWidth() - shrinkSpeed * delta, minWidth));
        bounds.setHeight(Math.max(bounds.getHeight() - shrinkSpeed * delta, minWidth));
        bounds.setX(App.getCurrentGame().getHero().getPosX() - bounds.getWidth() / 2f);
        bounds.setY(App.getCurrentGame().getHero().getPosY() - bounds.getHeight() / 2f);
    }

    public void render(Camera camera) {
        if (!active) return;

        Main.getShapeRenderer().setProjectionMatrix(camera.combined);
        Main.getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
        Main.getShapeRenderer().setColor(Color.RED);
        Main.getShapeRenderer().rect(bounds.x, bounds.y, bounds.width, bounds.height);
        Main.getShapeRenderer().end();
    }

    public void checkCollision(Hero hero) {
        if (active && !bounds.contains(hero.getPosX(), hero.getPosY())) {
            hero.takeDamage(1);
        }
    }

    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }
}
