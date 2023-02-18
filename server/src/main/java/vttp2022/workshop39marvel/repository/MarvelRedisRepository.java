package vttp2022.workshop39marvel.repository;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

@Repository
public class MarvelRedisRepository {

    @Value("${character.cache.duration}")
    private Long cacheTime;

    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;
    
    public void saveToRedis(JsonArray jsonArrayCharacters) {

        // save individual character (json object) into redis individually with key as the character name
        
        // opsForValue is to save a key value pair, opsForList is to save a key list pair
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        for(int i = 0; i < jsonArrayCharacters.size(); i++) {
            JsonObject joCharacter = jsonArrayCharacters.getJsonObject(i);
            valueOp.set(joCharacter.getString("name").toLowerCase(), joCharacter.toString(), Duration.ofMinutes(cacheTime));
        }

    }

}
