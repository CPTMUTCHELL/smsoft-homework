package com.smsoft;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FormatterTest {
    private Formatter formatter;
    private String pattern1="yyyy.dd.MM";
    private String pattern2="yyyy/MM/dd";
    private Calendar c=Calendar.getInstance();
    private XMLGregorianCalendar xmlGregorianCalendar=DatatypeFactory.newInstance().newXMLGregorianCalendar();

    public FormatterTest() throws DatatypeConfigurationException {
    }


    @Before
    public void setUp(){
        formatter=new Formatter();
        c.set(2000, Calendar.NOVEMBER,22);
        xmlGregorianCalendar.setDay(1);
        xmlGregorianCalendar.setHour(22);
        xmlGregorianCalendar.setMonth(2);
        xmlGregorianCalendar.setYear(2021);
    }
    @Test
    public void beautyDate()  {
        Assert.assertEquals(formatter.beautyDate(LocalDateTime.of(2000,11,20,11,22),pattern1),"2000.20.11");
        Assert.assertEquals(formatter.beautyDate(LocalDate.of(2000,11,10),pattern2),"2000/11/10");
        Assert.assertEquals(formatter.beautyDate(new Date(1881, Calendar.DECEMBER,11),pattern2),"3781/12/11");
        Assert.assertEquals(formatter.beautyDate(OffsetDateTime.of
                (LocalDate.of(2020,1,1), LocalTime.of(11,22), ZoneOffset.ofHours(1)),pattern1),"2020.01.01");
        Assert.assertEquals(formatter.beautyDate(new GregorianCalendar(2000, Calendar.NOVEMBER,22),pattern2),"2000/11/22");
        Assert.assertEquals(formatter.beautyDate( Instant.parse("2018-11-30T18:35:24.00Z"),pattern2),"2018/11/30");
        Assert.assertEquals(formatter.beautyDate(c,pattern2),"2000/11/22");
        Assert.assertEquals(formatter.beautyDate(new java.sql.Date(999999999),pattern2),"1970/01/12");
        Assert.assertEquals(formatter.beautyDate(xmlGregorianCalendar,pattern2),"2021/02/01");
        Assert.assertEquals(formatter.beautyDate("2001/01/01",pattern1),"");

    }
}