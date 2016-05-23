package com.NonSeleniumMethods;

import java.util.Calendar;
import java.util.HashMap;


public class CustomDateOperators {

    public static String getMonthJsonFileName(int decrement){
        int year;
        int currentMonth;
        String stringMonth;
        HashMap<String, Integer> monthMap = new HashMap<String, Integer>();
        monthMap.put("01",  31);
        monthMap.put("02",  28);
        monthMap.put("03",  31);
        monthMap.put("04",  30);
        monthMap.put("05",  31);
        monthMap.put("06",  30);
        monthMap.put("07",  31);
        monthMap.put("08",  31);
        monthMap.put("09",  30);
        monthMap.put("10",  31);
        monthMap.put("11",  30);
        monthMap.put("12",  31);

        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);

        if((cal.get(Calendar.MONTH) + 1 - decrement) <= 0) {
            year--;
            decrement = decrement - 12;
        }

        if((year%4 == 0 && year%100 !=0) || year%400 == 0)
            monthMap.put("February", 29);

        year = year - 2000;

        currentMonth = cal.get(Calendar.MONTH) + 1 - decrement;

        if(currentMonth <= 9) {
            stringMonth = "0" + Integer.toString(currentMonth);
        }
        else {
            stringMonth = Integer.toString(currentMonth);
        }

        return "01" + stringMonth + Integer.toString(year) + "-" +
                Integer.toString(monthMap.get(stringMonth)) + stringMonth + Integer.toString(year);

    }

    public static String getPeriodJsonFileName(String[] from, String[] to ){

        return from[1] + from[0] + from[2].substring(2) + "-" + to[1] + to[0] + to[2].substring(2);

    }

    public static String getMonthName(int decrement) {
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        int arrayMember = cal.get(Calendar.MONTH) - decrement;
        if(arrayMember <= 0)
            arrayMember = arrayMember + 12;

        return monthName[arrayMember];
    }
}
