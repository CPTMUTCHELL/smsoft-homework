package com.smsoft;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SwitchEnumFormatter {
    private final DateTimeFormatter FORMATTER=DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public Object enumFormatter(String date, DateType dateType) throws ParseException,
            DatatypeConfigurationException {
        return dateType.checkDate(date);
    }

    public Object switchFormatter(String date, DateType dateType) throws ParseException, DatatypeConfigurationException {
        return switch (dateType) {
            case XML_GREGORIAN_CALENDAR -> getXMLGregorianCalendar(date);
            case GREGORIAN_CALENDAR -> getGregorianCalendar(date);
            case SQL_DATE -> new java.sql.Date(getDate(date).getTime());
            case CALENDAR -> getCalendar(date);
            case INSTANT -> getDate(date).toInstant();
            case ZONED_DATE_TIME -> getDate(date).toInstant().atZone(ZoneOffset.UTC);
            case OFFSET_DATE_TIME -> OffsetDateTime.ofInstant(getDate(date).toInstant(), ZoneId.of("UTC"));
            case DATE -> getDate(date);
            case LOCAL_DATE -> LocalDate.parse(date, FORMATTER);
            case LOCAL_DATE_TIME -> LocalDate.parse(date, FORMATTER).atStartOfDay();
        };
    }

     private Date getDate(String date) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd");
        return f.parse(date);
    }

    private Calendar getCalendar(String date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate(date));
        return cal;
    }

    private XMLGregorianCalendar getXMLGregorianCalendar(String date) throws ParseException, DatatypeConfigurationException {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(getDate(date));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }
    private GregorianCalendar getGregorianCalendar(String date) throws ParseException {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(getDate(date));
        return cal;
    }



}
