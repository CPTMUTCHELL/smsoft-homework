package com.smsoft;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class Formatter {
    public String beautyDate(Object date, String format) {
        if (date instanceof Date d) return (convert(d, format));

        if (date instanceof LocalDateTime d) return (convert(convertLocalDateTimeToDate(d), format));

        if (date instanceof LocalDate d) return (convert(convertLocalDateToDate(d), format));

        if (date instanceof OffsetDateTime d) return (convert(convertOffsetDateToDate(d), format));

        if (date instanceof Instant d) return (convert(convertInstantToDate(d), format));
        if (date instanceof Calendar d) return (convert((d.getTime()), format));
        if (date instanceof XMLGregorianCalendar d) return (convert(d.toGregorianCalendar().getTime(), format));
        return "";
    }

    private String convert(Date dateToConvert, String f) {
        SimpleDateFormat formatter = new SimpleDateFormat(f);
        return formatter.format(dateToConvert);
    }

    private Date convertLocalDateTimeToDate(LocalDateTime d) {

        return Date.from(d.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date convertLocalDateToDate(LocalDate d) {

        return Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Date convertOffsetDateToDate(OffsetDateTime d) {

        return Date.from(d.toInstant());

    }

    private Date convertInstantToDate(Instant d) {
        return Date.from(d);
    }


}
