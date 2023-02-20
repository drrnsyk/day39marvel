package vttp2022.workshop39marvel.model;

import java.time.LocalDateTime;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class InsertedComment {
    
    private String characterId;
    private String comment;
    private LocalDateTime postedDate;

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    // constructor
    public InsertedComment(String characterId, String comment) {
        this.characterId = characterId;
        this.comment = comment;
        this.postedDate = LocalDateTime.now();
    }


    // helper functions
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("characterId", characterId)
            .add("comment", comment)
            .add("postedDate", postedDate.toString())
            .build();
    }

}
