package com.tau.rest3d.Owners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class OwnerConfig {

    @Bean
    CommandLineRunner commandLineRunner(OwnerRepository repository){
        return  args -> {
            Owner dbOwn = new Owner("name","dbOwn@öail.com", LocalDate.of(1000, Month.DECEMBER,23),"testPassword");
            Owner dbOwn1 = new Owner("name1","dbOwn@öail1.com", LocalDate.of(1001, Month.DECEMBER,23),"testPassword1");
            Owner dbOwn2 = new Owner("name2","dbOwn@öail2.com", LocalDate.of(1002, Month.DECEMBER,23),"testPassword2");

            repository.saveAll(List.of(dbOwn,dbOwn2,dbOwn1));

        };
    }
}
