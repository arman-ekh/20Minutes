package io.github.TwentyMinUtesTillDown.Controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterState;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterType;
import io.github.TwentyMinUtesTillDown.Models.Game;
import io.github.TwentyMinUtesTillDown.Models.GameModels.*;
import io.github.TwentyMinUtesTillDown.Models.RandomNumber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MonstersController {
     private List<Monster> monsters;
     private List<Bullet> monsterBullets = new ArrayList<>();
     private boolean bossFightSpawned = false;
     private boolean bossIsAlive = false;
    private Barrier barrier;
    private boolean barrierActive = false;


    public void monstersUpdate(Camera camera){
        Game game = App.getCurrentGame();
        monsters = game.getMonsters();
        List<Monster> deadMonsters = new ArrayList<>();
        for (Monster monster : monsters) {
            Rectangle bounds = monster.getMonsterSprite().getBoundingRectangle();

            if (monster.getState() == MonsterState.ALIVE) {
                monster.updateMonster();
                if(monster instanceof EyeBat eyeBat){
                    Bullet bullet = eyeBat.shootAtPlayer();
                    if (bullet != null) {
                        monsterBullets.add(bullet);
                    }
                }

                if (monster.getHealth() <= 0) {
                    game.getXpList().add(new Xp(monster.getX() , monster.getY()));
                    monster.setState(MonsterState.DYING);
                    monster.setDeathTime(0);
                }
            }

            if (camera.frustum.boundsInFrustum(
                bounds.getX() + bounds.getWidth()/2f,
                bounds.getY() + bounds.getHeight()/2f,
                0,
                bounds.getWidth()/2f,
                bounds.getHeight()/2f,
                1f
            )) {
                if (monster.getState() == MonsterState.ALIVE) {
                    monster.getMonsterSprite().draw(Main.getBatch());
                    runningAnimation();
                } else if (monster.getState() == MonsterState.DYING) {
                    deadAnimation(monster);
                    monster.getMonsterSprite().draw(Main.getBatch());
                }
            }

            if (monster.getState() == MonsterState.DYING) {
                monster.setDeathTime(monster.getDeathTime() + Gdx.graphics.getDeltaTime());
                if (AssetManager.getDeadMonster().isAnimationFinished(monster.getDeathTime())) {
                    monster.setState(MonsterState.DEAD);
                    deadMonsters.add(monster);
                    if(monster.getType().equals(MonsterType.SubNigget)){
                        bossIsAlive = false;
                        if(monster.getType().equals(MonsterType.SubNigget)){
                            bossIsAlive = false;
                            if (barrier != null) {
                                barrier.deactivate();
                            }
                        }
                    }

                }
            }
        }
        Hero hero = game.getHero();
        Iterator<Bullet> bulletIterator = monsterBullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            Vector2 dir = bullet.getDirection();
            float newX = bullet.getX() + dir.x * 600 * Gdx.graphics.getDeltaTime();
            float newY = bullet.getY() + dir.y * 600 * Gdx.graphics.getDeltaTime();
            bullet.move(newX, newY);
            if(hero.getRect().collidesWith(bullet.getCollisionRect())){
                hero.takeDamage(bullet.getDamage());
                bulletIterator.remove();
            }

            if (camera.frustum.pointInFrustum(newX, newY, 0)) {
                bullet.getSprite().setPosition(newX, newY);
                bullet.getSprite().draw(Main.getBatch());
            } else {
                bulletIterator.remove();
            }
        }



        float camX = camera.position.x;
        float camY = camera.position.y;
        float camWidth = camera.viewportWidth ;
        float camHeight = camera.viewportHeight ;

        float camLeft = camX - camWidth / 2;
        float camRight = camX + camWidth / 2;
        float camBottom = camY - camHeight / 2;
        float camTop = camY + camHeight / 2;

        for (Xp xp : game.getXpList()) {
            float xpX = xp.getX();
            float xpY = xp.getY();

            if (xpX >= camLeft && xpX <= camRight && xpY >= camBottom && xpY <= camTop) {
                xp.getSprite().draw(Main.getBatch());
            }
        }



        App.getCurrentGame().getMonsters().removeAll(deadMonsters);

        if (barrier != null && barrier.isActive()) {
            barrier.update(Gdx.graphics.getDeltaTime());
            barrier.render(camera);
            barrier.checkCollision(App.getCurrentGame().getHero());
        }

    }


    public void spawnMonster(Camera camera) {
        Game game = App.getCurrentGame();
        Hero player = game.getHero();

        float innerRadius = 900f;
        float outerRadius = 1100f;

        float playerX = player.getPosX();
        float playerY = player.getPosY();
        int t = game.getSecond();
        Random random = RandomNumber.getRandom();
        if(t %3 ==0){
            int numOfTentacleMonster = game.getSecond() / 30;

            for (int i = 0; i < numOfTentacleMonster; i++) {

                float angle = random.nextFloat() * (float) Math.PI * 2;


                float distance = innerRadius + random.nextFloat() * (outerRadius - innerRadius);

                float xSpawn = playerX + (float) Math.cos(angle) * distance;
                float ySpawn = playerY + (float) Math.sin(angle) * distance;


                Monster monster = new NormalMonster(MonsterType.TentacleMonster, xSpawn, ySpawn);
                game.getMonsters().add(monster);
            }
        }
        if (t >= game.getFullTime()/4 && t % 10 == 0) {
            int i = game.getSecond();
            int numEyeBats = (4 * i - game.getFullTime() + 30) / 30;

            for (int j = 0; j < numEyeBats; j++) {
                float angle = random.nextFloat() * (float) Math.PI * 2;
                float distance = innerRadius + random.nextFloat() * (outerRadius - innerRadius);

                float xSpawn = playerX + (float) Math.cos(angle) * distance;
                float ySpawn = playerY + (float) Math.sin(angle) * distance;
                EyeBat eyeBat = new EyeBat(MonsterType.EyeBat, xSpawn, ySpawn);
                game.getMonsters().add(eyeBat);
            }
        }
        if(game.getFullTime()/2 <= game.getSecond() && !bossFightSpawned){
            float angle = random.nextFloat() * (float) Math.PI * 2;
            float distance = innerRadius + random.nextFloat() * (outerRadius - innerRadius);

            float xSpawn = playerX + (float) Math.cos(angle) * distance;
            float ySpawn = playerY + (float) Math.sin(angle) * distance;
            game.getMonsters().add(new SubNiggut(MonsterType.SubNigget , xSpawn , ySpawn));
            bossFightSpawned = true;
            bossIsAlive = true;
            barrier = new Barrier(camera.viewportWidth, camera.viewportHeight);
        }
    }
    public void runningAnimation() {
        float heroX = App.getCurrentGame().getHero().getPosX();

        for (Monster monster : monsters) {
            if (monster.getType().equals(MonsterType.TreeMonster)) continue;

            Animation<Texture> animation;

            if (monster instanceof SubNiggut subNiggut && subNiggut.isDashing()) {
                animation = AssetManager.getSubNiggutDashing();
            } else if (monster.getType().equals(MonsterType.EyeBat)) {
                animation = AssetManager.getEyeBatMovement();
            } else if (monster.getType().equals(MonsterType.TentacleMonster)) {
                animation = AssetManager.getBrainMonsterMovment();
            } else {
                animation = AssetManager.subNiggutRunning;
            }

            Sprite sprite = monster.getMonsterSprite();


            sprite.setRegion(animation.getKeyFrame(monster.getTime(), true));

            float deltaX = monster.getX() - heroX;
            if (Math.abs(deltaX) > 5f) {
                if (deltaX < 0 && sprite.isFlipX()) {
                    sprite.flip(true, false);
                } else if (deltaX > 0 && !sprite.isFlipX()) {
                    sprite.flip(true, false);
                }
            }

            float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1f / 30f);
            monster.setTime(monster.getTime() + deltaTime);
        }
    }


    public void deadAnimation(Monster monster){
        Animation<Texture> animation = AssetManager.getDeadMonster();
        Sprite sprite = monster.getMonsterSprite();
        sprite.setRegion(animation.getKeyFrame(monster.getDeathTime()));
    }


}
