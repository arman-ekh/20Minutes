package io.github.TwentyMinUtesTillDown.Models;

import com.google.gson.*;
import io.github.TwentyMinUtesTillDown.Models.GameModels.EyeBat;
import io.github.TwentyMinUtesTillDown.Models.GameModels.Monster;
import io.github.TwentyMinUtesTillDown.Models.GameModels.NormalMonster;
import io.github.TwentyMinUtesTillDown.Models.GameModels.SubNiggut;

import java.lang.reflect.Type;

public class MonsterAdapter implements JsonSerializer<Monster>, JsonDeserializer<Monster> {

    @Override
    public JsonElement serialize(Monster src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getSimpleName());
        jsonObject.add("data", context.serialize(src, src.getClass()));
        return jsonObject;
    }

    @Override
    public Monster deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement dataElement = jsonObject.get("data");

        switch(type) {
            case "EyeBat":
                return context.deserialize(dataElement, EyeBat.class);
            case "SubNiggut":
                return context.deserialize(dataElement, SubNiggut.class);
            case "Monster", "TreeMonster" , "TentacleMonster" , "NormalMonster":
                return context.deserialize(dataElement, NormalMonster.class);
            default:
                throw new JsonParseException("Unknown monster type: " + type);
        }
    }
}
