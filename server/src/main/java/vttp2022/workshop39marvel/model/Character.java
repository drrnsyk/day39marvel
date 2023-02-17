package vttp2022.workshop39marvel.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Character {

    private String id;
    private String name;
    private String imageurl;
    private String totalCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    // helper functions
    public static Character create(JsonObject data, JsonObject joResult, JsonObject thumbnail) {
        Character c = new Character();
        c.setId(Integer.toString(joResult.getInt("id")));
        c.setName(joResult.getString("name"));
        c.setImageurl(thumbnail.getString("path") + "." + thumbnail.getString("extension"));
        c.setTotalCount(Integer.toString(data.getInt("count")));
        return c;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("id", id)
            .add("name", name)
            .add("imageurl", imageurl)
            .add("totalResultCount", totalCount)
            .build();
    }


    
}
