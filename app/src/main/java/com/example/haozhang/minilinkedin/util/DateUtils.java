package com.example.haozhang.minilinkedin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by haozhang on 3/17/17.
 */

public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", Locale.getDefault());

    public static String dateToString(Date date) {
        return sdf.format(date);
    }

    public static Date stringToDate(String dateString) {
        try {
            return sdf.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
            return new Date(0);
        }
    }
}
