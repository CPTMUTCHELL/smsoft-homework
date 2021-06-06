package com.smsoft;

import com.smsoft.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication

public class RestServiceCsvApplication implements CommandLineRunner {
    @Autowired
    private  CsvService service;

    public static void main(String[] args) {
        SpringApplication.run(RestServiceCsvApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.parseCSV();
    }

}
