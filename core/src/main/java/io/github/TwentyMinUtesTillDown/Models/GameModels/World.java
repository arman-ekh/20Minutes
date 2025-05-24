package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.graphics.Texture;
import io.github.TwentyMinUtesTillDown.Models.App;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;
import io.github.TwentyMinUtesTillDown.Models.Enums.MonsterType;
import io.github.TwentyMinUtesTillDown.Models.Enums.TileType;
import io.github.TwentyMinUtesTillDown.Models.Game;
import io.github.TwentyMinUtesTillDown.Models.RandomNumber;

import java.util.*;

public class World {
    private Map<String, Tile> tiles;

    public World() {
        this.tiles = new HashMap<>();
    }

    public Tile getTileAt(int x, int y) {
        String key = x + "," + y;

        if (!tiles.containsKey(key)) {
            int randomNum = RandomNumber.getRandomNumber();
            if(randomNum %100 == 0){
                Game game = App.getCurrentGame();
                game.getMonsters().add(new NormalMonster(MonsterType.TreeMonster,x*64,y*64));
            }
            tiles.put(key, new Tile(TileType.BoneTileTexture, x, y));
        }

        return tiles.get(key);
    }


    public List<Tile> getTilesInArea(int centerX, int centerY, int range) {
        List<Tile> result = new ArrayList<>();
        for (int x = centerX - range; x <= centerX + range; x++) {
            for (int y = centerY - range; y <= centerY + range; y++) {
                result.add(getTileAt(x, y));
            }
        }

        return result;
    }

    public Map<String, Tile> getTiles() {
        return tiles;
    }
}
