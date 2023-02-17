package vttp2022.workshop39marvel.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Character {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Character create(JsonObject jo) {
        Character c = new Character();
        c.setName(jo.getString("name"));
        return c;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("name", name)
            .build();
    }
    
}
