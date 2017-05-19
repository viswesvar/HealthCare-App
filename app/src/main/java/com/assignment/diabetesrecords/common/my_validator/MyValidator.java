package com.assignment.diabetesrecords.common.my_validator;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by Calculator Free on 11/24/16.
 */
public class MyValidator {

    public static  String getDateInddmmyyyy(String dateInyyyymmdd)
    {
        //output will be "2016-12-31"
        String result="";
        String sDay="", sMonth="", sYear="";
        String[] arrDate = dateInyyyymmdd.split("-");

        sDay = arrDate[2].toString();
        sMonth = arrDate[1].toString();
        sYear = arrDate[0].toString();

        if(sDay.length() == 1)
        {
            sDay = "0" +sDay;
        }

        if(sMonth.length() == 1)
        {
            sMonth = "0" +sMonth;
        }

        if(sYear.length() == 2)
        {
            sYear = "20" +sYear;
        }


        result =sDay + "-" + sMonth + "-" + sYear;
        return result;
    }

    //------
    public static  String getTimeInAMPMFormat(String timeIn24HoursFormat)
    {
        String result="";
        if(timeIn24HoursFormat.length() == 0)
        {
            return result;
        }

        String[] arrTime = timeIn24HoursFormat.split(":");


        String sAMPM= "", sHours="00", sMinutes="00";
        int iHours=0, iMinutes=0;

        iHours= Integer.valueOf(arrTime[0].toString());
        iMinutes= Integer.valueOf(arrTime[1].toString());

        if((iHours==0) && (iMinutes==0))
        {
            return "";
        }

        sMinutes = arrTime[1].toString();

        if(iHours > 12)
        {
            sAMPM= "PM";
            iHours = iHours - 12;
        }
        else
        {
            sAMPM= "AM";
        }



        sHours = String.valueOf(iHours);

        if(sHours.length() ==1)
        {
            sHours= "0"+sHours;
        }

        if(sMinutes.length() ==1)
        {
            sMinutes= "0"+sMinutes;
        }



        result =sHours + ":" + sMinutes + " " + sAMPM;
        return result;
    }
    //-----------------
    public static  String getDateInyyyymmdd(int day, int month, int year)
    {
        //output will be "2016-12-31"
        String result="";
        String sDay="", sMonth="", sYear="";
        sDay = String.valueOf(day);
        sMonth = String.valueOf(month);
        sYear = String.valueOf(year);

        if(sDay.length() == 1)
        {
            sDay = "0" +sDay;
        }

        if(sMonth.length() == 1)
        {
            sMonth = "0" +sMonth;
        }

        if(sYear.length() == 2)
        {
            sYear = "20" +sYear;
        }


        result =sYear + "-" + sMonth + "-" + sDay;
        return result;
    }
    //--------------------
    public static  String getTimeIn24HoursFormat(String timeInAMPMFormat)
    {
        String result="00:00:00";
        if(timeInAMPMFormat.length() == 0)
        {
            return result;
        }

        String sAMPM= "", sHours="00", sMinutes="00", sSeconds="00";
        int iHours=0;
        sAMPM = timeInAMPMFormat.substring(timeInAMPMFormat.length()-2, timeInAMPMFormat.length());
        iHours= Integer.valueOf(timeInAMPMFormat.substring(0,timeInAMPMFormat.indexOf(":")));
        sMinutes = timeInAMPMFormat.substring(timeInAMPMFormat.indexOf(":")+1,timeInAMPMFormat.indexOf(" "));
        if(sAMPM.equals("PM"))
        {
            iHours += 12;
        }

        sHours = String.valueOf(iHours);

        if(sHours.length() ==1)
        {
            sHours= "0"+sHours;
        }

        if(sMinutes.length() ==1)
        {
            sMinutes= "0"+sMinutes;
        }



        result =sHours + ":" + sMinutes + ":" + sSeconds;
        return result;
    }

    public static  String getDateInyyyymmdd(String dateInddmmyyyy)
    {
        //output will be "2016-12-31"
        String result="";
        String sDay="", sMonth="", sYear="";
        String[] arrDate = dateInddmmyyyy.split("-");

        sDay = arrDate[0].toString();
        sMonth = arrDate[1].toString();
        sYear = arrDate[2].toString();

        if(sDay.length() == 1)
        {
            sDay = "0" +sDay;
        }

        if(sMonth.length() == 1)
        {
            sMonth = "0" +sMonth;
        }

        if(sYear.length() == 2)
        {
            sYear = "20" +sYear;
        }


        result =sYear + "-" + sMonth + "-" + sDay;
        return result;
    }

    public static boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


    public static String getCurrentDateInDDMMYYYY()
    {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        String date = day+"-"+month+"-"+year;

        return date;

    }

    public static String getCurrentTimeInAMPMFormat()
    {
        Calendar c = Calendar.getInstance();

        int seconds = c.get(Calendar.SECOND);
        int minutes = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);

        String sAMPM= "";
        if(hour > 12)
        {
            sAMPM= "PM";
            hour = hour - 12;
        }
        else
        {
            sAMPM= "AM";
        }
        String time = hour+":"+minutes+" "+sAMPM;
        return time;

    }

    public static boolean isBreakfastTime(int iHrs)
    {
        boolean result=false;

        if((iHrs >= 6 ) && (iHrs <= 11))
        {
            result=true;
        }


        return  result;
    }


    public static boolean isLunchTime(int iHrs)
    {
        boolean result=false;

        if((iHrs >= 12 ) && (iHrs <= 16))
        {
            result=true;
        }


        return  result;
    }


    public static boolean isDinnerTime(int iHrs)
    {
        boolean result=false;

        if((iHrs >= 17 ) && (iHrs <= 23))
        {
            result=true;
        }


        return  result;
    }

    public static String getDateInYYYYMMDDFormat(String Dt)
    {
        String output="";
        String [] arrDate = Dt.split("-");
        String yr=arrDate[2].toString() ;
        String month=String.valueOf(Integer.valueOf(arrDate[1].toString()));
        String day=arrDate[0].toString() ;

        if(yr.length() ==2)
        {
            yr="00"+yr;
        }

        if(month.length() ==1)
        {
            month="0"+month;
        }

        if(day.length() ==1)
        {
            day="0"+day;
        }

        output = yr+month+day;

        return  output;
    }

    public static String getWeekDay2Letter(String weekDay3Letter)
    {
        String output="";

        switch (weekDay3Letter)
        {
            case "Mon":
                output="MO";
                break;
            case "Tue":
                output="TU";
                break;
            case "Wed":
                output="WE";
                break;
            case "Thu":
                output="TH";
                break;
            case "Fri":
                output="FR";
                break;
            case "Sat":
                output="SA";
                break;
            case "Sun":
                output="SU";
                break;
        }

        return  output;
    }

}
