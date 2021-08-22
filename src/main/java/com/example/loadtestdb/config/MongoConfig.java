package com.example.loadtestdb.config;


import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.List;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.example.loadtestdb.repository")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Bean
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings
                .builder()
                .applyToClusterSettings(builder -> builder.hosts(List.of(new ServerAddress("mongo1", 27017), new ServerAddress("mongo2", 27018), new ServerAddress("mongo3", 27019))))
                .build();
        return MongoClients.create(settings);

    }

    @Override
    protected String getDatabaseName() {
        return "reactive";
    }
}
