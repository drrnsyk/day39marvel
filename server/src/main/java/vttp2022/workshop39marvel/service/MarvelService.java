package vttp2022.workshop39marvel.service;

import java.io.Reader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.HexFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import vttp2022.workshop39marvel.model.Character;;

@Service
public class MarvelService {
    
    // API call from Marvel
    public static final String URL = "https://gateway.marvel.com:443/v1/public/characters";

    // inject in api key using envrioment variables
    @Value("${PUBLIC_KEY}") // also the api key
    private String publicKey;

    @Value("${PRIVATE_KEY}") // required for the hash generation
    private String privateKey;

    public List<Character> getCharactersBySearchTerm(String searchTerm, String limit, String offset) {
        
        String payload;

        System.out.println("Getting characters from Marvel");

        try {
            // construct the query string
            // marvel requires addtional param of ts (long string timestamp) and hash md5(ts+privateKey+publicKey)
            
            Long ts = System.currentTimeMillis();
            String signature = "%d%s%s".formatted(ts, privateKey, publicKey);
            String hash = "";
            // Message digest = md5, sha1, sha512
            // Get an instance of MD5
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // Calculate our hash
            // Update our message digest
            md5.update(signature.getBytes());
            // Get the MD5 digest
            byte[] h = md5.digest();
            // Stringify the MD5 digest
            hash = HexFormat.of().formatHex(h);

            String url = UriComponentsBuilder.fromUriString(URL)
                .queryParam("nameStartsWith", URLEncoder.encode(searchTerm, "UTF-8"))
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .queryParam("ts", ts)
                .queryParam("apikey", publicKey)
                .queryParam("hash", hash)
                .toUriString();

            RequestEntity<Void> req = RequestEntity.get(url).build();

            RestTemplate template = new RestTemplate();
            ResponseEntity<String> resp;
            // Throws an exception if status code not in between 200 - 399
            resp = template.exchange(req, String.class);
            payload = resp.getBody();
            // System.out.println("payload: " + payload);

        } catch (Exception ex) {
            System.err.printf("Error: %s\n", ex.getMessage());
            return Collections.emptyList();
        }

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);

        JsonObject jo = jsonReader.readObject();

        System.out.println("---------------------------------------");
        System.out.println(jo);
        System.out.println("---------------------------------------");

        JsonObject data = jo.getJsonObject("data");
        JsonArray results = data.getJsonArray("results");
        

        List<Character> characters = new LinkedList<>();

        for (int i = 0; i < results.size(); i++) {
            JsonObject joResult = results.getJsonObject(i);
            JsonObject thumbnail = joResult.getJsonObject("thumbnail");
            characters.add(Character.create(data, joResult, thumbnail));
        }

        return characters;

    }

}
