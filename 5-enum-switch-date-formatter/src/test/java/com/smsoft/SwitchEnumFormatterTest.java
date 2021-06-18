package com.smsoft;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SwitchEnumFormatterTest {
    private SwitchEnumFormatter formatter;
    @Before
    public void setUp()  {
        formatter = new SwitchEnumFormatter();


    }
    @Test
    public void enumFormatterTest() throws DatatypeConfigurationException, ParseException {
        Assert.assertTrue(formatter.enumFormatter("2021.06.15",DateType.DATE) instanceof Date);
        Assert.assertTrue(formatter.enumFormatter("2021.06.15",DateType.SQL_DATE) instanceof java.sql.Date);
        Assert.assertTrue(formatter.enumFormatter("2021.06.15",DateType.XML_GREGORIAN_CALENDAR) instanceof XMLGregorianCalendar);
        Assert.assertTrue(formatter.enumFormatter("2021.06.15",DateType.LOCAL_DATE) instanceof LocalDate);
        Assert.assertTrue(formatter.enumFormatter("2021.06.15",DateType.LOCAL_DATE_TIME) instanceof LocalDateTime);
        Assert.assertTrue(formatter.enumFormatter("2021.06.15",DateType.ZONED_DATE_TIME) instanceof ZonedDateTime);
        Assert.assertTrue(formatter.enumFormatter("2021.06.15",DateType.OFFSET_DATE_TIME) instanceof OffsetDateTime);
        Assert.assertTrue(formatter.enumFormatter("2021.06.15",DateType.CALENDAR) instanceof Calendar);
        Assert.assertTrue(formatter.enumFormatter("2021.06.15",DateType.INSTANT) instanceof Instant);
        Assert.assertTrue(formatter.enumFormatter("2021.06.15",DateType.GREGORIAN_CALENDAR) instanceof GregorianCalendar);



    }
    @Test
    public void switchFormatterTest() throws DatatypeConfigurationException, ParseException {
        Assert.assertTrue(formatter.switchFormatter("2021.06.15",DateType.DATE) instanceof Date);
        Assert.assertTrue(formatter.switchFormatter("2021.06.15",DateType.SQL_DATE) instanceof java.sql.Date);
        Assert.assertTrue(formatter.switchFormatter("2021.06.15",DateType.XML_GREGORIAN_CALENDAR) instanceof XMLGregorianCalendar);
        Assert.assertTrue(formatter.switchFormatter("2021.06.15",DateType.LOCAL_DATE) instanceof LocalDate);
        Assert.assertTrue(formatter.switchFormatter("2021.06.15",DateType.LOCAL_DATE_TIME) instanceof LocalDateTime);
        Assert.assertTrue(formatter.switchFormatter("2021.06.15",DateType.ZONED_DATE_TIME) instanceof ZonedDateTime);
        Assert.assertTrue(formatter.switchFormatter("2021.06.15",DateType.OFFSET_DATE_TIME) instanceof OffsetDateTime);
        Assert.assertTrue(formatter.switchFormatter("2021.06.15",DateType.CALENDAR) instanceof Calendar);
        Assert.assertTrue(formatter.switchFormatter("2021.06.15",DateType.INSTANT) instanceof Instant);
        Assert.assertTrue(formatter.switchFormatter("2021.06.15",DateType.GREGORIAN_CALENDAR) instanceof GregorianCalendar);
    }

}