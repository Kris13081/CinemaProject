package com.kris.cinema.model;

import com.kris.cinema.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner( CustomerRepository repository){
        return args -> {
            Customer Kris = new Customer(
                    "Kris",
                    "kris@mail.bg",
                    19
            );
            Customer Peter = new Customer(
                    "Peter",
                    "peter@abv.bg",
                    17
            );
            repository.saveAll(
                    List.of(Kris, Peter)
            );
        };
    }

}
