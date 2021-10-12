package com.rayonit.fleetmanager;

import com.rayonit.fleetmanager.general.classes.RouteGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


@SpringBootApplication(scanBasePackages={
		"com.rayonit.fleetmanager.controllers",
		"com.rayonit.fleetmanager.domain",
		"com.rayonit.fleetmanager.models",
		"com.rayonit.fleetmanager.security",
		"com.rayonit.fleetmanager.configuration",
        "com.rayonit.fleetmanager.general"
})
@EnableMongoRepositories
@EnableReactiveMongoRepositories
@EnableScheduling
public class FleetManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FleetManagerApplication.class, args);
	}


}
