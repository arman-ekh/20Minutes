package io.github.TwentyMinUtesTillDown.Models.Enums;

import com.badlogic.gdx.graphics.Texture;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;

public enum TileType {
    GrassTileTexture(AssetManager.getTileNum1Tex()),
    BoneTileTexture(AssetManager.getTileNum0Tex());

    private  transient Texture texture;

    TileType(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
