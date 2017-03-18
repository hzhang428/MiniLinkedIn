package com.example.haozhang.minilinkedin;

import com.example.haozhang.minilinkedin.util.DateUtils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    DateUtils dateUtils = new DateUtils();
    String dateString = "04/1988";
    @Test
    public void dateUtilsTest() {
        Date transformed = dateUtils.stringToDate(dateString);
//        Date testDate = new Date(1988 - 1900,4,23);
        System.out.println(transformed.toString());

        System.out.println(DateUtils.dateToString(transformed));
//        assertEquals(transformed,testDate);
    }
}