package com.smsoft.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Service {
    private final static String DATE_FORMAT = "yyyy-MM-dd-hh";

    public static boolean flag;
    public static int counter;
    public static void main(String[] args) throws IOException {
        Transaction transaction = null;
        var records = parseCSVFileToRecords(args[0]);
        try (SessionFactory factory = Factory.getSessionFactory();
             var session = factory.openSession()){
            transaction = session.beginTransaction();
            session.createQuery("delete from Record ").executeUpdate();
            for (counter = 0; counter < 10000; counter++) {
                if (counter % 1000 == 0) {

                    Thread.sleep(1000);
                }
                session.save(records.get(counter));

            }
            transaction.commit();

            flag = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    static Date convertFromCSVFormat(String date) {
        try {
            var format = new SimpleDateFormat(DATE_FORMAT);
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Record> parseCSVFileToRecords(String path) throws IOException {
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            flag=true;
            e.printStackTrace();
        }
        var records = new ArrayList<Record>();
        file.readLine();
        String line;
        while ((line = file.readLine()) != null) {
            records.add(new Record(line));
        }
        file.close();
        return records;
    }


}
