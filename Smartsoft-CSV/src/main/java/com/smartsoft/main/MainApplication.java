package com.smartsoft.main;

import com.smartsoft.main.service.CSVService;
import com.smartsoft.main.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {
	@Autowired
	private DBService service;
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length!=0){
			service.loadFilesToDbSubsequently(CSVService.parseCSVFileToRecords());
		}
	}
}
