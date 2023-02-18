package vttp2022.workshop39marvel.controller;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vttp2022.workshop39marvel.service.MarvelService;

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

        String jsonStringCharacters = marvelSvc.getCharactersBySearchTerm(searchTerm, limit, offset);

        return ResponseEntity.ok(jsonStringCharacters);

    }

    @GetMapping(value="/character/{characterId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getCharacterById(@PathVariable String characterId) {
        
        System.out.printf(">>> query param: id=%s\n", characterId);

        String jsonStringCharacter = marvelSvc.getCharacterById(characterId);

        return ResponseEntity.ok(jsonStringCharacter);
    }

}
