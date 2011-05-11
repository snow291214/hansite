package ru.sgnhp;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtils {

    private static final String DATE_FORMAT_NOW = "dd.MM.yyyy";

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

    public static String dateToString(java.util.Date date, String dateFormat) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        String s = formatter.format(date);
        return s;
    }

    public static String nowString(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    public static String increaseDateString(int value) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
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

