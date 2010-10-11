package net.skytelecom.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtils {

    public static Date stringToDate(String dateString, String dateFormat) {
        Date date = null;
        if(dateString == null)
            return null;
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat(dateFormat);
            date = new java.sql.Date(formatter.parse(dateString).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    public static String nowString(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    public static String increaseDateString(String dateFormat, int value) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        cal.add(Calendar.DATE, value);
        return sdf.format(cal.getTime());
    }

    public static Date nowDate() {
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        return sqlDate;
    }

    public static Date increaseDate(java.sql.Date date, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, value);
        Date d = new Date(cal.getTime().getTime());
        return d;
    }
}

