package com.desertgm.app.DBConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.desertgm.app.Repositories.prod", mongoTemplateRef = "primaryMongoTemplate")
public class ProdDbConfig {

    @Value("${prod.datasource.uri}")
    private String uri;

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(uri));
    }
}
