package vttp2022.workshop39marvel.controller;

import org.springframework.stereotype.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2022.workshop39marvel.service.MarvelService;
import vttp2022.workshop39marvel.model.Character;;

@Controller
@RequestMapping(path="/api")
public class MarvelController {

    @Autowired
    private MarvelService marvelSvc;

    @GetMapping(value="/characters", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getCharacters(@RequestParam String searchTerm, @RequestParam String limit, @RequestParam String offset) {
        
        System.out.printf(">>> query param: searchTerm=%s\n", searchTerm);
        System.out.printf(">>> query param: limit=%s\n", limit);
        System.out.printf(">>> query param: offset=%s\n", offset);

        List<Character> characters = marvelSvc.getCharactersBySearchTerm(searchTerm, limit, offset);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        characters.stream() 
            .forEach(c -> {
                arrBuilder.add(c.toJson());
            });
        
        return ResponseEntity.ok(arrBuilder.build().toString());

    }

}
