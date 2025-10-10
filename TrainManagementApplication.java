package com.example.train;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.train.service.TrainService;

@SpringBootApplication
public class TrainManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner init(TrainService trainService) {
        return args -> {
            if (trainService.countTrains() == 0) {
                trainService.addTrain("101", "Chandigarh Express", "Chandigarh", "Delhi", "08:00", "12:00", 120);
                trainService.addTrain("102", "Himachal Queen", "Kalka", "Shimla", "07:30", "10:30", 80);
            }
        };
    }
}
