package com.smsoft;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;

public class SwitchEnumFormatterTest {
    private SwitchEnumFormatter formatter;
    @Before
    public void setUp()  {
        formatter = new SwitchEnumFormatter();


    }
    @Test
    public void enumFormatterTest() throws DatatypeConfigurationException, ParseException {
        Assert.assertEquals(formatter.enumFormatter("2021.06.15",DateType.DATE).getClass().toString(), "class java.util.Date");
        Assert.assertEquals(formatter.enumFormatter("2021.06.15",DateType.SQL_DATE).getClass().toString(), "class java.sql.Date");
        Assert.assertEquals(formatter.enumFormatter("2021.06.15",DateType.XML_GREGORIAN_CALENDAR).getClass().toString(), "class javax.xml.datatype.XMLGregorianCalendar");
        Assert.assertEquals(formatter.enumFormatter("2021.06.15",DateType.LOCAL_DATE).getClass().toString(), "class java.time.LocalDate");
        Assert.assertEquals(formatter.enumFormatter("2021.06.15",DateType.LOCAL_DATE_TIME).getClass().toString(), "class java.time.LocalDateTime");
        Assert.assertEquals(formatter.enumFormatter("2021.06.15",DateType.OFFSET_DATE_TIME).getClass().toString(), "class java.time.OffsetDateTime");
        Assert.assertEquals(formatter.enumFormatter("2021.06.15",DateType.ZONED_DATE_TIME).getClass().toString(), "class java.time.ZonedDateTime");

    }
    @Test
    public void switchFormatterTest() throws DatatypeConfigurationException, ParseException {
        Assert.assertEquals(formatter.switchFormatter("2021.06.15",DateType.DATE).getClass().toString(), "class java.util.Date");
        Assert.assertEquals(formatter.switchFormatter("2021.06.15",DateType.SQL_DATE).getClass().toString(), "class java.sql.Date");
        Assert.assertEquals(formatter.switchFormatter("2021.06.15",DateType.XML_GREGORIAN_CALENDAR).getClass().toString(), "class javax.xml.datatype.XMLGregorianCalendar");
        Assert.assertEquals(formatter.switchFormatter("2021.06.15",DateType.LOCAL_DATE).getClass().toString(), "class java.time.LocalDate");
        Assert.assertEquals(formatter.switchFormatter("2021.06.15",DateType.LOCAL_DATE_TIME).getClass().toString(), "class java.time.LocalDateTime");
        Assert.assertEquals(formatter.switchFormatter("2021.06.15",DateType.OFFSET_DATE_TIME).getClass().toString(), "class java.time.OffsetDateTime");
        Assert.assertEquals(formatter.switchFormatter("2021.06.15",DateType.ZONED_DATE_TIME).getClass().toString(), "class java.time.ZonedDateTime");
    }

}