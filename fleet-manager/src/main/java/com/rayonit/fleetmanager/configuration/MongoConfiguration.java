package com.rayonit.fleetmanager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses= com.rayonit.fleetmanager.domain.user.model.User.class)
@Configuration
public class MongoConfiguration {

}
