package io.github.TwentyMinUtesTillDown.Models.GameModels;

import com.badlogic.gdx.graphics.Texture;
import io.github.TwentyMinUtesTillDown.Models.Enums.TileType;

public class Tile {
    private TileType texturePath;
    private int tileX, tileY;
    private transient Texture texture;

    public Tile(TileType texturePath, int tileX, int tileY) {
        this.texturePath = texturePath;
        this.tileX = tileX;
        this.tileY = tileY;
        texture = texturePath.getTexture();
    }

    public Texture getTexture() {
        return texture;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public float getWorldX() {
        return tileX * texture.getWidth();
    }

    public float getWorldY() {
        return tileY * texture.getHeight();
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TileType getTexturePath() {
        return texturePath;
    }
}
