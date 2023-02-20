package vttp2022.workshop39marvel.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.workshop39marvel.model.InsertedComment;

@Repository
public class MarvelMongoRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public InsertedComment insertComment(InsertedComment insertedComment) {
        return mongoTemplate.insert(insertedComment, "comments");
    }

}
