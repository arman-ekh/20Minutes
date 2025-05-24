package io.github.TwentyMinUtesTillDown.Models.Enums;

import com.badlogic.gdx.graphics.Texture;
import io.github.TwentyMinUtesTillDown.Models.AssetManager;

public enum LevelUpType {
    HEALTH(AssetManager.getHeartLvlUp_tex()),
    DAMAGE(AssetManager.getDamageLvlUp_tex()),
    PROJECTILE(AssetManager.getProjectileLvlUp_tex()),
    SPEED(AssetManager.getSpeedLvlUp_tex()),
    MAX_AMMO(AssetManager.getMaxAmmoLvlUp_tex());

    private Texture texture ;


    LevelUpType(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}

