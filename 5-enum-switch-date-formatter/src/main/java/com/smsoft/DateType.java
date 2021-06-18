package com.smsoft;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public enum DateType {

    XML_GREGORIAN_CALENDAR {
        @Override
        Object checkDate(String date) throws ParseException, DatatypeConfigurationException {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(getDate(date));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        }
    },
    GREGORIAN_CALENDAR {
        @Override
        Object checkDate(String date) throws ParseException {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(getDate(date));
            return cal;
        }
    },
    SQL_DATE {
        @Override
        Object checkDate(String date) throws ParseException {
            return new java.sql.Date(getDate(date).getTime());
        }
    },
    CALENDAR {
        @Override
        Object checkDate(String date) throws ParseException {

            Calendar cal = Calendar.getInstance();
            cal.setTime(getDate(date));
            return cal;
        }
    },
    INSTANT {
        @Override
        Object checkDate(String date) throws ParseException {
            return getDate(date).toInstant();
        }
    },
    ZONED_DATE_TIME {
        @Override
        Object checkDate(String date) throws ParseException {

            return getDate(date).toInstant().atZone(ZoneOffset.UTC);
        }
    },
    OFFSET_DATE_TIME {
        @Override
        Object checkDate(String date) throws ParseException {
            return OffsetDateTime.ofInstant(getDate(date).toInstant(), ZoneId.of("UTC"));
        }
    },

    DATE {
        @Override
        Object checkDate(String date) throws ParseException {
            return getDate(date);
        }
    },
    LOCAL_DATE {
        @Override
        Object checkDate(String date) {
            return LocalDate.parse(date, getFormatter());
        }
    }, LOCAL_DATE_TIME {
        @Override
        Object checkDate(String date) {

            return LocalDate.parse(date, getFormatter()).atStartOfDay();
        }
    };

    abstract Object checkDate(String date) throws ParseException, DatatypeConfigurationException;

    Date getDate(String date) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd");
        return f.parse(date);
    }

    DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy.MM.dd");
    }


}
