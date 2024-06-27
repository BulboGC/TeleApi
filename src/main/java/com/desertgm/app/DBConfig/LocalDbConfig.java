package com.desertgm.app.DBConfig;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.desertgm.app.Repositories.Imports", mongoTemplateRef = "secondaryMongoTemplate")
public class LocalDbConfig {

    @Value("${local.datasource.uri}")
    private String uri;


    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(uri));
    }
}