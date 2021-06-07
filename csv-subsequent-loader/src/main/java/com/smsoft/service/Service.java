package com.smsoft.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Service {
    private final static String DATE_FORMAT = "yyyy-MM-dd-hh";

    public static boolean flag;
    public static int i;
    public static void main(String[] args) throws IOException {
        Transaction transaction = null;
        List<Record> records = parseCSVFileToRecords();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("delete from Record ").executeUpdate();


            for (i = 0; i < records.size(); i++) {
                if (i % 1000 == 0) {

                    Thread.sleep(1000);
                }
                session.save(records.get(i));
                i++;
            }
            transaction.commit();

            flag = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        HibernateUtil.shutdown();
    }


        public static Date convertFromCSVFormat (String date){
            try {
                var format = new SimpleDateFormat(DATE_FORMAT);
                return format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static List<Record> parseCSVFileToRecords () throws IOException {
            var file = new BufferedReader(new FileReader("./csv-subsequent-loader/test_case.csv"));
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
