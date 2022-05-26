package com.tau.rest3d.users;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.tau.rest3d.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({FileStorageProperties.class})
public class User {



    private float cost;




}
