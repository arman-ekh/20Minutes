package io.github.TwentyMinUtesTillDown.Controllers.GameControllers;

import com.badlogic.gdx.graphics.Texture;
import io.github.TwentyMinUtesTillDown.Main;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Game;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Tile;
import io.github.TwentyMinUtesTillDown.Models.GameModels.World;

import java.util.List;

public class WorldController {
    private PlayerController playerController;
    private final int generationRange = 16;
    private Game game ;


    public WorldController(PlayerController playerController) {
        this.playerController = playerController;
        game = App.getCurrentGame();
    }



    public void update() {
        World world = game.getMap();
        float heroX = playerController.getHero().getPosX();
        float heroY = playerController.getHero().getPosY();


        int tileWidth = 64;
        int tileHeight = 64;

        int currentTileX = (int)(heroX / tileWidth);
        int currentTileY = (int)(heroY / tileHeight);


        List<Tile> tilesToDraw = world.getTilesInArea(currentTileX, currentTileY, generationRange);
        for (Tile tile : tilesToDraw) {
            Main.getBatch().draw(tile.getTexture(), tile.getWorldX(), tile.getWorldY(),64,64);
        }


    }



}
