package com.smartsoft.main.service;

import com.smartsoft.main.model.Record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVService {

    public static List<Record> parseCSVFileToRecords() throws IOException {
        var file = new BufferedReader(new FileReader("./Smartsoft-CSV/test_case.csv"));
        var records = new ArrayList<Record>();
        file.readLine(); // Drop 1-st line
        String line;
        while ((line = file.readLine()) != null) {
            records.add(new Record(line));
        }
        file.close();
        return records;
    }

}
