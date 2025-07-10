package com.mursalin.chat_app.config;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class MongoTTLIndexInitializer {

    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void createTTLIndex() {
        MongoDatabase database = mongoTemplate.getDb();
        MongoCollection<Document> collection = database.getCollection("password_reset_tokens");

        IndexOptions indexOptions = new IndexOptions()
                .expireAfter(0L, TimeUnit.SECONDS)
                .name("expiresAt_ttl_index");

        collection.createIndex(Indexes.ascending("expiresAt"), indexOptions);
    }
}
