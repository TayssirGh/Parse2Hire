package com.dist.interview.javacc.dal.mongodb.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean
    public MongoDatabase mongoDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase("test");
    }
}
